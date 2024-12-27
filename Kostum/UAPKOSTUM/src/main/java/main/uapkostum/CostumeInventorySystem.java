

package main.uapkostum;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CostumeInventorySystem extends JFrame {
    private JTextField idField, nameField, sizeField, imagePath;
    private JComboBox<String> categoryBox, themeBox, statusBox;
    private JTable costumeTable;
    private DefaultTableModel tableModel;
    private JButton addButton, editButton, deleteButton, browseButton, listButton;
    private JLabel imageLabel;

    public CostumeInventorySystem() {
        setTitle("Sistem Manajemen Inventaris Kostum");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Initialize components
        inputPanel.add(new JLabel("ID Kostum:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Nama Kostum:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Kategori:"));
        categoryBox = new JComboBox<>(new String[]{"Anime", "Film", "Game", "Cosplay", "Lainnya"});
        inputPanel.add(categoryBox);

        inputPanel.add(new JLabel("Tema:"));
        themeBox = new JComboBox<>(new String[]{"Action", "Adventure", "Fantasy", "Sci-Fi", "Horror"});
        inputPanel.add(themeBox);

        inputPanel.add(new JLabel("Ukuran:"));
        sizeField = new JTextField();
        inputPanel.add(sizeField);

        inputPanel.add(new JLabel("Status:"));
        statusBox = new JComboBox<>(new String[]{"Tersedia", "Dipinjam", "Rusak"});
        inputPanel.add(statusBox);

        inputPanel.add(new JLabel("Gambar:"));
        JPanel imagePanel = new JPanel(new BorderLayout(5, 0));
        imagePath = new JTextField();
        browseButton = new JButton("Browse");
        imagePanel.add(imagePath, BorderLayout.CENTER);
        imagePanel.add(browseButton, BorderLayout.EAST);
        inputPanel.add(imagePanel);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        addButton = new JButton("Tambah");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Hapus");
        listButton = new JButton("List Kostum");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(listButton);

        // Table
        String[] columns = {"ID", "Nama", "Kategori", "Tema", "Ukuran", "Status", "Path Gambar"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        costumeTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(costumeTable);

        // Image preview panel
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(200, 200));
        imageLabel.setBorder(BorderFactory.createTitledBorder("Preview Kostum"));

        // Layout
        JPanel westPanel = new JPanel(new BorderLayout());
        westPanel.add(inputPanel, BorderLayout.CENTER);
        westPanel.add(buttonPanel, BorderLayout.SOUTH);

        JPanel eastPanel = new JPanel(new BorderLayout());
        eastPanel.add(imageLabel, BorderLayout.CENTER);

        add(westPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
        add(eastPanel, BorderLayout.EAST);

        // Add event listeners
        addButton.addActionListener(e -> addCostume());
        editButton.addActionListener(e -> editCostume());
        deleteButton.addActionListener(e -> deleteCostume());
        browseButton.addActionListener(e -> browseCostumeImage());
        listButton.addActionListener(e -> listCostumes());

        costumeTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = costumeTable.getSelectedRow();
                if (selectedRow != -1) {
                    displayCostumeData(selectedRow);
                }
            }
        });
    }

    private void addCostume() {
        if (validateInput()) {
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
            JOptionPane.showMessageDialog(this, "Data kostum berhasil ditambahkan!");
        }
    }

    private void editCostume() {
        int selectedRow = costumeTable.getSelectedRow();
        if (selectedRow != -1 && validateInput()) {
            tableModel.setValueAt(idField.getText(), selectedRow, 0);
            tableModel.setValueAt(nameField.getText(), selectedRow, 1);
            tableModel.setValueAt(categoryBox.getSelectedItem(), selectedRow, 2);
            tableModel.setValueAt(themeBox.getSelectedItem(), selectedRow, 3);
            tableModel.setValueAt(sizeField.getText(), selectedRow, 4);
            tableModel.setValueAt(statusBox.getSelectedItem(), selectedRow, 5);
            tableModel.setValueAt(imagePath.getText(), selectedRow, 6);
            clearFields();
            JOptionPane.showMessageDialog(this, "Data kostum berhasil diupdate!");
        } else if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih kostum yang akan diedit!");
        }
    }

    private void deleteCostume() {
        int selectedRow = costumeTable.getSelectedRow();
        if (selectedRow != -1) {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Apakah Anda yakin ingin menghapus kostum ini?",
                    "Konfirmasi Hapus",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                tableModel.removeRow(selectedRow);
                clearFields();
                JOptionPane.showMessageDialog(this, "Data kostum berhasil dihapus!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih kostum yang akan dihapus!");
        }
    }

    private void browseCostumeImage() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Image files", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            imagePath.setText(selectedFile.getAbsolutePath());
            displayImage(selectedFile.getAbsolutePath());
        }
    }

    private void listCostumes() {
        String selectedCategory = categoryBox.getSelectedItem().toString();
        String selectedTheme = themeBox.getSelectedItem().toString();
        String selectedStatus = statusBox.getSelectedItem().toString();

        for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
            String category = tableModel.getValueAt(i, 2).toString();
            String theme = tableModel.getValueAt(i, 3).toString();
            String status = tableModel.getValueAt(i, 5).toString();

            boolean match = category.equals(selectedCategory) &&
                    theme.equals(selectedTheme) &&
                    status.equals(selectedStatus);

            if (!match) {
                tableModel.removeRow(i);
            }
        }
    }

    private void displayCostumeData(int row) {
        idField.setText(tableModel.getValueAt(row, 0).toString());
        nameField.setText(tableModel.getValueAt(row, 1).toString());
        categoryBox.setSelectedItem(tableModel.getValueAt(row, 2));
        themeBox.setSelectedItem(tableModel.getValueAt(row, 3));
        sizeField.setText(tableModel.getValueAt(row, 4).toString());
        statusBox.setSelectedItem(tableModel.getValueAt(row, 5));
        imagePath.setText(tableModel.getValueAt(row, 6).toString());
        displayImage(tableModel.getValueAt(row, 6).toString());
    }

    private void displayImage(String path) {
        ImageIcon imageIcon = new ImageIcon(path);
        Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(image));
    }

    private boolean validateInput() {
        if (idField.getText().trim().isEmpty() ||
                nameField.getText().trim().isEmpty() ||
                sizeField.getText().trim().isEmpty() ||
                imagePath.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Semua field harus diisi!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        sizeField.setText("");
        imagePath.setText("");
        categoryBox.setSelectedIndex(0);
        themeBox.setSelectedIndex(0);
        statusBox.setSelectedIndex(0);
        imageLabel.setIcon(null);
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new CostumeInventorySystem().setVisible(true);
        });
    }
}