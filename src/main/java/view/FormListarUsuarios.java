package view;

import dao.ConexaoDAO;
import model.UsuarioDTO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FormListarUsuarios extends JFrame {
    private JTable tabela;
    private DefaultTableModel modelo;

    public FormListarUsuarios() {
        setTitle("Consulta de Usuários");
        setSize(500, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        // Tabela
        modelo = new DefaultTableModel(new Object[]{"ID", "Login"}, 0);
        tabela = new JTable(modelo);
        tabela.setBackground(new Color(30, 30, 30));
        tabela.setForeground(Color.WHITE);
        tabela.getTableHeader().setBackground(new Color(200, 0, 0));
        tabela.getTableHeader().setForeground(Color.WHITE);

        add(new JScrollPane(tabela), BorderLayout.CENTER);

        // Botão para abrir o cadastro
        JButton btnNovo = new JButton("Incluir Novo Usuário");
        btnNovo.setBackground(new Color(200, 0, 0));
        btnNovo.setForeground(Color.WHITE);
        btnNovo.addActionListener(e -> new FormIncluirUsuarios(this).setVisible(true));

        add(btnNovo, BorderLayout.SOUTH);
        carregarDados();
    }

    public void carregarDados() {
        modelo.setRowCount(0);
        List<UsuarioDTO> lista = new ConexaoDAO().listarUsuarios();
        for (UsuarioDTO u : lista) {
            modelo.addRow(new Object[]{u.getId_usuario(), u.getNome_usuario()});
        }
    }
}
