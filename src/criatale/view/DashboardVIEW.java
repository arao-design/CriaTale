/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package criatale.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import criatale.dao.HistoriaDAO;
import java.util.List;

public class DashboardVIEW extends JFrame {

    private JPanel panelConteudoPrincipal;
    private CardLayout cardLayout;
    private JLabel lblTotalHistorias;
    private JLabel lblEmAndamento;
    private JLabel lblConcluidas;
    private DefaultTableModel modeloTabelaHistorias;
    private DefaultTableModel modeloTabelaPersonagens;

    public DashboardVIEW() {
        setTitle("CriaTale - Sistema de Criação de Histórias");
        setSize(1100, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // 1. MENU LATERAL (SIDEBAR)
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(225, 225, 225));
        sidebar.setPreferredSize(new Dimension(240, 720));
        sidebar.setLayout(null);

        JPanel logoBox = new JPanel();
        logoBox.setBounds(20, 25, 25, 25);
        logoBox.setBackground(Color.DARK_GRAY);
        sidebar.add(logoBox);

        JLabel lblLogo = new JLabel("CRIATALE");
        lblLogo.setBounds(55, 25, 150, 25);
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        sidebar.add(lblLogo);

        JLabel lblMenuTitulo = new JLabel("MENU PRINCIPAL");
        lblMenuTitulo.setBounds(20, 85, 150, 15);
        lblMenuTitulo.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblMenuTitulo.setForeground(Color.GRAY);
        sidebar.add(lblMenuTitulo);

        JButton btnVisaoGeral = criarBotaoMenu("VISÃO GERAL", "01", 115);
        JButton btnMinhasHistorias = criarBotaoMenu("MINHAS HISTÓRIAS", "02", 160);
        JButton btnNovaHistoria = criarBotaoMenu("NOVA HISTÓRIA", "03", 205);
        JButton btnPersonagens = criarBotaoMenu("PERSONAGENS", "04", 250);
        JButton btnConfiguracoes = criarBotaoMenu("CONFIGURAÇÕES", "05", 295);

        JButton btnSair = new JButton("← SAIR DO SISTEMA");
        btnSair.setBounds(15, 620, 210, 35);
        btnSair.setBackground(new Color(200, 200, 200));
        btnSair.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btnSair.setFocusPainted(false);

        sidebar.add(btnVisaoGeral);
        sidebar.add(btnMinhasHistorias);
        sidebar.add(btnNovaHistoria);
        sidebar.add(btnPersonagens);
        sidebar.add(btnConfiguracoes);
        sidebar.add(btnSair);

        add(sidebar, BorderLayout.WEST);

        // 2. PAINEL DE CONTEÚDO COM CARDLAYOUT
        cardLayout = new CardLayout();
        panelConteudoPrincipal = new JPanel(cardLayout);

        panelConteudoPrincipal.add(criarPainelVisaoGeral(), "VISAOGERAL");
        panelConteudoPrincipal.add(criarPainelMinhasHistorias(), "MINHASHISTORIAS");
        panelConteudoPrincipal.add(criarPainelNovaHistoria(), "NOVAHISTORIA");
        panelConteudoPrincipal.add(criarPainelPersonagens(), "PERSONAGENS");
        panelConteudoPrincipal.add(criarPainelConfiguracoes(), "CONFIGURACOES");

        add(panelConteudoPrincipal, BorderLayout.CENTER);

        // Eventos de navegação entre as telas
        btnVisaoGeral.addActionListener(e -> {
            atualizarEstatisticas();
            cardLayout.show(panelConteudoPrincipal, "VISAOGERAL");
        });
        btnMinhasHistorias.addActionListener(e -> {
            atualizarTabelaHistorias();
            cardLayout.show(panelConteudoPrincipal, "MINHASHISTORIAS");
        });
        btnNovaHistoria.addActionListener(e -> cardLayout.show(panelConteudoPrincipal, "NOVAHISTORIA"));
        btnPersonagens.addActionListener(e -> {
            atualizarTabelaPersonagens();
            cardLayout.show(panelConteudoPrincipal, "PERSONAGENS");
        });
        btnConfiguracoes.addActionListener(e -> cardLayout.show(panelConteudoPrincipal, "CONFIGURACOES"));
        btnSair.addActionListener(e -> {
            new LoginVIEW().setVisible(true);
            dispose();
        });
    }

    private JButton criarBotaoMenu(String texto, String numero, int y) {
        JButton btn = new JButton(texto + "    " + numero);
        btn.setBounds(15, y, 210, 35);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btn.setBackground(new Color(240, 240, 240));
        btn.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        btn.setFocusPainted(false);
        return btn;
    }

    // --- TELA 01: VISÃO GERAL ---
    private JPanel criarPainelVisaoGeral() {
        JPanel painel = new JPanel(null);
        painel.setBackground(new Color(248, 249, 250));

        JLabel lblCabecalho = new JLabel("CRIATALE / VISÃO GERAL");
        lblCabecalho.setBounds(40, 25, 300, 15);
        lblCabecalho.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        lblCabecalho.setForeground(Color.GRAY);
        painel.add(lblCabecalho);

        JLabel lblTituloTela = new JLabel("VISÃO GERAL");
        lblTituloTela.setBounds(40, 45, 400, 30);
        lblTituloTela.setFont(new Font("Segoe UI", Font.BOLD, 22));
        painel.add(lblTituloTela);

        JLabel lblSaudacao = new JLabel("OLÁ, " + DadosMemoria.usuarioLogado);
        lblSaudacao.setBounds(40, 110, 500, 35);
        lblSaudacao.setFont(new Font("Segoe UI", Font.BOLD, 28));
        painel.add(lblSaudacao);

        JLabel lblSubSaud = new JLabel("Continue escrevendo ou comece uma nova história.");
        lblSubSaud.setBounds(40, 150, 400, 20);
        lblSubSaud.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        painel.add(lblSubSaud);

        JButton btnNovaHist = new JButton("+ NOVA HISTÓRIA");
        btnNovaHist.setBounds(650, 120, 160, 40);
        btnNovaHist.setBackground(new Color(70, 70, 70));
        btnNovaHist.setForeground(Color.WHITE);
        btnNovaHist.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btnNovaHist.setFocusPainted(false);
        btnNovaHist.addActionListener(e -> cardLayout.show(panelConteudoPrincipal, "NOVAHISTORIA"));
        painel.add(btnNovaHist);

        // Cards de Estatísticas
        lblTotalHistorias = new JLabel("02");
        lblEmAndamento = new JLabel("01");
        lblConcluidas = new JLabel("01");

        painel.add(criarCardEstatistica(40, 200, lblTotalHistorias, "HISTÓRIAS CRIADAS"));
        painel.add(criarCardEstatistica(300, 200, lblEmAndamento, "EM ANDAMENTO"));
        painel.add(criarCardEstatistica(560, 200, lblConcluidas, "CONCLUÍDAS"));

        // Seção Histórias Recentes (conforme Wireframe 2)
        JLabel lblRecentes = new JLabel("HISTÓRIAS RECENTES");
        lblRecentes.setBounds(40, 330, 300, 20);
        lblRecentes.setFont(new Font("Segoe UI", Font.BOLD, 13));
        painel.add(lblRecentes);

        JTextArea areaRecentes = new JTextArea();
        areaRecentes.setBounds(40, 360, 770, 250);
        areaRecentes.setEditable(false);
        areaRecentes.setBackground(new Color(238, 238, 238));
        areaRecentes.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        areaRecentes.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        areaRecentes.setText("• [01] A Espada de Cristal — Gênero: Fantasia (Concluída)\n\n" +
                             "• [02] Sinal de Órion — Gênero: Ficção Científica (Em Andamento)\n\n" +
                             "Dica: Clique em '+ NOVA HISTÓRIA' para gerar um novo enredo interativo!");
        painel.add(areaRecentes);

        atualizarEstatisticas();
        return painel;
    }

    private JPanel criarCardEstatistica(int x, int y, JLabel lblNumero, String titulo) {
        JPanel card = new JPanel(null);
        card.setBounds(x, y, 240, 100);
        card.setBackground(new Color(238, 238, 238));
        card.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        lblNumero.setBounds(20, 15, 100, 35);
        lblNumero.setFont(new Font("Segoe UI", Font.BOLD, 26));
        card.add(lblNumero);

        JLabel lblTit = new JLabel(titulo);
        lblTit.setBounds(20, 60, 180, 15);
        lblTit.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblTit.setForeground(Color.DARK_GRAY);
        card.add(lblTit);

        return card;
    }

    // --- TELA 02: MINHAS HISTÓRIAS ---
    private JPanel criarPainelMinhasHistorias() {
        JPanel painel = new JPanel(null);
        painel.setBackground(new Color(248, 249, 250));

        JLabel lblTitulo = new JLabel("MINHAS HISTÓRIAS");
        lblTitulo.setBounds(40, 40, 400, 30);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        painel.add(lblTitulo);

        String[] colunas = {"Título", "Gênero", "Status", "Resumo / Enredo"};
        modeloTabelaHistorias = new DefaultTableModel(colunas, 0);
        JTable tabela = new JTable(modeloTabelaHistorias);
        tabela.setRowHeight(28);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(40, 100, 770, 450);
        painel.add(scroll);

        JButton btnLer = new JButton("LER HISTÓRIA SELECIONADA");
        btnLer.setBounds(40, 570, 220, 40);
        btnLer.setBackground(new Color(70, 70, 70));
        btnLer.setForeground(Color.WHITE);
        btnLer.addActionListener(e -> {
            int linha = tabela.getSelectedRow();
            if (linha >= 0) {
                String titulo = modeloTabelaHistorias.getValueAt(linha, 0).toString();
                String enredo = modeloTabelaHistorias.getValueAt(linha, 3).toString();
                JOptionPane.showMessageDialog(this, enredo, "Leitura: " + titulo, JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma história na tabela para ler.");
            }
        });
        painel.add(btnLer);

        atualizarTabelaHistorias();
        return painel;
    }

    // --- TELA 03: NOVA HISTÓRIA (GERADOR INTERATIVO) ---
    private JPanel criarPainelNovaHistoria() {
    JPanel painel = new JPanel(null);
    painel.setBackground(new Color(248, 249, 250));

    JLabel lblCabecalho = new JLabel("CRIATALE / NOVA HISTÓRIA");
    lblCabecalho.setBounds(40, 25, 300, 15);
    lblCabecalho.setFont(new Font("Segoe UI", Font.PLAIN, 10));
    lblCabecalho.setForeground(Color.GRAY);
    painel.add(lblCabecalho);

    JLabel lblCrie = new JLabel("CRIE SUA HISTÓRIA");
    lblCrie.setBounds(40, 55, 400, 35);
    lblCrie.setFont(new Font("Segoe UI", Font.BOLD, 26));
    painel.add(lblCrie);

    JButton btnCancelar = new JButton("← VOLTAR");
    btnCancelar.setBounds(690, 60, 120, 35);
    btnCancelar.addActionListener(e -> cardLayout.show(panelConteudoPrincipal, "VISAOGERAL"));
    painel.add(btnCancelar);

    // Coluna 01: Configurações
    JPanel blocoConfig = new JPanel(null);
    blocoConfig.setBounds(40, 120, 360, 510);
    blocoConfig.setBackground(new Color(238, 238, 238));
    blocoConfig.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

    JLabel lblTituloHist = new JLabel("TÍTULO DA HISTÓRIA");
    lblTituloHist.setBounds(20, 25, 200, 15);
    lblTituloHist.setFont(new Font("Segoe UI", Font.BOLD, 10));
    blocoConfig.add(lblTituloHist);

    JTextField txtTitulo = new JTextField("Minha Nova Aventura");
    txtTitulo.setBounds(20, 45, 320, 35);
    blocoConfig.add(txtTitulo);

    JLabel lblGenero = new JLabel("GÊNERO");
    lblGenero.setBounds(20, 100, 100, 15);
    lblGenero.setFont(new Font("Segoe UI", Font.BOLD, 10));
    blocoConfig.add(lblGenero);

    JComboBox<String> cmbGenero = new JComboBox<>(new String[]{"Fantasia", "Ficção Científica", "Suspense", "Romance"});
    cmbGenero.setBounds(20, 120, 320, 35);
    blocoConfig.add(cmbGenero);

    JLabel lblPublico = new JLabel("PÚBLICO-ALVO");
    lblPublico.setBounds(20, 175, 100, 15);
    lblPublico.setFont(new Font("Segoe UI", Font.BOLD, 10));
    blocoConfig.add(lblPublico);

    JComboBox<String> cmbPublico = new JComboBox<>(new String[]{"Jovem Adulto", "Infantojuvenil", "Adulto"});
    cmbPublico.setBounds(20, 195, 320, 35);
    blocoConfig.add(lblPublico);

    JLabel lblExtensao = new JLabel("EXTENSÃO");
    lblExtensao.setBounds(20, 250, 100, 15);
    lblExtensao.setFont(new Font("Segoe UI", Font.BOLD, 10));
    blocoConfig.add(lblExtensao);

    JComboBox<String> cmbExtensao = new JComboBox<>(new String[]{"Conto Curto", "Novela", "Romance Completo"});
    cmbExtensao.setBounds(20, 270, 320, 35);
    blocoConfig.add(lblExtensao);

    painel.add(blocoConfig);

    // Coluna 02: Descreva sua ideia e gere o enredo
    JPanel blocoTexto = new JPanel(null);
    blocoTexto.setBounds(420, 120, 390, 510);
    blocoTexto.setBackground(new Color(238, 238, 238));
    blocoTexto.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

    JLabel lblDescricaoTitulo = new JLabel("DESCREVA SUA IDEIA / ENREDO GERADO");
    lblDescricaoTitulo.setBounds(20, 25, 280, 15);
    lblDescricaoTitulo.setFont(new Font("Segoe UI", Font.BOLD, 10));
    blocoTexto.add(lblDescricaoTitulo);

    JTextArea txtIdeia = new JTextArea();
    txtIdeia.setBounds(20, 50, 350, 320);
    txtIdeia.setLineWrap(true);
    txtIdeia.setWrapStyleWord(true);
    txtIdeia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    txtIdeia.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    blocoTexto.add(txtIdeia);

    // Botões de sugestão conforme wireframe 3
    JButton btnSugPersonagem = new JButton("+ Personagem");
    btnSugPersonagem.setBounds(20, 385, 110, 28);
    btnSugPersonagem.setFont(new Font("Segoe UI", Font.PLAIN, 10));
    btnSugPersonagem.addActionListener(e -> txtIdeia.append("\n[Personagem: Um viajante misterioso com um mapa antigo] "));
    blocoTexto.add(btnSugPersonagem);

    JButton btnSugCenario = new JButton("+ Cenário");
    btnSugCenario.setBounds(140, 385, 100, 28);
    btnSugCenario.setFont(new Font("Segoe UI", Font.PLAIN, 10));
    btnSugCenario.addActionListener(e -> txtIdeia.append("\n[Cenário: Uma biblioteca flutuante acima das nuvens] "));
    blocoTexto.add(btnSugCenario);

    JButton btnGerarSalvar = new JButton("GERAR E SALVAR HISTÓRIA →");
    btnGerarSalvar.setBounds(20, 435, 350, 45);
    btnGerarSalvar.setBackground(new Color(70, 70, 70));
    btnGerarSalvar.setForeground(Color.WHITE);
    btnGerarSalvar.setFont(new Font("Segoe UI", Font.BOLD, 11));
    btnGerarSalvar.addActionListener(e -> {
        String genero = cmbGenero.getSelectedItem().toString();
        String titulo = txtTitulo.getText().trim();
        if (titulo.isEmpty()) {
            titulo = "História Sem Título";
        }

        String baseIdeia = txtIdeia.getText().trim();
        String enredoGerado = "Gênero: " + genero + " (" + cmbExtensao.getSelectedItem() + ")\n"
                + "Premissa: Em um mundo marcado por desafios de " + genero.toLowerCase()
                + ", a jornada começa quando segredos ocultos vêm à tona. " + baseIdeia;

        txtIdeia.setText(enredoGerado);

        // Salva no MySQL usando HistoriaDAO em vez de DadosMemoria
        HistoriaDAO dao = new HistoriaDAO();
        boolean salvou = dao.salvarHistoria(
                titulo,
                genero,
                cmbPublico.getSelectedItem().toString(),
                cmbExtensao.getSelectedItem().toString(),
                "Em Andamento",
                enredoGerado
        );

        if (salvou) {
            atualizarEstatisticas();
            JOptionPane.showMessageDialog(this, "História '" + titulo + "' gerada e salva no banco de dados!");
        }
    });
    blocoTexto.add(btnGerarSalvar);

    painel.add(blocoTexto);
    return painel;
}

    // --- TELA 04: PERSONAGENS ---
    private JPanel criarPainelPersonagens() {
    JPanel painel = new JPanel(null);
    painel.setBackground(new Color(248, 249, 250));

    JLabel lblTitulo = new JLabel("PERSONAGENS CRIADOS");
    lblTitulo.setBounds(40, 40, 400, 30);
    lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
    painel.add(lblTitulo);

    String[] colunas = {"Nome", "Papel", "Descrição"};
    modeloTabelaPersonagens = new DefaultTableModel(colunas, 0);
    JTable tabela = new JTable(modeloTabelaPersonagens);
    tabela.setRowHeight(28);
    JScrollPane scroll = new JScrollPane(tabela);
    scroll.setBounds(40, 100, 770, 430);
    painel.add(scroll);

    JButton btnNovoPersonagem = new JButton("+ ADICIONAR PERSONAGEM");
    btnNovoPersonagem.setBounds(40, 550, 220, 40);
    btnNovoPersonagem.setBackground(new Color(70, 70, 70));
    btnNovoPersonagem.setForeground(Color.WHITE);
    btnNovoPersonagem.addActionListener(e -> {
        String nome = JOptionPane.showInputDialog(this, "Nome do Personagem:");
        if (nome != null && !nome.trim().isEmpty()) {
            String papel = JOptionPane.showInputDialog(this, "Papel (Ex: Protagonista, Vilão, Mentor):");
            String desc = JOptionPane.showInputDialog(this, "Breve descrição:");

            String papelFinal = (papel != null && !papel.trim().isEmpty()) ? papel.trim() : "Secundário";
            String descFinal = (desc != null) ? desc.trim() : "";

            HistoriaDAO dao = new HistoriaDAO();
            boolean salvou = dao.salvarPersonagem(nome.trim(), papelFinal, descFinal);
            if (salvou) {
                atualizarTabelaPersonagens();
                JOptionPane.showMessageDialog(this, "Personagem '" + nome.trim() + "' salvo no banco de dados!");
            }
        }
    });
    painel.add(btnNovoPersonagem);

    atualizarTabelaPersonagens();
    return painel;
}

    // --- TELA 05: CONFIGURAÇÕES ---
    private JPanel criarPainelConfiguracoes() {
    JPanel painel = new JPanel(null);
    painel.setBackground(new Color(248, 249, 250));

    JLabel lblTitulo = new JLabel("CONFIGURAÇÕES DO SISTEMA");
    lblTitulo.setBounds(40, 40, 400, 30);
    lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
    painel.add(lblTitulo);

    JLabel lblInfo = new JLabel("Usuário Atual: Escritor");
    lblInfo.setBounds(40, 110, 400, 25);
    lblInfo.setFont(new Font("Segoe UI", Font.BOLD, 14));
    painel.add(lblInfo);

    JCheckBox chkAutoSave = new JCheckBox("Ativar salvamento automático no banco de dados", true);
    chkAutoSave.setBounds(40, 155, 380, 25);
    chkAutoSave.setOpaque(false);
    painel.add(chkAutoSave);

    JCheckBox chkAltoContraste = new JCheckBox("Modo de acessibilidade (Alto Contraste)", false);
    chkAltoContraste.setBounds(40, 190, 380, 25);
    chkAltoContraste.setOpaque(false);
    painel.add(chkAltoContraste);

    JButton btnSalvarConf = new JButton("SALVAR PREFERÊNCIAS");
    btnSalvarConf.setBounds(40, 240, 220, 40);
    btnSalvarConf.setBackground(new Color(70, 70, 70));
    btnSalvarConf.setForeground(Color.WHITE);
    btnSalvarConf.addActionListener(e -> {
        if (chkAltoContraste.isSelected()) {
            painel.setBackground(Color.BLACK);
            lblTitulo.setForeground(Color.YELLOW);
            lblInfo.setForeground(Color.WHITE);
            chkAutoSave.setForeground(Color.WHITE);
            chkAltoContraste.setForeground(Color.WHITE);
        } else {
            painel.setBackground(new Color(248, 249, 250));
            lblTitulo.setForeground(Color.BLACK);
            lblInfo.setForeground(Color.BLACK);
            chkAutoSave.setForeground(Color.BLACK);
            chkAltoContraste.setForeground(Color.BLACK);
        }
        painel.repaint();
        JOptionPane.showMessageDialog(this, "Preferências atualizadas com sucesso!");
    });
    painel.add(btnSalvarConf);

    return painel;
}

    // Métodos auxiliares de sincronização entre telas
    private void atualizarEstatisticas() {
    HistoriaDAO dao = new HistoriaDAO();
    List<String[]> lista = dao.listarHistorias();

    int total = lista.size();
    int andamento = 0;
    int concluidas = 0;

    for (String[] h : lista) {
        String status = (h.length > 3 && h[3] != null) ? h[3].trim() : "";
        if ("Concluída".equalsIgnoreCase(status) || "Concluida".equalsIgnoreCase(status)) {
            concluidas++;
        } else {
            andamento++;
        }
    }

    lblTotalHistorias.setText(String.format("%02d", total));
    lblEmAndamento.setText(String.format("%02d", andamento));
    lblConcluidas.setText(String.format("%02d", concluidas));
}

    private void atualizarTabelaHistorias() {
    modeloTabelaHistorias.setRowCount(0);
    HistoriaDAO dao = new HistoriaDAO();
    for (String[] h : dao.listarHistorias()) {
        // h[0] = id | h[1] = titulo | h[2] = genero | h[3] = status | h[4] = enredo
        modeloTabelaHistorias.addRow(new Object[]{h[1], h[2], h[3], h[4]});
    }
}

    private void atualizarTabelaPersonagens() {
    modeloTabelaPersonagens.setRowCount(0);
    HistoriaDAO dao = new HistoriaDAO();
    for (String[] p : dao.listarPersonagens()) {
        modeloTabelaPersonagens.addRow(p);
    }
}
}
