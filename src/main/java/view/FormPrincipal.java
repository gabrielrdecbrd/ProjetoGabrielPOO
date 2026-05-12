package view;
import javax.swing.*;
import java.awt.*;
public class FormPrincipal extends JFrame{

    public FormPrincipal() {
        configurarJanela();
        inicializarComponentes();
    }


    private void configurarJanela() {
        setTitle("Sistema - Gabriel Rodrigues");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.BLACK);
    }


    private void inicializarComponentes() {
        setLayout(new BorderLayout());


        JLabel lblBemVindo = new JLabel("Bem-vindo ao Sistema!", SwingConstants.CENTER);
        lblBemVindo.setFont(new Font("Arial", Font.BOLD, 22));
        lblBemVindo.setForeground(new Color(200, 0, 0));
        add(lblBemVindo, BorderLayout.CENTER);


        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(30, 30, 30));

        JMenu menuUsuarios = new JMenu("Usuários");
        menuUsuarios.setForeground(Color.WHITE);
        JMenuItem itemGerenciar = new JMenuItem("Gerenciar Usuários");
        itemGerenciar.addActionListener(e -> {
            FormUsuarios telaUsuarios = new FormUsuarios();
            telaUsuarios.setVisible(true);
        });
        menuUsuarios.add(itemGerenciar);
        menuBar.add(menuUsuarios);

        JMenu menuSistema = new JMenu("Sistema");
        menuSistema.setForeground(Color.WHITE);
        JMenuItem itemSair = new JMenuItem("Sair");
        itemSair.addActionListener(e -> {
            dispose();
            new FormLogin().setVisible(true);
        });
        menuSistema.add(itemSair);
        menuBar.add(menuSistema);

        setJMenuBar(menuBar);
    }
}
