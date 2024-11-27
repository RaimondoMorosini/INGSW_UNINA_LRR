import { postRestWithtoken, postRest, getRest } from '../scripts/RestUtils.js';
import { TipoAsta } from './astaService.js';

export async function faiOfferta(prezzoProposto, idAsta) {
    const payload = {
        prezzoProposto: prezzoProposto,
        idAsta: idAsta,
    };
    try {
        const response = await postRestWithtoken('faiOfferta', payload);
        return response;
    } catch (error) {
        console.error("Errore durante l'invio dell'offerta:", error);
        throw error;
    }
}

export async function getOfferteAstaIinglese(idAsta) {
    try {
        const response = await getRest('public/offerteAstaInglese/' + idAsta);
        return response;
    } catch (error) {
        console.error('Errore:', error);
        throw error; // Propaga l'errore per gestirlo nel componente chiamante
    }
}


export async function ConfermaOffertaVincente(idAsta,idOfferta) {
    const payload = {
        idOfferta: idOfferta,
        idAsta: idAsta,
    };
    try {
        const response = await postRestWithtoken('ConfermaOffertaVincente',payload);    
    }catch (error) {
        console.error('Errore:', error);
        throw error; // Propaga l'errore per gestirlo nel componente chiamante
    }
}
