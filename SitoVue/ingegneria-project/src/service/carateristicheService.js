import { getRest } from '../scripts/RestUtils';
async function getCaratteristiche(categoria) {
    try {
        const response = await getRest(
            'public/getCaratteristicheDaCategoria?categoria=' + categoria
        ); //Get delle caratteristihce relative alla categoria cercata
        response.forEach((element) => {
            // Ordina le caratteristiche in base ai numeri presenti se data.tipo = 'numero'
            if (element.tipoCaratteristica === 'numero')
                element.opzioniSelezionabili.sort(compareStringsByNumbers); // Ordina le caratteristiche in base ai numeri presenti
            else element.opzioniSelezionabili.sort(); // Ordina le caratteristiche in ordine alfabetico
        });
        return response;
    } catch (error) {
        console.log('errore richiesta  caratteristiche');
    }
}

function extractNumbersFromString(str) {
    let matches = str.match(/\d+/g); // Trova tutte le sequenze di cifre nella stringa
    if (matches) {
        return matches.map(Number); // Converte le sequenze di cifre in numeri
    }
    return [0]; // Se non ci sono numeri, ritorna 0 per mantenere la stringa alla fine dell'ordine
}

function compareStringsByNumbers(a, b) {
    let numA = extractNumbersFromString(a);
    let numB = extractNumbersFromString(b);

    // Confronta le sequenze numeriche trovate nelle stringhe
    for (let i = 0; i < Math.min(numA.length, numB.length); i++) {
        if (numA[i] !== numB[i]) {
            return numA[i] - numB[i];
        }
    }

    // Se tutte le sequenze numeriche confrontate sono uguali, confronta la lunghezza delle sequenze
    return numA.length - numB.length;
}

function getArrayOpzionePerMultiSelect(opzioni) {
    const arrayDelleOpzioniFormattato = new Array();

    opzioni.forEach((opzione) => {
        const oggettoOpzione = {
            name: opzione,
        };

        arrayDelleOpzioniFormattato.push(oggettoOpzione);
    });

    return arrayDelleOpzioniFormattato;
}

function getArrayOpzionePerChiamataAxsios(opzioni) {
    const arrayDelleOpzioniFormattato = new Array();

    opzioni.forEach((opzione) => {
        arrayDelleOpzioniFormattato.push(opzione.name);
    });

    return arrayDelleOpzioniFormattato;
}

function getCaratteristicheSelezionateDTO(
    caratteristicheSelezionateDTO,
    idCaratteristica,
    valoriSelezionati
) {
    if (valoriSelezionati.length === 0) {
        const index = caratteristicheSelezionateDTO.findIndex(
            (obj) => obj.idCaratteristica === idCaratteristica
        );

        caratteristicheSelezionateDTO.splice(index, 1);
    } else {
        const caratteristica = {
            idCaratteristica: idCaratteristica,

            valoriSelezionati: valoriSelezionati,
        };

        const index = caratteristicheSelezionateDTO.findIndex(
            (obj) => obj.idCaratteristica === idCaratteristica
        );

        if (index >= 0) {
            caratteristicheSelezionateDTO[index] = caratteristica;
        } else {
            caratteristicheSelezionateDTO.push(caratteristica);
        }
    }

    return caratteristicheSelezionateDTO;
}

export {
    getCaratteristiche,
    getArrayOpzionePerMultiSelect,
    getArrayOpzionePerChiamataAxsios,
    getCaratteristicheSelezionateDTO,
};
