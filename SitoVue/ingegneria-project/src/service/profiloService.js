import { getRest, getRestWithtoken, postRestWithtoken } from '../scripts/RestUtils.js';
import { useProfiloStore } from '../stores/profiloStore.js';
import { convertiDaSrcToFile } from './PhotoService.js';
const datiPublichi = {
    nome: '',
    cognome: '',
    siti: '',
    area_geografica: '',
    bio: '',
    isVenditore: false,
    immagine: '',
};
const datiVenditore = {
    nomeUtente: '',
    partitaIva: '',
    codiceFiscale: '',
    nomeAzienda: '',
    numeroTelefono: '',
};
const ruoliProfilo = ['compratore'];

const datiProfilo = {
    datiPublichi,
    datiVenditore,
    ruoliProfilo,
};

export async function getDatiProfiloPublici(email) {
    try {
        const response = await getRest('public/utente/datiProfilo/' + email);
        if (response) {
            const dati = await response;
            datiPublichi.nome = dati.nome;
            datiPublichi.cognome = dati.cognome;
            datiPublichi.siti = dati.siti;
            datiPublichi.area_geografica = dati.areaGeografica;
            datiPublichi.bio = dati.bio;
            datiPublichi.immagine = dati.foto_profilo;
            datiPublichi.isVenditore = dati.isVenditore;
            try {

                console.info('dati.isVenditore', dati.isVenditore);
                if (dati.isVenditore) {
                    console.info('sono dentro if', dati.datiVenditore);
                    datiVenditore.nomeUtente = dati.datiVenditore.nomeUtente;
                    datiVenditore.partitaIva = dati.datiVenditore.partitaIva;
                    datiVenditore.codiceFiscale = dati.datiVenditore.codiceFiscale;
                    datiVenditore.nomeAzienda = dati.datiVenditore.nomeAzienda;
                    datiVenditore.numeroTelefono = dati.datiVenditore.numeroTelefono;
                    datiPublichi.datiVenditore = datiVenditore;
                }
            } catch (error) {
                console.info('errore nel settaggio dei dati venditore:', error);
                datiVenditore = null;
            }
            console.log('dati profilo reucuperati dalla rest: ', JSON.stringify());
            return datiPublichi;
        } else {
            throw new Error('Errore nel recupero dei dati publichi');
        }
    } catch (error) {
        console.error('Errore:', error);
        return null;
    }
}

export async function isProfiloCompletato() {
    const response = await getRestWithtoken('/utente/isProfiloCompleto');
    if (response) {
        return response;
    } else {
        return false;
    }
}

export async function modificaProfiloPublico(
    nome,
    cognome,
    indirizzo,
    bio,
    sitiSocial,
    immagineProfiloBase64,
    nomeImmmagine
) {
    const formData = new FormData();
    formData.append('nome', nome);
    formData.append('cognome', cognome);
    formData.append('indirizzo', indirizzo);
    formData.append('bio', bio);
    formData.append('sitiSocial', sitiSocial);
    if (immagineProfiloBase64 === null) {
        formData.append('immagineProfilo', null);
    } else {
        const file = convertiDaSrcToFile(immagineProfiloBase64, nomeImmmagine);
        formData.append('immagineProfilo', file, nomeImmmagine);
    }
    try {
        const response = await postRestWithtoken('/utente/modificaProfilo', formData);
        if (response) {
            console.log('dati modificati con sucesso' + response);
            aggiornaProfilo();
            return response;
        }
        console.log('Errore nella modifica del profilo');
    } catch (error) {
        console.error('Errore:', error);
    }
    return null;
}

export async function aggiornaProfilo() {
    const email = useProfiloStore().profilo.email;
    getDatiProfiloPublici(email).then((datiProfiloAggiornato) => {
        useProfiloStore().profilo.area_geografica = datiProfiloAggiornato.area_geografica;
        useProfiloStore().profilo.bio = datiProfiloAggiornato.bio;
        useProfiloStore().profilo.cognome = datiProfiloAggiornato.cognome;
        useProfiloStore().profilo.nome = datiProfiloAggiornato.nome;
        useProfiloStore().profilo.siti_social = datiProfiloAggiornato.siti;
        useProfiloStore().profilo.imageURL = datiProfiloAggiornato.immagine;
        useProfiloStore().profilo.isVenditore = datiProfiloAggiornato.isVenditore;
        useProfiloStore().profilo.datiVenditore = datiProfiloAggiornato.datiVenditore;
        useProfiloStore().profilo.imageURL = useProfiloStore().profilo.immagine;
        datiProfiloAggiornato.siti = datiProfiloAggiornato.siti.replace('[', '').replace(']', '');
        useProfiloStore().profilo.siti_social = datiProfiloAggiornato.siti.split(',').filter((el) => el !== '');
    }).catch((error) => {
        console.error('Errore nell\'aggionare i dait :', error);
    });
}

export async function DiventaVenditore(partitaIva, codiceFiscale, numeroTelefono, nomeAzienda) {

    const email = useProfiloStore().profilo.email;
    const formData = new FormData();
    formData.append('email', email);
    formData.append('partitaIva', partitaIva);
    formData.append('CodiceFiscale', codiceFiscale);
    formData.append('numeroTelefono', numeroTelefono);
    formData.append('nomeAzienda', nomeAzienda);

    try {
        const response = await postRestWithtoken('utente/diventaVenditore', formData);
        if (response) {
            console.log('utente diventato venditore: ', response);
            useProfiloStore().profilo.isVenditore = true;
            return response;
        }
        console.log('Errore nella modifica del profilo');
    } catch (error) {
        console.error('Errore:', error);
    }

    aggiornaProfilo().then((response) => {
        console.log('Profilo aggiornato con successo', response);
    }).catch((error) => {
        console.error('Errore aggiornamento profilo', error);
    });
}

