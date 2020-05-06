<template>
  <div class="container-fluid">
    <div class="h3 text-center">
      Number of visits per day for the last week
    </div>
    <canvas id="barChart" />
    <div class="h3 mt-5 text-center">
      Number of visits per device for the last week
    </div>
    <canvas id="doughnutChart" />
  </div>
</template>

<script>
import Chart from '../chart/custom-charts.js'

export default {
  async mounted () {
    const timeVisits = await this.loadVisitsPerDayOfWeek()
    const barCanvas = document.getElementById('barChart').getContext('2d')
    Chart.createTimeVisitsChart(barCanvas, timeVisits)

    const deviceVisits = await this.loadDeviceVisitsForTheLastWeek()
    const doughnutCanvas = document.getElementById('doughnutChart').getContext('2d')
    Chart.createDeviceVisitsChart(doughnutCanvas, deviceVisits)
  },
  methods: {
    loadVisitsPerDayOfWeek () {
      return this.$axios.$get('/statistics/time/visits/week/1')
    },
    loadDeviceVisitsForTheLastWeek () {
      return this.$axios.$get('/statistics/device/visits/week/1')
    }
  }
}
</script>

<style>
</style>
