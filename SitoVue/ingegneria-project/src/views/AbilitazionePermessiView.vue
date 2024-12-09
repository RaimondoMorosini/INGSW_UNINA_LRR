<template>
    <Card class="m-3">
        <template #title>
            <h2>Richiesta di permessi di venditore</h2> 
        </template>
        <template #subtitle>
            Inserire i dati per effettuare la richiesta per diventare un compratore.
        </template>
        
        <template #content>
            <form @submit.prevent="submitForm">
            <div class="p-field text-primario-500">
                <FloatLabel variant="on">
                    <InputText :invalid="invalid" fluid id="name" v-model="seller.piva" required />
                    <label for="name">Partita Iva</label>    
                </FloatLabel>
            </div>
            <div class="p-field">
                <FloatLabel  variant="on">
                    <InputText :invalid="invalid" fluid id="email" v-model="seller.fiscalCode" required />
                    <label for="email">Codice Fiscale</label>
                </FloatLabel>
            </div>
            <div class="p-field">
                <FloatLabel  variant="on">
                <InputText fluid id="phone" v-model="seller.phone" required />
                <label for="phone">Numero di Telefono</label>
            </FloatLabel>
            </div>
            <div class="p-field">
                <FloatLabel variant="on">
                <InputText fluid id="address" v-model="seller.azienda" required />
                <label for="address">Nome Azienda</label>
            </FloatLabel>
            </div>
            <Button fluid label="Submit" type="submit" />
        </form>
        </template> 
    </Card>
</template>

<script setup>
import { ref } from 'vue';
import FloatLabel from 'primevue/floatlabel';
import  InputText  from 'primevue/inputtext';
import  Button  from 'primevue/button';
import Card from 'primevue/card'
import {isValidFiscalCode} from '../scripts/codiceFiscaleValidatorScript'
import {isValidPartitaIVA} from '../scripts/PartitaIvaValidator.js'

const seller = ref({
    piva: '',
    fiscalCode: '',
    phone: '',
    azienda: ''
});

const invalid = ref(false);

const submitForm = () => {
    if(!isValidFiscalCode(seller.fiscalCode)){
        
        invalid.value=true;
        return
    }
    if(!isValidPartitaIVA(seller.piva)){
        
        invalid.value=true;
        return
    }
    console.log(seller.value);
    invalid.value=false
    // Add your form submission logic here
};
</script>

<style scoped>
.p-field{
    margin-bottom: 1rem;
}
</style>