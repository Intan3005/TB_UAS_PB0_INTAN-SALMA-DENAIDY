import java.util.Scanner;
import java.util.InputMismatchException;
import java.sql.*;

//kelas induk dan implement
public class PendaftaranBimbel {
    // koneksi database
    static Connection conn;
    static String url = "jdbc:mysql://localhost:3306/bimbel";
    String nama_siswa, id_siswa, kelas, paket, cek, status, pembayaran;
    Integer harga, total_biaya, dibayar, sisa, kembalian;
    Double diskon;

    Scanner input = new Scanner(System.in);

    public void display() {
        System.out.println("\n------------Data Siswa Baru------------");
        System.out.println("Nama Siswa    : " + this.nama_siswa);
        System.out.println("ID Siswa    : " + this.id_siswa);
        System.out.println("Kelas       : " + this.kelas);
        System.out.println("Paket       : " + this.paket);
        System.out.println("Total Biaya : " + this.total_biaya);
    }

    // method implementasi dari interface
    public void daftar() throws SQLException {
        String text = "\n==========Pendaftaran Siswa baru==========";
        System.out.println(text.toUpperCase());

        try {
            // nama siswa
            System.out.print("Masukkan Nama Siswa : ");
            nama_siswa = input.nextLine();

            // ID siswa
            System.out.print("Masukkan ID Siswa   : ");
            id_siswa = input.nextLine();

            // Kelas
            System.out.print("Masukkan Kelas      : ");
            kelas = input.nextLine();

            // Pilihan Paket
            Integer pilihanPaket;
            System.out.println("\n------------Daftar Paket Bimbel------------");
            System.out.println("1. Basic 1,2,3 SD ");
            System.out.println("2. Intermediate SMP ");
            System.out.println("3. Advance SMA ");
            System.out.println("4. Conversation Class Mahasiswa dan Umum");
            System.out.print("> Pilihan Paket (1 - 4) : ");
            pilihanPaket = input.nextInt();

            if (pilihanPaket == 1) {
                paket = "Basic 1,2,3 SD ";
            } else if (pilihanPaket == 2) {
                paket = "Intermediate SMP";
            } else if (pilihanPaket == 3) {
                paket = "Advance SMA";
            } else if (pilihanPaket == 4) {
                paket = "Conversation Class Mahasiswa dan Umum";
            } else {
                System.out.println("\nPaket tidak tersedia");
            }
            System.out.println("\nPaket\t\t   : " + paket.toUpperCase());

            // harga
            if (paket == "Basic 1,2,3 SD ") {
                harga = 1000000;
            } else if (paket == "Intermediate SMP ") {
                harga = 2000000;
            } else if (paket == "Advance SMA ") {
                harga = 3000000;
            } else if (paket == "Conversation Class Mahasiswa dan Umum") {
                harga = 4000000;
            } else {
                System.out.println("\nHarga tidak tersedia");
            }
            System.out.println("Harga Paket\t   : Rp" + harga);

            // Potongan diskon
            diskon = harga * 0.15; // proses matematika
            System.out.println("Diskon\t\t   : Rp" + diskon);

            // Totalbiaya
            this.total_biaya = (int) (harga - diskon);
            System.out.println("Total yang dibayar : Rp" + this.total_biaya);

            System.out.println("\nBerhasil Menambahkan Siswa Baru!!!");

            display();

            System.out.println("\nSilahkan Lanjutkan Pembayaran!!!\n");
            String pilih;
            boolean tanya = true;
            while (tanya) {
                System.out.print("\n> Apakah Anda ingin melakukan Pembayaran [y/n]? ");
                pilih = input.next();

                // percabangan if else if
                if (pilih.equalsIgnoreCase("n")) // method string
                {
                    tanya = false;
                } else if (pilih.equalsIgnoreCase("y")) // method string
                {
                    System.out.print("\nMasukkan Jumlah uang : ");
                    this.dibayar = input.nextInt();
                    tanya = true;
                } else {
                    System.out.println("Inputkan 'y' atau 'n' saja");
                }

                display();

                if (this.dibayar < this.total_biaya) {
                    pembayaran = "Belum Lunas";
                    // proses matematika
                    this.sisa = this.total_biaya - this.dibayar;
                    System.out.println("\nPembayaran\t: " + pembayaran);
                    System.out.println("Sisa Pembayaran : Rp" + this.sisa + "");
                } else if (this.dibayar == this.total_biaya) {
                    pembayaran = "Lunas";
                    // proses matematika
                    this.sisa = this.dibayar - this.total_biaya;
                    System.out.println("\nPembayaran\t: " + pembayaran);
                    System.out.println("Sisa Pembayaran : Rp" + this.sisa + "");
                } else if (this.dibayar > this.total_biaya) {
                    pembayaran = "Lunas";
                    this.sisa = 0;
                    // proses matematika
                    kembalian = this.dibayar - this.total_biaya;
                    System.out.println("\nPembayaran\t: " + pembayaran);
                    System.out.println("Kembalian\t: " + kembalian);
                    System.out.println("Sisa Pembayaran : Rp" + this.sisa + "");
                } else {
                    System.out.println("\nPembayaran tidak tersedia\n");
                }

                // Status
                if (pembayaran == "Lunas") {
                    status = "Aktif";
                    System.out.println("Status\t\t: " + status + "\n");
                } else if (pembayaran == "Belum Lunas") {
                    status = "Belum Aktif";
                    System.out.println("Status\t\t: " + status);
                    System.out.println("\nMohon Lunaskan Pembayaran!!!\n");
                } else {
                    System.out.println("\n Status tidak tersedia\n");
                }

                String sql = "INSERT INTO siswa (nama_siswa, id_siswa, kelas, paket, total_biaya, pembayaran, sisa, status ) VALUES ('"
                        + nama_siswa + "','" + id_siswa + "', '" + kelas + "','" + paket + "','" + total_biaya + "','"
                        + pembayaran + "','" + sisa + "','" + status + "')";
                conn = DriverManager.getConnection(url, "root", "");
                Statement statement = conn.createStatement();
                statement.execute(sql);
                System.out.println("Pembayaran baru telah ditambahkan!!!");
            }
        }
        /// exception
        catch (SQLException e) {
            System.err.println("\nTerjadi kesalahan input data");
        } catch (InputMismatchException e) {
            System.err.println("\nInputan harus berupa angka");
        }
    }

    public void cekPembayaran() throws SQLException {
        System.out.println("\n======Cek Pembayaran Siswa======");
        // ID siswa
        System.out.print("Masukkan ID Siswa yang ingin dicek : ");
        id_siswa = input.nextLine();

        conn = DriverManager.getConnection(url, "root", "");
        Statement statement = conn.createStatement();
        String sql = "SELECT * FROM siswa WHERE id_siswa LIKE '" + this.id_siswa + "' ";
        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {
            System.out.print("\nNama Siswa      : ");
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
    }

   
    public void view() throws SQLException {

    }


    public void update() throws SQLException {

    }

   
    public void delete() throws SQLException {

    }

}
