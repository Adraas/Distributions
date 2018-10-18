package ru.wkn.model.distributions;

public class Distribution {

    private DistributionRecord[] distributionRecords;

    public Distribution(double[] implementationsOfRandomVariable, double[] distributionTable) {
        int size = implementationsOfRandomVariable.length;
        distributionRecords = new DistributionRecord[size];
        for (int indexOfRecord = 0; indexOfRecord < size; indexOfRecord++) {
            distributionRecords[indexOfRecord] = new DistributionRecord(implementationsOfRandomVariable[indexOfRecord],
                    distributionTable[indexOfRecord]);
        }
    }

    public double[] getImplementationsOfRandomVariable() {
        int size = distributionRecords.length;
        double[] implementationsOfRandomVariables = new double[size];
        for (int indexOfRecord = 0; indexOfRecord < size; indexOfRecord++) {
            implementationsOfRandomVariables[indexOfRecord] = distributionRecords[indexOfRecord]
                    .getImplementationOfRandomVariable();
        }
        return implementationsOfRandomVariables;
    }

    public double[] getDistributionTable() {
        int size = distributionRecords.length;
        double[] distributionTable = new double[size];
        for (int indexOfRecord = 0; indexOfRecord < size; indexOfRecord++) {
            distributionTable[indexOfRecord] = distributionRecords[indexOfRecord].getProbability();
        }
        return distributionTable;
    }

    public DistributionRecord[] getDistributionRecords() {
        return distributionRecords;
    }

    private class DistributionRecord implements Comparable<DistributionRecord> {

        private double implementationOfRandomVariable;
        private double probability;

        private DistributionRecord(double implementationOfRandomVariable, double probability) {
            this.implementationOfRandomVariable = implementationOfRandomVariable;
            this.probability = probability;
        }

        public double getImplementationOfRandomVariable() {
            return implementationOfRandomVariable;
        }

        public double getProbability() {
            return probability;
        }

        @Override
        public int compareTo(DistributionRecord o) {
            return Double.compare(this.getImplementationOfRandomVariable(), o.getImplementationOfRandomVariable());
        }
    }
}
