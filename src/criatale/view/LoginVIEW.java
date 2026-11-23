/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package criatale.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginVIEW extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JButton btnEntrar;
    private JButton btnCriarConta;

    public LoginVIEW() {
        setTitle("CriaTale - Software para Criação de História");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Painel Esquerdo (Apresentação / Ilustração do Wireframe 1)
        JPanel panelEsquerdo = new JPanel();
        panelEsquerdo.setBackground(new Color(230, 230, 230));
        panelEsquerdo.setPreferredSize(new Dimension(500, 650));
        panelEsquerdo.setLayout(null);

        JLabel lblTituloSoft = new JLabel("[ SOFTWARE PARA CRIAÇÃO DE HISTÓRIAS ]");
        lblTituloSoft.setBounds(40, 60, 400, 20);
        lblTituloSoft.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelEsquerdo.add(lblTituloSoft);

        // Barras simulando o título principal do wireframe
        JPanel barra1 = new JPanel(); barra1.setBounds(40, 100, 380, 15); barra1.setBackground(new Color(90, 90, 90)); panelEsquerdo.add(barra1);
        JPanel barra2 = new JPanel(); barra2.setBounds(40, 125, 300, 15); barra2.setBackground(new Color(90, 90, 90)); panelEsquerdo.add(barra2);
        JPanel barra3 = new JPanel(); barra3.setBounds(40, 150, 220, 15); barra3.setBackground(new Color(90, 90, 90)); panelEsquerdo.add(barra3);

        JTextArea txtDescricao = new JTextArea("Linha de texto placeholder para apresentação do software e sua principal função criativa.");
        txtDescricao.setBounds(40, 190, 400, 40);
        txtDescricao.setOpaque(false);
        txtDescricao.setEditable(false);
        txtDescricao.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        panelEsquerdo.add(txtDescricao);

        // Caixa simulando a [ ÁREA DE ILUSTRAÇÃO ]
        JPanel panelIlustracao = new JPanel();
        panelIlustracao.setBounds(40, 270, 420, 280);
        panelIlustracao.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panelIlustracao.setBackground(new Color(240, 240, 240));
        panelIlustracao.setLayout(new GridBagLayout());
        JLabel lblIlustracao = new JLabel("[ ÁREA DE ILUSTRAÇÃO ]");
        lblIlustracao.setForeground(Color.GRAY);
        panelIlustracao.add(lblIlustracao);
        panelEsquerdo.add(panelIlustracao);

        // Painel Direito (Formulário de Entrada / Login)
        JPanel panelDireito = new JPanel();
        panelDireito.setBackground(new Color(248, 249, 250));
        panelDireito.setLayout(null);

        JLabel lblAcesso = new JLabel("01 / ACESSO");
        lblAcesso.setBounds(60, 60, 200, 15);
        lblAcesso.setFont(new Font("Segoe UI", Font.BOLD, 11));
        panelDireito.add(lblAcesso);

        JLabel lblEntrar = new JLabel("ENTRAR");
        lblEntrar.setBounds(60, 85, 300, 40);
        lblEntrar.setFont(new Font("Segoe UI", Font.BOLD, 32));
        panelDireito.add(lblEntrar);

        JLabel lblSub = new JLabel("Insira seus dados nos campos abaixo.");
        lblSub.setBounds(60, 130, 300, 20);
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        panelDireito.add(lblSub);

        JLabel lblEmail = new JLabel("E-MAIL");
        lblEmail.setBounds(60, 180, 100, 15);
        lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 11));
        panelDireito.add(lblEmail);

        txtEmail = new JTextField("[ seu@email.com ]");
        txtEmail.setBounds(60, 200, 360, 40);
        panelDireito.add(txtEmail);

        JLabel lblSenha = new JLabel("SENHA");
        lblSenha.setBounds(60, 260, 100, 15);
        lblSenha.setFont(new Font("Segoe UI", Font.BOLD, 11));
        panelDireito.add(lblSenha);

        txtSenha = new JPasswordField("********");
        txtSenha.setBounds(60, 280, 360, 40);
        panelDireito.add(txtSenha);

        JCheckBox chkLembrar = new JCheckBox("LEMBRAR DE MIM");
        chkLembrar.setBounds(60, 335, 150, 20);
        chkLembrar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        chkLembrar.setOpaque(false);
        panelDireito.add(chkLembrar);

        JLabel lblEsqueceu = new JLabel("ESQUECI A SENHA");
        lblEsqueceu.setBounds(315, 335, 110, 20);
        lblEsqueceu.setFont(new Font("Segoe UI", Font.BOLD, 10));
        panelDireito.add(lblEsqueceu);

        btnEntrar = new JButton("ENTRAR →");
        btnEntrar.setBounds(60, 380, 360, 45);
        btnEntrar.setBackground(new Color(70, 70, 70));
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnEntrar.setFocusPainted(false);
        panelDireito.add(btnEntrar);

        JButton btnCriarConta = new JButton("CRIAR NOVA CONTA");
        btnCriarConta.setBounds(60, 460, 360, 40);
        btnCriarConta.setBackground(new Color(230, 230, 230));
        btnCriarConta.setFont(new Font("Segoe UI", Font.BOLD, 11));
        panelDireito.add(btnCriarConta);

        // Ação de clique para navegar para o Dashboard (Visão Geral)
        // Substitua o ActionListener do btnEntrar por este:
        btnEntrar.addActionListener(e -> {
            String email = txtEmail.getText().trim();
            if (!email.isEmpty() && !email.equals("[ seu@email.com ]")) {
                DadosMemoria.usuarioLogado = email.split("@")[0].toUpperCase();
            }
            new DashboardVIEW().setVisible(true);
            dispose();
        });

        // Ação para o botão Criar Nova Conta
        btnCriarConta.addActionListener(e -> {
            String novoUsuario = JOptionPane.showInputDialog(this, "Digite seu nome de usuário para cadastro rápido:");
            if (novoUsuario != null && !novoUsuario.trim().isEmpty()) {
                DadosMemoria.usuarioLogado = novoUsuario.toUpperCase();
                JOptionPane.showMessageDialog(this, "Conta criada com sucesso! Entrando no sistema...");
                new DashboardVIEW().setVisible(true);
                dispose();
            }
        });

        add(panelEsquerdo, BorderLayout.WEST);
        add(panelDireito, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginVIEW().setVisible(true);
        });
    }
}
