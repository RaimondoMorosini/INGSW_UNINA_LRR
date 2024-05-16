<template>
    <div class="px-3">
        <ul
            class="absolute top-[90px] w-full border-b-2 border-t-2 border-slate-300 bg-primario-100/90 px-6 py-6 text-lg duration-500 ease-in lg:static lg:flex lg:w-auto lg:items-center lg:border-transparent lg:bg-inherit lg:px-0 lg:pb-1"
            :class="[open ? 'left-0 ' : 'left-[-100%] ']"
        >
            <template v-if="!isAuthenticated">
                <li class="my-6 lg:mx-4 lg:my-0" @click="MenuClose()">
                    <SignupButton
                        class="mt-1 block w-[100%] rounded px-2 py-1 font-semibold hover:bg-primario-100 hover:ring-2 hover:ring-slate-300/80 lg:mx-4"
                    />
                </li>
                <li class="my-6 lg:mx-4 lg:my-0" @click="MenuClose()">
                    <LoginButton
                        class="mt-1 block w-[100%] rounded px-2 py-1 font-semibold hover:bg-primario-100 hover:ring-2 hover:ring-slate-300/80 lg:mx-4"
                    />
                </li>
            </template>

            <template v-if="isAuthenticated">
                <li class="my-6 lg:mx-4 lg:my-0" @click="MenuClose()">
                    <RouterLink :to="{ name: 'profilo' }">
                        <p
                            class="!sm:w-[100%] mt-1 block rounded px-2 py-1 text-center font-semibold hover:bg-primario-100 hover:ring-2 hover:ring-slate-300/80 lg:mx-4"
                        >
                            Ciao {{ user.name }}
                        </p>
                    </RouterLink>
                </li>
                <li class="my-6 lg:mx-4 lg:my-0" @click="MenuClose()">
                    <router-link to="/profilo/notifiche">
                        <p
                            class="mt-1 block rounded px-2 py-1 text-center font-semibold hover:bg-primario-100 hover:ring-2 hover:ring-slate-300/80 lg:mx-4"
                        >
                            Notifiche
                        </p>
                    </router-link>
                </li>
                <li class="my-6 lg:mx-4 lg:my-0" @click="MenuClose()">
                    <RouterLink :to="{ name: 'about' }">
                        <p
                            class="!sm:w-[100%] mt-1 block rounded px-2 py-1 text-center font-semibold hover:bg-primario-100 hover:ring-2 hover:ring-slate-300/80 lg:mx-4"
                        >
                            Tue Aste
                        </p>
                    </RouterLink>
                </li>
                <li class="my-6 lg:mx-4 lg:my-0" @click="MenuClose()">
                    <LogoutButton
                        class="mt-1 block w-[100%] rounded px-2 py-1 text-center font-semibold hover:bg-primario-100 hover:ring-2 hover:ring-slate-300/80 lg:mx-4"
                    />
                </li>
                <li class="my-6 lg:mx-4 lg:my-0" @click="MenuClose()"> 
                    <RouterLink :to="{ name: 'creaAsta' }">
                        <p
                            class="!sm:w-[100%] mt-1 block rounded bg-primario-200 px-2 py-1 text-center font-semibold ring-2 ring-slate-300/80 hover:bg-primario-100 lg:mx-4"
                        >
                            Crea Asta
                        </p>
                    </RouterLink>
                </li>
            </template>
        </ul>
    </div>

    <span
        class="absolute right-6 top-5 rounded text-4xl hover:bg-primario-100 hover:ring-2 hover:ring-slate-300/80 lg:hidden"
        @click="MenuOpen()"
    >
        <i :class="[open ? 'bi bi-x ' : 'bi bi-filter-left']"></i>
    </span>
</template>

<script setup>
import LogoutButton from '@/components/buttons/LogOut.vue';
import LoginButton from '@/components/buttons/LoginButton.vue';
import SignupButton from '@/components/buttons/SignUp.vue';
import { useAuth0 } from '@auth0/auth0-vue';
import { ref } from 'vue';

const { isAuthenticated } = useAuth0();

const { user } = useAuth0();

const open = ref(false);
function MenuOpen() {
    open.value = !open.value;
}
function MenuClose() {
    open.value = false;
}
</script>
