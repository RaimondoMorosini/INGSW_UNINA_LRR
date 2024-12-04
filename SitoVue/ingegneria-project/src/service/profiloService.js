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
            datiPublichi.isVenditore = dati.isVenditore;
            datiPublichi.immagine = dati.foto_profilo;
            console.log('dati profilo reucuperati dalla rest: ', datiPublichi);
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
    const file = convertiDaSrcToFile(immagineProfiloBase64, nomeImmmagine);
    formData.append('immagineProfilo', file, nomeImmmagine);

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
    const datiProfiloAggiornato = getDatiProfiloPublici(email);
    useProfiloStore().updateProfilo(datiProfiloAggiornato);
}
