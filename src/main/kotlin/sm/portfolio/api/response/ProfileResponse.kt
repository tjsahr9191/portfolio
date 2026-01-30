package sm.portfolio.api.response

import sm.portfolio.api.domain.*

data class ProfileResponse(
    val personalInfo: PersonalInfo?,
    val skills: List<Skill>,

    val featuredProjects: List<Project>,
    val awards: List<Award>,
    val educations: List<Education>,
    val certifications: List<Certification>,
    val languageSkills: List<LanguageSkill>,
    val activities: List<Activity>
)

