import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import ru.popkov.android.core.gradleplugins.libs

class FeatureDatastoreConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            dependencies {
                add("implementation", libs.findBundle("datastore").get())
            }
        }
    }

}