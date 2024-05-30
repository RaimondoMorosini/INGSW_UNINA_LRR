import { getCategorieRest } from '../scripts/categorie.js';

export async function getCategorie() {
    try {
        const data = await getCategorieRest();
        console.log('Service: categorie trovate', data);
        return data;
    } catch (error) {
        console.log('errore categorie non trovate', error);
        throw new Error('errore categorie non trovate');
        //TODO restituire delle categorie di default
    }
}
