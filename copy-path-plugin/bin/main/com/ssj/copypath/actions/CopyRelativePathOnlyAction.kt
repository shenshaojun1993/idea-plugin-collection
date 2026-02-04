package com.ssj.copypath.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.ssj.copypath.utils.CopyPathUtils

/**
 * 仅复制相对路径（不包含行号）
 * 格式：src/path/file.ts
 */
class CopyRelativePathOnlyAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val editor = e.getData(CommonDataKeys.EDITOR) ?: return
        val document = editor.document
        val file = FileDocumentManager.getInstance().getFile(document) ?: return

        val relativePath = CopyPathUtils.getRelativePath(project, file)

        CopyPathUtils.copyToClipboardAndNotify(project, relativePath, "已复制: $relativePath")
    }

    override fun update(e: AnActionEvent) {
        val editor = e.getData(CommonDataKeys.EDITOR)
        e.presentation.isEnabledAndVisible = editor != null
    }
}
