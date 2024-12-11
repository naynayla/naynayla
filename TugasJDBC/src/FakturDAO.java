import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FakturDAO {
    private Connection connection;

    // Constructor untuk inisialisasi koneksi
    public FakturDAO(Connection connection) {
        this.connection = connection;
    }

    // Menambahkan faktur baru ke database
    public boolean createFaktur(Faktur faktur) {
        String sql = "INSERT INTO faktur (no_faktur, kode_barang, nama_barang, harga_barang, jumlah_beli, tanggal_beli) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, faktur.getNoFaktur());
            statement.setString(2, faktur.getKodeBarang());
            statement.setString(3, faktur.getNamaBarang());
            statement.setDouble(4, faktur.getHargaBarang());
            statement.setInt(5, faktur.getJumlahBeli());
            statement.setTimestamp(6, faktur.getTanggalBeli());
            
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Mengambil semua faktur dari database
    public List<Faktur> getAllFakturs() {
        List<Faktur> fakturs = new ArrayList<>();
        String sql = "SELECT * FROM faktur";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            
            while (resultSet.next()) {
                Faktur faktur = new Faktur(
                    resultSet.getString("no_faktur"),
                    resultSet.getString("kode_barang"),
                    resultSet.getString("nama_barang"),
                    resultSet.getDouble("harga_barang"),
                    resultSet.getInt("jumlah_beli"),
                    resultSet.getTimestamp("tanggal_beli")
                );
                fakturs.add(faktur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fakturs;
    }

    // Mengambil faktur berdasarkan nomor faktur
    public Faktur getFakturByNo(String noFaktur) {
        String sql = "SELECT * FROM faktur WHERE no_faktur = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, noFaktur);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Faktur(
                        resultSet.getString("no_faktur"),
                        resultSet.getString("kode_barang"),
                        resultSet.getString("nama_barang"),
                        resultSet.getDouble("harga_barang"),
                        resultSet.getInt("jumlah_beli"),
                        resultSet.getTimestamp("tanggal_beli")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Mengupdate faktur
    public boolean updateFaktur(Faktur faktur) {
        String sql = "UPDATE faktur SET kode_barang = ?, nama_barang = ?, harga_barang = ?, jumlah_beli = ?, tanggal_beli = ? " +
                     "WHERE no_faktur = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, faktur.getKodeBarang());
            statement.setString(2, faktur.getNamaBarang());
            statement.setDouble(3, faktur.getHargaBarang());
            statement.setInt(4, faktur.getJumlahBeli());
            statement.setTimestamp(5, faktur.getTanggalBeli());
            statement.setString(6, faktur.getNoFaktur());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Menghapus faktur berdasarkan nomor faktur
    public boolean deleteFaktur(String noFaktur) {
        String sql = "DELETE FROM faktur WHERE no_faktur = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, noFaktur);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cek apakah faktur sudah ada berdasarkan nomor faktur
    public boolean fakturExists(String noFaktur) {
        return getFakturByNo(noFaktur) != null;
    }
}
