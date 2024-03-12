<template>
  <div>
    <button @click="login">Log in</button>
  </div>
</template>

<script setup>
import { useAuth0 } from '@auth0/auth0-vue'

const { loginWithRedirect } = useAuth0()

function login() {
  console.log('autenticated')
  loginWithRedirect()
}
</script>
