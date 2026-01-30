package sm.portfolio.api.domain

import jakarta.persistence.*

@Entity
class Award(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var title: String,
    var organization: String,
    var date: String,
    var description: String? = null
)
