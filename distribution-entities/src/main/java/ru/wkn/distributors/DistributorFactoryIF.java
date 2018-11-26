package ru.wkn.distributors;

public interface DistributorFactoryIF<T> {

    T createDistributorByType(String typeOfDistributor);

    static DistributorFactory getDistributorFactoryByDefault() {
        return new DistributorFactory();
    }
}
