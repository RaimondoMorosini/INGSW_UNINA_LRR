package api.dieti2024.testFiltri;

import api.dieti2024.dto.asta.ricerca.FiltroDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Repository
public class ProdottoRepositoryImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Object lanciaQuery(int[] idProdotti, List<List<String>> caratteristiche) {
        String where = makeWhere(idProdotti, caratteristiche,,null);
        String query= "SELECT p.id, p.nome_prodotto AS nome_prodotto \n " +
                "FROM prodotto p " +
                "               LEFT JOIN valore_specifico_di_un_prodotto v ON p.id = v.id_prodotto  " +
                " " + where +" ";

        // Creazione array di oggetti contenenti ID e valori
        Object[] params = creaArrayOrdinato(idProdotti, caratteristiche);

        return jdbcTemplate.queryForList(query, params);
//        return  params;
        //return query;
    }

    public static Object[] creaArrayOrdinato(int[] idProdotti, List<List<String>> caratteristiche) {
        List<Object> risultato = new ArrayList<>();

        // Itera sugli id dei prodotti
        for (int id =0;id< idProdotti.length; id++ ) {
            risultato.add(idProdotti[id]); // Aggiunge l'id del prodotto

            // Itera sulle caratteristiche
            for (String valore : caratteristiche.get(id)) {
                risultato.add(valore); // Aggiunge la stringa corrispondente all'id
            }
        }

        return risultato.toArray();
    }


    private String makeWhere(int[] idProdotti, List<List<String>> caratteristiche, FiltroDto filtroDto,List<Object> params) {
        String where = "WHERE p.id IN ( ";
        for (int i = 0; i < idProdotti.length; i++) {
            where +=" select id_prodotto from valore_specifico_di_un_prodotto v2 where  id_caratteristica = ? and valore in ( ";
            // ?, ?, ? ... FOR EACH ELEMENT IN caratteristiche.get(i)
            where += createQuestionMarks(caratteristiche.get(i));
            where += " )     ";

            if (i < idProdotti.length - 1) {
                where += " INTERSECT  ";
            }
        }
        where += ")  ";

        StringBuilder stringBuilder = new StringBuilder();

        // Aggiungi condizioni per categoria, tipoAsta e nomeProdotto solo se non sono
        // nulli o vuoti
        if (filtroDto.categoria() != null && !filtroDto.categoria().isEmpty() && filtroDto.categoria().equals("tutte")) {
            stringBuilder.append("categoria = ? AND \n");
            params.add(filtroDto.categoria());
        }
        // Aggiungi condizioni per tipoAsta solo se non è vuoto
        if (!filtroDto.tipoAsta().isEmpty()) {
            String puntiIterogativi = createQuestionMarks(filtroDto.tipoAsta());
            stringBuilder.append("tipo IN ("+puntiIterogativi+") AND \n");
        }

        if (filtroDto.nomeProdotto() != null && !filtroDto.nomeProdotto().isEmpty()) {
            stringBuilder.append("nome_prodotto LIKE '%").append(filtroDto.nomeProdotto()).append("%' AND ");
        }

        // Trova l'ultima occorrenza di "AND" e rimuovi l'eventuale "AND" finale
        int lastIndex = stringBuilder.lastIndexOf("AND");
        if (lastIndex != -1) {
            stringBuilder.delete(lastIndex, stringBuilder.length());
        }

        return where;
    }

    public String createQuestionMarks(List<String> list) {
        List<String> questionMarks = Collections.nCopies(list.size(), "?");
        return String.join(", ", questionMarks);
    }


}