package view;

import dao.UsuarioDAO;
import model.UsuarioDTO;
import javax.swing.*;
import java.awt.*;

public class FormCadastro extends JFrame {
    private JTextField txtNovoUsuario;
    private JPasswordField txtNovaSenha;
    private JButton btnSalvar;

    public FormCadastro() {
        configurarJanela();
        inicializarComponentes();
    }


    private void configurarJanela() {
        setTitle("Cadastro de Usuário - Gabriel Rodrigues");
        setSize(370, 270);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.BLACK);
    }


    private void inicializarComponentes() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        JLabel lblTitulo = new JLabel("CADASTRO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(200, 0, 0));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(lblTitulo, gbc);


        gbc.gridwidth = 1; gbc.gridy = 1; gbc.gridx = 0;
        JLabel lblUsuario = new JLabel("Novo Usuário:");
        lblUsuario.setForeground(Color.WHITE);
        add(lblUsuario, gbc);
        txtNovoUsuario = new JTextField(15);
        txtNovoUsuario.setBackground(new Color(30, 30, 30));
        txtNovoUsuario.setForeground(Color.WHITE);
        txtNovoUsuario.setCaretColor(Color.WHITE);
        txtNovoUsuario.setBorder(BorderFactory.createLineBorder(new Color(200, 0, 0)));
        gbc.gridx = 1;
        add(txtNovoUsuario, gbc);


        gbc.gridx = 0; gbc.gridy = 2;
        JLabel lblSenha = new JLabel("Nova Senha:");
        lblSenha.setForeground(Color.WHITE);
        add(lblSenha, gbc);
        txtNovaSenha = new JPasswordField(15);
        txtNovaSenha.setBackground(new Color(30, 30, 30));
        txtNovaSenha.setForeground(Color.WHITE);
        txtNovaSenha.setCaretColor(Color.WHITE);
        txtNovaSenha.setBorder(BorderFactory.createLineBorder(new Color(200, 0, 0)));
        gbc.gridx = 1;
        add(txtNovaSenha, gbc);


        btnSalvar = new JButton("Salvar Cadastro");
        btnSalvar.setBackground(new Color(200, 0, 0));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFocusPainted(false);
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 13));
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        add(btnSalvar, gbc);
        btnSalvar.addActionListener(e -> salvarDados());
    }


    private void salvarDados() {
        String usuario = txtNovoUsuario.getText().trim();
        String senha   = new String(txtNovaSenha.getPassword()).trim();


        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }


        if (usuario.length() < 3) {
            JOptionPane.showMessageDialog(this, "Usuário deve ter pelo menos 3 caracteres.");
            return;
        }


        if (!senhaValida(senha)) {
            JOptionPane.showMessageDialog(this,
                    "Senha fraca! Use pelo menos 6 caracteres,\num número e uma letra.");
            return;
        }


        if (usuario.length() > 50 || senha.length() > 50) {
            JOptionPane.showMessageDialog(this, "Usuário ou senha muito longos (máx. 50 caracteres).");
            return;
        }


        UsuarioDTO objUsuarioDTO = new UsuarioDTO();
        objUsuarioDTO.setNome_usuario(usuario);
        objUsuarioDTO.setSenha_usuario(senha);

        UsuarioDAO objUsuarioDAO = new UsuarioDAO();
        objUsuarioDAO.cadastrarUsuario(objUsuarioDTO);
        dispose();
    }

    private boolean senhaValida(String senha) {
        if (senha.length() < 6) return false;
        boolean temLetra  = senha.matches(".*[a-zA-Z].*");
        boolean temNumero = senha.matches(".*[0-9].*");
        return temLetra && temNumero;
    }
}
