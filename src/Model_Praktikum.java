import javax.swing.*;
import java.sql.*;

public class Model_Praktikum {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/praktikum";
    static final String USER = "root";
    static final String PASS = "";

    Connection connection;
    Statement statement;

    public Model_Praktikum() {
        try{
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Koneksi Berhasil");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    public void insertMahasiswa(String sNIM, String sNama, String sAlamat) {
        try {
            String query = "INSERT INTO `mahasiswa` (`nim`,`nama`,`alamat`) VALUES ('" + sNIM + "','" + sNama + "','" + sAlamat + "')";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Berhasil Ditambahkan");
            JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
        }catch (Exception e){
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    public void editMahasiswa(String sNIM, String sNama, String sAlamat) {
        try {
            String query = "UPDATE `mahasiswa` SET `nama` = '" + sNama +"', `alamat` = '" + sAlamat +"' WHERE `nim` = '" + sNIM +"' ";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Berhasil Diganti");
            JOptionPane.showMessageDialog(null, "Data Berhasil DIganti");
        }catch (Exception e){
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    public String[][] readMahasiswa(){
        try{
            int jmlData = 0;//menampung jumlah data

            String data[][] = new String[getBanyakData()][3]; //baris, kolom nya ada 3

            String query = "Select * from`mahasiswa`"; //pengambilan dara dalam java dari database
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){ //lanjut kedata selanjutnya jmlData bertambah
                data[jmlData][0] = resultSet.getString("nim"); //kolom nama harus sama besar kecilnya dgn database
                data[jmlData][1] = resultSet.getString("nama");
                data[jmlData][2] = resultSet.getString("alamat");
                jmlData++; //barisnya berpindah terus
            }
            return data;

        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return null;
        }
    }

    public int getBanyakData(){//menghitung jumlah baris
        int jmlData = 0;
        try{
            statement = connection.createStatement();
            String query = "Select * from `mahasiswa`"; //pengambilan dara dalam java dari database
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){ //lanjut kedata selanjutnya jmlData bertambah
                jmlData++;
            }
            return jmlData;

        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return 0;
        }
    }

    public void deleteMahasiswa (String nim) {
        try{
            String query = "DELETE FROM `mahasiswa` WHERE `nim` = '"+nim+"'";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Berhasil Dihapus");

        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
