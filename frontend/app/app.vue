<script setup>
useHead({
  meta: [
    { name: 'viewport', content: 'width=device-width, initial-scale=1' }
  ],
  link: [
    { rel: 'icon', href: '/favicon.ico' },
    { rel: 'preconnect', href: 'https://fonts.googleapis.com' },
    { rel: 'preconnect', href: 'https://fonts.gstatic.com', crossorigin: '' },
    { rel: 'stylesheet', href: 'https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap' }
  ],
  htmlAttrs: {
    lang: 'ko'
  }
})

const title = 'Portfolio | Full Stack Developer'
const description = 'Spring Boot, Kotlin, Vue.js를 활용한 풀스택 개발자 포트폴리오입니다. 프로젝트와 기술 스택을 확인해 보세요.'

useSeoMeta({
  title,
  description,
  ogTitle: title,
  ogDescription: description,
  ogImage: 'https://picsum.photos/1200/630',
  twitterImage: 'https://picsum.photos/1200/630',
  twitterCard: 'summary_large_image'
})
</script>

<template>
  <UApp>
    <UHeader>
      <template #left>
        <NuxtLink
          to="/"
          class="flex items-center gap-2 group"
        >
          <div class="w-8 h-8 rounded-lg bg-gradient-to-br from-primary-400 to-primary-600 flex items-center justify-center">
            <span class="text-white font-bold text-sm">P</span>
          </div>
          <span class="font-bold text-lg hidden sm:inline group-hover:text-primary-500 transition-colors">Portfolio</span>
        </NuxtLink>

        <!-- Navigation -->
        <nav class="hidden md:flex items-center gap-1 ml-8">
          <UButton
            to="/"
            variant="ghost"
            color="neutral"
            label="Home"
          />
          <UButton
            to="/projects"
            variant="ghost"
            color="neutral"
            label="Projects"
          />
          <UButton
            to="/#about"
            variant="ghost"
            color="neutral"
            label="About"
          />

          <UButton
            to="/#contact"
            variant="ghost"
            color="neutral"
            label="Contact"
          />
        </nav>
      </template>

      <template #right>
        <UButton
          to="https://github.com/tjsahr9191"
          target="_blank"
          icon="i-simple-icons-github"
          aria-label="GitHub"
          color="neutral"
          variant="ghost"
        />
        <UButton
          to="https://velog.io/@dbtjsahr/posts"
          target="_blank"
          icon="i-lucide-pen-line"
          aria-label="Blog"
          color="neutral"
          variant="ghost"
        />
      </template>
    </UHeader>

    <UMain class="min-h-screen">
      <NuxtPage />
    </UMain>

    <USeparator />

    <UFooter>
      <template #left>
        <div class="flex flex-col sm:flex-row sm:items-center gap-2">
          <p class="text-sm text-muted">
            © {{ new Date().getFullYear() }} Portfolio. Built with
          </p>
          <div class="flex items-center gap-2">
            <UBadge
              label="Nuxt 3"
              color="green"
              variant="subtle"
              size="sm"
            />
            <UBadge
              label="Spring Boot"
              color="green"
              variant="subtle"
              size="sm"
            />
          </div>
        </div>
      </template>

      <template #right>
        <div class="flex items-center gap-2">
          <UButton
            to="https://github.com/tjsahr9191"
            target="_blank"
            icon="i-simple-icons-github"
            aria-label="GitHub"
            color="neutral"
            variant="ghost"
            size="sm"
          />
          <UButton
            to="https://velog.io/@dbtjsahr/posts"
            target="_blank"
            icon="i-lucide-pen-line"
            aria-label="Blog"
            color="neutral"
            variant="ghost"
            size="sm"
          />
        </div>
      </template>
    </UFooter>
  </UApp>
</template>
