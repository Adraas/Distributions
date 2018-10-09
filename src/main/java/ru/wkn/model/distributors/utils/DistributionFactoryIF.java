package ru.wkn.model.distributors.utils;

public interface DistributionFactoryIF<T> {

    T createDistributorByType(String typeOfDistributor);
}
