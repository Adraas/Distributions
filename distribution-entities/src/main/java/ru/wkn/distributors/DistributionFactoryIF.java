package ru.wkn.distributors;

public interface DistributionFactoryIF<T> {

    T createDistributorByType(String typeOfDistributor);
}
