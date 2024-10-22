<template>
    <div class="flex flex-col gap-3 px-2 py-3">
        <div
            class="user-info-photo flex w-full flex-row items-center justify-between space-x-5 rounded bg-slate-200/20 px-2 py-3 ring-1 ring-[#cbd5e1]"
        >
            <div class="flex flex-row space-x-1">
                <img
                    :src="user.picture"
                    alt="Immagine Profilo impostata"
                    class="h-20 w-20 rounded-full border-primario-400"
                />
                <div class="User info">
                    <h2 class="mt-4 text-2xl font-semibold">{{ user.name }}</h2>
                    <h2 class="text-sm text-gray-500">{{ user.email }}</h2>
                </div>
            </div>
            <RouterLink :to="{ name: 'ediProfilo' }">
                <!--
                                <button class="bottone">Modifica Profilo</button>
                -->

                <Button><span class="font-bold text-black">Modifica Profilo</span> </Button>
            </RouterLink>
        </div>

        <div class="Bio rounded bg-slate-200/20 px-2 ring-1 ring-[#cbd5e1]">
            <h1 class="text-xl">Bio:</h1>
            <p>
                Lorem, ipsum dolor sit amet consectetur adipisicing elit. Maxime ex delectus
                obcaecati! Molestiae, dicta in error cupiditate, soluta similique cumque deleniti
                impedit sapiente ex itaque mollitia amet dolores perspiciatis. Provident?
            </p>
        </div>

        <div class="Indirizzo rounded bg-slate-200/20 px-2 ring-1 ring-[#cbd5e1]">
            <h1 class="text-xl">Indirizzo:</h1>
            {{ user.address }}
        </div>

        <ul class="LinkAssociati rounded bg-slate-200/20 px-2 ring-1 ring-[#cbd5e1]">
            <h1 class="text-xl">Collegamenti</h1>
            <li v-for="(link, index) in linkUtili" v-bind:key="index">
                {{ link }}
            </li>
        </ul>
    </div>
</template>

<script setup>
import { useAuth0 } from '@auth0/auth0-vue';
import Button from 'primevue/button';

const linkUtili = ['GitHub', 'LinkedIn', 'Twitter', 'Facebook'];

const { user } = useAuth0();
</script>


