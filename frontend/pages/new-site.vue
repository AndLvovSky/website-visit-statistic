<template>
  <b-container>
    <div class="h3 text-center">
      New Site
    </div>
    <b-row>
      <b-col cols="2">
        <div class="h5">
          Name:
        </div>
      </b-col>
      <b-col>
        <b-form-input id="name" v-model="site.name" type="text" />
      </b-col>
    </b-row>
    <b-row class="mt-2">
      <b-col cols="2">
        <div class="h5">
          Link:
        </div>
      </b-col>
      <b-col>
        <b-form-input id="link" v-model="site.link" type="text" />
      </b-col>
    </b-row>
    <b-button
      class="mt-3 float-right"
      variant="primary"
      @click="tryCreateSite"
    >
      Create
    </b-button>
  </b-container>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  data () {
    return {
      site: {
        id: null,
        link: null,
        name: null
      }
    }
  },
  methods: {
    ...mapActions(['createSite']),
    tryCreateSite () {
      this.createSite({ site: this.site })
        .then((response) => {
          this.$router.push(`/current-site?siteId=${response.id}`)
        })
    }
  }
}
</script>

<style scoped>

</style>
