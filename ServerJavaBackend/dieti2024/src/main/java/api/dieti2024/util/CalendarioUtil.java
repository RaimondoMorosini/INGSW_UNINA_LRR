package api.dieti2024.util;

import java.util.Date;

public class CalendarioUtil {

    public static boolean verificaIntevalloDate(long start, long end) {
        long current = GetTime();
        return  current <start <end
    }

    public static long GetTime() {
    return new Date().getTime();
    }
}
