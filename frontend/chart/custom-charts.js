import Chart from 'chart.js'

export default {
  createTimeVisitsChart: (canvas, timeVisits) => (new Chart(canvas, {
    type: 'bar',
    data: {
      labels: timeVisits.map(item => item.time),
      datasets: [{
        label: '# of Visits per Day of Week',
        data: timeVisits.map(item => item.visits),
        backgroundColor: 'rgba(54, 162, 235, 0.2)',
        borderColor: 'rgba(54, 162, 235, 1)',
        borderWidth: 1
      }]
    },
    options: {
      scales: {
        yAxes: [{
          type: 'linear',
          ticks: {
            beginAtZero: true,
            stepSize: 1
          }
        }]
      },
      aspectRatio: 3
    }
  }))
}
