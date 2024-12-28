package main.uapkostum;

import org.junit.Test;
import static org.junit.Assert.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CostumeInventorySystemTest {

    // Metode reflection untuk mengakses field private
    private Object getPrivateField(Object object, String fieldName) throws Exception {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }

    // Metode reflection untuk mengatur nilai field private
    private void setPrivateField(Object object, String fieldName, Object value) throws Exception {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(object, value);
    }

    @Test
    public void testAddCostumeValidInput() throws Exception {
        // Buat instance CostumeInventorySystem
        CostumeInventorySystem inventorySystem = new CostumeInventorySystem();

        // Akses komponen menggunakan reflection
        JTextField idField = (JTextField) getPrivateField(inventorySystem, "idField");
        JTextField nameField = (JTextField) getPrivateField(inventorySystem, "nameField");
        JTextField sizeField = (JTextField) getPrivateField(inventorySystem, "sizeField");
        JTextField imagePath = (JTextField) getPrivateField(inventorySystem, "imagePath");
        JComboBox<String> categoryBox = (JComboBox<String>) getPrivateField(inventorySystem, "categoryBox");
        JComboBox<String> themeBox = (JComboBox<String>) getPrivateField(inventorySystem, "themeBox");
        JComboBox<String> statusBox = (JComboBox<String>) getPrivateField(inventorySystem, "statusBox");
        JTable costumeTable = (JTable) getPrivateField(inventorySystem, "costumeTable");

        // Simpan jumlah baris awal
        int initialRowCount = costumeTable.getRowCount();

        // Isi field-field input menggunakan reflection
        idField.setText("001");
        nameField.setText("Spider-Man Costume");
        categoryBox.setSelectedItem("Anime");
        themeBox.setSelectedItem("Superhero");
        sizeField.setText("M");
        statusBox.setSelectedItem("Available");
        imagePath.setText("E:\\image 7.png");

        // Panggil method addCostume menggunakan reflection
        java.lang.reflect.Method addCostumeMethod = CostumeInventorySystem.class.getDeclaredMethod("addCostume");
        addCostumeMethod.setAccessible(true);
        addCostumeMethod.invoke(inventorySystem);

        // Ambil kembali tabel setelah operasi
        costumeTable = (JTable) getPrivateField(inventorySystem, "costumeTable");

        // Cek apakah jumlah baris bertambah
        assertEquals(initialRowCount + 1, costumeTable.getRowCount());

        // Cek data terakhir
        int lastRowIndex = costumeTable.getRowCount() - 1;
        assertEquals("001", costumeTable.getValueAt(lastRowIndex, 0));
        assertEquals("Spider-Man Costume", costumeTable.getValueAt(lastRowIndex, 1));
    }

    @Test
    public void testAddCostumeInvalidInput() throws Exception {
        // Buat instance CostumeInventorySystem
        CostumeInventorySystem inventorySystem = new CostumeInventorySystem();

        // Akses komponen menggunakan reflection
        JTextField idField = (JTextField) getPrivateField(inventorySystem, "idField");
        JTextField nameField = (JTextField) getPrivateField(inventorySystem, "nameField");
        JTextField sizeField = (JTextField) getPrivateField(inventorySystem, "sizeField");
        JTextField imagePath = (JTextField) getPrivateField(inventorySystem, "imagePath");
        JTable costumeTable = (JTable) getPrivateField(inventorySystem, "costumeTable");

        // Simpan jumlah baris awal
        int initialRowCount = costumeTable.getRowCount();

        // Kosongkan semua field
        idField.setText("");
        nameField.setText("");
        sizeField.setText("");
        imagePath.setText("");

        // Panggil method addCostume menggunakan reflection
        java.lang.reflect.Method addCostumeMethod = CostumeInventorySystem.class.getDeclaredMethod("addCostume");
        addCostumeMethod.setAccessible(true);
        addCostumeMethod.invoke(inventorySystem);

        // Ambil kembali tabel setelah operasi
        costumeTable = (JTable) getPrivateField(inventorySystem, "costumeTable");

        // Pastikan tidak ada baris baru yang ditambahkan
        assertEquals(initialRowCount, costumeTable.getRowCount());
    }

    @Test
    public void testEditCostume() throws Exception {
        // Buat instance CostumeInventorySystem
        CostumeInventorySystem inventorySystem = new CostumeInventorySystem();

        // Akses komponen menggunakan reflection
        JTextField idField = (JTextField) getPrivateField(inventorySystem, "idField");
        JTextField nameField = (JTextField) getPrivateField(inventorySystem, "nameField");
        JTextField sizeField = (JTextField) getPrivateField(inventorySystem, "sizeField");
        JTable costumeTable = (JTable) getPrivateField(inventorySystem, "costumeTable");

        // Tambahkan kostum terlebih dahulu
        testAddCostumeValidInput();

        // Pilih baris terakhir
        int lastRowIndex = costumeTable.getRowCount() - 1;
        costumeTable.setRowSelectionInterval(lastRowIndex, lastRowIndex);

        // Ubah beberapa field
        nameField.setText("Updated Spider-Man Costume");
        sizeField.setText("L");

        // Panggil method edit menggunakan reflection
        java.lang.reflect.Method editCostumeMethod = CostumeInventorySystem.class.getDeclaredMethod("editCostume");
        editCostumeMethod.setAccessible(true);
        editCostumeMethod.invoke(inventorySystem);

        // Ambil kembali tabel setelah operasi
        costumeTable = (JTable) getPrivateField(inventorySystem, "costumeTable");

        // Cek apakah data berhasil diubah
        assertEquals("Updated Spider-Man Costume", costumeTable.getValueAt(lastRowIndex, 1));
        assertEquals("L", costumeTable.getValueAt(lastRowIndex, 4));
    }

    @Test
    public void testDeleteCostume() throws Exception {
        // Buat instance CostumeInventorySystem
        CostumeInventorySystem inventorySystem = new CostumeInventorySystem();

        // Akses komponen menggunakan reflection
        JTable costumeTable = (JTable) getPrivateField(inventorySystem, "costumeTable");

        // Tambahkan beberapa kostum
        testAddCostumeValidInput();
        testAddCostumeValidInput();

        // Simpan jumlah baris awal
        int initialRowCount = costumeTable.getRowCount();

        // Pilih baris terakhir
        int lastRowIndex = costumeTable.getRowCount() - 1;
        costumeTable.setRowSelectionInterval(lastRowIndex, lastRowIndex);

        // Panggil method delete menggunakan reflection
        java.lang.reflect.Method deleteCostumeMethod = CostumeInventorySystem.class.getDeclaredMethod("deleteCostume");
        deleteCostumeMethod.setAccessible(true);
        deleteCostumeMethod.invoke(inventorySystem);

        // Ambil kembali tabel setelah operasi
        costumeTable = (JTable) getPrivateField(inventorySystem, "costumeTable");

        // Cek apakah jumlah baris berkurang
        assertEquals(initialRowCount - 1, costumeTable.getRowCount());
    }

    @Test
    public void testWaktuPencatatan() throws Exception {
        // Buat instance CostumeInventorySystem
        CostumeInventorySystem inventorySystem = new CostumeInventorySystem();

        // Akses komponen menggunakan reflection
        JTextField idField = (JTextField) getPrivateField(inventorySystem, "idField");
        JTextField nameField = (JTextField) getPrivateField(inventorySystem, "nameField");
        JTextField sizeField = (JTextField) getPrivateField(inventorySystem, "sizeField");
        JTextField imagePath = (JTextField) getPrivateField(inventorySystem, "imagePath");
        JComboBox<String> categoryBox = (JComboBox<String>) getPrivateField(inventorySystem, "categoryBox");
        JComboBox<String> themeBox = (JComboBox<String>) getPrivateField(inventorySystem, "themeBox");
        JComboBox<String> statusBox = (JComboBox<String>) getPrivateField(inventorySystem, "statusBox");
        JTable costumeTable = (JTable) getPrivateField(inventorySystem, "costumeTable");

        // Isi field-field input
        idField.setText("002");
        nameField.setText("Batman Costume");
        categoryBox.setSelectedItem("Cosplay");
        themeBox.setSelectedItem("Action");
        sizeField.setText("L");
        statusBox.setSelectedItem("Available");
        imagePath.setText("E:\\image 7.png");

        // Panggil method addCostume menggunakan reflection
        java.lang.reflect.Method addCostumeMethod = CostumeInventorySystem.class.getDeclaredMethod("addCostume");
        addCostumeMethod.setAccessible(true);
        addCostumeMethod.invoke(inventorySystem);

        // Ambil kembali tabel setelah operasi
        costumeTable = (JTable) getPrivateField(inventorySystem, "costumeTable");

        // Ambil waktu terakhir
        int lastRowIndex = costumeTable.getRowCount() - 1;
        String waktuTercatat = costumeTable.getValueAt(lastRowIndex, 7).toString();

        // Parse waktu
        LocalDateTime waktuParse = LocalDateTime.parse(
                waktuTercatat,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        );

        // Pastikan waktu tidak null dan format benar
        assertNotNull(waktuParse);

        // Pastikan waktu tidak terlalu jauh dari waktu sekarang (maks 5 detik)
        LocalDateTime now = LocalDateTime.now();
        assertTrue(Math.abs(java.time.Duration.between(now, waktuParse).getSeconds()) < 5);
    }

    @Test
    public void testValidasiInput() throws Exception {
        // Buat instance CostumeInventorySystem
        CostumeInventorySystem inventorySystem = new CostumeInventorySystem();

        // Akses method validateInput menggunakan reflection
        java.lang.reflect.Method validateInputMethod = CostumeInventorySystem.class.getDeclaredMethod("validateInput");
        validateInputMethod.setAccessible(true);

        // Skenario 1: Input lengkap (seharusnya valid)
        setPrivateField(inventorySystem, "idField", createTextField("001"));
        setPrivateField(inventorySystem, "nameField", createTextField("Test Costume"));
        setPrivateField(inventorySystem, "sizeField", createTextField("M"));
        setPrivateField(inventorySystem, "imagePath", createTextField("/path/to/image.jpg"));

        boolean resultValid = (boolean) validateInputMethod.invoke(inventorySystem);
        assertTrue("Input lengkap seharusnya valid", resultValid);

        // Skenario 2: Input tidak lengkap (seharusnya tidak valid)
        setPrivateField(inventorySystem, "idField", createTextField(""));
        boolean resultInvalid = (boolean) validateInputMethod.invoke(inventorySystem);
        assertFalse("Input tidak lengkap seharusnya tidak valid", resultInvalid);
    }

    // Helper method untuk membuat JTextField
    private JTextField createTextField(String text) {
        JTextField textField = new JTextField();
        textField.setText(text);
        return textField;
    }

    @Test
    public void testSaveToDocx() throws Exception {
        // Buat instance CostumeInventorySystem
        CostumeInventorySystem inventorySystem = new CostumeInventorySystem();

        // Tambahkan beberapa kostum
        testAddCostumeValidInput();
        testAddCostumeValidInput();

        // Panggil method saveToDocx menggunakan reflection
        java.lang.reflect.Method saveToDocxMethod = CostumeInventorySystem.class.getDeclaredMethod("saveToDocx");
        saveToDocxMethod.setAccessible(true);

        try {
            // Invoke method
            saveToDocxMethod.invoke(inventorySystem);

            // Cek apakah file berhasil dibuat
            String currentDir = System.getProperty("user.dir");
            String filePath = currentDir + File.separator + "CostumeData.docx";

            File savedFile = new File(filePath);
            assertTrue("File Word seharusnya dibuat", savedFile.exists());
            assertTrue("File Word seharusnya memiliki ukuran > 0", savedFile.length() > 0);
        } catch (Exception e) {
            fail("Gagal menyimpan dokumen: " + e.getMessage());
        }
    }
}