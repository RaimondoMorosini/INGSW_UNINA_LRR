import { getRest } from '../scripts/RestUtils';
async function  getCaratteristiche(categoria)  {
    try {
        const response = await getRest(
            'public/getCaratteristicheDaCategoria?categoria=' + categoria
        ); //Get delle caratteristihce relative alla categoria cercata

        return response.data;
    } catch (error) {
        console.log('errore richiesta  caratteristiche');
    }
};

export { getCaratteristiche };
