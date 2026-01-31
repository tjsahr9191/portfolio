<script setup lang="ts">
const props = defineProps<{
  texts: string[]
  typingSpeed?: number
  deleteSpeed?: number
  pauseTime?: number
}>()

const displayText = ref('')
const currentIndex = ref(0)
const isDeleting = ref(false)

const typingSpeed = props.typingSpeed || 100
const deleteSpeed = props.deleteSpeed || 50
const pauseTime = props.pauseTime || 2000

const type = () => {
  const currentText = props.texts[currentIndex.value]
  if (!currentText) return

  if (!isDeleting.value) {
    displayText.value = currentText.substring(0, displayText.value.length + 1)

    if (displayText.value === currentText) {
      setTimeout(() => {
        isDeleting.value = true
        type()
      }, pauseTime)
      return
    }
  } else {
    displayText.value = currentText.substring(0, displayText.value.length - 1)

    if (displayText.value === '') {
      isDeleting.value = false
      currentIndex.value = (currentIndex.value + 1) % props.texts.length
    }
  }

  setTimeout(type, isDeleting.value ? deleteSpeed : typingSpeed)
}

onMounted(() => {
  type()
})
</script>

<template>
  <span class="typing-cursor">{{ displayText }}</span>
</template>
