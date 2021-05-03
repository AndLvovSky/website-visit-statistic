export default {
  state: {
    timeVisits: [],
    deviceVisits: [],
    countryVisits: [],
    referralWebsites: [],
    websiteVersions: [],
    routeVisits: []
  },
  mutations: {
    setTimeVisits: (state, timeVisits) => (state.timeVisits = timeVisits),
    setDeviceVisits: (state, deviceVisits) => (state.deviceVisits = deviceVisits),
    setCountryVisits: (state, countryVisits) => (state.countryVisits = countryVisits),
    setReferralWebsites: (state, referralWebsites) => (state.referralWebsites = referralWebsites),
    setWebsiteVersions: (state, websiteVersions) => (state.websiteVersions = websiteVersions),
    setRouteVisits: (state, routeVisits) => (state.routeVisits = routeVisits)
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
    }
  }
}
