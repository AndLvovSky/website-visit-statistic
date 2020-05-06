import Chart from 'chart.js'

export default {
  createTimeVisitsChart: (canvas, timeVisits) => (new Chart(canvas, {
    type: 'bar',
    data: {
      labels: timeVisits.map(item => item.time),
      datasets: [{
        label: '# of Visits',
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
  })),
  createDeviceVisitsChart: (canvas, deviceVisits) => (new Chart(canvas, {
    type: 'doughnut',
    data: {
      labels: deviceVisits.map(item => item.device),
      datasets: [{
        data: deviceVisits.map(item => item.visits),
        backgroundColor: [
          'rgba(255, 99, 71)',
          'rgba(46, 112, 136)'
        ]
      }]
    }
  }))
}
