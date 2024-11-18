export const PhotoService = {
    getData(images) {
        const imageObjects = []; // Array dove aggiungiamo i path delle immagini

        for (let i = 0; i < images.length; i++) {
            imageObjects.push({
                itemImageSrc: images[i],
                thumbnailImageSrc: images[i],
            });
        }

        return imageObjects;
    },

    getImages(images) {
        return Promise.resolve(this.getData(images));
    },
};

export function convertiDaSrcToFile(base64, nomeFile) {
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
    let blob = new Blob([byteArray], { type: tipo });

    return new File([blob], nomeFile, { type: 'image/jpeg' });
}
