package utility;

import data.Movie;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;

public class CollectionManager {

    public LinkedHashSet<Movie> moviesLinkedHashSet;
    public LinkedHashSet<Movie> savedCollection = new LinkedHashSet<>();
    public LocalDateTime dateInitialization;
    public LocalDateTime dateUpdate;
    public String link;
    int countChange;

    public CollectionManager(LinkedHashSet<Movie> moviesLinkedHashSet, LocalDateTime dateInitialization, LocalDateTime dateUpdate, int countChange, String link) {
        this.link = link;
        this.moviesLinkedHashSet = moviesLinkedHashSet;
        this.dateInitialization = dateInitialization;
        this.dateUpdate = dateUpdate;
        this.countChange = countChange;
        savedCollection.addAll(moviesLinkedHashSet);
    }

    public LinkedHashSet<Movie> getMoviesLinkedHashSet() {
        return moviesLinkedHashSet;
    }

    public void setMoviesLinkedHashSet(LinkedHashSet<Movie> moviesLinkedHashSet) {
        this.moviesLinkedHashSet = moviesLinkedHashSet;
    }

    public void setSavedCollection(LinkedHashSet<Movie> collection) {
        savedCollection = collection;
    }

    public LocalDateTime getDateInitialization() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        CharSequence dateFormat = dateInitialization.format(formatter);
        return LocalDateTime.parse(dateFormat, formatter);
    }

    public void setDateInitialization(LocalDateTime dateInitialization) {
        this.dateInitialization = dateInitialization;
    }

    public LocalDateTime getDateUpdate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        CharSequence dateFormat = dateUpdate.format(formatter);
        return LocalDateTime.parse(dateFormat, formatter);
    }

    public void setDateUpdate() {
        this.dateUpdate = LocalDateTime.now();
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
