<template>
  <div class="mb-4 main-container">
    <div class="content-container">
      <Head />
      <Menu class="mb-4" />
      <nuxt />
    </div>
    <Footer class="footer-container mt-4" />
  </div>
</template>

<script>
import Head from '../components/Head'
import Menu from '../components/Menu'
import Footer from '../components/Footer'

export default {
  components: {
    Menu,
    Head,
    Footer
  }
}
</script>

<style>
html,body,#__nuxt,#__layout {
  height: 100%;
}
</style>

<style scoped>
.main-container {
  height: 100%;
  display: flex;
  flex-direction: column;
}
.footer-container {
  flex-shrink: 0;
}
.content-container {
  flex: 1 0 auto;
}
</style>
