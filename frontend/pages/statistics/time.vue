<template>
  <div class="container">
    <div class="h3 text-center">
      Visits per day for the last week
    </div>
    <BarChart :data="timeVisits" />
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
import BarChart from '../../components/chart/BarChart'

export default {
  name: 'Time',
  components: {
    BarChart
  },
  computed: {
    ...mapState('statistics', ['timeVisits'])
  },
  mounted () {
    this.loadVisitsPerDayOfWeek({
      siteId: this.$route.query.siteId
    })
  },
  methods: {
    ...mapActions('statistics', ['loadVisitsPerDayOfWeek'])
  }
}
</script>

<style scoped>

</style>
