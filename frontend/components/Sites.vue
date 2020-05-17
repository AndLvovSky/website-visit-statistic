<template>
  <div>
    <div class="h2 text-center">
      Your Sites
    </div>
    <div v-if="sites.length === 0" class="h3">
      <div>
        You don't have any site yet
      </div>
      <b-button class="mt-4" variant="primary" @click="$router.push('/new-site')">
        Create New
      </b-button>
    </div>
    <b-list-group>
      <SiteBriefInfo v-for="site in sites" :key="site.id" :site="site" />
    </b-list-group>
  </div>
</template>

<script>
import SiteBriefInfo from './SiteBriefInfo'

export default {
  name: 'Sites',
  components: {
    SiteBriefInfo
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
