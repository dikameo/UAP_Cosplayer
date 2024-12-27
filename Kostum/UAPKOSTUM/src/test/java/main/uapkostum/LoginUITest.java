package main.uapkostum;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class LoginUITest {
    private LoginUI loginUI;

    @Before
    public void setUp() {
        // Inisialisasi LoginUI sebelum setiap test
        loginUI = new LoginUI();
        loginUI.setVisible(true); // Menampilkan UI untuk pengujian
    }

    @Test
    public void testLoginSuccess() {
        // Simulasi input username dan password yang benar
        loginUI.usernameField.setText("admin");
        loginUI.passwordField.setText("password");

        // Simulasi klik tombol login
        loginUI.loginButton.doClick();

        // Verifikasi bahwa dialog berhasil muncul
        // (Anda bisa menggunakan mocking untuk memverifikasi dialog)
        // Di sini kita hanya memeriksa bahwa tidak ada exception yang dilempar
    }

    @Test
    public void testLoginFailure() {
        // Simulasi input username dan password yang salah
        loginUI.usernameField.setText("wrongUser ");
        loginUI.passwordField.setText("wrongPassword");

        // Simulasi klik tombol login
        loginUI.loginButton.doClick();

        // Verifikasi bahwa dialog error muncul
        // (Anda bisa menggunakan mocking untuk memverifikasi dialog)
        // Di sini kita hanya memeriksa bahwa tidak ada exception yang dilempar
    }

    @Test
    public void testPlaceholderBehavior() {
        // Memastikan placeholder berfungsi dengan baik
        loginUI.usernameField.setText("Username");
        loginUI.usernameField.requestFocus();
        assertEquals("", loginUI.usernameField.getText()); // Placeholder harus hilang

        loginUI.usernameField.setText("");
        loginUI.usernameField.transferFocus(); // Simulasi kehilangan fokus
        assertEquals("Username", loginUI.usernameField.getText()); // Placeholder harus muncul kembali
    }

    @Test
    public void testCloseButton() {
        // Simulasi klik tombol close
        loginUI.closeButton.doClick();

        // Verifikasi bahwa aplikasi ditutup
        assertFalse(loginUI.isDisplayable());
    }
}