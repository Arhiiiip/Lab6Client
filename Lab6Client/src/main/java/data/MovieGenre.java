package data;

public enum MovieGenre {
    WESTERN,
    DRAMA,
    TRAGEDY,
    SCIENCE_FICTION;

    public static boolean isIncludeElement(String aMovieGenre) {
        for (MovieGenre current : MovieGenre.values()) {
            if (current.equals(aMovieGenre.toUpperCase())) return true;
        }
        return false;
    }

    public boolean equals(String arg) {
        MovieGenre genre = this;
        String genreForEquals = String.valueOf(genre);
        return (genreForEquals.equals(arg));
    }
}
