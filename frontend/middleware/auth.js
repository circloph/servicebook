export default defineNuxtRouteMiddleware((to) => {
  const isAuthenticated = localStorage.getItem('token')
  if (!isAuthenticated) {
    return navigateTo('/login')
  }
})

