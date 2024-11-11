// store/dialogStore.js
import { defineStore } from 'pinia';

export const useDialogStore = defineStore('dialogStore', {
    state: () => ({
        isDialogVisible: false,
    }),
    actions: {
        apriDialogo() {
            this.isDialogVisible = true;
        },
        chiudiDialogo() {
            this.isDialogVisible = false;
        },
    },
});
