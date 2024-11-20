<template>
    <div class="home-intro">
        <h1>Scopri l’Asta Perfetta per Te</h1>
        <p>Partecipa a aste dinamiche e trasparenti, progettate per tutti.</p>

        <div class="aste-container">
            <div class="tipo-asta">
                <h3>Asta Inglese</h3>
                <p>Rilancia il prezzo e vinci con l’offerta più alta!</p>
            </div>
            <div class="tipo-asta">
                <h3>Asta Inversa</h3>
                <p>Più aspetti, meno paghi: il prezzo cala col tempo!</p>
            </div>
            <div class="tipo-asta">
                <h3>Asta Silenziosa</h3>
                <p>Fai la tua offerta migliore, senza rivelare nulla agli altri.</p>
            </div>
        </div>

        <button class="cta-btn">Scopri le Aste Disponibili</button>
    </div>
</template>

<script setup></script>

<style scoped>
.home-intro {
    text-align: center;
    padding: 2rem;
    background-color: #fafafa;
    border: 4px solid #333;
    border-radius: 15px;
    max-width: 800px;
    margin: auto;
    box-shadow: 5px 5px 0px #333;
}

.home-intro h1 {
    font-size: 2.5rem;
    color: #333;
    text-shadow:
        -2px -2px 0 #fdd835,
        2px 2px 0 #333;
}

.home-intro p {
    font-size: 1.2rem;
    color: #666;
    margin-bottom: 2rem;
}

.aste-container {
    display: flex;
    justify-content: space-around;
    margin: 2rem 0;
}

.tipo-asta {
    flex: 1;
    margin: 0 1rem;
    padding: 1rem;
    border: 3px solid #333;
    border-radius: 20px;
    background-color: #fefefe;
    position: relative;
    box-shadow: 5px 5px 0px #333;
    transition: transform 0.3s ease;
}

.tipo-asta:hover {
    transform: translateY(-10px);
}

.tipo-asta h3 {
    font-size: 1.5rem;
    color: #333;
    text-transform: uppercase;
    margin-bottom: 0.5rem;
}

.tipo-asta p {
    color: #555;
    font-size: 1rem;
    font-weight: bold;
}

.tipo-asta::after {
    content: '';
    position: absolute;
    bottom: -15px;
    left: 20px;
    width: 15px;
    height: 15px;
    background-color: #fefefe;
    border: 3px solid #333;
    transform: rotate(45deg);
}

.cta-btn {
    background-color: #ff4081;
    color: #fff;
    padding: 0.8rem 2rem;
    font-size: 1.1rem;
    border: 3px solid #333;
    border-radius: 10px;
    cursor: pointer;
    text-shadow: -1px -1px 0 #333;
    box-shadow: 5px 5px 0px #333;
    transition:
        background-color 0.3s ease,
        transform 0.3s ease;
}

.cta-btn:hover {
    background-color: #e91e63;
    transform: translateY(-5px);
}
</style>
