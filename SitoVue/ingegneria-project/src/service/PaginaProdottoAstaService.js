import {getRest,postRest} from '../scripts/RestUtils.js';

async function getInfoAstaProdotto(idAsta) {
    try {
        
        const response = await getRest('http://localhost:8081/public/prodottoasta/'+idAsta);
        if (response.ok) {
            const data = await response.json();
            
            console.log(data);
        } else {
            console.error('Error Non siamo riuscti a caricare i dati del prodotto:', response.statusText);
        }
    } catch (error) {
        console.error('Error:', error);
    }
}

export {getInfoAstaProdotto};