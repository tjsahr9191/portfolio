package sm.portfolio.api.domain

import jakarta.persistence.*

@Entity
class Activity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var title: String,
    var period: String,

    @Column(length = 2000)
    var description: String,

    var category: String = "스터디" // 스터디, 대외활동 등
)
