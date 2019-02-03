package cheapFlights.helpers;

public class StringHelper {
    public static String removeMb4Chars(String s) {
        if(notEmpty(s)) {
            return s.replaceAll("[^\\u0000-\\uFFFF]", "\uFFFD");
        }
        return s;
    }

    public static boolean notEmpty(String s) {
        return (s != null && !"".equals(s));
    }
}
