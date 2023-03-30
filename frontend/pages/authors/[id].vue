<template>
  <NuxtLayout name="authenticated">
    <div v-if="pending">loading</div>
    <div v-else>
      <div class="d-flex justify-center">
        <v-card width="700px">
          <v-card-title
            class="text-xs-h6 text-md-h5 text-lg-h4 font-weight-black"
            >Author</v-card-title
          >
          <v-img height="300" src="../assets/author.png"></v-img>
          <v-card-text style="color: black">
            <div class="font-weight-bold">First name</div>
            {{ data.getInfoAboutAuthor.firstName }} <br />
            <div class="font-weight-bold">Middle name</div>
            {{ data.getInfoAboutAuthor.middleName }}<br />
            <div class="font-weight-bold">Last name</div>
            {{ data.getInfoAboutAuthor.lastName }}<br />
            <div class="font-weight-bold">Short biography</div>
            {{ data.getInfoAboutAuthor.shortBiography }}<br />
          </v-card-text>
          </v-card>
      <button v-if="role.includes('ROLE_ADMIN')" v-on:click="show = !show">
        Change data
      </button>
      <transition name="fade">
        <v-form v-if="show" v-model="valid">
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
                <v-text-field
                  v-model="shortBiography"
                  label="Short biography"
                  required
                ></v-text-field>
              </v-col>
            </v-row>
          </v-container>
          <v-btn block elevation="2" @click="change">Change </v-btn>
        </v-form>
      </transition>
    </div>
    </div>
  </NuxtLayout>
</template>

<script setup>
const show = ref(false);
const firstname = ref("");
const middlename = ref("");
const lastname = ref("");
const shortBiography = ref("");
const route = useRoute();
console.log("here");
useGqlToken(localStorage.getItem("token"), {
  config: {
    type: "Bearer_",
    name: "Authorization",
  },
});
useGqlHeaders({ Accept: "application/json", "Cache-Control": "no-cache" });
console.log(parseInt(route.params.id));
const { pending, data, refresh } = await useAsyncData("queries", () =>
  GqlGetInfoAboutAuthor({ authorId: parseInt(route.params.id) })
);
refresh();

async function change() {
  console.log("change method");
  useGqlToken(localStorage.getItem("token"), {
    config: {
      type: "Bearer_",
      name: "Authorization",
    },
  });
  useGqlHeaders({ Accept: "application/json", "Cache-Control": "no-cache" });
  const {
    pending,
    data,
    refresh: updateRefresh,
  } = await useAsyncData("mutations", () =>
    GqlUpdateAuthor({
      authorId: parseInt(route.params.id),
      firstName: firstname.value,
      middleName: middlename.value,
      lastName: lastname.value,
      shortBiography: shortBiography.value,
    })
  );
  updateRefresh();
  refresh();
}

const role = computed(() => localStorage.getItem("role"));
</script>