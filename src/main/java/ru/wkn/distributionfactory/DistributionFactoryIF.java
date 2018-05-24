package ru.wkn.distributionfactory;

public interface DistributionFactoryIF<T> {

    T createDistributionByFilename(String prefixName);
}
