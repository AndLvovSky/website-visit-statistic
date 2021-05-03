<template>
  <div class="container">
    <b-form class="mt-2" @submit.prevent="login">
      <b-form-group label-for="username" label="Enter username:">
        <b-form-input id="username" v-model="username" type="text" />
      </b-form-group>
      <b-form-group label-for="password" label="Enter password:">
        <b-form-input id="password" v-model="password" type="password" />
      </b-form-group>
      <b-alert variant="danger" :show="hasError">
        Login error occurred
      </b-alert>
      <b-button type="submit" variant="primary">
        Login
      </b-button>
    </b-form>
    <b-button
      class="float-right"
      variant="success"
      @click="$router.push('/sign-up')"
    >
      Sign up
    </b-button>
  </div>
</template>

<script>
export default {
  name: 'LoginForm',
  data () {
    return {
      username: '',
      password: '',
      hasError: false
    }
  },
  methods: {
    login () {
      this.$axios.post('/login', { }, {
        auth: {
          username: this.username,
          password: this.password
        }
      }).then(() => (this.$router.push('/')))
        .catch(() => (this.hasError = true))
    }
  }
}
</script>

<style scoped>
</style>
