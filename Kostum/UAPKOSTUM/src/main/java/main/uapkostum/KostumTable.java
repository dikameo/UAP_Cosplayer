package main.uapkostum;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.text.SimpleDateFormat;
import java.util.Date;

public class KostumTable extends JFrame {
    private JTable kostumTable;
    private DefaultTableModel tableModel;

    public KostumTable() {
        // Pengaturan Frame
        setTitle("Tabel Kostum");
        setSize(1200, 600); // Mengubah ukuran jendela menjadi 1200
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
        JLabel headerLabel = new JLabel("Tabel Kostum", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBounds(0, 20, 1200, 50);
        mainPanel.add(headerLabel);

        // Tabel
        String[] columnNames = {"ID Kostum", "Nama Kostum", "Kategori", "Tema", "Ukuran", "Status", "Gambar", "Path Gambar", "Waktu", "Aksi"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tidak bisa diedit
            }
        };
        kostumTable = new JTable(tableModel);
        kostumTable.setBackground(new Color(240, 240, 240));
        kostumTable.setForeground(Color.BLACK);
        kostumTable.setFont(new Font("Arial", Font.PLAIN, 14));
        kostumTable.setRowHeight(25);

        // Styling tabel
//        kostumTable.getColumnModel().getColumn(9).setCellRenderer(new ButtonRenderer());
        kostumTable.getColumnModel().getColumn(9).setCellEditor(new ButtonEditor(new JCheckBox()));

        // Scroll Pane untuk tabel
        JScrollPane scrollPane = new JScrollPane(kostumTable);
        scrollPane.setBounds(50, 80, 1100, 400); // Memperlebar tabel
        mainPanel.add(scrollPane);

        // Tombol Tambah Data
        JButton addButton = createRoundedButton("Tambah Data");
        addButton.setBounds(50, 500, 130, 45);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aksi untuk menambah data
                KostumForm kostumForm = new KostumForm();
                kostumForm.setVisible(true);
                dispose(); // Menutup tabel
            }
        });
        mainPanel.add(addButton);

        // Tombol Kembali
        JButton backButton = createRoundedButton("Kembali");
        backButton.setBounds(220, 500, 130, 45);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kembali ke halaman utama
//                MainPage.setVisible(true);
                dispose(); // Menutup tabel
            }
        });
        mainPanel.add(backButton);

        // Menambahkan beberapa data contoh
        addSampleData();

        // Set Shape Rounded
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));

        add(mainPanel);
    }

    // Menambahkan data contoh ke tabel
    private void addSampleData() {
        String[] data1 = {"K001", "Kostum Halloween", "Kostum 1", "Tema 1", "M", "Tersedia", "gambar1.png", "C:/path/gambar1.png", getCurrentTime(), "Cek | Edit | Hapus"};
        String[] data2 = {"K002", "Kostum Natal", "Kostum 2", "Tema 2", "L", "Tidak Tersedia", "gambar2.png", "C:/path/gambar2.png", getCurrentTime(), "Cek | Edit | Hapus"};
        String[] data3 = {"K003", "Kostum Pahlawan", "Kostum 3", "Tema 3", "S", "Tersedia", "gambar3.png", "C:/path/gambar3.png", getCurrentTime(), "Cek | Edit | Hapus"};

        tableModel.addRow(data1);
        tableModel.addRow(data2);
        tableModel.addRow(data3);
    }

    // Mendapatkan waktu saat ini dalam format yang diinginkan
    private String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return formatter.format(new Date());
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

    // Kelas untuk tombol dalam tabel
//    class ButtonRenderer extends JButton implements TableCellRenderer {
//        public ButtonRenderer() {
//            setOpaque(true);
//        }
//
//        @Override
//        public Component getTableCellRendererComponent(JTable table, Object value,
//                                                       boolean isSelected, boolean hasFocus, int row) {
//            setText(value.toString());
//            return this;
//        }
//    }

    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    int row = kostumTable.getSelectedRow();
                    if (label.equals("Cek")) {
                        // Tampilkan detail kostum
                        JOptionPane.showMessageDialog(null, "Detail Kostum: " + tableModel.getValueAt(row, 1));
                    } else if (label.equals("Edit")) {
                        // Aksi untuk mengedit data
                        JOptionPane.showMessageDialog(null, "Edit Kostum: " + tableModel.getValueAt(row, 1));
                    } else if (label.equals("Hapus")) {
                        // Aksi untuk menghapus data
                        tableModel.removeRow(row);
                    }
                }
            });
        }

//        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row) {
            label = value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                // Kembali ke nilai default
            }
            isPushed = false;
            return label;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KostumTable kostumTable = new KostumTable();
            kostumTable.setVisible(true);
        });
    }
}
