package sm.portfolio.api.repository

import org.springframework.data.jpa.repository.JpaRepository
import sm.portfolio.api.domain.Project

interface ProjectRepository : JpaRepository<Project, Long> {
    fun findAllByIsActiveOrderByStartDateDesc(isActive: Boolean): List<Project>
    fun findByFeaturedTrueAndIsActiveTrueOrderByStartDateDesc(): List<Project>
}
