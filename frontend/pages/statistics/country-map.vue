<template>
  <div class="container">
    <div class="h3 text-center">
      Visits per country
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
    <MapChart v-if="dataExists" :country-data="countryVisitsMap" />
    <b-alert v-else show variant="warning" class="mt-4">
      There is no data yet
    </b-alert>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
import MapChart from 'vue-map-chart'

export default {
  components: {
    MapChart
  },
  data () {
    return {
      duration: 'week',
      fromDate: null,
      toDate: null
    }
  },
  computed: {
    ...mapState('statistics', ['countryVisits']),
    dataExists () {
      return this.countryVisits.length
    },
    countryVisitsMap () {
      const map = {}
      this.countryVisits.forEach((item) => {
        map[item.label] = item.value
      })
      return map
    }
  },
  mounted () {
    this.loadVisits()
  },
  methods: {
    ...mapActions('statistics', ['loadCountryVisitsForTheLastWeek', 'loadCountryVisitsForTheLastMonth', 'loadCountryVisits']),
    loadVisits () {
      const params = {
        siteId: this.$route.query.siteId,
        fromDate: this.fromDate,
        toDate: this.toDate
      }
      if (this.duration === 'week') {
        this.loadCountryVisitsForTheLastWeek(params)
      } else if (this.duration === 'month') {
        this.loadCountryVisitsForTheLastMonth(params)
      } else {
        if (this.fromDate == null || this.toDate == null) {
          return
        }
        this.loadCountryVisits(params)
      }
    }
  }
}
</script>

<style scoped>

</style>
