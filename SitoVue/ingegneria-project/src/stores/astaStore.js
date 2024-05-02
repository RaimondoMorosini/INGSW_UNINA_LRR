import { defineStore } from 'pinia';

export const useAstaStore = defineStore('asta', {
  state: () => ({
    asta: {
      nameProdotto: '',
      descrizione: '',
      prezzoBase: 0,
    },
  }),
  actions:{
    updateAsta(newData){
      this.asta = {...this.asta, ...newData};
    },
  }
});