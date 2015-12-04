package com.intellij.lang.yang.interfaces;

import com.intellij.ide.structureView.StructureViewBuilder;
import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.TreeBasedStructureViewBuilder;
import com.intellij.lang.PsiStructureViewFactory;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by leon on 15-12-3.
 */
public class YangStructureViewFactory implements PsiStructureViewFactory {
    @Nullable @Override
    public StructureViewBuilder getStructureViewBuilder(PsiFile psiFile) {
        return new TreeBasedStructureViewBuilder() {
            @NotNull @Override
            public StructureViewModel createStructureViewModel(@Nullable Editor editor) {
                return new YangStructureViewModel(psiFile);
            }
        };
    }
}
