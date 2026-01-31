<script setup lang="ts">
import type { Project } from '../types'

defineProps<{
  project: Project
}>()
</script>

<template>
  <div
    class="card-hover cursor-pointer group h-full"
    @click="navigateTo(`/projects/${project.id}`)"
  >
    <UCard class="h-full overflow-hidden flex flex-col">
      <template #header>
        <div class="h-48 bg-gradient-to-br from-gray-100 to-gray-200 dark:from-gray-800 dark:to-gray-900 -mx-4 -mt-4 mb-4 flex items-center justify-center overflow-hidden relative">
          <img
            v-if="project.thumbnailUrl"
            :src="project.thumbnailUrl"
            :alt="project.title"
            class="w-full h-full object-cover transition-transform duration-300 group-hover:scale-110"
          >
          <div
            v-else
            class="flex flex-col items-center justify-center gap-2"
          >
            <UIcon
              name="i-lucide-code-2"
              class="w-16 h-16 text-gray-400 dark:text-gray-500"
            />
          </div>

          <!-- Overlay gradient -->
          <div class="absolute inset-0 bg-gradient-to-t from-black/20 to-transparent opacity-0 group-hover:opacity-100 transition-opacity duration-300" />
        </div>

        <h3 class="text-xl font-bold text-gray-900 dark:text-white group-hover:text-primary-500 transition-colors line-clamp-2 min-h-[3.5rem]">
          {{ project.title }}
        </h3>
      </template>

      <div class="flex flex-col flex-grow">
        <p class="text-gray-600 dark:text-gray-300 line-clamp-2 mb-4 min-h-[3rem]">
          {{ project.description }}
        </p>

        <!-- Tech Stack -->
        <div class="flex flex-wrap gap-1.5 mb-4 min-h-[2rem]">
          <template v-if="project.techStack?.length">
            <UBadge
              v-for="tech in project.techStack.slice(0, 4)"
              :key="tech"
              :label="tech"
              color="primary"
              variant="subtle"
              size="sm"
              class="tech-badge"
            />
            <UBadge
              v-if="project.techStack.length > 4"
              :label="`+${project.techStack.length - 4}`"
              color="neutral"
              variant="subtle"
              size="sm"
            />
          </template>
        </div>
      </div>

      <template #footer>
        <div class="flex items-center justify-between">
          <span class="text-sm text-gray-500 dark:text-gray-400">
            {{ project.startDate }} {{ project.endDate ? `- ${project.endDate}` : '- 진행중' }}
          </span>
          <UButton
            label="자세히 보기"
            variant="ghost"
            color="primary"
            trailing-icon="i-lucide-arrow-right"
            size="sm"
            class="group-hover:translate-x-1 transition-transform"
          />
        </div>
      </template>
    </UCard>
  </div>
</template>
