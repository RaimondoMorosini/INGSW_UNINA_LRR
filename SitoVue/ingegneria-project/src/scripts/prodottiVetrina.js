import { postRest } from './RestUtils';

const parametriRichiesta1 = {
    pagina: 1,
    elementiPerPagina: 10,
    categoria: 'tutte',
    nomeProdotto: 'smartphone',
    tipoAsta: [],
    caratteristicheSelezionate: [],
};

async function fetchCaroselli() {
    try {
        const prodotti = await postRest('public/asta/getAllAste', parametriRichiesta1);

        const caroselli = [
            {
                titoloCarosello: 'Smartphone in asta',
                prodotti: prodotti,
            },
        ];

        return caroselli;
    } catch (error) {
        console.log('errore recupero caroselli');
    }
}

export { fetchCaroselli };
