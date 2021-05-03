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
        <b-form-select-option value="custom">
          Custom
        </b-form-select-option>
      </b-form-select>
    </div>
    <div v-if="duration === 'custom'">
      <b-form-datepicker
        v-model="fromDate"
        class="mt-2"
        placeholder="fromDate"
        @input="loadVisits"
      />
      <b-form-datepicker
        v-model="toDate"
        class="mt-2 mb-4"
        placeholder="toDate"
        @input="loadVisits"
      />
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
      duration: 'week',
      fromDate: null,
      toDate: null
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
    ...mapActions('statistics', ['loadDeviceVisitsForTheLastWeek', 'loadDeviceVisitsForTheLastMonth', 'loadDeviceVisits']),
    loadVisits () {
      const params = {
        siteId: this.$route.query.siteId,
        fromDate: this.fromDate,
        toDate: this.toDate
      }
      if (this.duration === 'week') {
        this.loadDeviceVisitsForTheLastWeek(params)
      } else if (this.duration === 'month') {
        this.loadDeviceVisitsForTheLastMonth(params)
      } else {
        if (this.fromDate == null || this.toDate == null) {
          return
        }
        this.loadDeviceVisits(params)
      }
    }
  }
}
</script>

<style scoped>

</style>
