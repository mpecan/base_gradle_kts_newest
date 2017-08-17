package si.pecan.spring

import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.lifecycle.CachingMode
import org.jetbrains.spek.api.lifecycle.LifecycleAware
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper
import org.springframework.context.ApplicationContext
import org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
import org.springframework.test.context.support.DefaultBootstrapContext
import org.springframework.test.context.support.DefaultTestContext
import si.pecan.Application


@SpringBootTest(classes = arrayOf(Application::class), webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class AppConfig


class Injector(private val scope: SpecBody, testConfiguration: Class<*>? = AppConfig::class.java) {

    private val context: ApplicationContext
    init {
        val builtTestContext = SpringBootTestContextBootstrapper().apply {
            bootstrapContext = DefaultBootstrapContext(testConfiguration, DefaultCacheAwareContextLoaderDelegate())
        }.buildTestContext()
        (builtTestContext as DefaultTestContext).updateState(AppConfig(), null, null)
        context = builtTestContext.applicationContext
    }

    operator fun <T> invoke(clazz: Class<T>, mode: CachingMode = CachingMode.TEST): LifecycleAware<T> {
        return scope.memoized(mode) {
            context.getBean(clazz)
        }
    }

    fun getServerPort() = 8080

}


inline fun SpecBody.injector(testConfiguration: Class<*>? = null) = testConfiguration?.let { Injector(this, testConfiguration) } ?: Injector(this)
