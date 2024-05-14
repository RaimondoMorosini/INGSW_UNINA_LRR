import { defineStore } from 'pinia';

export const useAstaStore = defineStore('asta', {
    state: () => ({
        asta: {
            step: 0,
            tipoAsta: 'Inglese',
            nomeProdotto: '',
            descrizione: '',
            prezzoBase: '',
            categoria: '',
            incrementoMinimo: '',
            durataEstensione: '',
            scadenzaAsta: '',
        },
    }),
    getters: {
        getAsta() {
            return asta;
        },
    },
    actions: {
        updateAsta(newData) {
            this.asta = { ...this.asta, ...newData };
        },
    },

    persist: true,
});
