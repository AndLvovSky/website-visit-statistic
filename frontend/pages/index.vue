<template>
  <div class="container">
    <Sites :sites="sites" />
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
import Sites from '../components/Sites'

export default {
  components: {
    Sites
  },
  computed: {
    ...mapState(['sites'])
  },
  mounted () {
    this.loadSites()
  },
  methods: {
    ...mapActions(['loadSites'])
  }
}
</script>

<style>
</style>
