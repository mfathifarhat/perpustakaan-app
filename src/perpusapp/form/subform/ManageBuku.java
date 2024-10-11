/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package perpusapp.form.subform;

import com.formdev.flatlaf.FlatClientProperties;
import com.mysql.jdbc.PreparedStatement;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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
public class ManageBuku extends javax.swing.JPanel {

    /**
     * Creates new form ManageBuku
     */
    private String status;
    Statement st;
    ResultSet rs;

    public ManageBuku() {
        initComponents();
        customInit();
        showTable();
        disabled();
        empty();
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
            rs = st.executeQuery("SELECT * FROM tbl_buku");
            model.addColumn("Kode Buku");
            model.addColumn("Judul Buku");
            model.addColumn("Pengarang");
            model.addColumn("Penerbit");
            model.addColumn("Tahun Buku");
            model.addColumn("Jumlah");

            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            model.setRowCount(0);

            while (rs.next()) {
                String kode = rs.getString("KodeBuku");
                String judul = rs.getString("JudulBuku");
                String pengarang = rs.getString("PengarangBuku");
                String penerbit = rs.getString("PenerbitBuku");
                String tahun = rs.getString("TahunBuku");
                String jumlah = rs.getString("JumlahBuku");

                Object[] data = {
                    kode,
                    judul,
                    pengarang,
                    penerbit,
                    tahun,
                    jumlah};
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

    private void enabled() {
        jTextField2.setEnabled(true);
        jTextField3.setEnabled(true);
        jTextField4.setEnabled(true);
        jTextField5.setEnabled(true);
        jTextField6.setEnabled(true);
        jTextField7.setEnabled(true);

        jButton4.setEnabled(true);
        jButton5.setEnabled(true);
    }

    private void disabled() {
        jTextField2.setEnabled(false);
        jTextField3.setEnabled(false);
        jTextField4.setEnabled(false);
        jTextField5.setEnabled(false);
        jTextField6.setEnabled(false);
        jTextField7.setEnabled(false);

        jButton4.setEnabled(false);
        jButton5.setEnabled(false);
    }

    private void empty() {
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
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
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();

        jLabel1.setText("Manage Buku");

        jPanel1.setBackground(new java.awt.Color(207, 209, 208));

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

        jTextField1.setToolTipText("");
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel2.setText("Cari");

        jButton1.setText("Tambah");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Ubah");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Hapus");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
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

        jLabel3.setText("Kode Buku");

        jLabel4.setText("Judul Buku");

        jLabel5.setText("Pengarang");

        jLabel6.setText("Penerbit");

        jLabel7.setText("Tahun Terbit");

        jButton4.setText("Save");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Cancel");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel8.setText("Jumlah");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jTextField6))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jTextField4))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jTextField5))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jTextField7)))
                        .addGap(168, 168, 168)
                        .addComponent(jButton4)
                        .addGap(15, 15, 15)
                        .addComponent(jButton5)
                        .addGap(1, 1, 1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

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
                    PreparedStatement ps = (PreparedStatement) cn.prepareStatement("DELETE FROM tbl_buku WHERE KodeBuku=?");
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
            rs = st.executeQuery("SELECT * FROM tbl_buku WHERE JudulBuku like '%" + jTextField1.getText() + "%'");
            model.addColumn("Kode Buku");
            model.addColumn("Judul Buku");
            model.addColumn("Pengarang");
            model.addColumn("Penerbit");
            model.addColumn("Tahun Buku");
            model.addColumn("Jumlah");

            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            model.setRowCount(0);

            while (rs.next()) {
                String kode = rs.getString("KodeBuku");
                String judul = rs.getString("JudulBuku");
                String pengarang = rs.getString("PengarangBuku");
                String penerbit = rs.getString("PenerbitBuku");
                String tahun = rs.getString("TahunBuku");
                String jumlah = rs.getString("JumlahBuku");

                Object[] data = {
                    kode,
                    judul,
                    pengarang,
                    penerbit,
                    tahun,
                    jumlah};
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
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (status == "tambah") {
            java.sql.Connection cn = Connection.BukaKoneksi();

            if (jTextField2.getText().isEmpty() || jTextField3.getText().isEmpty() || jTextField4.getText().isEmpty() || jTextField5.getText().isEmpty() || jTextField6.getText().isEmpty() || jTextField7.getText().isEmpty()) {
                Notifications.getInstance().setJFrame(app);
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.BOTTOM_RIGHT, "Silahkan isi semua field yang diperlukan!");
            } else {
                try {
                    String kode = jTextField2.getText();
                    String judul = jTextField3.getText();
                    String pengarang = jTextField4.getText();
                    String penerbit = jTextField5.getText();
                    String tahun = jTextField6.getText();
                    String jumlah = jTextField7.getText();

                    rs = st.executeQuery("SELECT * FROM tbl_buku WHERE KodeBuku='" + kode + "'");
                    if (rs.next()) {
                        Notifications.getInstance().setJFrame(app);
                        Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.BOTTOM_RIGHT, "Kode Buku sudah tersedia!");
                    } else {

                        PreparedStatement ps = (PreparedStatement) cn.prepareStatement("INSERT INTO tbl_buku VALUES (?,?,?,?,?,?)");
//                        st.executeUpdate("INSERT INTO tbl_petugas VALUES ('" + kode + "','" + nama + "','" + password + "', '" + level + "')");
                        ps.setString(1, kode);
                        ps.setString(2, judul);
                        ps.setString(3, pengarang);
                        ps.setString(4, penerbit);
                        ps.setString(5, tahun);
                        ps.setString(6, jumlah);

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

            if (jTextField2.getText().isEmpty() || jTextField3.getText().isEmpty() || jTextField4.getText().isEmpty() || jTextField5.getText().isEmpty() || jTextField6.getText().isEmpty() || jTextField7.getText().isEmpty()) {
                Notifications.getInstance().setJFrame(app);
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.BOTTOM_RIGHT, "Silahkan isi semua field yang diperlukan!");
            } else {
                try {
                    String kode = jTextField2.getText();
                    String judul = jTextField3.getText();
                    String pengarang = jTextField4.getText();
                    String penerbit = jTextField5.getText();
                    String tahun = jTextField6.getText();
                    String jumlah = jTextField7.getText();

                    PreparedStatement ps = (PreparedStatement) cn.prepareStatement("UPDATE tbl_buku SET JudulBuku=?, PengarangBuku=?, PenerbitBuku=?, TahunBuku=?, JumlahBuku=? where KodeBuku = ? ");
                    ps.setString(6, kode);
                    ps.setString(1, judul);
                    ps.setString(2, pengarang);
                    ps.setString(3, penerbit);
                    ps.setString(4, tahun);
                    ps.setString(5, jumlah);

                    ps.executeUpdate();

                    disabled();
                    showTable();
                    empty();

                    status = "";
//                    JOptionPane.showMessageDialog(null, "Data telah berhasil disimpan!");
                    Notifications.getInstance().setJFrame(app);
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.BOTTOM_RIGHT, "Data telah berhasil disimpan!");

                } catch (Exception exc) {

                    JOptionPane.showMessageDialog(null, exc);
                }
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        empty();
        disabled();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        JTable target = (JTable) evt.getSource();
        int row = target.getSelectedRow();

        jTextField2.setText(target.getValueAt(row, 0).toString());
        jTextField3.setText(target.getValueAt(row, 1).toString());
        jTextField4.setText(target.getValueAt(row, 2).toString());
        jTextField5.setText(target.getValueAt(row, 3).toString());
        jTextField6.setText(target.getValueAt(row, 4).toString());
        jTextField7.setText(target.getValueAt(row, 5).toString());
    }//GEN-LAST:event_jTable1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}
