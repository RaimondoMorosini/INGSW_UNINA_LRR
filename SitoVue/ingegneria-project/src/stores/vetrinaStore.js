import { defineStore } from 'pinia';

export const useVetrinaStore = defineStore('Vetrina', {
    state: () => ({
        vetrinaShare: true,
    }),
    getters: {
        getVetrina() {
            return this.vetrinaShare;
        },
    },

    actions: {
        setAccessVetrina(statoVetrina) {
            this.vetrinaShare = statoVetrina;
        },
        clearAccessVetrina() {
            this.vetrinaShare = null;
        },
    },
});
