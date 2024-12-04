import { defineStore } from 'pinia';

export const useProfiloStore = defineStore('profilo', {
    state: () => ({
        profilo: {
            isAutenticato: false,
            isVenditore: false,
            email: '',
            nome: '',
            cognome: '',
            imageURL: [],
            siti_social: [],
            area_geografica: '',
            bio: '',
            metodo_di_registrazione: '',
        },

    }),
    actions: {
        updateProfilo(newData) {
            this.profilo = { ...this.profilo, ...newData };
        },
        logout() {
            this.profilo= {};
            this.profilo.isAutenticato = false;
        },
        isProfiloPresente() {
            return this.profilo.email !== '' && this.profilo.nome !== '';
        },
    },

    persist: true,
});
