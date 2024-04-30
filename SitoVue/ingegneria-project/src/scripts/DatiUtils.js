// Funzione per controllare se il dato è presente nei cookie
function checkEsisteDato(nomeDato) {
  const valoreCookie = getDato(nomeDato);
  return !!valoreCookie; // Restituisce true se il cookie ha un valore, false altrimenti
}

// Funzione per ottenere un cookie dal nome specificato
function getDato(nomeCookie) {
  const cookies = document.cookie.split(';');
  for (let i = 0; i < cookies.length; i++) {
    const cookie = cookies[i].trim();
    if (cookie.startsWith(nomeCookie + '=')) {
      return cookie.split('=')[1]; // Restituisce il valore del cookie
    }
  }
  return null; // Se il cookie non esiste, restituisce null
}

// Funzione per inserire un cookie di sessione
function inserisciDato(nome, valore) {
  document.cookie = nome + '=' + valore + ';path=/';
}
// Funzione per eliminare un cookie
function eliminaDato(nomeCookie) {
  document.cookie = nomeCookie + '=; expires=Thu, 01 Jan 1970 00:00:00 UTC';
}
//dataScadenza deve essere un oggetto Date
function setScadenza(nomeCookie, dataScadenza) {
  document.cookie = nomeCookie + '=; expires=' + dataScadenza.toUTCString();
}
export { checkEsisteDato, inserisciDato, getDato, eliminaDato };
