<template>
  <NuxtLayout name="authenticated">
    <div
      class="d-flex ma-6 mb-0"
    >
        <v-text-field
        class="mr-6"
          v-model="search"
          clearable
          label="Search"
          type="text"
          hide-details="auto"
          variant="outlined"
        ></v-text-field>
        <v-btn
          class="align-self-center"
          depressed
          elevation="2"
          outlined
          size="x-large"
          @click="refreshData"
        >search</v-btn>
    </div>
    <div class="d-flex mx-6">
        <v-checkbox
          class="flex-grow-0"
          v-model="checkboxes"
          :label="`title`"
          value="title"
          hide-details="auto"
          v-on:click="checkBox()"
        ></v-checkbox>
        <v-checkbox
          class="flex-grow-0"
          v-model="checkboxes"
          :label="`description`"
          value="description"
          hide-details="auto"
          v-on:click="checkBox()"
        ></v-checkbox>
        <v-checkbox
          class="flex-grow-0"
          v-model="checkboxes"
          :label="`author`"
          value="author"
          hide-details="auto"
          @click="checkBox()"
        ></v-checkbox>
    </div>
    <div v-if="pending">Loading ...</div>
    <div v-else>
      <v-table height="600px">
        <thead>
          <tr>
            <th class="text-left">Title</th>
            <th class="text-left">Genre</th>
            <th class="text-left">YearOfProduction</th>
            <th class="text-left">AverageMark</th>
            <th class="text-left">Author</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in books?.getBooks?.books" :key="item.id">
            <td @click="getBook(item.id)">{{ item.title }}</td>
            <td>{{ item.genre }}</td>
            <td>{{ item.yearOfProduction }}</td>
            <td>{{ item.averageMark }}</td>
              <td 
                v-for="aut in item.authors"
                :key="aut.id"
                @click="getAuthor(aut.id)"
              >
                {{ aut.firstName }} {{ aut.lastName }} {{ aut.middleName }}
              </td>
          </tr>
        </tbody>
      </v-table>
    <div class="text-center">
      <v-pagination
        v-model="page"
        :length="len"
        @click="refreshData"
      ></v-pagination>
    </div>
    </div>
  </NuxtLayout>
</template>


<script setup>
const checkboxes = ref([]);
const search = ref("");
const page = ref(1);
const lenght = ref(1);
useGqlToken(localStorage.getItem("token"), {
  config: {
    type: "Bearer_",
    name: "Authorization",
  },
});
useGqlHeaders({ "Accept": "application/json", "Cache-Control": "no-cache" });
const {
  data: books,
  pending,
  refresh,
  error,
} = await useAsyncData("queries", () =>
  GqlGetBooks({
    page: page.value - 1,
    size: 10,
    fieldForSearch: checkboxes.value[0],
    value: search.value,
  }),
);
watch(error, (newValue) => {
  if (JSON.stringify( newValue.message ).substring(1, 17).includes('Access is denied')) {
    localStorage.removeItem('token')
    localStorage.removeItem('id')
    localStorage.removeItem('username')
    localStorage.removeItem('role')
    navigateTo('/login')
  }
} )
console.log("books", error.value)
function refreshData() {
  console.log();
  refresh();
}

onMounted(() => {
  console.log(books)
    refreshData();
})

watch(books, (newValue) => {
  const { amountOfBooks } = newValue.getBooks
  lenght.value =
    amountOfBooks % 10 != 0
      ? Math.trunc(amountOfBooks / 10) + 1
      : amountOfBooks / 10;
      if (amountOfBooks < 10) {
        page.value = 1
      }
});
const len = computed(() => lenght.value)

function getBook(id) {
  navigateTo("/books/" + id);
}
function getAuthor(id) {
  navigateTo("/authors/" + id);
}
function checkBox() {
  this.checkboxes = [];
}
definePageMeta({
  middleware: "auth",
});
</script>


<style>

.text-center {
  padding-top: 0px;
  padding-right: 10px;
  padding-bottom: 0px;
  padding-left: 10px;
}

</style>