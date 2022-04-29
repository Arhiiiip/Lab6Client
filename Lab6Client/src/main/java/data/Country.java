package data;

public enum Country {
    RUSSIA,
    GERMANY,
    FRANCE;

    public static boolean isIncludeElement(String aCountry) {
        for (Country current : Country.values()) {
            if (current.equals(aCountry.toUpperCase())) return true;
        }
        return false;
    }

    public boolean equals(String arg) {
        Country country = this;
        String genreForEquals = String.valueOf(country);
        return (genreForEquals.equals(arg));
    }
}
