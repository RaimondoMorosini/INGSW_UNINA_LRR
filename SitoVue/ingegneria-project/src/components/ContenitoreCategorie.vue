<template>
  <div class="contenitoreEtichetta">
    <p :style="{ color: colorEtichetta }">IN QUALE CATEGORIA?</p>
  </div>
  <div
    class="barraCategorie"
    :style="{ border: coloreBordo }"
    @mouseover="hoverBarra(true)"
    @mouseleave="hoverBarra(false)"
  >
    <div class="outputBarra" @click="gestioneTendina">
      <img src="../assets/Icon/TutteLeCategorie.svg" class="iconaTutteCategorie" />
      <label class="labelCategoria">{{ testoBarra }}</label>
      <img src="../assets/Icon/frecciaGiu.svg" class="iconaFrecciaGiu" />
    </div>
    <div v-if="tendina" class="tendina">
      <div class="navigazione">
        <label v-if="padreCategoria === ''" @click="updateTestoBarra('Tutte le categorie', -1)"
          >Tutte le categorie</label
        >
        <div v-else class="indietro" @click="updateTestoBarra(padreCategoria, -2)">
          <img src="../assets/Icon/frecciaSinistra.svg" class="iconaFrecciaSinistra" />
        </div>
        <div class="lineaSeparatoria"></div>
      </div>
      <div
        v-if="padreCategoria !== ''"
        class="sceltaCategoria"
        @click="updateTestoBarra(padreCategoria, -3)"
      >
        {{ padreCategoria }}
      </div>
      <div
        v-for="(categoria, index) in categorie"
        class="sceltaCategoria"
        @click="updateTestoBarra(categoria.nome, index)"
      >
        <label>{{ categoria.nome }}</label>
        <img
          v-if="categoria.figlie.length > 0"
          src="../assets/Icon/frecciaDestra.svg"
          class="iconaFrecciaDestra"
        />
      </div>
    </div>
  </div>
</template>

<script setup lang="js">
import ref from 'vue'
import categorie from '../script/getCategorie.js'
import { trovaGenitore } from '../script/getCategorie.js'
import { trovaStringaGenitore } from '../script/getCategorie.js'

testoBarra = ref('Categorie')
tendina = ref(false)
categorie = ref(categorie)
padreCategoria = ref('')
colorEtichetta = ref('black')
coloreBordo = ref('2px solid black')

function gestioneTendina() {
  this.tendina = !this.tendina
}

function updateTestoBarra(categoriaSelected, index) {
  this.testoBarra = categoriaSelected
  if (index >= 0) {
    if (this.categorie[index].figlie.length > 0) {
      this.categorie = this.categorie[index].figlie
      this.padreCategoria = categoriaSelected
    } else {
      this.tendina = !this.tendina
      this.colorEtichetta = 'black'
      this.coloreBordo = '2px solid black'
    }
  } else if (index === -1) {
    this.tendina = !this.tendina
    this.categorie = categorie
    this.padreCategoria = ''
    this.colorEtichetta = 'black'
    this.coloreBordo = '2px solid black'
  } else if (index === -3) {
    this.tendina = !this.tendina
    this.colorEtichetta = 'black'
    this.coloreBordo = '2px solid black'
  } else {
    this.categorie = trovaGenitore(categorie, this.padreCategoria)
    let risultatoPadre = trovaStringaGenitore(categorie, this.padreCategoria)
    if (risultatoPadre !== null) {
      this.padreCategoria = risultatoPadre
    } else {
      this.padreCategoria = ''
    }
  }
}

function hoverBarra(stato) {
  if (stato) {
    this.colorEtichetta = '#0071fe'
    this.coloreBordo = '2px solid #0071fe'
  } else {
    this.colorEtichetta = 'black'
    this.coloreBordo = '2px solid black'
  }
}
</script>

<style scoped>
.barraCategorie {
  color: rgb(215, 14, 14);
  width: 16rem;
  height: 3rem;
  margin-top: 0.1rem;
  margin-left: auto;
  margin-right: auto;
  border-radius: 1rem;
  border: 2px solid black;
  cursor: pointer;
}

.outputBarra {
  display: flex;
  padding: 1rem;
  width: 100%;
  height: 100%;
  color: #9e9e9e;
}

.outputBarra:hover {
  color: black;
}

.iconaTutteCategorie {
  width: 1rem;
  height: 1rem;
  margin-right: 0.5rem;
}

.labelCategoria {
  font-size: 70%;
  font: Inter;
  /*color: #9E9E9E;*/
}

.iconaFrecciaGiu {
  width: 1rem;
  height: 1rem;
  margin-left: auto;
}

.tendina {
  width: 70%;
  height: auto;
  margin-top: 0.1rem;
  margin-left: 4.6rem;
  border: 2px solid black;
  cursor: pointer;
}

.navigazione {
  width: 100%;
  height: 2rem;
  cursor: pointer;
  color: #9e9e9e;
  font: Inter;
  cursor: pointer;
  position: relative;
}

.navigazione:hover {
  background-color: #9e9e9e;
  color: black;
}

.indietro {
  width: 100%;
  height: 100%;
}

.iconaFrecciaSinistra {
  width: 1rem;
  height: 1rem;
  margin-right: auto;
}

.lineaSeparatoria {
  width: 100%;
  height: 1px;
  background-color: black;
  position: absolute;
  bottom: 0;
}

.sceltaCategoria {
  width: 100%;
  height: 2rem;
  cursor: pointer;
  color: #9e9e9e;
  font: Inter;
  cursor: pointer;
  text-align: left;
  display: flex;
}

.sceltaCategoria:hover {
  background-color: #9e9e9e;
  color: black;
}

label {
  cursor: pointer;
}

.iconaFrecciaDestra {
  width: 1rem;
  height: 1rem;
  margin-left: auto;
}

.contenitoreEtichetta {
  width: 16rem;
  height: 3rem;
  margin-top: 1rem;
  margin-left: auto;
  margin-right: auto;
  display: flex;
}

p {
  margin-top: auto;
}
</style>
