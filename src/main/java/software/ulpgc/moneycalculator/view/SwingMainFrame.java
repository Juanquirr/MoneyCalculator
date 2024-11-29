package software.ulpgc.moneycalculator.view;

import software.ulpgc.moneycalculator.apps.windows.CustomCurrencyLoader;
import software.ulpgc.moneycalculator.apps.windows.FixerCurrencyLoader;
import software.ulpgc.moneycalculator.control.ExchangeMoneyCommand;
import software.ulpgc.moneycalculator.apps.mock.MockExchangeRateLoader;
import software.ulpgc.moneycalculator.control.Command;
import software.ulpgc.moneycalculator.io.MoneyDialog;
import software.ulpgc.moneycalculator.model.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwingMainFrame extends JFrame {
    private final Map<String,Command> commands = new HashMap<>();
    private MoneyDisplay moneyDisplay;
    private SwingMoneyDialog moneyDialogLeft;
    private SwingMoneyDialog moneyDialogRight;

    public static void main(String[] args) throws IOException {
        SwingMainFrame main = new SwingMainFrame();
        List<Currency> currencies = new FixerCurrencyLoader().load();
        Command command = new ExchangeMoneyCommand(
                main.getMoneyDialogLeft(),
                new MockExchangeRateLoader(),
                main.moneyDisplay());
        main.add("exchange money", command);
        main.setVisible(true);
    }

    public SwingMainFrame() throws HeadlessException, IOException {
        this.setTitle("Money calculator");
        this.setSize(1200,500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(new Color(60,51,154));
        this.add(createNorthPanel(), BorderLayout.NORTH);
        this.add(createCenterPanel(), BorderLayout.CENTER);
        this.add(toolbar(), BorderLayout.SOUTH);
    }

    private Component createNorthPanel() {
        JPanel northPanel = new JPanel();
        northPanel.setOpaque(false);
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.setBorder(new LineBorder(Color.RED, 2)); // RECUERDA BORRAR
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

    private JPanel createCenterPanel() throws IOException {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        centerPanel.setBorder(new LineBorder(Color.blue, 2)); // RECUERDA BORRAR
        centerPanel.setOpaque(false);

        this.moneyDialogLeft = (SwingMoneyDialog) createMoneyDialog().define(new CustomCurrencyLoader().load());
        this.moneyDialogRight = (SwingMoneyDialog) createMoneyDialog().define(new CustomCurrencyLoader().load());


        centerPanel.add(moneyDialogLeft);
        centerPanel.add(moneyDialogRight);

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
        button.addActionListener(e -> commands.get("Calculate").execute());

        panel.add(button);
        return panel;
    }

    private void add(String name, Command command) {
        commands.put(name, command);
    }

    private MoneyDisplay moneyDisplay() {
        return moneyDisplay;
    }

    public MoneyDialog getMoneyDialogLeft() {
        return moneyDialogLeft;
    }

    public MoneyDialog getMoneyDialogRight() {
        return moneyDialogRight;
    }
}
