<template>
<NuxtLayout name="authenticated"> 
<div v-if="pending">
    Loading ...
  </div>
  <div v-else>
      <div class="d-flex justify-center">
      <v-card width="700px">
      <v-card-title class="text-xs-h6 text-md-h5 text-lg-h4 font-weight-black">
        <p>
          {{username}}
        </p>
        </v-card-title>
        <v-img height="350" src="../assets/user.jpg"></v-img>
        <v-card-text style="color: black">
            <div class="font-weight-bold">Firstname</div>
            {{ user.getInfoAboutClient.firstName }} <br />
            <div class="font-weight-bold">Middlename</div>
            {{ user.getInfoAboutClient?.middleName }}<br />
            <div class="font-weight-bold">Lastname</div>
            {{ user.getInfoAboutClient?.lastName }}<br />
            <div class="font-weight-bold">Role</div>
            {{ user.getInfoAboutClient?.role }}<br />
          </v-card-text>
      </v-card>
      </div>
  </div>
    </NuxtLayout>
</template>

<script setup>
    definePageMeta({
      middleware: 'auth'
    })
    const username = localStorage.getItem("username")
    console.log("here")
    useGqlToken(localStorage.getItem('token'), {
    config: {
      type: 'Bearer_',
      name: 'Authorization'
    }
    })
    useGqlHeaders({'Accept': 'application/json'})
    const { pending, data:user, refresh } = await useAsyncData('queries', () => GqlGetInfoAboutClient({ login: username }));
        refresh();
</script>
