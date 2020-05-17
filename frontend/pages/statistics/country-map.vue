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
      </b-form-select>
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
      duration: 'week'
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
    ...mapActions('statistics', ['loadCountryVisitsForTheLastWeek', 'loadCountryVisitsForTheLastMonth']),
    loadVisits () {
      const params = {
        siteId: this.$route.query.siteId
      }
      if (this.duration === 'week') {
        this.loadCountryVisitsForTheLastWeek(params)
      } else {
        this.loadCountryVisitsForTheLastMonth(params)
      }
    }
  }
}
</script>

<style scoped>

</style>
