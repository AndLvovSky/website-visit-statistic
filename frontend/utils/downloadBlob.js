export default (axios, url, fileName) => {
  axios.$get(url, {
    responseType: 'blob'
  })
    .then((response) => {
      const url = window.URL.createObjectURL(response)
      const link = document.createElement('a')
      link.href = url
      link.setAttribute('download', fileName)
      document.body.appendChild(link)
      link.click()
    })
    .catch(console.error)
}
