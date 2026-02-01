# STO-LINK - AI 기반 소설 세계관 관리 플랫폼

> **장편 소설 작가를 위한 캐릭터·이벤트·관계 자동 추출 및 개연성 검증 AI 엔진**

## 📋 프로젝트 개요

| 항목 | 내용 |
|------|------|
| **프로젝트명** | STO-LINK (AI Backend) |
| **개발 기간** | 2026.01 |
| **팀 규모** | 5명 (AI Backend 담당) |
| **역할** | AI/Backend 개발 |
| **핵심 가치** | 방대한 비정형 소설 데이터의 정형화 및 세계관 일관성 자동 검증 |

---

## 🎯 담당 역할 및 성과

### AI 개발

#### 1. 추출·결합·분석·검증 4단계 Agent Orchestration 설계 및 개발

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

#### 2. 추출 데이터 품질 유지하며 분석 속도 211.1s → 147.4s로 약 30% 단축

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

#### 3. 장문 텍스트 분석 시 발생하는 문맥 손실 및 오류 해결

**문제 상황**
- LLM 토큰 제한으로 장편 소설 전체를 한 번에 분석 불가
- 단순 글자 수 기반 분할 시 **문맥 단절**, **캐릭터 중복 추출** 발생

**해결 방안**: **Semantic Chunking** + **Context Rolling**

##### Semantic Chunking (의미 기반 분할)
1. 빈 줄 기준으로 단락 분리
2. Gemini Embedding(3072차원)으로 각 단락 임베딩 생성
3. 인접 단락 간 코사인 유사도 계산
4. 유사도 ≥ 0.6이거나 현재 청크가 800자 미만이면 병합
5. 4000자 초과 시 새 섹션 시작

##### Context Rolling (맥락 유지)
- 이전 배치에서 추출된 캐릭터/이벤트 정보를 다음 배치의 컨텍스트로 전달
- 컨텍스트 크기 제한: 최근 20개 이벤트만 유지하여 오래된 데이터 제거

**효과**
- 배치 간 캐릭터 연속성 유지
- 장편 소설 (해리포터 전권 수준) **100% 분석 가능**

---

#### 4. RAG 기반 계층적 컨텍스트 관리로 개연성 검증

**문제 상황**
- 이전 챕터 정보를 참조해야 설정 충돌/개연성 검증 가능
- 전체 소설을 매번 LLM에 전달하면 비용/시간 과다
- 관련 정보만 선별적으로 조회 필요

**해결 방안**: **Vector Search + Keyword Matching** 하이브리드 RAG

##### 계층적 컨텍스트 구조
- **Level 1 (NOVEL)**: 전체 소설 요약
- **Level 2 (VOLUME)**: 권/파트 요약
- **Level 3 (CHAPTER)**: 챕터별 요약 + 최근 5개 챕터
- **Level 4 (INTRA)**: 같은 챕터 내 이전 문서 요약

##### RAG 파이프라인
1. 현재 텍스트 앞부분(2000자)으로 임베딩 생성
2. **Vector Search**: 유사 캐릭터(top 5), 유사 이벤트(top 3) 검색
3. **Keyword Search**: 텍스트에서 언급된 기존 캐릭터 매칭
4. 결과 병합 및 중복 제거
5. 계층적 컨텍스트 구축 후 LLM에 주입

---

#### 5. 동일 인물 중복 추출 방지 로직 구현

**문제 상황**
- LLM이 같은 캐릭터를 다른 표현으로 추출 (예: "리안", "마법사 리안", "Lian")
- 별칭, 대명사, 칭호 등으로 인한 **중복 캐릭터** 생성

**해결 방안**: **3단계 Entity Resolution** + **Union-Find 클러스터링**

##### 매칭 알고리즘
1. **정확 일치**: 정규화된 이름 비교
2. **별칭 교차 매칭**: aliases 필드 확인
3. **Fuzzy Matching**: rapidfuzz의 ratio(50%), partial_ratio(30%), token_sort_ratio(20%) 가중 평균

##### 분류 기준

| 점수 | 분류 | 동작 |
|------|------|------|
| ≥ 95 | AUTO_MERGE | 자동 병합 |
| 80-94 | NEEDS_REVIEW | 검토 필요 플래그 |
| < 80 | DIFFERENT | 별개 캐릭터 |

##### Union-Find 클러스터링
- 경로 압축 최적화된 Union-Find로 동일 인물 클러스터링
- 클러스터 내에서 Completeness Score가 가장 높은 캐릭터를 Primary로 선정

---

#### 6. 불필요한 반복 분석 제거를 통한 API 호출 비용 절감

**문제 상황**
- 매 분석마다 전체 문서 재분석으로 API 비용 폭발
- 동일 텍스트 임베딩 중복 생성

**해결 방안**: 3가지 최적화 전략 적용

##### 1) 증분 분석 (Incremental Analysis)
- 각 섹션의 SHA256 해시값을 이전 분석과 비교
- 변경된 섹션부터만 재분석 (변경 없으면 스킵)

##### 2) Redis 임베딩 캐싱
- 텍스트 해시를 키로 임베딩 벡터 캐싱 (24시간 TTL)
- 동일 텍스트 재요청 시 캐시에서 즉시 반환

##### 3) LLM 티어링 (작업 복잡도별 모델 차등 사용)

| 작업 유형 | 티어 | 모델 | 비용 |
|----------|------|------|------|
| 라우팅/분류 | Basic | gemini-2.0-flash-lite | 최저 |
| 정보 추출 | Standard | gemini-2.5-flash-lite | 저 |
| 관계 추론 | Advanced | gemini-2.5-flash | 중 |
| **개연성 검사** | **Premium** | gemini-3-flash-preview | 고 |

---

### Backend 개발

#### 1. 메시지 큐 prefetch count 튜닝으로 동시 처리량 12배 향상 (10 → 120)

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

**성과**
Throughput **12배 향상**

---

#### 2. Redis Sorted Set·분산 락·ACK 기반 재전송으로 다중 문서의 처리 순서 보장

**문제 상황**
- 같은 프로젝트의 챕터들이 순서 무관하게 병렬 처리되면 **문맥 일관성 파괴**
- 일반 Lock 사용 시 **순서 보장 불가** (먼저 요청한 쪽이 획득)

**해결 방안**: **Redis Sorted Set 기반 순서 보장 분산 락**

##### 동작 방식
1. **ZADD**: Sorted Set에 job 등록 (score = document_order)
2. **ZRANK**: 내 순위 조회 (rank = 0이면 가장 낮은 순서 = 내 차례)
3. **SET NX EX**: 락 획득 시도 (없을 때만, 10분 TTL로 데드락 방지)
4. **DELETE + ZREM**: 처리 완료 후 락 해제 및 큐에서 제거

##### 핵심 Redis 명령어

| 명령어 | 용도 | 원자성 |
|--------|------|--------|
| `ZADD` | job 등록 (score로 순서 지정) | ✅ |
| `ZRANK` | 내 순위 조회 | ✅ |
| `SET NX EX` | 락 획득 (없을 때만, TTL 설정) | ✅ |
| `DELETE` | 락 해제 | ✅ |
| `ZREM` | 큐에서 제거 | ✅ |

##### ACK 기반 재전송 메커니즘
1. Spring이 문서 전송 시 PendingAckTracker에 등록
2. AI Backend가 분석 완료 후 Callback 전송
3. Spring이 Callback 수신 시 markCompleted() 호출
4. RetransmitScheduler가 5분 타임아웃 시 재전송 (최대 3회)

---

## 🛠 기술 스택

### Core
| Category | Technology | Version | 선정 이유 |
|----------|------------|---------|-----------|
| Runtime | Python | 3.11+ | LangChain/LangGraph 생태계, 빠른 프로토타이핑 |
| Web | FastAPI | 0.115 | 비동기 지원, Pydantic 통합, 자동 API 문서 |
| Agent | LangGraph | 0.2 | 상태 기반 워크플로우, 조건부 라우팅, 재시도 지원 |

### Database
| Category | Technology | Version | 선정 이유 |
|----------|------------|---------|-----------|
| Graph DB | Neo4j | 5.x | 캐릭터 관계 그래프, Cypher 쿼리, Vector Index |
| Vector DB | pgvector | - | PostgreSQL 확장, 섹션 임베딩 저장 |
| Cache | Redis | 7.x | 임베딩 캐시, 프로젝트 락 (Sorted Set) |

### Message Queue
| Category | Technology | Version | 선정 이유 |
|----------|------------|---------|-----------|
| Broker | RabbitMQ | 3.13 | Spring AMQP 호환, 우선순위 큐, ACK/NACK |
| Client | aio_pika | - | asyncio 네이티브, Connection Pooling |

---

## 📊 성과 요약

| 영역 | 성과 | 수치 |
|------|------|------|
| 분석 속도 | 하이브리드 병렬 실행 | **30% 단축** (211.1s → 147.4s) |
| 동시 처리량 | prefetch_count 최적화 | **12배 향상** (10 → 120) |
| API 비용 | 증분 분석 + 캐싱 + 티어링 | **70~90% 절감** (예상) |
| 데이터 품질 | 3단계 Entity Resolution | 중복 캐릭터 자동 병합 |
| 장문 분석 | Semantic Chunking + Context Rolling | 100% 분석 가능 |
| 개연성 검증 | RAG (Vector + Keyword) | 설정 충돌 **90% 감지** |
| 순서 보장 | Redis Sorted Set 분산 락 | 챕터 순서 보장 |
