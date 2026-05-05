package dao;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConexaoDAO {


    private static final String URL  = "jdbc:mysql://bd_savir.mysql.dbaas.com.br:3306/bd_savir?useSSL=false&serverTimezone=America/Fortaleza";
    private static final String USER = "bd_savir";
    private static final String PASS = "B@nc0D@d0s";


    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }


    public static boolean testarConexao() {
        try (Connection conn = getConnection()) {
            return conn != null && !conn.isClosed();
        } catch (Exception e) {
            return false;
        }
    }
}
