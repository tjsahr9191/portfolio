package sm.portfolio.api.repository

import org.springframework.data.jpa.repository.JpaRepository
import sm.portfolio.api.domain.PersonalInfo

interface PersonalInfoRepository : JpaRepository<PersonalInfo, Long> {
    fun findFirstByOrderByIdAsc(): PersonalInfo?
}
