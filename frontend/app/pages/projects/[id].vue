<script setup lang="ts">
import MarkdownIt from 'markdown-it'
import type { Project } from '../../types'

const route = useRoute()
const md = new MarkdownIt({
  html: true,
  linkify: true,
  typographer: true
})

// Customize renderer to add IDs to headings for linking
md.renderer.rules.heading_open = (tokens, idx) => {
  const token = tokens[idx]
  const contentToken = tokens[idx + 1]
  const title = contentToken?.content || ''

  if (!token) return ''

  // Create a simple ID: remove special chars, spaces to hyphens, lowercase
  const slug = title
    .toLowerCase()
    .replace(/[^\w\s-가-힣]/g, '') // Keep Korean, alphanumeric, spaces, hyphens
    .trim()
    .replace(/\s+/g, '-')

  return `<h${token.tag.slice(1)} id="${slug}">`
}

const config = useRuntimeConfig()

const { data: project } = await useFetch<Project>(`/api/v1/projects/${route.params.id}`, {
  baseURL: config.public.apiBase as string
})
const { data: allProjects } = await useFetch<Project[]>('/api/v1/projects', {
  baseURL: config.public.apiBase as string
})

// TOC State
interface TocItem {
  id: string
  text: string
  level: number
}
const toc = ref<TocItem[]>([])
const activeId = ref<string>('')

// Render content
const renderedContent = computed(() => {
  return project.value ? md.render(project.value.content) : ''
})

// Extract TOC on mount/update based on content
watchEffect(() => {
  if (!project.value?.content) return

  const matches = [...project.value.content.matchAll(/^(#{1,3})\s+(.+)$/gm)]
  toc.value = matches.map(match => {
    const level = match[1].length
    const text = match[2] || ''
    const id = text
      .toLowerCase()
      .replace(/[^\w\s-가-힣]/g, '')
      .trim()
      .replace(/\s+/g, '-')

    return { id, text, level }
  })
})

// Scroll handling for active state
onMounted(() => {
  const observer = new IntersectionObserver(entries => {
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        activeId.value = entry.target.id
      }
    })
  }, { rootMargin: '-100px 0px -66% 0px' })

  // Observe all headings
  setTimeout(() => {
    toc.value.forEach(item => {
      const el = document.getElementById(item.id)
      if (el) observer.observe(el)
    })
  }, 500) // Wait for render
})

// Scroll to section
const scrollToSection = (id: string) => {
  const el = document.getElementById(id)
  if (el) {
    const offset = 80 // header height approx
    const elementPosition = el.getBoundingClientRect().top
    const offsetPosition = elementPosition + window.pageYOffset - offset

    window.scrollTo({
      top: offsetPosition,
      behavior: 'smooth'
    })
    activeId.value = id
  }
}

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
          <div
            v-if="project.techStack?.length"
            class="flex flex-wrap justify-center gap-2"
          >
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
              <UIcon
                name="i-lucide-calendar"
                class="w-5 h-5"
              />
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
      <!-- Layout Container -->
      <div class="max-w-7xl mx-auto grid grid-cols-1 lg:grid-cols-12 gap-12 px-4 sm:px-6 lg:px-8">
        <!-- Main Content (Left, 9 cols) -->
        <div class="lg:col-span-9">
          <UCard class="overflow-hidden">
            <!-- eslint-disable vue/no-v-html -->
            <div
              class="prose dark:prose-invert max-w-none"
              v-html="renderedContent"
            />
            <!-- eslint-enable vue/no-v-html -->
          </UCard>
        </div>

        <!-- Sidebar (Right, 3 cols) - Hidden on Mobile -->
        <aside class="hidden lg:block lg:col-span-3">
          <div class="sticky top-24 space-y-4">
            <div class="bg-gray-50 dark:bg-gray-800/50 rounded-lg p-4 border border-gray-200 dark:border-gray-700">
              <h3 class="font-bold text-gray-900 dark:text-white mb-4 flex items-center gap-2">
                <UIcon
                  name="i-lucide-list"
                  class="w-4 h-4"
                />
                목차
              </h3>
              <nav class="space-y-1">
                <button
                  v-for="item in toc"
                  :key="item.id"
                  class="block text-sm text-left w-full truncate py-1.5 transition-colors border-l-2 px-3"
                  :class="[
                    activeId === item.id
                      ? 'border-primary-500 text-primary-600 dark:text-primary-400 font-medium bg-primary-50 dark:bg-primary-900/10'
                      : 'border-transparent text-gray-600 dark:text-gray-400 hover:text-gray-900 dark:hover:text-gray-200 hover:border-gray-300 dark:hover:border-gray-600',
                    item.level === 2 ? 'pl-3' : '',
                    item.level === 3 ? 'pl-6' : ''
                  ]"
                  @click="scrollToSection(item.id)"
                >
                  {{ item.text }}
                </button>
              </nav>
            </div>
          </div>
        </aside>
      </div>
    </UPageSection>

    <!-- Navigation between projects -->
    <UPageSection>
      <div class="max-w-4xl mx-auto">
        <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
          <!-- Previous Project -->
          <div
            v-if="prevProject"
            class="group"
          >
            <NuxtLink
              :to="`/projects/${prevProject.id}`"
              class="block"
            >
              <UCard class="h-full card-hover">
                <div class="flex items-center gap-4">
                  <UIcon
                    name="i-lucide-arrow-left"
                    class="w-6 h-6 text-gray-400 group-hover:text-primary-500 transition-colors"
                  />
                  <div>
                    <p class="text-sm text-gray-500 dark:text-gray-400">
                      이전 프로젝트
                    </p>
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
          <div
            v-if="nextProject"
            class="group"
          >
            <NuxtLink
              :to="`/projects/${nextProject.id}`"
              class="block"
            >
              <UCard class="h-full card-hover">
                <div class="flex items-center justify-end gap-4 text-right">
                  <div>
                    <p class="text-sm text-gray-500 dark:text-gray-400">
                      다음 프로젝트
                    </p>
                    <p class="font-semibold text-gray-900 dark:text-white group-hover:text-primary-500 transition-colors">
                      {{ nextProject.title }}
                    </p>
                  </div>
                  <UIcon
                    name="i-lucide-arrow-right"
                    class="w-6 h-6 text-gray-400 group-hover:text-primary-500 transition-colors"
                  />
                </div>
              </UCard>
            </NuxtLink>
          </div>
        </div>
      </div>
    </UPageSection>

    <!-- Back to projects -->
    <div class="flex justify-center pb-16">
      <UButton
        to="/projects"
        icon="i-lucide-grid-3x3"
        label="모든 프로젝트 보기"
        variant="outline"
      />
    </div>
  </div>

  <!-- Loading State -->
  <div
    v-else
    class="flex flex-col justify-center items-center h-64 gap-4"
  >
    <UIcon
      name="i-lucide-loader-2"
      class="animate-spin w-8 h-8 text-primary-500"
    />
    <p class="text-gray-500 dark:text-gray-400">
      로딩중...
    </p>
  </div>
</template>

<style scoped>
/* Scroll margin for IDs to account for fixed header */
:deep(h1[id]),
:deep(h2[id]),
:deep(h3[id]) {
  scroll-margin-top: 100px;
}
</style>
