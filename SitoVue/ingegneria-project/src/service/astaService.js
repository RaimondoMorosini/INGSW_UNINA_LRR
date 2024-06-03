import {postRestWithtoken} from '../scripts/RestUtils.js';
import {useAstaStore} from '../stores/astaStore.js'

export async function creaAsta() {
    const datiAsta =useAstaStore().getFormattedData();
        
    if (datiAsta.datiProdotto.immagini.length === 0) {
        throw new Error("Inserire almeno un'immagine");
    }
    console.log('immagini:', datiAsta.datiProdotto.immagini);
    console.log('datiAsta:', datiAsta); 
    try {
        const response = await postRestWithtoken('asta/creaasta', datiAsta);
        return response;
    } catch (error) {
        console.error("Errore durante la creazione dell'asta:", error);
        throw new Error("Impossibile creare l'asta");
    }   

}