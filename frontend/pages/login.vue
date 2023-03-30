<template>
  <NuxtLayout name="authenticated">
    <div class="d-flex align-center flex-column">
      <v-card width="400">
        <v-card-header>
          <v-card-header-text>
            <v-card-title>Login</v-card-title>
          </v-card-header-text>
        </v-card-header>

        <v-card-text>
          <v-form ref="form" v-model="valid" lazy-validation>
            <v-text-field
              v-model="username"
              label="Username"
              :rules="nameRules"
              required
            ></v-text-field>

            <v-text-field
              v-model="password"
              :append-icon="show1 ? mdiEyeOff : mdiEye"
              :type="show1 ? 'text' : 'password'"
              name="input-10-1"
              label="Normal with hint text"
              @click:append="show1 = !show1"
            >
            </v-text-field>

            <v-btn color="success" class="mr-4" @click="login"> Login </v-btn>
          </v-form>
        </v-card-text>
      </v-card>
    </div>
  </NuxtLayout>
</template>

<script>
import { mdiEye, mdiEyeOff } from "@mdi/js";
import { aliases, mdi } from "vuetify/lib/iconsets/mdi-svg";

export default {
  data: () => ({
    mdiEye,
    mdiEyeOff,
    valid: false,
    username: "",
    show1: false,
    show2: true,
    show3: false,
    show4: false,
    password: "",
    nameRules: [
      (v) => !!v || "Name is required",
      (v) => v.length <= 10 || "Name must be less than 10 characters",
    ],

    rules: {
      required: (value) => !!value || "Required.",
      min: (v) => v.length >= 8 || "Min 8 characters",
      emailMatch: () => `The email and password you entered don't match`,
    },
  }),
  methods: {
    async login() {
      console.log(this.username);
      useGqlHeaders({ Accept: "application/json" });
      console.log("here");
      const { data } = await useLazyAsyncData("mutations", () =>
        GqlLogin({ login: this.username, password: this.password })
      );
      localStorage.setItem("token", data.value.login.token);
      localStorage.setItem("role", data.value.login.role);
      localStorage.setItem("username", this.username);
      localStorage.setItem("id", data.value.login.userId);
      navigateTo("/");
    },
  },
};
</script>

