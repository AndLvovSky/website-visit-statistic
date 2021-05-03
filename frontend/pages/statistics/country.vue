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
    <b-alert v-if="!dataExists" show variant="warning" class="mt-4">
      There is no data yet
    </b-alert>
    <DoughnutChart :class="{'hidden' : !dataExists}" :data="readableCountryVisits" />
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
import countries from 'i18n-iso-countries'
import DoughnutChart from '../../components/chart/DoughnutChart'

const LOCALE = 'en'
countries.registerLocale(require(`i18n-iso-countries/langs/${LOCALE}.json`))

export default {
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
    ...mapState('statistics', ['countryVisits']),
    dataExists () {
      return this.countryVisits.length
    },
    readableCountryVisits () {
      return this.countryVisits.map(item => ({
        label: countries.getName(item.label, LOCALE),
        value: item.value
      }))
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
