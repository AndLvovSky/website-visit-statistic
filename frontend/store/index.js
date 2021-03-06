export default {
  state: {
    sites: [],
    site: {
      id: null,
      name: null,
      link: null
    }
  },
  mutations: {
    setSites: (state, sites) => (state.sites = sites),
    setSite: (state, site) => (state.site = site)
  },
  actions: {
    async loadSites ({ commit }) {
      const data = await this.$axios.$get('/sites')
      commit('setSites', data)
    },
    async loadSite ({ commit }, { id }) {
      const data = await this.$axios.$get(`/sites/${id}`)
      commit('setSite', data)
    },
    createSite ({ commit }, { site }) {
      return this.$axios.$post('/sites', site)
    },
    async updateSite ({ commit }, { site }) {
      const data = await this.$axios.$put('/sites', site)
      commit('setSite', data)
    },
    deleteSite ({ commit }, { siteId }) {
      return this.$axios.$delete(`/sites/${siteId}`)
    }
  }
}
