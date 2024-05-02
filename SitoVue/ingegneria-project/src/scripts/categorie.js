import { getRest } from './RestUtils.js';

import { getDato, inserisciDato, eliminaDato } from './DatiUtils.js';
// Funzione per ottenere tutte le categorie dal server

async function getCategorieRest() {
  try {
    let data = getDato('categorie');

    if (data === null) {
      const response = await getRest('public/getGerarchiaCategorie');
      data = response.data;
      inserisciDato('categorie', JSON.stringify(data));
    }
    console.log('categorie', data);
    return JSON.parse(data);
  } catch (error) {
    console.error(error);
    throw new Error('Impossibile ottenere le categorie dal server');
  }
}

export { getCategorieRest };
