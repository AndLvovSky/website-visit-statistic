<template>
  <div class="container">
    <div class="h2 text-center">
      Current site
    </div>
    <div class="h3">
      Name: {{ site.name }}
    </div>
    <div class="h3">
      Link:
      <a :href="site.link">
        {{ site.link }}
      </a>
    </div>
    <IntegrationCode :api-key="site.apiKey" />
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
import IntegrationCode from '../components/IntegrationCode'

export default {
  components: { IntegrationCode },
  computed: {
    ...mapState(['site'])
  },
  mounted () {
    this.loadSite({
      id: this.$route.query.siteId
    })
  },
  methods: {
    ...mapActions(['loadSite'])
  }
}
</script>

<style>
</style>
