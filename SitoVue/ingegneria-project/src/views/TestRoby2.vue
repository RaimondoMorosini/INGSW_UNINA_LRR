<template>
    <div class="lista">
        <div class="contenitore-articolo-lista">
            <div class="contenitore-copertina">
                <img class="copertina" src="https://www.dedicated.world/wp-content/uploads/2020/07/Google.jpg" />
            </div>
            <div class="contenitore-info">
                <div class="tipo-asta">
                    <p>Elettronica</p>
                </div>
            </div>
        </div>
        <div class="contenitore-articolo-lista">
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped>
.lista {

    flex-direction: column;
    padding: auto;
}

.contenitore-articolo-lista {

    width: 45rem;
    height: 8rem;
    background-color: gray;
    margin-top: 2rem;
    margin-bottom: 2rem;
    padding-top: 1rem;
    padding-bottom: 1rem;
    padding-left: 1rem;
    display: flex;
    flex-direction: row;
}

.contenitore-copertina {

    width: 6rem;
    height: 6rem;
    border: 2px solid #000;
    border-radius: 2rem;
}

.copertina {

    height: 100%;
    width: 100%;
    border-radius: 2rem;
}

.contenitore-info{

    width: 15rem;
    background-color: red;
    margin-left: 1rem;
    
}

.tipo-asta{

    width: 6rem;
    height: 1.3rem;
    border: 2px solid #c5c5c58a;
    border-radius: 1rem;
    background-color: blue;
    font-size: 40%;
}

</style>