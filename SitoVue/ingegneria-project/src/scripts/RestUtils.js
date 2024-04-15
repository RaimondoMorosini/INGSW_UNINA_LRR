import axios from "axios";

axios.defaults.baseURL = "http://localhost:8081/";
axios.defaults.headers.common['Authorization']= 'Bearer ' + localStorage.getItem('token');

// Funzione per ottenere un elemento dal server
async function getRest(path) {
    try {
        const response = await axios.get(`${path}`);
        console.log("Get path:",path," response.data:",response.data);
        return response;
    } catch (error) {
        console.error(error);
        throw new Error('Impossibile ottenere l\'elemento dal server' + error);
    }
}

async function postRest(path, data) {
    try {
        console.log("Post path:",path," dati body: ",JSON.stringify(data));
        const response = await axios.post(`${path}`, data );
        console.log(" response.data:",response.data);
        return response.data;
    } catch (error) {
        console.error(error);
        throw new Error('Impossibile inviare l\'elemento al server' + error);
    }
}

export {
    getRest, postRest
};