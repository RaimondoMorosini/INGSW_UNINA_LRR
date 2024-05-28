<template>
    <div class="contenitore-asta">
        <div class="contenitore-immagine">
            <img
                class="img-prodotto"
                alt="Immagine Prodotto"
                src="https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/refurb-iphone-13-pro-max-blue-2023?wid=1144&hei=1144&fmt=jpeg&qlt=90&.v=1679072989205"
            />
        </div>
        <div class="dati-asta">
            <section class="nome-prodotto">iphone 15max 256GB</section>
            <section class="venditore">Venditore: Roberto</section>
            <section class="base-asta">BASE ASTA: EURO 30</section>
            <section class="base-asta">PREZZO ATTUALE: EURO 120</section>
        </div>
    </div>
</template>

<style scoped>
.contenitore-asta {
    display: flex;
    flex-direction: column;
    width: 30%;
    height: 30rem;
    margin-right: 1rem;
    padding: 0.5rem;
    background-color: #f2f2f2;
    cursor: pointer;
}

.contenitore-immagine {
    width: 80%;
    border-radius: 10px;
}

.img-prodotto {
    border-radius: 10px;
}

.dati-asta {
    width: 95%;
    margin-top: 1rem;
    overflow: hidden;
    word-wrap: break-word;
}

.nome-prodotto {
    font-size: 20px;
    font-weight: bold;
}

.nome-prodotto:hover {
    text-decoration: underline;
}

.venditore {
    color: rgb(180, 180, 180);
}

.base-asta {
    margin-top: 1rem;
    font-size: 15px;
    font-weight: bold;
}

.img-asta {
    width: 5rem;
    height: 5rem;
}
</style>
