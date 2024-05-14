import { defineStore } from 'pinia';

export const useTokenStore = defineStore('token', {
    state: () => ({
        token: null,
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
    },

    persist: true,
});
