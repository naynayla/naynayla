import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BarangDAO {
    private Connection connection;

    public BarangDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Barang> getAllBarang() {
        List<Barang> barangs = new ArrayList<>();
        String sql = "SELECT * FROM barang";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String kodeBarang = resultSet.getString("kode_barang");
                String namaBarang = resultSet.getString("nama_barang");
                double hargaBarang = resultSet.getDouble("harga_barang");
                Barang barang = new Barang(kodeBarang, namaBarang, hargaBarang);
                barangs.add(barang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return barangs;
    }
}
