import { defineStore } from 'pinia';

export const useTokenStore = defineStore('token', {
    state: () => ({
        token: null,
        darkMode: false,
    }),
    actions: {
        getToken() {
            return this.token;
        },
        setAccessToken(token) {
            this.token = token;
        },
        clearAccessToken() {
            this.token = null;
        },
        setDarkMode(darkMode) {
            this.darkMode = darkMode;
        },
    },

    persist: true,
});
