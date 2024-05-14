const getAllCategoria = [
    {
        nome: 'Elettronica',
        figlie: [
            { nome: 'Smartphone', figlie: [] },
            { nome: 'Laptop', figlie: [] },
            {
                nome: 'Accessori',
                figlie: [
                    { nome: 'Custodie', figlie: [] },
                    { nome: 'Caricatori', figlie: [] },
                ],
            },
        ],
    },
    {
        nome: 'Libri',
        figlie: [
            { nome: 'Fantascienza', figlie: [] },
            { nome: 'Storici', figlie: [] },
        ],
    },
    {
        nome: 'Abbigliamento',
        figlie: [
            { nome: 'Uomo', figlie: [] },
            { nome: 'Donna', figlie: [] },
            { nome: 'Bambini', figlie: [] },
        ],
    },
];
