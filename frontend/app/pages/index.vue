<script setup lang="ts">
import MarkdownIt from 'markdown-it'
import type { ProfileResponse, Skill } from '../types'

const md = new MarkdownIt({ html: true, linkify: true, breaks: true })
const renderMarkdown = (text: string) => text ? md.render(text) : ''

const config = useRuntimeConfig()
const { data: profile } = await useFetch<ProfileResponse>('/api/v1/profile', {
  baseURL: config.public.apiBase as string
})

// Group skills by category
const skillsByCategory = computed(() => {
  if (!profile.value?.skills) return {}
  return profile.value.skills.reduce((acc, skill) => {
    if (!acc[skill.category]) acc[skill.category] = []
    acc[skill.category]!.push(skill)
    return acc
  }, {} as Record<string, Skill[]>)
})

// Category display names
const categoryNames: Record<string, string> = {
  LANGUAGE: '언어',
  BACKEND: '백엔드',
  DEVOPS: 'DevOps'
}

// Typing texts for hero
const typingTexts = [
  'Software Engineer',
  'Backend Developer',
  'Problem Solver'
]
</script>

<template>
  <div>
    <!-- Hero Section -->
    <section class="hero-gradient min-h-[80vh] flex items-center relative overflow-hidden">
      <UContainer>
        <div class="grid grid-cols-1 lg:grid-cols-2 gap-12 items-center py-16">
          <!-- Left Content -->
          <div class="space-y-8 animate-fade-in-up">
            <div class="space-y-4">
              <p class="text-primary-500 font-semibold text-lg mb-1">
                안녕하세요, 저는
              </p>
              <div class="flex items-end flex-wrap gap-3 mb-4">
                <h1 class="text-4xl md:text-5xl lg:text-6xl font-bold text-gray-900 dark:text-white leading-tight">
                  {{ profile?.personalInfo?.name || 'Developer' }}
                </h1>
                <span class="text-xl md:text-2xl text-gray-600 dark:text-gray-400 font-medium mb-1.5">입니다.</span>
              </div>
              <div class="text-2xl md:text-3xl text-gray-600 dark:text-gray-300 h-10">
                <ClientOnly>
                  <TypingText :texts="typingTexts" />
                </ClientOnly>
              </div>
              <p class="text-lg text-gray-600 dark:text-gray-400 max-w-xl">
                {{ profile?.personalInfo?.shortBio || 'Spring Boot, Kotlin, Vue.js를 활용한 풀스택 개발자입니다.' }}
              </p>
            </div>

            <!-- CTA Buttons -->
            <div class="flex flex-wrap gap-4">
              <UButton
                to="/projects"
                size="xl"
                trailing-icon="i-lucide-arrow-right"
                class="group"
              >
                프로젝트 보기
              </UButton>
              <UButton
                to="#contact"
                size="xl"
                color="neutral"
                variant="subtle"
                icon="i-lucide-mail"
              >
                연락하기
              </UButton>
            </div>

            <!-- Social Links -->
            <SocialLinks 
              :github="profile?.personalInfo?.githubUrl"
              :linkedin="profile?.personalInfo?.linkedinUrl"
              :blog="profile?.personalInfo?.blogUrl"
              :email="profile?.personalInfo?.email"
              size="lg"
            />
          </div>

          <!-- Right - Profile Image -->
          <div class="flex justify-center lg:justify-end animate-fade-in-up stagger-2">
            <div class="relative">
              <div class="w-72 h-72 md:w-80 md:h-80 rounded-full overflow-hidden ring-4 ring-primary-500/20 shadow-2xl animate-float">
                <img 
                  :src="profile?.personalInfo?.profileImageUrl || 'https://picsum.photos/400/400'" 
                  alt="Profile"
                  class="w-full h-full object-cover"
                />
              </div>
              <!-- Decorative elements -->
              <div class="absolute -top-4 -right-4 w-24 h-24 bg-primary-500/10 rounded-full blur-2xl" />
              <div class="absolute -bottom-8 -left-8 w-32 h-32 bg-blue-500/10 rounded-full blur-2xl" />
            </div>
          </div>
        </div>
      </UContainer>
    </section>

    <!-- About Section -->
    <UPageSection id="about" title="About Me" description="기술 스택과 전문 분야">
      <div class="grid grid-cols-1 lg:grid-cols-5 gap-12">
        <!-- Bio (3/5 = 60%) -->
        <div class="lg:col-span-3 space-y-6">
          <div class="glass rounded-2xl p-6">
            <h3 class="text-xl font-semibold mb-4 text-gray-900 dark:text-white">자기소개</h3>
            <p class="text-gray-600 dark:text-gray-300 leading-relaxed whitespace-pre-line">
              {{ profile?.personalInfo?.bio || '저는 풀스택 개발자로서 백엔드와 프론트엔드 모두에서 경험을 쌓아왔습니다. 복잡한 문제를 해결하고 사용자 경험을 개선하는 것에 열정을 가지고 있습니다.' }}
            </p>
          </div>
        </div>

        <!-- Skills as Badges (2/5 = 40%) -->
        <div class="lg:col-span-2 space-y-6">
          <div v-for="(skills, category) in skillsByCategory" :key="category" class="space-y-3">
            <h3 class="text-lg font-semibold text-gray-900 dark:text-white flex items-center gap-2">
              <UIcon 
                :name="category === 'BACKEND' ? 'i-lucide-server' : 
                       category === 'DEVOPS' ? 'i-lucide-cloud' : 'i-lucide-code'"
                class="w-5 h-5 text-primary-500"
              />
              {{ categoryNames[category as string] || category }}
            </h3>
            <div class="flex flex-wrap gap-2">
              <UBadge 
                v-for="skill in skills" 
                :key="skill.id" 
                :label="skill.name" 
                color="primary" 
                variant="subtle"
                size="lg"
                class="tech-badge"
              />
            </div>
          </div>
        </div>
      </div>
    </UPageSection>

    <!-- Awards Section -->
    <UPageSection id="awards" title="Awards" description="수상 내역">
      <div class="grid grid-cols-1 md:grid-cols-2 gap-6 max-w-4xl mx-auto">
        <div v-for="award in profile?.awards" :key="award.id" class="glass rounded-xl p-6 card-hover border-l-4 border-primary-500 flex flex-col h-full">
          <div class="flex justify-between items-start mb-2 gap-4">
            <h4 class="font-bold text-lg text-gray-900 dark:text-white">{{ award.title }}</h4>
            <time class="text-sm font-medium text-gray-500 bg-gray-100 dark:bg-gray-800 px-2 py-1 rounded">{{ award.date }}</time>
          </div>
          <p class="text-primary-600 dark:text-primary-400 font-medium mb-2">{{ award.organization }}</p>
          <p class="text-sm text-gray-600 dark:text-gray-300 mt-auto">{{ award.description }}</p>
        </div>
      </div>
    </UPageSection>

    <!-- Featured Projects Section -->
    <UPageSection 
      v-if="profile?.featuredProjects?.length" 
      title="Featured Projects" 
      description="주요 프로젝트들을 확인해 보세요"
    >
      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        <ProjectCard 
          v-for="project in profile.featuredProjects" 
          :key="project.id" 
          :project="project" 
        />
      </div>
      
      <div class="flex justify-center mt-8">
        <UButton 
          to="/projects" 
          variant="outline" 
          size="lg"
          trailing-icon="i-lucide-arrow-right"
        >
          모든 프로젝트 보기
        </UButton>
      </div>
    </UPageSection>



    <!-- Education & Certifications Section -->
    <UPageSection title="Education & Certifications" description="학력 및 자격증">
      <div class="space-y-12">
        <!-- Education -->
        <div class="space-y-6">
          <h3 class="text-xl font-semibold flex items-center gap-2">
            <UIcon name="i-lucide-graduation-cap" class="text-primary-500" />
            Educations
          </h3>
          <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
             <div v-for="edu in profile?.educations" :key="edu.id" class="glass rounded-xl p-6 card-hover h-full">
              <div class="flex justify-between items-start mb-2">
                <h4 class="font-bold text-lg text-gray-900 dark:text-white">{{ edu.institution }}</h4>
                <span class="text-xs text-gray-500 bg-gray-100 dark:bg-gray-800 px-2 py-1 rounded">{{ edu.degree }}</span>
              </div>
              <p class="text-gray-700 dark:text-gray-300 mb-2">{{ edu.major }} <span v-if="edu.gpa" class="text-sm text-gray-500">({{ edu.gpa }})</span></p>
              <time class="text-sm text-gray-500 dark:text-gray-400 block mt-auto">{{ edu.startDate }} - {{ edu.endDate }}</time>
            </div>
          </div>
        </div>

        <!-- Certifications -->
        <div class="space-y-6">
          <h3 class="text-xl font-semibold flex items-center gap-2">
              <UIcon name="i-lucide-file-badge" class="text-primary-500" />
              Certifications & Language
          </h3>
          <div class="flex flex-wrap gap-3">
             <div v-for="cert in profile?.certifications" :key="cert.id" class="glass px-4 py-3 rounded-xl border border-gray-200 dark:border-gray-700 flex items-center gap-3">
               <span class="font-bold text-gray-900 dark:text-white">{{ cert.name }}</span>
               <span class="text-sm text-gray-500">{{ cert.date }}</span>
             </div>
             <div v-for="lang in profile?.languageSkills" :key="lang.id" class="glass px-4 py-3 rounded-xl border border-gray-200 dark:border-gray-700 flex items-center gap-3">
               <span class="font-bold text-gray-900 dark:text-white">{{ lang.name }}</span>
               <span class="text-sm text-primary-500 font-bold bg-primary-50 dark:bg-primary-900/30 px-2 py-0.5 rounded">{{ lang.level }}</span>
             </div>
          </div>
        </div>
      </div>
    </UPageSection>

    <!-- Activities Section -->
    <UPageSection title="Activities" description="스터디 및 대외활동">
      <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
        <div v-for="activity in profile?.activities" :key="activity.id" class="glass rounded-xl p-5 card-hover border-l-4 border-primary-500">
          <div class="flex justify-between items-start mb-2">
            <h4 class="font-bold text-gray-900 dark:text-white">{{ activity.title }}</h4>
            <span class="text-xs text-gray-500">{{ activity.period }}</span>
          </div>
          <p class="text-sm text-gray-600 dark:text-gray-300">{{ activity.description }}</p>
        </div>
      </div>
    </UPageSection>

    <!-- Contact Section -->
    <UPageSection id="contact" title="Contact" description="연락처">
      <div class="max-w-2xl mx-auto text-center space-y-8">
        <div class="glass rounded-2xl p-8 space-y-6">
          <p class="text-lg text-gray-600 dark:text-gray-300">
            새로운 기회나 협업에 대해 이야기하고 싶으시다면 언제든지 연락해 주세요!
          </p>
          
          <div class="flex flex-wrap justify-center gap-4">
            <UButton 
              :to="`mailto:${profile?.personalInfo?.email || 'your.email@example.com'}`"
              size="xl"
              icon="i-lucide-mail"
            >
              이메일 보내기
            </UButton>
            
            <UButton 
              v-if="profile?.personalInfo?.githubUrl"
              :to="profile.personalInfo.githubUrl"
              target="_blank"
              size="xl"
              color="neutral"
              variant="subtle"
              icon="i-simple-icons-github"
            >
              GitHub
            </UButton>
            
            <UButton 
              v-if="profile?.personalInfo?.linkedinUrl"
              :to="profile.personalInfo.linkedinUrl"
              target="_blank"
              size="xl"
              color="neutral"
              variant="subtle"
              icon="i-simple-icons-linkedin"
            >
              LinkedIn
            </UButton>
            
            <UButton 
              v-if="profile?.personalInfo?.blogUrl"
              :to="profile.personalInfo.blogUrl"
              target="_blank"
              size="xl"
              color="neutral"
              variant="subtle"
              icon="i-lucide-pen-line"
            >
              Blog
            </UButton>
          </div>

          <!-- Contact Info Cards -->
          <div class="grid grid-cols-1 sm:grid-cols-2 gap-4 mt-8">
            <div v-if="profile?.personalInfo?.email" class="bg-gray-50 dark:bg-gray-800/50 rounded-xl p-4 flex items-center gap-4">
              <div class="w-12 h-12 rounded-full bg-primary-100 dark:bg-primary-900/30 flex items-center justify-center">
                <UIcon name="i-lucide-mail" class="w-6 h-6 text-primary-500" />
              </div>
              <div class="text-left">
                <p class="text-sm text-gray-500 dark:text-gray-400">이메일</p>
                <p class="font-medium text-gray-900 dark:text-white">{{ profile.personalInfo.email }}</p>
              </div>
            </div>
            
            <div v-if="profile?.personalInfo?.phone" class="bg-gray-50 dark:bg-gray-800/50 rounded-xl p-4 flex items-center gap-4">
              <div class="w-12 h-12 rounded-full bg-primary-100 dark:bg-primary-900/30 flex items-center justify-center">
                <UIcon name="i-lucide-phone" class="w-6 h-6 text-primary-500" />
              </div>
              <div class="text-left">
                <p class="text-sm text-gray-500 dark:text-gray-400">전화번호</p>
                <p class="font-medium text-gray-900 dark:text-white">{{ profile.personalInfo.phone }}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </UPageSection>
  </div>
</template>
