import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Inisialisasi user dan supermarket
            User user = new User("admin", "password123");
            Supermarket supermarket = new Supermarket("Supermarket Bersama", "Dina");

            // Proses login
            boolean isLoggedIn = false;
            while (!isLoggedIn) {
                isLoggedIn = user.login(scanner); // Scanner diteruskan sebagai parameter
            }

            // Setelah login berhasil, lanjutkan program
            supermarket.tampilkanInfo();

            // Input data barang
            System.out.println("+-----------------------------------------------------+");
            System.out.print("No. Faktur: ");
            String noFaktur = scanner.nextLine().trim();
            System.out.print("Kode Barang: ");
            String kodeBarang = scanner.nextLine().trim();
            System.out.print("Nama Barang: ");
            String namaBarang = scanner.nextLine().trim();

            // Input Harga Barang
            double hargaBarang = 0;
            while (true) {
                try {
                    System.out.print("Harga Barang: ");
                    hargaBarang = Double.parseDouble(scanner.nextLine().trim());
                    if (hargaBarang < 0) {
                        System.out.println("Harga barang tidak boleh negatif. Silakan masukkan ulang.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Input tidak valid. Masukkan angka untuk harga barang.");
                }
            }

            // Input Jumlah Beli
            int jumlahBeli = 0;
            while (true) {
                try {
                    System.out.print("Jumlah Beli: ");
                    jumlahBeli = Integer.parseInt(scanner.nextLine().trim());
                    if (jumlahBeli < 0) {
                        System.out.println("Jumlah beli tidak boleh negatif. Silakan masukkan ulang.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Input tidak valid. Masukkan angka untuk jumlah beli.");
                }
            }

            // Buat objek barang dan tampilkan detail pembelian
            Barang barang = new Barang(noFaktur, kodeBarang, namaBarang, hargaBarang, jumlahBeli);
            barang.tampilkanDetail();

            // Tampilkan nama kasir
            System.out.println("Kasir           : " + supermarket.getNamaKasir());
            System.out.println("+-----------------------------------------------------+");
        }
    }
}
