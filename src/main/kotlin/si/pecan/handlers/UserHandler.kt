package si.pecan.handlers

import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok
import si.pecan.model.User
import si.pecan.repositories.UserRepository

@Service
class UserHandler(private val userRepository: UserRepository) {
    fun createUser(request: ServerRequest) = ok()
            .body(userRepository.saveAll(request.bodyToFlux(User::class.java)), User::class.java)

    fun getUsers(request: ServerRequest) = ok()
            .body(userRepository.findAll(), User::class.java)

    fun getUser(request: ServerRequest) = ok()
            .body(userRepository.findById(ObjectId( request.pathVariable("userId"))), User::class.java)

    fun findUser(request: ServerRequest) = ok()
            .body(userRepository.findByName(request.queryParam("name")), User::class.java)
}
