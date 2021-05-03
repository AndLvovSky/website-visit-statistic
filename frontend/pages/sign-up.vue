<template>
  <div class="container">
    <b-form class="mt-2" @submit.prevent="login">
      <b-form-group label-for="username" label="Enter username:">
        <b-form-input id="username" v-model="newUser.username" type="text" />
      </b-form-group>
      <b-form-group label-for="email" label="Enter email:">
        <b-form-input id="email" v-model="newUser.email" type="email" />
      </b-form-group>
      <b-form-group label-for="fullName" label="Enter full name:">
        <b-form-input id="fullName" v-model="newUser.fullName" type="text" />
      </b-form-group>
      <b-form-group label-for="password" label="Enter password:">
        <b-form-input id="password" v-model="newUser.password" type="password" />
      </b-form-group>
      <b-alert variant="danger" :show="hasError">
        Sign up error occurred
      </b-alert>
      <b-button type="submit" variant="primary">
        Sign up
      </b-button>
    </b-form>
  </div>
</template>

<script>
export default {
  name: 'SignUp',
  data () {
    return {
      newUser: {
        username: '',
        email: '',
        fullName: '',
        password: ''
      },
      hasError: false
    }
  },
  methods: {
    login () {
      this.$axios.post('/users/register', this.newUser)
        .then(() => (this.$router.push('/login')))
        .catch(() => (this.hasError = true))
    }
  }
}
</script>

<style scoped>
</style>
