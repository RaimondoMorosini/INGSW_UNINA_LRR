<template>
    <form @submit.prevent="gestioneInvio">
        <main
            class="mt-5 flex h-auto min-w-3col flex-col justify-around gap-3 px-5 md:w-auto lg:flex-row"
        >
            <div class="h-4col auto-rows-max justify-between gap-3 px-2 lg:w-[50%]">
                <div class="formSpace pt-6">
                    <FloatLabel>
                        <InputText
                            class="w-[100%] rounded"
                            id="nomeProdotto"
                            v-model="nomeProdotto"
                        />
                        <label for="nomeProdotto">Nome Prodotto</label>
                    </FloatLabel>
                </div>
                <div class="formSpace pt-6">
                    <FloatLabel>
                        <InputText
                            id="descrizione"
                            class="min-h-[10rem] w-[100%] rounded"
                            v-model="descrizione"
                        />
                        <label for="descrizione">Descrizione Prodotto</label>
                    </FloatLabel>
                </div>
                <div class="formSpace pt-6">
                    <FloatLabel>
                        <InputNumber
                            class="w-[100%] rounded"
                            id="prezzoBase"
                            v-model="prezzoBase"
                        />
                        <label for="prezzoBase">Prezzo Base</label>
                    </FloatLabel>
                </div>

                <InputGroup class="categoriaSelector pt-6">
                    <InputGroupAddon class="ring-1 ring-black">
                        <i class="pi pi-th-large"></i>
                    </InputGroupAddon>
                    <TreeSelect
                        id="categoria"
                        v-model="selectedCategory"
                        :options="nodes"
                        option-label="name"
                        placeholder="Seleziona Categoria"
                        class="rounded-r text-black ring-1 ring-black"
                    />
                </InputGroup>
            </div>
            <div class="flex justify-center p-2 lg:w-[50%] lg:justify-start">
                <ImageUploader />
                <!--
                
                -->
                
            </div>
        </main>

        <div class="areaBottoni my-4 px-10">
            <button class="bottone" type="submit">Successivo</button>
        </div>
    </form>
</template>

<script setup>
import InputNumber from 'primevue/inputnumber';
import FloatLabel from 'primevue/floatlabel';
import InputText from 'primevue/inputtext';
import InputGroup from 'primevue/inputgroup';
import InputGroupAddon from 'primevue/inputgroupaddon';
import TreeSelect from 'primevue/treeselect';
import ImageUploader from './ImageUploader.vue';
import { onMounted, onUnmounted, ref, defineEmits } from 'vue';
import { useAstaStore } from '../../stores/astaStore.js';
import { getCategorie } from '../../service/categoriaService.js';

const nodes = ref([]);

const storeInstance = useAstaStore();
const emit = defineEmits('increase-page','decreasse-page');
const selectedCategory = ref(storeInstance.asta.categoria);
const nomeProdotto = ref(storeInstance.asta.nomeProdotto);
const descrizione = ref(storeInstance.asta.descrizione);
const prezzoBase = ref(storeInstance.asta.prezzoBase);


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
    emit('increase-page');
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
