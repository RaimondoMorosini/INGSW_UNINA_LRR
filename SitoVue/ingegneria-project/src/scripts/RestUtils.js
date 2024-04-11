const axios = require('axios');

// Funzione per ottenere un elemento dal server
async function getElement(id) {
    try {
        const response = await axios.get(`/api/element/${id}`);
        return response.data;
    } catch (error) {
        console.error(error);
        throw new Error('Impossibile ottenere l\'elemento dal server');
    }
}

// Funzione per creare un nuovo elemento sul server
async function createElement(element) {
    try {
        const response = await axios.post('/api/element', element);
        return response.data;
    } catch (error) {
        console.error(error);
        throw new Error('Impossibile creare l\'elemento sul server');
    }
}

// Funzione per aggiornare un elemento esistente sul server
async function updateElement(id, updatedElement) {
    try {
        const response = await axios.put(`/api/element/${id}`, updatedElement);
        return response.data;
    } catch (error) {
        console.error(error);
        throw new Error('Impossibile aggiornare l\'elemento sul server');
    }
}

// Funzione per eliminare un elemento dal server
async function deleteElement(id) {
    try {
        const response = await axios.delete(`/api/element/${id}`);
        return response.data;
    } catch (error) {
        console.error(error);
        throw new Error('Impossibile eliminare l\'elemento dal server');
    }
}

module.exports = {
    getElement,
    createElement,
    updateElement,
    deleteElement
};