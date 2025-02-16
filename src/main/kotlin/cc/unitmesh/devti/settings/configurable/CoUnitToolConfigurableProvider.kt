package cc.unitmesh.devti.settings.configurable

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.options.ConfigurableProvider
import com.intellij.openapi.project.Project

class CoUnitToolConfigurableProvider (private val project: Project) : ConfigurableProvider() {
    override fun createConfigurable(): Configurable {
        return CoUnitToolConfigurable(project)
    }
}