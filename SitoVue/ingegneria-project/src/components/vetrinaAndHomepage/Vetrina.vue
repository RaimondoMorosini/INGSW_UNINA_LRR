<template>
    <h1 class="p-12 text-4xl font-bold">Tutte le aste</h1>
        <DataView :value="aste" :layout="layout">
            <template #grid="slotProps">
                <div class="grid grid-cols-12 gap-4 justify-items-center p-2 sm:p-6 md:p-8 lg:p-10">
                    <div v-for="(item, index) in slotProps.items" :key="index"
                        class="col-span-12 sm:col-span-6 md:col-span-4 xl:col-span-6 p-1 flex justify-center">

                        <div
                            class="contenitore-asta p-6 border border-surface-200 dark:border-surface-700 bg-surface-0 dark:bg-surface-900 rounded flex flex-col justify-between items-center w-full">

                            <!-- Sezione immagine -->
                            <div class="bg-surface-50 flex justify-center rounded p-4">
                                <div class="relative mx-auto w-40 sm:w-64 h-40 sm:h-64">
                                    <img class="rounded w-full h-full object-cover" :src="`${item.immagini[0]}`"
                                        :alt="item.name" />
                                    <div class="absolute bg-black/70 rounded-border" style="left: 4px; top: 4px">
                                        <Tag :value="item.tipoAsta"></Tag>
                                    </div>
                                </div>
                            </div>

                            <!-- Dettagli -->
                            <div class="pt-6">
                                <div class="flex flex-row justify-between items-start gap-2">
                                    <div>
                                        <span
                                            class="font-bold span-categoria text-surface-500 dark:text-surface-400 text-sm">{{
                                            item.categoria }}</span>
                                        <div class="text-lg font-medium mt-1">{{ item.nome }}</div>
                                    </div>
                                </div>
                                <div class="flex flex-col gap-6 mt-6">
                                    <span v-if="item.tipoAsta == 'asta_inglese'"
                                        class="text-center text-2xl font-semibold">ULTIMA OFFERTA: {{ item.prezzoAttuale
                                        }}€</span>
                                    <span v-else class="text-center text-2xl font-semibold">BASE ASTA: {{ item.baseAsta
                                        }}€</span>
                                    <div class="flex gap-2">
                                        <Button v-if="isScaduta(item.dataScadenza)" class="flex-auto whitespace-nowrap" :disabled="true">ASTA SCADUTA</Button>
                                        <Button v-else @click="clickParteciAsta(item.idAsta)" icon="pi pi-shopping-cart" label="PARTECIPA ALL'ASTA"
                                            class="flex-auto whitespace-nowrap"></Button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </template>
        </DataView>

    <Paginator :rows="12" :totalRecords="nunmeroAste" @page="onPage"></Paginator>

</template>

<script setup>
import { ref, onMounted } from "vue";
import DataView from 'primevue/dataview';
import Button from 'primevue/button';
import Tag from 'primevue/tag';
import Paginator from 'primevue/paginator';
import { postRest } from '../../scripts/RestUtils'
import { useRouter } from 'vue-router';

const router = useRouter(); // Usa il router
const paginaCliccata = ref(1);
const nunmeroAste = ref(0);
const aste = ref(null);
const layout = ref('grid');


onMounted(async () => {

    aste.value = await postRest("public/asta/getAllAste", bodyPerLaPost);
    nunmeroAste.value = await postRest("public/asta/getNumeroAste", bodyPerLaPost);
    console.log("asteeeeeeeeeee: ", aste.value);
});

const onPage = async (event) => {
    
    paginaCliccata.value = event.page + 1;
    bodyPerLaPost.pagina = paginaCliccata.value;
    aste.value = await postRest("public/asta/getAllAste", bodyPerLaPost);
    console.log("pagina cliccata: ", paginaCliccata.value);
};

const bodyPerLaPost = {
    pagina: paginaCliccata.value,
    elementiPerPagina: 12,
    categoria: "tutte",
    nomeProdotto: "",
    tipoAsta: [],
    caratteristicheSelezionate: [],
    prezzoMin: null,
    prezzoMax: null,
    campoOrdinamento: null,
    direzioneOrdinamento: null
}

const clickParteciAsta = (idAsta) => {

    router.push({

        path: '/asta/'+idAsta,
    })
}

const isScaduta = (dataScadenza) => {

    const dataOggi = Date.now();
    return dataScadenza < dataOggi;
}

</script>


<style scoped>

.contenitore-asta {

    background-color: #F2F2F2;
    border: 2px solid #E879F9;
}

.span-categoria {

    color: #E879F9;
}
</style>