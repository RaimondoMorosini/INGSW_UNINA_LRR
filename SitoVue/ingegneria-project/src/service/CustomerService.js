export const CustomerService = {
    data: [], // Array per memorizzare i dati

    initializeData(newData) {
        this.data = newData;
    },

    getData() {
        return this.data;
    },

    getCustomersSmall() {
        return Promise.resolve(this.getData().slice(0, 10));
    },

    getCustomersMedium() {
        return Promise.resolve(this.getData().slice(0, 50));
    },

    getCustomersLarge() {
        return Promise.resolve(this.getData().slice(0, 200));
    },

    getCustomersXLarge() {
        return Promise.resolve(this.getData());
    },

    // Aggiunge un nuovo oggetto all'array dei dati
    aggiungiOfferta(offerta) {
        this.data.push(offerta);

        // Ordina l'array in base al campo `tempo` in ordine decrescente
        this.data.sort((a, b) => (a['tempo'] < b['tempo'] ? 1 : -1));
    },

    getCustomers(params) {
        const queryParams = params
            ? Object.keys(params)
                  .map((k) => encodeURIComponent(k) + '=' + encodeURIComponent(params[k]))
                  .join('&')
            : '';

        return fetch('https://www.primefaces.org/data/customers?' + queryParams).then((res) =>
            res.json()
        );
    },
};
