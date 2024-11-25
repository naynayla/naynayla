import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Supermarket {
    private String namaSupermarket;
    private String namaKasir;

    public Supermarket(String namaSupermarket, String namaKasir) {
        this.namaSupermarket = namaSupermarket;
        this.namaKasir = namaKasir;
    }

    public void tampilkanInfo() {
        System.out.println("\n+-----------------------------------------------------+");
        System.out.println("Selamat Datang di Supermarket " + namaSupermarket);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Tanggal dan Waktu: " + now.format(formatter));
    }

    public String getNamaKasir() {
        return namaKasir;
    }
}
