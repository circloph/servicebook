import { defineNuxtConfig } from 'nuxt';

// https://v3.nuxtjs.org/api/configuration/nuxt.config
export default defineNuxtConfig({
  ssr: false,
  target: "static",
    css: ['vuetify/lib/styles/main.sass'],
    build: {
        transpile: [
        'vuetify'
    ],
        
    },
    vite: {
        define: {
            'process.env.DEBUG': 'true',
        }
    },
    modules: ['nuxt-graphql-client'],
    runtimeConfig: {
        public: {
          GQL_HOST: 'http://localhost:8080/graphql' // overwritten by process.env.GQL_HOST
        }
    }
    // runtimeConfig: {
    //     public: {
    //       GQL_HOST: 'https://api.spacex.land/graphql',
    //     }
    //   }
})