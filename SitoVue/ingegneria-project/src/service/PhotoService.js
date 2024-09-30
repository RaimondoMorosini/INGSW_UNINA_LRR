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
