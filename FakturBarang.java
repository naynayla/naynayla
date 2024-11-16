import java.util.Scanner; // Import Scanner

public class FakturBarang {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Input No Faktur
            System.out.print("Masukkan No Faktur: ");
            String noFaktur = scanner.nextLine();

            // Validasi input kode barang
            System.out.print("Masukkan Kode Barang: ");
            String kodeBarang;
            while ((kodeBarang = scanner.nextLine().trim()).isEmpty()) {
                System.out.println("Kode Barang tidak boleh kosong. Silakan masukkan ulang.");
            }

            // Validasi input nama barang
            System.out.print("Masukkan Nama Barang: ");
            String namaBarang;
            while ((namaBarang = scanner.nextLine().trim()).isEmpty()) {
                System.out.println("Nama Barang tidak boleh kosong. Silakan masukkan ulang.");
            }

            // Validasi harga barang
            double hargaBarang = 0;
            while (true) {
                try {
                    System.out.print("Masukkan Harga Barang: ");
                    hargaBarang = Double.parseDouble(scanner.nextLine());
                    if (hargaBarang <= 0) {
                        System.out.println("Harga Barang harus lebih besar dari 0.");
                    } else {
                        break;  // Keluar dari loop jika harga valid
                    }
                } catch (NumberFormatException e) {
                    // Exception Handling untuk input non-numerik
                    System.out.println("Harga Barang harus berupa angka. Silakan masukkan ulang.");
                }
            }

            // Validasi jumlah beli
            int jumlahBeli = 0;
            while (true) {
                try {
                    System.out.print("Masukkan Jumlah Beli: ");
                    jumlahBeli = Integer.parseInt(scanner.nextLine());
                    if (jumlahBeli <= 0) {
                        System.out.println("Jumlah Beli harus lebih besar dari 0.");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    // Exception Handling untuk input non-numerik
                    System.out.println("Jumlah Beli harus berupa angka. Silakan masukkan ulang.");
                }
            }

            // Membuat objek Invoice
            Invoice invoice = new Invoice(noFaktur, kodeBarang, namaBarang, hargaBarang, jumlahBeli);

            // Menampilkan faktur
            invoice.displayInvoice();

        } catch (Exception e) {
            // Exception Handling untuk kesalahan umum
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}