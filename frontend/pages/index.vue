<template>
  <div class="container-fluid">
    <canvas id="testChart" />
  </div>
</template>

<script>
import Chart from '../chart/custom-charts.js'

export default {
  async mounted () {
    const timeVisits = await this.loadVisitsPerDayOfWeek()
    const canvas = document.getElementById('testChart').getContext('2d')
    Chart.createTimeVisitsChart(canvas, timeVisits)
  },
  methods: {
    loadVisitsPerDayOfWeek () {
      return this.$axios.$get('/statistics/time/visits-per-day-of-week/1')
    }
  }
}
</script>

<style>
</style>
