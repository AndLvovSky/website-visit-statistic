<template>
  <div>
    <div class="h2 text-center">
      Your Sites
    </div>
    <div v-if="sites.length === 0" class="h3">
      You don't have any site yet
    </div>
    <b-list-group>
      <Site v-for="site in sites" :key="site.id" :site="site" />
    </b-list-group>
  </div>
</template>

<script>
import Site from './Site'

export default {
  name: 'Sites',
  components: {
    Site
  },
  props: {
    sites: {
      type: Array,
      default: () => []
    }
  }
}
</script>

<style scoped>

</style>
