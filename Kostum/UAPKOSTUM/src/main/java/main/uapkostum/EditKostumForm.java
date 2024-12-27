package main.uapkostum;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditKostumForm extends JFrame {
    private JTextField idKostumField;
    private JTextField namaKostumField;
    private JComboBox<String> kategoriComboBox;
    private JComboBox<String> temaComboBox;
    private JTextField ukuranField;
    private JComboBox<String> statusComboBox;
    private JLabel gambarLabel;

    public EditKostumForm(String idKostum, String namaKostum, String kategori, String tema, String ukuran, String status, String pathGambar) {
        // Pengaturan Frame
        setTitle("Edit Kostum");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

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
        add(mainPanel);

        // Header
        JLabel headerLabel = new JLabel("Edit Kostum", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBounds(0, 20, 400, 50);
        mainPanel.add(headerLabel);

        // ID Kostum
        JLabel idKostumLabel = new JLabel("ID Kostum:");
        idKostumLabel.setForeground(Color.WHITE);
        idKostumLabel.setBounds(50, 80, 100, 30);
        mainPanel.add(idKostumLabel);

        idKostumField = new JTextField(idKostum);
        idKostumField.setBounds(150, 80, 200, 30);
        idKostumField.setEditable(false); // Tidak bisa diedit
        mainPanel.add(idKostumField);

        // Nama Kostum
        JLabel namaKostumLabel = new JLabel("Nama Kostum:");
        namaKostumLabel.setForeground(Color.WHITE);
        namaKostumLabel.setBounds(50, 120, 100, 30);
        mainPanel.add(namaKostumLabel);

        namaKostumField = new JTextField(namaKostum);
        namaKostumField.setBounds(150, 120, 200, 30);
        mainPanel.add(namaKostumField);

        // Kategori
        JLabel kategoriLabel = new JLabel("Kategori:");
        kategoriLabel.setForeground(Color.WHITE);
        kategoriLabel.setBounds(50, 160, 100, 30);
        mainPanel.add(kategoriLabel);

        kategoriComboBox = new JComboBox<>(new String[]{"Kostum 1", "Kostum 2", "Kostum 3"});
        kategoriComboBox.setSelectedItem(kategori);
        kategoriComboBox.setBounds(150, 160, 200, 30);
        mainPanel.add(kategoriComboBox);

        // Tema
        JLabel temaLabel = new JLabel("Tema:");
        temaLabel.setForeground(Color.WHITE);
        temaLabel.setBounds(50, 200, 100, 30);
        mainPanel.add(temaLabel);

        temaComboBox = new JComboBox<>(new String[]{"Tema 1", "Tema 2", "Tema 3"});
        temaComboBox.setSelectedItem(tema);
        temaComboBox.setBounds(150, 200, 200, 30);
        mainPanel.add(temaComboBox);

        // Ukuran
        JLabel ukuranLabel = new JLabel("Ukuran:");
        ukuranLabel.setForeground(Color.WHITE);
        ukuranLabel.setBounds(50, 240, 100, 30);
        mainPanel.add(ukuranLabel);

        ukuranField = new JTextField(ukuran);
        ukuranField.setBounds(150, 240, 200, 30);
        mainPanel.add(ukuranField);

        // Status
        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setBounds(50, 280, 100, 30);
        mainPanel.add(statusLabel);

        statusComboBox = new JComboBox<>(new String[]{"Tersedia", "Tidak Tersedia"});
        statusComboBox.setSelectedItem(status);
        statusComboBox.setBounds(150, 280, 200, 30);
        mainPanel.add(statusComboBox);

        // Gambar
        JLabel gambarLabel = new JLabel("Gambar:");
        gambarLabel.setForeground(Color.WHITE);
        gambarLabel.setBounds(50, 320, 100, 30);
        mainPanel.add(gambarLabel);

        this.gambarLabel = new JLabel();
        this.gambarLabel.setBounds(150, 320, 200, 200);
        this.gambarLabel.setIcon(new ImageIcon(pathGambar)); // Menampilkan gambar
        mainPanel.add(this.gambarLabel);

        // Tombol Edit
        JButton editButton = createRoundedButton("Edit");
        editButton.setBounds(50, 540, 130, 45);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logika untuk menyimpan perubahan
                String updatedNamaKostum = namaKostumField.getText();
                String updatedKategori = (String) kategoriComboBox.getSelectedItem();
                String updatedTema = (String) temaComboBox.getSelectedItem();
                String updatedUkuran = ukuranField.getText();
                String updatedStatus = (String) statusComboBox.getSelectedItem();

                // Simpan perubahan ke database atau model
                // (Logika penyimpanan tidak ditampilkan di sini)

                JOptionPane.showMessageDialog(EditKostumForm.this, "Data Kostum telah diperbarui.");
                dispose(); // Menutup form edit
            }
        });
        mainPanel.add(editButton);

        // Tombol Hapus
        JButton deleteButton = createRoundedButton("Hapus");
        deleteButton.setBounds(220, 540, 130, 45);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logika untuk menghapus kostum
                // (Logika penghapusan tidak ditampilkan di sini)

                JOptionPane.showMessageDialog(EditKostumForm.this, "Data Kostum telah dihapus.");
                dispose(); // Menutup form edit
            }
        });
        mainPanel.add(deleteButton);
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
            // Contoh data untuk mengedit
            EditKostumForm editForm = new EditKostumForm("K001", "Kostum Halloween", "Kostum 1", "Tema 1", "M", "Tersedia", "C:/path/gambar1.png");
            editForm.setVisible(true);
        });
    }
}