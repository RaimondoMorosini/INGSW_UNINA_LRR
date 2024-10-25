import {getRest} from '../scripts/RestUtils.js';

const datiPublichi = {
    nome: '',
    cognome: '',
    siti: '',
    areaGeografica: '',
    bio: '',
    isVenditore: false
};
const datiVenditore = {
    nomeUtente: '',
    partitaIva: '',
    codiceFiscale: '',
    nomeAzienda: '',
    numeroTelefono: ''
};
const ruoliProfilo = ['compratore'];

const datiProfilo = {
    datiPublichi,
    datiVenditore,
    ruoliProfilo
};

export async function getDatiProfiloPublichi(email) {
    try {
        const response = await getRest('/utente/datiProfilo?email=' + email);
        if (response.ok) {
            const dati = await response.json();
            datiPublichi.nome = dati.nome;
            datiPublichi.cognome = dati.cognome;
            datiPublichi.siti = dati.siti;
            datiPublichi.areaGeografica = dati.areaGeografica;
            datiPublichi.bio = dati.bio;
            datiPublichi.isVenditore = dati.isVenditore;
            return datiPublichi;
        } else {
            throw new Error('Errore nel recupero dei dati publichi');
        }
    } catch (error) {
        console.error('Errore:', error);
        return null;
    }

    
}