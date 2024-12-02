import { defineStore } from 'pinia';
import { getImageInFormData } from '../service/astaService.js';
import { getInfoAstaProdotto } from '../service/PaginaProdottoAstaService.js';

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
                incrementoMinimo: '',
                durataEstensione: '',
                scadenzaAsta: '',
                dataInizio: '',
                immaginiSalvate: [],
                caratteristiche: {},
            };
        },
        async getFormattedData() {
            const formData = await getImageInFormData();
            const categoriaSalvata = Object.keys(this.asta.categoria)[0];
            const file = this.asta.immaginiSalvate;
            /**
             * file.forEach((f) => {
                formData.append('file', srcToFile(f.src, f.name));
            });
             */

            return {
                datiProdotto: {
                    nomeProdotto: this.asta.nomeProdotto,
                    descrizioneProdotto: this.asta.descrizione,
                    immagini: formData,
                    categoriaProdotto: categoriaSalvata,
                    caratteristicheProdotto: Object.entries(this.asta.caratteristiche).map(
                        ([idCaratteristica, valore]) => ({ idCaratteristica, valore })
                    ),
                },
                datiAsta: {
                    baseAsta: parseFloat(this.asta.prezzoBase),
                    dataScadenza: Date.parse(this.asta.scadenzaAsta),
                    dataInizio: 0,
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
