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
        logout(){
            this.profilo.isAutenticato=false;
            this.profilo.email='';
            this.profilo.password='';
            this.profilo.nome='';
            this.profilo.cognome='';
            this.profilo.siti='';
            this.profilo.area_geografica='';
            this.profilo.bio='';
            this.profilo.metodo_di_registrazione='';
        },
    },

    persist: true,
});
