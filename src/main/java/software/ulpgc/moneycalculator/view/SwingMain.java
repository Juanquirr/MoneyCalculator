package software.ulpgc.moneycalculator.view;

import software.ulpgc.moneycalculator.apps.windows.FixerCurrencyLoader;
import software.ulpgc.moneycalculator.io.MockExchangeRateLoader;
import software.ulpgc.moneycalculator.control.Command;
import software.ulpgc.moneycalculator.model.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwingMain extends JFrame {
    private final Map<String,Command> commands = new HashMap<>();
    private MoneyDisplay moneyDisplay;
    private MoneyDialog moneyDialog;
    private CurrencyDialog currencyDialog;

    public static void main(String[] args) {
        SwingMain main = new SwingMain();
        List<Currency> currencies = new FixerCurrencyLoader().load();
        Command command = new ExchangeMoneyCommand(
                main.moneyDialog().define(currencies),
                main.currencyDialog().define(currencies),
                new MockExchangeRateLoader(),
                main.moneyDisplay());
        main.add("exchange money", command);
        main.setVisible(true);
    }

    public SwingMain() throws HeadlessException {
        this.setTitle("Money calculator");
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(createNorthPanel(), BorderLayout.NORTH);
        this.add(createCenterPanel(), BorderLayout.CENTER);
        this.add(toolbar(), BorderLayout.SOUTH);
    }

    private Component createNorthPanel() {
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.setBorder(new LineBorder(Color.RED, 2)); // RECUERDA BORRAR

        JLabel title = createTitle();
        JLabel name = createAuthorName();

        northPanel.add(title);
        northPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        northPanel.add(name);

        return northPanel;
    }

    private Component createCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(new LineBorder(Color.blue, 2)); // RECUERDA BORRAR

        centerPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        centerPanel.add(createMoneyDialog());
        centerPanel.add(createCurrencyDialog());
        centerPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        centerPanel.add(createMoneyDisplay());
        centerPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        return centerPanel;
    }

    private Component createMoneyDialog() {
        SwingMoneyDialog dialog = new SwingMoneyDialog();
        this.moneyDialog = dialog;
//        dialog.getAmountField().addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                super.keyTyped(e);
//                SwingUtilities.invokeLater(() -> commands.get("Calculate"));
//            }
//        });
        return dialog;
    }

    private Component createCurrencyDialog() {

        SwingCurrencyDialog dialog = new SwingCurrencyDialog("To: ");
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
        panel.setLayout(new FlowLayout());
        panel.setBorder(new LineBorder(Color.RED, 2)); // RECUERDA BORRAR

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

    private MoneyDialog moneyDialog() {
        return moneyDialog;
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
}
