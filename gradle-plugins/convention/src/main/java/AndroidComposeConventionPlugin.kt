import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import ru.popkov.android.core.gradleplugins.commonExtension
import ru.popkov.android.core.gradleplugins.configureAndroidCompose
import ru.popkov.android.core.gradleplugins.libs

class AndroidComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            commonExtension.apply {
                configureAndroidCompose(this)
            }
            dependencies {
                add("implementation", libs.findBundle("coil").get())
            }
        }
    }

}