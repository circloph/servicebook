<template>
  <v-app :theme="theme">
    <v-app-bar app>
      <v-btn flat to="/" nuxt> Home </v-btn>
      <v-btn flat to="/books"> Books </v-btn>
      <span v-if="name != null && role.includes('ROLE_ADMIN')">
        <v-btn flat to="/constructor" prefetch nuxt> Constructor </v-btn>
      </span>
        <v-switch hide-details inset v-model="model"></v-switch>
      <span v-if="name != null">
        <v-btn @click="logout" nuxt> Logout </v-btn>
        <v-btn @click="getInfoAboutUser" nuxt> {{ name }} </v-btn>
        <v-avatar color="teal" size="45"></v-avatar>
      </span>
      <span v-else>
        <v-btn flat to="/register" prefetch nuxt> Register </v-btn>
        <v-btn flat to="/login" prefetch nuxt> Login </v-btn>
      </span>
    </v-app-bar>
    <v-main>
      <slot />
    </v-main>
  </v-app>
</template>

<script setup>
const model = useState('model', () => 0)
const role = computed(() => localStorage.getItem('role'))
</script>

<script>

export default {
  data: () => ({
    cards: ['Today', 'Yesterday'],
    drawer: null,
    links: [
      '/',
      'books',
      'register',
      'login',
      'user'
    ],
  }),
  computed: {
    theme() {
      return this.model > 0 ? 'dark' : 'light'
    }
  },
  computed: {
    name() {
      return localStorage.getItem("username");
    },
  },
  methods: {
    async logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('username');
    localStorage.removeItem('id');
    localStorage.clear()
    navigateTo('/')
    window.location.reload();
    },
    async getInfoAboutUser() {
      navigateTo('/users/' + localStorage.getItem('id'))
    }
  },
}
</script>
