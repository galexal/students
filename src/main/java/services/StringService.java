package services;

public class StringService {
    public static String convertIdsIntoString(String[] ids) {
        StringBuilder result = new StringBuilder();
        for (String id : ids) {
            result.append("'").append(id).append("', ");
        }
        result.setLength(result.length() - 2);
        return result.toString();
    }
}
