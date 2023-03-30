<template>
  <NuxtLayout name="authenticated">
    <div v-if="pending">loading</div>
    <div v-else>
      <div class="d-flex justify-center">
        <v-card width="700px">
          <v-card-title
            class="text-xs-h6 text-md-h5 text-lg-h4 font-weight-black"
            >{{ book.title }}</v-card-title
          >
          <v-img height="200" src="../assets/noname.png"></v-img>
          <v-card-text style="color: black">
            <div class="font-weight-bold">Genre</div>
            {{ book?.genre }} <br />
            <div class="font-weight-bold">Description</div>
            {{ book.description }}<br />
            <div class="font-weight-bold">Year of production</div>
            {{ book.yearOfProduction }}<br />
            <div class="font-weight-bold">Average mark</div>
            {{ book.averageMark }}<br />
            <div class="font-weight-bold">Amount of reviews</div>
            {{ book.amountOfReviews }}<br />
            <div class="font-weight-bold">Authors</div>
            <p v-for="item in book.authors" :key="item.id">
              {{ item.firstName }}
              {{ item.middleName }}
              {{ item.lastName }}
            </p>
          </v-card-text>
          <v-card width="700px">
            <v-card-text>
              <div class="font-weight-bold">Reviews</div>
              <p v-for="item in book.reviews" :key="item.id">
                <v-chip class="ma-2" color="orange" text-color="white">
                  <v-icon :icon="mdiStar" />
                  {{ item.mark }}
                </v-chip>
                <v-banner lines="one" icon="mdi-lock" color="teal" class="my-4">
                  <v-banner-avatar>{{ item.userId }}</v-banner-avatar>
                  <v-banner-text> {{ item.text }} </v-banner-text>
                  <v-banner-text class="d-flex align-end flex-column">
                  </v-banner-text>

                  <template v-slot:actions>
                    <v-btn
                      v-if="Number.parseInt(userId) == item.userId"
                      @click="showChangeReview = !showChangeReview"
                      >Change review</v-btn
                    >
                  </template>
                </v-banner>
              </p>
            </v-card-text>
          </v-card>
          
      <transition name="fade">
        <div
            class="d-flex flex-column w-5 ma-12"
            hide-details="auto"
            v-if="showChangeReview"
          >
            <v-row>
              <v-col cols="12" md="6">
                <v-textarea
                  v-model="textForChange"
                  solo
                  class="align-self-xl-stretch"
                  variant="outlined"
                  name="input-7-4"
                  label="Review"
                ></v-textarea>
              </v-col>
            </v-row>
            <v-select
              v-model="selectedForChange"
              :items="marks"
              variant="outlined"
              label="Select"
              hint="Choose one of the options"
              persistent-hint
            ></v-select>
            <v-btn flat color="secondary" @click="changeReview(book.reviews.find(review => review.userId == Number.parseInt(userId)).id)">
              Change review
            </v-btn>
          </div>
      </transition>
          <div
            class="d-flex flex-column w-5 ma-12"
            hide-details="auto"
            v-if="!(book.reviews.filter(review => review.userId == Number.parseInt(userId))).length > 0"
          >
            <v-row>
              <v-col cols="12" md="6">
                <v-textarea
                  v-model="text"
                  solo
                  class="align-self-xl-stretch"
                  variant="outlined"
                  name="input-7-4"
                  label="Review"
                ></v-textarea>
              </v-col>
            </v-row>
            <v-select
              v-model="selected"
              :items="marks"
              variant="outlined"
              label="Select"
              hint="Choose one of the options"
              persistent-hint
            ></v-select>
            <v-btn flat color="secondary" @click="addReview">
              Add review
            </v-btn>
          </div>
          <div class="d-flex flex-column w-5 ma-12">
      <v-btn flat color="grey" v-if="role.includes('ROLE_ADMIN')" v-on:click="show = !show">
        Change book
      </v-btn>
      <transition name="fade">
        <v-form v-if="show" v-model="valid">
          <v-container>
            <v-row>
              <v-col cols="12" md="4">
                <v-text-field
                  v-model="title"
                  :rules="nameRules"
                  :counter="10"
                  label="Title"
                  required
                ></v-text-field>
              </v-col>

              <v-col cols="12" md="4">
                <v-text-field
                  v-model="genre"
                  :rules="nameRules"
                  :counter="10"
                  label="Genre"
                  required
                ></v-text-field>
              </v-col>

              <v-col cols="12" md="4">
                <v-text-field
                  v-model="description"
                  :rules="nameRules"
                  :counter="10"
                  label="Description"
                  required
                ></v-text-field>
              </v-col>

              <v-col cols="12" md="4">
                <v-text-field
                  v-model="yearOfProduction"
                  label="Year of production"
                  required
                ></v-text-field>
              </v-col>
            </v-row>
          </v-container>
          <v-btn block elevation="2" color="red" @click="changeBook">Change </v-btn>
        </v-form>
      </transition>
      </div>
        </v-card>
      </div>

    </div>
  </NuxtLayout>
</template>

<script setup>
import { mdiAccount, mdiStar } from "@mdi/js";

const icon = mdiAccount;
const icon2 = mdiStar;
const text = ref("");
const show = ref(false);
const showChange = ref(true);
const title = ref("");
const genre = ref("");
const description = ref("");
const yearOfProduction = ref("");
const selected = ref("");

const textForChange = ref('')
const selectedForChange = ref('')

const marks = ref([1, 2, 3, 4, 5]);
const showChangeReview = ref(false)
definePageMeta({
  middleware: "auth",
});
const route = useRoute();
console.log("here");
useGqlToken(localStorage.getItem("token"), {
  config: {
    type: "Bearer_",
    name: "Authorization",
  },
});
useGqlHeaders({ Accept: "application/json", "Cache-Control": "no-cache" });
const { pending, data, refresh } = await useAsyncData("queries", () =>
  GqlGetInfoAboutBook({ bookId: parseInt(route.params.id) })
);
refresh();

const book = computed(() => data.value.getInfoAboutBook);

async function addReview() {
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
    refresh: reviewRefresh,
  } = await useAsyncData("mutations", () =>
    GqlAddReview({
      text: text.value,
      mark: selected.value,
      bookId: parseInt(route.params.id),
      userId: localStorage.getItem("id"),
    })
  );
  reviewRefresh();
  watch(data, (newValue) => {
    refresh();
  });
}

async function changeBook() {
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
    GqlUpdateBook({
      bookId: parseInt(route.params.id),
      title: title.value,
      genre: genre.value,
      description: description.value,
      yearOfProduction: yearOfProduction.value,
    })
  );
  updateRefresh();
  refresh();
}

async function changeReview(id) {
  console.log("id", id)
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
    GqlChangeReview({
      reviewId: id,
      text: textForChange.value,
      mark: selectedForChange.value,
      bookId: parseInt(route.params.id),
      userId: Number.parseInt(userId)
    })
  );
  updateRefresh();
  refresh();
}

const role = computed(() => localStorage.getItem("role"));
const userId = localStorage.getItem("id");
</script>