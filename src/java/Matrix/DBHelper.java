package Matrix;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/*
    Her defasında tek tek drivemanager satırları yazmamak için yardımcı
bir nesne yardımı ile bunu halletmek için bu sınıfı kullandık.
Her bir db bağlantısında bunu rahatça kullanabiliriz.
*/
public class DBHelper {

    private final String username = "root";
    private final String password = "123";
    private final String dbUrl = "jdbc:derby://localhost:1527/MatrixDB";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, username, password);
    }
    public void showErrorMessage(SQLException exception){
        System.out.println(exception.getMessage());
    }
}
