
    package dao;

import model.UsuarioDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


    public class ConexaoDAO {


        private static final String URL  = "jdbc:mysql://localhost:3306/bancoGabriel?useSSL=false&serverTimezone=America/Fortaleza";
        private static final String USER = "root";
        private static final String PASS = "";


        private static Connection getConnection() throws Exception {
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


        public void cadastrarUsuario(UsuarioDTO objUsuarioDTO) {
            String sql = "INSERT INTO tb_usuarios (login, senha) VALUES (?, ?)";
            try {
                Connection conn = getConnection();
                PreparedStatement pstm = conn.prepareStatement(sql);
                pstm.setString(1, objUsuarioDTO.getNome_usuario());
                pstm.setString(2, objUsuarioDTO.getSenha_usuario());
                pstm.execute();
                pstm.close();
                conn.close();
                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + erro.getMessage());
            }
        }


        public boolean autenticarUsuario(UsuarioDTO objUsuarioDTO) {
            String sql = "SELECT id_usuario FROM tb_usuarios WHERE login = ? AND senha = ?";
            try {
                Connection conn = getConnection();
                PreparedStatement pstm = conn.prepareStatement(sql);
                pstm.setString(1, objUsuarioDTO.getNome_usuario());
                pstm.setString(2, objUsuarioDTO.getSenha_usuario());
                ResultSet rs = pstm.executeQuery();
                boolean autenticado = rs.next();
                rs.close();
                pstm.close();
                conn.close();
                return autenticado;
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, "Erro na autenticação: " + erro.getMessage());
                return false;
            }
        }
    }

