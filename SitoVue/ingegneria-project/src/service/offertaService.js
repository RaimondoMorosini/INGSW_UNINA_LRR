import {postRestWithtoken,postRest} from '../scripts/RestUtils.js';

export async function faiOfferta(prezzoProposto, idAsta) {
    const payload = {
        prezzoProposto: prezzoProposto,
        idAsta: idAsta
    };
    try {
        const response = await postRestWithtoken('faiOfferta', payload);
        return response;
    } catch (error) {
        console.error('Errore durante l\'invio dell\'offerta:', error);
        throw error;
    }
}