package sm.portfolio.api.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sm.portfolio.api.domain.Project
import sm.portfolio.api.repository.ProjectRepository

@Service
@Transactional(readOnly = true)
class ProjectService(
    private val projectRepository: ProjectRepository
) {
    fun getAllProjects(): List<Project> {
        return projectRepository.findAllByIsActiveOrderByStartDateDesc(true)
    }

    fun getProject(id: Long): Project {
        return projectRepository.findById(id).orElseThrow { RuntimeException("Project not found") }
    }

    fun getFeaturedProjects(): List<Project> {
        return projectRepository.findByFeaturedTrueAndIsActiveTrueOrderByStartDateDesc()
    }
}
