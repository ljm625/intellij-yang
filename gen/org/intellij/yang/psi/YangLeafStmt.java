// This is a generated file. Not intended for manual editing.
package org.intellij.yang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface YangLeafStmt extends PsiElement {

  @Nullable
  YangConfigStmt getConfigStmt();

  @Nullable
  YangDefaultStmt getDefaultStmt();

  @Nullable
  YangDescriptionStmt getDescriptionStmt();

  @NotNull
  List<YangIfFeatureStmt> getIfFeatureStmtList();

  @Nullable
  YangMandatoryStmt getMandatoryStmt();

  @NotNull
  List<YangMustStmt> getMustStmtList();

  @Nullable
  YangReferenceStmt getReferenceStmt();

  @Nullable
  YangStatusStmt getStatusStmt();

  @NotNull
  YangTypeStmt getTypeStmt();

  @Nullable
  YangUnitsStmt getUnitsStmt();

  @Nullable
  YangWhenStmt getWhenStmt();

}