const categorie = [
  {
    nome: 'Elettronica',
    figlie: [
      { nome: 'Smartphone', figlie: [] },
      { nome: 'Laptop', figlie: [] },
      {
        nome: 'Accessori', figlie: [
          { nome: 'Custodie', figlie: [] },
          { nome: 'Caricatori', figlie: [] }
        ]
      }
    ]
  },
  {
    nome: 'Libri',
    figlie: [
      { nome: 'Fantascienza', figlie: [] },
      { nome: 'Storici', figlie: [] }
    ]
  },
  {
    nome: 'Abbigliamento',
    figlie: [
      { nome: 'Uomo', figlie: [] },
      { nome: 'Donna', figlie: [] },
      { nome: 'Bambini', figlie: [] }
    ]
  }
];
// Esporta l'array di categorie con sottocategorie
export default categorie;

export function trovaGenitore(padre, stringaPadre) {

  for (let i = 0; i < padre.length; i++) {

    if (padre[i].nome == stringaPadre) {

      return padre
    }

    let genitore = trovaGenitore(padre[i].figlie, stringaPadre)

    if (genitore !== null) {

      return genitore
    }

  }

  return null

};

export function trovaStringaGenitore(padre, stringaFiglio) {

  for (let i = 0; i < padre.length; i++) {

    for (let j = 0; j < padre[i].figlie.length; j++) {

      if (padre[i].figlie[j].nome == stringaFiglio) {

        return padre[i].nome

      }

    }

    let genitore = trovaStringaGenitore(padre[i].figlie, stringaFiglio)

    if (genitore != null) {

      return genitore
    }

  }


  return null

}
