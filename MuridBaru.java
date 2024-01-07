//kelas anak
public class MuridBaru extends PendaftaranBimbel {

    String nama_siswa;

    //constructor
    public MuridBaru()
    {

    }

    //constructor
    public MuridBaru (String nama_siswa)
    {
        this.nama_siswa = nama_siswa;
        System.out.println("Nama Siswa  : " + this.nama_siswa);
    }


}