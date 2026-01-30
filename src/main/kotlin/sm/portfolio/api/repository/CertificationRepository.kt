package sm.portfolio.api.repository

import org.springframework.data.jpa.repository.JpaRepository
import sm.portfolio.api.domain.Certification

interface CertificationRepository : JpaRepository<Certification, Long>
