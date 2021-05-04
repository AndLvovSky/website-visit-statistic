<template>
  <div class="container">
    <div class="h3 text-center">
      Visits per browser
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
        placeholder="From"
        @input="loadVisits"
      />
      <b-form-datepicker
        v-model="toDate"
        class="mt-2 mb-4"
        placeholder="To"
        @input="loadVisits"
      />
    </div>
    <b-alert v-if="!dataExists" show variant="warning" class="mt-4">
      There is no data yet
    </b-alert>
    <DoughnutChart :class="{'hidden' : !dataExists}" :data="browserVisits" />
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
import DoughnutChart from '../../components/chart/DoughnutChart'

export default {
  name: 'Browser',
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
    ...mapState('statistics', ['browserVisits']),
    dataExists () {
      return this.browserVisits.length
    }
  },
  mounted () {
    this.loadVisits()
  },
  methods: {
    ...mapActions('statistics', ['loadBrowserVisitsForTheLastWeek', 'loadBrowserVisitsForTheLastMonth', 'loadBrowserVisits']),
    loadVisits () {
      const params = {
        siteId: this.$route.query.siteId,
        fromDate: this.fromDate,
        toDate: this.toDate
      }
      if (this.duration === 'week') {
        this.loadBrowserVisitsForTheLastWeek(params)
      } else if (this.duration === 'month') {
        this.loadBrowserVisitsForTheLastMonth(params)
      } else {
        if (this.fromDate == null || this.toDate == null) {
          return
        }
        this.loadBrowserVisits(params)
      }
    }
  }
}
</script>

<style scoped>

</style>
