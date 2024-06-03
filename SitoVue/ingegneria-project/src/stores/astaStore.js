import { defineStore } from 'pinia';


// Funzione per convertire Base64 in Blob
function srcToFile(base64, nomeFile) {
    // Verifica e rimuovi il prefisso data:image
    let base64String = base64;
    console.log('base64:', base64);
    if (base64.startsWith('data:image/jpeg;base64,')) {
        base64String = base64.replace(/^data:image\/jpeg;base64,/, '');
    } else if (base64.startsWith('data:image/png;base64,')) {
        base64String = base64.replace(/^data:image\/png;base64,/, '');
    } else {
        // Gestisci altri tipi di immagine o restituisci un errore
        throw new Error('Tipo di immagine non supportato');
    }

    // Convertire la stringa base64 in un Blob
    let byteCharacters = atob(base64String);
    let byteNumbers = new Array(byteCharacters.length);
    for (let i = 0; i < byteCharacters.length; i++) {
        byteNumbers[i] = byteCharacters.charCodeAt(i);
    }
    let byteArray = new Uint8Array(byteNumbers);
    let tipo = base64.startsWith('data:image/png;base64,') ? 'image/png' : 'image/jpeg';
    let blob = new Blob([byteArray], {type: tipo});

    return new File([blob], nomeFile, { type: 'image/jpeg' })
}

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
            const formData = new FormData();
            const file = this.asta.immaginiSalvate;
            file.forEach((f) => {
            formData.append('file', srcToFile(f.src, f.name));
                });
            
                console.log('astastore getformatted formData:', formData.getAll('file'));

            return {
                datiProdotto: {
                    nomeProdotto: this.asta.nomeProdotto,
                    descrizioneProdotto: this.asta.descrizione,
                    immagini: formData,
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
