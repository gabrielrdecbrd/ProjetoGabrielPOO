package dao;

import model.UsuarioDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ConexaoDAO {

    private static final String URL  = "jdbc:mysql://bd_savir.mysql.dbaas.com.br:3306/bd_savir?useSSL=false&serverTimezone=America/Fortaleza";
    private static final String USER = "bd_savir";
    private static final String PASS = "B@nc0D@d0s";

    private static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public void cadastrarUsuario(UsuarioDTO objUsuarioDTO) {

        String sql = "INSERT INTO tb_usuarios (nome, login, senha) VALUES (?, ?, ?)";
        try {
            Connection conn = getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, objUsuarioDTO.getNome_usuario());
            pstm.setString(2, objUsuarioDTO.getNome_usuario()); // Usando o login como nome também
            pstm.setString(3, objUsuarioDTO.getSenha_usuario());
            pstm.execute();
            pstm.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + erro.getMessage());
        }
    }

    public boolean autenticarUsuario(UsuarioDTO objUsuarioDTO) {
        String sql = "SELECT * FROM tb_usuarios WHERE login = ? AND senha = ?";
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


    public List<UsuarioDTO> listarUsuarios() {
        List<UsuarioDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM tb_usuarios";
        try {
            Connection conn = getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                UsuarioDTO obj = new UsuarioDTO();

                obj.setId_usuario(rs.getInt("usuario_id"));
                obj.setNome_usuario(rs.getString("login"));
                obj.setSenha_usuario(rs.getString("senha"));
                lista.add(obj);
            }
            rs.close();
            pstm.close();
            conn.close();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar: " + erro.getMessage());
        }
        return lista;
    }
}
