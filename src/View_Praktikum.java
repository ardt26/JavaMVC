import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class View_Praktikum extends JFrame {

    JLabel lNama = new JLabel("Nama : ");
    JTextField tfNama = new JTextField();
    JLabel lNIM = new JLabel("NIM : ");
    JTextField tfNIM = new JTextField();
    JLabel lAlamat = new JLabel("Alamat : ");
    JTextArea taAlamat = new JTextArea();

    JButton btnTambah = new JButton("Tambah");
    JButton btnCancel = new JButton("Cancel");
    JButton btnEdit = new JButton("Edit");

    JTable tabel;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;  //buat scroll
    Object namaKolom[] = {"NIM","Nama","Alamat"}; //membuat kolom dalam array

    public View_Praktikum(){

        tableModel = new DefaultTableModel(namaKolom,0); //0 baris
        tabel = new JTable(tableModel);
        scrollPane = new JScrollPane(tabel);


        setTitle("Pendataan Mahasiswa UPN Jogja");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setSize(700,500);

        //Tabel
        add(scrollPane);
        scrollPane.setBounds(50, 140, 600, 200);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        add(lNIM);
        lNIM.setBounds(60,20,90,20);
        add(tfNIM);
        tfNIM.setBounds(120,20,120,20);

        add(lNama);
        lNama.setBounds(260,20,90,20);
        add(tfNama);
        tfNama.setBounds(320,20,120,20);

        add(lAlamat);
        lAlamat.setBounds(460,20,90,20);
        add(taAlamat);
        taAlamat.setBounds(520,20,120,20);


        add(btnTambah);
        btnTambah.setBounds(165,80,90,20);

        add(btnEdit);
        btnEdit.setBounds(305,80,90,20);

        add(btnCancel);
        btnCancel.setBounds(445,80,90,20);

    }

    public String getNama() {
        return tfNama.getText();
    }

    public String getNIM() {
        return tfNIM.getText();
    }

    public String getAlamat() {
        return taAlamat.getText();
    }

    public void setNama(String tfNama) {
        this.tfNama.selectAll();
        this.tfNama.replaceSelection(tfNama);
    }

    public void setNIM(String tfNIM) {
        this.tfNIM.selectAll();
        this.tfNIM.replaceSelection(tfNIM);
    }

    public void setAlamat(String taAlamat) {
        this.taAlamat.selectAll();
        this.taAlamat.replaceSelection(taAlamat);
    }
}
