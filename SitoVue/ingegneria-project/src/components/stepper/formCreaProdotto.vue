<template>
    <form @submit.prevent="gestioneInvio">
        <main
            class="mt-5 flex h-auto w-[100%] min-w-3col flex-col justify-around gap-3 px-5 md:w-auto lg:flex-row"
        >
            <div class="flex h-4col w-[100%] auto-rows-max flex-col justify-between gap-3 px-2">
                <div class="formSpace">
                    <label for="nomeProdotto">Nome Prodotto</label>
                    <input
                        class="w-[60%] rounded md:w-[70%]"
                        type="text"
                        id="nomeProdotto"
                        v-model="nomeProdotto"
                    />
                </div>
                <div class="formSpace">
                    <label for="descrizione">Descrizione Prodotto</label>
                    <textarea
                        rows="5"
                        class="w-[60%] rounded md:w-[70%]"
                        id="descrizione"
                        v-model="descrizione"
                    />
                </div>
                <div class="formSpace">
                    <label for="prezzoBase">Prezzo Base</label>
                    <input
                        class="w-[60%] rounded md:w-[70%]"
                        type="number"
                        id="prezzoBase"
                        v-model="prezzoBase"
                    />
                </div>

                <InputGroup class="categoriaSelector w-[100%]">
                    <InputGroupAddon class="bg-slate-100 ring-1 ring-black">
                        <i class="pi pi-th-large" style="color: #000"></i>
                    </InputGroupAddon>
                    <TreeSelect
                        id="categoria"
                        v-model="selectedCategory"
                        :options="nodes"
                        option-label="name"
                        placeholder="Seleziona Categoria"
                        class="rounded-r bg-slate-100 text-black ring-1 ring-black"
                    />
                </InputGroup>
            </div>
            <div class="flex h-[100%] w-[100%] justify-center px-2 lg:justify-start">
                <ImageUploader />
            </div>
        </main>

        <div class="areaBottoni my-4 px-10">
            <button class="bottone" type="submit">Successivo</button>
        </div>
    </form>
</template>

<script setup>

import InputGroup from 'primevue/inputgroup';
import InputGroupAddon from 'primevue/inputgroupaddon';
import TreeSelect from 'primevue/treeselect';
import ImageUploader from './ImageUploader.vue';
import { onMounted,onUnmounted, ref, defineEmits } from 'vue';
import { useAstaStore } from '../../stores/astaStore.js';
import{ getCategorie} from '../../service/categoriaService.js';

const nodes = ref([]);


const storeInstance = useAstaStore();
const emit = defineEmits(['update:active']);
const selectedCategory = ref(storeInstance.asta.categoria);
const nomeProdotto = ref(storeInstance.asta.nomeProdotto);
const descrizione = ref(storeInstance.asta.descrizione);
const prezzoBase = ref(storeInstance.asta.prezzoBase);
const immagini = ref(null);

const categoriaSelezionata = function (obj) {
    let keys = '';
    for (let key in obj) {
        keys = key;
    }
    return keys;
};

const categoriaSalvata = ref(categoriaSelezionata(storeInstance.asta.categoria));

onMounted(() => {
    storeInstance.updateAsta({
        step: 0,
    });
    getCategorie().then((response) => {
        nodes.value = response;
    });
});
onUnmounted(() => {
    storeInstance.updateAsta({
        nomeProdotto: nomeProdotto.value,
        descrizione: descrizione.value,
        prezzoBase: prezzoBase.value,
        categoria: selectedCategory,
    });
});

const gestioneInvio = () => {
    if (!nomeProdotto.value || !descrizione.value || !prezzoBase.value || !categoriaSalvata) {
        alert('Compila tutti i campi.');
        return;
    }

    storeInstance.updateAsta({
        nomeProdotto: nomeProdotto.value,
        descrizione: descrizione.value,
        prezzoBase: prezzoBase.value,
        categoria: selectedCategory,
    });
    //emit('update:active', 1);
};
</script>

<style scoped>
#imgInp {
    width: 100%;
    background: #cc85f5;
    color: #fff;
    padding: 40px 15px;
    text-align: center;
    border-radius: 10px;
    border: 3px dashed #fff;
    font-size: 20px;
}
form {
    text-align: center;
}
label {
    text-align: center;
    display: block;
    margin: 25px 0 15px;
    font-size: 1rem;
    text-transform: uppercase;
    letter-spacing: 1px;
    font-weight: bold;
}
.formSpace {
    justify-content: space-between;
    display: flex;
    direction: column;
}

button.bottone {
    background-color: #cc85f5;
    padding: 10px 20px;
    color: white;
    border-radius: 5px;
    font-size: 1.1rem;
    font-weight: bold;
    width: 50%;
    margin: 10px;
}

button.bottone:hover {
    background-color: #7c3aed;
}

textarea {
    resize: none;
}

/* Chrome, Safari, Edge, Opera */
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
}

/* Firefox */
input[type='number'] {
    -moz-appearance: textfield;
}
</style>
