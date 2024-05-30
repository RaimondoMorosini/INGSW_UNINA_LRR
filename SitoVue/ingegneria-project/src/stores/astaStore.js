import { defineStore } from 'pinia';

export const useAstaStore = defineStore('asta', {
    state: () => ({
        asta: {
            step: 0,
            tipoAsta: 'asta_inglese',
            nomeProdotto: '',
            descrizione: '',
            prezzoBase: '',
            categoria: '',
            filtri: [],
            incrementoMinimo: '',
            durataEstensione: '',
            scadenzaAsta: '',
            immaginiSalvate: [],
            caratteristiche: {},

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
        getFormattedData() {
            const categoriaSalvata = Object.keys(this.asta.categoria)[0];
            const immagini = this.asta.immaginiSalvate.value.map(item => item.file);
            return {
                datiProdotto: {
                    nomeProdotto: this.asta.nomeProdotto,
                    descrizioneProdotto: this.asta.descrizione,
                    immagini: immagini,
                    categoriaProdotto: categoriaSalvata,
                    caratteristicheProdotto: Object.entries(this.asta.caratteristiche).map(([idCaratteristica, valore]) => ({ idCaratteristica, valore })),
                },
                datiAsta: {
                    baseAsta: parseFloat(this.asta.prezzoBase),
                    dataScadenza: Date.parse(this.asta.scadenzaAsta),
                    dataInizio: Date.parse(this.asta.dataInizio),
                    tipoAsta: this.asta.tipoAsta,
                    datiExtraJson: JSON.stringify({
                        tempoEstensione: parseFloat(this.asta.durataEstensione),
                        quotaFissaPerLaPuntata: parseFloat(this.asta.incrementoMinimo),
                        astaId: 8, 
                    }),
                },
            };
        },
    },

    persist: true,
    //persist: {
    //   storage: sessionStorage, // data in sessionStorage is cleared when the page session ends.
    // },
});
