import { postRest } from './RestUtils.js';

export async function uploadImages(file) {
    try {
        const formData = new FormData();
        file.forEach((f) => {
            formData.append('file', f.file);
        });
        const response = postRest('public/asta/uploadImages', formData);
        return response;
    } catch (error) {
        console.error("Errore durante il caricamento dell'immagine:", error);
        throw new Error("Impossibile caricare l'immagine");
    }
}
