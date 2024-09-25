import {postRestWithtoken,postRest} from '../scripts/RestUtils.js';
import {useAstaStore} from '../stores/astaStore.js'
import { salvaImaginiAsta } from '../scripts/ImageUploadService.js';

function srcToFile(base64, nomeFile) {
    // Verifica e rimuovi il prefisso data:image
    let base64String = base64;
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


export async function creaAsta() {
    const datiAsta =await useAstaStore().getFormattedData();
    console.log('datiAsta:\n', datiAsta);
    datiAsta.datiProdotto.immagini=[];
    try {
        const response = await postRestWithtoken('asta/creaasta', datiAsta);
        console.log('log ora recupero idasta e salvo le img:', response);
        const idAsta = response.idAsta;
        await salvaImaginiAsta(idAsta);

        return response;
    } catch (error) {
        console.error("Errore durante la creazione dell'asta:", error);
        throw new Error("Impossibile creare l'asta");
    }   

}

export async function getImageInFormdata(){
   const formData = new FormData();
    useAstaStore().asta.immaginiSalvate.forEach((f) => {
        console.log('f:', f);
        // La tua stringa Base64
        const base64Data = f.src;
        // Nome del file (puoi cambiarlo in base alle tue esigenze)
        const nomeFile = f.name;

        // Convertire la stringa Base64 in un File
        const file1= srcToFile(f.src, f.name);

        formData.append('files', file1, f.name);
 
    });
    return formData;

}