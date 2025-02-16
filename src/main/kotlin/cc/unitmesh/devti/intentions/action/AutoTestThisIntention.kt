package cc.unitmesh.devti.intentions.action

import cc.unitmesh.devti.AutoDevBundle
import cc.unitmesh.devti.intentions.action.base.AbstractChatIntention
import com.intellij.temporary.getElementToAction
import cc.unitmesh.devti.intentions.action.task.TestCodeGenRequest
import cc.unitmesh.devti.intentions.action.task.TestCodeGenTask
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.progress.impl.BackgroundableProcessIndicator
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile

class AutoTestThisIntention : AbstractChatIntention() {
    override fun priority(): Int = 998
    override fun getText(): String = AutoDevBundle.message("intentions.chat.code.test.name")
    override fun getFamilyName(): String = AutoDevBundle.message("intentions.chat.code.test.family.name")

    override fun invoke(project: Project, editor: Editor?, file: PsiFile?) {
        if (editor == null || file == null) return

        val element = getElementToAction(project, editor) ?: return
        selectElement(element, editor)

        val task = TestCodeGenTask(TestCodeGenRequest(file, element, project, editor, element.text))

        ProgressManager.getInstance()
            .runProcessWithProgressAsynchronously(task, BackgroundableProcessIndicator(task))
    }
}
