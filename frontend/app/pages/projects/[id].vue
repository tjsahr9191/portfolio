<script setup lang="ts">
import MarkdownIt from 'markdown-it'
import type { Project } from '../../types'

const route = useRoute()
const md = new MarkdownIt()

const { data: project } = await useFetch<Project>(`http://localhost:8080/api/v1/projects/${route.params.id}`)
const { data: allProjects } = await useFetch<Project[]>('http://localhost:8080/api/v1/projects')

const renderedContent = computed(() => {
    return project.value ? md.render(project.value.content) : ''
})

// Find prev/next projects
const currentIndex = computed(() => {
  if (!allProjects.value || !project.value) return -1
  return allProjects.value.findIndex(p => p.id === project.value!.id)
})

const prevProject = computed(() => {
  if (currentIndex.value <= 0 || !allProjects.value) return null
  return allProjects.value[currentIndex.value - 1]
})

const nextProject = computed(() => {
  if (!allProjects.value || currentIndex.value >= allProjects.value.length - 1) return null
  return allProjects.value[currentIndex.value + 1]
})

// Format date
const formatDate = (date: string) => {
  return new Date(date).toLocaleDateString('ko-KR', {
    year: 'numeric',
    month: 'long'
  })
}
</script>

<template>
  <div v-if="project">
    <!-- Hero Section -->
    <section class="hero-gradient py-16">
      <UContainer>
        <div class="max-w-4xl mx-auto text-center space-y-6">
          <h1 class="text-4xl md:text-5xl font-bold text-gray-900 dark:text-white">
            {{ project.title }}
          </h1>
          <p class="text-xl text-gray-600 dark:text-gray-300">
            {{ project.description }}
          </p>
          
          <!-- Tech Stack -->
          <div v-if="project.techStack?.length" class="flex flex-wrap justify-center gap-2">
            <UBadge 
              v-for="tech in project.techStack" 
              :key="tech" 
              :label="tech" 
              color="primary" 
              variant="subtle"
              size="lg"
              class="tech-badge"
            />
          </div>

          <!-- Meta Info -->
          <div class="flex flex-wrap justify-center items-center gap-6 text-gray-500 dark:text-gray-400">
            <div class="flex items-center gap-2">
              <UIcon name="i-lucide-calendar" class="w-5 h-5" />
              <span>{{ formatDate(project.startDate) }} - {{ project.endDate ? formatDate(project.endDate) : '진행중' }}</span>
            </div>
          </div>

          <!-- Action Buttons -->
          <div class="flex flex-wrap justify-center gap-4 pt-4">
            <UButton 
              v-if="project.repoUrl" 
              :to="project.repoUrl" 
              target="_blank" 
              icon="i-simple-icons-github" 
              label="GitHub" 
              size="lg"
              color="neutral"
              variant="subtle"
            />
            <UButton 
              v-if="project.demoUrl" 
              :to="project.demoUrl" 
              target="_blank" 
              icon="i-lucide-external-link" 
              label="Live Demo" 
              size="lg"
            />
          </div>
        </div>
      </UContainer>
    </section>

    <!-- Content Section -->
    <UPageSection>
      <div class="max-w-4xl mx-auto">
        <UCard class="overflow-hidden">
          <div class="prose dark:prose-invert max-w-none" v-html="renderedContent" />
        </UCard>
      </div>
    </UPageSection>

    <!-- Navigation between projects -->
    <UPageSection>
      <div class="max-w-4xl mx-auto">
        <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
          <!-- Previous Project -->
          <div v-if="prevProject" class="group">
            <NuxtLink :to="`/projects/${prevProject.id}`" class="block">
              <UCard class="h-full card-hover">
                <div class="flex items-center gap-4">
                  <UIcon name="i-lucide-arrow-left" class="w-6 h-6 text-gray-400 group-hover:text-primary-500 transition-colors" />
                  <div>
                    <p class="text-sm text-gray-500 dark:text-gray-400">이전 프로젝트</p>
                    <p class="font-semibold text-gray-900 dark:text-white group-hover:text-primary-500 transition-colors">
                      {{ prevProject.title }}
                    </p>
                  </div>
                </div>
              </UCard>
            </NuxtLink>
          </div>
          <div v-else />

          <!-- Next Project -->
          <div v-if="nextProject" class="group">
            <NuxtLink :to="`/projects/${nextProject.id}`" class="block">
              <UCard class="h-full card-hover">
                <div class="flex items-center justify-end gap-4 text-right">
                  <div>
                    <p class="text-sm text-gray-500 dark:text-gray-400">다음 프로젝트</p>
                    <p class="font-semibold text-gray-900 dark:text-white group-hover:text-primary-500 transition-colors">
                      {{ nextProject.title }}
                    </p>
                  </div>
                  <UIcon name="i-lucide-arrow-right" class="w-6 h-6 text-gray-400 group-hover:text-primary-500 transition-colors" />
                </div>
              </UCard>
            </NuxtLink>
          </div>
        </div>
      </div>
    </UPageSection>
    
    <!-- Back to projects -->
    <div class="flex justify-center pb-16">
      <UButton to="/projects" icon="i-lucide-grid-3x3" label="모든 프로젝트 보기" variant="outline" />
    </div>
  </div>
  
  <!-- Loading State -->
  <div v-else class="flex flex-col justify-center items-center h-64 gap-4">
    <UIcon name="i-lucide-loader-2" class="animate-spin w-8 h-8 text-primary-500" />
    <p class="text-gray-500 dark:text-gray-400">로딩중...</p>
  </div>
</template>
