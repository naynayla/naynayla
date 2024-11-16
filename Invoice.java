import java.text.DecimalFormat;

// Subclass Faktur yang mewarisi Item
class Invoice extends Item {
    private String noFaktur;
    private int jumlahBeli;

    public Invoice(String noFaktur, String kodeBarang, String namaBarang, double hargaBarang, int jumlahBeli) {
        super(kodeBarang, namaBarang, hargaBarang);
        this.noFaktur = noFaktur;
        this.jumlahBeli = jumlahBeli;
    }

    // Menghitung total harga
    public double calculateTotal() {
        return hargaBarang * jumlahBeli;
    }

    // Menampilkan faktur
    public void displayInvoice() {
        DecimalFormat formatter = new DecimalFormat("Rp###,###.00");
        System.out.println("\n=== Faktur Barang ===");
        System.out.println("No Faktur   : " + noFaktur);
        System.out.println("Kode Barang : " + kodeBarang);
        System.out.println("Nama Barang : " + namaBarang);
        System.out.println("Harga Barang: " + formatter.format(hargaBarang));
        System.out.println("Jumlah Beli : " + jumlahBeli);
        System.out.println("Total       : " + formatter.format(calculateTotal()));
    }
}