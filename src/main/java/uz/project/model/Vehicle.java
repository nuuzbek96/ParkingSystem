package uz.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import uz.project.enums.VehicleType;

import java.time.LocalDateTime;
import java.util.UUID;

public class Vehicle {
    private VehicleType vehicleType;
    private String licenceNumber;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime enteredTime;
    private Long enterCurrentMile;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime leftTime;
    private int state;
    private boolean isInPark;
    private UUID parkingId;

    public Vehicle(VehicleType vehicleType, String licenceNumber,
                   LocalDateTime enteredTime, LocalDateTime leftTime, boolean isInPark,UUID parkingId,Long enterCurrentMile) {
        this.vehicleType = vehicleType;
        this.licenceNumber = licenceNumber;
        this.enteredTime = enteredTime;
        this.leftTime = leftTime;
        this.isInPark = isInPark;
        this.parkingId = parkingId;
        this.enterCurrentMile = enterCurrentMile;
    }

    public Vehicle(VehicleType vehicleType, String licenceNumber, LocalDateTime enteredTime, int state, boolean isInPark,
                   Long enterCurrentMile) {
        this.vehicleType = vehicleType;
        this.licenceNumber = licenceNumber;
        this.enteredTime = enteredTime;
        this.state = state;
        this.isInPark = isInPark;
        this.enterCurrentMile = enterCurrentMile;
    }

    public Vehicle() {
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public LocalDateTime getEnteredTime() {
        return enteredTime;
    }

    public void setEnteredTime(LocalDateTime enteredTime) {
        this.enteredTime = enteredTime;
    }

    public LocalDateTime getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(LocalDateTime leftTime) {
        this.leftTime = leftTime;
    }

    public boolean isInPark() {
        return isInPark;
    }

    public void setInPark(boolean inPark) {
        isInPark = inPark;
    }

    public UUID getParkingId() {
        return parkingId;
    }

    public void setParkingId(UUID parkingId) {
        this.parkingId = parkingId;
    }

    public Long getEnterCurrentMile() {
        return enterCurrentMile;
    }

    public void setEnterCurrentMile(Long enterCurrentMile) {
        this.enterCurrentMile = enterCurrentMile;
    }
}
