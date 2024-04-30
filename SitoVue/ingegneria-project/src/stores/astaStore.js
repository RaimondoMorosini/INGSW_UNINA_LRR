import { defineStore } from 'pinia';

export const useAstaStore = defineStore('asta', {
  state: () => ({
    descrizione : null,
    nomeProdotto : null,
    prezzoBase : null
  }),
  getters
  : {
    getAsta(){
      
    }
  },
  
  actions: {
    clearAsta() {
      this.descrizione = null;
    },
    setInfoProdotto(descrizione,nomeProdotto,prezzoBase){
      this.descrizione=descrizione;
      this.nomeProdotto=nomeProdotto;
      this.prezzoBase=prezzoBase;
    },
  },
});