import downloadBlob from '~/utils/downloadBlob'

export default {
  state: {
    timeVisits: [],
    deviceVisits: [],
    countryVisits: [],
    referralWebsites: [],
    websiteVersions: [],
    routeVisits: [],
    visitSummary: {}
  },
  mutations: {
    setTimeVisits: (state, timeVisits) => (state.timeVisits = timeVisits),
    setDeviceVisits: (state, deviceVisits) => (state.deviceVisits = deviceVisits),
    setCountryVisits: (state, countryVisits) => (state.countryVisits = countryVisits),
    setReferralWebsites: (state, referralWebsites) => (state.referralWebsites = referralWebsites),
    setWebsiteVersions: (state, websiteVersions) => (state.websiteVersions = websiteVersions),
    setRouteVisits: (state, routeVisits) => (state.routeVisits = routeVisits),
    setVisitSummary: (state, visitSummary) => (state.visitSummary = visitSummary)
  },
  actions: {
    async loadVisitsPerDayOfWeek ({ commit }, { siteId }) {
      const data = await this.$axios.$get(`/statistics/time/visits/week/${siteId}`)
      const timeVisits = data.map(item => ({
        label: item.time,
        value: item.visits
      }))
      commit('setTimeVisits', timeVisits)
    },
    async loadVisitsForTheLastMonth ({ commit }, { siteId }) {
      const data = await this.$axios.$get(`/statistics/time/visits/month/${siteId}`)
      const timeVisits = data.map(item => ({
        label: item.time,
        value: item.visits
      }))
      commit('setTimeVisits', timeVisits)
    },
    async loadVisitsPerHour ({ commit }, { siteId }) {
      const data = await this.$axios.$get(`/statistics/time/visits/day/${siteId}`)
      const timeVisits = data.map(item => ({
        label: item.time,
        value: item.visits
      }))
      commit('setTimeVisits', timeVisits)
    },
    async loadDeviceVisitsForTheLastWeek ({ commit }, { siteId }) {
      const data = await this.$axios.$get(`/statistics/device/visits/week/${siteId}`)
      const deviceVisits = data.map(item => ({
        label: item.device,
        value: item.visits
      }))
      commit('setDeviceVisits', deviceVisits)
    },
    async loadDeviceVisitsForTheLastMonth ({ commit }, { siteId }) {
      const data = await this.$axios.$get(`/statistics/device/visits/month/${siteId}`)
      const deviceVisits = data.map(item => ({
        label: item.device,
        value: item.visits
      }))
      commit('setDeviceVisits', deviceVisits)
    },
    async loadDeviceVisits ({ commit }, { siteId, fromDate, toDate }) {
      const data = await this.$axios.$get(`/statistics/device/visits/custom/${siteId}?fromDate=${fromDate}&toDate=${toDate}`)
      const deviceVisits = data.map(item => ({
        label: item.device,
        value: item.visits
      }))
      commit('setDeviceVisits', deviceVisits)
    },
    async loadCountryVisitsForTheLastWeek ({ commit }, { siteId }) {
      const data = await this.$axios.$get(`/statistics/country/visits/week/${siteId}`)
      const countryVisits = data.map(item => ({
        label: item.country,
        value: item.visits
      }))
      commit('setCountryVisits', countryVisits)
    },
    async loadCountryVisitsForTheLastMonth ({ commit }, { siteId }) {
      const data = await this.$axios.$get(`/statistics/country/visits/month/${siteId}`)
      const countryVisits = data.map(item => ({
        label: item.country,
        value: item.visits
      }))
      commit('setCountryVisits', countryVisits)
    },
    async loadCountryVisits ({ commit }, { siteId, fromDate, toDate }) {
      const data = await this.$axios.$get(`/statistics/country/visits/custom/${siteId}?fromDate=${fromDate}&toDate=${toDate}`)
      const countryVisits = data.map(item => ({
        label: item.country,
        value: item.visits
      }))
      commit('setCountryVisits', countryVisits)
    },
    async loadReferralWebsitesForTheLastWeek ({ commit }, { siteId }) {
      const data = await this.$axios.$get(`/statistics/referral-websites/visits/week/${siteId}`)
      const referralWebsites = data.map(item => ({
        label: item.website,
        value: item.visits
      }))
      commit('setReferralWebsites', referralWebsites)
    },
    async loadReferralWebsitesForTheLastMonth ({ commit }, { siteId }) {
      const data = await this.$axios.$get(`/statistics/referral-websites/visits/month/${siteId}`)
      const referralWebsites = data.map(item => ({
        label: item.website,
        value: item.visits
      }))
      commit('setReferralWebsites', referralWebsites)
    },
    async loadReferralWebsites ({ commit }, { siteId, fromDate, toDate }) {
      const data = await this.$axios.$get(`/statistics/referral-websites/visits/month/${siteId}?fromDate=${fromDate}&toDate=${toDate}`)
      const referralWebsites = data.map(item => ({
        label: item.website,
        value: item.visits
      }))
      commit('setReferralWebsites', referralWebsites)
    },
    async loadWebsiteVersionsForTheLastWeek ({ commit }, { siteId }) {
      const data = await this.$axios.$get(`/statistics/a-b/visits/week/${siteId}`)
      const websiteVersions = data.map(item => ({
        label: item.version,
        value: item.visits
      }))
      commit('setWebsiteVersions', websiteVersions)
    },
    async loadWebsiteVersionsForTheLastMonth ({ commit }, { siteId }) {
      const data = await this.$axios.$get(`/statistics/a-b/visits/month/${siteId}`)
      const websiteVersions = data.map(item => ({
        label: item.version,
        value: item.visits
      }))
      commit('setWebsiteVersions', websiteVersions)
    },
    async loadWebsiteVersions ({ commit }, { siteId, fromDate, toDate }) {
      const data = await this.$axios.$get(`/statistics/a-b/visits/custom/${siteId}?fromDate=${fromDate}&toDate=${toDate}`)
      const websiteVersions = data.map(item => ({
        label: item.version,
        value: item.visits
      }))
      commit('setWebsiteVersions', websiteVersions)
    },
    async loadRouteVisitsForTheLastWeek ({ commit }, { siteId }) {
      const data = await this.$axios.$get(`/statistics/route/visits/week/${siteId}`)
      const routeVisits = data.map(item => ({
        label: item.path,
        value: item.visits
      }))
      commit('setRouteVisits', routeVisits)
    },
    async loadRouteVisitsForTheLastMonth ({ commit }, { siteId }) {
      const data = await this.$axios.$get(`/statistics/route/visits/month/${siteId}`)
      const routeVisits = data.map(item => ({
        label: item.path,
        value: item.visits
      }))
      commit('setRouteVisits', routeVisits)
    },
    async loadRouteVisits ({ commit }, { siteId, fromDate, toDate }) {
      const data = await this.$axios.$get(`/statistics/route/visits/custom/${siteId}?fromDate=${fromDate}&toDate=${toDate}`)
      const routeVisits = data.map(item => ({
        label: item.path,
        value: item.visits
      }))
      commit('setRouteVisits', routeVisits)
    },
    async loadVisitSummaryForTheLastWeek ({ commit }, { siteId }) {
      const data = await this.$axios.$get(`/statistics/summary/visits/week/${siteId}`)
      commit('setVisitSummary', data)
    },
    async loadVisitSummaryForTheLastMonth ({ commit }, { siteId }) {
      const data = await this.$axios.$get(`/statistics/summary/visits/month/${siteId}`)
      commit('setVisitSummary', data)
    },
    async loadVisitSummary ({ commit }, { siteId, fromDate, toDate }) {
      const data = await this.$axios.$get(`/statistics/summary/visits/custom/${siteId}?fromDate=${fromDate}&toDate=${toDate}`)
      commit('setVisitSummary', data)
    },
    exportVisitSummaryForTheLastWeek ({ commit }, { siteId }) {
      const url = `/statistics/summary/visits/week/${siteId}/export`
      const fileName = 'visit_summary.xlsx'
      downloadBlob(this.$axios, url, fileName)
    },
    exportVisitSummaryForTheLastMonth ({ commit }, { siteId }) {
      const url = `/statistics/summary/visits/month/${siteId}/export`
      const fileName = 'visit_summary.xlsx'
      downloadBlob(this.$axios, url, fileName)
    },
    exportVisitSummary ({ commit }, { siteId, fromDate, toDate }) {
      const url = `/statistics/summary/visits/custom/${siteId}/export?fromDate=${fromDate}&toDate=${toDate}`
      const fileName = 'visit_summary.xlsx'
      downloadBlob(this.$axios, url, fileName)
    }
  }
}
