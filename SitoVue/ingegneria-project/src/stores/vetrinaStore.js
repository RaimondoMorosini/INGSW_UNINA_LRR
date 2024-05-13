import { defineStore } from 'pinia';

export const useVetrinaStore = defineStore('Vetrina', {
  state: () => ({
    Vetrina: true,
  }),
  getters:{
    getVetrina() {
        return this.Vetrina;
      },
  },

  actions: {
    setVetrina(Vetrina) {
      this.Vetrina = Vetrina;
    },
    clearVetrina() {
      this.Vetrina = null;
    },
  },
  
});
