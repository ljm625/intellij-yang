package com.intellij.lang.yang.interfaces;

import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.StructureViewModelBase;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.Sorter;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;

/**
 * Created by leon on 15-12-3.
 */
public class YangStructureViewModel extends StructureViewModelBase {
    public YangStructureViewModel(@NotNull PsiFile psiFile) {
        super(psiFile,new YangStructureViewElement(psiFile));
    }
    @NotNull
    public Sorter[] getSorters() {
        return new Sorter[] {Sorter.ALPHA_SORTER};
    }

    public boolean isAlwaysShowsPlus(StructureViewTreeElement element) {
        return false;
    }

    public boolean isAlwaysLeaf(StructureViewTreeElement element) {
        return element instanceof File;
    }
}
