package utility;


import data.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class MovieFactory {
    private Long id;
    HashSet hashSetId;
    LinkedHashSet<Movie> collectionForWork;
    CollectionManager collectionManager;


    public MovieFactory(HashSet hashSetId, CollectionManager collectionManager) {
        this.hashSetId = hashSetId;
        this.collectionManager = collectionManager;
    }

    public Long getId() {
        return id;
    }

    public LinkedHashSet<Movie> getCollectionForWork() {
        return collectionManager.getMoviesLinkedHashSet();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HashSet getHashSetId() {
        return hashSetId;
    }

    public void setHashSetId(HashSet hashSetId) {
        this.hashSetId = hashSetId;
    }

    public void setCollectionForWork(LinkedHashSet<Movie> collectionForWork) {
        this.collectionForWork = collectionForWork;
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public Movie GetMovieFromConsole() {
        long id;
        String movieName;
        Coordinates coordinates;
        int oscarsCount;
        MovieGenre genre;
        MpaaRating mpaaRating;
        Person director;
        int coordinatesX;
        Integer coordinatesY;
        String personName;
        Float weight;
        Color eyeColor;
        Country nationality;
        Location location;
        Integer locationX;
        Float locationY;
        String locationName;

        id = Validator.autoCreatAndCheckId(hashSetId);
        movieName = Validator.validatorName();
        coordinatesX = Validator.validatorCoordinateX();
        coordinatesY = Validator.validatorCoordinateY();
        coordinates = new Coordinates(coordinatesX, coordinatesY);
        LocalDateTime creationDate = Validator.autoCreatAndCheckDate();
        oscarsCount = Validator.validatorOscarsCount();
        genre = Validator.validatorMovieGenre();
        mpaaRating = Validator.validatorMpaaRating();
        personName = Validator.validatorName();
        weight = Validator.validatorWeight();
        eyeColor = Validator.validatorColor();
        nationality = Validator.validatorCountry();
        locationName = Validator.validatorName();
        locationX = Validator.validatorLocationX();
        locationY = Validator.validatorLocationY();
        location = new Location(locationX, locationY, locationName);
        director = new Person(personName, weight, eyeColor, nationality, location);
        return new Movie(id, movieName, coordinates, creationDate, oscarsCount, genre, mpaaRating, director);

    }
}
