package ru.wkn.streams;

public interface StreamFactoryIF<T> {

    T createStreamByType(String typeOfStream);

    static StreamFactory getStreamFactoryByDefault() {
        return new StreamFactory();
    }
}
