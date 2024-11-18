import { getDato } from '../scripts/DatiUtils';

export function isTokenScaduto() {
    try {
        const token = getDato('token');
        if (!token) {
            console.warn('Token non trovato');
            return true;
        }
        const tokenDecodificato = JSON.parse(atob(token.split('.')[1]));
        console.log(tokenDecodificato);
        return tokenDecodificato.exp < Date.now() / 1000;
    } catch (error) {
        console.watn(error);
        return true;
    }
}
