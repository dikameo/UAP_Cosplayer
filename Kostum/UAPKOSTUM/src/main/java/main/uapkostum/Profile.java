package main.uapkostum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

public class Profile extends JFrame {
    public Profile() {
        // Pengaturan Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Pengaturan Frame
        setTitle("Profile");
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
        JLabel headerLabel = new JLabel("Profile Anggota", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        headerLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(headerLabel, gbc);

        // Daftar Anggota
        String[][] anggota = {
                {"Wahyu Andika", "NIM: 202310370311075"},
                {"Ahmad Nur Mu’minin", "NIM: 202310370311089"}
        };

        for (int i = 0; i < anggota.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            mainPanel.add(createProfileButton(anggota[i][0], anggota[i][1], i), gbc);
        }

        // Tombol Kembali
        JButton backButton = createRoundedButton("Kembali");
        backButton.addActionListener(e -> {
            dispose();
            SwingUtilities.invokeLater(() -> {
                MainPage mainPage = new MainPage();
                mainPage.setVisible(true);
            });
        });
        gbc.gridx = 0;
        gbc.gridy = anggota.length + 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(backButton, gbc);

        // Set Shape Rounded
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));

        add(mainPanel);

        // Tambahkan efek dragging
        addDragListener(mainPanel);
    }

    // Membuat Tombol Profil dengan desain modern
    private JButton createProfileButton(String nama, String nim, int index) {
        JButton button = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Background gradient untuk setiap tombol
                Color[] colors = {
                        new Color(52, 152, 219),   // Biru
                        new Color(46, 204, 113)    // Hijau
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
        JLabel titleLabel = new JLabel(nama);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel descLabel = new JLabel(nim);
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

        return button;
    }

    // Membuat tombol rounded
    private JButton createRoundedButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Background gradient
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(52, 152, 219),   // Biru
                        getWidth(), getHeight(), new Color(41, 128, 185).darker()
                );
                g2d.setPaint(gradient);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                super.paintComponent(g);
                g2d.dispose();
            }
        };

        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        return button;
    }

    // Tambahkan method untuk drag listener
    private void addDragListener(JPanel panel) {
        final Point[] mousePoint = new Point[1];
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mousePoint[0] = e.getPoint();
            }
        });

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen() - mousePoint[0].x;
                int y = e.getYOnScreen() - mousePoint[0].y;
                setLocation(x, y);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Profile profile = new Profile();
            profile.setVisible(true);
        });
    }
}