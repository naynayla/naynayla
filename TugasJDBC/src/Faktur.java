import java.sql.Timestamp;

public class Faktur {
    private String noFaktur;
    private String kodeBarang;
    private String namaBarang;
    private double hargaBarang;
    private int jumlahBeli;
    private Timestamp tanggalBeli;

    // Constructor
    public Faktur(String noFaktur, String kodeBarang, String namaBarang, double hargaBarang, int jumlahBeli, Timestamp tanggalBeli) {
        this.noFaktur = noFaktur;
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
        this.jumlahBeli = jumlahBeli;
        this.tanggalBeli = tanggalBeli;
    }

    // Getters and Setters
    public String getNoFaktur() {
        return noFaktur;
    }

    public void setNoFaktur(String noFaktur) {
        this.noFaktur = noFaktur;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public double getHargaBarang() {
        return hargaBarang;
    }

    public void setHargaBarang(double hargaBarang) {
        this.hargaBarang = hargaBarang;
    }

    public int getJumlahBeli() {
        return jumlahBeli;
    }

    public void setJumlahBeli(int jumlahBeli) {
        this.jumlahBeli = jumlahBeli;
    }

    public Timestamp getTanggalBeli() {
        return tanggalBeli;
    }

    public void setTanggalBeli(Timestamp tanggalBeli) {
        this.tanggalBeli = tanggalBeli;
    }
}
