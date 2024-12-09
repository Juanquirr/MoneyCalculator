package software.ulpgc.moneycalculator.apps.windows;

import software.ulpgc.moneycalculator.control.Command;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.view.SwingMoneyDialog;
import software.ulpgc.moneycalculator.view.SwingMoneyDisplay;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SwingMainFrame extends JFrame {
    private final Map<String,Command> commands = new HashMap<>();
    private final SwingMoneyDialog moneyDialog;
    private final SwingMoneyDisplay moneyDisplay;

    public SwingMainFrame() throws HeadlessException {
        this.setLayout(new BorderLayout());
        this.add(createNorthPanel(), BorderLayout.NORTH);
        this.add(createCenterPanel(this.moneyDialog = createMoneyDialog(),
                this.moneyDisplay = createMoneyDisplay()), BorderLayout.CENTER);
        this.add(toolbar(), BorderLayout.SOUTH);
    }

    private Component createNorthPanel() {
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE,150));
        northPanel.setOpaque(false);

        northPanel.add(Box.createVerticalGlue());
        northPanel.add(new CustomizedComponent().createTitle());
        northPanel.add(Box.createVerticalGlue());
        northPanel.add(new CustomizedComponent().createAuthorName());
        northPanel.add(Box.createVerticalGlue());

        return northPanel;
    }

    private JPanel createCenterPanel(SwingMoneyDialog moneyDialog, SwingMoneyDisplay moneyDisplay) {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        centerPanel.setOpaque(false);

        centerPanel.add(moneyDialog);
        centerPanel.add(moneyDisplay);

        return centerPanel;
    }

    private SwingMoneyDialog createMoneyDialog() {
        FrequentButton frequentButton = createButtonsForFrequentUsedCurrency();

        frequentButton.gbp.addActionListener(_ -> commands.get("pound dialog").execute());
        frequentButton.usd.addActionListener(_ -> commands.get("dollar dialog").execute());
        frequentButton.eur.addActionListener(_ -> commands.get("euro dialog").execute());

        return new SwingMoneyDialog().addButtonsToToolbar(frequentButton.gbp(), frequentButton.usd(), frequentButton.eur());
    }

    private SwingMoneyDisplay createMoneyDisplay() {
        FrequentButton frequentButton = createButtonsForFrequentUsedCurrency();

        frequentButton.gbp().addActionListener(_ -> commands.get("pound display").execute());
        frequentButton.usd().addActionListener(_ -> commands.get("dollar display").execute());
        frequentButton.eur().addActionListener(_ -> commands.get("euro display").execute());

        return new SwingMoneyDisplay().addButtonsToToolbar(frequentButton.gbp(), frequentButton.usd(), frequentButton.eur());
    }

    private static FrequentButton createButtonsForFrequentUsedCurrency() {
        JButton gbp = new CustomizedComponent().customizeButton(new JButton("GBP"), 100, 35);
        JButton usd = new CustomizedComponent().customizeButton(new JButton("USD"), 100, 35);
        JButton eur = new CustomizedComponent().customizeButton(new JButton("EUR"), 100, 35);
        return new FrequentButton(gbp, usd, eur);
    }

    private record FrequentButton(JButton gbp, JButton usd, JButton eur) {
    }

    private Component toolbar() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setOpaque(false);

        JButton formatSwitchButton = new CustomizedComponent().customizeButton(new JButton("Change Format"), 300, 50);
        formatSwitchButton.addActionListener(_ -> commands.get("change format").execute());

        JButton exchangeRateButton = new CustomizedComponent().customizeButton(new JButton("Calculate"), 300, 50);
        exchangeRateButton.addActionListener(_ -> commands.get("exchange rate").execute());

        JButton clearField = new CustomizedComponent().customizeButton(new JButton("Clear"), 250, 50);
        clearField.addActionListener(_ -> commands.get("clear").execute());

        panel.add(formatSwitchButton);
        panel.add(exchangeRateButton);
        panel.add(clearField);
        return panel;
    }

    public SwingMainFrame add(Map<String, Command> commands) {
        for (String name : commands.keySet()) this.commands.put(name, commands.get(name));
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

    public SwingMainFrame initializeFrame() {
        this.setTitle("Money calculator");
        this.setSize(900,600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(60,51,154));
        return this;
    }
}
