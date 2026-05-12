package view;

import dao.ConexaoDAO;
import model.UsuarioDTO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FormUsuarios extends JFrame {

    private JTable tabelaUsuarios;
    private DefaultTableModel modeloTabela;
    private JTextField txtNovoUsuario;
    private JPasswordField txtNovaSenha;
    private JButton btnIncluir;
    private JButton btnAtualizar;

    public FormUsuarios() {
        configurarJanela();
        inicializarComponentes();
        carregarUsuarios();
    }

    private void configurarJanela() {
        // Adaptado para o título e padrão de cores do Gabriel
        setTitle("Gerenciar Usuários - Módulo Administrativo");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.BLACK);
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));

        // Painel de formulário seguindo o estilo Dark/Red do amigo
        JPanel painelFormulario = new JPanel(new GridBagLayout());
        painelFormulario.setBackground(Color.BLACK);
        painelFormulario.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(200, 0, 0)), // Vermelho dele
                "Incluir Novo Usuário",
                0, 0,
                new Font("Arial", Font.BOLD, 12),
                Color.WHITE // Texto do título em branco para destacar no preto
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campo usuário
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setForeground(Color.WHITE);
        painelFormulario.add(lblUsuario, gbc);

        txtNovoUsuario = new JTextField(15);
        txtNovoUsuario.setBackground(new Color(30, 30, 30)); // Cinza escuro dele
        txtNovoUsuario.setForeground(Color.WHITE);
        txtNovoUsuario.setCaretColor(Color.WHITE);
        txtNovoUsuario.setBorder(BorderFactory.createLineBorder(new Color(200, 0, 0)));
        gbc.gridx = 1;
        painelFormulario.add(txtNovoUsuario, gbc);

        // Campo senha
        gbc.gridx = 2; gbc.gridy = 0;
        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setForeground(Color.WHITE);
        painelFormulario.add(lblSenha, gbc);

        txtNovaSenha = new JPasswordField(15);
        txtNovaSenha.setBackground(new Color(30, 30, 30));
        txtNovaSenha.setForeground(Color.WHITE);
        txtNovaSenha.setCaretColor(Color.WHITE);
        txtNovaSenha.setBorder(BorderFactory.createLineBorder(new Color(200, 0, 0)));
        gbc.gridx = 3;
        painelFormulario.add(txtNovaSenha, gbc);

        // Botão incluir (Estilo Vermelho)
        btnIncluir = new JButton("Incluir");
        btnIncluir.setBackground(new Color(200, 0, 0));
        btnIncluir.setForeground(Color.WHITE);
        btnIncluir.setFocusPainted(false);
        btnIncluir.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 4;
        painelFormulario.add(btnIncluir, gbc);
        btnIncluir.addActionListener(e -> incluirUsuario());

        add(painelFormulario, BorderLayout.NORTH);

        // --- TABELA DE USUÁRIOS (Sua lógica, visual dele) ---
        String[] colunas = {"ID", "Usuário", "Senha"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaUsuarios = new JTable(modeloTabela);
        tabelaUsuarios.setBackground(new Color(30, 30, 30));
        tabelaUsuarios.setForeground(Color.WHITE);
        tabelaUsuarios.setGridColor(new Color(200, 0, 0));
        tabelaUsuarios.setSelectionBackground(new Color(200, 0, 0));
        tabelaUsuarios.setSelectionForeground(Color.WHITE);
        tabelaUsuarios.setRowHeight(25);

        // Cabeçalho da tabela
        tabelaUsuarios.getTableHeader().setBackground(new Color(200, 0, 0));
        tabelaUsuarios.getTableHeader().setForeground(Color.WHITE);
        tabelaUsuarios.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        JScrollPane scrollPane = new JScrollPane(tabelaUsuarios);
        scrollPane.getViewport().setBackground(new Color(30, 30, 30));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 0, 0)));
        add(scrollPane, BorderLayout.CENTER);

        // --- BOTÃO ATUALIZAR (rodapé) ---
        btnAtualizar = new JButton("Atualizar Lista");
        btnAtualizar.setBackground(new Color(30, 30, 30));
        btnAtualizar.setForeground(new Color(200, 0, 0));
        btnAtualizar.setFocusPainted(false);
        btnAtualizar.setBorder(BorderFactory.createLineBorder(new Color(200, 0, 0)));
        btnAtualizar.setFont(new Font("Arial", Font.BOLD, 12));
        btnAtualizar.addActionListener(e -> carregarUsuarios());

        JPanel painelRodape = new JPanel();
        painelRodape.setBackground(Color.BLACK);
        painelRodape.add(btnAtualizar);
        add(painelRodape, BorderLayout.SOUTH);
    }


    private void carregarUsuarios() {
        modeloTabela.setRowCount(0);
        ConexaoDAO objConexaoDAO = new ConexaoDAO();
        List<UsuarioDTO> lista = objConexaoDAO.listarUsuarios();
        for (UsuarioDTO usuario : lista) {
            modeloTabela.addRow(new Object[]{
                    usuario.getId_usuario(),
                    usuario.getNome_usuario(),
                    usuario.getSenha_usuario()
            });
        }
    }


    private void incluirUsuario() {
        String usuario = txtNovoUsuario.getText().trim();
        String senha   = new String(txtNovaSenha.getPassword()).trim();

        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        if (usuario.contains(" ")) {
            JOptionPane.showMessageDialog(this, "O usuário não pode conter espaços.");
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

        UsuarioDTO objUsuarioDTO = new UsuarioDTO();
        objUsuarioDTO.setNome_usuario(usuario);
        objUsuarioDTO.setSenha_usuario(senha);

        ConexaoDAO objConexaoDAO = new ConexaoDAO();
        objConexaoDAO.cadastrarUsuario(objUsuarioDTO);

        txtNovoUsuario.setText("");
        txtNovaSenha.setText("");
        carregarUsuarios();
    }

    private boolean senhaValida(String senha) {
        if (senha.length() < 6) return false;
        boolean temLetra  = senha.matches(".*[a-zA-Z].*");
        boolean temNumero = senha.matches(".*[0-9].*");
        return temLetra && temNumero;
    }
}
