package view;

import dao.ConexaoDAO;
import model.UsuarioDTO;
import javax.swing.*;
import java.awt.*;

public class FormIncluirUsuarios extends JFrame {
    private JTextField txtUser = new JTextField(15);
    private JPasswordField txtPass = new JPasswordField(15);
    private FormListarUsuarios telaListagem;

    public FormIncluirUsuarios(FormListarUsuarios telaListagem) {
        this.telaListagem = telaListagem;
        setTitle("Cadastro de Usuário");
        setSize(300, 250);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);
        setLayout(new FlowLayout());

        // Estilização igual ao do Gabriel
        txtUser.setBackground(new Color(30, 30, 30));
        txtUser.setForeground(Color.WHITE);
        txtUser.setBorder(BorderFactory.createLineBorder(new Color(200, 0, 0)));

        txtPass.setBackground(new Color(30, 30, 30));
        txtPass.setForeground(Color.WHITE);
        txtPass.setBorder(BorderFactory.createLineBorder(new Color(200, 0, 0)));

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBackground(new Color(200, 0, 0));
        btnSalvar.setForeground(Color.WHITE);

        btnSalvar.addActionListener(e -> salvar());

        add(new JLabel("<html><font color='white'>Usuário:</font></html>"));
        add(txtUser);
        add(new JLabel("<html><font color='white'>Senha:</font></html>"));
        add(txtPass);
        add(btnSalvar);
    }

    private void salvar() {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNome_usuario(txtUser.getText());
        dto.setSenha_usuario(new String(txtPass.getPassword()));

        new ConexaoDAO().cadastrarUsuario(dto);

        // Atualiza a tabela da outra tela e fecha esta
        if(telaListagem != null) telaListagem.carregarDados();
        dispose();
    }
}
