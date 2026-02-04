package com.ssj.copypath.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.ssj.copypath.utils.CopyPathUtils

/**
 * 复制完整路径和行号
 * 格式：C:/path/to/file.ts:L5-L10
 */
class CopyFullPathWithLineAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val editor = e.getData(CommonDataKeys.EDITOR) ?: return
        val document = editor.document
        val file = FileDocumentManager.getInstance().getFile(document) ?: return

        val filePath = file.path
        val lineRange = CopyPathUtils.getLineRange(editor)
        val result = "$filePath:$lineRange"

        CopyPathUtils.copyToClipboardAndNotify(project, result, "已复制: $result")
    }

    override fun update(e: AnActionEvent) {
        val editor = e.getData(CommonDataKeys.EDITOR)
        e.presentation.isEnabledAndVisible = editor != null
    }
}
