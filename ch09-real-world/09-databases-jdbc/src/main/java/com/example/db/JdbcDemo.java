// 9.9 Talking to a database with JDBC
// Run: bash run.sh   (Maven downloads the H2 database driver the first time)
package com.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDemo {
    public static void main(String[] args) throws SQLException {
        // The URL says which database to open. "mem:" = in memory, gone when the program ends.
        // For PostgreSQL it would look like jdbc:postgresql://host:5432/shop
        String url = "jdbc:h2:mem:shop";

        try (Connection conn = DriverManager.getConnection(url, "sa", "")) {
            try (Statement st = conn.createStatement()) {
                st.execute("CREATE TABLE products (id INT PRIMARY KEY, name VARCHAR(50), price DECIMAL(8,2), stock INT)");
            }

            // PreparedStatement: ? placeholders keep data separate from SQL (prevents SQL injection)
            String insert = "INSERT INTO products (id, name, price, stock) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insert)) {
                Object[][] rows = {{1, "Kettle", 24.99, 12}, {2, "Toaster", 39.50, 0}, {3, "Blender", 59.00, 5}};
                for (Object[] r : rows) {
                    ps.setInt(1, (int) r[0]);
                    ps.setString(2, (String) r[1]);
                    ps.setDouble(3, (double) r[2]);
                    ps.setInt(4, (int) r[3]);
                    ps.executeUpdate();
                }
            }

            // Query: walk the ResultSet row by row, like reading a spreadsheet top to bottom
            System.out.println("In stock:");
            try (PreparedStatement ps = conn.prepareStatement("SELECT name, price, stock FROM products WHERE stock > ? ORDER BY price");
                 ) {
                ps.setInt(1, 0);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        System.out.printf("  %-8s $%6.2f  (%d left)%n", rs.getString("name"), rs.getDouble("price"), rs.getInt("stock"));
                    }
                }
            }

            // Transactions: both updates succeed together or not at all
            conn.setAutoCommit(false);
            try (PreparedStatement sell = conn.prepareStatement("UPDATE products SET stock = stock - 1 WHERE id = ? AND stock > 0")) {
                sell.setInt(1, 1);
                int changed = sell.executeUpdate();
                sell.setInt(1, 2);
                int changed2 = sell.executeUpdate();          // toaster has no stock: 0 rows change
                if (changed2 == 0) {
                    conn.rollback();
                    System.out.println("Order cancelled: toaster out of stock, kettle sale rolled back (" + changed + " row undone)");
                } else {
                    conn.commit();
                }
            }
            conn.setAutoCommit(true);

            try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery("SELECT stock FROM products WHERE id = 1")) {
                rs.next();
                System.out.println("Kettle stock still: " + rs.getInt(1));
            }
        }
    }
}
