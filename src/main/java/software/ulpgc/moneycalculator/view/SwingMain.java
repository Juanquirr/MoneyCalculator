package software.ulpgc.moneycalculator.view;

import software.ulpgc.moneycalculator.apps.windows.CustomizedComponent;
import software.ulpgc.moneycalculator.apps.windows.FixerCurrencyLoader;
import software.ulpgc.moneycalculator.control.ExchangeMoneyCommand;
import software.ulpgc.moneycalculator.io.MockExchangeRateLoader;
import software.ulpgc.moneycalculator.control.Command;
import software.ulpgc.moneycalculator.model.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwingMain extends JFrame {
    private final Map<String,Command> commands = new HashMap<>();
    private MoneyDisplay moneyDisplay;
    private MoneyDialog moneyDialogLeft;
    private MoneyDialog moneyDialogRight;
    private CurrencyDialog currencyDialog;

    public static void main(String[] args) {
        SwingMain main = new SwingMain();
        List<Currency> currencies = new FixerCurrencyLoader().load();
        Command command = new ExchangeMoneyCommand(
                main.moneyDialogLeft.define(currencies),
                main.moneyDialogRight.define(currencies),
                new MockExchangeRateLoader(),
                main.moneyDisplay());
        main.add("exchange money", command);
        main.setVisible(true);
    }

    public SwingMain() throws HeadlessException {
        this.setTitle("Money calculator");
        this.setSize(1000,600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(new Color(71,75,78));
        this.add(createNorthPanel(), BorderLayout.NORTH);
        this.add(Box.createVerticalStrut(100));
        this.add(createCenterPanel(), BorderLayout.CENTER);
        this.add(toolbar(), BorderLayout.SOUTH);
    }

    private Component createNorthPanel() {
        JPanel northPanel = new JPanel();
        northPanel.setOpaque(false);
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.setBorder(new LineBorder(Color.RED, 2)); // RECUERDA BORRAR
        northPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE,200));

        JLabel title = new CustomizedComponent().createTitle();
        JLabel name = new CustomizedComponent().createAuthorName();

        northPanel.add(Box.createVerticalGlue());
        northPanel.add(title);
        northPanel.add(Box.createVerticalGlue());
        northPanel.add(name);
        northPanel.add(Box.createVerticalGlue());

        return northPanel;
    }

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        centerPanel.setBorder(new LineBorder(Color.blue, 2)); // RECUERDA BORRAR

        centerPanel.add((Component) (this.moneyDialogLeft = createMoneyDialog()));
        centerPanel.add((Component) (this.moneyDialogRight = createMoneyDialog()));
//        centerPanel.add(createMoneyDisplay());
        return centerPanel;
    }

    private SwingMoneyDialog createMoneyDialog() {
        //        dialog.getAmountField().addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                super.keyTyped(e);
//                SwingUtilities.invokeLater(() -> commands.get("exchange money").execute());
//            }
//        });
        return new SwingMoneyDialog();
    }

    private Component createCurrencyDialog() {
        SwingCurrencyDialog dialog = new SwingCurrencyDialog();
        this.currencyDialog = dialog;
        return dialog;
    }

    private Component createMoneyDisplay() {
        SwingMoneyDisplay display = new SwingMoneyDisplay();
        this.moneyDisplay = display;
        return display;
    }

    private Component toolbar() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new FlowLayout());

        JButton button = new JButton("Calculate");
        button.addActionListener(e -> commands.get("exchange money").execute());

        panel.add(button);
        return panel;
    }

    private void add(String name, Command command) {
        commands.put(name, command);
    }

    private MoneyDisplay moneyDisplay() {
        return moneyDisplay;
    }

    private CurrencyDialog currencyDialog() {
        return currencyDialog;
    }

    public MoneyDialog getMoneyDialogLeft() {
        return moneyDialogLeft;
    }

    public MoneyDialog getMoneyDialogRight() {
        return moneyDialogRight;
    }
}
