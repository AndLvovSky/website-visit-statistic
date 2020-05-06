<script>
import { Bar } from 'vue-chartjs'

export default {
  name: 'BarChart',
  extends: Bar,
  props: {
    data: {
      type: Array,
      default: () => []
    }
  },
  data () {
    return {
      chartOptions: {
        scales: {
          yAxes: [{
            type: 'linear',
            ticks: {
              beginAtZero: true,
              stepSize: 1
            }
          }]
        },
        aspectRatio: 3,
        maintainAspectRatio: false
      }
    }
  },
  watch: {
    data () {
      this.renderChart(this.getChartData(), this.chartOptions)
    }
  },
  methods: {
    getChartData () {
      return {
        labels: this.data.map(item => item.label),
        datasets: [{
          label: '# of Visits',
          data: this.data.map(item => item.value),
          backgroundColor: 'rgba(54, 162, 235, 0.2)',
          borderColor: 'rgba(54, 162, 235, 1)',
          borderWidth: 1
        }]
      }
    }
  }
}
</script>
