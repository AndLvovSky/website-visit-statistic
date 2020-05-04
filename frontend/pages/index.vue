<template>
  <div class="container-fluid">
    <canvas id="testChart" />
  </div>
</template>

<script>
import Chart from 'chart.js'

export default {
  mounted () {
    const ctx = document.getElementById('testChart').getContext('2d')
    // eslint-disable-next-line no-unused-vars
    const testChart = new Chart(ctx, {
      type: 'bar',
      data: {
        labels: ['Sun', 'Mon', 'Tue', 'Wen', 'Thu', 'Fri', 'Sat'],
        datasets: [{
          label: '# of Visits per Day',
          data: [12, 19, 3, 5, 2, 3, 7],
          backgroundColor: 'rgba(54, 162, 235, 1)'
        }]
      },
      options: {
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: true
            }
          }]
        },
        aspectRatio: 3
      }
    })
  }
}
</script>

<style>
</style>
