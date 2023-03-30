<template>
  <NuxtLayout name="authenticated">
    <lt></lt>
    <div id="app">REGISTRATION</div>

    <v-form v-model="valid">
      <v-container>
        <v-row>
          <v-col cols="12" md="4">
            <v-text-field
              v-model="firstname"
              :rules="nameRules"
              :counter="10"
              label="First name"
              required
            ></v-text-field>
          </v-col>

          <v-col cols="12" md="4">
            <v-text-field
              v-model="middlename"
              :rules="nameRules"
              :counter="10"
              label="Middle name"
              required
            ></v-text-field>
          </v-col>

          <v-col cols="12" md="4">
            <v-text-field
              v-model="lastname"
              :rules="nameRules"
              :counter="10"
              label="Last name"
              required
            ></v-text-field>
          </v-col>

          <v-col cols="12" md="4">
            <v-text-field v-model="login" label="Login" required></v-text-field>
          </v-col>

          <v-col cols="12" md="4">
            <v-text-field
              v-model="password"
              :rules="emailRules"
              label="Password"
              required
            ></v-text-field>
          </v-col>

          <v-col cols="12" md="4">
            <v-text-field
              v-model="numberPhone"
              :rules="emailRules"
              label="Number phone"
              required
            ></v-text-field>
          </v-col>
        </v-row>
      </v-container>
    </v-form>

    <v-container>
      <v-btn block elevation="2" raised rounded @click="register">button</v-btn>
    </v-container>

    <div v-if="pending">loadiing</div>
    <div v-else>
      {{ validation }}
    </div>
  </NuxtLayout>
</template>



<script setup>
const firstname = ref("");
const middlename = ref("");
const lastname = ref("");
const login = ref("");
const password = ref("");
const numberPhone = ref("");

const errorOfValidation = ref("");
const validation = ref('')
const dataa = ref('')

async function register() {
  console.log("in register");
  useGqlHeaders({ Accept: "application/json" });
  const { data, error, refresh, pending } = await useAsyncData(
    "mutations",
    () =>
      GqlRegisterClient({
        firstName: firstname.value,
        middleName: middlename.value,
        lastName: lastname.value,
        login: login.value,
        password: password.value,
        numberPhone: numberPhone.value,
      })
  );
  refresh();
    validation.value = computed(() => error.value?.response?.errors[0].message);
    console.log(validation.value)

      watch(data, (newValue) => {
      console.log(newValue.registerClient.userId);
      navigateTo('/login')
  });
}
</script>

        <style>
html,
body,
#app {
  margin: 0;
}

#app {
  font-family: "Times New Roman", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #198d98;

  display: flex;
  flex-direction: column;
}
.wrapper {
  flex: 1 0 auto;
}
footer {
  background: rgb(204, 0, 0);
  padding: 30px;
}
</style>