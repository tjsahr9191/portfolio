package sm.portfolio.api.domain

import jakarta.persistence.*

@Entity
class Skill(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var name: String,

    @Enumerated(EnumType.STRING)
    var category: SkillCategory,

    var proficiency: Int = 0 // Optional, 0 means not displayed
)

enum class SkillCategory {
    BACKEND, DEVOPS, LANGUAGE
}

