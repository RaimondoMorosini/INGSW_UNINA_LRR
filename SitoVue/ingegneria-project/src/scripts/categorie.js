import { getRest } from './RestUtils.js';

import { getDato, inserisciDato } from './DatiUtils.js';
// Funzione per ottenere tutte le categorie dal server

async function getCategorieRest() {
    try {
        let data = getDato('categorie');

        if (data === null) {
            const response = await getRest('public/getGerarchiaCategorie');
            data = response;
            inserisciDato('categorie', data);
        }
        return data;
    } catch (error) {
        console.error(error);
        throw new Error('Impossibile ottenere le categorie dal server');
    }
}

export { getCategorieRest };
