import java.sql.*;

public class CRUDPerformance {

    static final String URL = "jdbc:mysql://localhost:3306/crud_analysis";
    static final String USER = "root";
    static final String PASSWORD = "root123";

    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

            measureInsert(con);
            measureRead(con);
            measureUpdate(con);
            measureDelete(con);

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // CREATE
    public static void measureInsert(Connection con) throws Exception {
        String query = "INSERT INTO students(name, age, department) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);

        long start = System.nanoTime();

        for (int i = 0; i < 1000; i++) {
            ps.setString(1, "Student" + i);
            ps.setInt(2, 20 + (i % 5));
            ps.setString(3, "CSE");
            ps.executeUpdate();
        }

        long end = System.nanoTime();
        System.out.println("Insert Time: " + (end - start) / 1_000_000 + " ms");
    }

    // READ
    public static void measureRead(Connection con) throws Exception {
        String query = "SELECT * FROM students";
        Statement st = con.createStatement();

        long start = System.nanoTime();

        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            rs.getInt("id");
            rs.getString("name");
        }

        long end = System.nanoTime();
        System.out.println("Read Time: " + (end - start) / 1_000_000 + " ms");
    }

    // UPDATE
    public static void measureUpdate(Connection con) throws Exception {
        String query = "UPDATE students SET age = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);

        long start = System.nanoTime();

        for (int i = 1; i <= 500; i++) {
            ps.setInt(1, 25);
            ps.setInt(2, i);
            ps.executeUpdate();
        }

        long end = System.nanoTime();
        System.out.println("Update Time: " + (end - start) / 1_000_000 + " ms");
    }

    // DELETE
    public static void measureDelete(Connection con) throws Exception {
        String query = "DELETE FROM students WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);

        long start = System.nanoTime();

        for (int i = 1; i <= 500; i++) {
            ps.setInt(1, i);
            ps.executeUpdate();
        }

        long end = System.nanoTime();
        System.out.println("Delete Time: " + (end - start) / 1_000_000 + " ms");
    }
}