import { defineStore } from 'pinia';

export const useVetrinaStore = defineStore('Vetrina', {
  state: () => ({
    Vetrina: null,
  }),
  getters:{
    getVetrina() {
        return this.Vetrina;
      },
  },

  actions: {
    setAccessVetrina(Vetrina) {
      this.Vetrina = Vetrina;
    },
    clearAccessVetrina() {
      this.Vetrina = null;
    },
  },
  persist : true,
});
