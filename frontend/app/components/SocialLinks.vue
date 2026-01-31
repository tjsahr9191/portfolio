<script setup lang="ts">
defineProps<{
  github?: string
  linkedin?: string
  blog?: string
  email?: string
  size?: 'sm' | 'md' | 'lg'
}>()

const iconSize = {
  sm: 'w-5 h-5',
  md: 'w-6 h-6',
  lg: 'w-8 h-8'
}
</script>

<template>
  <div class="flex items-center gap-4">
    <a
      v-if="github"
      :href="github"
      target="_blank"
      class="social-icon text-gray-600 dark:text-gray-400 hover:text-gray-900 dark:hover:text-white"
      aria-label="GitHub"
    >
      <UIcon
        name="i-simple-icons-github"
        :class="iconSize[size || 'md']"
      />
    </a>

    <a
      v-if="linkedin"
      :href="linkedin"
      target="_blank"
      class="social-icon text-gray-600 dark:text-gray-400 hover:text-[#0A66C2]"
      aria-label="LinkedIn"
    >
      <UIcon
        name="i-simple-icons-linkedin"
        :class="iconSize[size || 'md']"
      />
    </a>

    <a
      v-if="blog"
      :href="blog"
      target="_blank"
      class="social-icon text-gray-600 dark:text-gray-400 hover:text-primary-500"
      aria-label="Blog"
    >
      <UIcon
        name="i-lucide-pen-line"
        :class="iconSize[size || 'md']"
      />
    </a>

    <a
      v-if="email"
      :href="`mailto:${email}`"
      class="social-icon text-gray-600 dark:text-gray-400 hover:text-red-500"
      aria-label="Email"
    >
      <UIcon
        name="i-lucide-mail"
        :class="iconSize[size || 'md']"
      />
    </a>
  </div>
</template>
