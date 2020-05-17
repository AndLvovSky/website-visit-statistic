<template>
  <div class="container">
    <div class="h3 text-center">
      Visits per device
    </div>
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
    <DoughnutChart :class="{'hidden' : !dataExists}" :data="deviceVisits" />
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
    ...mapState('statistics', ['deviceVisits']),
    dataExists () {
      return this.deviceVisits.length
    }
  },
  mounted () {
    this.loadVisits()
  },
  methods: {
    ...mapActions('statistics', ['loadDeviceVisitsForTheLastWeek', 'loadDeviceVisitsForTheLastMonth']),
    loadVisits () {
      const params = {
        siteId: this.$route.query.siteId
      }
      if (this.duration === 'week') {
        this.loadDeviceVisitsForTheLastWeek(params)
      } else {
        this.loadDeviceVisitsForTheLastMonth(params)
      }
    }
  }
}
</script>

<style scoped>

</style>
