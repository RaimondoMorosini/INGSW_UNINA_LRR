import { defineStore } from 'pinia';

export const useAstaStore = defineStore('asta', {
  state: () => ({
    asta: {
      tipoAsta: '',
      nomeProdotto: '',
      descrizione: '',
      prezzoBase: 0,
      categoria: '',
      incrementoMinimo: 0,
      durataEstensione: 0,
      scadenzaAsta: '',
    },
  }),
  actions:{
    updateAsta(newData){
      this.asta = {...this.asta, ...newData};
    },
  }
});