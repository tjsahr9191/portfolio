package sm.portfolio.api.domain

import jakarta.persistence.*

@Entity
class Education(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var institution: String,
    var degree: String,
    var major: String,
    var gpa: String? = null,
    var startDate: String,
    var endDate: String,
    var description: String? = null
)
