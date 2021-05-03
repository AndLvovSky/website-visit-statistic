<template>
  <div class="container">
    <div class="h2 text-center">
      Current site
    </div>
    <b-row class="mt-2">
      <b-col cols="2">
        <div class="h5">
          Name:
        </div>
      </b-col>
      <b-col>
        <b-form-input v-if="modified" id="name" v-model="localSite.name" type="text" />
        <div v-if="!modified" class="h3">
          {{ site.name }}
        </div>
      </b-col>
    </b-row>
    <b-row class="mt-2">
      <b-col cols="2">
        <div class="h5">
          Link:
        </div>
      </b-col>
      <b-col>
        <b-form-input v-if="modified" id="link" v-model="localSite.link" type="text" />
        <a v-if="!modified" :href="site.link" class="h3">
          {{ site.link }}
        </a>
      </b-col>
    </b-row>
    <div class="d-flex justify-content-end mt-3">
      <b-button
        v-if="!modified"
        variant="primary"
        @click="modify"
      >
        Modify
      </b-button>
      <b-button
        v-if="modified"
        class="ml-2"
        variant="primary"
        @click="save"
      >
        Save
      </b-button>
      <b-button
        v-if="modified"
        class="ml-2"
        variant="primary"
        @click="cancel"
      >
        Cancel
      </b-button>
    </div>
    <div class="h4">
      Copy these lines into header of your website:
    </div>
    <IntegrationCode :api-key="site.apiKey" class="mt-2" />
    <b-button
      v-b-modal.delete-confirmation
      size="sm"
      class="float-right"
      variant="danger"
      @click="cancel"
    >
      Delete
    </b-button>
    <b-modal
      id="delete-confirmation"
      ok-variant="danger"
      cancel-variant="primary"
      size="lg"
      @ok="remove"
    >
      <div class="h4">
        Do you really want to delete site <span class="site-to-delete-name">{{ site.name }}</span> ?
      </div>
    </b-modal>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
import IntegrationCode from '../components/IntegrationCode'

export default {
  components: { IntegrationCode },
  data () {
    return {
      modified: false,
      localSite: {
        name: null,
        link: null
      }
    }
  },
  computed: {
    ...mapState(['site'])
  },
  mounted () {
    this.loadSite({
      id: this.$route.query.siteId
    })
  },
  methods: {
    ...mapActions(['loadSite', 'updateSite', 'deleteSite']),
    modify () {
      Object.assign(this.localSite, this.site)
      this.modified = true
    },
    save () {
      this.updateSite({
        site: this.localSite
      }).finally(() => {
        this.modified = false
      })
    },
    cancel () {
      this.modified = false
    },
    remove () {
      this.deleteSite({
        siteId: this.site.id
      }).then(() => {
        this.$router.push('/')
      })
    }
  }
}
</script>

<style>
  .site-to-delete-name {
    font-style: italic;
    color: darkred;
  }
</style>
