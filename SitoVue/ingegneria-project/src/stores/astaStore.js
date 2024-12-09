import { defineStore } from 'pinia';
import { getImageInFormData } from '../service/astaService.js';
import { getInfoAstaProdotto } from '../service/PaginaProdottoAstaService.js';
const dataMinima = new Date();
dataMinima.setDate(dataMinima.getDate() + 1); // Data minima: domani
export const useAstaStore = defineStore('asta', {
    state: () => ({
        asta: {
            tipoAsta: 'asta_inversa',
            nomeProdotto: '',
            descrizione: '',
            categoria: '',
            filtri: [],
            prezzoBase: '',
            incremento: '',
            durataEstensione: '',
            scadenzaAsta: dataMinima.getTime(),
            dataInizio: '',
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
        deleteAsta() {
            this.asta = {
                step: 0,
                tipoAsta: 'asta_inglese',
                nomeProdotto: '',
                descrizione: '',
                prezzoBase: '',
                categoria: '',
                filtri: [],
                incremento: '',
                durataEstensione: '',
                scadenzaAsta: '',
                dataInizio: '',
                immaginiSalvate: [],
                caratteristiche: {},
            };
        },
        async getFormattedData() {
            const formData = await getImageInFormData();
            const file = this.asta.immaginiSalvate;

            return {
                datiProdotto: {
                    nomeProdotto: this.asta.nomeProdotto,
                    descrizioneProdotto: this.asta.descrizione,
                    immagini: formData,
                    categoriaProdotto: this.asta.categoria,
                    caratteristicheProdotto: Object.entries(this.asta.caratteristiche).map(
                        ([idCaratteristica, valore]) => ({ idCaratteristica, valore })
                    ),
                },
                datiAsta: {
                    baseAsta: parseFloat(this.asta.prezzoBase),
                    dataScadenza: Date.parse(this.asta.scadenzaAsta),
                    dataInizio: new Date(this.asta.dataInizio).getTime(),
                    tipoAsta: this.asta.tipoAsta,
                    datiExtraJson: JSON.stringify({
                        tempoEstensione: parseFloat(this.asta.durataEstensione),
                        quotaFissaPerLaPuntata: parseFloat(this.asta.incremento),
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

// Definizione dello store Pinia
export const useAstaChacheStore = defineStore('astaChacheStore', {
    state: () => ({
        asteCache: {}, // Struttura dati per memorizzare le aste con chiave idAsta
    }),

    actions: {
        // Metodo per ottenere un'asta con verifica della cache
        async getAstaById(idAsta) {
            // Controllo se l'asta è già presente nella cache
            if (!this.asteCache[idAsta]) {
                // Se non è presente, recupera i dati e aggiungili alla cache
                const asta = await getInfoAstaProdotto(idAsta);
                this.asteCache[idAsta] = asta;
            }
            // Restituisci l'asta dalla cache
            return this.asteCache[idAsta];
        },

        // Metodo per aggiornare manualmente la cache (ad esempio, per ricaricare i dati di un'asta)
        async aggiornaAsta(idAsta) {
            const asta = await getInfoAstaProdotto(idAsta);
            this.asteCache[idAsta] = asta;
        },
    },

    getters: {
        // Getter per controllare se un'asta è già nella cache
        isAstaInCache: (state) => (idAsta) => !!state.asteCache[idAsta],
    },
});
