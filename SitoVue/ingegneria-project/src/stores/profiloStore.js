import { defineStore } from 'pinia';

export const useProfiloStore = defineStore('profilo', {
    state: () => ({
        profilo: {
            nomeProfilo: '',
            emailProfilo: '',
            passwordProfilo: '',
            immagineProfilo: '',
        },
    }),
    actions: {
        updateProfilo(newData) {
            this.profilo = { ...this.profilo, ...newData };
        },
    },
});
