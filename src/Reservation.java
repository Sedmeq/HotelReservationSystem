import java.time.LocalDate;

public class Reservation {
    private int reservationId;
    private Customer customer;
    private Room room;
    private LocalDate checkInTime;
    private LocalDate checkOutTime;

    public Reservation(int reservationId, Customer customer, Room room, LocalDate checkInTime, LocalDate checkOutTime) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.room = room;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public LocalDate getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalDate checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public LocalDate getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalDate checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
