<template>
    <div class="px-3">
        <ul
            class="duration-400 absolute top-[100px] w-full border-b-2 border-t-2 border-slate-300 bg-primario-400 px-6 py-6 text-lg ease-in lg:static lg:flex lg:w-auto lg:items-center lg:border-transparent lg:bg-inherit lg:px-0 lg:pb-1"
            :class="[open ? 'left-0 ' : 'left-[-100%] ']"
        >
            <template v-if="!profiloStore.profilo.isAutenticato">
                <li class="my-6 lg:mx-4 lg:my-0" @click="MenuClose()">
                    <SignupButton class="w-[100%]" />
                </li>
                <li class="my-6 lg:mx-4 lg:my-0" @click="MenuClose()">
                    <LoginButton class="w-[100%]" />
                </li>
            </template>

            <template v-else>
                <li class="my-6 lg:mx-4 lg:my-0" @click="MenuClose()">
                    <RouterLink :to="{ name: 'profilo' }">
                        <Button severity="secondary" class="w-[100%]"
                            ><span class="font-bold">Ciao {{ profiloStore.profilo.nome }}</span>
                            <Badge v-if="checked"></Badge>
                        </Button>
                    </RouterLink>
                </li>
                <li class="my-6 lg:mx-4 lg:my-0" @click="MenuClose()">
                    <RouterLink :to="{ name: 'about' }">
                        <Button severity="secondary" class="w-[100%]"
                            ><span class="font-bold">Le Tue Aste</span></Button
                        >
                    </RouterLink>
                </li>
                <li class="my-6 lg:mx-4 lg:my-0" @click="MenuClose()">
                    <LogoutButton class="w-[100%]" />
                </li>
                <li class="my-6 lg:mx-4 lg:my-0" @click="MenuClose()">
                    <RouterLink :to="{ name: 'creaAsta' }">
                        <Button label="Crea Asta" class="sp-button w-[100%]"
                            ><span class="font-bold">Crea Asta</span></Button
                        >
                    </RouterLink>
                </li>
            </template>
        </ul>
    </div>

    <span class="absolute right-6 top-8 lg:hidden">
        <Button @click="MenuOpen()" size="small" plain severity="secondary" class=" ">
            <i :class="[open ? 'pi pi-times icon-size' : 'pi pi-bars icon-size']"></i>
        </Button>
    </span>
</template>

<script setup>
import Badge from 'primevue/badge';
import OverlayBadge from 'primevue/overlaybadge';
import Button from 'primevue/button';
import LogoutButton from '@/components/buttons/LogOut.vue';
import LoginButton from '@/components/buttons/LoginButton.vue';
import SignupButton from '@/components/buttons/SignUp.vue';
import { useAuth0 } from '@auth0/auth0-vue';
import { ref, onMounted } from 'vue';
import { useProfiloStore } from '../../stores/profiloStore';
import { on } from 'ws';

const checked = ref(false);
const profiloStore = useProfiloStore();

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

.sp-button {
    background-image: linear-gradient(to top left, var(--tw-gradient-stops));
    --tw-gradient-from: rgb(244 117 117 / 0.9) var(--tw-gradient-from-position);
    --tw-gradient-to: rgb(244 117 117 / 0) var(--tw-gradient-from-position);
    --tw-gradient-stops: var(var(--tw-gradient-from), var(--tw-gradient-to));
    --tw-gradient-to: rgb(232 121 249 / 0) var(--tw-gradient-to-position);
    --tw-gradient-stops: var(--tw-gradient-from), #e879f9 var(--tw-gradient-via-position),
        var(--tw-gradient-to);
    --tw-gradient-to: #4d91ff var(--tw-gradient-to-position);
}

.slide-fade-enter-active {
    transition: all 0.3s ease-out;
}

.slide-fade-leave-active {
    transition: all 0.8s cubic-bezier(1, 0.5, 0.8, 1);
}

.slide-fade-enter-from,
.slide-fade-leave-to {
    transform: translateX(20px);
    opacity: 0;
}
</style>
