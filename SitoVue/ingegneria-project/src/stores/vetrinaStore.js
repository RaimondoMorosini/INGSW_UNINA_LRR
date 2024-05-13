import { defineStore } from 'pinia';

export const useVetrinaStore = defineStore('Vetrina', {
  state: () => ({
    Vetrina: null,
  }),
  actions: {
    getVetrina() {
      return this.Vetrina;
    },
    setAccessVetrina(Vetrina) {
      this.Vetrina = Vetrina;
    },
    clearAccessVetrina() {
      this.Vetrina = null;
    },
  },
  persist : true,
});
