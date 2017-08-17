package si.pecan.repositories

import org.bson.types.ObjectId
import org.reactivestreams.Publisher
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import si.pecan.model.User
import java.util.*


interface UserRepository: ReactiveMongoRepository<User, ObjectId> {
    fun findByName(queryParam: Optional<String>): Publisher<User>
}
