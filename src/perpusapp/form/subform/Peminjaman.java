/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package perpusapp.form.subform;

import com.formdev.flatlaf.FlatClientProperties;
import com.mysql.jdbc.PreparedStatement;
import java.awt.Dimension;
import java.awt.Point;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import perpusapp.Connection;
import static perpusapp.PerpusApp.app;
import static perpusapp.PerpusApp.showDeleteConfirmationDialog;
import raven.toast.Notifications;

/**
 *
 * @author Fathi
 */
public class Peminjaman extends javax.swing.JPanel {

    Statement st;
    ResultSet rs;
    Peminjaman pjm;

    /**
     * Creates new form Peminjaman
     */
    public Peminjaman() {
        initComponents();
        customInit();
        autoNumber();
        firstInit();
        disableBuku();
    }

    private void firstInit() {
        jTextField5.setText(app.NamaPetugas);
        autoNumber();
        clearAgt();
        clearBuku();
        disableBuku();
        jTextField6.setText("0");
        DefaultTableModel table2 = (DefaultTableModel) jTable2.getModel();

        table2.setRowCount(0);
    }

    ;

    private void autoNumber() {
        String urutan;
        long hitung = 0;

        java.sql.Connection cn = Connection.BukaKoneksi();

        try {
            st = cn.createStatement();
            DefaultTableModel model = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int i, int i1) {
                    return false; //To change body of generated methods, choose Tools | Templates.
                }
            };
            rs = st.executeQuery("SELECT * FROM tbl_peminjaman WHERE KodePeminjaman IN (SELECT MAX(KodePeminjaman) FROM tbl_peminjaman)");

            if (!rs.next()) {
                urutan = "PJM00001";
            } else {
                String lastKodePeminjaman = rs.getString("KodePeminjaman");
                hitung = Long.parseLong(lastKodePeminjaman.substring(3)) + 1;
                urutan = "PJM" + String.format("%05d", hitung);
            }

            jTextField3.setText(urutan);
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, exc);
        }
    }

    private void customInit() {
        jLabel1.putClientProperty(FlatClientProperties.STYLE, "font:bold +8");
        jLabel7.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        jLabel8.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");

        jButton1.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:lighten(@background, 10%);"
                + "font:bold -1;"
                + "arc:5");
        jButton1.setMaximumSize(new Dimension(75, 30));
        jButton1.setPreferredSize(new Dimension(75, 30));
        jButton2.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:lighten(@background, 10%);"
                + "font:bold -1;"
                + "arc:5");
        jButton2.setMaximumSize(new Dimension(75, 30));
        jButton2.setPreferredSize(new Dimension(75, 30));
        jButton3.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:lighten(@background, 10%);"
                + "font:bold -1;"
                + "arc:5");
        jButton3.setMaximumSize(new Dimension(75, 30));
        jButton3.setPreferredSize(new Dimension(75, 30));

        jButton4.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:lighten(@background, 10%);"
                + "font:bold -1;"
                + "foreground:#ED4629;"
                + "arc:5");
        jButton4.setMaximumSize(new Dimension(75, 30));
        jButton4.setPreferredSize(new Dimension(75, 30));

        jButton5.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:lighten(@background, 10%);"
                + "font:bold -1;"
                + "foreground:#ED4629;"
                + "arc:5");
        jButton5.setMaximumSize(new Dimension(75, 30));
        jButton5.setPreferredSize(new Dimension(75, 30));
    }

    public void setAnggota(String kode, String nama) {
        jTextField1.setText(kode);
        jTextField2.setText(nama);

        clearBuku();
        showTable(kode);
    }

    public boolean setBuku(JDialog buku, String kode, String judul, String jumlah) {
        if (Integer.parseInt(jumlah) > 0) {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            DefaultTableModel model1 = (DefaultTableModel) jTable2.getModel();

            int jumPjm = 0;
            int jumPjm1 = 0;
            int jumRow = 0;

            for (int i = 0; i < model.getRowCount(); i++) {
                String row = model.getValueAt(i, 0).toString();
                if (row.equals(kode)) {
                    model.removeRow(i);
                    i--; // Decrease i to check the next row
                }
            }

            for (int i = 0; i < model.getRowCount(); i++) {
                String total = model.getValueAt(i, 2).toString();
                jumPjm += Integer.parseInt(total);
            }

            for (int i = 0; i < model1.getRowCount(); i++) {
                String total = model1.getValueAt(i, 2).toString();
                jumPjm1 += Integer.parseInt(total);
            }

            if ((jumPjm + jumPjm1 + Integer.parseInt(jumlah)) <= 5) {
                for (int i = 0; i < model.getRowCount(); i++) {
                    String row = model.getValueAt(i, 0).toString();
                    if (row.equals(kode)) {
                        model.removeRow(i);
                        i--; // Decrease i to check the next row
                    }
                }

                Object[] data = {
                    kode,
                    judul,
                    jumlah
                };

                model.addRow(data);

                for (int i = 0; i < model.getRowCount(); i++) {
                    String total = model.getValueAt(i, 2).toString();
                    jumRow += Integer.parseInt(total);
                }

                jTextField6.setText(String.valueOf(jumRow));

                return true;
            } else {
                JOptionPane.showMessageDialog(buku, "Jumlah melebihi batas peminjaman!");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(buku, "Jumlah tidak boleh kurang dari 1!");
            return false;
        }
    }

    private void clearAgt() {
        jTextField1.setText("");
        jTextField2.setText("");

        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
    }

    private void disableBuku() {
        jButton2.setEnabled(false);
        clearBuku();
    }

    public void enableBuku() {
        jButton2.setEnabled(true);
    }

    private void clearBuku() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        jTextField6.setText("0");
    }

    private void showTable(String agt) {
        java.sql.Connection cn = Connection.BukaKoneksi();

        try {
            st = cn.createStatement();
            DefaultTableModel model = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int i, int i1) {
                    return false; //To change body of generated methods, choose Tools | Templates.
                }
            };
            rs = st.executeQuery("SELECT * FROM v_pinjamagt WHERE Agt = '" + agt + "' AND Jumlah > 0");
            model.addColumn("Kode Buku");
            model.addColumn("Judul Buku");
            model.addColumn("Jumlah");

            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            model.setRowCount(0);

            while (rs.next()) {
                String kode = rs.getString("Kode");
                String nama = rs.getString("Judul");
                String jk = rs.getString("Jumlah");

                Object[] data = {
                    kode,
                    nama,
                    jk,};
                model.addRow(data);
            }

            jTable2.setModel(model);
            jTable2.setFocusable(false);
            DefaultTableCellRenderer renderer;
            JTableHeader thead = (JTableHeader) jTable1.getTableHeader();
            renderer = (DefaultTableCellRenderer) jTable1.getTableHeader().getDefaultRenderer();
            renderer.setHorizontalAlignment(JLabel.LEFT);
        } catch (Exception exc) {

            JOptionPane.showMessageDialog(null, exc);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        jLabel1.setText("Peminjaman");

        jPanel1.setBackground(new java.awt.Color(207, 209, 208));
        jPanel1.setMinimumSize(new java.awt.Dimension(100, 1));
        jPanel1.setPreferredSize(new java.awt.Dimension(612, 1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jLabel2.setText("Kode Peminjaman");

        jLabel4.setText("Kode Anggota");

        jTextField1.setEditable(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("Cari");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Nama Anggota");

        jTextField2.setEditable(false);

        jTextField3.setEditable(false);

        jTextField5.setEditable(false);

        jLabel6.setText("Petugas");

        jLabel7.setText("Buku yang ingin dipinjam");

        jPanel2.setBackground(new java.awt.Color(207, 209, 208));
        jPanel2.setMinimumSize(new java.awt.Dimension(100, 1));
        jPanel2.setPreferredSize(new java.awt.Dimension(612, 1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Buku", "Judul Buku", "Jumlah"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("Input");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel8.setText("Buku yang telah dipinjam");

        jPanel3.setBackground(new java.awt.Color(207, 209, 208));
        jPanel3.setMinimumSize(new java.awt.Dimension(100, 1));
        jPanel3.setPreferredSize(new java.awt.Dimension(612, 1));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Kode Buku", "Judul Buku", "Jumlah"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jLabel9.setText("Total Buku");

        jTextField6.setEditable(false);
        jTextField6.setText("0");
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jButton3.setText("Simpan");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Batal");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Batal");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3)
                                .addGap(15, 15, 15)
                                .addComponent(jButton4))
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextField1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton5))
                                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                                    .addComponent(jTextField2))
                                .addGap(98, 98, 98)
                                .addComponent(jLabel6)
                                .addGap(12, 12, 12)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2)))
                        .addGap(40, 40, 40))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addGap(12, 12, 12)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jButton2))
                .addGap(8, 8, 8)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(25, 25, 25)
                .addComponent(jLabel8)
                .addGap(8, 8, 8)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        SrcAnggotaPJM srcAgt = new SrcAnggotaPJM(app, "Search Anggota", true);

        srcAgt.setLocationRelativeTo(app);
        srcAgt.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        SrcBuku srcBk = new SrcBuku(app, "Search Buku", true);

        srcBk.setLocationRelativeTo(app);
        srcBk.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
        JTable table = (JTable) jTable1;
        Point point = evt.getPoint();
        int row = table.rowAtPoint(point);
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        if (evt.getClickCount() == 2 && table.getSelectedRow() != -1) {
            boolean deleteConfirmed = showDeleteConfirmationDialog("Apakah anda yakin ingin menghapus data ini?");
            if (deleteConfirmed) {
                model.removeRow(table.getSelectedRow());
                int jumPjm = 0;

                for (int i = 0; i < model.getRowCount(); i++) {
                    String total = model.getValueAt(i, 2).toString();
                    jumPjm += Integer.parseInt(total);
                }
                jTextField6.setText(String.valueOf(jumPjm));
            }
        }
    }//GEN-LAST:event_jTable1MousePressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (jTextField1.getText().isEmpty() || jTextField2.getText().isEmpty() || jTextField3.getText().isEmpty() || jTable1.getModel().getRowCount() < 0) {
            Notifications.getInstance().setJFrame(app);
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.BOTTOM_RIGHT, "Silahkan isi semua field yang diperlukan!");
        } else {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

            String kodePjm = jTextField3.getText();
            String kodeAgt = jTextField1.getText();
            String namaAgt = jTextField2.getText();
            String kodePtg = app.KodePetugas;
            int itemPjm = model.getRowCount();
            int jumPjm = 0;

            ZoneId utcPlus7 = ZoneOffset.ofHours(7);

            // Get the current date and time in the specified time zone
            LocalDateTime currentDateTime = LocalDateTime.now(utcPlus7);

            // Define a format for the output string
            DateTimeFormatter tglFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter jamFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

            // Format the current date and time as a string
            String tgl = currentDateTime.format(tglFormat);
            String jam = currentDateTime.format(jamFormat);

            for (int i = 0; i < model.getRowCount(); i++) {
                String total = model.getValueAt(i, 2).toString();
                jumPjm += Integer.parseInt(total);
            }

            java.sql.Connection cn = Connection.BukaKoneksi();

            try {
                PreparedStatement ps = (PreparedStatement) cn.prepareStatement("INSERT INTO tbl_peminjaman VALUES (?,?,?,?,?,?,?)");
//                        st.executeUpdate("INSERT INTO tbl_petugas VALUES ('" + kode + "','" + nama + "','" + password + "', '" + level + "')");
                ps.setString(1, kodePjm);
                ps.setString(2, tgl);
                ps.setString(3, jam);
                ps.setString(4, kodeAgt);
                ps.setString(5, String.valueOf(itemPjm));
                ps.setString(6, String.valueOf(jumPjm));
                ps.setString(7, kodePtg);

                ps.executeUpdate();

            } catch (Exception ex) {

            }

            try {
                for (int i = 0; i < model.getRowCount(); i++) {

                    PreparedStatement ps = (PreparedStatement) cn.prepareStatement("INSERT INTO tbl_detailpinjam VALUES (?,?,?)");
//                        st.executeUpdate("INSERT INTO tbl_petugas VALUES ('" + kode + "','" + nama + "','" + password + "', '" + level + "')");
                    ps.setString(1, kodePjm);
                    ps.setString(2, model.getValueAt(i, 0).toString());
                    ps.setString(3, model.getValueAt(i, 2).toString());

                    ps.executeUpdate();

                    rs = st.executeQuery("SELECT * FROM tbl_buku WHERE KodeBuku = '" + model.getValueAt(i, 0).toString() + "'");

                    while (rs.next()) {
                        PreparedStatement upBuku = (PreparedStatement) cn.prepareStatement("Update tbl_buku set JumlahBuku='" + String.valueOf((rs.getInt("JumlahBuku") - Integer.parseInt(model.getValueAt(i, 2).toString()))) + "' where KodeBuku='" + model.getValueAt(i, 0).toString() + "'");
//                        st.executeUpdate("INSERT INTO tbl_petugas VALUES ('" + kode + "','" + nama + "','" + password + "', '" + level + "')");
                        upBuku.executeUpdate();
                    }
                }
            } catch (Exception ex) {

            }

            firstInit();

            Notifications.getInstance().setJFrame(app);
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.BOTTOM_RIGHT, "Data telah berhasil disimpan!");

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        disableBuku();
        clearAgt();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        firstInit();
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
