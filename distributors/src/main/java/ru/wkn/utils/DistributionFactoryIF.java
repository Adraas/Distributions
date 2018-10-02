package ru.wkn.utils;

public interface DistributionFactoryIF<T> {

    T createDistributorByType(String typeOfDistributor);
}
