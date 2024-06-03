import { postRest } from './RestUtils.js';
import { getImageInFormdata} from '../service/astaService.js';

export async function uploadImages() {
    try {

        const formData = await getImageInFormdata();
        const response = postRest('public/asta/uploadImages',formData);
        return response;
    } catch (error) {
        console.error("Errore durante il caricamento dell'immagine:", error);
        throw new Error("Impossibile caricare l'immagine");
    }
}




