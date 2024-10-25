<template>

    <div class="flex flex-col gap-3 rounded px-2 py-1 mx-auto w-[80%] my-2.5">

        <div class=" flex flex-col">
            <Dropdown @change="onClickOpzione()" v-model="opzioneSelezionata" :options="opzioniNotifiche"
                optionLabel="name" placeholder="Ordina per" class="dimensione font-bold" />
        </div>

        <Card class="h-[300px] overflow-y-scroll">
            <template #title>
                {{ titoloOpzione }}
            </template>
            <template #content v-if="notifiche">
                <Accordion @click="onClickNotifica" v-for="(notifica,index) in notifiche">
                    <AccordionTab :header="notifica.oggettoDellaNotifica">
                        {{ notifica.messaggio }}
                    </AccordionTab>
                </Accordion>
            </template>
        </Card>

        <Paginator :rows="5" :totalRecords="numeroNotifiche" @page="onPage"></Paginator>

    </div>

</template>

<script setup>
import { ref,onMounted } from 'vue';
import Dropdown from 'primevue/dropdown';
import Card from 'primevue/card';
import Paginator from 'primevue/paginator';
import Accordion from 'primevue/accordion';
import AccordionTab from 'primevue/accordiontab';
import { getNotificheNonLette } from '../../service/notifiche';


const opzioneSelezionata = ref(null);
const opzioniNotifiche = ref([
    { name: 'Notifiche da leggere' },
    { name: 'Tutte le notifiche ordinati per data (più recenti)' },
    { name: 'Tutte le notifiche ordinati per data (meno recenti)' },
]);
const props = defineProps([
    'numeroNotificheNonLette',
]);
const numeroNotificheNonLette = ref();
const titoloOpzione = ref('Notifiche da leggere');
const numeroPagina = ref(0);
const notifiche = ref();

onMounted(async () => {

    opzioneSelezionata.value = { name: 'Notifiche da leggere' };
    numeroNotificheNonLette.value = props.numeroNotificheNonLette;

    const parametriRichiestaNotificheNonLette = {

        numeroElementi: 5,
        numeroPagina: 0
    };

    if(numeroNotificheNonLette.value > 0) {

        try {

            notifiche.value = await getNotificheNonLette(parametriRichiestaNotificheNonLette);

        } catch (e) {

            console.error("get notifiche fallitoooooo:", e);
        }
    }
});

const onClickOpzione = () => {

    titoloOpzione.value = opzioneSelezionata.value.name;
};

const onPage = (event) => {

    console.log(event.page + 1);
};

const onClickNotifica = () => {
    
    numeroNotificheNonLette.value--;
};

</script>
