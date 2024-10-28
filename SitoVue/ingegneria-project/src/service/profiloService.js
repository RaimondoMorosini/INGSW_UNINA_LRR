import {getRest,getRestWithtoken} from '../scripts/RestUtils.js';

const datiPublichi = {
    nome: '',
    cognome: '',
    siti: '',
    area_geografica: '',
    bio: '',
    isVenditore: false,
    immagine:""
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
        const response = await getRest('/public/utente/datiProfilo/' + email);
        if (response) {
            const dati = await response;    
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

    


export async function isProfiloCompletato(){
    const response = await getRestWithtoken('/utente/isProfiloCompleto');
    if (response) {
        return response;
    } else {
        return false;
    }
 }