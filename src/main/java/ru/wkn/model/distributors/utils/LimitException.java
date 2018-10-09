package ru.wkn.model.distributors.utils;

public class LimitException extends Exception {

    public LimitException() {}

    public LimitException(String message) {
        super(message);
    }
}
