import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

import java.sql.*;

//class program
public class BimbelLTC extends SiswaLTC {
    // koneksi database
    static Connection conn;
    static String url = "jdbc:mysql://localhost:3306/bimbel";
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        // date
        DateFormat tanggal = new SimpleDateFormat("dd MMMM yyyy");
        DateFormat jam = new SimpleDateFormat("HH:mm:ss");
        Date tgldanjam = new Date();

        System.out.println("\n===============================================");
        System.out.println(" Tanggal\t: " + tanggal.format(tgldanjam));
        System.out.println(" Jam    \t: " + jam.format(tgldanjam) + " WIB");
        System.out.println("-----------------------------------------------");

        // method String replace()
        String sapa = "\n    Hello!! Welcome Student   ";
        String GantiKalimat = sapa.replace("Belajar Bersama", "Sukses Bersama");
        // String sapa = "\nHello!! Welcome Student";
        // String GantiKalimat = sapa.replace("Belajar Bersama","Sukses Bersama");//method
        // String replace()
        System.out.println(GantiKalimat.trim());// method trim()

        // Method String toUpperCase()
        String sapa1 = ("\nSelamat datang di program bimbel LTC");
        System.out.println(sapa1.toUpperCase());
        System.out.println("");
        System.out.println("Dapatkan Diskon 10% Untuk Pendaftaran Siswa Baru!!!\n");

        admin();

        menu();
    }

    private static void menu() throws SQLException {
        Integer pilihan;
        boolean isLanjutkan = true;

        // perulangan while
        while (isLanjutkan) {
            {
                System.out.println("\n===========================================");
                System.out.println("=           PROGRAM BIMBEL LTC            =");
                System.out.println("===========================================");
                System.out.println("1.Pendaftaran Siswa Baru");
                System.out.println("2.Cek Pembayaran Siswa");
                System.out.println("3.LIhat Data Seluruh Siswa");
                System.out.println("4.Ubah Data Siswa");
                System.out.println("5.Hapus Data Siswa");
                System.out.print("> Pilihan Anda (1/2/3/4/5): ");
                pilihan = input.nextInt();

                PendaftaranBimbel daftar = new PendaftaranBimbel();
                SiswaLTC siswa = new SiswaLTC();

                // percabangan switch case
                switch (pilihan) {
                    case 1:
                        daftar.daftar();
                        break;

                    case 2:
                        daftar.cekPembayaran();
                        break;

                    case 3:
                        siswa.view();
                        break;

                    case 4:
                        siswa.update();
                        break;

                    case 5:
                        siswa.delete();
                        break;

                    default:
                        System.err.println("\nInput anda tidak ditemukan");
                        System.out.println("> Silakan pilih [1-5]\n");
                        break;
                }
            }
        }
    }

    // collection
    private static void admin() throws SQLException {
        // membuat objek HashMap baru
        Map<String, String> Login = new HashMap<String, String>();

        // membaca data di database
        String Username, Password;
        String sql = "SELECT * FROM login";
        boolean relogin = true;
        conn = DriverManager.getConnection(url, "root", "");
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);

        // perulangan while
        while (result.next()) {
            // mengambil nilai di database dan menyimpannya ke dalam variable
            String username = result.getString("username");
            String password = result.getString("password");

            // input key dan value
            Login.put(username, password);
        }
        System.out.println("======login terlebih dahulu======");

        // perulangan while
        while (relogin) {
            System.out.println("Masukkan username dan password yang benar!");
            System.out.print("Username : ");
            Username = input.nextLine();
            System.out.print("Password : ");
            Password = input.nextLine();

            // percabangan if
            if (Login.containsValue(Username) == true) {
                // percabangan if
                if (Login.get(Username).equals(Password)) {
                    relogin = false;
                } else {
                    relogin = true;
                }
            } else {
                relogin = false;
                System.out.println("\nBerhasil login!!!");
            }
        }
        statement.close();
    }
}
