package main.uapkostum;

import javax.swing.*;
import java.awt.*;

public class Profile extends JFrame {
    public Profile() {
        // Pengaturan Frame
        setTitle("Profile");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);

        // Panel Utama
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Background gradient
                GradientPaint gradient = new GradientPaint(0, 0, new Color(58, 123, 213),
                        getWidth(), getHeight(), new Color(58, 96, 115));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());

                g2d.dispose();
            }
        };
        mainPanel.setLayout(null);

        // Header
        JLabel headerLabel = new JLabel("Profile", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBounds(0, 50, 400, 50);
        mainPanel.add(headerLabel);

        // Nama Wahyu Andika
        JLabel nameLabel1 = new JLabel("Wahyu Andika", SwingConstants.CENTER);
        nameLabel1.setFont(new Font("Arial", Font.PLAIN, 18));
        nameLabel1.setForeground(Color.WHITE);
        nameLabel1.setBounds(0, 120, 400, 30);
        mainPanel.add(nameLabel1);

        JLabel nimLabel1 = new JLabel("NIM: 202310370311075", SwingConstants.CENTER);
        nimLabel1.setFont(new Font("Arial", Font.PLAIN, 16));
        nimLabel1.setForeground(Color.WHITE);
        nimLabel1.setBounds(0, 160, 400, 30);
        mainPanel.add(nimLabel1);

        // Nama Ahmad Nurmukminin
        JLabel nameLabel2 = new JLabel("Ahmad Nurmukminin", SwingConstants.CENTER);
        nameLabel2.setFont(new Font("Arial", Font.PLAIN, 18));
        nameLabel2.setForeground(Color.WHITE);
        nameLabel2.setBounds(0, 220, 400, 30);
        mainPanel.add(nameLabel2);

        JLabel nimLabel2 = new JLabel("NIM: 202310370311089", SwingConstants.CENTER);
        nimLabel2.setFont(new Font("Arial", Font.PLAIN, 16));
        nimLabel2.setForeground(Color.WHITE);
        nimLabel2.setBounds(0, 260, 400, 30);
        mainPanel.add(nimLabel2);

        // Tombol Kembali
        JButton backButton = new JButton("Kembali");
        backButton.setBounds(150, 350, 100, 40);
        backButton.setBackground(new Color(25, 25, 112)); // Dark blue
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setFocusPainted(false);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(e -> {
            // Kembali ke halaman sebelumnya
            MainPage mainPage = new MainPage();
            mainPage.setVisible(true);
            dispose(); // Menutup halaman profil
        });
        mainPanel.add(backButton);

        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Profile profile = new Profile();
            profile.setVisible(true);
        });
    }
}