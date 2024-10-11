<template>
    <div class="px-3">
        <ul
            class="duration-400 absolute top-[100px] w-full border-b-2 border-t-2 border-slate-300 bg-primario-400/90 px-6 py-6 text-lg ease-in lg:static lg:flex lg:w-auto lg:items-center lg:border-transparent lg:bg-inherit lg:px-0 lg:pb-1"
            :class="[open ? 'left-0 ' : 'left-[-100%] ']"
        >
            <template v-if="!isAuthenticated">
                <li class="my-6 lg:mx-4 lg:my-0" @click="MenuClose()">
                    <SignupButton class="w-[100%]"
                    />
                </li>
                <li class="my-6 lg:mx-4 lg:my-0" @click="MenuClose()">
                    <LoginButton class="w-[100%]"
                    />
                </li>
            </template>

            <template v-if="isAuthenticated">
                <li class="my-6 lg:mx-4 lg:my-0" @click="MenuClose()">
                    <RouterLink :to="{ name: 'profilo' }">
                        <Button class="ring-2 ring-slate-300/80 w-[100%]"><span class="text-[#1C1B22]  font-bold">Ciao {{ user.name }}</span></Button>
                    </RouterLink>
                </li>
                <li class="my-6 lg:mx-4 lg:my-0" @click="MenuClose()">
                    <router-link to="/profilo/notifiche">
                        <Button class="ring-2 ring-slate-300/80 w-[100%]"><span class="text-[#1C1B22]  font-bold">Notifiche </span></Button>
                    </router-link>
                </li>
                <li class="my-6 lg:mx-4 lg:my-0" @click="MenuClose()">
                    <RouterLink :to="{ name: 'about' }">
                        <Button class="ring-2 ring-slate-300/80 w-[100%]"><span class="text-[#1C1B22] font-bold">Le Tue Aste</span></Button>
                    </RouterLink>
                </li>
                <li class="my-6 lg:mx-4 lg:my-0" @click="MenuClose()">
                    <LogoutButton class="w-[100%]"/>
                </li>
                <li class="my-6 lg:mx-4 lg:my-0" @click="MenuClose()">
                    <RouterLink :to="{ name: 'creaAsta' }">
                        <Button label="Crea Asta" class="sp-button w-[100%] ring-2 ring-slate-300/80"><span class="text-[#1C1B22]  font-bold">Crea Asta</span></Button>
                    </RouterLink>
                </li>
            </template>
        </ul>
    </div>

    <span  class="absolute right-6 top-8 lg:hidden">
        <Button @click="MenuOpen()" size="small" plain text raised>
            <i :class="[open ? 'pi pi-times icon-size' : 'pi pi-bars icon-size']"></i>
        </Button>
    </span>
</template>

<script setup>
import Button from 'primevue/button';
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

<style scoped>
.icon-size {
    font-size: 2rem;
}

.sp-button{
    background-image: linear-gradient(to top left, var(--tw-gradient-stops));
    --tw-gradient-from:rgb(244 117 117 / 0.9) var(--tw-gradient-from-position);
    --tw-gradient-to:rgb(244 117 117 / 0) var(--tw-gradient-from-position);
    --tw-gradient-stops: var(var(--tw-gradient-from), var(--tw-gradient-to));
    --tw-gradient-to: rgb(232 121 249 / 0)  var(--tw-gradient-to-position);
    --tw-gradient-stops: var(--tw-gradient-from), #e879f9 var(--tw-gradient-via-position), var(--tw-gradient-to);
    --tw-gradient-to: #4d91ff var(--tw-gradient-to-position);
}
</style>
