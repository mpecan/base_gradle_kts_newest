package si.pecan.handlers

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.winterbe.expekt.expect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import org.springframework.web.client.RestTemplate
import si.pecan.model.User
import si.pecan.spring.injector

class UserHandlerIntegrationTest : Spek({
    val inject = injector()
    val restTemplate = RestTemplate()

    val userName = "Some User"
    it("should allow for the creation of a user") {
        val response = restTemplate.postForEntity("http://localhost:${inject.getServerPort()}/api/users",
                User().apply {
                    name = userName
                }, String::class.java)
        val users: List<User> = jacksonObjectMapper().readValue(response.body, object : TypeReference<List<User>>() {})
        val user = users[0]
        expect(user.created).not.to.be.`null`
        expect(user.name).to.equal(userName)
        expect(user.id).to.not.be.`null`
    }

})
