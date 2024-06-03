import { postRest } from './RestUtils.js';



// Funzione per convertire Base64 in Blob
function base64ToBlob(base64) {
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
    console.log('base64:', base64String);


    // Convertire la stringa base64 in un Blob
    let byteCharacters = atob(base64String);
    let byteNumbers = new Array(byteCharacters.length);
    for (let i = 0; i < byteCharacters.length; i++) {
        byteNumbers[i] = byteCharacters.charCodeAt(i);
    }
    let byteArray = new Uint8Array(byteNumbers);
    let tipo = base64.startsWith('data:image/png;base64,') ? 'image/png' : 'image/jpeg';
    let blob = new Blob([byteArray], {type: tipo});

    return blob;
}









export async function uploadImages(file) {
    try {
        console.log('uploadImages file:', file);
        const formData = new FormData();
        file.forEach((f) => {
            console.log('f:', f);
            // La tua stringa Base64
            const base64Data = f.src;
            console.log('base64Data:', base64Data);
            // Nome del file (puoi cambiarlo in base alle tue esigenze)
            const nomeFile = f.name;

            // Convertire la stringa Base64 in Blob
            const blob = base64ToBlob(base64Data);

            // Creare un oggetto File (opzionale)
            const superfile = new File([blob], nomeFile, { type: 'image/jpeg' });

            console.log('file:', superfile);
            formData.append('file', superfile);
        });
        const response = postRest('public/asta/uploadImages', formData);
        return response;
    } catch (error) {
        console.error("Errore durante il caricamento dell'immagine:", error);
        throw new Error("Impossibile caricare l'immagine");
    }
}




