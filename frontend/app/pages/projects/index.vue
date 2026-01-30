<script setup lang="ts">
import type { Project } from '../../types'

const config = useRuntimeConfig()
const { data: projects } = await useFetch<Project[]>('/api/v1/projects', {
  baseURL: config.public.apiBase as string
})

// Get all unique tech stacks
const allTechStacks = computed(() => {
  if (!projects.value) return []
  const techs = new Set<string>()
  projects.value.forEach(p => {
    p.techStack?.forEach(t => techs.add(t))
  })
  return Array.from(techs).sort()
})

// Selected filter
const selectedTech = ref<string | null>(null)

// Filtered projects
const filteredProjects = computed(() => {
  if (!projects.value) return []
  if (!selectedTech.value) return projects.value
  return projects.value.filter(p => p.techStack?.includes(selectedTech.value!))
})

// Clear filter
const clearFilter = () => {
  selectedTech.value = null
}
</script>

<template>
  <div>
    <UPageHero
      title="Projects"
      description="제가 작업한 프로젝트들을 확인해 보세요"
      align="center"
    />

    <UPageSection>
      <!-- Filter Section -->
      <div class="mb-8">
        <div class="flex flex-wrap items-center gap-2">
          <span class="text-sm text-gray-500 dark:text-gray-400 mr-2">필터:</span>
          <UButton
            label="전체"
            :color="selectedTech === null ? 'primary' : 'neutral'"
            :variant="selectedTech === null ? 'solid' : 'ghost'"
            size="sm"
            @click="clearFilter"
          />
          <UButton
            v-for="tech in allTechStacks"
            :key="tech"
            :label="tech"
            :color="selectedTech === tech ? 'primary' : 'neutral'"
            :variant="selectedTech === tech ? 'solid' : 'ghost'"
            size="sm"
            @click="selectedTech = tech"
          />
        </div>
        
        <p v-if="selectedTech" class="text-sm text-gray-500 dark:text-gray-400 mt-2">
          {{ filteredProjects.length }}개의 프로젝트가 "{{ selectedTech }}"와 관련되어 있습니다.
        </p>
      </div>

      <!-- Projects Grid -->
      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        <ProjectCard 
          v-for="project in filteredProjects" 
          :key="project.id" 
          :project="project"
        />
      </div>

      <!-- Empty State -->
      <div v-if="filteredProjects.length === 0" class="text-center py-12">
        <UIcon name="i-lucide-search-x" class="w-16 h-16 text-gray-400 mx-auto mb-4" />
        <p class="text-gray-500 dark:text-gray-400">해당 기술 스택을 사용한 프로젝트가 없습니다.</p>
        <UButton
          label="필터 초기화"
          variant="link"
          class="mt-2"
          @click="clearFilter"
        />
      </div>
    </UPageSection>
  </div>
</template>