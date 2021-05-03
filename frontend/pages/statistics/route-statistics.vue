<template>
  <div class="container">
    <div class="h3 text-center">
      Page statistics
    </div>
    <p>
      Shows which pages are more popular than others
    </p>
    <div class="d-flex">
      <b-form-select v-model="duration" class="duration-select" @change="loadVisits">
        <b-form-select-option value="week">
          Week
        </b-form-select-option>
        <b-form-select-option value="month">
          Month
        </b-form-select-option>
      </b-form-select>
    </div>
    <b-alert v-if="!dataExists" show variant="warning" class="mt-4">
      There is no data yet
    </b-alert>
    <DoughnutChart :class="{'hidden' : !dataExists}" :data="routeVisits" />
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
import DoughnutChart from '../../components/chart/DoughnutChart'

export default {
  name: 'Device',
  components: {
    DoughnutChart
  },
  data () {
    return {
      duration: 'week'
    }
  },
  computed: {
    ...mapState('statistics', ['routeVisits']),
    dataExists () {
      return this.routeVisits.length
    }
  },
  mounted () {
    this.loadVisits()
  },
  methods: {
    ...mapActions('statistics', ['loadRouteVisitsForTheLastWeek', 'loadRouteVisitsForTheLastMonth']),
    loadVisits () {
      const params = {
        siteId: this.$route.query.siteId
      }
      if (this.duration === 'week') {
        this.loadRouteVisitsForTheLastWeek(params)
      } else {
        this.loadRouteVisitsForTheLastMonth(params)
      }
    }
  }
}
</script>

<style scoped>

</style>
