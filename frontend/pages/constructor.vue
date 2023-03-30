<template>
  <NuxtLayout name="authenticated">
    <div v-if="role.includes('ROLE_ADMIN')">
      <div class="forms">
        <v-form v-if="show" v-model="valid">
          <v-container>
            <v-row>
              <v-col cols="4" md="2">
                <v-text-field
                  v-model="title"
                  :rules="nameRules"
                  :counter="10"
                  label="Title"
                  required
                ></v-text-field>
              </v-col>
              <v-col cols="4" md="2">
                <v-text-field
                  v-model="genre"
                  :rules="nameRules"
                  :counter="10"
                  label="Genre"
                  required
                ></v-text-field>
              </v-col>

              <v-col cols="4" md="2">
                <v-text-field
                  v-model="description"
                  :rules="nameRules"
                  :counter="10"
                  label="Description"
                  required
                ></v-text-field>
              </v-col>

              <v-col cols="4" md="2">
                <v-text-field
                  v-model="yearOfProduction"
                  :rules="nameRules"
                  :counter="10"
                  label="Year of production"
                  required
                ></v-text-field>
              </v-col>
            </v-row>
            <v-btn style="text-align: center" elevation="2" @click="createBook"
              >Create book
            </v-btn>
          </v-container>
        </v-form>
      </div>
      <v-form v-if="show" v-model="valid">
        <v-container>
          <v-row>
            <v-col cols="4" md="2">
              <v-text-field
                v-model="firstName"
                :rules="nameRules"
                :counter="10"
                label="firstName"
                required
              ></v-text-field>
            </v-col>
            <v-col cols="4" md="2">
              <v-text-field
                v-model="middleName"
                :rules="nameRules"
                :counter="10"
                label="MiddleName"
                required
              ></v-text-field>
            </v-col>

            <v-col cols="4" md="2">
              <v-text-field
                v-model="lastName"
                :rules="nameRules"
                :counter="10"
                label="LastName"
                required
              ></v-text-field>
            </v-col>

            <v-col cols="4" md="2">
              <v-text-field
                v-model="shortBiography"
                :rules="nameRules"
                :counter="10"
                label="ShortBiography"
                required
              ></v-text-field>
            </v-col>
          </v-row>
          <v-btn style="text-align: center" elevation="2" @click="addAuthor"
            >Add author
          </v-btn>
          <div v-if="pending">loading...</div>
          <div v-else>
            <select v-model="selected">
              <option disabled value="">Выберите один из вариантов</option>
              <option
                v-for="item in books.getBooks.books"
                v-bind:key="item.id"
                v-bind:value="item.id"
              >
                {{ item.title }}
              </option>
            </select>
            <span>Выбрано: {{ selected }}</span>
          </div>
        </v-container>
      </v-form>
    </div>
    <div v-if="role.includes('ROLE_CLIENT')">ACCESS DENIED</div>
  </NuxtLayout>
</template>

<script setup>
const selected = ref("");
const cond = ref(false);
const show = ref(true);
const title = ref("");
const genre = ref("");
const description = ref("");
const yearOfProduction = ref("");
const firstName = ref("");
const middleName = ref("");
const lastName = ref("");
const shortBiography = ref("");
const isSuccessful = ref(false);
const role = localStorage.getItem("role");

useGqlToken(localStorage.getItem("token"), {
  config: {
    type: "Bearer_",
    name: "Authorization",
  },
});
useGqlHeaders({ Accept: "application/json", "Cache-Control": "no-cache" });
const {
  data: books,
  refresh,
  pending,
} = await useAsyncData("queries", () =>
  GqlGetBooks({
    page: 0,
    size: 0,
    fieldForSearch: null,
    value: null,
  })
);

onMounted(() => {
  refresh();
});

async function createBook() {
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
    data: createBook,
    refresh: refreshBook,
  } = await useAsyncData("mutations", () =>
    GqlCreateBook({
      title: title.value,
      genre: genre.value,
      description: description.value,
      yearOfProduction: yearOfProduction.value,
    })
  );
  if (cond.value == false) {
    refresh();
  }
  watch(createBook, (newValue) => {
    refresh();
  });
  if (cond.value == true) {
    refreshBook();
  }
  cond.value = true;
  if (createBook.value.createBook != null) {
    isSuccessful.value = true;
  }
}

async function addAuthor() {
  console.log("change method1");
  console.log(selected.value);
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
    refresh: refreshAuthor,
  } = await useAsyncData("mutations", () =>
    GqlAddAuthor({
      bookId: selected.value,
      firstName: firstName.value,
      middleName: middleName.value,
      lastName: lastName.value,
      shortBiography: shortBiography.value,
    })
  );
  if (cond.value == true) {
    refreshAuthor();
  }
  cond.value = true;
  if (data.value != null) {
    isSuccessful.value = true;
  }
}
definePageMeta({
  middleware: "auth",
});
</script>