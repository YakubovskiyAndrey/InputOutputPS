package ua.yakubovskiy.task2.entity;

import java.time.LocalDateTime;

public class TrafficViolation {

    private int id;

    private LocalDateTime dateTime;

    private String firstName;

    private String lastName;

    private double fineAmount;

    private TypeViolation type;


    public TrafficViolation() {
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public TypeViolation getType() {
        return type;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }

    public void setType(TypeViolation type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TrafficViolation{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fineAmount=" + fineAmount +
                ", type=" + type +
                '}';
    }
}
