package com.intellij.lang.yang.interfaces;

import com.intellij.codeInsight.template.Template;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.NavigationItem;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by leon on 15-12-3.
 */
public class YangStructureViewElement implements StructureViewTreeElement, SortableTreeElement {
    private PsiElement element;

    public YangStructureViewElement(PsiElement element)
    {
        this.element=element;
    }
    @Override public Object getValue() {
        return element;
    }

    @Override public void navigate(boolean requestFocus) {
        if (element instanceof NavigationItem) {
            ((NavigationItem) element).navigate(requestFocus);
        }
    }

    @Override public boolean canNavigate() {
        return element instanceof NavigationItem &&
            ((NavigationItem)element).canNavigate();
    }

    @Override public boolean canNavigateToSource() {
        return element instanceof NavigationItem &&
            ((NavigationItem)element).canNavigateToSource();
    }

    @NotNull @Override public String getAlphaSortKey() {
        return element instanceof PsiNamedElement ? ((PsiNamedElement) element).getName() : null;
    }

    @NotNull @Override public ItemPresentation getPresentation() {
        return element instanceof NavigationItem ?
            ((NavigationItem) element).getPresentation() : null;
    }

    @NotNull @Override public TreeElement[] getChildren() {
        if (element instanceof File) {
 /**               Property[] properties = PsiTreeUtil
                    .getChildrenOfType(element, Property.class);
                List<TreeElement> treeElements = new ArrayList<TreeElement>(properties.length);
                for (Template.Property property : properties) {
                    treeElements.add(new YangStructureViewElement(property));
                }
                return treeElements.toArray(new TreeElement[treeElements.size()]);
            } else {**/
        }
        return EMPTY_ARRAY;
    }
}
