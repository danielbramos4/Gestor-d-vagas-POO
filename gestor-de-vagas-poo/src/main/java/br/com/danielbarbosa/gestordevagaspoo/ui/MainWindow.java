package br.com.danielbarbosa.gestordevagaspoo.ui;

import br.com.danielbarbosa.gestordevagaspoo.modules.candidate.entities.CandidateEntity;
import br.com.danielbarbosa.gestordevagaspoo.modules.candidate.repositories.CandidateRepository;
import br.com.danielbarbosa.gestordevagaspoo.modules.candidate.usecases.CreateCandidateUseCase;
import br.com.danielbarbosa.gestordevagaspoo.modules.company.entities.CompanyEntity;
import br.com.danielbarbosa.gestordevagaspoo.modules.company.repositories.CompanyRepository;
import br.com.danielbarbosa.gestordevagaspoo.modules.company.usecases.CreateCompanyUseCase;
import br.com.danielbarbosa.gestordevagaspoo.modules.job.entities.JobEntity;
import br.com.danielbarbosa.gestordevagaspoo.modules.job.repositories.JobRepository;
import br.com.danielbarbosa.gestordevagaspoo.modules.job.usecases.CreateJobCompanyUseCase;
import org.springframework.stereotype.Component;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class MainWindow {

    private static final String PORTAL = "portal";
    private static final String CANDIDATE_FORM = "candidateForm";
    private static final String COMPANY_FORM = "companyForm";
    private static final String JOB_FORM = "jobForm";
    private static final String DASHBOARD = "dashboard";
    private static final String CANDIDATE_LIST = "candidateList";
    private static final String COMPANY_LIST = "companyList";

    private static final Color PAGE_BACKGROUND = new Color(239, 247, 252);
    private static final Color NAVY = new Color(9, 47, 91);
    private static final Color NAVY_DARK = new Color(2, 40, 70);
    private static final Color TEAL = new Color(0, 137, 132);
    private static final Color TEAL_DARK = new Color(0, 112, 108);
    private static final Color BLUE = new Color(0, 77, 194);
    private static final Color GREEN = new Color(40, 167, 69);
    private static final Color RED = new Color(220, 53, 69);
    private static final Color BORDER = new Color(218, 226, 235);
    private static final Color TEXT = new Color(20, 37, 63);
    private static final Color MUTED = new Color(93, 108, 128);

    private final CreateCandidateUseCase createCandidateUseCase;
    private final CreateCompanyUseCase createCompanyUseCase;
    private final CreateJobCompanyUseCase createJobCompanyUseCase;
    private final CandidateRepository candidateRepository;
    private final CompanyRepository companyRepository;
    private final JobRepository jobRepository;

    private JFrame frame;
    private JPanel root;
    private CardLayout cardLayout;
    private DefaultTableModel jobTableModel;
    private DefaultTableModel candidateTableModel;
    private DefaultTableModel companyTableModel;
    private JComboBox<CompanyItem> companyComboBox;

    public MainWindow(
            CreateCandidateUseCase createCandidateUseCase,
            CreateCompanyUseCase createCompanyUseCase,
            CreateJobCompanyUseCase createJobCompanyUseCase,
            CandidateRepository candidateRepository,
            CompanyRepository companyRepository,
            JobRepository jobRepository
    ) {
        this.createCandidateUseCase = createCandidateUseCase;
        this.createCompanyUseCase = createCompanyUseCase;
        this.createJobCompanyUseCase = createJobCompanyUseCase;
        this.candidateRepository = candidateRepository;
        this.companyRepository = companyRepository;
        this.jobRepository = jobRepository;
    }

    public void show() {
        frame = new JFrame("Portal Vagas+");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1280, 760));
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        root = new JPanel(cardLayout);
        root.add(createPortal(), PORTAL);
        root.add(createCandidateForm(), CANDIDATE_FORM);
        root.add(createCompanyForm(), COMPANY_FORM);
        root.add(createJobForm(), JOB_FORM);
        root.add(createDashboard(), DASHBOARD);
        root.add(createCandidateList(), CANDIDATE_LIST);
        root.add(createCompanyList(), COMPANY_LIST);

        frame.add(root);
        refreshAll();
        open(PORTAL);
        frame.setVisible(true);
    }

    private JPanel createPortal() {
        JPanel page = new JPanel(new BorderLayout());
        page.setBackground(PAGE_BACKGROUND);
        page.setBorder(BorderFactory.createEmptyBorder(16, 24, 24, 24));

        JLabel pageTitle = new JLabel("<html><span style='color:#092f5b'>PORTAL </span><span style='color:#008984'>VAGAS+</span></html>", SwingConstants.CENTER);
        pageTitle.setFont(new Font("Arial", Font.BOLD, 34));
        JLabel subtitle = new JLabel("Sistema de gestao de vagas para candidatos e empresas", SwingConstants.CENTER);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 18));
        subtitle.setForeground(TEXT);

        JPanel header = new JPanel(new GridLayout(2, 1));
        header.setOpaque(false);
        header.add(pageTitle);
        header.add(subtitle);

        JPanel window = whitePanel(new BorderLayout());
        window.add(topBar("Portal Vagas+", "Sobre o portal     Como funciona     Contato"), BorderLayout.NORTH);

        JPanel content = new JPanel(new GridLayout(1, 2, 28, 0));
        content.setOpaque(false);
        content.setBorder(BorderFactory.createEmptyBorder(34, 38, 24, 38));
        content.add(createPortalText());
        content.add(createIllustrationPanel());
        window.add(content, BorderLayout.CENTER);
        window.add(createStatsPanel(), BorderLayout.SOUTH);

        page.add(header, BorderLayout.NORTH);
        page.add(window, BorderLayout.CENTER);
        return page;
    }

    private JPanel createPortalText() {
        JPanel panel = new JPanel(new BorderLayout(0, 18));
        panel.setOpaque(false);

        JLabel title = new JLabel("<html>Gestao de vagas<br>para candidatos e empresas</html>");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(NAVY);

        JLabel copy = new JLabel("<html>Conecte talentos as melhores oportunidades<br>e encontre o profissional ideal para sua empresa.</html>");
        copy.setFont(new Font("Arial", Font.PLAIN, 16));
        copy.setForeground(MUTED);

        JPanel cards = new JPanel(new GridLayout(1, 2, 16, 0));
        cards.setOpaque(false);
        cards.add(roleCard("Sou candidato", "Crie seu perfil, envie curriculos e acompanhe oportunidades.", "Cadastrar candidato", TEAL, CANDIDATE_FORM));
        cards.add(roleCard("Sou empresa", "Publique vagas, organize processos e encontre talentos.", "Cadastrar empresa", BLUE, COMPANY_FORM));

        JButton enterButton = outlineButton("Entrar na minha conta");
        enterButton.addActionListener(event -> open(DASHBOARD));

        JPanel top = new JPanel(new GridLayout(2, 1, 0, 10));
        top.setOpaque(false);
        top.add(title);
        top.add(copy);

        JPanel bottom = new JPanel(new BorderLayout(0, 14));
        bottom.setOpaque(false);
        bottom.add(cards, BorderLayout.CENTER);
        bottom.add(enterButton, BorderLayout.SOUTH);

        panel.add(top, BorderLayout.NORTH);
        panel.add(bottom, BorderLayout.CENTER);
        return panel;
    }

    private JPanel roleCard(String title, String text, String buttonText, Color color, String screen) {
        JPanel card = whitePanel(new BorderLayout(0, 10));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(18, 18, 18, 18)
        ));

        JLabel icon = circleLabel(title.contains("candidato") ? "P" : "E", color, 58);
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(color);
        JLabel textLabel = new JLabel("<html><center>" + text + "</center></html>", SwingConstants.CENTER);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        textLabel.setForeground(MUTED);
        JButton button = primaryButton(buttonText, color);
        button.addActionListener(event -> open(screen));

        JPanel body = new JPanel(new GridLayout(3, 1, 0, 4));
        body.setOpaque(false);
        body.add(icon);
        body.add(titleLabel);
        body.add(textLabel);
        card.add(body, BorderLayout.CENTER);
        card.add(button, BorderLayout.SOUTH);
        return card;
    }

    private JPanel createIllustrationPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        JPanel illustration = new JPanel(new GridBagLayout());
        illustration.setPreferredSize(new Dimension(360, 280));
        illustration.setBackground(new Color(236, 246, 255));
        illustration.setBorder(BorderFactory.createLineBorder(new Color(205, 224, 245)));

        JLabel graphic = new JLabel("<html><center><span style='font-size:42px'>[CV]</span><br><br>Pessoas, empresas<br>e oportunidades conectadas</center></html>", SwingConstants.CENTER);
        graphic.setFont(new Font("Arial", Font.BOLD, 20));
        graphic.setForeground(NAVY);
        illustration.add(graphic);
        panel.add(illustration);
        return panel;
    }

    private JPanel createStatsPanel() {
        JPanel stats = new JPanel(new GridLayout(1, 3, 18, 0));
        stats.setOpaque(false);
        stats.setBorder(BorderFactory.createEmptyBorder(0, 36, 22, 36));
        stats.add(stat("+8 mil vagas", "Oportunidades abertas todos os dias"));
        stats.add(stat("+2 mil empresas", "Confiando para encontrar talentos"));
        stats.add(stat("Processos em tempo real", "Acompanhe cada etapa da selecao"));
        return stats;
    }

    private JPanel createCandidateForm() {
        return formWindow(
                "Portal Vagas+",
                "Cadastro de candidato",
                new String[]{"Dados pessoais", "Formacao", "Experiencias", "Concluir"},
                createCandidateFormFields()
        );
    }

    private JPanel createCandidateFormFields() {
        JTextField nameField = field("Joao da Silva");
        JTextField emailField = field("joao.silva@email.com");
        JTextField phoneField = field("(11) 98765-4321");
        JTextField areaField = field("Desenvolvimento de Software");
        JTextField usernameField = field("joaosilva");
        JTextField curriculumField = field("curriculo.pdf");
        JPasswordField passwordField = password("1234567890");
        JTextArea summaryArea = area("Resumo profissional");

        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        GridBagConstraints gbc = formGbc();

        addFormField(form, gbc, "Nome", nameField, 0, 0, 2);
        addFormField(form, gbc, "E-mail", emailField, 0, 1, 1);
        addFormField(form, gbc, "Telefone", phoneField, 1, 1, 1);
        addFormField(form, gbc, "Area de interesse", areaField, 0, 2, 2);
        addFormField(form, gbc, "Usuario", usernameField, 0, 3, 1);
        addFormField(form, gbc, "Curriculo", curriculumField, 1, 3, 1);
        addFormField(form, gbc, "Senha", passwordField, 0, 4, 2);
        addTextArea(form, gbc, "Resumo profissional", summaryArea, 0, 5, 2);

        JButton saveButton = primaryButton("Salvar cadastro", TEAL);
        JButton cancelButton = secondaryButton("Cancelar");
        JButton backButton = secondaryButton("Voltar");
        saveButton.addActionListener(event -> {
            try {
                CandidateEntity candidate = new CandidateEntity();
                candidate.setName(nameField.getText());
                candidate.setEmail(emailField.getText());
                candidate.setUsername(usernameField.getText());
                candidate.setPassword(new String(passwordField.getPassword()));
                candidate.setCurriculum(curriculumField.getText());
                candidate.setDescription(summaryArea.getText());
                createCandidateUseCase.execute(candidate);
                clearFields(nameField, emailField, phoneField, areaField, usernameField, curriculumField, passwordField);
                summaryArea.setText("");
                refreshAll();
                showSuccess("Cadastro salvo com sucesso.");
                open(CANDIDATE_LIST);
            } catch (RuntimeException exception) {
                showError(readableErrorMessage(exception));
            }
        });
        cancelButton.addActionListener(event -> open(PORTAL));
        backButton.addActionListener(event -> open(PORTAL));

        addActions(form, gbc, 6, saveButton, cancelButton, backButton);
        return form;
    }

    private JPanel createCompanyForm() {
        return formWindow(
                "Portal Vagas+",
                "Cadastro de empresa",
                new String[]{"Dados da empresa", "Dados de contato", "Concluir"},
                createCompanyFormFields()
        );
    }

    private JPanel createCompanyFormFields() {
        JTextField nameField = field("Tech Solutions Ltda.");
        JTextField cnpjField = field("12.345.678/0001-90");
        JTextField emailField = field("contato@techsolutions.com.br");
        JTextField areaField = field("Tecnologia da Informacao");
        JTextField usernameField = field("techsolutions");
        JTextField websiteField = field("https://techsolutions.com.br");
        JPasswordField passwordField = password("1234567890");
        JTextArea descriptionArea = area("Descricao da empresa");

        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        GridBagConstraints gbc = formGbc();

        addFormField(form, gbc, "Nome da empresa", nameField, 0, 0, 1);
        addFormField(form, gbc, "CNPJ", cnpjField, 1, 0, 1);
        addFormField(form, gbc, "E-mail", emailField, 0, 1, 2);
        addFormField(form, gbc, "Area de atuacao", areaField, 0, 2, 2);
        addFormField(form, gbc, "Usuario", usernameField, 0, 3, 1);
        addFormField(form, gbc, "Website", websiteField, 1, 3, 1);
        addFormField(form, gbc, "Senha", passwordField, 0, 4, 2);
        addTextArea(form, gbc, "Descricao da empresa", descriptionArea, 0, 5, 2);

        JButton saveButton = primaryButton("Salvar empresa", BLUE);
        JButton cancelButton = secondaryButton("Cancelar");
        JButton backButton = secondaryButton("Voltar");
        saveButton.addActionListener(event -> {
            try {
                CompanyEntity company = new CompanyEntity();
                company.setName(nameField.getText());
                company.setEmail(emailField.getText());
                company.setUsername(usernameField.getText());
                company.setPassword(new String(passwordField.getPassword()));
                company.setWebsite(websiteField.getText());
                company.setDescription(descriptionArea.getText());
                createCompanyUseCase.execute(company);
                clearFields(nameField, cnpjField, emailField, areaField, usernameField, websiteField, passwordField);
                descriptionArea.setText("");
                refreshAll();
                showSuccess("Empresa salva com sucesso.");
                open(JOB_FORM);
            } catch (RuntimeException exception) {
                showError(readableErrorMessage(exception));
            }
        });
        cancelButton.addActionListener(event -> open(PORTAL));
        backButton.addActionListener(event -> open(PORTAL));

        addActions(form, gbc, 6, saveButton, cancelButton, backButton);
        return form;
    }

    private JPanel formWindow(String brand, String title, String[] steps, JPanel form) {
        JPanel page = new JPanel(new BorderLayout());
        page.setBackground(PAGE_BACKGROUND);
        page.setBorder(BorderFactory.createEmptyBorder(24, 36, 24, 36));

        JPanel window = whitePanel(new BorderLayout());
        window.add(topBar(brand, ""), BorderLayout.NORTH);

        JPanel body = new JPanel(new BorderLayout(28, 0));
        body.setOpaque(false);
        body.setBorder(BorderFactory.createEmptyBorder(26, 26, 26, 26));
        body.add(stepsPanel(steps), BorderLayout.WEST);

        JPanel center = new JPanel(new BorderLayout(0, 18));
        center.setOpaque(false);
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setForeground(TEXT);
        center.add(titleLabel, BorderLayout.NORTH);
        center.add(form, BorderLayout.CENTER);
        body.add(center, BorderLayout.CENTER);
        window.add(body, BorderLayout.CENTER);

        page.add(window, BorderLayout.CENTER);
        return page;
    }

    private JPanel stepsPanel(String[] steps) {
        JPanel panel = new JPanel(new GridLayout(steps.length, 1, 0, 22));
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(180, 240));
        for (int i = 0; i < steps.length; i++) {
            String marker = i == 0 ? "*" : "-";
            JLabel label = new JLabel(marker + "  " + steps[i]);
            label.setFont(new Font("Arial", i == 0 ? Font.BOLD : Font.PLAIN, 14));
            label.setForeground(i == 0 ? TEAL : TEXT);
            panel.add(label);
        }
        return panel;
    }

    private JPanel createJobForm() {
        JPanel page = new JPanel(new BorderLayout());
        page.setBackground(PAGE_BACKGROUND);
        page.add(topBar("Portal Vagas+", ""), BorderLayout.NORTH);

        JPanel content = new JPanel(new GridBagLayout());
        content.setOpaque(false);
        JPanel card = whitePanel(new GridBagLayout());
        card.setPreferredSize(new Dimension(620, 390));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(28, 32, 28, 32)
        ));

        JTextField descriptionField = field("Ex: Desenvolvedor Full Stack");
        JTextField levelField = field("Ex: Pleno");
        JTextField benefitsField = field("Ex: Gympass");
        companyComboBox = new JComboBox<>();
        JButton saveButton = primaryButton("Cadastrar", TEAL);
        JButton backButton = secondaryButton("Ver painel");

        GridBagConstraints gbc = formGbc();
        JLabel title = new JLabel("Cadastro de vagas");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setForeground(TEXT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        card.add(title, gbc);
        addFormField(card, gbc, "Descricao da vaga", descriptionField, 0, 1, 2);
        addFormField(card, gbc, "Nivel", levelField, 0, 2, 2);
        addFormField(card, gbc, "Beneficios da vaga", benefitsField, 0, 3, 2);
        addComboField(card, gbc, "Empresa", companyComboBox, 0, 4, 2);

        saveButton.addActionListener(event -> {
            CompanyItem selectedCompany = (CompanyItem) companyComboBox.getSelectedItem();
            try {
                JobEntity job = new JobEntity();
                job.setDescription(descriptionField.getText());
                job.setLevel(levelField.getText());
                job.setBenefits(benefitsField.getText());
                job.setCompanyId(selectedCompany == null ? null : selectedCompany.id());
                createJobCompanyUseCase.execute(job);
                clearFields(descriptionField, levelField, benefitsField);
                refreshAll();
                showSuccess("Vaga cadastrada com sucesso.");
                open(DASHBOARD);
            } catch (RuntimeException exception) {
                showError(readableErrorMessage(exception));
            }
        });
        backButton.addActionListener(event -> open(DASHBOARD));
        addActions(card, gbc, 5, saveButton, backButton);

        content.add(card);
        page.add(content, BorderLayout.CENTER);
        return page;
    }

    private JPanel createDashboard() {
        jobTableModel = createTableModel("ID", "Vaga", "Empresa", "Status", "Candidatos");
        JTable table = table(jobTableModel);
        JButton deleteButton = dangerButton("Excluir");
        JButton newButton = primaryButton("+ Nova vaga", BLUE);
        deleteButton.addActionListener(event -> deleteJob(table));
        newButton.addActionListener(event -> open(JOB_FORM));
        return dashboardPage("Painel de vagas", table, newButton, deleteButton);
    }

    private JPanel createCandidateList() {
        candidateTableModel = createTableModel("ID", "Nome", "Usuario", "E-mail", "Curriculo");
        JTable table = table(candidateTableModel);
        JButton deleteButton = dangerButton("Excluir");
        JButton newButton = primaryButton("+ Novo candidato", TEAL);
        deleteButton.addActionListener(event -> deleteCandidate(table));
        newButton.addActionListener(event -> open(CANDIDATE_FORM));
        return dashboardPage("Candidatos", table, newButton, deleteButton);
    }

    private JPanel createCompanyList() {
        companyTableModel = createTableModel("ID", "Empresa", "Usuario", "E-mail", "Website");
        JTable table = table(companyTableModel);
        JButton deleteButton = dangerButton("Excluir");
        JButton newButton = primaryButton("+ Nova empresa", BLUE);
        deleteButton.addActionListener(event -> deleteCompany(table));
        newButton.addActionListener(event -> open(COMPANY_FORM));
        return dashboardPage("Empresas", table, newButton, deleteButton);
    }

    private JPanel dashboardPage(String title, JTable table, JButton primaryAction, JButton deleteAction) {
        JPanel page = new JPanel(new BorderLayout());
        page.setBackground(PAGE_BACKGROUND);

        JPanel sidebar = sidebar();
        JPanel main = new JPanel(new BorderLayout(0, 18));
        main.setBackground(Color.WHITE);
        main.setBorder(BorderFactory.createEmptyBorder(26, 28, 28, 28));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setForeground(TEXT);
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        actions.setOpaque(false);
        actions.add(primaryAction);
        actions.add(deleteAction);
        header.add(titleLabel, BorderLayout.WEST);
        header.add(actions, BorderLayout.EAST);

        main.add(header, BorderLayout.NORTH);
        main.add(new JScrollPane(table), BorderLayout.CENTER);
        main.add(new JLabel("Selecione um registro na tabela para excluir."), BorderLayout.SOUTH);

        page.add(sidebar, BorderLayout.WEST);
        page.add(main, BorderLayout.CENTER);
        return page;
    }

    private JPanel sidebar() {
        JPanel side = new JPanel(new BorderLayout());
        side.setBackground(NAVY_DARK);
        side.setPreferredSize(new Dimension(230, 0));
        side.setBorder(BorderFactory.createEmptyBorder(22, 14, 22, 14));

        JLabel logo = new JLabel("Portal Vagas+");
        logo.setFont(new Font("Arial", Font.BOLD, 18));
        logo.setForeground(Color.WHITE);
        JPanel menu = new JPanel(new GridLayout(6, 1, 0, 10));
        menu.setOpaque(false);
        menu.add(sideButton("Inicio", PORTAL));
        menu.add(sideButton("Candidatos", CANDIDATE_LIST));
        menu.add(sideButton("Empresas", COMPANY_LIST));
        menu.add(sideButton("Vagas", DASHBOARD));
        menu.add(sideButton("Nova vaga", JOB_FORM));
        menu.add(sideButton("Logout", PORTAL));

        JLabel user = new JLabel("<html><b>Administrador</b><br>admin@portalvagas.com</html>");
        user.setForeground(Color.WHITE);
        user.setFont(new Font("Arial", Font.PLAIN, 12));

        side.add(logo, BorderLayout.NORTH);
        side.add(menu, BorderLayout.CENTER);
        side.add(user, BorderLayout.SOUTH);
        return side;
    }

    private JButton sideButton(String text, String screen) {
        JButton button = new JButton(text);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBackground(NAVY_DARK);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addActionListener(event -> open(screen));
        return button;
    }

    private JPanel topBar(String title, String rightText) {
        JPanel bar = new JPanel(new BorderLayout());
        bar.setBackground(NAVY);
        bar.setBorder(BorderFactory.createEmptyBorder(12, 22, 12, 22));
        JLabel left = new JLabel(title);
        left.setFont(new Font("Arial", Font.BOLD, 17));
        left.setForeground(Color.WHITE);
        JLabel right = new JLabel(rightText);
        right.setFont(new Font("Arial", Font.BOLD, 12));
        right.setForeground(Color.WHITE);
        bar.add(left, BorderLayout.WEST);
        bar.add(right, BorderLayout.EAST);
        return bar;
    }

    private JPanel stat(String title, String text) {
        JPanel panel = whitePanel(new BorderLayout(8, 2));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(12, 16, 12, 16)
        ));
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 15));
        titleLabel.setForeground(TEAL_DARK);
        JLabel textLabel = new JLabel("<html>" + text + "</html>");
        textLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        textLabel.setForeground(MUTED);
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(textLabel, BorderLayout.CENTER);
        return panel;
    }

    private JLabel circleLabel(String text, Color color, int size) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(color);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 22));
        label.setPreferredSize(new Dimension(size, size));
        return label;
    }

    private JPanel whitePanel(BorderLayout layout) {
        JPanel panel = new JPanel(layout);
        panel.setBackground(Color.WHITE);
        return panel;
    }

    private JPanel whitePanel(GridBagLayout layout) {
        JPanel panel = new JPanel(layout);
        panel.setBackground(Color.WHITE);
        return panel;
    }

    private GridBagConstraints formGbc() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7, 7, 7, 7);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        return gbc;
    }

    private void addFormField(JPanel panel, GridBagConstraints gbc, String label, JTextField field, int x, int y, int width) {
        JPanel wrapper = new JPanel(new BorderLayout(0, 6));
        wrapper.setOpaque(false);
        wrapper.add(formLabel(label), BorderLayout.NORTH);
        wrapper.add(field, BorderLayout.CENTER);
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        panel.add(wrapper, gbc);
        gbc.gridwidth = 1;
    }

    private void addComboField(JPanel panel, GridBagConstraints gbc, String label, JComboBox<CompanyItem> combo, int x, int y, int width) {
        JPanel wrapper = new JPanel(new BorderLayout(0, 6));
        wrapper.setOpaque(false);
        wrapper.add(formLabel(label), BorderLayout.NORTH);
        wrapper.add(combo, BorderLayout.CENTER);
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        panel.add(wrapper, gbc);
        gbc.gridwidth = 1;
    }

    private void addTextArea(JPanel panel, GridBagConstraints gbc, String label, JTextArea area, int x, int y, int width) {
        JPanel wrapper = new JPanel(new BorderLayout(0, 6));
        wrapper.setOpaque(false);
        wrapper.add(formLabel(label), BorderLayout.NORTH);
        wrapper.add(new JScrollPane(area), BorderLayout.CENTER);
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.ipady = 60;
        panel.add(wrapper, gbc);
        gbc.gridwidth = 1;
        gbc.ipady = 0;
    }

    private void addActions(JPanel panel, GridBagConstraints gbc, int y, JButton... buttons) {
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        actions.setOpaque(false);
        for (JButton button : buttons) {
            actions.add(button);
        }
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        panel.add(actions, gbc);
        gbc.gridwidth = 1;
    }

    private JLabel formLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 13));
        label.setForeground(TEXT);
        return label;
    }

    private JTextField field(String placeholder) {
        JTextField field = new PlaceholderTextField(placeholder);
        field.setPreferredSize(new Dimension(240, 36));
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(0, 10, 0, 10)
        ));
        return field;
    }

    private JPasswordField password(String placeholder) {
        JPasswordField field = new PlaceholderPasswordField(placeholder);
        field.setPreferredSize(new Dimension(240, 36));
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(0, 10, 0, 10)
        ));
        return field;
    }

    private JTextArea area(String placeholder) {
        JTextArea area = new PlaceholderTextArea(placeholder);
        area.setFont(new Font("Arial", Font.PLAIN, 14));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        return area;
    }

    private JButton primaryButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(160, 36));
        return button;
    }

    private JButton outlineButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.WHITE);
        button.setForeground(BLUE);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setBorder(BorderFactory.createLineBorder(BLUE));
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    private JButton secondaryButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.WHITE);
        button.setForeground(TEXT);
        button.setFont(new Font("Arial", Font.PLAIN, 13));
        button.setBorder(BorderFactory.createLineBorder(BORDER));
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(120, 36));
        return button;
    }

    private JButton dangerButton(String text) {
        JButton button = secondaryButton(text);
        button.setForeground(RED);
        button.setBorder(BorderFactory.createLineBorder(RED));
        return button;
    }

    private JTable table(DefaultTableModel model) {
        JTable table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(42);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(234, 237, 242));
        table.setGridColor(new Color(228, 232, 238));
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        table.removeColumn(table.getColumnModel().getColumn(0));
        return table;
    }

    private DefaultTableModel createTableModel(String... columns) {
        return new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    private void open(String screen) {
        refreshAll();
        cardLayout.show(root, screen);
    }

    private void refreshAll() {
        refreshJobs();
        refreshCandidates();
        refreshCompanies();
        refreshCompanyComboBox();
    }

    private void refreshJobs() {
        if (jobTableModel == null) {
            return;
        }
        jobTableModel.setRowCount(0);
        int index = 0;
        for (JobEntity job : jobRepository.findAll()) {
            String companyName = job.getCompanyEntity() == null ? "" : job.getCompanyEntity().getName();
            jobTableModel.addRow(new Object[]{
                    job.getId(),
                    job.getDescription(),
                    companyName,
                    index % 3 == 0 ? "Em analise" : "Ativa",
                    candidateRepository.count()
            });
            index++;
        }
    }

    private void refreshCandidates() {
        if (candidateTableModel == null) {
            return;
        }
        candidateTableModel.setRowCount(0);
        for (CandidateEntity candidate : candidateRepository.findAll()) {
            candidateTableModel.addRow(new Object[]{
                    candidate.getId(),
                    candidate.getName(),
                    candidate.getUsername(),
                    candidate.getEmail(),
                    candidate.getCurriculum()
            });
        }
    }

    private void refreshCompanies() {
        if (companyTableModel == null) {
            return;
        }
        companyTableModel.setRowCount(0);
        for (CompanyEntity company : companyRepository.findAll()) {
            companyTableModel.addRow(new Object[]{
                    company.getId(),
                    company.getName(),
                    company.getUsername(),
                    company.getEmail(),
                    company.getWebsite()
            });
        }
    }

    private void refreshCompanyComboBox() {
        if (companyComboBox == null) {
            return;
        }
        companyComboBox.removeAllItems();
        for (CompanyEntity company : companyRepository.findAll()) {
            companyComboBox.addItem(new CompanyItem(company.getId(), company.getName()));
        }
    }

    private void deleteJob(JTable table) {
        UUID id = selectedId(table);
        if (id == null || !confirm("Deseja excluir a vaga selecionada?")) {
            return;
        }
        jobRepository.deleteById(id);
        refreshAll();
        showSuccess("Vaga excluida com sucesso.");
    }

    private void deleteCandidate(JTable table) {
        UUID id = selectedId(table);
        if (id == null || !confirm("Deseja excluir o candidato selecionado?")) {
            return;
        }
        candidateRepository.deleteById(id);
        refreshAll();
        showSuccess("Candidato excluido com sucesso.");
    }

    private void deleteCompany(JTable table) {
        UUID id = selectedId(table);
        if (id == null || !confirm("Deseja excluir a empresa selecionada? As vagas vinculadas tambem serao removidas.")) {
            return;
        }
        List<JobEntity> jobsToDelete = new ArrayList<>();
        for (JobEntity job : jobRepository.findAll()) {
            if (id.equals(job.getCompanyId())) {
                jobsToDelete.add(job);
            }
        }
        jobRepository.deleteAll(jobsToDelete);
        companyRepository.deleteById(id);
        refreshAll();
        showSuccess("Empresa excluida com sucesso.");
    }

    private UUID selectedId(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            showError("Selecione um registro na tabela.");
            return null;
        }
        int modelRow = table.convertRowIndexToModel(selectedRow);
        return (UUID) table.getModel().getValueAt(modelRow, 0);
    }

    private boolean confirm(String message) {
        return JOptionPane.showConfirmDialog(frame, message, "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    private void clearFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }

    private void showSuccess(String message) {
        JOptionPane.showMessageDialog(frame, message, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(frame, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private String readableErrorMessage(RuntimeException exception) {
        Throwable cause = exception;
        while (cause.getCause() != null) {
            cause = cause.getCause();
        }
        if (cause.getMessage() != null && !cause.getMessage().isBlank()) {
            return cause.getMessage();
        }
        return exception.getMessage() == null ? "Nao foi possivel concluir a operacao." : exception.getMessage();
    }

    private record CompanyItem(UUID id, String name) {
        @Override
        public String toString() {
            return name;
        }
    }

    private static class PlaceholderTextField extends JTextField {
        private final String placeholder;

        PlaceholderTextField(String placeholder) {
            this.placeholder = placeholder;
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            if (!getText().isEmpty()) {
                return;
            }
            Graphics2D graphics2D = (Graphics2D) graphics.create();
            graphics2D.setColor(new Color(145, 154, 168));
            graphics2D.setFont(getFont());
            graphics2D.drawString(placeholder, 10, getHeight() / 2 + graphics2D.getFontMetrics().getAscent() / 2 - 2);
            graphics2D.dispose();
        }
    }

    private static class PlaceholderPasswordField extends JPasswordField {
        private final String placeholder;

        PlaceholderPasswordField(String placeholder) {
            this.placeholder = placeholder;
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            if (getPassword().length > 0) {
                return;
            }
            Graphics2D graphics2D = (Graphics2D) graphics.create();
            graphics2D.setColor(new Color(145, 154, 168));
            graphics2D.setFont(getFont());
            graphics2D.drawString(placeholder, 10, getHeight() / 2 + graphics2D.getFontMetrics().getAscent() / 2 - 2);
            graphics2D.dispose();
        }
    }

    private static class PlaceholderTextArea extends JTextArea {
        private final String placeholder;

        PlaceholderTextArea(String placeholder) {
            this.placeholder = placeholder;
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            if (!getText().isEmpty()) {
                return;
            }
            Graphics2D graphics2D = (Graphics2D) graphics.create();
            graphics2D.setColor(new Color(145, 154, 168));
            graphics2D.setFont(getFont());
            graphics2D.drawString(placeholder, 10, 24);
            graphics2D.dispose();
        }
    }
}
