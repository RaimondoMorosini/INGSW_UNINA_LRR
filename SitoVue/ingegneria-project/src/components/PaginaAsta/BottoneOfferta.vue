<template>
  <div class="container">
    <div class="card">
      <h2 class="title">{{ titoloAsta }}</h2>

      <div v-if="tipoAsta === TipoAsta.INGLESE" class="content">
        <p class="info text-base">{{ testoPrezzo }} <span>{{ prezzoAttuale }}€</span></p>
        <button @click="aumentaOfferta" class="button green text-xl">PUNTA</button>
        <p class="info text-base">Incremento per ogni puntata:  <span class="font-bold">{{ incrementoOfferta }}€</span></p>
      </div>

      <div v-else-if="tipoAsta === TipoAsta.INVERSA" class="content">
        <p class="info">Il prezzo attuale è: <span class="highlight">{{ prezzoAttuale }}€</span></p>
        <p class="info">Inserisci un'offerta inferiore al prezzo attuale:</p>
        <input v-model="nuovaOfferta" type="number" class="input" placeholder="Inserisci la tua offerta" />
        <button @click="inviaOffertaInversa" class="button blue">Invia Offerta</button>
      </div>

      <div v-else-if="tipoAsta === TipoAsta.SILENZIOSA" class="content">
        <p class="info">La base d'asta è: <span class="highlight">{{ baseAsta }}€</span></p>
        <p class="info">Offri quanto vuoi, purché sia superiore alla base d'asta:</p>
        <input v-model="nuovaOfferta" type="number" class="input" placeholder="Inserisci la tua offerta" />
        <button @click="inviaOffertaSilenziosa" class="button purple">Invia Offerta</button>
      </div>

      <div v-if="errore" class="error">{{ errore }}</div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, watch } from 'vue';
import { defineProps } from 'vue';
import { TipoAsta } from '../../service/astaService.js';

const props = defineProps({
  tipoAsta: {
    type: String,
    required: true
  },
  prezzoAttuale: {
    type: Number,
    required: true
  },
  baseAsta: {
    type: Number,
    required: false
  },
  incrementoOfferta: {
    type: Number,
    default: 10
  },
  faiOfferta: {
    type: Function,
    required: true
  }

});

//console.log("offerta: ", props.offerta);

const nuovaOfferta = ref(0);
const errore = ref('');

// Variabili reattive per lo stile CSS
const coloreOffertaUtente = ref('green');
const sizeTestoPrezzo = ref('large');
const fontWeight = ref('600');
const testoPrezzo = ref('Prezzo attuale:')


const titoliAsta = {
  [TipoAsta.INGLESE]: 'Asta Inglese',
  [TipoAsta.INVERSA]: 'Asta Inversa',
  [TipoAsta.SILENZIOSA]: 'Asta Silenziosa'
};

const titoloAsta = computed(() => {
  return titoliAsta[props.tipoAsta] || 'Asta';
});

const aumentaOfferta = () => {
  nuovaOfferta.value = props.prezzoAttuale + props.incrementoOfferta;
  props.faiOfferta(nuovaOfferta.value).catch(err => {
    errore.value = "Errore durante l'invio dell'offerta."+err;
  });
};

const inviaOffertaInversa = () => {
  if (nuovaOfferta.value < props.prezzoAttuale) {
    errore.value = '';
    props.faiOfferta(nuovaOfferta.value).catch(err => {
      errore.value = "Errore durante l'invio dell'offerta.";
    });
  } else {
    errore.value = "L'offerta deve essere inferiore al prezzo corrente.";
  }
};

const inviaOffertaSilenziosa = () => {
  if (nuovaOfferta.value > props.baseAsta) {
    errore.value = '';
    props.faiOfferta(nuovaOfferta.value).catch(err => {
      errore.value = "Errore durante l'invio dell'offerta.";
    });
  } else {
    errore.value = "L'offerta deve essere superiore alla base d'asta.";
  }
};

// Animazione refresh puntata asta inglese
watch(() => props.prezzoAttuale, (newVal) => {
  console.log("Entro nel wathccccc");
  changeStyle();
});

// Funzione di animazione reflesh asta inglese
const changeStyle = () => {
  coloreOffertaUtente.value = 'red';
  sizeTestoPrezzo.value = 'xx-large';
  testoPrezzo.value = 'Nuova offerta:';
  setTimeout(() => {
    coloreOffertaUtente.value = 'black';
    sizeTestoPrezzo.value = 'large';
    testoPrezzo.value = 'Prezzo attuale:';
  }, 2000); // Pausa di 2000 millisecondi (2 secondi)
  
};

</script>

<style scoped>
.container {
  padding: 1.5rem;
  background-color: #f7fafc;
  min-height: 100vh;
}
.card {
  max-width: 28rem;
  margin: 0 auto;
  background-color: #fff;
  box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05);
  border-radius: 0.5rem;
  overflow: hidden;
}
.title {
  font-size: 1.5rem;
  font-weight: bold;
  text-align: center;
  background-color: #4299e1;
  color: #fff;
  padding: 1rem;
}
.content {
  padding: 1rem;
  text-align: center;
}
.info {

  color: #4a5568;
}

.dynamic-text {
  transition: all 0.3s ease; /* Aggiungi transizioni per vedere meglio il cambiamento */
}

.input {
  border: 1px solid #e2e8f0;
  border-radius: 0.375rem;
  padding: 0.5rem;
  width: 100%;
  margin-top: 0.5rem;
  focus: ring;
  focus: ring-blue-500;
}
.button {
  margin-top: 1rem;
  padding: 0.5rem 1rem;
  border-radius: 9999px;
  color: #fff;
  width: 250px; /* Larghezza */
}
.green {
  background-color: #48bb78;
}
.green:hover {
  background-color: #38a169;
}
.blue {
  background-color: #4299e1;
}
.blue:hover {
  background-color: #3182ce;
}
.purple {
  background-color: #9f7aea;
}
.purple:hover {
  background-color: #805ad5;
}
.error {
  padding: 1rem;
  background-color: #fed7d7;
  color: #c53030;
  text-align: center;
  margin-top: 1rem;
}
</style>