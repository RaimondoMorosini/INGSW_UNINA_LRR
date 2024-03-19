import { defineStore } from "pinia";

export const useTokenStore = defineStore({
  persist: true,
  id: "token",
  state: () => ({
    token: JSON.parse(localStorage.getItem("token")) || "",
  }),
  actions: {
    persistToLocalStorage() {
      localStorage.setItem("token", JSON.stringify(this.token));
    },
    setToken(token) {
      this.token = token;4 
       
    },
    deleteToken() {
      this.token = "";
       
    },
  },
});