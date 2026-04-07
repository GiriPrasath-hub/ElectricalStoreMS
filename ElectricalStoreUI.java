import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ElectricalStoreUI extends JFrame {

    static final String DB_URL = "jdbc:mysql://localhost:3306/electrical_store";
    static final String USER = "root";
    static final String PASS = "dbms";

    JTable table;
    DefaultTableModel model;

    JTextField txtId, txtName, txtPrice;

    public ElectricalStoreUI() {
        setTitle("Electrical Store Management System");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // ===== INPUT PANEL =====
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Product Details"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("Product ID:"), gbc);

        gbc.gridx = 1;
        txtId = new JTextField(10);
        inputPanel.add(txtId, gbc);

        gbc.gridx = 2;
        inputPanel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 3;
        txtName = new JTextField(10);
        inputPanel.add(txtName, gbc);

        gbc.gridx = 4;
        inputPanel.add(new JLabel("Price:"), gbc);

        gbc.gridx = 5;
        txtPrice = new JTextField(10);
        inputPanel.add(txtPrice, gbc);

        // ===== BUTTON PANEL =====
        JPanel buttonPanel = new JPanel();

        JButton btnAdd = new JButton("Add");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnRefresh = new JButton("Refresh");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnRefresh);

        // ===== TABLE =====
        model = new DefaultTableModel();
        model.addColumn("Product ID");
        model.addColumn("Name");
        model.addColumn("Price");

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // ===== FIXED LAYOUT (RESPONSIVE) =====
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        loadData();

        // ===== BUTTON ACTIONS =====
        btnAdd.addActionListener(e -> addProduct());
        btnUpdate.addActionListener(e -> updateProduct());
        btnDelete.addActionListener(e -> deleteProduct());
        btnRefresh.addActionListener(e -> loadData());

        // ===== AUTO-FILL WHEN CLICKING TABLE =====
        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                txtId.setText(model.getValueAt(row, 0).toString());
                txtName.setText(model.getValueAt(row, 1).toString());
                txtPrice.setText(model.getValueAt(row, 2).toString());
            }
        });
    }

    // ===== LOAD DATA =====
    void loadData() {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM products");

            model.setRowCount(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("product_id"),
                        rs.getString("product_name"),
                        rs.getInt("price")
                });
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ===== ADD =====
    void addProduct() {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO products VALUES (?, ?, ?)"
            );

            ps.setString(1, txtId.getText().trim());
            ps.setString(2, txtName.getText().trim());
            ps.setInt(3, Integer.parseInt(txtPrice.getText().trim()));

            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Product added!");

            conn.close();
            loadData();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ===== UPDATE =====
    void updateProduct() {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE products SET product_name=?, price=? WHERE product_id=?"
            );

            ps.setString(1, txtName.getText().trim());
            ps.setInt(2, Integer.parseInt(txtPrice.getText().trim()));
            ps.setString(3, txtId.getText().trim());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Product ID not found!");
            }

            conn.close();
            loadData();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ===== DELETE =====
    void deleteProduct() {
        try {
            int row = table.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a row to delete!");
                return;
            }

            String id = model.getValueAt(row, 0).toString();

            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM products WHERE product_id=?"
            );

            ps.setString(1, id);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Deleted successfully!");

            conn.close();
            loadData();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ElectricalStoreUI().setVisible(true));
    }
}