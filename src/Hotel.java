
// Hotel.java - Düzəldilmiş
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hotel {
    private List<Room> rooms;
    private List<Reservation> reservations;

    public Hotel() {
        this.rooms = new ArrayList<>();  // Düzəliş: new ArrayList<>() əlavə edildi
        this.reservations = new ArrayList<>();  // Düzəliş: new ArrayList<>() əlavə edildi
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
        System.out.println("Room " + room.getRoomNumber() + " added"); // Düzəliş: boşluq əlavə edildi
    }

    public void displayRooms() {
        if (rooms.isEmpty()) {
            System.out.println("Heç bir otaq mövcud deyil.");
            return;
        }

        for (Room room : rooms) {
            System.out.println(room.getRoomNumber()
                    + " " + room.getRoomType()
                    + " " + room.getPrice()
                    + " " + (room.isAvailable() ? "Boş" : "Dolu")); // Düzəliş: "Bos" -> "Boş"
        }
    }

    // Düzəliş: Otağı nömrəyə görə tapmaq üçün metod əlavə edildi
    public Room getRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    public List<Room> findAvailableRooms(LocalDate checkIn, LocalDate checkOut, String roomType) {
        return rooms.stream()
                .filter(room -> room.isAvailable() && room.getRoomType().equalsIgnoreCase(roomType))
                .collect(Collectors.toList());
    }

    public void makeReservation(Reservation reservation) {
        reservation.getRoom().setAvailable(false);
        this.reservations.add(reservation);
        System.out.println("Rezervasiya yaradıldı. ID: " + reservation.getReservationId()); // Düzəliş: mətn əlavə edildi
    }

    public boolean cancelReservation(int reservationId) {
        Reservation reservationToCancel = null;
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId() == reservationId) {
                reservationToCancel = reservation;
                break;
            }
        }

        if (reservationToCancel != null) {
            reservationToCancel.getRoom().setAvailable(true);
            reservations.remove(reservationToCancel);
            System.out.println(reservationId + " ID-li rezervasiya ləğv edildi.");
            return true;
        } else {
            System.out.println("Bu ID ilə rezervasiya tapılmadı.");
            return false;
        }
    }

    public Reservation getReservation(int reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId() == reservationId) {
                return reservation;
            }
        }
        return null;
    }

    public void viewAllReservations() {
        System.out.println("--- Bütün Rezervasiyalar ---");
        if (reservations.isEmpty()) {
            System.out.println("Aktiv rezervasiya yoxdur.");
            return;
        }
        for (Reservation res : reservations) {
            System.out.println("ID: " + res.getReservationId() +
                    ", Müştəri: " + res.getCustomer().getName() +
                    ", Otaq No: " + res.getRoom().getRoomNumber() +
                    ", Giriş: " + res.getCheckInTime() +
                    ", Çıxış: " + res.getCheckOutTime());
        }
    }
}
