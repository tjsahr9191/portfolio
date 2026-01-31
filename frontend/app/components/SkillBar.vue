<script setup lang="ts">
defineProps<{
  name: string
  proficiency: number
  color?: string
}>()

const barWidth = ref(0)

onMounted(() => {
  setTimeout(() => {
    barWidth.value = 1
  }, 100)
})
</script>

<template>
  <div class="space-y-2">
    <div class="flex justify-between items-center">
      <span class="font-medium text-gray-700 dark:text-gray-300">{{ name }}</span>
      <span class="text-sm text-gray-500 dark:text-gray-400">{{ proficiency }}%</span>
    </div>
    <div class="skill-bar">
      <div
        class="skill-bar-fill"
        :style="{ width: barWidth ? `${proficiency}%` : '0%' }"
      />
    </div>
  </div>
</template>
