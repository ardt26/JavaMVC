import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller_Praktikum {
    Model_Praktikum model_praktikum;
    View_Praktikum view_praktikum;

    public Controller_Praktikum(Model_Praktikum model_praktikum, View_Praktikum view_praktikum){
        this.model_praktikum = model_praktikum;
        this.view_praktikum = view_praktikum;

        if (model_praktikum.getBanyakData() != 0){
            String dataMahasiswa[][] = model_praktikum.readMahasiswa();
            view_praktikum.tabel.setModel(new JTable(dataMahasiswa, view_praktikum.namaKolom).getModel());
        } else {
            JOptionPane.showMessageDialog(null, "Data Tidak Ada");
        }

        view_praktikum.btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!view_praktikum.getNIM().equals("") && !view_praktikum.getNama().equals("")){
                    if (view_praktikum.getNIM().length() == 9){
                        String nim = view_praktikum.getNIM();
                        String nama = view_praktikum.getNama();
                        String alamat = view_praktikum.getAlamat();
                        model_praktikum.insertMahasiswa(nim, nama, alamat);

                        String datamahasiswa[][] = model_praktikum.readMahasiswa();
                        view_praktikum.tabel.setModel(new JTable(datamahasiswa,view_praktikum.namaKolom).getModel());
                        cancelMahasiswa();
                    } else {
                        JOptionPane.showMessageDialog(null, "NIM Harus Terdiri Dari 9 Digit");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "NIM dan Nama Tidak Boleh Kosong");
                }
            }
        });

        view_praktikum.btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!view_praktikum.getNIM().equals("")){
                    String nim = view_praktikum.getNIM();
                    String nama = view_praktikum.getNama();
                    String alamat = view_praktikum.getAlamat();
                    model_praktikum.editMahasiswa(nim, nama, alamat);

                    String datamahasiswa[][] = model_praktikum.readMahasiswa();
                    view_praktikum.tabel.setModel(new JTable(datamahasiswa,view_praktikum.namaKolom).getModel());
                    cancelMahasiswa();
                } else {
                    JOptionPane.showMessageDialog(null, "NIM Tidak Boleh Kosong");
                }
            }
        });

        view_praktikum.btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelMahasiswa();
            }
        });

        view_praktikum.tabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int baris = view_praktikum.tabel.getSelectedRow();
                int kolom = view_praktikum.tabel.getSelectedColumn();

                String dataTerpilih = view_praktikum.tabel.getValueAt(baris, 0).toString();

                int input = JOptionPane.showConfirmDialog(null,
                        "Apa anda ingin menghapus NIM " + dataTerpilih + "?", "Pilih Opsi...", JOptionPane.YES_NO_OPTION);

                if (input == 0) {
                    model_praktikum.deleteMahasiswa(dataTerpilih);
                    String dataMahasiswa[][] = model_praktikum.readMahasiswa();
                    view_praktikum.tabel.setModel(new JTable(dataMahasiswa, view_praktikum.namaKolom).getModel());
                } else {
                    JOptionPane.showMessageDialog(null, "Tidak Jadi Dihapus");
                }

            }
        });
    }

    public void cancelMahasiswa () {
        view_praktikum.setNama("");
        view_praktikum.setNIM("");
        view_praktikum.setAlamat("");
    }
}
