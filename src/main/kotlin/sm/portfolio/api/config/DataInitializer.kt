package sm.portfolio.api.config

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import sm.portfolio.api.domain.*
import sm.portfolio.api.repository.*
import java.time.LocalDate

@Configuration
class DataInitializer {

    private val logger = LoggerFactory.getLogger(DataInitializer::class.java)

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
        logger.info("========== DataInitializer STARTED ==========")
        
        // Personal Info
        logger.info("Deleting all personal info...")
        personalInfoRepository.deleteAll()
        logger.info("Saving personal info...")
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

        // Projects
        projectRepository.deleteAll()
        projectRepository.saveAll(
                listOf(
                    Project(
                        title = "인프라 구축 및 포트폴리오 개발",
                        description = "Kotlin과 Kubernetes 학습을 위해 3일간 집중 개발하여 배포한 풀스택 프로젝트",
                        content = """## 🔍 프로젝트 소개

**자신의 기술적 한계를 넓히기 위해, 익숙하지 않은 Kotlin과 Kubernetes를 3일(26.01.30 ~ 26.02.01)이라는 짧은 기간 동안 집중적으로 학습하고 이를 실제 운영 환경에 배포까지 성공시킨 프로젝트입니다.**

단순한 기능 구현을 넘어, **Spring Boot 3 + Kotlin** 기반의 백엔드 아키텍처를 설계하고, **GitHub Actions**와 **K3s(경량 Kubernetes)** 를 활용해 CI/CD 파이프라인부터 SSL 보안 적용까지 완전한 자동화 배포 환경을 구축했습니다.

---

## 🏗️ 시스템 아키텍처

![시스템 아키텍처](/images/portfolio-architecture.png)

---

## 👨‍💻 역할 및 기간

| 구분 | 내용 |
|------|------|
| 프로젝트 유형 | 개인 프로젝트 |
| 개발 기간 | 2026. 01. 30 ~ 2026. 02. 01 (3일) |
| 담당 역할 | 풀스택 개발 및 인프라 구축 (1인 개발) |

---

## 🏆 주요 성과

- **초단기 신기술 도입 및 적용**: 3일 만에 Kotlin 문법 숙지부터 백엔드 개발, K3s 클러스터 구축까지 완료
- **완전 자동화된 배포 시스템 구축**: GitHub Actions를 통해 코드 푸시부터 배포까지 원클릭 파이프라인 구현
- **보안 및 안정성 확보**: cert-manager와 Let's Encrypt를 연동하여 SSL 인증서 자동 발급 및 갱신 체계 구축

---

## 🛠 기술적 도전 및 구현

### 1️⃣ Kotlin & Spring Boot 3 백엔드 개발
- **Java 대비 간결한 문법 활용**: Data Class, Smart Cast, Null Safety 등 Kotlin 고유 기능을 통해 코드 생산성 향상

### 2️⃣ Kubernetes (K3s) 인프라 구축
- 리소스 효율성을 고려하여 경량화된 K3s 클러스터 도입
- Deployment, Service, Ingress 등 K8s 리소스를 직접 정의하고 관리

### 3️⃣ CI/CD & DevOps
- **GitHub Actions**: 빌드, 컨테이너 이미지 생성(Docker), 배포 프로세스 자동화
- **SSL/TLS**: cert-manager를 활용한 HTTPS 보안 통신 적용
""",
                        repoUrl = "https://github.com/tjsahr9191/portfolio",
                        demoUrl = "https://numkyelee.store",
                        startDate = LocalDate.of(2026, 1, 30),
                        endDate = LocalDate.of(2026, 2, 1),
                        isActive = true,
                        thumbnailUrl = "/images/portfolio-logo.png",
                        techStack = mutableListOf("Kotlin", "Spring Boot 3", "Kubernetes", "K3s", "GitHub Actions", "Nuxt 3"),
                        featured = true
                    ),

                    Project(
                        title = "AI 에이전트 기반 웹 소설 작가 보조 서비스: Stolink",
                        description = "4단계 Agent Orchestration을 통한 웹 소설 분석 및 보조 서비스",
                        content = """## 🔍 프로젝트 소개

**장편 소설 작가를 위한 캐릭터·이벤트·관계 자동 추출 및 개연성 검증 AI 엔진**

크래프톤 정글 최종 프로젝트로 기획부터 개발까지 전 과정 참여했습니다. 방대한 비정형 소설 데이터를 정형화하고 세계관 일관성을 자동 검증하는 AI 백엔드를 개발했습니다.

> [!NOTE]
> 본 포트폴리오의 GitHub 링크는 전체 프로젝트에 대한 링크입니다.
> AI/Backend에 대한 더 자세한 사항은 [Stolink FastAPI Agent](https://github.com/stolink/stolink_fastapi_agent) 리포지토리를 참고해 주세요.

---

## 🏗️ 시스템 아키텍처

![시스템 아키텍처](/images/stolink%20아키텍처.png)

---

## 👨‍💻 역할 및 기간

| 구분 | 내용 |
|------|------|
| 팀 구성 | 5인 팀 프로젝트 |
| 개발 기간 | 2025. 12 ~ 2026. 01 |
| 담당 역할 | AI/Backend 개발 |

---

## 🏆 주요 성과

| 영역 | 성과 | 수치 |
|------|------|------|
| 분석 속도 | 하이브리드 병렬 실행 | **30% 단축** (211.1s → 147.4s) |
| 동시 처리량 | prefetch_count 최적화 | **12배 향상** (10 → 120) |
| API 비용 | 증분 분석 + 캐싱 + 티어링 | **70~90% 절감** (예상) |
| 데이터 품질 | 3단계 Entity Resolution | 중복 캐릭터 자동 병합 |
| 장문 분석 | Semantic Chunking + Context Rolling | 100% 분석 가능 |
| 개연성 검증 | RAG (Vector + Keyword) | 설정 충돌 **90% 감지** |
| 순서 보장 | Redis Sorted Set 분산 락 | 챕터 순서 보장 |

---

## 🤖 AI 개발

### 1️⃣ 추출·결합·분석·검증 4단계 Agent Orchestration 설계 및 개발

**문제 상황**
- 단일 LLM으로 소설 분석 시 **정보 누락**, **구조화 실패**, **품질 저하** 문제 발생
- 캐릭터, 이벤트, 관계 등 다양한 정보를 한 번에 추출하면 정확도 저하

**해결 방안**: LangGraph 기반 **멀티 에이전트 파이프라인** 구축

- **Phase 1 (EXTRACTION)**: Character Team, Setting Agent, Event Agent가 병렬로 캐릭터/배경/사건 추출
- **Phase 2 (RESOLUTION)**: Global Resolution에서 Fuzzy Matching으로 중복 캐릭터 병합
- **Phase 3 (ANALYSIS)**: Relationship Agent와 Consistency Agent가 병렬로 관계 추론 및 개연성 검사
- **Phase 4 (VALIDATION)**: Validator Agent가 품질 검증 후 필요 시 재추출 결정 (Feedback Loop)

**주요 기술적 의사결정**

| 선택 | 대안 | 선택 이유 |
|------|------|----------|
| **LangGraph Supervisor Pattern** | AutoGen, CrewAI | 상태 기반 라우팅, 재추출 루프 지원, Pydantic 구조화 출력 |
| **서브그래프 패턴** (Character Team) | 단일 에이전트 | 전문화된 추출로 정확도 향상 |
| **Feedback Loop** | 단방향 파이프라인 | 품질 미달 시 재추출로 품질 보장 |

---

### 2️⃣ 추출 데이터 품질 유지하며 분석 속도 211.1s → 147.4s로 약 30% 단축

**문제 상황**
- 순차 실행 시 각 에이전트의 LLM 호출 대기 시간이 누적되어 전체 분석 시간 증가

**해결 방안**: 의존성 분석 기반 **하이브리드 병렬 실행** 전략

| Phase | 에이전트 | 실행 방식 | 이유 |
|-------|---------|----------|------|
| Extraction | Character Team, Setting Agent | **병렬** | 서로 독립적 |
| Extraction | Event Agent | **순차** (Phase 1 후) | 캐릭터 이름 필요 |
| Analysis | Relationship, Consistency | **병렬** | 서로 독립적 |

**구현 방식**: asyncio.gather()로 독립적인 에이전트들을 동시 실행하고, 의존성이 있는 Event Agent는 Phase 1 완료 후 순차 실행

**성과**

| 지표 | Before | After | 개선율 |
|------|--------|-------|--------|
| 분석 시간 | 211.1초 | 147.4초 | **30.2% 단축** |

---

### 3️⃣ 장문 텍스트 분석 시 발생하는 문맥 손실 및 오류 해결

**문제 상황**
- LLM 토큰 제한으로 장편 소설 전체를 한 번에 분석 불가
- 단순 글자 수 기반 분할 시 **문맥 단절**, **캐릭터 중복 추출** 발생

**해결 방안**: **Semantic Chunking** + **Context Rolling**

**Semantic Chunking (의미 기반 분할)**
1. 빈 줄 기준으로 단락 분리
2. Gemini Embedding(3072차원)으로 각 단락 임베딩 생성
3. 인접 단락 간 코사인 유사도 계산
4. 유사도 ≥ 0.6이거나 현재 청크가 800자 미만이면 병합
5. 4000자 초과 시 새 섹션 시작

**Context Rolling (맥락 유지)**
- 이전 배치에서 추출된 캐릭터/이벤트 정보를 다음 배치의 컨텍스트로 전달
- 컨텍스트 크기 제한: 최근 20개 이벤트만 유지하여 오래된 데이터 제거

**효과**
- 배치 간 캐릭터 연속성 유지
- 장편 소설 (해리포터 전권 수준) **100% 분석 가능**

---

### 4️⃣ RAG 기반 계층적 컨텍스트 관리로 개연성 검증

**문제 상황**
- 이전 챕터 정보를 참조해야 설정 충돌/개연성 검증 가능
- 전체 소설을 매번 LLM에 전달하면 비용/시간 과다
- 관련 정보만 선별적으로 조회 필요

**해결 방안**: **Vector Search + Keyword Matching** 하이브리드 RAG

**계층적 컨텍스트 구조**
- **Level 1 (NOVEL)**: 전체 소설 요약
- **Level 2 (VOLUME)**: 권/파트 요약
- **Level 3 (CHAPTER)**: 챕터별 요약 + 최근 5개 챕터
- **Level 4 (INTRA)**: 같은 챕터 내 이전 문서 요약

**RAG 파이프라인**
1. 현재 텍스트 앞부분(2000자)으로 임베딩 생성
2. **Vector Search**: 유사 캐릭터(top 5), 유사 이벤트(top 3) 검색
3. **Keyword Search**: 텍스트에서 언급된 기존 캐릭터 매칭
4. 결과 병합 및 중복 제거
5. 계층적 컨텍스트 구축 후 LLM에 주입

---

### 5️⃣ 동일 인물 중복 추출 방지 로직 구현

**문제 상황**
- LLM이 같은 캐릭터를 다른 표현으로 추출 (예: "리안", "마법사 리안", "Lian")
- 별칭, 대명사, 칭호 등으로 인한 **중복 캐릭터** 생성

**해결 방안**: **3단계 Entity Resolution** + **Union-Find 클러스터링**

**매칭 알고리즘**
1. **정확 일치**: 정규화된 이름 비교
2. **별칭 교차 매칭**: aliases 필드 확인
3. **Fuzzy Matching**: rapidfuzz의 ratio(50%), partial_ratio(30%), token_sort_ratio(20%) 가중 평균

**분류 기준**

| 점수 | 분류 | 동작 |
|------|------|------|
| ≥ 95 | AUTO_MERGE | 자동 병합 |
| 80-94 | NEEDS_REVIEW | 검토 필요 플래그 |
| < 80 | DIFFERENT | 별개 캐릭터 |

**Union-Find 클러스터링**
- 경로 압축 최적화된 Union-Find로 동일 인물 클러스터링
- 클러스터 내에서 Completeness Score가 가장 높은 캐릭터를 Primary로 선정

---

### 6️⃣ 불필요한 반복 분석 제거를 통한 API 호출 비용 절감

**문제 상황**
- 매 분석마다 전체 문서 재분석으로 API 비용 폭발
- 동일 텍스트 임베딩 중복 생성

**해결 방안**: 3가지 최적화 전략 적용

**1) 증분 분석 (Incremental Analysis)**
- 각 섹션의 SHA256 해시값을 이전 분석과 비교
- 변경된 섹션부터만 재분석 (변경 없으면 스킵)

**2) Redis 임베딩 캐싱**
- 텍스트 해시를 키로 임베딩 벡터 캐싱 (24시간 TTL)
- 동일 텍스트 재요청 시 캐시에서 즉시 반환

**3) LLM 티어링 (작업 복잡도별 모델 차등 사용)**

| 작업 유형 | 티어 | 모델 | 비용 |
|----------|------|------|------|
| 라우팅/분류 | Basic | gemini-2.0-flash-lite | 최저 |
| 정보 추출 | Standard | gemini-2.5-flash-lite | 저 |
| 관계 추론 | Advanced | gemini-2.5-flash | 중 |
| **개연성 검사** | **Premium** | gemini-3-flash-preview | 고 |

---

## 🔧 Backend 개발

### 1️⃣ 메시지 큐 prefetch count 튜닝으로 동시 처리량 12배 향상 (10 → 120)

**문제 상황**
- 기본 prefetch_count=10 설정으로 메모리 35%만 활용
- LLM API 호출은 I/O-bound 작업으로 대기 시간 동안 리소스 미활용

**해결 방안**: 부하 테스트 기반 **prefetch_count 최적화**

RabbitMQ Consumer의 set_qos()에서 prefetch_count를 120으로 설정

**튜닝 과정**

| Prefetch | 메모리 사용 | 상태 |
|----------|------------|------|
| 10 | 625MB (35%) | ⚡ 기본값 (비효율) |
| 50 | 930MB (52%) | ✅ 안전 |
| 100 | 1.28GB (71%) | ✅ 최적 |
| 150 | 1.63GB (91%) | ⚠️ 한계 |
| **120** | **~1.4GB (78%)** | 🎯 **Sweet Spot** |

**왜 120인가?**
- 메모리 사용률 78%로 안전 마진 확보
- 150 이상 시 메모리 부족으로 OOM 위험
- 120이 성능과 안정성의 **최적 균형점**

**성과**: Throughput **12배 향상**

---

### 2️⃣ Redis Sorted Set·분산 락·ACK 기반 재전송으로 다중 문서의 처리 순서 보장

**문제 상황**
- 같은 프로젝트의 챕터들이 순서 무관하게 병렬 처리되면 **문맥 일관성 파괴**
- 일반 Lock 사용 시 **순서 보장 불가** (먼저 요청한 쪽이 획득)

**해결 방안**: **Redis Sorted Set 기반 순서 보장 분산 락**

**동작 방식**
1. **ZADD**: Sorted Set에 job 등록 (score = document_order)
2. **ZRANK**: 내 순위 조회 (rank = 0이면 가장 낮은 순서 = 내 차례)
3. **SET NX EX**: 락 획득 시도 (없을 때만, 10분 TTL로 데드락 방지)
4. **DELETE + ZREM**: 처리 완료 후 락 해제 및 큐에서 제거

**핵심 Redis 명령어**

| 명령어 | 용도 | 원자성 |
|--------|------|--------|
| `ZADD` | job 등록 (score로 순서 지정) | ✅ |
| `ZRANK` | 내 순위 조회 | ✅ |
| `SET NX EX` | 락 획득 (없을 때만, TTL 설정) | ✅ |
| `DELETE` | 락 해제 | ✅ |
| `ZREM` | 큐에서 제거 | ✅ |

**ACK 기반 재전송 메커니즘**
1. Spring이 문서 전송 시 PendingAckTracker에 등록
2. AI Backend가 분석 완료 후 Callback 전송
3. Spring이 Callback 수신 시 markCompleted() 호출
4. RetransmitScheduler가 5분 타임아웃 시 재전송 (최대 3회)
""",
                        repoUrl = "https://github.com/stolink",
                        demoUrl = "https://stolink.link",
                        startDate = LocalDate.of(2025, 12, 12),
                        endDate = LocalDate.of(2026, 1, 24),
                        isActive = true,
                        thumbnailUrl = "/images/stolink.png",
                        techStack = mutableListOf("Java", "SpringBoot", "JPA", "SpringDataJpa", "QueryDSL", "Python", "FastAPI", "PostgreSQL", "Neo4j", "Redis", "LangGraph", "RabbitMQ", "AWS EC2", "AWS RDS", "AWS Bedrock", "AWS CloudWatch", "htop", "Docker", "GEMINI API"),
                        featured = true
                    ),
                    Project(
                        title = "농구 코트 예약 시스템: Court-Kok",
                        description = "동시성 문제를 해결한 실시간 농구 코트 예약 서비스",
                        content = """## 🔍 프로젝트 소개

크래프톤 정글 첫 프로젝트로 **3일간 서비스 개발 및 배포**를 완료한 프로젝트입니다.

---

## 🏗️ 시스템 아키텍처

![시스템 아키텍처](/images/court-kok-architecture.png)

---

## 👨‍💻 역할 및 기간

| 구분 | 내용 |
|------|------|
| 팀 구성 | 5인 팀 프로젝트 |
| 개발 기간 | 2025. 09 (3일) |
| 담당 역할 | Backend 개발 |

---

## 🏆 주요 성과

| 항목 | 개선 결과 |
|------|-----------|
| 예약 API 처리량 | **60 TPS → 220 TPS** (약 3.5배 개선) |
| 실시간성 | WebSocket 기반 즉시 반영 |
| 배포 자동화 | GitHub Actions CI/CD 구축 |
| 동시성 제어 | 원자적 업데이트로 Race Condition 해결 |

---

## 🔧 기술적 도전 및 구현

### 1️⃣ 성능 튜닝으로 API 처리량 3.5배 개선

**문제 상황**
- 단일 워커 기반 Flask 서버에서 예약 API 처리량이 **60 TPS**에 불과

**해결 방안**
- Gunicorn **워커 수** 및 **worker-class(gevent)** 튜닝을 통한 최적화

**결과**
- 예약 API 처리량 60 TPS → **220 TPS**로 약 **3.5배 개선**

**검증**
- k6 부하 테스트 스크립트를 작성하여 **6,000 VU** 기반 성능 측정
- Prometheus/Grafana를 통한 모니터링

![k6 부하 테스트 결과](/images/court-kok-stress.png)

---

### 2️⃣ WebSocket 기반 실시간 예약 현황 반영

**문제 상황**
- 기존 HTTP 폴링 방식은 서버 부하 증가 및 실시간성 부족

**해결 방안**
- **Flask-Sock**을 활용한 WebSocket 통신 구현

**결과**
- 예약 생성/취소/참가 시 연결된 모든 클라이언트에 **실시간 브로드캐스팅**

**구현**
- `broadcast_event_update()` 함수를 통해 특정 날짜의 이벤트 변경 시 즉시 UI 반영

---

### 3️⃣ GitHub Actions 기반 CI/CD 파이프라인 구축

**구성**
- GitHub Actions → Docker 이미지 빌드 → Amazon ECR 푸시 → EC2 자동 배포

**결과**
- **main 브랜치 push 시 자동으로 빌드 및 배포 완료**

**특징**
- docker-compose를 활용한 멀티 컨테이너 오케스트레이션 (Flask, MongoDB)
- Prometheus/Grafana 모니터링 스택은 별도 EC2 인스턴스에서 운영

**배운 점**
- 안정화 버전(main)과 개발 버전(dev) 브랜치를 분리하여 관리해야 함을 깨달음
- 개발 중 빈번한 배포로 서비스 중단 리스크 경험 → 브랜치 전략의 중요성 인식

---

### 4️⃣ 테스트 코드 기반 동시성 문제 해결

**문제 상황**
- 다수의 사용자가 동시에 예약 신청 시 **Race Condition** 발생 가능성

**해결 방안**
- MongoDB의 `find_one_and_update()`와 `${"$"}expr` 연산자를 활용한 **원자적(Atomic) 업데이트**

**검증**
- Python unittest 기반 동시성 테스트 코드 작성 (**500명 동시 참가 신청 시뮬레이션**)

**결과**
- 최대 정원 초과 없이 **정확히 정원만큼만 참가 성공** 확인
""",
                        startDate = LocalDate.of(2025, 9, 1),
                        endDate = LocalDate.of(2025, 9, 3),
                        isActive = true,
                        thumbnailUrl = "/images/court-kok.png",
                        techStack = mutableListOf("Flask", "MongoDB", "WebSocket", "Docker", "GitHub Actions", "Prometheus", "AWS EC2", "AWS CloudWatch", "Grafana"),
                        featured = true
                    ),
                    Project(
                        title = "SVD를 이용한 이미지 압축 프로젝트",
                        description = "SVD(특이값 분해)와 멀티스레딩을 활용한 고속 이미지 압축기",
                        content = """## 🔍 프로젝트 소개

**전공과목 프로젝트로, 특이값 분해(SVD) 이론을 배우고 이를 실제 프로젝트에 적용해보기 위해 시작되었습니다.**

사진 스튜디오 직원이 1000장 이상의 고용량 사진을 의뢰인에게 전달을 해야 하며, 파일 용량 문제로 인해 압축이 필요한 상황이었습니다. 이 문제를 해결하기 위해, NumPy SVD(Singular Value Decomposition)를 활용한 이미지 압축 프로그램을 개발하는 프로젝트를 진행하였습니다.

---

## 👨‍💻 역할

| 구분 | 내용 |
|------|------|
| 팀 구성 | 6인 팀 프로젝트 |
| 담당 역할 | 핵심 기능 구현 및 코드 작성 |
| 핵심 업무 | 멀티스레드 처리 구조 설계 및 이메일 자동 발송 구현 |

- **프로그램 속도 개선**을 위해 멀티스레드 처리 구조 설계 및 적용
- **압축된 이미지를 이메일로 자동 발송**할 수 있도록 기능 구현

---

## 🏆 성과 요약

| 항목 | 개선 결과 |
|------|-----------|
| 처리 속도 | 57초 → **13초** (100장 기준, **77% 단축**) |
| 동시성 제어 | 락(Lock) 적용으로 Race Condition 해결 |
| 압축 효율 | 35KB → **30KB** (SVD 적용) |

---

## 1️⃣ Multi Thread 도입과 성능 개선

![Multi Thread Performance](/images/svd_1.png)

### ❌ 기존 문제점
- 이미지 압축을 순차적으로 처리하여 전체 프로세스에 많은 시간이 소요됨
- 단순 멀티스레드 적용 시 **공유 자원 접근 충돌 문제** 발생

### ✅ 해결 방법
1. **Lock 적용**: 공유 자원 충돌 문제를 해결하여 안정성 확보
2. **Thread Pool 도입**: 스레드 생성/소멸 비용을 줄이고 자원 관리 효율 최적화

### 📊 개선 결과
- **Gray Scale 변환**: 21.5초 → **4.4초**
- **전체 압축 시간**: 57.3초 → **13.1초** (약 **77% 성능 개선**)

---

## 2️⃣ 결론

![SVD Compression Result](/images/svd_2.png)

- 원본 이미지는 약 **35KB**였으며, SVD 기반 압축을 통해 약 **30KB**로 줄일 수 있었습니다.
- 실제 이미지 용량이 더 클수록, **SVD의 압축 효과는 더욱 크게 나타날 수 있기 때문에** 고해상도 이미지에 활용 시 높은 효율을 기대할 수 있습니다.
- numpy의 SVD 원리를 이해하고 이를 기반으로 사진 압축 프로그램을 **정확하고 안정적으로 구현**하였습니다.
""",
                        startDate = LocalDate.of(2024, 6, 4),
                        endDate = LocalDate.of(2024, 6, 20),
                        isActive = true,
                        thumbnailUrl = "/images/svd.png",
                        techStack = mutableListOf("Python", "NumPy"),
                        featured = true
                    ),
                    Project(
                        title = "프린터 생산 공정 자동 비전 검사 시스템: Canon",
                        description = "CNN과 템플릿 매칭을 활용한 불량 검출 및 이상 탐지 시스템",
                        content = """## 🔍 프로젝트 소개

정확한 기종 식별과 부품 부착 여부는 대오더라벨 품질 관리의 핵심입니다. 라벨의 구분번호를 기준으로 기종을 분류하고, 템플릿 매칭 기술을 활용해 정규 부품의 부착 여부를 자동으로 검사하는 시스템을 개발했습니다.

---

## 👨‍💻 역할

| 구분 | 내용 |
|------|------|
| 팀 구성 | 4인 팀 프로젝트 |
| 담당 역할 | 백엔드 개발 |
| 핵심 업무 | 검사 프로세스 설계 및 시스템 구축 |

- **웹 통신 기반 시뮬레이션 기능** 구현
- **대오더라벨 이미지 자동 추출 로직** 개발
- **검사 결과 시각화를 위한 GUI** 제작

---

## 🏆 성과 요약

| 항목 | 개선 결과 |
|------|-----------|
| Template Matching | 검출률 **75% → 99%** (1,000개 기준) |
| SIFT + FLANN | 검출률 **70% → 96%** (300개 기준) |
| CNN 분류 모델 | 테스트 정확도 **99.4%** |

---

## 1️⃣ 대오더라벨 바코드 디코딩

![바코드 디코딩 프로세스](/images/Canon_1.png)

Canny 알고리즘과 허프 변환을 통해 라벨의 기울어진 각도를 계산한 후, 이를 기준으로 이미지를 정렬하였습니다. 이후 회전된 이미지에서 바코드 영역을 인식하고 decode 함수를 활용해 텍스트 데이터를 추출하여 라벨 정보를 획득했습니다.

---

## 2️⃣ Template Matching 개선

### ❌ 문제점

![수정 전 Template Matching](/images/canon_2.png)

- 약 **25%의 이미지**에서 체크포인트를 올바르게 인식하지 못함
- 임계값 상향 조정 시 **정상 제품조차 인식되지 않는** 경우 빈번
- 밝기 조절 보정 기법 시도했으나 **처리 시간만 증가**

### ✅ 해결 방법

![수정 후 전처리 과정](/images/Canon_3.png)

**Step 1. 이미지 전처리 및 정렬**
1. 그레이스케일 변환 → 반전 → 팽창 과정을 통해 전처리
2. Otsu 이진화를 적용해 객체 윤곽 추출
3. 가장 큰 사각형 컨투어 기준으로 라벨 위치 탐색
4. 라벨 영역을 수평으로 정렬

![Template Matching 결과](/images/Canon_4.png)

**Step 2. 템플릿 매칭 수행**
- 정렬된 이미지와 템플릿 이미지 비교로 유사도 기반 매칭 수행
- 결과: **검출률 99.6%** 및 **98.4%** 달성

---

## 3️⃣ SIFT와 FLANN을 이용한 특징점 매칭

### ❌ 문제점

![수정 전 SIFT 매칭](/images/canon_5.png)

- 실제 매칭되어야 하는 이미지에서도 **매칭 실패**
- 잘못된 위치 간 매칭 발생

### ✅ 해결 방법

**1. 전처리**

![전처리 과정](/images/canon_7.png)

- grayscale 변환 및 `cv2.adaptiveThreshold` 이진화 처리

**2. 템플릿 매칭 수행**

![템플릿 매칭](/images/canon_8.png)

- 이진화된 이미지에서 고정된 템플릿과 유사한 영역 탐색

**3. SIFT 정밀 검출**

![SIFT 정밀 검출](/images/canon_9.png)

- 좁혀진 영역 내에서 SIFT + FLANN 매처 + Lowe's Ratio Test 적용

### 📊 개선 결과

![개선 결과](/images/canon_10.png)

- Step 9 검출률 96%까지 향상
- 오탐 및 미탐 문제 크게 감소

---

## 4️⃣ OneClassSVM 기반 이상 탐지

![OneClassSVM 파이프라인](/images/canon_11.png)

### 🔍 배경
- 제품 내부 구조가 복잡하고 **불량 사례가 희귀**
- 불량 데이터 수집 어려움 (**Cold Start Problem**)

### 💡 해결 방법
- **VGG16 Feature Extractor**를 활용하여 고차원 특징 벡터 추출
- One-Class SVM 모델 학습 (`nu = 0.01`로 약 1%만 이상치 허용)

### ✅ 결과
- 적은 데이터로도 **강건한(Robust) 이상 탐지 파이프라인** 구축

---

## 5️⃣ CNN 기반 분류 모델

### 🔍 도입 배경
| 기존 방식 | 한계점 |
|-----------|--------|
| SIFT | 조명, 반사에 민감 |
| Template Matching | 미세한 위치 변화, 회전에 정확도 저하 |
| One-Class SVM | 다양한 불량 유형 탐지에 분포 표현력 부족 |

### 📐 모델 아키텍처

![데이터 구성](/images/canon_12.png)

- Step 12 영역 이미지를 pass/fail로 라벨링
- 데이터 증강을 통해 클래스 균형 조정

![모델 구조](/images/canon_13.png)

- 4개의 Conv2D 계층 + MaxPooling + Fully Connected Layer
- Adam Optimizer, Binary Crossentropy Loss

### 📊 모델 성능

- 테스트 정확도 **99.4%** 달성
- 전처리 없이도 다양한 시각적 변형에 유연하게 대응

---

## 6️⃣ 확장성 있는 검사 프로세스 설계

### 🏗️ 시스템 아키텍처

![시스템 구조](/images/canon_14.png)

- 클라이언트에서 업로드한 이미지와 대오더 라벨 정보를 서버에서 수신
- 기종에 따라 적절한 **핸들러(handler)** 자동 선택
- 체크포인트 프로세서가 단계별 이미지 분석 결과를 GUI에 시각화

### 📦 핸들러 클래스 구조

![핸들러 구조](/images/canon_15.png)

- `Handler` 추상 클래스 정의 (`is_check()`, `processor_type()` 추상 메서드)
- 각 기종별 핸들러 클래스 상속 구현
- 검사 순서는 **Queue 형태**로 관리

### ⚙️ 체크포인트 프로세서 구조

![체크포인트 프로세서 구조](/images/canon_16.png)

- `CheckPointProcessor` 추상 클래스에 `process()`, `log_event()` 정의
- 기종/단계별 세분화된 프로세서 클래스 상속 구현

### ✅ 결과
- **핸들러 클래스만 추가**하면 신규 기종 즉시 반영 가능
- 유지보수성과 확장성 확보
""",
                        startDate = LocalDate.of(2024, 3, 11),
                        endDate = LocalDate.of(2024, 5, 30),
                        isActive = true,
                        thumbnailUrl = "/images/canon.png",
                        techStack = mutableListOf("Python", "PyTorch", "OpenCV", "CNN", "Flask"),
                        featured = true
                    ),
                    Project(
                        title = "오픈 마켓 쇼핑몰: MarketBridge",
                        description = "대규모 트래픽 대응 및 성능 최적화를 경험한 이커머스 프로젝트",
                        content = """## 🔍 프로젝트 소개

Spring Boot, JPA 스터디 후 사이드 프로젝트로 발전시킨 이커머스 플랫폼입니다.

---

## 🏗️ 시스템 아키텍처

![시스템 아키텍처](/images/MarketBridge-archiecture.png)

---

## 👨‍💻 역할 및 기간

| 구분 | 내용 |
|------|------|
| 팀 구성 | 4인 팀 프로젝트 |
| 개발 기간 | 2023. 12 ~ 2024. 02 (3개월) |
| 담당 역할 | Backend 개발 |

---

## 🏆 주요 성과

| 항목 | 개선 결과 |
|------|-----------|
| 통계 쿼리 성능 | 2분 35초 → **200ms** (약 775배 개선) |
| 결제 API 처리량 | 80 TPS → **280 TPS** (약 3.5배 개선) |
| 동시성 제어 | Optimistic Lock Deadlock 해결 |
| 테스트 커버리지 | **90%** 유지 |

---

## 🔧 기술적 도전 및 구현

### 1️⃣ Optimistic Lock 적용 시 Deadlock 발생 및 해결

#### 문제 상황

동시성 제어를 위해 Pessimistic Lock, Named Lock, Optimistic Lock을 비교 테스트하던 중, **Optimistic Lock에서 Deadlock 발생**

100개 스레드로 재고 50개 상품에 동시 주문 요청 시 Deadlock으로 트랜잭션 롤백

#### 원인 분석

MySQL 공식 문서에서 단서를 발견:

> FK 제약조건이 있는 테이블에서 INSERT, UPDATE, DELETE 시 **제약 조건 확인을 위해 S Lock**이 레코드 레벨에 걸린다

**Deadlock 발생 흐름**:

1. 트랜잭션 1: `orderDetailRepository.saveAll()` → Product에 **S Lock 획득**
2. 트랜잭션 2: `orderDetailRepository.saveAll()` → 같은 Product에 **S Lock 획득**
3. 트랜잭션 1: `product.decrease()` → **X Lock 필요** (트랜잭션 2의 S Lock 해제 대기)
4. 트랜잭션 2: `product.decrease()` → **X Lock 필요** (트랜잭션 1의 S Lock 해제 대기)
5. **서로 S Lock 해제를 기다리며 Deadlock 발생**

#### 첫 번째 해결 시도 (실패)

**가설**: X Lock을 먼저 획득하도록 `decrease()`를 `saveAll()` 이전에 호출하면 해결될 것

**결과**: 여전히 Deadlock 발생

**원인 파악**: JPA 쓰기 지연(Write-Behind) 때문

JPA는 트랜잭션 종료 시 SQL을 **아래 순서**로 플러시:
1. **Inserts** (먼저 실행)
2. Updates
3. Deletes

→ 코드에서 `decrease()`를 먼저 호출해도, **실제 SQL은 INSERT가 먼저 실행**되어 S Lock이 먼저 걸림

#### 두 번째 해결 시도 (성공)

**해결책**: `saveAllAndFlush()`를 사용하여 UPDATE를 먼저 플러시

```java
// 재고 감소 후 즉시 플러시 → X Lock 먼저 획득
products.forEach(product -> product.decrease(quantity));
productRepository.saveAllAndFlush(products);  // ★ 즉시 플러시

// 이후 OrderDetail 저장 (S Lock 획득)
orderDetailRepository.saveAll(orderDetails);
```

**결과**: Deadlock 없이 정상 동작

---

### 2️⃣ 주문 API 부하 테스트 및 성능 튜닝 (80 TPS → 280 TPS)

#### 테스트 목표

카카오페이 테크 블로그(23년 12월 기준) 400 TPS를 참고하여 성능 목표 설정

#### 1차 테스트: Connection Pool = 10 → **80 TPS**

**관측**: 특정 시점에서 Request Rate가 일정해지며 병목 발생

**분석**:
- Order Server의 커넥션 부족 현상 확인
- CPU, 메모리는 정상
- RDS에 14개 커넥션 요청 확인

**결론**: Order Server의 커넥션 풀 부족으로 인한 병목

**조치**: 커넥션 풀 20개로 증가

#### 2차 테스트: Connection Pool = 20 → **130 TPS**

**관측**: 이전보다 개선되었으나 여전히 병목 발생

**분석**:
- Order Server 커넥션 부족 재확인
- CPU, 메모리는 정상
- RDS에 24개 커넥션 요청

**결론**: 커넥션 풀이 여전히 부족

**조치**: 커넥션 풀 30개로 증가

#### 3차 테스트: Connection Pool = 30 → **170 TPS**

**관측**: 커넥션 고갈 현상 지속

**분석**: RDS에 29~34개 커넥션 요청

**조치**: 커넥션 풀 40개로 증가

#### 4차 테스트: Connection Pool = 40 → **200 TPS**

**관측**: 커넥션과 CPU 사용률 동시 병목

**가설**: 커넥션 풀을 더 늘리면 개선될 것

**조치**: 커넥션 풀 50개로 증가

#### 5차 테스트: Connection Pool = 50 → **200 TPS** (정체)

**관측**: TPS 변화 없음

**분석**: 
- CPU 사용률이 병목의 주 원인
- 커넥션 풀 증가만으로는 더 이상 개선 불가

**방향 전환**: 코드 레벨 개선 검토
1. 서버 증설 (Scale-Out) → 비용 문제
2. **코드 개선** → 선택

#### 코드 분석: 문제 발견

트랜잭션 내에서 **외부 API 호출** (`kakao.ready()`) 발견

→ 트랜잭션이 외부 API 응답을 기다리는 동안 커넥션을 점유하며 낭비

#### 6차 테스트: 트랜잭션 범위 축소 (Facade 패턴) → **200 TPS**

**조치**: Facade 패턴으로 외부 API 호출을 트랜잭션 밖으로 분리

**관측**: 여전히 CPU 병목

**조치**: 트랜잭션 범위 추가 최소화

#### 7차 테스트 (최종): 트랜잭션 최적화 → **280 TPS**

**결과**: 80 TPS → **280 TPS** (약 3.5배 개선)

---

### 3️⃣ 통계 쿼리 성능 개선 (2분 35초 → 200ms, 약 775배 개선)

#### 문제 상황

통계 쿼리 실행 시간 **약 2분 35초** 소요

#### 장시간 쿼리가 부하 상황에서 발생시키는 문제

쿼리가 오래 걸리는 상황에서 동시에 많은 요청이 들어오면:

1. **커넥션 풀 고갈**: 각 쿼리가 2분 35초 동안 커넥션을 점유 → 다른 요청들이 커넥션 대기
2. **DB Lock 경합**: 장시간 테이블/레코드 Lock 유지 → 쓰기 작업 지연
3. **메모리 압박**: 대용량 결과셋이 메모리에 오래 상주 → OOM 위험
4. **Timeout 발생**: 응답 시간 초과로 사용자 요청 실패
5. **장애 전파**: DB 부하 → API 서버 부하 → 전체 서비스 장애로 확산

#### 1차 시도: 인덱스 적용 → **약 15초로 개선**

**조치**: `member_id`, `total_amount` 컬럼에 인덱스 적용

**결과**: 2분 35초 → **15초** (약 10배 개선)

#### 의문: 15초가 과연 사용 가능한 시간인가?

**문제점**:
- 사용자 입장에서 15초 대기는 여전히 긴 시간
- 동시 요청 시 커넥션 풀 고갈 문제 여전히 존재
- API 응답 시간으로는 부적합

**결론**: 근본적인 구조 변경 필요

#### 2차 시도: 테이블 구조 재설계 → **300ms로 단축**

**해결 방안**: 통계 결과를 미리 계산하여 별도 테이블에 저장

**구현**:
- 통계 결과 저장용 **새로운 테이블 설계**
- **스케줄링**(배치)을 통해 주기적으로 결과 미리 계산 및 저장
- 조회 시에는 미리 계산된 결과만 조회

**결과**: 15초 → **300ms** (약 50배 개선)

#### 3차 시도: 실행계획 분석 및 추가 최적화 → **200ms로 개선**

**분석**: 실행계획(EXPLAIN) 확인 → **Full Table Scan** 발생

**조치**: `total_amount` 컬럼에 인덱스 적용

**결과**: 
- 실행계획: Full Table Scan → **Range Scan**으로 변경
- 300ms → **200ms** (약 1.5배 추가 개선)

#### 최종 결과

| 단계 | 시간 | 개선 방법 |
|------|------|-----------|
| 초기 | 2분 35초 | - |
| 1차 | 15초 | 인덱스 적용 |
| 2차 | 300ms | 테이블 재설계 + 스케줄링 |
| **최종** | **200ms** | 실행계획 분석 + Range Scan |

**총 성능 개선**: 2분 35초 → 200ms = **약 775배 개선**
""",
                        startDate = LocalDate.of(2023, 12, 27),
                        endDate = LocalDate.of(2024, 2, 28),
                        isActive = true,
                        thumbnailUrl = "/images/마켓브릿지.png",
                        techStack = mutableListOf("Java", "Spring Boot", "Spring Security", "Spring Data JPA", "QueryDSL", "JUnit5", "MySQL", "Redis", "H2", "AWS S3", "AWS EC2", "Docker", "Spring REST Docs", "Jenkins", "k6", "Prometheus", "Grafana", "AWS CloudWatch"),
                        featured = true
                    )
                )
            )

        // Skills
        skillRepository.deleteAll()
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



        // Awards
        awardRepository.deleteAll()
        awardRepository.saveAll(
            listOf(
                Award(title = "학업 최우수상", organization = "한양대학교 ERICA", date = "2025. 04.", description = "전체 평점 우수"),
                Award(title = "프로젝트 장려상", organization = "메타버스 아카데미 부트캠프", date = "2022. 12.", description = "23개 팀 중 3위")
            )
        )

        // Education
        educationRepository.deleteAll()
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

        // Certifications
        certificationRepository.deleteAll()
        certificationRepository.saveAll(
            listOf(
                Certification(name = "정보처리기사", date = "2025. 09.", organization = "한국산업인력공단"),
                Certification(name = "SQLD", date = "2025. 04.", organization = "한국데이터산업진흥원")
            )
        )

        // Language Skills
        languageSkillRepository.deleteAll()
        languageSkillRepository.saveAll(
            listOf(
                LanguageSkill(name = "TOEIC Speaking", level = "IM2", date = "2025. 03.")
            )
        )

        // Activities
        activityRepository.deleteAll()
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

