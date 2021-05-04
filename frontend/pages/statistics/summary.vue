<template>
  <div class="container">
    <div class="h3 text-center">
      Visits summary per day
    </div>
    <div class="d-flex">
      <b-button type="submit" variant="info" @click="exportSummary">
        Export
      </b-button>
      <b-form-select v-model="duration" class="duration-select" @change="loadSummary">
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
        @input="loadSummary"
      />
      <b-form-datepicker
        v-model="toDate"
        class="mt-2 mb-4"
        placeholder="To"
        @input="loadSummary"
      />
    </div>
    <b-table
      v-if="summaryItems.length"
      striped
      hover
      :items="summaryItems"
      class="mt-2"
    />
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'

export default {
  name: 'Summary',
  data () {
    return {
      duration: 'week',
      fromDate: null,
      toDate: null
    }
  },
  computed: {
    ...mapState('statistics', ['visitSummary']),
    summaryItems () {
      console.log(this.visitSummary)
      if (this.visitSummary == null) {
        return []
      }
      return [
        { name: 'Total Active Users', value: this.visitSummary.activeUsers },
        { name: 'Average Visits', value: this.visitSummary.averageVisits },
        { name: 'Average Unique Visits', value: this.visitSummary.averageUniqueVisits },
        { name: 'Minimum Visits', value: this.visitSummary.minVisits },
        { name: 'Minimum Unique Visits', value: this.visitSummary.minUniqueVisits },
        { name: 'Maximum Visits', value: this.visitSummary.maxVisits },
        { name: 'Maximum Unique Visits', value: this.visitSummary.maxUniqueVisits },
        { name: 'Visits Standard Deviation', value: this.visitSummary.visitsStandardDeviation },
        { name: 'Unique Visits Standard Deviation', value: this.visitSummary.uniqueVisitsStandardDeviation }
      ]
    }
  },
  mounted () {
    this.loadSummary()
  },
  methods: {
    ...mapActions('statistics', ['loadVisitSummaryForTheLastWeek', 'loadVisitSummaryForTheLastMonth', 'loadVisitSummary',
      'exportVisitSummaryForTheLastWeek', 'exportVisitSummaryForTheLastMonth', 'exportVisitSummary']),
    loadSummary () {
      const params = {
        siteId: this.$route.query.siteId,
        fromDate: this.fromDate,
        toDate: this.toDate
      }
      if (this.duration === 'week') {
        this.loadVisitSummaryForTheLastWeek(params)
      } else if (this.duration === 'month') {
        this.loadVisitSummaryForTheLastMonth(params)
      } else {
        if (this.fromDate == null || this.toDate == null) {
          return
        }
        this.loadVisitSummary(params)
      }
    },
    exportSummary () {
      const params = {
        siteId: this.$route.query.siteId,
        fromDate: this.fromDate,
        toDate: this.toDate
      }
      if (this.duration === 'week') {
        this.exportVisitSummaryForTheLastWeek(params)
      } else if (this.duration === 'month') {
        this.exportVisitSummaryForTheLastMonth(params)
      } else {
        if (this.fromDate == null || this.toDate == null) {
          return
        }
        this.exportVisitSummary(params)
      }
    }
  }
}
</script>

<style scoped>

</style>
