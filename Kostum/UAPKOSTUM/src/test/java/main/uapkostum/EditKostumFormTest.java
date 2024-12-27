package main.uapkostum;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class EditKostumFormTest {
    private EditKostumForm editKostumForm;

    @Before
    public void setUp() {
        // Inisialisasi EditKostumForm sebelum setiap test
        editKostumForm = new EditKostumForm("K001", "Kostum Halloween", "Kostum 1", "Tema 1", "M", "Tersedia", "C:/path/gambar1.png");
        editKostumForm.setVisible(true); // Menampilkan UI untuk pengujian
    }

    @Test
    public void testEditKostumFormComponents() {
        // Memastikan bahwa komponen utama ada
        assertNotNull(editKostumForm.getContentPane().getComponent(0)); // Header Label
        assertTrue(editKostumForm.getContentPane().getComponent(0) instanceof JLabel);

        assertNotNull(editKostumForm.getContentPane().getComponent(1)); // ID Kostum Label
        assertTrue(editKostumForm.getContentPane().getComponent(1) instanceof JLabel);

        assertNotNull(editKostumForm.getContentPane().getComponent(2)); // ID Kostum TextField
        assertTrue(editKostumForm.getContentPane().getComponent(2) instanceof JTextField);

        assertNotNull(editKostumForm.getContentPane().getComponent(3)); // Nama Kostum Label
        assertTrue(editKostumForm.getContentPane().getComponent(3) instanceof JLabel);

        assertNotNull(editKostumForm.getContentPane().getComponent(4)); // Nama Kostum TextField
        assertTrue(editKostumForm.getContentPane().getComponent(4) instanceof JTextField);

        assertNotNull(editKostumForm.getContentPane().getComponent(5)); // Kategori ComboBox
        assertTrue(editKostumForm.getContentPane().getComponent(5) instanceof JComboBox);

        assertNotNull(editKostumForm.getContentPane().getComponent(6)); // Tema ComboBox
        assertTrue(editKostumForm.getContentPane().getComponent(6) instanceof JComboBox);

        assertNotNull(editKostumForm.getContentPane().getComponent(7)); // Ukuran TextField
        assertTrue(editKostumForm.getContentPane().getComponent(7) instanceof JTextField);

        assertNotNull(editKostumForm.getContentPane().getComponent(8)); // Status ComboBox
        assertTrue(editKostumForm.getContentPane().getComponent(8) instanceof JComboBox);

        assertNotNull(editKostumForm.getContentPane().getComponent(9)); // Gambar Label
        assertTrue(editKostumForm.getContentPane().getComponent(9) instanceof JLabel);

        assertNotNull(editKostumForm.getContentPane().getComponent(10)); // Gambar Display
        assertTrue(editKostumForm.getContentPane().getComponent(10) instanceof JLabel);

        assertNotNull(editKostumForm.getContentPane().getComponent(11)); // Edit Button
        assertTrue(editKostumForm.getContentPane().getComponent(11) instanceof JButton);

        assertNotNull(editKostumForm.getContentPane().getComponent(12)); // Hapus Button
        assertTrue(editKostumForm.getContentPane().getComponent(12) instanceof JButton);
    }

    @Test
    public void testEditButtonAction() {
        // Simulasi klik tombol Edit
        JButton editButton = (JButton) editKostumForm.getContentPane().getComponent(11);
        editButton.doClick();

        // Verifikasi bahwa dialog muncul
        // (Anda bisa menggunakan mocking untuk memverifikasi dialog)
        // Di sini kita hanya memeriksa bahwa tidak ada exception yang dilempar
    }

    @Test
    public void testDeleteButtonAction() {
        // Simulasi klik tombol Hapus
        JButton deleteButton = (JButton) editKostumForm.getContentPane().getComponent(12);
        deleteButton.doClick();

        // Verifikasi bahwa dialog muncul
        // (Anda bisa menggunakan mocking untuk memverifikasi dialog)
    }

    @Test
    public void testInitialFieldValues() {
        // Memastikan nilai awal dari field sesuai dengan yang diharapkan
        assertEquals("K001", editKostumForm.idKostumField.getText());
        assertEquals("Kostum Halloween", editKostumForm.namaKostumField.getText());
        assertEquals("Kostum 1", editKostumForm.kategoriComboBox.getSelectedItem());
        assertEquals("Tema 1", editKostumForm.temaComboBox.getSelectedItem());
        assertEquals("M", editKostumForm.ukuranField.getText());
        assertEquals("Tersedia", editKostumForm.statusComboBox.getSelectedItem());
    }

    @Test
    public void testGambarLabel() {
        // Memastikan gambar label diisi dengan benar
        assertNotNull(editKostumForm.gambarLabel.getIcon());
        assertEquals("C:/path/gambar1.png", ((ImageIcon) editKostumForm.gambarLabel.getIcon()).getDescription());
    }
}