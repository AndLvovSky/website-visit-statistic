export default {
  state: {
    timeVisits: [],
    deviceVisits: [],
    countryVisits: []
  },
  mutations: {
    setTimeVisits: (state, timeVisits) => (state.timeVisits = timeVisits),
    setDeviceVisits: (state, deviceVisits) => (state.deviceVisits = deviceVisits),
    setCountryVisits: (state, countryVisits) => (state.countryVisits = countryVisits)
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
    }
  }
}
