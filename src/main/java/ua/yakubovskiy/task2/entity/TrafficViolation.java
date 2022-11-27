package ua.yakubovskiy.task2.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;

@JsonAutoDetect
public class TrafficViolation {

    @JsonIgnore
    private int id;

    private TypeViolation type;

    private double fineAmount;

    public TrafficViolation(int id, TypeViolation type, double fineAmount) {
        this.id = id;
        this.type = type;
        this.fineAmount = fineAmount;
    }

    public TrafficViolation() {
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public TypeViolation getType() {
        return type;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }

    public void setType(TypeViolation type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrafficViolation that = (TrafficViolation) o;
        return id == that.id && Double.compare(that.fineAmount, fineAmount) == 0 && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fineAmount, type);
    }

}
