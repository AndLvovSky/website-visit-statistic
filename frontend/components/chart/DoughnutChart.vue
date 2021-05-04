<script>
import { Doughnut } from 'vue-chartjs'
import randomColor from 'randomcolor'

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
      const colors = [
        'rgba(255, 99, 71)',
        'rgba(46, 112, 136)',
        'rgba(46, 200, 136)',
        'rgba(200, 244, 23)'
      ]
      const additionalCount = this.data.length - colors.length
      if (additionalCount > 0) {
        for (let i = 0; i < additionalCount; i++) {
          colors.push(randomColor({
            format: 'rgba',
            alpha: 1
          }))
        }
      }
      return {
        labels: this.data.map(item => item.label),
        datasets: [{
          data: this.data.map(item => item.value),
          backgroundColor: colors
        }]
      }
    }
  }
}
</script>
