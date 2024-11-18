import { defineStore } from 'pinia';

export const useProfiloStore = defineStore('profilo', {
    state: () => ({
        profilo: {
            isAutenticato: false,
            email: '',
            password: '',
            nome: '',
            cognome: '',
            imageURL: [],
            nomeImmagine: '',
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
            this.profilo.isAutenticato = false;
            this.profilo.email = '';
            this.profilo.password = '';
            this.profilo.nome = '';
            this.profilo.cognome = '';
            this.profilo.immagineURL = [];
            this.profilo.siti_social = '';
            this.profilo.area_geografica = '';
            this.profilo.bio = '';
            this.profilo.metodo_di_registrazione = '';
        },
        isProfiloPresente() {
            return this.profilo.email !== '' && this.profilo.nome !== '';
        },
    },

    persist: true,
});
