import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class MainProgram {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Koneksi ke database
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/faktur_db", "postgres", "230973"
            );

            // Inisialisasi DAO
            FakturDAO fakturDAO = new FakturDAO(connection);
            BarangDAO barangDAO = new BarangDAO(connection);

            // Deklarasi untuk mendapatkan tanggal dan waktu saat ini
            LocalDateTime now = LocalDateTime.now();

            while (true) {
                System.out.println("Menu:");
                System.out.println("1. Tambah Faktur");
                System.out.println("2. Tampilkan Semua Faktur");
                System.out.println("3. Update Faktur");
                System.out.println("4. Hapus Faktur");
                System.out.print("Pilih menu (1-4): ");
                int pilihan = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (pilihan == 1) {
                    System.out.println("Tambah Faktur");

                    // Menampilkan daftar barang
                    List<Barang> barangs = barangDAO.getAllBarang();
                    System.out.println("Daftar Barang:");
                    for (Barang barang : barangs) {
                        System.out.println(
                                "Kode Barang: " + barang.getKodeBarang() +
                                        ", Nama Barang: " + barang.getNamaBarang() +
                                        ", Harga: " + barang.getHargaBarang()
                        );
                    }

                    // Input nomor faktur
                    String noFaktur;
                    while (true) {
                        System.out.print("Masukkan nomor faktur: ");
                        noFaktur = scanner.nextLine();
                        if (fakturDAO.fakturExists(noFaktur)) {
                            System.out.println("Nomor faktur sudah ada, masukkan nomor faktur yang berbeda.");
                        } else {
                            break;
                        }
                    }

                    // Input kode barang dan penetapan nama_barang serta harga_barang otomatis
                    String kodeBarang;
                    String namaBarang = "";
                    double hargaBarang = 0.0;

                    while (true) {
                        System.out.print("Masukkan kode barang: ");
                        kodeBarang = scanner.nextLine();

                        // Tentukan nama_barang dan harga_barang berdasarkan kodeBarang
                        if (kodeBarang.equals("N001")) {
                            namaBarang = "Pensil";
                            hargaBarang = 1000.0;  // Pastikan harga di-set
                            break;
                        } else if (kodeBarang.equals("N002")) {
                            namaBarang = "Pena";
                            hargaBarang = 4000.0;  // Pastikan harga di-set
                            break;
                        } else if (kodeBarang.equals("N003")) {
                            namaBarang = "Penghapus";
                            hargaBarang = 2000.0;  // Pastikan harga di-set
                            break;
                        } else if (kodeBarang.equals("N004")) {
                            namaBarang = "Buku";
                            hargaBarang = 10000.0; // Pastikan harga di-set
                            break;
                        } else {
                            System.out.println("Kode barang tidak valid, coba lagi.");
                        }
                    }

                    // Debugging: Pastikan hargaBarang di-set sebelum melanjutkan
                    System.out.println("Harga Barang yang dimasukkan: " + hargaBarang);

                    // Validasi hargaBarang sebelum menyimpan ke database
                    if (hargaBarang <= 0) {
                        System.out.println("Harga barang tidak valid, tidak dapat melanjutkan proses.");
                        continue;
                    }

                    // Input jumlah beli
                    int jumlahBeli = 0;
                    while (true) {
                        try {
                            System.out.print("Masukkan jumlah beli: ");
                            jumlahBeli = Integer.parseInt(scanner.nextLine());
                            if (jumlahBeli <= 0) {
                                System.out.println("Jumlah beli harus lebih besar dari 0.");
                            } else {
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Jumlah beli harus berupa angka.");
                        }
                    }

                    // Mengonversi string tanggal menjadi Timestamp
                    Timestamp timestampBeli = Timestamp.valueOf(now);

                    // Membuat objek Faktur dengan nilai yang sudah ditentukan
                    Faktur faktur = new Faktur(noFaktur, kodeBarang, namaBarang, hargaBarang, jumlahBeli, timestampBeli);
                    
                    // Pastikan hargaBarang sudah terisi sebelum menyimpan
                    if (fakturDAO.createFaktur(faktur)) {
                        System.out.println("Faktur berhasil ditambahkan.");
                    } else {
                        System.out.println("Gagal menambahkan faktur.");
                    }

                } else if (pilihan == 2) {
                    System.out.println("Tampilkan Semua Faktur");
                    List<Faktur> fakturs = fakturDAO.getAllFakturs();
                    for (Faktur faktur : fakturs) {
                        System.out.println(
                                faktur.getNoFaktur() + ", " +
                                        faktur.getKodeBarang() + ", " +
                                        faktur.getNamaBarang() + ", " +
                                        faktur.getJumlahBeli() + ", " +
                                        faktur.getHargaBarang() + ", " +
                                        faktur.getTanggalBeli()
                        );
                    }
                } else if (pilihan == 3) {
                    System.out.println("Update Faktur");

                    // Input nomor faktur yang akan diupdate
                    String noFaktur;
                    while (true) {
                        System.out.print("Masukkan nomor faktur yang akan diupdate: ");
                        noFaktur = scanner.nextLine();
                        if (!fakturDAO.fakturExists(noFaktur)) {
                            System.out.println("Nomor faktur tidak ditemukan, masukkan nomor faktur yang valid.");
                        } else {
                            break;
                        }
                    }

                    // Input kode barang baru dan jumlah beli
                    String kodeBarang;
                    String namaBarang = "";
                    double hargaBarang = 0.0;
                    while (true) {
                        System.out.print("Masukkan kode barang baru: ");
                        kodeBarang = scanner.nextLine();

                        // Tentukan nama_barang dan harga_barang berdasarkan kodeBarang
                        if (kodeBarang.equals("N001")) {
                            namaBarang = "Pensil";
                            hargaBarang = 1000.0;
                            break;
                        } else if (kodeBarang.equals("N002")) {
                            namaBarang = "Pena";
                            hargaBarang = 4000.0;
                            break;
                        } else if (kodeBarang.equals("N003")) {
                            namaBarang = "Penghapus";
                            hargaBarang = 2000.0;
                            break;
                        } else if (kodeBarang.equals("N004")) {
                            namaBarang = "Buku";
                            hargaBarang = 10000.0;
                            break;
                        } else {
                            System.out.println("Kode barang tidak valid, coba lagi.");
                        }
                    }

                    // Input jumlah beli baru
                    int jumlahBeli = 0;
                    while (true) {
                        try {
                            System.out.print("Masukkan jumlah beli baru: ");
                            jumlahBeli = Integer.parseInt(scanner.nextLine());
                            if (jumlahBeli <= 0) {
                                System.out.println("Jumlah beli harus lebih besar dari 0.");
                            } else {
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Jumlah beli harus berupa angka.");
                        }
                    }

                    // Membuat objek Faktur dengan data yang sudah diperbarui
                    Faktur faktur = new Faktur(noFaktur, kodeBarang, namaBarang, hargaBarang, jumlahBeli, Timestamp.valueOf(now));

                    // Mengupdate faktur
                    if (fakturDAO.updateFaktur(faktur)) {
                        System.out.println("Faktur berhasil diupdate.");
                    } else {
                        System.out.println("Gagal mengupdate faktur.");
                    }

                } else if (pilihan == 4) {
                    System.out.println("Hapus Faktur");

                    // Input nomor faktur yang ingin dihapus
                    String noFaktur;
                    while (true) {
                        System.out.print("Masukkan nomor faktur yang akan dihapus: ");
                        noFaktur = scanner.nextLine();
                        if (!fakturDAO.fakturExists(noFaktur)) {
                            System.out.println("Nomor faktur tidak ditemukan, masukkan nomor faktur yang valid.");
                        } else {
                            break;
                        }
                    }

                    // Konfirmasi untuk menghapus faktur
                    System.out.print("Apakah Anda yakin ingin menghapus faktur dengan nomor " + noFaktur + "? (Y/N): ");
                    String konfirmasi = scanner.nextLine();
                    
                    if (konfirmasi.equalsIgnoreCase("Y")) {
                        // Menghapus faktur
                        if (fakturDAO.deleteFaktur(noFaktur)) {
                            System.out.println("Faktur dengan nomor " + noFaktur + " berhasil dihapus.");
                        } else {
                            System.out.println("Gagal menghapus faktur.");
                        }
                    } else {
                        System.out.println("Penghapusan faktur dibatalkan.");
                    }

                } else {
                    System.out.println("Pilihan tidak valid.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}