package main.uapkostum;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class KostumForm extends JFrame {
    private JTextField idKostumField;
    private JTextField namaKostumField;
    private JComboBox<String> kategoriComboBox;
    private JComboBox<String> temaComboBox;
    private JTextField ukuranField;
    private JComboBox<String> statusComboBox;
    private JTextField pathGambarField;

    public KostumForm() {
        // Pengaturan Frame
        setTitle("Form Kostum");
        setSize(400, 600);
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
        JLabel headerLabel = new JLabel("Form Kostum", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBounds(0, 50, 400, 50);
        mainPanel.add(headerLabel);

        // ID Kostum
        JLabel idKostumLabel = new JLabel("ID Kostum:");
        idKostumLabel.setForeground(Color.WHITE);
        idKostumLabel.setBounds(50, 120, 100, 30);
        mainPanel.add(idKostumLabel);

        idKostumField = createRoundedTextField();
        idKostumField.setBounds(150, 120, 200, 30);
        mainPanel.add(idKostumField);

        // Nama Kostum
        JLabel namaKostumLabel = new JLabel("Nama Kostum:");
        namaKostumLabel.setForeground(Color.WHITE);
        namaKostumLabel.setBounds(50, 160, 100, 30);
        mainPanel.add(namaKostumLabel);

        namaKostumField = createRoundedTextField();
        namaKostumField.setBounds(150, 160, 200, 30);
        mainPanel.add(namaKostumField);

        // Kategori
        JLabel kategoriLabel = new JLabel("Kategori:");
        kategoriLabel.setForeground(Color.WHITE);
        kategoriLabel.setBounds(50, 200, 100, 30);
        mainPanel.add(kategoriLabel);

        kategoriComboBox = new JComboBox<>(new String[]{"Kostum 1", "Kostum 2", "Kostum 3"});
        kategoriComboBox.setBounds(150, 200, 200, 30);
        mainPanel.add(kategoriComboBox);

        // Tema
        JLabel temaLabel = new JLabel("Tema:");
        temaLabel.setForeground(Color.WHITE);
        temaLabel.setBounds(50, 240, 100, 30);
        mainPanel.add(temaLabel);

        temaComboBox = new JComboBox<>(new String[]{"Tema 1", "Tema 2", "Tema 3"});
        temaComboBox.setBounds(150, 240, 200, 30);
        mainPanel.add(temaComboBox);

        // Ukuran
        JLabel ukuranLabel = new JLabel("Ukuran:");
        ukuranLabel.setForeground(Color.WHITE);
        ukuranLabel.setBounds(50, 280, 100, 30);
        mainPanel.add(ukuranLabel);

        ukuranField = createRoundedTextField();
        ukuranField.setBounds(150, 280, 200, 30);
        mainPanel.add(ukuranField);

        // Status
        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setBounds(50, 320, 100, 30);
        mainPanel.add(statusLabel);

        statusComboBox = new JComboBox<>(new String[]{"Tersedia", "Tidak Tersedia"});
        statusComboBox.setBounds(150, 320, 200, 30);
        mainPanel.add(statusComboBox);

        // Gambar
        JLabel gambarLabel = new JLabel("Gambar:");
        gambarLabel.setForeground(Color.WHITE);
        gambarLabel.setBounds(50, 360, 100, 30);
        mainPanel.add(gambarLabel);

        JButton browseButton = new JButton("Browse");
        browseButton.setBounds(150, 360, 100, 30);
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(KostumForm.this);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    pathGambarField.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });
        mainPanel.add(browseButton);

        // Path Gambar
        JLabel pathGambarLabel = new JLabel("Path Gambar:");
        pathGambarLabel.setForeground(Color.WHITE);
        pathGambarLabel.setBounds(50, 400, 100, 30);
        mainPanel.add(pathGambarLabel);

        pathGambarField = createRoundedTextField();
        pathGambarField.setBounds(150, 400, 200, 30);
        pathGambarField.setEditable(false); // Tidak bisa diedit langsung
        mainPanel.add(pathGambarField);

        // Tombol Simpan
        JButton saveButton = createRoundedButton("Simpan");
        saveButton.setBounds(50, 450, 130, 45);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aksi untuk menyimpan data
                JOptionPane.showMessageDialog(KostumForm.this, "Data Kostum telah disimpan.");
            }
        });
        mainPanel.add(saveButton);

        // Tombol Kembali
        JButton backButton = createRoundedButton("Kembali");
        backButton.setBounds(220, 450, 130, 45);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kembali ke halaman utama
                MainPage mainPage = new MainPage();
                mainPage.setVisible(true);
                dispose(); // Menutup form kostum
            }
        });
        mainPanel.add(backButton);

        // Set Shape Rounded
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));

        add(mainPanel);
    }

    // Membuat TextField Rounded
    private JTextField createRoundedTextField() {
        JTextField textField = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(255, 255, 255, 50));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                super.paintComponent(g);
                g2d.dispose();
            }
        };
        textField.setOpaque(false);
        textField.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        textField.setForeground(Color.WHITE);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        return textField;
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
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

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
            KostumForm kostumForm = new KostumForm();
            kostumForm.setVisible(true);
        });
    }
}