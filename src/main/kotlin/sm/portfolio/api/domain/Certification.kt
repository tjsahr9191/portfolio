package sm.portfolio.api.domain

import jakarta.persistence.*

@Entity
class Certification(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var name: String,
    var date: String,
    var organization: String? = null
)
