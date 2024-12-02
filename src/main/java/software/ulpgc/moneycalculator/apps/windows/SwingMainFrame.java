package software.ulpgc.moneycalculator.apps.windows;

import software.ulpgc.moneycalculator.control.Command;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.view.CustomizedComponent;
import software.ulpgc.moneycalculator.view.SwingMoneyDialog;
import software.ulpgc.moneycalculator.view.SwingMoneyDisplay;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SwingMainFrame extends JFrame {
    private final Map<String,Command> commands = new HashMap<>();
    private final SwingMoneyDialog moneyDialog;
    private final SwingMoneyDisplay moneyDisplay;

    public SwingMainFrame() throws HeadlessException, IOException {
        this.setTitle("Money calculator");
        this.setSize(900,600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(new Color(60,51,154));
        this.add(createNorthPanel(), BorderLayout.NORTH);
        this.add(createCenterPanel(this.moneyDialog = createMoneyDialog(),
                this.moneyDisplay = createMoneyDisplay()), BorderLayout.CENTER);
        this.add(toolbar(), BorderLayout.SOUTH);
    }

    private Component createNorthPanel() {
        JPanel northPanel = new JPanel();
        northPanel.setOpaque(false);
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE,150));

        JLabel title = new CustomizedComponent().createTitle();
        JLabel name = new CustomizedComponent().createAuthorName();

        northPanel.add(Box.createVerticalGlue());
        northPanel.add(title);
        northPanel.add(Box.createVerticalGlue());
        northPanel.add(name);
        northPanel.add(Box.createVerticalGlue());

        return northPanel;
    }

    private JPanel createCenterPanel(SwingMoneyDialog swingMoneyDialogLeft, SwingMoneyDisplay swingMoneyDialogRight) throws IOException {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        centerPanel.setOpaque(false);

        centerPanel.add(swingMoneyDialogLeft);
        centerPanel.add(swingMoneyDialogRight);

        return centerPanel;
    }

    private SwingMoneyDialog createMoneyDialog() {
        JButton gbp = new CustomizedComponent().customizeButton(new JButton("GBP"), 100, 35);
        JButton usd = new CustomizedComponent().customizeButton(new JButton("USD"), 100, 35);
        JButton eur = new CustomizedComponent().customizeButton(new JButton("EUR"), 100, 35);

        gbp.addActionListener(e -> commands.get("pound dialog").execute());
        usd.addActionListener(e -> commands.get("dollar dialog").execute());
        eur.addActionListener(e -> commands.get("euro dialog").execute());

        SwingMoneyDialog dialog = new SwingMoneyDialog();
        dialog.addButtonsToToolbar(gbp, usd, eur);
        return dialog;
    }

    private SwingMoneyDisplay createMoneyDisplay() {
        JButton gbp = new CustomizedComponent().customizeButton(new JButton("GBP"), 100, 35);
        JButton usd = new CustomizedComponent().customizeButton(new JButton("USD"), 100, 35);
        JButton eur = new CustomizedComponent().customizeButton(new JButton("EUR"), 100, 35);

        gbp.addActionListener(e -> commands.get("pound display").execute());
        usd.addActionListener(e -> commands.get("dollar display").execute());
        eur.addActionListener(e -> commands.get("euro display").execute());

        SwingMoneyDisplay display = new SwingMoneyDisplay();
        display.addButtonsToToolbar(gbp, usd, eur);
        return display;
    }

    private Component toolbar() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new FlowLayout());

        JButton formatSwitchButton = new CustomizedComponent().customizeButton(new JButton("Change Format"), 300, 50);
        formatSwitchButton.addActionListener(e -> commands.get("change format").execute());

        JButton exchangeRateButton = new CustomizedComponent().customizeButton(new JButton("Calculate"), 300, 50);
        exchangeRateButton.addActionListener(e -> commands.get("exchange rate").execute());

        JButton clearField = new CustomizedComponent().customizeButton(new JButton("Clear"), 250, 50);
        clearField.addActionListener(e -> commands.get("clear").execute());

        panel.add(formatSwitchButton);
        panel.add(exchangeRateButton);
        panel.add(clearField);
        return panel;
    }

    public SwingMainFrame add(Map<String, Command> commands) {
        for (String name : commands.keySet()) {
            this.commands.put(name, commands.get(name));
        }
        return this;
    }

    public SwingMoneyDialog getMoneyDialog() {
        return moneyDialog;
    }

    public SwingMoneyDisplay getMoneyDisplay() {
        return moneyDisplay;
    }

    public SwingMainFrame defineCurrencies(Map<String, Currency> load) {
        moneyDialog.define(load);
        moneyDisplay.define(load);
        return this;
    }
}
