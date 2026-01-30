package sm.portfolio.api.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sm.portfolio.api.domain.Project
import sm.portfolio.api.service.ProjectService

@RestController
@RequestMapping("/api/v1/projects")
class ProjectController(
    private val projectService: ProjectService
) {
    @GetMapping
    fun getProjects(): ResponseEntity<List<Project>> {
        return ResponseEntity.ok(projectService.getAllProjects())
    }

    @GetMapping("/{id}")
    fun getProject(@PathVariable id: Long): ResponseEntity<Project> {
        return ResponseEntity.ok(projectService.getProject(id))
    }
}
