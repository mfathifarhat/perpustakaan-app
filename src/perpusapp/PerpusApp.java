/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package perpusapp;

import perpusapp.form.MainForm;
import perpusapp.form.Login;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.formdev.flatlaf.fonts.inter.FlatInterFont;
import com.formdev.flatlaf.*;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import javax.swing.*;
import perpusapp.form.MainFormSecond;
import raven.toast.Notifications;

/**
 *
 * @author Fathi
 */
public class PerpusApp extends JFrame {

    public static PerpusApp app;

    public MainFormSecond mainForm;
    private final Login login;

    public String KodePetugas;
    public String NamaPetugas;
    public String LevelPetugas;

    public PerpusApp() {
        login = new Login();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1200, 700));
        setMinimumSize(new Dimension(1200, 700));
        setLocationRelativeTo(null);
        setContentPane(login);
    }

    public static boolean showDeleteConfirmationDialog(String message) {
        int choice = JOptionPane.showConfirmDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION);

        return choice == JOptionPane.YES_OPTION;
    }

    public static void login(String kode, char[] pass) {
        Statement st;
        ResultSet rs;
        java.sql.Connection cn = Connection.BukaKoneksi();

        try {
            st = cn.createStatement();
            String kodePetugas = kode;
            char[] password = pass;
            String passwordStr = new String(password);

            rs = st.executeQuery("SELECT * FROM tbl_petugas WHERE KodePetugas = '" + kodePetugas + "' AND PasswordPetugas = '" + passwordStr + "' ");

            if (rs.next()) {
                app.KodePetugas = rs.getString("KodePetugas");
                app.NamaPetugas = rs.getString("NamaPetugas");
                app.LevelPetugas = rs.getString("LevelPetugas");

                setForm();
                FlatAnimatedLafChange.showSnapshot();
                app.setContentPane(app.mainForm);
                SwingUtilities.updateComponentTreeUI(app.mainForm);
                FlatAnimatedLafChange.hideSnapshotWithAnimation();

                Notifications.getInstance().setJFrame(app);
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.BOTTOM_RIGHT, "Login Berhasil!");

            } else {
                  Notifications.getInstance().setJFrame(app);
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.BOTTOM_RIGHT, "Username atau Password salah!");
        
            }
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, exc);

        }
    }

    private static  void setForm() {
        app.mainForm = new MainFormSecond(app.LevelPetugas);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        FlatMacLightLaf.registerCustomDefaultsSource("theme");
        FlatMacLightLaf.setup();

        FlatInterFont.install();
        UIManager.put("defaultFont", new Font(FlatInterFont.FAMILY, Font.PLAIN, 13));
        java.awt.EventQueue.invokeLater(() -> {
            app = new PerpusApp();
            app.setVisible(true);
        });
    }

    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 719, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 521, Short.MAX_VALUE)
        );

        pack();
    }

}
