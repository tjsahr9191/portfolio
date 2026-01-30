package sm.portfolio.api.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sm.portfolio.api.repository.*
import sm.portfolio.api.response.ProfileResponse

import sm.portfolio.api.service.PersonalInfoService
import sm.portfolio.api.service.ProjectService
import sm.portfolio.api.service.SkillService

@RestController
@RequestMapping("/api/v1/profile")
class ProfileController(
    private val skillService: SkillService,

    private val personalInfoService: PersonalInfoService,
    private val projectService: ProjectService,
    private val awardRepository: AwardRepository,
    private val educationRepository: EducationRepository,
    private val certificationRepository: CertificationRepository,
    private val languageSkillRepository: LanguageSkillRepository,
    private val activityRepository: ActivityRepository
) {
    @GetMapping
    fun getProfile(): ResponseEntity<ProfileResponse> {
        val personalInfo = personalInfoService.getPersonalInfo()
        val skills = skillService.getAllSkills()

        val featuredProjects = projectService.getFeaturedProjects()
        val awards = awardRepository.findAll()
        val educations = educationRepository.findAll()
        val certifications = certificationRepository.findAll()
        val languageSkills = languageSkillRepository.findAll()
        val activities = activityRepository.findAll()
        return ResponseEntity.ok(
            ProfileResponse(
                personalInfo, skills, featuredProjects,
                awards, educations, certifications, languageSkills, activities
            )
        )
    }
}

