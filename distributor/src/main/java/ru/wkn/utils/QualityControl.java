package ru.wkn.utils;

public class QualityControl {

    private static double significanceLevel = 0.05;

    public static double getSignificanceLevel() {
        return significanceLevel;
    }

    public static void setSignificanceLevel(double significanceLevel) {
        QualityControl.significanceLevel = significanceLevel;
    }
}
