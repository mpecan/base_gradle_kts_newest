package si.pecan

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.function.server.router
import si.pecan.handlers.UserHandler

@SpringBootApplication
@EnableWebFlux
class Application {
    @Bean
    fun mainRouter(userHandler: UserHandler) = router {
        contentType(MediaType.APPLICATION_JSON).nest {
            "/api".nest {
                POST("/users", userHandler::createUser)
                GET("/users", userHandler::getUsers)
                GET("/users/{userId}", userHandler::getUser)
                GET("/users/search", userHandler::findUser)
            }
        }

    }


    @Bean
    fun objectMapperBuilder(): Jackson2ObjectMapperBuilder
            = Jackson2ObjectMapperBuilder().modulesToInstall(KotlinModule(), Jdk8Module(), JavaTimeModule())
            .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
            .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .serializationInclusion(JsonInclude.Include.NON_NULL)

}


fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
