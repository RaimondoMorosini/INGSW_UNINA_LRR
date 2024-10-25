import { defineStore } from 'pinia';

export const useProfiloStore = defineStore('profilo', {
    state: () => ({
        profilo: {
            isAutenticato: false,
            email: '',
            password: '',
            nome: '',
            cognome: '',
            siti: '',
            area_geografica: '',
            bio: '',
            metodo_di_registrazione: '',
        },
    }),
    actions: {
        updateProfilo(newData) {
            this.profilo = { ...this.profilo, ...newData };
        },
    },

    persist: true,
});
