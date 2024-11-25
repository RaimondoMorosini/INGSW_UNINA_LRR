import { postRest, postRestWithtoken } from './RestUtils.js';
import { getImageInFormData } from '../service/astaService.js';

export async function uploadImages() {
    try {
        const idAsta = 66;
        console.log('salvo img con idAsta:', idAsta);
        const formData = await getImageInFormdata();
        formData.append('idAsta', idAsta);
        const response = postRestWithtoken('asta/AggiornaImgAsta', formData);
        return response;
    } catch (error) {
        console.error("Errore durante il caricamento dell'immagine:", error);
        throw new Error("Impossibile caricare l'immagine");
    }
}

export async function salvaImaginiAsta(idAsta) {
    try {
        console.log('salvo img con idAsta:', idAsta);
        const formData = await getImageInFormData();
        formData.append('idAsta', idAsta);
        const response = postRestWithtoken('asta/AggiornaImgAsta', formData);
        return response;
    } catch (error) {
        console.error("Errore durante il caricamento dell'immagine:", error);
        throw new Error("Impossibile caricare l'immagine");
    }
}
