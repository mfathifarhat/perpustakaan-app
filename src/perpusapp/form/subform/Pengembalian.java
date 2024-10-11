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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import perpusapp.Connection;
import static perpusapp.PerpusApp.app;
import static perpusapp.PerpusApp.showDeleteConfirmationDialog;
import raven.toast.Notifications;

/**
 *
 * @author Fathi
 */
public class Pengembalian extends javax.swing.JPanel {

    Statement st;
    ResultSet rs;

    /**
     * Creates new form Pengembalian
     */
    public Pengembalian() {
        initComponents();
        customInit();
        firstInit();
    }

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
            rs = st.executeQuery("SELECT * FROM tbl_kembali WHERE NoKembali IN (SELECT MAX(NoKembali) FROM tbl_kembali)");

            if (!rs.next()) {
                urutan = "KBL00001";
            } else {
                String lastKodePeminjaman = rs.getString("NoKembali");
                hitung = Long.parseLong(lastKodePeminjaman.substring(3)) + 1;
                urutan = "KBL" + String.format("%05d", hitung);
            }

            jTextField3.setText(urutan);
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, exc);
        }
    }

    private void customInit() {
        jLabel1.putClientProperty(FlatClientProperties.STYLE, "font:bold +8");
        jLabel7.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");

        jButton1.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:lighten(@background, 10%);"
                + "font:bold -1;"
                + "arc:5");
        jButton1.setMaximumSize(new Dimension(75, 30));
        jButton1.setPreferredSize(new Dimension(75, 30));
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

        jButton6.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:lighten(@background, 10%);"
                + "font:bold -1;"
                + "arc:5");
        jButton6.setMaximumSize(new Dimension(75, 30));
        jButton6.setPreferredSize(new Dimension(75, 30));
    }

    public void setAnggota(String kode, String nama) {
        jTextField1.setText(kode);
        jTextField2.setText(nama);

        clearBuku();
//        showTable(kode);
    }

    private void clearAgt() {
        jTextField1.setText("");
        jTextField2.setText("");
    }

    private void disableBuku() {
        jButton6.setEnabled(false);
        jTextField8.setEnabled(false);
        clearBuku();
    }

    public void enableBuku() {
        jButton6.setEnabled(true);
        jTextField8.setEnabled(true);
    }

    private void firstInit() {
        jTextField5.setText(app.NamaPetugas);
        autoNumber();
        clearAgt();
        clearBuku();
        disableBuku();

    }

    private void clearBuku() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        jTextField6.setText("0");
        jTextField6.setText("0");
        jTextField7.setText("0");
        jTextField8.setText("0");
        jTextField4.setText("0");
    }

    public boolean setBuku(JDialog buku, String no, String kode, String judul, String jumlah, String tanggal) {
        if (Integer.parseInt(jumlah) > 0) {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
//            DefaultTableModel model1 = (DefaultTableModel) jTable2.getModel();
            int jumRow = 0;

            LocalDate tglPjm = LocalDate.parse(tanggal, DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDate currentDate = LocalDate.now();

            long lamaPjm = ChronoUnit.DAYS.between(tglPjm, currentDate);

            for (int i = 0; i < model.getRowCount(); i++) {
                String row = model.getValueAt(i, 1).toString();
                if (row.equals(kode) && model.getValueAt(i, 0).toString().equals(no)) {
                    model.removeRow(i);
                    i--; // Decrease i to check the next row
                }
            }

            Object[] data = {
                no,
                kode,
                judul,
                jumlah,
                tanggal,};

            model.addRow(data);

            for (int i = 0; i < model.getRowCount(); i++) {
                String total = model.getValueAt(i, 3).toString();
                jumRow += Integer.parseInt(total);
            }

            jTextField6.setText(String.valueOf(jumRow));
            jTextField4.setText("0");
            jTextField8.setText("0");
            if (lamaPjm > 7) {
                long totalBefore = Long.valueOf(jTextField7.getText());
                jTextField7.setText(String.valueOf(totalBefore + ((lamaPjm - 7) * 5000) * Long.valueOf(jumlah)));
                jTextField4.setText(String.valueOf(Long.valueOf(jTextField8.getText()) - (totalBefore + ((lamaPjm - 7) * 5000) * Long.valueOf(jumlah))));
            }

            return true;
        } else {
            JOptionPane.showMessageDialog(buku, "Jumlah tidak boleh kurang dari 1!");
            return false;
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
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jTextField8 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();

        jLabel1.setText("Pengembalian");

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

        jLabel2.setText("Kode Pengembalian");

        jTextField3.setEditable(false);

        jLabel4.setText("Kode Anggota");

        jLabel5.setText("Nama Anggota");

        jTextField2.setEditable(false);

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

        jButton5.setText("Batal");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel6.setText("Petugas");

        jTextField5.setEditable(false);

        jLabel7.setText("Buku yang ingin dikembalikan");

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
                "No. Pinjam", "Kode Buku", "Judul Buku", "Jumlah", "Tanggal Pinjam"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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

        jLabel9.setText("Total Buku");

        jTextField6.setEditable(false);
        jTextField6.setText("0");
        jTextField6.setToolTipText("");
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

        jLabel10.setText("Total Denda");

        jTextField7.setEditable(false);
        jTextField7.setText("0");
        jTextField7.setToolTipText("");
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jButton6.setText("Input");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField8KeyReleased(evt);
            }
        });

        jLabel11.setText("Total Dibayar");

        jLabel3.setText("Kembali");

        jTextField4.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton6))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton5))
                                    .addComponent(jTextField3)
                                    .addComponent(jTextField2))
                                .addGap(98, 98, 98)
                                .addComponent(jLabel6)
                                .addGap(12, 12, 12)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(24, 24, 24)
                                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel10))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel3)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField4)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4)))
                        .addGap(40, 40, 40))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                    .addComponent(jButton6))
                .addGap(8, 8, 8)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4)
                    .addComponent(jButton3)
                    .addComponent(jLabel3)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(98, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        SrcAnggotaPGB srcAgt = new SrcAnggotaPGB(app, "Search Anggota", true);

        srcAgt.setLocationRelativeTo(app);
        srcAgt.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
//        disableBuku();
//        clearAgt();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
        JTable table = (JTable) jTable1;
        Point point = evt.getPoint();
        int row = table.rowAtPoint(point);
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        if (evt.getClickCount() == 2 && table.getSelectedRow() != -1) {
            boolean deleteConfirmed = showDeleteConfirmationDialog("Apakah anda yakin ingin menghapus data ini?");
            if (deleteConfirmed) {
                int denda;

                LocalDate tglPjm = LocalDate.parse(model.getValueAt(row, 4).toString(), DateTimeFormatter.ISO_LOCAL_DATE);
                LocalDate currentDate = LocalDate.now();
                
                Long jumlah = Long.valueOf(model.getValueAt(row, 3).toString());

                long lamaPjm = ChronoUnit.DAYS.between(tglPjm, currentDate);
                if (lamaPjm > 7) {
                    long totalBefore = Long.valueOf(jTextField7.getText());
                    jTextField7.setText(String.valueOf(totalBefore - ((lamaPjm - 7) * 5000) * Long.valueOf(jumlah)));
                    jTextField4.setText(String.valueOf(Long.valueOf(jTextField8.getText()) - (totalBefore + ((lamaPjm - 7) * 5000) * Long.valueOf(jumlah))));
                }

                model.removeRow(table.getSelectedRow());
                int jumPjm = 0;
                int jumRow = 0;

                for (int i = 0; i < model.getRowCount(); i++) {
                    String total = model.getValueAt(i, 2).toString();
                    jumPjm += Integer.parseInt(total);
                }

                for (int i = 0; i < model.getRowCount(); i++) {
                    String total = model.getValueAt(i, 3).toString();
                    jumRow += Integer.parseInt(total);
                }

                jTextField6.setText(String.valueOf(jumRow));
                jTextField4.setText("0");
                jTextField8.setText("0");
                jTextField6.setText(String.valueOf(jumPjm));
            }
        }
    }//GEN-LAST:event_jTable1MousePressed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed


    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (jTextField1.getText().isEmpty() || jTextField2.getText().isEmpty() || jTextField3.getText().isEmpty() || jTextField8.getText().isEmpty() || jTable1.getModel().getRowCount() < 0) {
            Notifications.getInstance().setJFrame(app);
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.BOTTOM_RIGHT, "Silahkan isi semua field yang diperlukan!");
        } else {
            if (Integer.valueOf(jTextField4.getText()) < 0) {
                Notifications.getInstance().setJFrame(app);
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.BOTTOM_RIGHT, "Uang bayar kurang!");
            } else {
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

                String kodePjm = jTextField3.getText();
                String kodeAgt = jTextField1.getText();
                String namaAgt = jTextField2.getText();
                String kodePtg = app.KodePetugas;
                String totalDibayar = jTextField8.getText();
                String totalDenda = jTextField7.getText();
                String kembali = jTextField4.getText();
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
                    String total = model.getValueAt(i, 3).toString();
                    jumPjm += Integer.parseInt(total);
                }

                java.sql.Connection cn = Connection.BukaKoneksi();

                try {
                    PreparedStatement ps = (PreparedStatement) cn.prepareStatement("INSERT INTO tbl_kembali VALUES (?,?,?,?,?,?,?,?)");
                    //                        st.executeUpdate("INSERT INTO tbl_petugas VALUES ('" + kode + "','" + nama + "','" + password + "', '" + level + "')");
                    ps.setString(1, kodePjm);
                    ps.setString(2, tgl);
                    ps.setString(3, String.valueOf(jumPjm));
                    ps.setString(4, totalDenda);
                    ps.setString(5, totalDibayar);
                    ps.setString(6, kembali);
                    ps.setString(7, kodeAgt);
                    ps.setString(8, kodePtg);

                    ps.executeUpdate();

                } catch (Exception ex) {

                }

                try {
                    for (int i = 0; i < model.getRowCount(); i++) {

                        PreparedStatement ps = (PreparedStatement) cn.prepareStatement("INSERT INTO tbl_detailkembali VALUES (?,?,?,?)");
                        //                        st.executeUpdate("INSERT INTO tbl_petugas VALUES ('" + kode + "','" + nama + "','" + password + "', '" + level + "')");
                        ps.setString(1, kodePjm);
                        ps.setString(2, model.getValueAt(i, 0).toString());
                        ps.setString(3, model.getValueAt(i, 1).toString());
                        ps.setString(4, model.getValueAt(i, 3).toString());

                        ps.executeUpdate();

                        rs = st.executeQuery("SELECT * FROM tbl_buku WHERE KodeBuku = '" + model.getValueAt(i, 1).toString() + "'");
//
                        while (rs.next()) {
                            PreparedStatement upBuku = (PreparedStatement) cn.prepareStatement("Update tbl_buku set JumlahBuku='" + String.valueOf((rs.getInt("JumlahBuku") + Integer.parseInt(model.getValueAt(i, 3).toString()))) + "' where KodeBuku='" + model.getValueAt(i, 1).toString() + "'");
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
        }


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        firstInit();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        SrcBukuPGB srcAgt = new SrcBukuPGB(app, jTextField1.getText(), true);

        srcAgt.setLocationRelativeTo(app);
        srcAgt.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyReleased
        // TODO add your handling code here:
        int denda = Integer.valueOf(jTextField7.getText());
        int bayar;

        if (jTextField8.getText().isEmpty()) {
            bayar = 0;
        } else {
            bayar = Integer.valueOf(jTextField8.getText());
        }

        jTextField4.setText(String.valueOf(bayar - denda));
    }//GEN-LAST:event_jTextField8KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables

}
