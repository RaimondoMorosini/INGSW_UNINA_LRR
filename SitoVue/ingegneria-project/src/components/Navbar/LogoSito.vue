<template>
    <RouterLink to="/">
        <Button
            text
            @click="impostaVetrina"
            class="hover:ring-greyButton-400 mt-1 flex content-evenly rounded bg-inherit px-3 font-bold hover:ring-2 md:items-center md:px-0 md:pb-2"
        >
            <img src="../../assets/Icon/LogoSVGSito.svg" alt="Logo" class="h-1col w-1col" />
            <div class="flex pt-[1.1rem] md:pt-0">
                <h1 class="text-2xl text-slate-900">Deals24</h1>
                <h1 class="pt-[0.8rem] text-xs text-slate-900">RRL</h1>
            </div>
        </Button>
    </RouterLink>
</template>

<script setup>
import { RouterLink } from 'vue-router';
import { useVetrinaStore } from '../../stores/vetrinaStore';
import Button from 'primevue/button';

const vetrinaInstance = useVetrinaStore();

function impostaVetrina() {
    vetrinaInstance.setAccessVetrina(true);
}
</script>
