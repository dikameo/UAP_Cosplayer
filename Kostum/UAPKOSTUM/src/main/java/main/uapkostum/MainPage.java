package main.uapkostum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class MainPage extends JFrame {
    public MainPage() {
        // Pengaturan Frame
        setTitle("Cosrent Manajemen");
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
        JLabel headerLabel = new JLabel("Cosrent Manajemen", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBounds(0, 50, 400, 50);
        mainPanel.add(headerLabel);

        // Tombol Tambahkan Data
        JButton addDataButton = createRoundedButton("Tambahkan Data");
        addDataButton.setForeground(new Color(0, 0, 139)); // Warna biru tua
        addDataButton.setBounds(50, 150, 300, 45);
        addDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aksi untuk menambahkan data
                JOptionPane.showMessageDialog(MainPage.this, "Fitur Tambahkan Data belum tersedia.");
            }
        });
        mainPanel.add(addDataButton);

        // Tombol Tampilkan Data
        JButton displayDataButton = createRoundedButton("Tampilkan Data");
        displayDataButton.setForeground(new Color(0, 0, 139)); // Warna biru tua
        displayDataButton.setBounds(50, 220, 300, 45);
        displayDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aksi untuk menampilkan data
                JOptionPane.showMessageDialog(MainPage.this, "Fitur Tampilkan Data belum tersedia.");
            }
        });
        mainPanel.add(displayDataButton);

        // Tombol Profile
        JButton profileButton = createRoundedButton("Profile");
        profileButton.setForeground(new Color(0, 0, 139)); // Warna biru tua
        profileButton.setBounds(50, 290, 300, 45);
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aksi untuk melihat profile
                JOptionPane.showMessageDialog(MainPage.this, "Fitur Profile belum tersedia.");
            }
        });
        mainPanel.add(profileButton);

        // Tombol Keluar
        JButton exitButton = createRoundedButton("Keluar");
        exitButton.setForeground(new Color(0, 0, 139)); // Warna biru tua
        exitButton.setBounds(50, 360, 300, 45);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mainPanel.add(exitButton);

        // Set Shape Rounded
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));

        add(mainPanel);
    }

    // Membuat Tombol Rounded
    private JButton createRoundedButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Background biru gelap
                g2d.setColor(new Color(25, 25, 112)); // Dark blue
                g2d.fillRoundRect(8, 8, getWidth(), getHeight(), 15, 15);

                super.paintComponent(g);
                g2d.dispose();
            }
        };
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainPage mainPage = new MainPage();
            mainPage.setVisible(true);
        });
    }
}