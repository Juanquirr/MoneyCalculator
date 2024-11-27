package software.ulpgc.moneycalculator.view;

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

        JLabel title = createTitle();
        JLabel name = createAuthorName();

        northPanel.add(title);
        northPanel.add(Box.createVerticalStrut(10));
        northPanel.add(name);

        return northPanel;
    }

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        centerPanel.setBorder(new LineBorder(Color.blue, 2)); // RECUERDA BORRAR

//        centerPanel.add(Box.createVerticalStrut(200));
        centerPanel.add((Component) (this.moneyDialogLeft = createMoneyDialog()));
        centerPanel.add((Component) (this.moneyDialogRight = createMoneyDialog()));
//        centerPanel.add(Box.createVerticalStrut(50));
//        centerPanel.add(createMoneyDisplay());
//        centerPanel.add(Box.createVerticalStrut(50));

        // REFACTORIZA

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


    private static JLabel createTitle() {
        JLabel title = new JLabel("MONEY CALCULATOR");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(new Color(202, 60, 1));
        return title;
    }

    private static JLabel createAuthorName() {
        JLabel name = new JLabel("Juan Carlos Rodríguez Ramírez");
        name.setAlignmentX(Component.CENTER_ALIGNMENT);
        name.setFont(new Font("Arial", Font.BOLD, 12));
        name.setForeground(new Color(70, 120, 201));
        return name;
    }

    public MoneyDialog getMoneyDialogLeft() {
        return moneyDialogLeft;
    }

    public MoneyDialog getMoneyDialogRight() {
        return moneyDialogRight;
    }
}
