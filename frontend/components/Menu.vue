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
      <b-nav-item :to="`/statistics/time${getSiteSuffix()}`" exact-active-class="active">
        Time
      </b-nav-item>
      <b-nav-item :to="`/statistics/device${getSiteSuffix()}`" exact-active-class="active">
        Device
      </b-nav-item>
      <b-nav-item :to="`/statistics/referral-website${getSiteSuffix()}`" exact-active-class="active">
        Referrals
      </b-nav-item>
      <b-nav-item :to="`/statistics/ab-statistics${getSiteSuffix()}`" exact-active-class="active">
        A/B
      </b-nav-item>
      <b-nav-item :to="`/statistics/route-statistics${getSiteSuffix()}`" exact-active-class="active">
        Pages
      </b-nav-item>
      <b-nav-dropdown v-show="this.$route.query.siteId" text="Country">
        <b-dropdown-item :to="`/statistics/country${getSiteSuffix()}`" exact-active-class="active">
          Chart
        </b-dropdown-item>
        <b-dropdown-item :to="`/statistics/country-map${getSiteSuffix()}`" exact-active-class="active">
          Map
        </b-dropdown-item>
      </b-nav-dropdown>
      <b-nav-item :to="`/documentation${getSiteSuffix()}`" exact-active-class="active">
        Documentation
      </b-nav-item>
    </b-navbar-nav>
    <b-navbar-nav class="ml-auto">
      <b-button v-if="!$route.name.startsWith('login') && !$route.name.startsWith('sign-up')" variant="link" @click="logout">
        Logout
      </b-button>
    </b-navbar-nav>
  </b-navbar>
</template>

<script>
export default {
  name: 'Menu',
  methods: {
    getSiteSuffix () {
      return this.$route.query.siteId ? `?siteId=${this.$route.query.siteId}` : ''
    },
    logout () {
      this.$axios.post('/logout', { }, {
        auth: {
          username: this.username,
          password: this.password
        }
      }).then(() => (this.$router.push('/')))
    }
  }
}
</script>

<style>
.custom-navbar {
  background: #64a8b8;
}
.dropdown-menu {
  z-index: 1050
}
</style>
