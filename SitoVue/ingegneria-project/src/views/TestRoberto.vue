<template>
    <div>
        <input type="file" ref="fileInput" @change="handleFileInputChange" />
        <button @click="caricaImmagine">Carica</button>
        <div v-if="immagineCaricata">
            <img :src="immagineCaricata" alt="Immagine Caricata" />
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { postRestWithtoken } from '../scripts/RestUtils.js';

// Variabile per memorizzare l'URL dell'immagine caricata
const immagineCaricata = ref(null);
const fileSelezionato = ref(null);

// Gestisce il cambiamento dell'input file
const handleFileInputChange = (event) => {
    const file = event.target.files[0];
    fileSelezionato.value = file;
    const reader = new FileReader();

    reader.onload = (e) => {
        // Legge il file come URL di dati
        immagineCaricata.value = e.target.result;
    };

    // Legge il file come URL di dati
    reader.readAsDataURL(file);
};

// Gestisce il click sul bottone Carica
const caricaImmagine = () => {
    // In questo caso, potresti voler implementare il codice per inviare l'immagine al server
    // e gestire la risposta qui
    console.log('Immagine caricata:', immagineCaricata.value);

    if (immagineCaricata.value === null) {
        alert("Seleziona un'immagine prima di caricare");
        return;
    }

    const formData = new FormData();
    formData.append('file', fileSelezionato.value);

    let inputAstaProdotto = {
        datiProdotto: {
            nomeProdotto: 'Smartphone di VUE',
            descrizioneProdotto: 'Un potente smartphone con fotocamera migliorata',
            immagini: [JSON.stringify(formData)],
            categoriaProdotto: 'Telefonia',
            caratteristicheProdotto: [
                {
                    idCaratteristica: 0,
                    valore: 'android',
                },
                {
                    idCaratteristica: 2,
                    valore: '64GB',
                },
            ],
        },
        datiAsta: {
            baseAsta: 200.0,
            dataScadenza: 1767170400000,
            dataInizio: 1767084000000,
            tipoAsta: 'asta_inglese',
            datiExtraJson: '{"tempoEstensione":10,"quotaFissaPerLaPuntata":1.0,"astaId":8}',
        },
    };

    postRestWithtoken('asta/creaasta', inputAstaProdotto);
};
</script>
