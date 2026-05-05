package dao;
import model.UsuarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class UsuarioDAO {


    public void cadastrarUsuario(UsuarioDTO objUsuarioDTO) {
        String sql = "INSERT INTO tb_usuarios (login, senha) VALUES (?, ?)";
        try {
            Connection conn = ConexaoDAO.getConnection();
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
            Connection conn = ConexaoDAO.getConnection();
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