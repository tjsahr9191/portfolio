package sm.portfolio.api.domain

import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Project(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var title: String,

    @Column(length = 2000)
    var description: String, // Short description

    @Lob
    var content: String, // Detailed content (Markdown/HTML)

    var thumbnailUrl: String? = null,

    var repoUrl: String? = null,

    var demoUrl: String? = null,

    var startDate: LocalDate,

    var endDate: LocalDate? = null, // Null if ongoing

    var isActive: Boolean = true,

    @ElementCollection
    @CollectionTable(name = "project_techs", joinColumns = [JoinColumn(name = "project_id")])
    @Column(name = "tech")
    var techStack: MutableList<String> = mutableListOf(),

    var featured: Boolean = false
)
