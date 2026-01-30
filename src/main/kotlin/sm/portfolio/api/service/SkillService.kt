package sm.portfolio.api.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sm.portfolio.api.domain.Skill
import sm.portfolio.api.repository.SkillRepository

@Service
@Transactional(readOnly = true)
class SkillService(
    private val skillRepository: SkillRepository
) {
    fun getAllSkills(): List<Skill> {
        return skillRepository.findAll()
    }
}
