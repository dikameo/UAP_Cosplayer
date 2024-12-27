package main.uapkostum;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.border.*;
import javax.swing.table.TableColumnModel;
import org.apache.poi.xwpf.usermodel.*;
import java.io.FileOutputStream;
import java.io.IOException;
public class CostumeInventorySystem extends JFrame {
    // Skema Warna Modern
    private static final Color DARK_BLUE = new Color(44, 62, 80);
    private static final Color LIGHT_BLUE = new Color(52, 152, 219);
    private static final Color BACKGROUND_COLOR = new Color(236, 240, 241);
    private static final Color ACCENT_COLOR = new Color(231, 76, 60);
    private static final Color SOFT_GRAY = new Color(189, 195, 199);

    // Font
    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 24);
    private static final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 16);
    private static final Font REGULAR_FONT = new Font("Segoe UI", Font.PLAIN, 14);

    // Komponen
    private JTextField idField, nameField, sizeField, imagePath;
    private JComboBox<String> categoryBox, themeBox, statusBox;
    private JTable costumeTable;
    private DefaultTableModel tableModel;
    private JButton addButton, editButton, deleteButton, browseButton, saveButton;
    private JLabel imageLabel, titleLabel;

    public CostumeInventorySystem() {


        // Konfigurasi Frame
        setTitle("Costume Inventory Management");
        setSize(1400, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(BACKGROUND_COLOR);

        // Layout Utama
        setLayout(new BorderLayout(15, 15));

        // Tambahkan Komponen
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createMainPanel(), BorderLayout.CENTER);

        // Tambahkan Event Listeners
        addEventListeners();

        // Atur Look and Feel
        setCustomLookAndFeel();
    }


    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(DARK_BLUE);

        titleLabel = new JLabel("Costume Inventory Management", SwingConstants.CENTER);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(Color.WHITE);

        // Tombol Kembali
        JButton backButton = new JButton("Kembali");
        backButton.setFont(HEADER_FONT);
        backButton.setBackground(LIGHT_BLUE);
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        // Efek hover
        backButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                backButton.setBackground(DARK_BLUE);
                backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent evt) {
                backButton.setBackground(LIGHT_BLUE);
                backButton.setCursor(Cursor.getDefaultCursor());
            }
        });

        // Aksi kembali ke MainPage
        backButton.addActionListener(e -> {
            dispose(); // Tutup halaman CostumeInventorySystem
            SwingUtilities.invokeLater(() -> {
                MainPage mainPage = new MainPage();
                mainPage.setVisible(true);
            });
        });

        headerPanel.add(backButton, BorderLayout.WEST);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        return headerPanel;
    }

    // Buat Panel Utama
    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20)); // Kurangi padding atas

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 10, 10); // Kurangi jarak antar komponen

        // Panel Form Input
        JPanel formPanel = createFormPanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        gbc.weighty = 0.4; // Tambahkan bobot vertikal
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(formPanel, gbc);

        // Panel Preview Gambar
        JPanel imagePanel = createImagePreviewPanel();
        gbc.gridx = 1;
        gbc.weightx = 0.3;
        gbc.weighty = 0.4; // Tambahkan bobot vertikal
        mainPanel.add(imagePanel, gbc);

        // Panel Tabel
        JPanel tablePanel = createTablePanel();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.6; // Berikan bobot vertikal lebih besar
        mainPanel.add(tablePanel, gbc);

        return mainPanel;
    }

    // Buat Panel Form Input
    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(SOFT_GRAY, 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        String[] labels = {
                "Costume ID:", "Costume Name:", "Category:",
                "Theme:", "Size:", "Status:", "Image:"
        };

        JComponent[] components = {
                idField = createStyledTextField(),
                nameField = createStyledTextField(),
                categoryBox = createStyledComboBox(new String[]{"Anime", "Film", "Game", "Cosplay", "Other"}),
                themeBox = createStyledComboBox(new String[]{"Action", "Adventure", "Fantasy", "Sci-Fi", "Horror"}),
                sizeField = createStyledTextField(),
                statusBox = createStyledComboBox(new String[]{"Available", "Borrowed", "Damaged"}),
                createImageBrowsePanel()
        };

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 0.3;
            JLabel label = new JLabel(labels[i]);
            label.setFont(HEADER_FONT);
            label.setForeground(DARK_BLUE);
            formPanel.add(label, gbc);

            gbc.gridx = 1;
            gbc.weightx = 0.7;
            formPanel.add(components[i], gbc);
        }

        return formPanel;
    }

    // Buat TextField Kustom
    private JTextField createStyledTextField() {
        JTextField textField = new JTextField(20);
        textField.setFont(REGULAR_FONT);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(SOFT_GRAY, 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        textField.setBackground(Color.WHITE);
        textField.setForeground(DARK_BLUE);

        return textField;
    }

    // Buat ComboBox Kustom
    private JComboBox<String> createStyledComboBox(String[] options) {
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setFont(REGULAR_FONT);
        comboBox.setBackground(Color.WHITE);
        comboBox.setForeground(DARK_BLUE);
        comboBox.setBorder(BorderFactory.createLineBorder(SOFT_GRAY, 1));

        return comboBox;
    }

    // Buat Panel Browse Gambar
    private JPanel createImageBrowsePanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBackground(Color.WHITE);

        imagePath = createStyledTextField();

        // Tombol Browse dengan desain modern
        browseButton = new JButton("Browse");
        browseButton.setFont(HEADER_FONT);
        browseButton.setBackground(LIGHT_BLUE);
        browseButton.setForeground(Color.WHITE);
        browseButton.setFocusPainted(false);
        browseButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Efek hover untuk tombol browse
        browseButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                browseButton.setBackground(DARK_BLUE);
                browseButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent evt) {
                browseButton.setBackground(LIGHT_BLUE);
                browseButton.setCursor(Cursor.getDefaultCursor());
            }
        });

        panel.add(imagePath, BorderLayout.CENTER);
        panel.add(browseButton, BorderLayout.EAST);


        return panel;
    }

    // Buat Panel Preview Gambar
    private JPanel createImagePreviewPanel() {
        JPanel imagePanel = new JPanel(new BorderLayout(10, 10));
        imagePanel.setBackground(Color.WHITE);
        imagePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(SOFT_GRAY, 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // Label preview gambar
        imageLabel = new JLabel("No Image Selected");
        imageLabel.setPreferredSize(new Dimension(200, 300));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        imageLabel.setBorder(BorderFactory.createLineBorder(SOFT_GRAY, 1));
        imageLabel.setFont(HEADER_FONT);
        imageLabel.setForeground(DARK_BLUE);

        // Panel tombol aksi
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.WHITE);

        // Tambahkan tombol Save
        saveButton = createActionButton("Save to Word", LIGHT_BLUE);
        buttonPanel.add(saveButton);

        // Buat tombol dengan desain modern
        addButton = createActionButton("Add", LIGHT_BLUE);

        buttonPanel.add(addButton);

        imagePanel.add(imageLabel, BorderLayout.CENTER);
        imagePanel.add(buttonPanel, BorderLayout.SOUTH);

        return imagePanel;
    }

    // Buat tombol aksi kustom
    private JButton createActionButton(String label, Color baseColor) {
        JButton button = new JButton(label);
        button.setFont(HEADER_FONT);
        button.setBackground(baseColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        // Efek hover
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(DARK_BLUE);
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent evt) {
                button.setBackground(baseColor);
                button.setCursor(Cursor.getDefaultCursor());
            }
        });

        return button;
    }

// Buat Panel Tabel
    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout(10, 10));
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(SOFT_GRAY, 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // Kolom tabel
        String[] columns = {"ID", "Name", "Category", "Theme", "Size", "Status", "Image Path"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        costumeTable = new JTable(tableModel);

        // Atur lebar kolom
        TableColumnModel columnModel = costumeTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);   // ID
        columnModel.getColumn(1).setPreferredWidth(150);  // Name
        columnModel.getColumn(2).setPreferredWidth(100);  // Category
        columnModel.getColumn(3).setPreferredWidth(100);  // Theme
        columnModel.getColumn(4).setPreferredWidth(50);   // Size
        columnModel.getColumn(5).setPreferredWidth(100);  // Status
        columnModel.getColumn(6).setPreferredWidth(200);  // Image Path

        // Buat renderer untuk rata tengah dan padding
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Rata tengah
                setHorizontalAlignment(JLabel.CENTER);

                // Atur padding
                if (c instanceof JLabel) {
                    ((JLabel) c).setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createEmptyBorder(0, 10, 0, 10),
                            ((JLabel) c).getBorder()
                    ));
                }

                return c;
            }
        };

        // Terapkan renderer ke semua kolom
        for (int i = 0; i < costumeTable.getColumnCount(); i++) {
            costumeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Styling tabel - Perbesar tinggi baris
        costumeTable.setRowHeight(50);  // Tinggi baris
        costumeTable.setFont(REGULAR_FONT);
        costumeTable.setSelectionBackground(LIGHT_BLUE);
        costumeTable.setSelectionForeground(Color.WHITE);

        // Header tabel
        JTableHeader header = costumeTable.getTableHeader();
        header.setBackground(DARK_BLUE);
        header.setForeground(Color.WHITE);
        header.setFont(HEADER_FONT);
        header.setReorderingAllowed(false);

        // Buat renderer khusus untuk header
        ((DefaultTableCellRenderer)header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        header.setPreferredSize(new Dimension(header.getWidth(), 50)); // Perbesar tinggi header

        // Scrollpane untuk tabel
        JScrollPane scrollPane = new JScrollPane(costumeTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(SOFT_GRAY, 1));

        tablePanel.add(scrollPane, BorderLayout.CENTER);

        return tablePanel;
    }

    // Method untuk browse gambar
    private void browseCostumeImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Costume Image");

        // Filter ekstensi gambar
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter(
                "Image files", "jpg", "jpeg", "png", "gif", "bmp"
        );
        fileChooser.setFileFilter(imageFilter);

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            // Validasi ukuran gambar (maks 5MB)
            if (selectedFile.length() > 5 * 1024 * 1024) {
                JOptionPane.showMessageDialog(
                        this,
                        "Image size exceeds 5MB limit",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            imagePath.setText(selectedFile.getAbsolutePath());
            displayImage(selectedFile.getAbsolutePath());
        }
    }

    // Method untuk menampilkan gambar
    // Method untuk menampilkan gambar
    private void displayImage(String path) {
        try {
            ImageIcon originalIcon = new ImageIcon(path);

            // Dapatkan dimensi label
            int labelWidth = imageLabel.getWidth();
            int labelHeight = imageLabel.getHeight();

            // Jika dimensi label 0, gunakan dimensi default
            if (labelWidth <= 0 || labelHeight <= 0) {
                labelWidth = 200;
                labelHeight = 300;
            }

            // Scale gambar dengan mempertahankan rasio aspek
            double imageAspect = (double) originalIcon.getIconWidth() / originalIcon.getIconHeight();
            double labelAspect = (double) labelWidth / labelHeight;

            int scaledWidth, scaledHeight;
            if (imageAspect > labelAspect) {
                // Gambar lebih lebar dari label
                scaledWidth = labelWidth;
                scaledHeight = (int) (scaledWidth / imageAspect);
            } else {
                // Gambar lebih tinggi dari label
                scaledHeight = labelHeight;
                scaledWidth = (int) (scaledHeight * imageAspect);
            }

            // Scale gambar dengan kualitas tinggi
            Image scaledImage = originalIcon.getImage().getScaledInstance(
                    scaledWidth,
                    scaledHeight,
                    Image.SCALE_SMOOTH
            );

            imageLabel.setIcon(new ImageIcon(scaledImage));
            imageLabel.setText(""); // Hapus teks default

            // Tambahkan border untuk memberi kejelasan
            imageLabel.setBorder(BorderFactory.createLineBorder(SOFT_GRAY, 1));
        } catch (Exception e) {
            imageLabel.setIcon(null);
            imageLabel.setText("Failed to load image");
            JOptionPane.showMessageDialog(
                    this,
                    "Error loading image: " + e.getMessage(),
                    "Image Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Method untuk mengatur Look and Feel
    private void setCustomLookAndFeel() {
        try {
            // Set Nimbus Look and Feel
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            try {
                // Fallback ke sistem look and feel
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    // Method untuk menambahkan event listeners
    private void addEventListeners() {
        browseButton.addActionListener(e -> browseCostumeImage());

        // Listener untuk tombol Add
        addButton.addActionListener(e -> {
            if (addButton.getText().equals("Add")) {
                addCostume();
            } else {
                editCostume();
            }
        });

        saveButton.addActionListener(e -> saveToDocx());
        if (saveButton == null) {
            System.out.println("Save to Word button is null");
        }



        // Listener untuk seleksi baris tabel
        costumeTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = costumeTable.getSelectedRow();
                if (selectedRow != -1) {
                    // Ubah tampilan dan fungsi tombol saat baris dipilih
                    addButton.setText("Edit");
                    addButton.setBackground(new Color(39, 174, 96));

                    // Tambahkan tombol Hapus di sebelah tombol Edit
                    JPanel buttonPanel = (JPanel) addButton.getParent();
                    buttonPanel.removeAll();
                    buttonPanel.add(addButton);

                    deleteButton = createActionButton("Delete", ACCENT_COLOR);
                    buttonPanel.add(deleteButton);

                    buttonPanel.add(saveButton); // Tambahkan Save to Word
                    buttonPanel.revalidate();
                    buttonPanel.repaint();

                    // Tambahkan listener untuk tombol Delete
                    deleteButton.addActionListener(deleteEvent -> deleteCostume());

                    buttonPanel.revalidate();
                    buttonPanel.repaint();

                    // Isi field dengan data yang dipilih
                    idField.setText(tableModel.getValueAt(selectedRow, 0).toString());
                    nameField.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    categoryBox.setSelectedItem(tableModel.getValueAt(selectedRow, 2));
                    themeBox.setSelectedItem(tableModel.getValueAt(selectedRow, 3));
                    sizeField.setText(tableModel.getValueAt(selectedRow, 4).toString());
                    statusBox.setSelectedItem(tableModel.getValueAt(selectedRow, 5));
                    imagePath.setText(tableModel.getValueAt(selectedRow, 6).toString());

                    // Tampilkan gambar
                    displayImage(tableModel.getValueAt(selectedRow, 6).toString());
                }
            }
        });
    }

    // Method untuk menambahkan kostum
    private void addCostume() {
        if (validateInput()) {
            try {
                String[] rowData = {
                        idField.getText(),
                        nameField.getText(),
                        categoryBox.getSelectedItem().toString(),
                        themeBox.getSelectedItem().toString(),
                        sizeField.getText(),
                        statusBox.getSelectedItem().toString(),
                        imagePath.getText()
                };

                tableModel.addRow(rowData);
                clearFields();
                showSuccessMessage("Costume added successfully");
            } catch (Exception e) {
                showErrorMessage("Failed to add costume: " + e.getMessage());
            }
        }
    }

    // Method untuk mengedit kostum
    private void editCostume() {
        int selectedRow = costumeTable.getSelectedRow();
        if (selectedRow != -1 && validateInput()) {
            try {
                tableModel.setValueAt(idField.getText(), selectedRow, 0);
                tableModel.setValueAt(nameField.getText(), selectedRow, 1);
                tableModel.setValueAt(categoryBox.getSelectedItem(), selectedRow, 2);
                tableModel.setValueAt(themeBox.getSelectedItem(), selectedRow, 3);
                tableModel.setValueAt(sizeField.getText(), selectedRow, 4);
                tableModel.setValueAt(statusBox.getSelectedItem(), selectedRow, 5);
                tableModel.setValueAt(imagePath.getText(), selectedRow, 6);

                clearFields();
                showSuccessMessage("Costume updated successfully");
            } catch (Exception e) {
                showErrorMessage("Failed to update costume: " + e.getMessage());
            }
        } else {
            showErrorMessage("Please select a costume to edit");
        }
    }

    // Method untuk menghapus kostum
    private void deleteCostume() {
        int selectedRow = costumeTable.getSelectedRow();
        if (selectedRow != -1) {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this costume?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    tableModel.removeRow(selectedRow);
                    clearFields();
                    showSuccessMessage("Costume deleted successfully");
                } catch (Exception e) {
                    showErrorMessage("Failed to delete costume: " + e.getMessage());
                }
            }
        } else {
            showErrorMessage("Please select a costume to delete");
        }
    }

    private void saveToDocx() {
        try (XWPFDocument document = new XWPFDocument()) {
            XWPFTable table = document.createTable();

            // Tambahkan header ke tabel
            XWPFTableRow headerRow = table.getRow(0); // Baris pertama sudah ada secara default
            for (int i = 0; i < tableModel.getColumnCount(); i++) {
                if (i == 0) {
                    headerRow.getCell(0).setText(tableModel.getColumnName(i));
                } else {
                    headerRow.addNewTableCell().setText(tableModel.getColumnName(i));
                }
            }

            // Tambahkan data ke tabel
            for (int row = 0; row < tableModel.getRowCount(); row++) {
                XWPFTableRow dataRow = table.createRow();
                for (int col = 0; col < tableModel.getColumnCount(); col++) {
                    Object value = tableModel.getValueAt(row, col);
                    dataRow.getCell(col).setText(value != null ? value.toString() : "");
                }
            }

            // Atur style tabel
            table.setWidth("100%");

            // Tentukan lokasi penyimpanan berdasarkan direktori kerja aplikasi
            String currentDir = System.getProperty("user.dir");
            String filePath = currentDir + File.separator + "CostumeData.docx";

            // Simpan dokumen
            try (FileOutputStream out = new FileOutputStream(filePath)) {
                document.write(out);
            }

            showSuccessMessage("Data saved successfully to: " + filePath);
        } catch (IOException ex) {
            showErrorMessage("Failed to save data: " + ex.getMessage());
        }
    }



    // Method untuk membersihkan fields
    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        sizeField.setText("");
        imagePath.setText("");
        categoryBox.setSelectedIndex(0);
        themeBox.setSelectedIndex(0);
        statusBox.setSelectedIndex(0);
        imageLabel.setIcon(null);
        imageLabel.setText("No Image Selected");

        // Kembalikan tombol ke mode Add
        JPanel buttonPanel = (JPanel) addButton.getParent();
        buttonPanel.removeAll();
        addButton.setText("Add");
        addButton.setBackground(LIGHT_BLUE);
        buttonPanel.add(addButton);
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    // Method validasi input
    private boolean validateInput() {
        StringBuilder errorMessage = new StringBuilder();

        if (idField.getText().trim().isEmpty()) {
            errorMessage.append("- Costume ID is required\n");
        }

        if (nameField.getText().trim().isEmpty()) {
            errorMessage.append("- Costume Name is required\n");
        }

        if (sizeField.getText().trim().isEmpty()) {
            errorMessage.append("- Size is required\n");
        }

        if (imagePath.getText().trim().isEmpty()) {
            errorMessage.append("- Image is required\n");
        }

        if (errorMessage.length() > 0) {
            showErrorMessage(errorMessage.toString());
            return false;
        }

        return true;
    }

    // Method untuk menampilkan pesan sukses
    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // Method untuk menampilkan pesan error
    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    // Method main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CostumeInventorySystem frame = new CostumeInventorySystem();
            frame.setVisible(true);
        });
    }
}