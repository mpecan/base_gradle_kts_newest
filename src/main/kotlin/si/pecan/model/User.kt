package si.pecan.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
class User {
    @Id
    var id: String? = null

    lateinit var name: String
    var created: Date = Date()
}
