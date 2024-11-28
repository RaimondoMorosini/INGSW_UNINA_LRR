import { defineStore } from 'pinia';

export const usaAstaStore = defineStore('astaStore', {
    state: () => ({
        aste: [],
        filtroRicerca: '',
        filtroTipoAsta: [], // Cambiato in array
        filtroScadenza: [], // Cambiato in array
        ordine: 'dataScadenza',
        paginaCorrente: 1,
        elementiPerPagina: 5,
    }),
    getters: {
        asteFiltrate(state) {
            let asteFiltrate = state.aste;

            // Filtra per ricerca
            if (state.filtroRicerca) {
                asteFiltrate = asteFiltrate.filter((asta) =>
                    asta.nome.toLowerCase().includes(state.filtroRicerca.toLowerCase())
                );
            }

            // Filtra per tipo d'asta
            if (state.filtroTipoAsta.length > 0) {
                asteFiltrate = asteFiltrate.filter((asta) =>
                    state.filtroTipoAsta.includes(asta.tipoAsta)
                );
            }

            // Filtra per scadenza
            const oraAttuale = Date.now();
            if (state.filtroScadenza.includes('scadute')) {
                asteFiltrate = asteFiltrate.filter((asta) => asta.dataScadenza < oraAttuale);
            }
            if (state.filtroScadenza.includes('non_scadute')) {
                asteFiltrate = asteFiltrate.filter((asta) => asta.dataScadenza >= oraAttuale);
            }

            // Ordina
            if (state.ordine === 'prezzo') {
                asteFiltrate = asteFiltrate.sort((a, b) => a.prezzoAttuale - b.prezzoAttuale);
            } else if (state.ordine === 'dataScadenza') {
                asteFiltrate = asteFiltrate.sort((a, b) => a.dataScadenza - b.dataScadenza);
            }

            return asteFiltrate;
        },
        asteVisibili(state) {
            const inizio = (state.paginaCorrente - 1) * state.elementiPerPagina;
            const fine = inizio + state.elementiPerPagina;
            return this.asteFiltrate.slice(inizio, fine);
        },
        numeroPagine() {
            return Math.ceil(this.asteFiltrate.length / this.elementiPerPagina);
        },
    },
    actions: {
        impostaAste(nuoveAste) {
            this.aste = nuoveAste;
        },
        aggiornaFiltroRicerca(testo) {
            this.filtroRicerca = testo;
        },
        aggiornaFiltroTipoAsta(tipi) {
            this.filtroTipoAsta = tipi;
        },
        aggiornaFiltroScadenza(tipiScadenza) {
            this.filtroScadenza = tipiScadenza;
        },
        aggiornaOrdine(tipoOrdine) {
            this.ordine = tipoOrdine;
        },
        cambiaPagina(pagina) {
            this.paginaCorrente = pagina;
        },
    },
});
