package sm.portfolio.api.config

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import sm.portfolio.api.domain.*
import sm.portfolio.api.repository.*
import java.time.LocalDate

@Configuration
@Profile("!prod") // Don't run in production
class DataInitializer {

    @Bean
    fun initData(
        projectRepository: ProjectRepository,
        skillRepository: SkillRepository,

        personalInfoRepository: PersonalInfoRepository,
        awardRepository: AwardRepository,
        educationRepository: EducationRepository,
        certificationRepository: CertificationRepository,
        languageSkillRepository: LanguageSkillRepository,
        activityRepository: ActivityRepository
    ) = CommandLineRunner {
        // Personal Info
        if (personalInfoRepository.count() == 0L) {
            personalInfoRepository.save(
                PersonalInfo(
                    name = "유선목",
                    email = "tjsahr9191@naver.com",
                    phone = "+82-10-3424-5140",
                    bio = "쿼리 최적화로 775배, API 처리량 3.5배 개선 등 성능 병목을 분석하고 해결하는 백엔드 개발자입니다.\n비관적·낙관적·네임드 락 성능 비교 테스트를 수행하며, 낙관적 락에서 발생한 데드락 문제를 분석하고 해결했고, ShedLock과 Redis 분산 락으로 분산 환경에 대응했습니다.\nJUnit 기반 테스트 커버리지 90%를 유지하며 안정적인 코드 확장을 추구합니다.\nJava, Spring Boot를 주력으로 하며, AI Agent 시스템 설계 및 메시지 큐 기반 비동기 처리를 구현한 경험이 있습니다.\n크래프톤 정글에서 매주 새로운 팀과 협업하며 커뮤니케이션 역량을 키웠고, 스터디에서 배운 내용을 실제 프로젝트에 적용하며 성장하고 있습니다. 멘토링을 통해 지식을 공유하고, 학업 최우수상 수상으로 성실한 학습 태도를 증명했습니다.",
                    shortBio = "Software Engineer | Java, Kotlin, Spring Boot",
                    githubUrl = "https://github.com/tjsahr9191",
                    linkedinUrl = null,
                    blogUrl = null,
                    profileImageUrl = "/images/KakaoTalk_20241115_193738824.jpg"
                )
            )
        }

        // Projects
        if (projectRepository.count() == 0L) {
            projectRepository.saveAll(
                listOf(
                    Project(
                        title = "포트폴리오 웹사이트",
                        description = "지금 보고 계신 웹사이트입니다! Spring Boot와 Nuxt 3로 만들었습니다. 깔끔한 UI와 반응형 디자인을 갖추고 있습니다.",
                        content = """## 프로젝트 개요
이 포트폴리오 웹사이트는 제 기술 스택과 프로젝트를 보여주기 위해 제작되었습니다.

## 아키텍처
- **백엔드**: Spring Boot 3 + Kotlin + JPA
- **프론트엔드**: Nuxt 3 + Vue 3 + TailwindCSS
- **데이터베이스**: H2 (개발) / MySQL (운영)

## 주요 기능
- 🎨 모던 UI/UX 디자인
- 📱 반응형 레이아웃
- 🌙 다크모드 지원
- ⚡ SSR 최적화
""",
                        repoUrl = "https://github.com/user/portfolio",
                        demoUrl = "https://yourportfolio.com",
                        startDate = LocalDate.now().minusWeeks(2),
                        isActive = true,
                        techStack = mutableListOf("Spring Boot", "Kotlin", "Nuxt 3", "Vue.js", "TailwindCSS", "H2"),
                        featured = true
                    ),
                    Project(
                        title = "이커머스 플랫폼",
                        description = "높은 트래픽을 처리하는 풀스택 이커머스 솔루션입니다. 마이크로서비스 아키텍처를 기반으로 합니다.",
                        content = """## 프로젝트 개요
대규모 트래픽을 처리할 수 있는 이커머스 플랫폼을 설계하고 개발했습니다.

## 주요 성과
- 📈 10,000 TPS 이상 처리 가능
- 🏗️ 마이크로서비스 아키텍처 도입
- 💳 PG사 연동 결제 시스템 구축
- 📦 실시간 재고 관리 시스템

## 기술 스택
- Backend: Spring Boot, Kotlin, Redis, RabbitMQ
- Frontend: React, TypeScript
- Infra: AWS, Docker, Kubernetes
""",
                        startDate = LocalDate.now().minusMonths(6),
                        endDate = LocalDate.now().minusMonths(1),
                        isActive = true,
                        techStack = mutableListOf("Spring Boot", "Kotlin", "Redis", "RabbitMQ", "React", "TypeScript", "AWS", "Docker"),
                        featured = true
                    ),
                    Project(
                        title = "AI 문서 분석 시스템",
                        description = "LLM을 활용한 대량 문서 자동 분석 및 요약 시스템입니다.",
                        content = """## 프로젝트 개요
LangChain과 GPT API를 활용하여 대량의 문서를 자동으로 분석하고 요약하는 시스템을 개발했습니다.

## 주요 기능
- 🤖 자동 문서 분석 및 요약
- 📊 키워드 추출 및 분류
- 🔄 비동기 처리 파이프라인

## 아키텍처
멀티 에이전트 시스템을 활용하여 문서를 병렬로 처리합니다.
""",
                        startDate = LocalDate.now().minusMonths(3),
                        isActive = true,
                        techStack = mutableListOf("Python", "FastAPI", "LangChain", "Neo4j", "RabbitMQ", "PostgreSQL"),
                        featured = true
                    )
                )
            )
        }

        // Skills
        if (skillRepository.count() == 0L) {
            skillRepository.saveAll(
                listOf(
                    // Language
                    Skill(name = "Java", category = SkillCategory.LANGUAGE),
                    Skill(name = "Kotlin", category = SkillCategory.LANGUAGE),
                    // Backend
                    Skill(name = "Spring Boot", category = SkillCategory.BACKEND),
                    Skill(name = "JPA", category = SkillCategory.BACKEND),
                    Skill(name = "JUnit", category = SkillCategory.BACKEND),
                    Skill(name = "MySQL", category = SkillCategory.BACKEND),
                    Skill(name = "Redis", category = SkillCategory.BACKEND),
                    Skill(name = "RabbitMQ", category = SkillCategory.BACKEND),
                    // DevOps
                    Skill(name = "AWS EC2", category = SkillCategory.DEVOPS),
                    Skill(name = "AWS RDS", category = SkillCategory.DEVOPS),
                    Skill(name = "Docker", category = SkillCategory.DEVOPS),
                    Skill(name = "Kubernetes", category = SkillCategory.DEVOPS),
                    Skill(name = "GitHub Actions", category = SkillCategory.DEVOPS),
                    Skill(name = "k6", category = SkillCategory.DEVOPS),
                    Skill(name = "Prometheus", category = SkillCategory.DEVOPS),
                    Skill(name = "Grafana", category = SkillCategory.DEVOPS)
                )
            )
        }



        // Awards
        if (awardRepository.count() == 0L) {
            awardRepository.saveAll(
                listOf(
                    Award(title = "학업 최우수상", organization = "한양대학교 ERICA", date = "2025. 04.", description = "전체 평점 우수"),
                    Award(title = "프로젝트 장려상", organization = "메타버스 아카데미 부트캠프", date = "2022. 12.", description = "23개 팀 중 3위")
                )
            )
        }

        // Education
        if (educationRepository.count() == 0L) {
            educationRepository.saveAll(
                listOf(
                    Education(
                        institution = "크래프톤 정글",
                        degree = "수료",
                        major = "SW 엔지니어링",
                        startDate = "2025. 09.",
                        endDate = "2026. 01."
                    ),
                    Education(
                        institution = "메타버스 아카데미",
                        degree = "수료",
                        major = "XR 전공",
                        startDate = "2022. 05.",
                        endDate = "2022. 12."
                    ),
                    Education(
                        institution = "한양대학교 ERICA",
                        degree = "학사 (복수전공)",
                        major = "컴퓨터공학",
                        gpa = "4.41/4.5",
                        startDate = "2019. 09.",
                        endDate = "2026. 02."
                    ),
                    Education(
                        institution = "한양대학교 ERICA",
                        degree = "학사 (주전공)",
                        major = "응용수학과",
                        gpa = "4.1/4.5",
                        startDate = "2018. 03.",
                        endDate = "2026. 02."
                    )
                )
            )
        }

        // Certifications
        if (certificationRepository.count() == 0L) {
            certificationRepository.saveAll(
                listOf(
                    Certification(name = "정보처리기사", date = "2025. 09.", organization = "한국산업인력공단"),
                    Certification(name = "SQLD", date = "2025. 04.", organization = "한국데이터산업진흥원")
                )
            )
        }

        // Language Skills
        if (languageSkillRepository.count() == 0L) {
            languageSkillRepository.saveAll(
                listOf(
                    LanguageSkill(name = "TOEIC Speaking", level = "IM2", date = "2025. 03.")
                )
            )
        }

        // Activities
        if (activityRepository.count() == 0L) {
            activityRepository.saveAll(
                listOf(
                    Activity(title = "클린 코드 스터디", period = "24.09", description = "2인 스터디 리드. 코드 가독성과 유지보수성 향상을 위한 원칙 학습"),
                    Activity(title = "테스트 코드 스터디", period = "23.12", description = "6인 스터디 리드. Mock을 활용하여 외부 의존성을 격리한 단위 테스트 작성, 테스트 커버리지 90% 달성"),
                    Activity(title = "모던 자바 인 액션 스터디", period = "23.10 ~ 23.11", description = "6인 스터디 리드. 스트림, 람다를 학습하고 실제 프로젝트에 적용하여 가독성 높은 코드 작성"),
                    Activity(title = "디자인 패턴 스터디", period = "23.09 ~ 23.10", description = "6인 스터디 리드. 학습한 디자인 패턴을 실제 프로젝트에 적용하여 유연하고 유지보수성 높은 코드 작성"),
                    Activity(title = "Java, SpringBoot, JPA 스터디", period = "23.09 ~ 23.12", description = "6인 스터디 리드. 책과 강의를 기반으로 학습한 내용을 실제로 적용하기 위해 사이드 프로젝트 진행")
                )
            )
        }
    }
}

