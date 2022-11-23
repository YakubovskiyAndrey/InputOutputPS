package ua.yakubovskiy.task2.entity;

public class TrafficViolation {

    private final double fineAmount;

    private final TypeViolation type;

    public TrafficViolation(double fineAmount, TypeViolation type) {
        this.fineAmount = fineAmount;
        this.type = type;
    }
}
