<template>
  <div class="mb-4">
    <Head />
    <Menu class="mb-4" />
    <nuxt />
  </div>
</template>

<script>
import Head from '../components/Head'
import Menu from '../components/Menu'

export default {
  components: {
    Menu,
    Head
  }
}
</script>
