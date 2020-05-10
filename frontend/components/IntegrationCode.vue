<template>
  <div>
    <div class="h4">
      Copy these lines into header of your website:
    </div>
    <pre v-highlightjs="code">
      <code class="html" />
    </pre>
  </div>
</template>

<script>
export default {
  name: 'IntegrationCode',
  props: {
    apiKey: {
      type: String,
      default: null
    }
  },
  computed: {
    code () {
      return `
  &lt;script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js" /&gt;
  &lt;script src="http://localhost:8787/js/visit.js" /&gt;
  &lt;script&gt;
      let apiKey = '${this.apiKey}';
      visit(apiKey)
  &lt;/script&gt;
      `.split('&lt;').join('<')
        .split('&gt;').join('>')
    }
  }
}
</script>

<style scoped>

</style>
