<template>
    <footer class="footer flex justify-center bg-primario-400">
        <div class="container">
            <div class="content">
                Fatto con
                <a href="https://primevue.org/" target="_blank"
                    ><i size="large" class="pi pi-prime"></i>
                </a>
                da Lorenzo Sepe, Raimondo Morosini, Roberto Spena.

                <br />
                <div>
                    <p class="control">
                        <a class="button" href="https://github.com/Lorenzo-Sepe" target="_blank">
                            <i class="pi pi-github"></i> <span>GitHub - Lorenzo Sepe</span>
                        </a>
                    </p>
                    <p class="control">
                        <a
                            class="button"
                            href="https://github.com/RaimondoMorosini"
                            target="_blank"
                        >
                            <i class="pi pi-github"></i> <span>GitHub - Raimondo Morosini</span>
                        </a>
                    </p>
                    <p class="control">
                        <a class="button" href="https://github.com/Sperob98" target="_blank">
                            <i class="pi pi-github"></i> <span>GitHub - Roberto Spena</span>
                        </a>
                    </p>
                </div>
            </div>
        </div>
    </footer>
</template>

<script setup>
import { ref } from 'vue';
import ToggleButton from 'primevue/togglebutton';
import { useTokenStore } from '../../stores/tokenStore';

const storeInstance = useTokenStore();

const checked = ref(storeInstance.darkMode);

function toggleDarkMode() {
    root.classList.toggle('dark-mode-switch');
    storeInstance.setDarkMode(checked);
}
</script>

<style scoped>
.footer {
    padding: 2rem 0;
    text-align: center;
}
</style>
