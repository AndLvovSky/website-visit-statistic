export default {
  state: {
    timeVisits: [],
    deviceVisits: []
  },
  mutations: {
    setTimeVisits: (state, timeVisits) => (state.timeVisits = timeVisits),
    setDeviceVisits: (state, deviceVisits) => (state.deviceVisits = deviceVisits)
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
    async loadDeviceVisitsForTheLastWeek ({ commit }, { siteId }) {
      const data = await this.$axios.$get(`/statistics/device/visits/week/${siteId}`)
      const deviceVisits = data.map(item => ({
        label: item.device,
        value: item.visits
      }))
      commit('setDeviceVisits', deviceVisits)
    }
  }
}
