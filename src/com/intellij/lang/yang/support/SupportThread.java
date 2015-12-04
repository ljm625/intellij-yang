package com.intellij.lang.yang.support;

import com.intellij.lang.yang.interfaces.ProblemsViewUtil;
import com.intellij.openapi.compiler.CompilerMessageCategory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by leon on 15-12-4.
 */
public class SupportThread  extends Thread{

    private Project project;
    private VirtualFile virtualFile;
    private String yangpath;
    private String cmds;
    private ArrayList<String> tmp=new ArrayList<String>();



    public void postInfo(Project project,VirtualFile virtualFile,String yangpath){
        this.project=project;
        this.virtualFile=virtualFile;
        this.yangpath=yangpath;
    }

    @Override public void run() {
        if(yangpath==null||yangpath.equals(""))
        {
            cmds = "pyang "+ virtualFile.getPath();
        }
        else{
            cmds= "pyang "+ "--path="+yangpath+" "+virtualFile.getPath();
        }
        try{
            Process process = Runtime.getRuntime().exec(cmds);
            read(process.getInputStream(),System.out,process);
            read(process.getErrorStream(),System.out,process);
            process.waitFor();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        if(tmp.size()!=0)
        {
            ProblemsViewUtil problemsViewUtil=new ProblemsViewUtil();
            //problemsViewUtil.clearProblemsView(project);
            //Output the error info
            for(String info:tmp)
            {
                CompilerMessageCategory category=null;
                String[] tmpinfo =info.split(":");
                if (tmpinfo.length<4) continue;
                switch(tmpinfo[2].trim()){
                    case "warning":
                        category=CompilerMessageCategory.WARNING;
                        break;
                    case "error":
                        category=CompilerMessageCategory.ERROR;
                        break;
                    case "info":
                        category=CompilerMessageCategory.INFORMATION;
                        break;
                }
                String errormsg="";
                for(int i=3;i<tmpinfo.length;i++)
                {
                    errormsg=errormsg+tmpinfo[i];
                }

                int line=Integer.parseInt(tmpinfo[1].trim());
                problemsViewUtil.addToProblemsView(project, category,errormsg,virtualFile,line,1);

            }

        }
        ToolWindow toolWindow = ToolWindowManager.getInstance(project).getToolWindow("Problems");
        toolWindow.show(null);
        super.run();
    }

    private void read(InputStream inputStream, PrintStream out,Process process) {
        try {
            BufferedReader
                reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                tmp.add(line);
                out.println(line);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
