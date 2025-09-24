import java.time.LocalDate;

public class Payment {
    private int paymentId;
    private Reservation reservation;
    private double amount;
    private LocalDate paymentDate;

    public Payment(int paymentId, Reservation reservation ,  double amount, LocalDate paymentDate ) {
        this.paymentId = paymentId;
        this.reservation = reservation;
        this.amount = amount;
        this.paymentDate = paymentDate;

    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
}
