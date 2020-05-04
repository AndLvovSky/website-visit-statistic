export default function ({ $axios, redirect }) {
  $axios.onError((error) => {
    const code = error.response.status
    if (code === 401) {
      redirect('/login')
    }
  })
}
