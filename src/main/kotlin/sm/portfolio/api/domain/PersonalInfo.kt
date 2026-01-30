package sm.portfolio.api.domain

import jakarta.persistence.*

@Entity
class PersonalInfo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var name: String,

    var email: String,

    var phone: String? = null,

    @Column(length = 2000)
    var bio: String,

    var shortBio: String,

    var githubUrl: String? = null,

    var linkedinUrl: String? = null,

    var blogUrl: String? = null,

    var profileImageUrl: String? = null,

    var resumeUrl: String? = null
)
