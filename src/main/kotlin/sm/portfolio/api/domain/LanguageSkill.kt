package sm.portfolio.api.domain

import jakarta.persistence.*

@Entity
class LanguageSkill(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var name: String,
    var level: String,
    var date: String? = null
)
