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
      this.token = null;
    },
  },
});