<template>
    <div class="flex h-4col w-[100%] auto-rows-max flex-col justify-between gap-3 px-2">
        <div class="formSpace pt-6">
            <FloatLabel>
                <InputText
                    class="w-[60%] rounded md:w-[70%]"
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
                    class="min-h-[10rem] w-[60%] rounded md:w-[70%]"
                    v-model="descrizione"
                />
                <label for="descrizione">Descrizione Prodotto</label>
            </FloatLabel>
        </div>
        <div class="formSpace pt-6">
            <FloatLabel>
                <InputNumber
                    class="w-[60%] rounded md:w-[70%]"
                    id="prezzoBase"
                    v-model="prezzoBase"
                />
                <label for="prezzoBase">Prezzo Base</label>
            </FloatLabel>
        </div>

        <InputGroup class="categoriaSelector w-[100%] pt-6">
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
</template>

<script setup>
import InputNumber from 'primevue/inputnumber';
import FloatLabel from 'primevue/floatlabel';
import InputText from 'primevue/inputtext';
import InputGroup from 'primevue/inputgroup';
import InputGroupAddon from 'primevue/inputgroupaddon';
import TreeSelect from 'primevue/treeselect';
import CreaProdotto from '../components/stepper/formCreaProdotto.vue';
</script>
