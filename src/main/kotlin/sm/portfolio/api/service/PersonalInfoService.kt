package sm.portfolio.api.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sm.portfolio.api.domain.PersonalInfo
import sm.portfolio.api.repository.PersonalInfoRepository

@Service
@Transactional(readOnly = true)
class PersonalInfoService(
    private val personalInfoRepository: PersonalInfoRepository
) {
    fun getPersonalInfo(): PersonalInfo? {
        return personalInfoRepository.findFirstByOrderByIdAsc()
    }
}
