<template>
  <div>
    <b-list-group-item :to="`/current-site?siteId=${site.id}`" :class="{ 'active-site': isSiteChosen() }">
      {{ site.name }}
    </b-list-group-item>
  </div>
</template>

<script>
export default {
  name: 'SiteBriefInfo',
  props: {
    site: {
      type: Object,
      default: () => ({
        id: null,
        name: null
      })
    }
  },
  methods: {
    isSiteChosen () {
      return +this.$route.query.siteId === this.site.id
    }
  }
}
</script>

<style scoped>
  .active-site {
    background: #c3f2e5;
  }
</style>
