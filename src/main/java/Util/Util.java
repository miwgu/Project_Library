package Util;

/**
 * Created by Toshiko Kuno
 * Date: 2020-12-09
 * Time: 09:47
 * Project: IntelliJ IDEA
 * Copyright: MIT
 */


public class Util {

    /**
     * Ta bort white space
     *
     * @param text t.ex Harry Potter
     * @return harrypotter
     */
    public static String removeWhiteSpace(String text) {
        String[] split = text.split(" ");
        text = "";
        for (int i = 0; i < split.length; i++) {
            if (split[i].length() != 0)
                text = text.concat(split[i]).toLowerCase();
        }
        return text;
    }
}
