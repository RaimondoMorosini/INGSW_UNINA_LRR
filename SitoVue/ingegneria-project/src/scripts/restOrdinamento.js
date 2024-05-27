function getInfoOrdinamento(opzioneSelezionata) {
    const campiOrdinamento = [];

    switch (opzioneSelezionata) {
        case 'Prezzo più basso':
            campiOrdinamento[0] = 'PREZZO_ATTUALE';
            campiOrdinamento[1] = 'ASCENDENTE';
            return campiOrdinamento;

        case 'Prezzo più alto':
            campiOrdinamento[0] = 'PREZZO_ATTUALE';
            campiOrdinamento[1] = 'DISCENDENTE';
            return campiOrdinamento;

        case 'Nome discedente':
            campiOrdinamento[0] = 'NOME_PRODOTTO';
            campiOrdinamento[1] = 'DISCENDENTE';
            return campiOrdinamento;

        case 'Nome crescente':
            campiOrdinamento[0] = 'NOME_PRODOTTO';
            campiOrdinamento[1] = 'ASCENDENTE';
            return campiOrdinamento;

        case 'Più recenti':
            campiOrdinamento[0] = 'DATA_INIZIO';
            campiOrdinamento[1] = 'DISCENDENTE';
            return campiOrdinamento;

        default:
            return null;
    }
}

export { getInfoOrdinamento };
