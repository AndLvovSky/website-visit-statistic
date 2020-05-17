<template>
  <div class="container">
    <div class="h3 text-center">
      Visits per day
    </div>
    <div class="d-flex">
      <b-form-select v-model="duration" class="duration-select" @change="loadVisits">
        <b-form-select-option value="week">
          Week
        </b-form-select-option>
        <b-form-select-option value="month">
          Month
        </b-form-select-option>
        <b-form-select-option value="day">
          Day
        </b-form-select-option>
      </b-form-select>
    </div>
    <b-alert v-if="!dataExists" show variant="warning" class="mt-4">
      There is no data yet
    </b-alert>
    <BarChart :class="{'hidden' : !dataExists}" :data="timeVisits" />
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
    ...mapState('statistics', ['timeVisits']),
    dataExists () {
      return !this.timeVisits.every(item => item.value === 0)
    }
  },
  mounted () {
    this.loadVisits()
  },
  methods: {
    ...mapActions('statistics', ['loadVisitsPerDayOfWeek', 'loadVisitsForTheLastMonth', 'loadVisitsPerHour']),
    loadVisits () {
      const params = {
        siteId: this.$route.query.siteId
      }
      if (this.duration === 'week') {
        this.loadVisitsPerDayOfWeek(params)
      } else if (this.duration === 'month') {
        this.loadVisitsForTheLastMonth(params)
      } else {
        this.loadVisitsPerHour(params)
      }
    }
  }
}
</script>

<style scoped>

</style>
