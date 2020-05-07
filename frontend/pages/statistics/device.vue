<template>
  <div class="container">
    <div class="h3 text-center">
      Visits per device for the last week
    </div>
    <DoughnutChart :data="deviceVisits" />
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
import DoughnutChart from '../../components/chart/DoughnutChart'

export default {
  name: 'Device',
  components: {
    DoughnutChart
  },
  computed: {
    ...mapState('statistics', ['deviceVisits'])
  },
  mounted () {
    this.loadDeviceVisitsForTheLastWeek({
      siteId: this.$route.query.siteId
    })
  },
  methods: {
    ...mapActions('statistics', ['loadDeviceVisitsForTheLastWeek'])
  }
}
</script>

<style scoped>

</style>
