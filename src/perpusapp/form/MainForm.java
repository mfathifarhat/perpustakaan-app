/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package perpusapp.form;

import org.w3c.dom.Document;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import com.formdev.flatlaf.*;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.Statement;
import perpusapp.PerpusApp;
import javax.swing.border.EmptyBorder;
import static perpusapp.PerpusApp.app;
import static perpusapp.PerpusApp.login;
import perpusapp.form.subform.Beranda;
import perpusapp.form.subform.ManageAdmin;
import perpusapp.form.subform.ManageAnggota;

/**
 *
 * @author Fathi
 */
public class MainForm extends javax.swing.JPanel {

    /**
     * Creates new form MainForm
     */
    public MainForm() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("fill"));
        JPanel mainPanel = new JPanel(new MigLayout("wrap,fillx,insets 16 20 12 20", "fill,250"));
        mainPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:lighten(@background, 3%)");

        formPanel = new JPanel(new MigLayout("wrap,fillx,insets 12 16 12 16", "fill"));
        container = new JScrollPane(formPanel);
        container.getVerticalScrollBar().setUnitIncrement(20);
        container.putClientProperty(FlatClientProperties.STYLE, ""
                + "borderWidth:0;");

        button1 = new JButton("Beranda");
        button2 = new JButton("Manage Admin");
        button3 = new JButton("Manage Anggota");
        button4 = new JButton("Manage Buku");
        button5 = new JButton("Peminjaman");

        button1.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Menu.button.background;"
                + "arc:10;"
                + "focusWidth:0;"
                + "borderWidth:0;");
        button2.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Menu.button.background;"
                + "arc:10;"
                + "focusWidth:0;"
                + "borderWidth:0;");
        button3.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Menu.button.background;"
                + "arc:10;"
                + "focusWidth:0;"
                + "borderWidth:0;");
        button4.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Menu.button.background;"
                + "arc:10;"
                + "focusWidth:0;"
                + "borderWidth:0;");
        button5.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Menu.button.background;"
                + "arc:10;"
                + "focusWidth:0;"
                + "borderWidth:0;");

        button1.setHorizontalAlignment(SwingConstants.LEFT);
        button1.setMinimumSize(new Dimension(50, 40));
        button1.setFocusable(false);

        button2.setHorizontalAlignment(SwingConstants.LEFT);
        button2.setMinimumSize(new Dimension(50, 40));
        button2.setFocusable(false);

        button3.setHorizontalAlignment(SwingConstants.LEFT);
        button3.setMinimumSize(new Dimension(50, 40));
        button3.setFocusable(false);

        button4.setHorizontalAlignment(SwingConstants.LEFT);
        button4.setMinimumSize(new Dimension(50, 40));
        button4.setFocusable(false);

        button5.setHorizontalAlignment(SwingConstants.LEFT);
        button5.setMinimumSize(new Dimension(50, 40));
        button5.setFocusable(false);

        button1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        header = new JLabel("Perpustakaan");
        header.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +5;"
                + "iconTextGap:10;");
        header.putClientProperty("JComponent.minimumHeight", 35);
        header.setBorder(new EmptyBorder(5, 12, 5, 5));

        Color lightColor = FlatUIUtils.getUIColor("Menu.icon.lightColor", Color.red);
        Color darkColor = FlatUIUtils.getUIColor("Menu.icon.darkColor", Color.red);

        FlatSVGIcon icon = new FlatSVGIcon("icons/library.svg", 50, 50);
//        FlatSVGIcon.ColorFilter f = new FlatSVGIcon.ColorFilter();
//        f.add(Color.decode("#969696"), lightColor, darkColor);
//        icon.setColorFilter(f);

        header.setIcon(icon);

        hr = new JPanel();
        hr.setMinimumSize(new Dimension(50, 1));
        hr.setPreferredSize(new Dimension(100, 1));
        hr.putClientProperty(FlatClientProperties.STYLE, "background:#3B3B3B");

        mainPanel.add(header, "");
        mainPanel.add(hr, "gapy 2 2, gapx 10 10");
        mainPanel.add(button2, "gapy 2");
        mainPanel.add(button3, "gapy 2");
        mainPanel.add(button4, "gapy 2");
        mainPanel.add(button5, "gapy 2");
        add(mainPanel, "dock west");
        add(container, "grow");

        showForm(new Beranda());

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showForm(new Beranda());
            }

        }
        );

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showForm(new ManageAdmin());
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showForm(new ManageAnggota());
            }
        });

//        button2.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                showForm(new ManageAdmin());
//            }
//
//        }
//        );
    }

    public void showForm(Component component) {
        formPanel.removeAll();
        formPanel.add(component);
        formPanel.repaint();
        formPanel.revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JLabel header;
    private JPanel hr;
    private JPanel formPanel;
    private JScrollPane container;
}
