# Portfolio Website

Spring Boot와 Nuxt 3로 구축된 모던한 풀스택 포트폴리오 웹사이트입니다. 개발자의 경력, 프로젝트, 기술 스택을 효과적으로 보여주기 위해 설계되었습니다.

## 🚀 Key Features

- **Modern UI/UX**: TailwindCSS와 Nuxt UI를 활용한 깔끔하고 반응형 디자인
- **Dynamic Content**:
  - **Typing Animation**: Hero 섹션의 역동적인 텍스트 효과
  - **Project Filtering**: 기술 스택별 프로젝트 필터링 기능
  - **Dark Mode**: 시스템 설정 연동 및 수동 토글 지원
- **Comprehensive Profile**: 
  - 학력(Education), 수상(Awards), 자격증(Certifications), 대외활동(Activities) 등 상세 정보 섹션
  - Markdown 렌더링 지원으로 풍부한 텍스트 표현
- **Robust Backend**:
  - Kotlin & Spring Boot 3 기반의 안정적인 API 서버
  - Profile별 설정 분리 (Local: H2, Prod: MySQL)

## 🛠 Tech Stack

### Backend
- **Language**: Kotlin
- **Framework**: Spring Boot 3.4.1
- **Database**: 
  - Local: H2 (In-memory)
  - Prod: MySQL
- **Build Tool**: Gradle (Kotlin DSL)

### Frontend
- **Framework**: Nuxt 3.15.2 (Vue 3)
- **Styling**: TailwindCSS
- **UI Library**: Nuxt UI
- **Package Manager**: pnpm

## ⚙️ Getting Started

### Prerequisites
- JDK 17+
- Node.js 18+
- pnpm

### Backend Setup

```bash
# 프로젝트 루트 디렉토리 이동
cd c:\jungle\portfolio\portfolio

# 실행 (기본 local 프로필: H2 사용)
./gradlew bootRun
```
서버는 `http://localhost:8080`에서 실행됩니다.
- H2 Console: `http://localhost:8080/h2-console`
- API Endpoint: `http://localhost:8080/api/v1/profile`

### Frontend Setup

```bash
# 프론트엔드 디렉토리 이동
cd frontend

# 의존성 설치
pnpm install

# 개발 서버 실행
pnpm dev
```
웹사이트는 `http://localhost:3000`에서 확인할 수 있습니다.

## 📝 Configuration

설정 파일은 `src/main/resources`에 위치하며, 실행 환경에 따라 분리되어 있습니다.

| 파일명 | 설명 | DB 설정 |
|--------|------|---------|
| `application.yml` | 공통 설정 및 기본 프로필 지정 | - |
| `application-local.yml` | 로컬 개발용 설정 (기본값) | H2 (Mem) |
| `application-prod.yml` | 운영 배포용 설정 | MySQL |

운영 환경(Prod)으로 실행하려면:
```bash
./gradlew bootRun --args='--spring.profiles.active=prod'
```

## 📂 Project Structure

```
portfolio/
├── src/main/kotlin/sm/portfolio/api  # Backend Source
│   ├── config/       # 설정 및 데이터 초기화 (DataInitializer)
│   ├── controller/   # API 컨트롤러
│   ├── domain/       # JPA 엔티티
│   ├── repository/   # DB 리포지토리
│   └── service/      # 비즈니스 로직
├── frontend/         # Frontend Source (Nuxt 3)
│   ├── app/
│   │   ├── components/  # 재사용 가능한 UI 컴포넌트
│   │   ├── pages/       # 페이지 라우팅
│   │   └── types/       # TypeScript 인터페이스
│   └── nuxt.config.ts   # Nuxt 설정
└── build.gradle.kts  # Backend Dependencies
```

## License
MIT License
