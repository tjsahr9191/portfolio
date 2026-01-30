package sm.portfolio.api.repository

import org.springframework.data.jpa.repository.JpaRepository
import sm.portfolio.api.domain.Award

interface AwardRepository : JpaRepository<Award, Long>
