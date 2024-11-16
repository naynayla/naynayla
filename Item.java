// Kelas dasar untuk barang (Inheritance)
class Item {
    protected String kodeBarang;
    protected String namaBarang;
    protected double hargaBarang;

    public Item(String kodeBarang, String namaBarang, double hargaBarang) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
    }
}