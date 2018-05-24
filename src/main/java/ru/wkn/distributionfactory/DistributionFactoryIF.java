package ru.wkn.distributionfactory;

public interface DistributionFactoryIF<T> {

    T createDistribution(String prefixName);
}
