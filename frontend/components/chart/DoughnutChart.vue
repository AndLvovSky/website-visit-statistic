<script>
import { Doughnut } from 'vue-chartjs'

export default {
  name: 'DoughnutChart',
  extends: Doughnut,
  props: {
    data: {
      type: Array,
      default: () => []
    }
  },
  data () {
    return {
      chartOptions: {
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
          data: this.data.map(item => item.value),
          backgroundColor: [
            'rgba(255, 99, 71)',
            'rgba(46, 112, 136)',
            'rgba(46, 200, 136)',
            'rgba(200, 244, 23)'
          ] // TODO: Support more colors
        }]
      }
    }
  }
}
</script>
