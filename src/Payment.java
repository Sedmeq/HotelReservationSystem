import java.time.LocalDate;

public class Payment {
    public int paymentId;
    public Reservation reservation;
    public double amount;
    public LocalDate paymentDate;

    public Payment(int paymentId, Reservation reservation ,  double amount, LocalDate paymentDate ) {
        this.paymentId = paymentId;
        this.reservation = reservation;
        this.amount = amount;
        this.paymentDate = paymentDate;

    }
}
