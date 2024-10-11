/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package perpusapp.form.subform;

import com.formdev.flatlaf.FlatClientProperties;
import com.mysql.jdbc.PreparedStatement;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ManageAnggota extends javax.swing.JPanel {

    String status;
    Statement st;
    ResultSet rs;

    /**
     * Creates new form ManageAnggota
     */
    public ManageAnggota() {
        initComponents();
        customInit();
        showTable();
        empty();
        disabled();
    }

    private void customInit() {

        jLabel1.putClientProperty(FlatClientProperties.STYLE, "font:bold +8");
        jTable1.setFocusable(false);
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
                + "foreground:#ED4629;"
                + "arc:5");
        jButton3.setMaximumSize(new Dimension(75, 30));
        jButton3.setPreferredSize(new Dimension(75, 30));

        jButton4.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:lighten(@background, 10%);"
                + "font:bold -1;"
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

    private void disabled() {
        jTextField2.setEnabled(false);
        jTextField3.setEnabled(false);
        jTextField5.setEnabled(false);
        jTextArea1.setEnabled(false);
        jComboBox1.setEnabled(false);
        jButton4.setEnabled(false);
        jButton5.setEnabled(false);
    }

    private void enabled() {
        jTextField2.setEnabled(true);
        jTextField3.setEnabled(true);
        jTextField5.setEnabled(true);
        jTextArea1.setEnabled(true);
        jComboBox1.setEnabled(true);
        jButton4.setEnabled(true);
        jButton5.setEnabled(true);
    }

    private void empty() {
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField5.setText("");
        jTextArea1.setText("");
        jComboBox1.setSelectedIndex(-1);
    }

    private void showTable() {
        java.sql.Connection cn = Connection.BukaKoneksi();

        try {
            st = cn.createStatement();
            DefaultTableModel model = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int i, int i1) {
                    return false; //To change body of generated methods, choose Tools | Templates.
                }
            };
            rs = st.executeQuery("SELECT * FROM tbl_anggota");
            model.addColumn("Kode Anggota");
            model.addColumn("Nama Anggota");
            model.addColumn("Jenis Kelamin");
            model.addColumn("Alamat");
            model.addColumn("No. Telp");
            model.addColumn("Tanggal Bergabung");

            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            model.setRowCount(0);

            while (rs.next()) {
                String kode = rs.getString("KodeAnggota");
                String nama = rs.getString("NamaAnggota");
                String jk = rs.getString("JenisKelamin");
                String alamat = rs.getString("AlamatAnggota");
                String notelp = rs.getString("TelpAnggota");
                String tgl = rs.getString("Tgl");

                Object[] data = {
                    kode,
                    nama,
                    jk,
                    alamat,
                    notelp,
                    tgl,};
                model.addRow(data);
            }

            jTable1.setModel(model);
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
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField5 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jLabel1.setText("Manage Anggota");

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

        jLabel2.setText("Cari");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Tambah");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Ubah");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Hapus");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setText("Kode Anggota");

        jLabel4.setText("Nama Anggota");

        jLabel5.setText("Jenis Kelamin");

        jLabel6.setText("Alamat");

        jLabel7.setText("No. Telepon");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PRIA", "WANITA" }));

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jButton5.setText("Cancel");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton4.setText("Save");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1)
                        .addGap(224, 224, 224)
                        .addComponent(jButton1)
                        .addGap(12, 12, 12)
                        .addComponent(jButton2)
                        .addGap(12, 12, 12)
                        .addComponent(jButton3))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField5)
                            .addComponent(jTextField2)
                            .addComponent(jTextField3)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
                        .addGap(149, 149, 149)
                        .addComponent(jButton4)
                        .addGap(15, 15, 15)
                        .addComponent(jButton5)))
                .addGap(40, 40, 40))
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
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4)
                        .addComponent(jButton5))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        JTable target = (JTable) evt.getSource();
        int row = target.getSelectedRow();

        jTextField2.setText(target.getValueAt(row, 0).toString());
        jTextField3.setText(target.getValueAt(row, 1).toString());
        jTextArea1.setText(target.getValueAt(row, 3).toString());
        jTextField5.setText(target.getValueAt(row, 4).toString());
        for (int i = 0; i < jComboBox1.getItemCount(); i++) {
            if (jComboBox1.getItemAt(i).equals(target.getValueAt(row, 2).toString())) {
                jComboBox1.setSelectedIndex(i);
                break; // Break the loop once a match is found
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        status = "tambah";
        enabled();
        empty();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        status = "ubah";
        enabled();
        jTextField2.setEnabled(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (jTextField2.getText().isEmpty()) {
            Notifications.getInstance().setJFrame(app);
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.BOTTOM_RIGHT, "Silahkan pilih data yang ingin dihapus!");
        } else {

            boolean deleteConfirmed = showDeleteConfirmationDialog("Apakah anda yakin ingin menghapus data ini?");

            if (deleteConfirmed) {

                java.sql.Connection cn = Connection.BukaKoneksi();
                try {
                    PreparedStatement ps = (PreparedStatement) cn.prepareStatement("DELETE FROM tbl_anggota WHERE KodeAnggota=?");
                    ps.setString(1, jTextField2.getText());
                    ps.executeUpdate();

                    disabled();
                    showTable();
                    empty();

                    status = "";
                    Notifications.getInstance().setJFrame(app);
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.BOTTOM_RIGHT, "Data telah berhasil dihapus!");

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        empty();
        disabled();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (status == "tambah") {
            java.sql.Connection cn = Connection.BukaKoneksi();

            if (jTextField2.getText().isEmpty() || jTextField3.getText().isEmpty() || jTextArea1.getText().isEmpty() || jComboBox1.getSelectedIndex() < 0) {
                Notifications.getInstance().setJFrame(app);
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.BOTTOM_RIGHT, "Silahkan isi semua field yang diperlukan!");
            } else {
                try {
                    String kode = jTextField2.getText();
                    String nama = jTextField3.getText();
                    String jk = jComboBox1.getSelectedItem().toString();
                    String alamat = jTextArea1.getText();
                    String notelp = jTextField5.getText();

                    rs = st.executeQuery("SELECT * FROM tbl_anggota WHERE KodeAnggota='" + kode + "'");
                    if (rs.next()) {
                        Notifications.getInstance().setJFrame(app);
                        Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.BOTTOM_RIGHT, "Kode Anggota sudah tersedia!");
                    } else {

                        ZoneId utcPlus7 = ZoneOffset.ofHours(7);

                        // Get the current date and time in the specified time zone
                        LocalDateTime currentDateTime = LocalDateTime.now(utcPlus7);

                        // Define a format for the output string
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                        // Format the current date and time as a string
                        String formattedDateTime = currentDateTime.format(formatter);

                        PreparedStatement ps = (PreparedStatement) cn.prepareStatement("INSERT INTO tbl_anggota VALUES (?,?,?,?,?,?)");
//                        st.executeUpdate("INSERT INTO tbl_petugas VALUES ('" + kode + "','" + nama + "','" + password + "', '" + level + "')");
                        ps.setString(1, kode);
                        ps.setString(2, nama);
                        ps.setString(3, jk);
                        ps.setString(4, alamat);
                        ps.setString(5, notelp);
                        ps.setString(6, formattedDateTime);

                        ps.executeUpdate();

                        empty();
                        disabled();
                        showTable();

                        status = "";
//                        JOptionPane.showMessageDialog(null, "Data telah berhasil disimpan!");
                        Notifications.getInstance().setJFrame(app);
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.BOTTOM_RIGHT, "Data telah berhasil disimpan!");

                    }
                } catch (Exception exc) {

                    JOptionPane.showMessageDialog(null, exc);
                }
            }
        } else if (status == "ubah") {
            java.sql.Connection cn = Connection.BukaKoneksi();

            if (jTextField2.getText().isEmpty() || jTextField3.getText().isEmpty() || jTextArea1.getText().isEmpty() || jComboBox1.getSelectedIndex() < 0) {
                Notifications.getInstance().setJFrame(app);
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.BOTTOM_RIGHT, "Silahkan isi semua field yang diperlukan!");
            } else {
                try {
                    String kode = jTextField2.getText();
                    String nama = jTextField3.getText();
                    String jk = jComboBox1.getSelectedItem().toString();
                    String alamat = jTextArea1.getText();
                    String notelp = jTextField5.getText();
                    ZoneId utcPlus7 = ZoneOffset.ofHours(7);

                    PreparedStatement ps = (PreparedStatement) cn.prepareStatement("UPDATE tbl_anggota SET NamaAnggota=?, JenisKelamin=?, AlamatAnggota=?, TelpAnggota=? where KodeAnggota = ? ");
//                        st.executeUpdate("INSERT INTO tbl_petugas VALUES ('" + kode + "','" + nama + "','" + password + "', '" + level + "')");
                    ps.setString(1, nama);
                    ps.setString(2, jk);
                    ps.setString(3, alamat);
                    ps.setString(4, notelp);
                    ps.setString(5, kode);

                    ps.executeUpdate();

                    empty();
                    disabled();
                    showTable();

                    status = "";
//                        JOptionPane.showMessageDialog(null, "Data telah berhasil disimpan!");
                    Notifications.getInstance().setJFrame(app);
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.BOTTOM_RIGHT, "Data telah berhasil disimpan!");

                } catch (Exception exc) {

                    JOptionPane.showMessageDialog(null, exc);
                }
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        java.sql.Connection cn = Connection.BukaKoneksi();

        try {
            st = cn.createStatement();
            DefaultTableModel model = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int i, int i1) {
                    return false; //To change body of generated methods, choose Tools | Templates.
                }
            };
            rs = st.executeQuery("SELECT * FROM tbl_anggota WHERE NamaAnggota like '%" + jTextField1.getText() + "%'");
            model.addColumn("Kode Anggota");
            model.addColumn("Nama Anggota");
            model.addColumn("Jenis Kelamin");
            model.addColumn("Alamat");
            model.addColumn("No. Telp");
            model.addColumn("Tanggal Bergabung");

            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            model.setRowCount(0);

            while (rs.next()) {
                String kode = rs.getString("KodeAnggota");
                String nama = rs.getString("NamaAnggota");
                String jk = rs.getString("JenisKelamin");
                String alamat = rs.getString("AlamatAnggota");
                String notelp = rs.getString("TelpAnggota");
                String tgl = rs.getString("Tgl");

                Object[] data = {
                    kode,
                    nama,
                    jk,
                    alamat,
                    notelp,
                    tgl,};
                model.addRow(data);
            }

            jTable1.setModel(model);
            DefaultTableCellRenderer renderer;
            JTableHeader thead = (JTableHeader) jTable1.getTableHeader();
            renderer = (DefaultTableCellRenderer) jTable1.getTableHeader().getDefaultRenderer();
            renderer.setHorizontalAlignment(JLabel.LEFT);
        } catch (Exception exc) {

            JOptionPane.showMessageDialog(null, exc);
        } finally {
            try {
                cn.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Handle exceptions appropriately
            }
        }
    }//GEN-LAST:event_jTextField1KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
