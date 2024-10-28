import { defineStore } from 'pinia';

export const useProfiloStore = defineStore('profilo', {
    state: () => ({
        profilo: {
            isAutenticato: false,
            email: '',
            password: '',
            nome: '',
            cognome: '',
            immagineSalvata: [],
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
            this.profilo.immagineSalvata=[];
            this.profilo.siti='';
            this.profilo.area_geografica='';
            this.profilo.bio='';
            this.profilo.metodo_di_registrazione='';
        },
        isProfiloPresente() {
            return this.profilo.email !== '' && this.profilo.nome !== '' ;
        }
    },

    persist: true,
});
