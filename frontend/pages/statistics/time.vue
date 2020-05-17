<template>
  <div class="container">
    <div class="h3 text-center">
      Visits per day
    </div>
    <b-form-select v-model="duration" class="duration-select" @change="loadVisits">
      <b-form-select-option value="week">
        Week
      </b-form-select-option>
      <b-form-select-option value="month">
        Month
      </b-form-select-option>
    </b-form-select>
    <BarChart :data="timeVisits" />
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
import BarChart from '../../components/chart/BarChart'

export default {
  name: 'Time',
  components: {
    BarChart
  },
  data () {
    return {
      duration: 'week'
    }
  },
  computed: {
    ...mapState('statistics', ['timeVisits'])
  },
  mounted () {
    this.loadVisits()
  },
  methods: {
    ...mapActions('statistics', ['loadVisitsPerDayOfWeek', 'loadVisitsForTheLastMonth']),
    loadVisits () {
      const params = {
        siteId: this.$route.query.siteId
      }
      if (this.duration === 'week') {
        this.loadVisitsPerDayOfWeek(params)
      } else {
        this.loadVisitsForTheLastMonth(params)
      }
    }
  }
}
</script>

<style scoped>

</style>
