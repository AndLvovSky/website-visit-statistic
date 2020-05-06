<template>
  <div class="container-fluid">
    <div class="row">
      <div class="col-sm">
        <div class="h3 text-center">
          Visits per day for the last week
        </div>
        <BarChart :data="timeVisits" />
      </div>
      <div class="col-sm">
        <div class="h3 text-center">
          Visits per device for the last week
        </div>
        <DoughnutChart :data="deviceVisits" />
      </div>
    </div>
  </div>
</template>

<script>
import BarChart from '../components/chart/BarChart'
import DoughnutChart from '../components/chart/DoughnutChart'

export default {
  components: {
    BarChart,
    DoughnutChart
  },
  data () {
    return {
      timeVisits: [],
      deviceVisits: []
    }
  },
  async mounted () {
    this.timeVisits = await this.loadVisitsPerDayOfWeek()
    this.deviceVisits = await this.loadDeviceVisitsForTheLastWeek()
  },
  methods: {
    async loadVisitsPerDayOfWeek () {
      const data = await this.$axios.$get('/statistics/time/visits/week/1')
      return data.map(item => ({
        label: item.time,
        value: item.visits
      }))
    },
    async loadDeviceVisitsForTheLastWeek () {
      const data = await this.$axios.$get('/statistics/device/visits/week/1')
      return data.map(item => ({
        label: item.device,
        value: item.visits
      }))
    }
  }
}
</script>

<style>
</style>
