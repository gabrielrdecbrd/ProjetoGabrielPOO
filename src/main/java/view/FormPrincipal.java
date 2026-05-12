package view;

import javax.swing.*;
import java.awt.*;

public class FormPrincipal extends JFrame {

    public FormPrincipal() {
        configurarJanela();
        inicializarComponentes();
    }

    private void configurarJanela() {
        setTitle("Sistema de Gestão - Home");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);
    }

    private void inicializarComponentes() {
        // Criando a barra de menu
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(30, 30, 30));
        menuBar.setBorder(BorderFactory.createLineBorder(new Color(200, 0, 0)));

        // Menu "Administração"
        JMenu menuAdmin = new JMenu("Administração");
        menuAdmin.setForeground(Color.WHITE);

        // Item para gerenciar usuários (Apontando para sua nova tela de listagem)
        JMenuItem itemUsuarios = new JMenuItem("Gerenciar Usuários");
        itemUsuarios.setBackground(new Color(30, 30, 30));
        itemUsuarios.setForeground(Color.WHITE);

        itemUsuarios.addActionListener(e -> {
            // Chamando a tela de listagem conforme a regra do professor
            FormListarUsuarios telaLista = new FormListarUsuarios();
            telaLista.setVisible(true);
        });

        // Item Sair
        JMenuItem itemSair = new JMenuItem("Sair");
        itemSair.addActionListener(e -> System.exit(0));

        menuAdmin.add(itemUsuarios);
        menuAdmin.addSeparator();
        menuAdmin.add(itemSair);
        menuBar.add(menuAdmin);

        setJMenuBar(menuBar);

        // Painel central com Boas-Vindas
        JPanel painelCentral = new JPanel(new GridBagLayout());
        painelCentral.setBackground(Color.BLACK);

        JLabel lblBoasVindas = new JLabel("Bem-vindo ao Sistema");
        lblBoasVindas.setFont(new Font("Arial", Font.BOLD, 24));
        lblBoasVindas.setForeground(new Color(200, 0, 0));

        painelCentral.add(lblBoasVindas);
        add(painelCentral, BorderLayout.CENTER);
    }
}
