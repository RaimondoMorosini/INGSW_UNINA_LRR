import {getRest} from"./RestUtils.js";

// Funzione per ottenere tutte le categorie dal server

async function getCategorieRest() {
    try {
      let data= null;
        if (sessionStorage.getItem('categorie') === null) {
          const response = await getRest('getGerarchiaCategorie');
           data = response.data;
          sessionStorage.setItem('categorie', JSON.stringify(data));

        }else{
            data= JSON.parse(localStorage.getItem('categorie'));
        }
        return data;

    } catch (error) {
        console.error(error);
        throw new Error('Impossibile ottenere le categorie dal server');
    }
}

export { getCategorieRest };