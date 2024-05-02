import { defineStore } from 'pinia';

export const useAstaStore = defineStore('prodottoAsta', () => {
  const prodottoAsta = ref({
    // Initialize your prodottoAsta fields here
    nomeProdotto: '',
    descrizione: '',
    prezzoBase: 0,
  });

  function getNomeProdotto() {
    return this.prodottoAsta.nomeProdotto;
  }
  function getDescrizione() {
    return this.prodottoAsta.descrizione;
  }
  function getPrezzoBase() {
    return this.prodottoAsta.prezzoBase;
  }
  // Add getters for other prodottoAsta fields here
  function getAsta() {
    return this.prodottoAsta;
  }

  function setNomeProdotto(value) {
    this.prodottoAsta.nomeProdotto = value;
  }
  function setDescrizione(value) {
    this.prodottoAsta.descrizione = value;
  }
  function setPrezzoBase(value) {
    this.prodottoAsta.prezzoBase = value;
  }
  // Add actions for other prodottoAsta fields here
  return {
    prodottoAsta,
    getNomeProdotto,
    getDescrizione,
    getPrezzoBase,
    getAsta,
    setNomeProdotto,
    setDescrizione,
    setPrezzoBase,
  };
});
