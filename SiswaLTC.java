import java.sql.*;

//kelas anak
public class SiswaLTC extends PendaftaranBimbel {
    static Connection conn;
    static String link = "jdbc:mysql://localhost:3306/bimbel";

    String id_siswa, Pembayaran, status;
    Integer harga, total_biaya, dibayar, sisa, kembalian;

    @Override
    public void view () throws SQLException {
        System.out.println("\n======Data Seluruh Siswa LTC======");
        // mengakses database
        String sql = "SELECT * FROM siswa";
        conn = DriverManager.getConnection(link, "root", "");
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        // percabangan while
        while (result.next()) {
            // menampilkan dari database
            System.out.print("\nNama Siswa Bimbel     : ");
            System.out.println(result.getString("nama_siswa"));
            System.out.print("ID Siswa        : ");
            System.out.println(result.getString("id_siswa"));
            System.out.print("Kelas           : ");
            System.out.println(result.getString("kelas"));
            System.out.print("Paket           : ");
            System.out.println(result.getString("paket"));
            System.out.print("Total Biaya     : ");
            System.out.println(result.getInt("total_biaya"));
            System.out.print("Pembayaran      : ");
            System.out.println(result.getString("pembayaran"));
            System.out.print("Status          : ");
            System.out.println(result.getString("status"));
            System.out.print("Sisa Pembayaran : ");
            System.out.println(result.getInt("sisa"));
            System.out.print("\n");
        }
        statement.close();
    }

    @Override
    public void update() throws SQLException {
        String text2 = "\n====Update Data Siswa====";
        System.out.println(text2.toUpperCase());

        try {
            view();
            System.out.print("\nMasukkan ID Siswa yang akan diupdate : ");
            String id_siswa = input.nextLine();

            String sql = "SELECT * FROM siswa WHERE id_siswa = '" + id_siswa + "'";
            conn = DriverManager.getConnection(link, "root", "");
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            if (result.next()) {

                System.out.print("Nama baru [" + result.getString("nama_siswa") + "]\t: ");
                String nama_siswa = input.nextLine();

                sql = "UPDATE siswa SET nama_siswa ='" + nama_siswa + "' WHERE id_siswa = '" + id_siswa + "'";
                // System.out.println(sql);

                if (statement.executeUpdate(sql) > 0) {

                    System.out.println("\nBerhasil memperbaharui data siswa dengan id " + id_siswa + "");
                }
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println("\nTerjadi kesalahan dalam mengedit data\n");
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete() {
        String text2 = "\n=======Hapus Data Siswa=======";
        System.out.println(text2.toUpperCase());

        try {
            view();
            System.out.print("Masukkan ID Siswa yang akan dihapus : ");
            String id_siswa = input.nextLine();

            String sql = "DELETE FROM siswa WHERE id_siswa = '" + id_siswa + "'";
            conn = DriverManager.getConnection(link, "root", "");
            Statement statement = conn.createStatement();

            if (statement.executeUpdate(sql) > 0) {
                System.out.println("\nBerhasil menghapus data Siswa dengan ID " + id_siswa + "" + "\n");
            }
        }
        // exeption
        catch (SQLException e) {
            System.out.println("Terjadi kesalahan dalam menghapus data\n");
        } catch (Exception e) {
            System.out.println("masukan data yang benar\n");
        }
    }

    public void save() {
    }
}

