package com.intellij.lang.yang.interfaces;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.filters.ExceptionFilter;
import com.intellij.execution.filters.TextConsoleBuilder;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.process.ColoredProcessHandler;
import com.intellij.execution.process.DefaultJavaProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.icons.AllIcons;
import com.intellij.lang.Language;
import com.intellij.lang.yang.support.SupportThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.compiler.CompileContext;
import com.intellij.openapi.compiler.CompilerMessageCategory;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.ui.content.Content;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.builtInWebServer.ConsoleManager;
import org.jetbrains.jps.javac.JavacRemoteProto.Message.Response.CompileMessage;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by leon on 15-12-1.
 */
public class Pyangdis extends AnAction {

    @Override public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        Project project = e.getData(PlatformDataKeys.PROJECT);
        //Messages.showMessageDialog(project, "Hello, " + txt + "!\n I am glad to see you.", "Information", Messages
        //    .getInformationIcon());
        ProblemsViewUtil problemsViewUtil=null;
        problemsViewUtil.clearProblemsView(project);

        Document currentDoc = FileEditorManager.getInstance(project).getSelectedTextEditor().getDocument();
        VirtualFile currentFile = FileDocumentManager.getInstance().getFile(currentDoc);
        String fileName = currentFile.getPath();
        FileDocumentManager fileDocumentManager = FileDocumentManager.getInstance();
        fileDocumentManager.saveDocument(currentDoc);
        if(!currentFile.getFileType().getName().equals("Yang file")) return;
        String cmds=null;
        String includepath= Messages
            .showInputDialog(project, "Input the dependency Path(Seperate Multiple Path By':')", "Need Dependencies?", Messages.getQuestionIcon());
        if(includepath==null)
        {
        cmds = "pyang "+ fileName;
        }
        else{
            cmds= "pyang "+ "--path="+includepath+" "+fileName;
        }
        //CompileContext compileContext=null;
        //compileContext.addMessage(CompilerMessageCategory.ERROR,fileName,"Test1",1,1);
        SupportThread thread=new SupportThread();
        thread.postInfo(project,currentFile,includepath);
        thread.run();
        /**
        try {
            TextConsoleBuilder consoleBuilder = TextConsoleBuilderFactory.getInstance().createBuilder(project);
            consoleBuilder.addFilter(new ExceptionFilter(GlobalSearchScope.projectScope(project)));
            ConsoleView console = consoleBuilder.getConsole();
            //ConsoleView console1 = ConsoleManager.getInstance(project);
            Content[] curcontent=toolWindow.getContentManager().getContents();
            boolean needtocreate=true;
            for(Content con: curcontent)
            {
                if(con.getTabName().equals("Yang Compile Result"))
                {
                    con.setComponent(console.getComponent());
                    con.setCloseable(true);
                    toolWindow.getContentManager().setSelectedContent(con);
                    needtocreate=false;
                    break;
                }
            }
            if(needtocreate) {
                Content content = toolWindow.getContentManager().getFactory()
                    .createContent(console.getComponent(), "Yang Compile Result", true);
                toolWindow.getContentManager().addContent(content);
                toolWindow.getContentManager().setSelectedContent(content);
            }//console.print("test",ConsoleViewContentType.SYSTEM_OUTPUT);
            toolWindow.setAutoHide(false);
            //toolWindow.show(Runnable);

            toolWindow.setAvailable(true,null);
            toolWindow.show(null);
            //ProcessHandler
            Process ps = Runtime.getRuntime().exec(cmds);
            //ps.waitFor();

            ColoredProcessHandler
                processHandler = new ColoredProcessHandler(ps, null, Charset
                .defaultCharset());

            processHandler.startNotify();
            console.attachToProcess(processHandler);
            String line = null;
            InputStreamReader isr = new InputStreamReader(ps.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
               // Messages.showMessageDialog(project,line,"test", Messages
               //     .getInformationIcon());
                String output[]=line.split(":");

                console.print("Line:"+output[1]+" "+output[2]+": ", ConsoleViewContentType.NORMAL_OUTPUT);
                for(int i=3;i<output.length;i++)
                {
                    console.print(output[i],ConsoleViewContentType.NORMAL_OUTPUT);
                }
                console.print("\n",ConsoleViewContentType.NORMAL_OUTPUT);
            }




        } catch(Exception ioe) {
        ioe.printStackTrace();
        }
         **/

  }

    static String loadStream(InputStream in) throws IOException {
    int ptr = 0;
    in = new BufferedInputStream(in);
     StringBuffer buffer = new StringBuffer();
    while( (ptr = in.read()) != -1 ) {
         buffer.append((char)ptr);
         }
     return buffer.toString();





    }
}
