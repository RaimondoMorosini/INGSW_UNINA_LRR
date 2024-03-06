<script setup>
import { decodeCredential,googleLogout,googleTokenLogin } from 'vue3-google-login'
const callback = (response) => {
  // decodeCredential will retrive the JWT payload from the credential
  const userData = decodeCredential(response.credential)
  console.log("Handle the userData", userData)
}

const getToken = () => {
  googleTokenLogin().then((response) => {
    console.log("token:", response)
  })
}

</script>

<template>
  <GoogleLogin :callback="callback" />

  <button @click="getToken">stampa token</button>
</template>
