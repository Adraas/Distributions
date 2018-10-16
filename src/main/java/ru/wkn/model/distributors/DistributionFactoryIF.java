package ru.wkn.model.distributors;

public interface DistributionFactoryIF<T> {

    T createDistributorByType(String typeOfDistributor);
}
