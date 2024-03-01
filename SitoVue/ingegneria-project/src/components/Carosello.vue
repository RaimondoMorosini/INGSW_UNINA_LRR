<template>
    <div class="contenitoreCarosello z-0" :style="{ backgroundColor: sfondo }">
        <img src="../assets/Icon/frecciaSinistra2.svg" class="frecciaSinistra" @click="updatePhotoFreccia('sinistra')" />
        <div class="contenitoreImg">
            <img v-if="countImg == 1" src="../assets/img/carosello/carousel1.png" class="immagine" />
            <img v-else-if="countImg == 2" src="../assets/img/carosello/carousel2.png" class="immagine" />
            <img v-else src="../assets/img/carosello/carousel3.png" class="immagine" />
            <div class="contenitoreOpzioni absolute inset-x-0 bottom-0 mx-auto">
                <div v-if="countImg != 1" class="opzione" @click="updatePhoto(1)"></div>
                <div v-else class="opzioneSelected" @click="updatePhoto(1)"></div>
                <div v-if="countImg != 2" class="opzione" @click="updatePhoto(2)"></div>
                <div v-else class="opzioneSelected" @click="updatePhoto(2)"></div>
                <div v-if="countImg != 3" class="opzione" @click="updatePhoto(3)"></div>
                <div v-else class="opzioneSelected" @click="updatePhoto(3)"></div>
            </div>
        </div>
        <img src="../assets/Icon/frecciaDestra.svg" class="frecciaDestra" @click="updatePhotoFreccia('destra')" />
    </div>
</template>

<script>

export default {
    data() {
        return {

            countImg: 1,
            sfondo: '#e6818d'
        };
    },
    mounted() {

        this.intervallo = setInterval(() => {
            if (this.countImg == 3) {

                this.countImg = 1
                this.sfondo = '#e6818d'

            } else {

                this.countImg++
                if (this.countImg == 2) {

                    this.sfondo = '#fee782'
                } else {

                    this.sfondo = '#e9c9c6'
                }
            }
        }, 5000); // 5000 millisecondi = 5 secondi
    },

    methods: {

        updatePhotoFreccia(direzione) {

            if (direzione == 'sinistra') {

                if (this.countImg != 1) {

                    this.countImg--

                    switch (this.countImg) {
                        case 2:
                            this.sfondo = '#fee782'
                            break;

                        case 1:
                            this.sfondo = '#e6818d'
                            break;
                    
                        default:
                            break;
                    }

                } else {

                    this.countImg = 3
                    this.sfondo = '#e9c9c6'
                }

            } else {

                if (this.countImg != 3) {

                    this.countImg++

                    switch (this.countImg) {
                        case 2:
                            this.sfondo = '#fee782'
                            break;

                        case 3:
                            this.sfondo = '#e9c9c6'
                            break;
                    
                        default:
                            break;
                    }

                } else {

                    this.countImg = 1
                    this.sfondo = '#e6818d'
                }
            }
        },

        updatePhoto(opzione) {

            if (opzione == 1) {

                this.countImg = 1
                this.sfondo = '#e6818d'
            }

            else if (opzione == 2) {

                this.countImg = 2
                this.sfondo = '#fee782'
            }

            else {

                this.countImg = 3
                this.sfondo = '#e9c9c6'
            }
        }
    }
};

</script>

<style scoped>
.contenitoreCarosello {

    width: 80%;
    height: auto;
    background-color: rgba(128, 128, 128, 0.15);
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    display: flex;
}

.contenitoreImg {
    width: auto;
    margin-left: auto;
    margin-right: auto;
}

.immagine {

    width: 100%;
    height: 100%;
}

.frecciaSinistra {

    width: 3rem;
    height: 3rem;
    margin-top: auto;
    margin-bottom: auto;
    cursor: pointer;
}

.frecciaDestra {

    width: 3rem;
    height: 3rem;
    margin-top: auto;
    margin-bottom: auto;
    margin-left: 1px;
    cursor: pointer;

}

.contenitoreOpzioni {
    @apply w-1/6 ; 
    height: 3rem;
    border-radius: 2rem;
    background-color: rgba(128, 128, 128, 0.5);
    display: flex;
    padding: 1rem;
    text-align: center;
    justify-content: center;
}

.opzione {

    width: 1rem;
    height: 1rem;
    border-radius: 9999px;
    background-color: #d3b0ff;
    margin: 0.2rem;
    cursor: pointer;
}

.opzioneSelected {

    width: 1rem;
    height: 1rem;
    border-radius: 9999px;
    background-color: #6953a9;
    margin: 0.2rem;
    cursor: pointer;
}
</style>