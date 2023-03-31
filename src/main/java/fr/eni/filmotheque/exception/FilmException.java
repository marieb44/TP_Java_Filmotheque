package fr.eni.filmotheque.exception;

import java.util.ArrayList;
import java.util.List;

public class FilmException extends Exception {

    private List<String> errors;

    public List<String> getErrors() {
        return errors;
    }

    public FilmException() {
        super();
        errors = new ArrayList<>();
    }

    public void addError(String msg) {
        errors.add(msg);
    }

    public boolean hasError() {
        return !errors.isEmpty();
    }
}
