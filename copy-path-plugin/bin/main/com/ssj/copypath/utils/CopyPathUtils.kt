package com.ssj.copypath.utils

import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.ide.CopyPasteManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import java.awt.datatransfer.StringSelection

/**
 * 复制路径功能的工具类
 */
object CopyPathUtils {

    private const val NOTIFICATION_GROUP_ID = "Copy Path Notification"

    /**
     * 获取行号范围字符串
     * @param editor 编辑器
     * @return 行号字符串，格式如 "L5" 或 "L5-L10"
     */
    fun getLineRange(editor: Editor): String? {
        val document = editor.document
        val selectionModel = editor.selectionModel

        return if (selectionModel.hasSelection()) {
            // 有选中内容时，获取选中范围的起止行号
            val startLine = document.getLineNumber(selectionModel.selectionStart) + 1
            val endLine = document.getLineNumber(selectionModel.selectionEnd) + 1

            if (startLine == endLine) {
                "L$startLine"
            } else {
                "L$startLine-L$endLine"
            }
        } else {
            // 无选中时，使用光标所在行
            val caretLine = document.getLineNumber(editor.caretModel.offset) + 1
            "L$caretLine"
        }
    }

    /**
     * 获取相对路径
     * @param project 项目
     * @param file 文件
     * @return 相对路径，如果无法获取则返回完整路径
     */
    fun getRelativePath(project: Project, file: VirtualFile): String {
        val basePath = project.basePath ?: return file.path
        val filePath = file.path

        return if (filePath.startsWith(basePath)) {
            filePath.removePrefix(basePath).removePrefix("/").removePrefix("\\")
        } else {
            filePath
        }
    }

    /**
     * 复制文本到剪贴板并显示通知
     * @param project 项目
     * @param text 要复制的文本
     * @param message 通知消息
     */
    fun copyToClipboardAndNotify(project: Project, text: String, message: String) {
        // 复制到剪贴板
        CopyPasteManager.getInstance().setContents(StringSelection(text))

        // 显示通知
        try {
            NotificationGroupManager.getInstance()
                .getNotificationGroup(NOTIFICATION_GROUP_ID)
                .createNotification(message, NotificationType.INFORMATION)
                .notify(project)
        } catch (e: Exception) {
            // 如果通知组未注册，忽略错误
        }
    }
}
