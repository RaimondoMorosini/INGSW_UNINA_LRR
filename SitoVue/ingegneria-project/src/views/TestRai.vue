<script setup>
import BaseListbox from "../components/BaseListBox.vue";
import dropdownCategoria from "../components/CategoriaListBox.vue";
import LogoSito from "../components/LogoSito.vue";
import { watch, ref } from "vue";
import logoIcon from "../components/Icone/logo.vue";



const persone = ref([
  { value: 1, label: "Wade Cooper", hasChild: false },
  { value: 2, label: "Arlene Mccoy", hasChild: false },
  { value: 3, label: "Devon Webb", hasChild: false },
  { value: 4, label: "Tom Cook", hasChild: false },
  { value: 5, label: "Tanya Fox", hasChild: true },
  { value: 6, label: "Hellen Schmidt", hasChild: false },
]);


const ruoli = ref([
  { value: 1, label: "Marketing" },
  { value: 2, label: "Front desk" },
  { value: 3, label: "Support" },
]);

const modulo = ref({
  person_id: null,
  role_ids: [],
});

// Osserva i cambiamenti di `person_id` e mantiene solo l'ultima selezione
function gestisciSelezionePersona(valoriSelezionati) {
  if (valoriSelezionati.length > 0) {
    const ultimoValoreSelezionato = valoriSelezionati[valoriSelezionati.length - 1];
    modulo.value.person_id = [ultimoValoreSelezionato];
  } else {
    // Gestisci il caso in cui tutte le selezioni sono state cancellate
    modulo.value.person_id = [];
  }
}


const categorie = ref([
  {
    nome: 'Elettronica',
    figlie: [
      { nome: 'Smartphone', figlie: [] },
      { nome: 'Laptop', figlie: [] },
      {
        nome: 'Accessori', figlie: [
          { nome: 'Custodie', figlie: [] },
          { nome: 'Caricatori', figlie: [] }
        ]
      },
    ],
  },
  {
    nome: 'Libri', figlie: [
      { nome: 'Fantascienza', figlie: [] },
      { nome: 'Storici', figlie: [] }
    ]
  },
  {
    nome: 'Abbigliamento', figlie: [
      { nome: 'Uomo', figlie: [] },
      { nome: 'Donna', figlie: [] },
      { nome: 'Bambini', figlie: [] }
    ]
  },
]);

// Converti l'elenco delle categorie in opzioni per il BaseListbox
const opzioniCategorie = ref(categorie.value.map(categoria => ({
  label: categoria.nome,
  value: categoria.nome,
  hasChild: categoria.figlie.length > 0,
  hasFather: false,
})));

const idCategoriaSelezionata = ref([]);

function trovaCategoria(nome, categorie) {
  let risultato = null;

  // Itera su ogni categoria
  for (const categoria of categorie) {
    // Se il nome corrisponde, restituisce la categoria
    if (categoria.nome === nome) {
      return categoria;
    }
    // Se la categoria ha delle sottocategorie, cerca ricorsivamente in quelle
    if (categoria.figlie && categoria.figlie.length > 0) {
      risultato = trovaCategoria(nome, categoria.figlie);
      if (risultato) {
        return risultato;
      }
    }
  }

  // Restituisce null se nessuna categoria corrisponde
  return risultato;
}

const storicoOpzioni = ref([]);
const categoriaPadre = ref([]);
function aggiornaSelezioneCategoria(nuovoValore) {
  if (nuovoValore.length === 0) {
    // Se non ci sono selezioni, non fare nulla
    return;
  }
  console.log("Selezione attuale:", nuovoValore); // Visualizza la selezione corrente
  const ultimaSelezione = nuovoValore.at(-1);
  console.log("nuovo VAlore:", nuovoValore); // Visualizza la selezione corrente
  console.log("Ultima selezione:", ultimaSelezione); // Visualizza l'ultima selezione

  if (storicoOpzioni && ultimaSelezione === "indietro" && storicoOpzioni.value.length > 0) {
    // Rimuove e ottiene l'ultimo stato delle opzioni salvato nello storico
    const ultimeOpzioni = storicoOpzioni.value.pop();
    categoriaPadre.value.pop();

    idCategoriaSelezionata.value = [];
    console.log("idCategoriaSelezionata:", idCategoriaSelezionata.value); // Visualizza la categoria selezionata
    console.log("Opzioni storico:", ultimeOpzioni); // Visualizza le opzioni storico
    // Aggiorna le opzioni correnti con l'ultimo stato
    opzioniCategorie.value = ultimeOpzioni;
    return;
  }

  idCategoriaSelezionata.value = [ultimaSelezione];// associa al model solo l'ultima selezione
  console.log("idCategoriaSelezionata:", idCategoriaSelezionata.value); // Visualizza la categoria selezionata
  const categoriaCorrispondente = trovaCategoria(idCategoriaSelezionata.value[0], categorie.value);
  console.log("Categoria corrispondente:", categoriaCorrispondente); // Visualizza la categoria corrispondente

  if (categoriaCorrispondente && categoriaCorrispondente.figlie.length > 0) {
    if (categoriaPadre.value.at(-1)?.nome !== categoriaCorrispondente.nome) {
      categoriaPadre.value.push(categoriaCorrispondente);
      console.log("categoriaPadre:", categoriaPadre.value); // Visualizza la categoria corrispondente
          //salva
          storicoOpzioni.value.push([...opzioniCategorie.value]);
          // Aggiorna le opzioni
          opzioniCategorie.value = [{ label: "indietro", value: "indietro" },
          { label: categoriaCorrispondente.nome, value: categoriaCorrispondente.nome, hasChild: true},
          ...categoriaCorrispondente.figlie.map(figlio => ({
            label: figlio.nome,
            value: figlio.nome,
            hasChild: figlio.figlie.length > 0,
          }))
          ];
          console.log("Nuove opzioniCategorie:", opzioniCategorie.value); // Visualizza le nuove opzioni
    } else {
      console.log("stessa categoria");
    }
  
} else {
  // Se non ci sono figli  

}

}


</script>
<template >
  <div class="mx-auto  w-3col">

    <form class="flex flex-col space-y-6">
      <BaseListbox placeholder="Seleziona persona" v-model="modulo.person_id" :options="persone" />
      <BaseListbox placeholder="Seleziona ruoli" v-model="modulo.role_ids" :options="ruoli" multiple />

      <BaseListbox placeholder="Seleziona categoria" v-model="idCategoriaSelezionata" :options="opzioniCategorie"
        @update:modelValue="aggiornaSelezioneCategoria" multiple />
        <dropdownCategoria placeholder="Seleziona categoria" v-model="idCategoriaSelezionata" :options="opzioniCategorie"
        @update:modelValue="aggiornaSelezioneCategoria" multiple />
    
      </form>
      <logoIcon></logoIcon>
      <LogoSito/>
      

      <div class="flex justify-between ...">
  <div>01</div>
  <div>02</div>
</div>
  </div>

</template>
