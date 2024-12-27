package main.uapkostum;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class MainPageTest {
    private MainPage mainPage;

    @Before
    public void setUp() {
        // Inisialisasi MainPage sebelum setiap test
        mainPage = new MainPage();
        mainPage.setVisible(true); // Menampilkan UI untuk pengujian
    }

    @Test
    public void testMainPageComponents() {
        // Memastikan bahwa komponen utama ada
        assertNotNull(mainPage.getContentPane().getComponent(0)); // Header Label
        assertTrue(mainPage.getContentPane().getComponent(0) instanceof JLabel);

        assertNotNull(mainPage.getContentPane().getComponent(1)); // Tombol Tambahkan Data
        assertTrue(mainPage.getContentPane().getComponent(1) instanceof JButton);

        assertNotNull(mainPage.getContentPane().getComponent(2)); // Tombol Tampilkan Data
        assertTrue(mainPage.getContentPane().getComponent(2) instanceof JButton);

        assertNotNull(mainPage.getContentPane().getComponent(3)); // Tombol Profile
        assertTrue(mainPage.getContentPane().getComponent(3) instanceof JButton);

        assertNotNull(mainPage.getContentPane().getComponent(4)); // Tombol Keluar
        assertTrue(mainPage.getContentPane().getComponent(4) instanceof JButton);
    }

    @Test
    public void testAddDataButtonAction() {
        // Simulasi klik tombol Tambahkan Data
        JButton addDataButton = (JButton) mainPage.getContentPane().getComponent(1);
        addDataButton.doClick();

        // Verifikasi bahwa dialog muncul
        // (Anda bisa menggunakan mocking untuk memverifikasi dialog)
        // Di sini kita hanya memeriksa bahwa tidak ada exception yang dilempar
    }

    @Test
    public void testDisplayDataButtonAction() {
        // Simulasi klik tombol Tampilkan Data
        JButton displayDataButton = (JButton) mainPage.getContentPane().getComponent(2);
        displayDataButton.doClick();

        // Verifikasi bahwa dialog muncul
        // (Anda bisa menggunakan mocking untuk memverifikasi dialog)
    }

    @Test
    public void testProfileButtonAction() {
        // Simulasi klik tombol Profile
        JButton profileButton = (JButton) mainPage.getContentPane().getComponent(3);
        profileButton.doClick();

        // Verifikasi bahwa dialog muncul
        // (Anda bisa menggunakan mocking untuk memverifikasi dialog)
    }

    @Test
    public void testExitButtonAction() {
        // Simulasi klik tombol Keluar
        JButton exitButton = (JButton) mainPage.getContentPane().getComponent(4);
        exitButton.doClick();

        // Verifikasi bahwa aplikasi ditutup
        assertFalse(mainPage.isDisplayable());
    }
}