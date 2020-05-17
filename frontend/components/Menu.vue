<template>
  <b-navbar sticky type="light" class="custom-navbar">
    <b-navbar-nav>
      <b-nav-dropdown text="Site">
        <b-dropdown-item :to="`/${getSiteSuffix()}`" exact-active-class="active">
          Sites
        </b-dropdown-item>
        <b-dropdown-item v-show="this.$route.query.siteId" :to="`/current-site${getSiteSuffix()}`" exact-active-class="active">
          Current Site
        </b-dropdown-item>
        <b-dropdown-item :to="`/new-site${getSiteSuffix()}`" exact-active-class="active">
          New Site
        </b-dropdown-item>
      </b-nav-dropdown>
      <b-nav-dropdown v-show="this.$route.query.siteId" text="Statistics">
        <b-dropdown-item :to="`/statistics/time${getSiteSuffix()}`" exact-active-class="active">
          Time
        </b-dropdown-item>
        <b-dropdown-item :to="`/statistics/device${getSiteSuffix()}`" exact-active-class="active">
          Device
        </b-dropdown-item>
      </b-nav-dropdown>
    </b-navbar-nav>
  </b-navbar>
</template>

<script>
export default {
  name: 'Menu',
  methods: {
    getSiteSuffix () {
      return this.$route.query.siteId ? `?siteId=${this.$route.query.siteId}` : ''
    }
  }
}
</script>

<style scoped>
.custom-navbar {
  background: #64a8b8;
}
</style>
