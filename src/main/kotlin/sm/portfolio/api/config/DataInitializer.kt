package sm.portfolio.api.config

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import sm.portfolio.api.domain.*
import sm.portfolio.api.repository.*
import java.time.LocalDate

@Configuration
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
                    blogUrl = "https://velog.io/@dbtjsahr/posts",
                    profileImageUrl = "/images/KakaoTalk_20241115_193738824.jpg"
                )
            )
        }

        // Projects
        projectRepository.deleteAll()
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
                        repoUrl = "https://github.com/tjsahr9191/portfolio",
                        demoUrl = null,
                        startDate = LocalDate.now().minusWeeks(2),
                        isActive = true,
                        thumbnailUrl = "/images/portfolio-logo.png",
                        techStack = mutableListOf("Spring Boot", "Kotlin", "Nuxt 3", "Vue.js", "TailwindCSS", "H2"),
                        featured = true
                    ),
                    Project(
                        title = "AI 에이전트 기반 웹 소설 작가 보조 서비스: Stolink",
                        description = "4단계 Agent Orchestration을 통한 웹 소설 분석 및 보조 서비스",
                        content = """## 프로젝트 개요
크래프톤 정글 최종 프로젝트로 기획부터 개발까지 전 과정 참여

## 주요 성과
- **Agent Orchestration**: 추출·결합·분석·검증 4단계 Agent Orchestration 설계 및 개발
- **성능 개선**: 추출 데이터 품질 유지하며 분석 속도 211.1s → 147.4s로 약 30% 단축
- **문제 해결**: 장문 텍스트 분석 시 발생하는 문맥 손실 및 오류 해결
- **중복 방지**: 동일 인물 중복 추출 방지 로직 구현
- **비용 절감**: 불필요한 반복 분석 제거를 통한 API 호출 비용 절감
- **대용량 처리**: 메시지 큐 prefetch count 튜닝으로 동시 처리량 12배 향상 (10 → 120)
- **안정성 확보**: Redis Sorted Set·분산 락·ACK 기반 재전송으로 다중 문서의 처리 순서 보장

## 기술 스택
- **Backend**: Python, FastAPI, LangChain, RabbitMQ, Redis, PostgreSQL
- **Frontend**: React, TypeScript
- **Infra**: AWS (EC2, RDS)
""",
                        startDate = LocalDate.of(2025, 12, 1),
                        endDate = LocalDate.of(2026, 1, 31),
                        isActive = true,
                        thumbnailUrl = "/images/stolink.png",
                        techStack = mutableListOf("Python", "FastAPI", "LangChain", "RabbitMQ", "Redis", "PostgreSQL", "AWS"),
                        featured = true
                    ),
                    Project(
                        title = "농구 코트 예약 시스템: Court-Kok",
                        description = "동시성 문제를 해결한 실시간 농구 코트 예약 서비스",
                        content = """## 프로젝트 개요
크래프톤 정글 첫 프로젝트로 3일간 서비스 개발 및 배포

## 주요 성과
- **성능 튜닝**: 워커 수 튜닝으로 예약 API 처리량 60 TPS → 220 TPS로 약 3.5배 개선
- **실시간성**: WebSocket 통신으로 예약 현황 실시간 반영 구현
- **CI/CD**: GitHub Actions를 통한 CI/CD 파이프라인 구축
- **동시성 해결**: 테스트 코드 기반 동시성 문제 해결

## 기술 스택
- **Backend**: Spring Boot, JPA, MySQL, Redis
- **Frontend**: thymeleaf, JavaScript
""",
                        startDate = LocalDate.of(2025, 9, 1),
                        endDate = LocalDate.of(2025, 9, 30),
                        isActive = true,
                        thumbnailUrl = "/images/court-kok.png",
                        techStack = mutableListOf("Spring Boot", "JPA", "MySQL", "Redis", "WebSocket", "GitHub Actions"),
                        featured = true
                    ),
                    Project(
                        title = "SVD를 이용한 이미지 압축 프로젝트",
                        description = "SVD(특이값 분해)와 멀티스레딩을 활용한 고속 이미지 압축기",
                        content = """## 프로젝트 개요
특이값 분해(SVD) 이론의 실제 프로젝트 적용

## 주요 성과
- **압축 효율**: SVD 기반 이미지 압축 구현 (35KB → 30KB)
- **성능 최적화**: 스레드 풀 도입으로 처리 시간 57s → 13s로 약 77% 단축 (100장 기준)
- **동시성 제어**: 파일 생성 시 Race Condition 문제를 Lock으로 해결

## 기술 스택
- **Language**: Python (NumPy, OpenCV)
- **Concurrency**: ThreadPoolExecutor, Lock
""",
                        startDate = LocalDate.of(2024, 6, 1),
                        endDate = LocalDate.of(2024, 6, 30),
                        isActive = true,
                        thumbnailUrl = "/images/svd.png",
                        techStack = mutableListOf("Python", "NumPy", "OpenCV", "Multi-threading"),
                        featured = true
                    ),
                    Project(
                        title = "프린터 생산 공정 자동 비전 검사 시스템: Canon",
                        description = "CNN과 템플릿 매칭을 활용한 불량 검출 및 이상 탐지 시스템",
                        content = """## 프로젝트 개요
프린터 생산 기업과의 산학 협력 프로젝트

## 주요 성과
- **유연한 설계**: 전략 패턴·템플릿 메서드 패턴 적용으로 유연한 검사 프로세스 설계
- **로직 개발**: 대오더 라벨 이미지 자동 추출 로직 개발
- **시각화**: 검사 결과 시각화를 위한 GUI 제작
- **검출률 향상**: Template Matching으로 검출률 75% → 99% 개선 (1,000개 기준)
- **특징점 매칭**: SIFT + FLANN 특징점 매칭으로 검출률 70% → 96% 개선 (300개 기준)
- **이상 탐지**: OneClassSVM + VGG16 Feature Extractor를 활용하여 불량 데이터 부족 환경에서 이상 탐지 파이프라인 구축
- **정확도**: CNN 기반 분류 모델로 테스트 정확도 99.4% 달성

## 기술 스택
- **AI/Vision**: Python, PyTorch, OpenCV, Scikit-learn
- **Algorithm**: CNN, SIFT, OneClassSVM
""",
                        startDate = LocalDate.of(2024, 3, 1),
                        endDate = LocalDate.of(2024, 5, 31),
                        isActive = true,
                        thumbnailUrl = "/images/canon.png",
                        techStack = mutableListOf("Python", "PyTorch", "OpenCV", "CNN", "Design Patterns"),
                        featured = true
                    ),
                    Project(
                        title = "오픈 마켓 쇼핑몰: MarketBridge",
                        description = "대규모 트래픽 대응 및 성능 최적화를 경험한 이커머스 프로젝트",
                        content = """## 프로젝트 개요
Spring Boot, JPA 스터디 후 사이드 프로젝트로 발전

## 주요 성과
- **쿼리 최적화**: 통계 쿼리 성능 2분 35초 → 200ms로 약 775배 개선
- **처리량 개선**: 결제 API 처리량 80 TPS → 280 TPS로 약 3.5배 개선
- **동시성 제어**: 비관적·낙관적·네임드 락 성능 비교 테스트 및 재고 동시성·데드락 문제 해결
- **분산 환경**: 분산 환경 대응을 위한 ShedLock 적용
- **외부 연동**: 카카오페이 결제 API 연동
- **안정성**: JUnit 기반 테스트 커버리지 90% 유지로 안정적인 기능 확장 및 유지보수 지원
- **문서화**: Spring REST Docs로 프론트엔드 팀에 API 문서 제공

## 기술 스택
- **Backend**: Spring Boot, JPA, MySQL, Redis
- **Infra**: Docker, ShedLock
- **Tools**: Spring REST Docs, nGrinder
""",
                        startDate = LocalDate.of(2023, 12, 1),
                        endDate = LocalDate.of(2024, 2, 29),
                        isActive = true,
                        thumbnailUrl = "/images/마켓브릿지.png",
                        techStack = mutableListOf("Spring Boot", "JPA", "MySQL", "Redis", "ShedLock", "Spring REST Docs"),
                        featured = true
                    )
                )
            )

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

