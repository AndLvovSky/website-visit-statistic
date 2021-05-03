<template>
  <div class="container">
    <div class="h4">
      Website integration
    </div>
    <p>
      Firstly, you should register your website at <b>Site -> New Site</b>.
      To enable statistics gathering go to <b>Site -> Current Site</b>
      and copy the code that looks similar to this into the header of your website.
      It's possible to change current website on page <b>Site -> Sites</b>.
    </p>
    <IntegrationCode api-key="<your_api_key>" />
    <hr/>
    <div class="h4">
      Referral websites
    </div>
    <p>
      You can view websites which are used to get visitors to your website.
      In order for it to work one should append the string <b>?referral=&lt;website&gt;</b>
      to your website URL which is posted on a source website.
    </p>
    <hr/>
    <div class="h4">
      A/B testing
    </div>
    <p>
      Suppose you have a new version of your website and an ability to switch to the old one.
      You can check which percent of users still uses the old version. It can help to make a decision
      whether to stick with the old one or to completely switch to the new interface.
      Specify current version as the second parameter to <b>visit</b> function:
    </p>
    <pre v-highlightjs>
      <code class="js" >
        visit(apiKey, '&lt;version&gt;')
      </code>
    </pre>
  </div>
</template>

<script>
import IntegrationCode from '../components/IntegrationCode'

export default {
  components: {
    IntegrationCode
  }
}
</script>

<style>
</style>
