
// Main.java - Düzəldilmiş
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Scanner scanner = new Scanner(System.in);

        // Test üçün məlumatlar əlavə et
        hotel.addRoom(new Room(101, "Standard", 75.0, true));
        hotel.addRoom(new Room(102, "Standard", 75.0, true));
        hotel.addRoom(new Room(201, "Deluxe", 120.0, true));
        hotel.addRoom(new Room(301, "Suite", 200.0, true));

        System.out.println("Otel Rezervasiya Sisteminə Xoş Gəlmisiniz!");

        while (true) {
            System.out.println("\n--- Əsas Menyu ---");
            System.out.println("1. Bütün Otaqları Göstər");
            System.out.println("2. Boş Otaqları Axtar");
            System.out.println("3. Rezervasiya Et");
            System.out.println("4. Rezervasiyanı Ləğv Et");
            System.out.println("5. Bütün Rezervasiyalara Bax");
            System.out.println("0. Çıxış");
            System.out.print("Seçiminizi daxil edin: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Boşluğu aradan qaldırmaq üçün

                switch (choice) {
                    case 1:
                        hotel.displayRooms();
                        break;
                    case 2:
                        searchAvailableRooms(hotel, scanner);
                        break;
                    case 3:
                        makeReservation(hotel, scanner);
                        break;
                    case 4:
                        cancelReservation(hotel, scanner);
                        break;
                    case 5:
                        hotel.viewAllReservations();
                        break;
                    case 0:
                        System.out.println("Sistemdən çıxılır. Təşəkkürlər!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Yanlış seçim! Zəhmət olmasa menyudan bir rəqəm daxil edin.");
                }
            } catch (Exception e) {
                System.out.println("Xəta baş verdi: " + e.getMessage());
                scanner.nextLine(); // Buffer-i təmizləmək
            }
        }
    }

    // Düzəliş: Boş otaq axtarışı üçün ayrı metod
    private static void searchAvailableRooms(Hotel hotel, Scanner scanner) {
        try {
            System.out.print("Giriş tarixi (YYYY-MM-DD): ");
            LocalDate checkIn = LocalDate.parse(scanner.nextLine());
            System.out.print("Çıxış tarixi (YYYY-MM-DD): ");
            LocalDate checkOut = LocalDate.parse(scanner.nextLine());
            System.out.print("Otaq növü (Standard, Deluxe, Suite): ");
            String roomType = scanner.nextLine();

            List<Room> availableRooms = hotel.findAvailableRooms(checkIn, checkOut, roomType);
            if (availableRooms.isEmpty()) {
                System.out.println("Təəssüf ki, bu kriteriyalara uyğun boş otaq tapılmadı.");
            } else {
                System.out.println("Uyğun Otaqlar:");
                availableRooms.forEach(room ->
                        System.out.println("Otaq No: " + room.getRoomNumber() +
                                ", Tip: " + room.getRoomType() +
                                ", Qiymət: $" + room.getPrice()));
            }
        } catch (DateTimeParseException e) {
            System.out.println("Xətalı tarix formatı! Zəhmət olmasa YYYY-MM-DD formatında daxil edin.");
        }
    }

    // Düzəliş: Rezervasiya etmək üçün tam funksional metod
    private static void makeReservation(Hotel hotel, Scanner scanner) {
        try {
            System.out.print("Müştəri ID: ");
            int custId = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Müştərinin adı: ");
            String custName = scanner.nextLine();
            System.out.print("Əlaqə məlumatı: ");
            String custContact = scanner.nextLine();
            Customer customer = new Customer(custId, custName, custContact);

            System.out.print("Rezerv etmək istədiyiniz otağın nömrəsi: ");
            int roomNum = scanner.nextInt();
            scanner.nextLine();

            // Otağı tap
            Room roomToBook = hotel.getRoomByNumber(roomNum);
            if (roomToBook == null) {
                System.out.println("Bu nömrədə otaq tapılmadı.");
                return;
            }

            if (!roomToBook.isAvailable()) {
                System.out.println("Bu otaq artıq rezerv edilib.");
                return;
            }

            System.out.print("Giriş tarixi (YYYY-MM-DD): ");
            LocalDate checkInDate = LocalDate.parse(scanner.nextLine());
            System.out.print("Çıxış tarixi (YYYY-MM-DD): ");
            LocalDate checkOutDate = LocalDate.parse(scanner.nextLine());

            // Tarixləri yoxla
            if (checkInDate.isAfter(checkOutDate)) {
                System.out.println("Giriş tarixi çıxış tarixindən sonra ola bilməz!");
                return;
            }

            // Yeni Rezervasiya yarat
            int reservationId = (int) (System.currentTimeMillis() % 100000);
            Reservation newReservation = new Reservation(reservationId, customer, roomToBook, checkInDate, checkOutDate);

            hotel.makeReservation(newReservation);
            System.out.println("Rezervasiya uğurla yaradıldı!");
            System.out.println("Rezervasiya ID: " + reservationId);
            System.out.println("Ümumi məbləğ: $" + roomToBook.getPrice());

        } catch (DateTimeParseException e) {
            System.out.println("Xətalı tarix formatı! Zəhmət olmasa YYYY-MM-DD formatında daxil edin.");
        } catch (Exception e) {
            System.out.println("Rezervasiya yaradılarkən xəta: " + e.getMessage());
        }
    }

    // Düzəliş: Rezervasiya ləğvi üçün ayrı metod
    private static void cancelReservation(Hotel hotel, Scanner scanner) {
        try {
            System.out.print("Ləğv etmək istədiyiniz rezervasiyanın ID-sini daxil edin: ");
            int resIdToCancel = scanner.nextInt();
            hotel.cancelReservation(resIdToCancel);
        } catch (Exception e) {
            System.out.println("ID daxil edərkən xəta: " + e.getMessage());
            scanner.nextLine(); // Buffer-i təmizlə
        }
    }
}