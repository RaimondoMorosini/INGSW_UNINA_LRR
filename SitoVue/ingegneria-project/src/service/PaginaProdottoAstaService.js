import { getRest, postRest } from '../scripts/RestUtils.js';

async function getInfoAstaProdotto(idAsta) {
    try {
        const response = await getRest('public/prodottoAsta/' + idAsta);
        return response;
    } catch (error) {
        console.error('Errore:', error);
        throw error; // Propaga l'errore per gestirlo nel componente chiamante
    }
}

async function getDatiastaInglese(idAsta) {
    try {
        const response = await getRest('public/prodottoAsta/extra/' + idAsta);
        return response;
    } catch (error) {
        console.error('Errore:', error);
        throw error; // Propaga l'errore per gestirlo nel componente chiamante
    }
}

export { getInfoAstaProdotto, getDatiastaInglese };
