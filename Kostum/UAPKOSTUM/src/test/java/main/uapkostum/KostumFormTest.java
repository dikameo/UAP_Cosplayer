package main.uapkostum;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class KostumFormTest {
    private KostumForm kostumForm;

    @Before
    public void setUp() {
        // Inisialisasi KostumForm sebelum setiap test
        kostumForm = new KostumForm();
        kostumForm.setVisible(true); // Menampilkan UI untuk pengujian
    }

    @Test
    public void testKostumFormComponents() {
        // Memastikan bahwa komponen utama ada
        assertNotNull(kostumForm.getContentPane().getComponent(0)); // Header Label
        assertTrue(kostumForm.getContentPane().getComponent(0) instanceof JLabel);

        assertNotNull(kostumForm.getContentPane().getComponent(1)); // ID Kostum Label
        assertTrue(kostumForm.getContentPane().getComponent(1) instanceof JLabel);

        assertNotNull(kostumForm.getContentPane().getComponent(2)); // ID Kostum TextField
        assertTrue(kostumForm.getContentPane().getComponent(2) instanceof JTextField);

        assertNotNull(kostumForm.getContentPane().getComponent(3)); // Nama Kostum Label
        assertTrue(kostumForm.getContentPane().getComponent(3) instanceof JLabel);

        assertNotNull(kostumForm.getContentPane().getComponent(4)); // Nama Kostum TextField
        assertTrue(kostumForm.getContentPane().getComponent(4) instanceof JTextField);

        assertNotNull(kostumForm.getContentPane().getComponent(5)); // Kategori ComboBox
        assertTrue(kostumForm.getContentPane().getComponent(5) instanceof JComboBox);

        assertNotNull(kostumForm.getContentPane().getComponent(6)); // Tema ComboBox
        assertTrue(kostumForm.getContentPane().getComponent(6) instanceof JComboBox);

        assertNotNull(kostumForm.getContentPane().getComponent(7)); // Ukuran TextField
        assertTrue(kostumForm.getContentPane().getComponent(7) instanceof JTextField);

        assertNotNull(kostumForm.getContentPane().getComponent(8)); // Status ComboBox
        assertTrue(kostumForm.getContentPane().getComponent(8) instanceof JComboBox);

        assertNotNull(kostumForm.getContentPane().getComponent(9)); // Path Gambar TextField
        assertTrue(kostumForm.getContentPane().getComponent(9) instanceof JTextField);

        assertNotNull(kostumForm.getContentPane().getComponent(10)); // Simpan Button
        assertTrue(kostumForm.getContentPane().getComponent(10) instanceof JButton);

        assertNotNull(kostumForm.getContentPane().getComponent(11)); // Kembali Button
        assertTrue(kostumForm.getContentPane().getComponent(11) instanceof JButton);
    }

    @Test
    public void testSaveButtonAction() {
        // Simulasi klik tombol Simpan
        JButton saveButton = (JButton) kostumForm.getContentPane().getComponent(10);
        saveButton.doClick();

        // Verifikasi bahwa dialog muncul
        // (Anda bisa menggunakan mocking untuk memverifikasi dialog)
        // Di sini kita hanya memeriksa bahwa tidak ada exception yang dilempar
    }

    @Test
    public void testBackButtonAction() {
        // Simulasi klik tombol Kembali
        JButton backButton = (JButton) kostumForm.getContentPane().getComponent(11);
        backButton.doClick();

        // Verifikasi bahwa form ditutup
        assertFalse(kostumForm.isDisplayable());
    }

    @Test
    public void testBrowseButtonAction() {
        // Simulasi klik tombol Browse
        JButton browseButton = (JButton) kostumForm.getContentPane().getComponent(9);
        browseButton.doClick();

        // Verifikasi bahwa JFileChooser muncul
        // (Anda bisa menggunakan mocking untuk memverifikasi dialog)
    }
}