package api.dieti2024.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


public class CalendarioUtil {

    public static boolean verificaIntevalloDate(long start, long end) {
        long current = GetTime();
        return  current <start && start <end ;
    }
    public static boolean verificaScadenza (long tempoInputi, long dataDiScadenza) {
        return  tempoInputi <dataDiScadenza ;
    }

    /**
     * Restituisce il tempo attuale in millisecondi
     * @return il tempo attuale in millisecondi
     */
    public static long GetTime() {
    return  Instant.now().toEpochMilli();

    }
    /**
     * Restituisce il tempo in millisecondi di una data
     * @param giorno il giorno
     * @param mese il mese
     * @param anno l'anno
     * @param ora l'ora
     * @param minuti i minuti
     * @return il tempo in millisecondi
     */
    public static long getTime(int giorno, int mese, int anno, int ora, int minuti) {
        LocalDateTime dataOra = LocalDateTime.of(anno, mese, giorno, ora, minuti);
        return dataOra.toInstant(ZoneOffset.UTC).toEpochMilli();
    }
}
