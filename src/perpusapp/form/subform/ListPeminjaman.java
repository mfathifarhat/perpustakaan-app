/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package perpusapp.form.subform;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import perpusapp.Connection;
import static perpusapp.PerpusApp.app;
import raven.toast.Notifications;

/**
 *
 * @author Fathi
 */
public class ListPeminjaman extends javax.swing.JPanel {

    Statement st;
    ResultSet rs;

    /**
     * Creates new form ListPeminjaman
     */
    public ListPeminjaman() {
        initComponents();
        customInit();
        showTable();
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
                + "arc:5");
        jButton3.setMaximumSize(new Dimension(75, 30));
        jButton3.setPreferredSize(new Dimension(75, 30));
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
            rs = st.executeQuery("SELECT * FROM v_peminjamandtl");
            model.addColumn("Kode Peminjaman");
            model.addColumn("Tanggal");
            model.addColumn("Jam");
            model.addColumn("Buku");
            model.addColumn("Jumlah");
            model.addColumn("Peminjam");
            model.addColumn("Petugas");

            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            model.setRowCount(0);

            while (rs.next()) {
                String kode = rs.getString("NoPinjam");
                String tgl = rs.getString("TglPinjam");
                String jam = rs.getString("JamPinjam");
                String buku = rs.getString("Judul");
                String jumlah = rs.getString("JumlahBuku");
                String peminjam = rs.getString("NamaAnggota");
                String petugas = rs.getString("NamaPetugas");

                Object[] data = {
                    kode,
                    tgl,
                    jam,
                    buku,
                    jumlah,
                    peminjam,
                    petugas};
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();

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

        jLabel1.setText("List Peminjaman");

        jLabel2.setText("Dari");

        jLabel3.setText("Sampai");

        jLabel4.setText("Kode Peminjaman");

        jButton1.setText("Cari");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cari");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
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
        jScrollPane1.setViewportView(jTable1);

        jButton3.setText("Reset");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                                .addComponent(jButton3))))
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                    .addComponent(jLabel3)
                    .addComponent(jButton1)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jButton2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        if (jTextField2.getText().isEmpty() || jTextField3.getText().isEmpty()) {
            Notifications.getInstance().setJFrame(app);
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.BOTTOM_RIGHT, "Silahkan isi field 'Dari' dan 'Sampai'!");

        } else {
            java.sql.Connection cn = Connection.BukaKoneksi();
            try {
                st = cn.createStatement();
                DefaultTableModel model = new DefaultTableModel() {
                    @Override
                    public boolean isCellEditable(int i, int i1) {
                        return false; //To change body of generated methods, choose Tools | Templates.
                    }
                };
                rs = st.executeQuery("SELECT * FROM v_peminjaman WHERE TglPinjam BETWEEN '" + jTextField3.getText() + "' AND '" + jTextField2.getText() + "' ");
                model.addColumn("Kode Peminjaman");
                model.addColumn("Tanggal");
                model.addColumn("Jam");
                model.addColumn("Peminjam");
                model.addColumn("Jumlah Pinjam");
                model.addColumn("Petugas");

                model.getDataVector().removeAllElements();
                model.fireTableDataChanged();
                model.setRowCount(0);

                while (rs.next()) {
                    String kode = rs.getString("KodePeminjaman");
                    String tgl = rs.getString("TglPinjam");
                    String jam = rs.getString("JamPinjam");
                    String peminjam = rs.getString("NamaAnggota");
                    String jumlah = rs.getString("JumlahPinjam");
                    String petugas = rs.getString("NamaPetugas");

                    Object[] data = {
                        kode,
                        tgl,
                        jam,
                        peminjam,
                        jumlah,
                        petugas};
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

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (jTextField1.getText().isEmpty()) {
            Notifications.getInstance().setJFrame(app);
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.BOTTOM_RIGHT, "Silahkan isi Kode Peminjaman yang ingin dicari!");
        } else {
            java.sql.Connection cn = Connection.BukaKoneksi();
            try {
                st = cn.createStatement();
                DefaultTableModel model = new DefaultTableModel() {
                    @Override
                    public boolean isCellEditable(int i, int i1) {
                        return false; //To change body of generated methods, choose Tools | Templates.
                    }
                };
                rs = st.executeQuery("SELECT * FROM v_peminjaman WHERE KodePeminjaman = '" + jTextField1.getText() + "'");
                model.addColumn("Kode Peminjaman");
                model.addColumn("Tanggal");
                model.addColumn("Jam");
                model.addColumn("Peminjam");
                model.addColumn("Jumlah Pinjam");
                model.addColumn("Petugas");

                model.getDataVector().removeAllElements();
                model.fireTableDataChanged();
                model.setRowCount(0);

                while (rs.next()) {
                    String kode = rs.getString("KodePeminjaman");
                    String tgl = rs.getString("TglPinjam");
                    String jam = rs.getString("JamPinjam");
                    String peminjam = rs.getString("NamaAnggota");
                    String jumlah = rs.getString("JumlahPinjam");
                    String petugas = rs.getString("NamaPetugas");

                    Object[] data = {
                        kode,
                        tgl,
                        jam,
                        peminjam,
                        jumlah,
                        petugas};
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
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        showTable();
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
