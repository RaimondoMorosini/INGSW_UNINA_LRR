<template>
  <div>NOn ti mostro il value gne gne</div>
</template>

<script setup>
import { onMounted } from 'vue'

// Definizione delle props
const props = defineProps({
  value: String
})

// Funzione per verificare se il value inizia con una vocale
const iniziaConVocale = (value) => {
  // Definisci un'espressione regolare per le vocali
  const regexVocale = /^[aeiou]/i // "i" rende l'espressione regolare insensibile al caso (case-insensitive)
  return regexVocale.test(value)
}

// Utilizzo di onMounted per eseguire il controllo una volta che il componente è montato
onMounted(() => {
  if (props.value && iniziaConVocale(props.value)) {
    //alert("Il value inizia con una vocale!");
  }
})
</script>
