<template>
    <form name="login-form" >
      <div class="mb-3">
         <label for="username">Username: </label>
         <input type="text" id="username" v-model="input.username" />
     </div>
    <div class="mb-3">
        <label for="password">Password: </label>
        <input type="password" id="password" v-model="input.password" />
    </div>
      <button class="btn btn-outline-dark" type="submit">
        Login
      </button>

    </form>
</template>

<script setup>
import ref from 'vue';

input = ref (
    {
        username: "",
        password: ""
    }
);

    function login(){
        // Add a semicolon at the end of the line
            if(this.input.username != ""|| this.input.password != ""){
                console.log("autenticated")
            }else{
                console.log("not autenticatedUsername and Password cannot be empty")
            }
        }
   
</script>