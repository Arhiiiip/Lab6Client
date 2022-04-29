package data;

/**
 * Какие цвета могут быть
 */
public enum Color {
    /** Зелёный */
    GREEN,
    RED,
    BLACK,
    BLUE,
    ORANGE;

    public static boolean isIncludeElement(String aColor) {
        for (Color current : Color.values()) {
            if (current.equals(aColor.toUpperCase())) return true;
        }
        return false;
    }

    public boolean equals(String arg) {
        Color color = this;
        String genreForEquals = String.valueOf(color);
        return (genreForEquals.equals(arg));
    }
}
