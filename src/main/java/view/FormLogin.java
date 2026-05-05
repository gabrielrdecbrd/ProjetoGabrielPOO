package view;

import dao.UsuarioDAO;
import model.UsuarioDTO;
import javax.swing.*;
import java.awt.*;

public class FormLogin extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnEntrar;

    public FormLogin() {
        configurarJanela();
        inicializarComponentes();
    }

    private void configurarJanela() {
        setTitle("Acesso ao Sistema - Gabriel Rodrigues");
        setSize(420, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.BLACK);
    }

    private void inicializarComponentes() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("LOGIN", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitulo.setForeground(new Color(200, 0, 0));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(lblTitulo, gbc);

        gbc.gridwidth = 1; gbc.gridy = 1; gbc.gridx = 0;
        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setForeground(Color.WHITE);
        add(lblUsuario, gbc);
        txtUsuario = new JTextField(15);
        txtUsuario.setBackground(new Color(30, 30, 30));
        txtUsuario.setForeground(Color.WHITE);
        txtUsuario.setCaretColor(Color.WHITE);
        txtUsuario.setBorder(BorderFactory.createLineBorder(new Color(200, 0, 0)));
        gbc.gridx = 1;
        add(txtUsuario, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setForeground(Color.WHITE);
        add(lblSenha, gbc);
        txtSenha = new JPasswordField(15);
        txtSenha.setBackground(new Color(30, 30, 30));
        txtSenha.setForeground(Color.WHITE);
        txtSenha.setCaretColor(Color.WHITE);
        txtSenha.setBorder(BorderFactory.createLineBorder(new Color(200, 0, 0)));
        gbc.gridx = 1;
        add(txtSenha, gbc);


        btnEntrar = new JButton("Entrar");
        btnEntrar.setBackground(new Color(200, 0, 0));
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setFocusPainted(false);
        btnEntrar.setFont(new Font("Arial", Font.BOLD, 13));
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        add(btnEntrar, gbc);
        btnEntrar.addActionListener(e -> acaoLogin());


        JButton btnIrCadastro = new JButton("Criar Nova Conta");
        btnIrCadastro.setBackground(new Color(30, 30, 30));
        btnIrCadastro.setForeground(new Color(200, 0, 0));
        btnIrCadastro.setFocusPainted(false);
        btnIrCadastro.setBorder(BorderFactory.createLineBorder(new Color(200, 0, 0)));
        gbc.gridy = 4;
        add(btnIrCadastro, gbc);
        btnIrCadastro.addActionListener(e -> new FormCadastro().setVisible(true));
    }


    private void acaoLogin() {
        String usuario = txtUsuario.getText().trim();
        String senha   = new String(txtSenha.getPassword()).trim();

        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }

        UsuarioDTO objUsuarioDTO = new UsuarioDTO();
        objUsuarioDTO.setNome_usuario(usuario);
        objUsuarioDTO.setSenha_usuario(senha);

        UsuarioDAO objUsuarioDAO = new UsuarioDAO();
        boolean autenticado = objUsuarioDAO.autenticarUsuario(objUsuarioDTO);

        if (autenticado) {
            JOptionPane.showMessageDialog(this, "Bem-vindo, " + usuario + "!");
            new FormPrincipal().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Usuário ou senha incorretos.",
                    "Acesso negado",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormLogin().setVisible(true));
    }
}
