import axios from "axios";

axios.defaults.baseURL = "http://localhost:8081/";
axios.defaults.headers.common['Authorization']= 'Bearer ' + localStorage.getItem('token');

// Funzione per ottenere un elemento dal server
async function getRest(path) {
    try {
        const response = await axios.get(`${path}`);
        console.log(response.data);
        return response;
    } catch (error) {
        console.error(error);
        throw new Error('Impossibile ottenere l\'elemento dal server' + error);
    }
}

export {
    getRest
};