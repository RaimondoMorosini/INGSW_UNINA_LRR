<!-- eslint-disable prettier/prettier -->
<template>
  <div class="px-3">
    <ul
      class="absolute top-[90px] w-full border-b-2 border-t-2 border-slate-300 bg-primario-100/90 px-6 py-6 text-lg duration-500 ease-in md:static md:flex md:w-auto md:items-center md:border-transparent md:bg-inherit md:px-0 md:pb-1"
      :class="[open ? 'left-0 ' : 'left-[-100%] ']"
    >
      <template v-if="!isAuthenticated">
        <li class="my-6 md:mx-4 md:my-0">
          <SignupButton
            class="mt-1 block w-[100%] rounded px-2 py-1 font-semibold hover:bg-primario-100 hover:ring-2 hover:ring-slate-300/80 md:mx-4"
          />
        </li>
        <li class="my-6 md:mx-4 md:my-0">
          <LoginButton
            class="mt-1 block w-[100%] rounded px-2 py-1 font-semibold hover:bg-primario-100 hover:ring-2 hover:ring-slate-300/80 md:mx-4"
          />
        </li>
      </template>

      <template v-if="isAuthenticated">
        <li class="my-6 md:mx-4 md:my-0">
          <RouterLink :to="{ name: 'profilo' }">
            <p
              class="!sm:w-[100%] mt-1 block rounded px-2 py-1 text-center font-semibold hover:bg-primario-100 hover:ring-2 hover:ring-slate-300/80 md:mx-4"
            >
              Ciao {{ user.name }}
            </p>
          </RouterLink>
        </li>
        <li class="my-6 md:mx-4 md:my-0">
          <router-link to="/profilo/notifiche">
            <p
              class="mt-1 block rounded px-2 py-1 text-center font-semibold hover:bg-primario-100 hover:ring-2 hover:ring-slate-300/80 md:mx-4"
            >
              Notifiche
            </p>
          </router-link>
        </li>
        <li class="my-6 md:mx-4 md:my-0">
          <RouterLink :to="{ name: 'about' }">
            <p
              class="!sm:w-[100%] mt-1 block rounded px-2 py-1 text-center font-semibold hover:bg-primario-100 hover:ring-2 hover:ring-slate-300/80 md:mx-4"
            >
              Tue Aste
            </p>
          </RouterLink>
        </li>
        <li class="my-6 md:mx-4 md:my-0">
          <LogoutButton
            class="mt-1 block w-[100%] rounded px-2 py-1 text-center font-semibold hover:bg-primario-100 hover:ring-2 hover:ring-slate-300/80 md:mx-4"
          />
        </li>
        <li class="my-6 md:mx-4 md:my-0">
          <p
            class="!sm:w-[100%] mt-1 block rounded bg-primario-200 px-2 py-1 text-center font-semibold ring-2 ring-slate-300/80 hover:bg-primario-100 md:mx-4"
          >
            Crea Asta
          </p>
        </li>
      </template>
    </ul>
  </div>

  <span
    class="absolute right-6 top-5 rounded text-4xl hover:bg-primario-100 hover:ring-2 hover:ring-slate-300/80 md:hidden"
    @click="MenuOpen()"
  >
    <i :class="[open ? 'bi bi-x ' : 'bi bi-filter-left']"></i>
  </span>
</template>

<script setup>
import { ref } from 'vue'
import LoginButton from '@/components/buttons/LoginButtom.vue'
import LogoutButton from '@/components/buttons/LogOut.vue'
import SignupButton from '@/components/buttons/SignUp.vue'
import { useAuth0 } from '@auth0/auth0-vue'

const { isAuthenticated } = useAuth0()

const { user } = useAuth0()

const open = ref(false)
function MenuOpen() {
  open.value = !open.value
}
</script>
