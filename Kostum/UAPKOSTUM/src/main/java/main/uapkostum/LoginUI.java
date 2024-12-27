package main.uapkostum;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

public class LoginUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginUI() {
        // Pengaturan Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Konfigurasi Frame
        setTitle("Modern Login");
        setSize(400, 500);
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

        // Logo atau Judul
        JLabel titleLabel = new JLabel("LOGIN");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(150, 50, 200, 50);
        mainPanel.add(titleLabel);

        // Username Field
        usernameField = createRoundedTextField("Username");
        usernameField.setBounds(50, 150, 300, 45);
        mainPanel.add(usernameField);

        // Password Field
        passwordField = createRoundedPasswordField("Password");
        passwordField.setBounds(50, 220, 300, 45);
        mainPanel.add(passwordField);

        // Login Button
        loginButton = createRoundedButton("Login");
        loginButton.setBounds(50, 300, 300, 45);
        loginButton.setForeground(new Color(0, 0, 139)); // Warna biru tua
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginAction();
            }
        });
        mainPanel.add(loginButton);


        // Close Button
        JButton closeButton = new JButton("X");
        closeButton.setBounds(360, 10, 30, 30);
        closeButton.setBackground(Color.RED);
        closeButton.setForeground(Color.BLACK);
        closeButton.addActionListener(e -> System.exit(0));
        mainPanel.add(closeButton);

        // Tambahkan efek dragging
        addDragListener(mainPanel);

        // Set Shape Rounded
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));

        add(mainPanel);
    }

    // Membuat TextField Rounded
    private JTextField createRoundedTextField(String placeholder) {
        JTextField textField = new JTextField(placeholder) {
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

        // Placeholder effect
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                }
            }
        });

        return textField;
    }

    // Membuat Password Field Rounded
    private JPasswordField createRoundedPasswordField(String placeholder) {
        JPasswordField passwordField = new JPasswordField(placeholder) {
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
        passwordField.setOpaque(false);
        passwordField.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        passwordField.setForeground(Color.WHITE);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setEchoChar((char) 0);

        // Placeholder effect
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals(placeholder)) {
                    passwordField.setText("");
                    passwordField.setEchoChar('*');
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText(placeholder);
                    passwordField.setEchoChar((char) 0);
                }
            }
        });

        return passwordField;
    }

    // Membuat Tombol Rounded dengan Background Biru Gelap
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

    // Membuat Hyperlink Button
    private JButton createHyperlinkButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setForeground(Color.LIGHT_GRAY);
            }
        };
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setFont(new Font("Arial", Font.PLAIN, 12));
        button.addActionListener(e -> {
            // Aksi untuk tombol register
            JOptionPane.showMessageDialog(this, "Halaman pendaftaran belum tersedia.");
        });
        return button;
    }

    // Aksi untuk tombol login
    private void loginAction() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Logika autentikasi (contoh sederhana)
        if (username.equals("admin") && password.equals("password")) {
            JOptionPane.showMessageDialog(this, "Login berhasil!");
            // Arahkan ke halaman berikutnya
        } else {
            JOptionPane.showMessageDialog(this, "Username atau password salah!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Menambahkan efek dragging pada frame
    private void addDragListener(JPanel panel) {
        panel.addMouseListener(new MouseAdapter() {
            Point mousePoint;

            @Override
            public void mousePressed(MouseEvent e) {
                mousePoint = e.getPoint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen() - mousePoint.x;
                int y = e.getYOnScreen() - mousePoint.y;
                setLocation(x, y);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginUI loginUI = new LoginUI();
            loginUI.setVisible(true);
        });
    }
}