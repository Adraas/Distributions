package ru.wkn.utils;

public interface DistributionFactoryIF<T> {

    T createDistributionByFilenameOfProperty(String typeOfDistributor);
}
