package main.uapkostum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

public class MainPage extends JFrame {
    public MainPage() {
        // Pengaturan Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Pengaturan Frame
        setTitle("Cosrent Manajemen");
        setSize(600, 700);
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

                // Background gradient dengan variasi warna
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(41, 128, 185),  // Biru cerah
                        getWidth(), getHeight(), new Color(142, 68, 173)  // Ungu
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());

                g2d.dispose();
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Header
        JLabel headerLabel = new JLabel("Cosrent Manajemen", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        headerLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(headerLabel, gbc);

        // Tombol Menu Utama
        String[][] menuItems = {
                {"Manajemen Kostum", "Kelola semua kostum"},
                {"Profile", "Lihat dan edit profil"},
                {"Keluar", "Tutup aplikasi"}
        };

        for (int i = 0; i < menuItems.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            mainPanel.add(createMenuButton(menuItems[i][0], menuItems[i][1], i), gbc);
        }

        // Set Shape Rounded
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));

        add(mainPanel);

        // Tambahkan efek dragging
        addDragListener(mainPanel);
    }

    // Membuat Tombol Menu dengan desain modern
    private JButton createMenuButton(String title, String description, int index) {
        JButton button = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Background gradient untuk setiap tombol
                Color[] colors = {
                        new Color(52, 152, 219),   // Biru
                        new Color(46, 204, 113),   // Hijau
                        new Color(231, 76, 60)     // Merah
                };

                GradientPaint gradient = new GradientPaint(
                        0, 0, colors[index],
                        getWidth(), getHeight(), colors[index].darker()
                );
                g2d.setPaint(gradient);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                super.paintComponent(g);
                g2d.dispose();
            }
        };

        // Layout tombol
        button.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel descLabel = new JLabel(description);
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        descLabel.setForeground(Color.WHITE);
        descLabel.setHorizontalAlignment(SwingConstants.CENTER);

        button.add(titleLabel, BorderLayout.CENTER);
        button.add(descLabel, BorderLayout.SOUTH);

        button.setPreferredSize(new Dimension(500, 120));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Aksi untuk setiap tombol
        button.addActionListener(e -> {
            switch(title) {
                case "Manajemen Kostum":
                    openCostumeInventorySystem();
                    break;
                case "Profile":
                    openProfilePage();
                    break;
                case "Keluar":
                    // Tambahkan konfirmasi keluar
                    int result = JOptionPane.showConfirmDialog(
                            MainPage.this,
                            "Apakah Anda yakin ingin keluar?",
                            "Konfirmasi Keluar",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (result == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                    break;
            }
        });

        return button;
    }

    // Method untuk membuka Costume Inventory System
    private void openCostumeInventorySystem() {
        dispose(); // Tutup halaman utama
        SwingUtilities.invokeLater(() -> {
            CostumeInventorySystem costumeSystem = new CostumeInventorySystem();
            costumeSystem.setVisible(true);
        });
    }

    // Method untuk membuka Profile Page
    private void openProfilePage() {
        dispose(); // Tutup halaman utama
        SwingUtilities.invokeLater(() -> {
            Profile profilePage = new Profile();
            profilePage.setVisible(true);
        });
    }

    // Tambahkan method untuk drag listener
    private void addDragListener(JPanel panel) {
        final Point[] mousePoint = new Point[1];
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Tambahkan aksi klik kanan untuk keluar
                if (e.getButton() == MouseEvent.BUTTON3) {
                    int result = JOptionPane.showConfirmDialog(
                            MainPage.this,
                            "Apakah Anda yakin ingin keluar?",
                            "Konfirmasi Keluar",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (result == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                }
                mousePoint[0] = e.getPoint();
            }
        });

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen() - mousePoint[0].x;
                int y = e.getYOnScreen() - mousePoint[0].y;
                setLocation(x, y);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainPage mainPage = new MainPage();
            mainPage.setVisible(true);
        });
    }
}