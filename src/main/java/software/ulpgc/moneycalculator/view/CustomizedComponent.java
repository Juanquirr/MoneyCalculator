package software.ulpgc.moneycalculator.view;

import software.ulpgc.moneycalculator.model.Currency;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;

public class CustomizedComponent {

    private final Font customFont;

    public CustomizedComponent() {
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("LuckiestGuy-Regular.ttf")).deriveFont(12f);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load font", e);
        }
    }
    public JTextField customizeTextField(JTextField textField) {
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setBorder(null);
        textField.setColumns(20);
        textField.setFont(getCustomFont().deriveFont(18f));
        return textField;
    }
    public JButton customizeButton(JButton button) {
        button.setFont(getCustomFont().deriveFont(12f));
        button.setForeground(Color.black);
        button.setBackground(new Color(102, 150, 200));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(100, 35));
        return button;
    }

    public JComboBox<String> customizeComboBox(JComboBox<String> selector) {
        selector.setBackground(new Color(102, 150, 200));
        selector.setBorder(new LineBorder(Color.orange, 3));
        selector.setPreferredSize(new Dimension(370, 35));
        selector.setMaximumSize(new Dimension(370, 35));
        selector.setFont(getCustomFont().deriveFont(15f));
        selector.setMaximumRowCount(5);
        return selector;
    }
    public JLabel createTitle() {
        JLabel title = new JLabel("MONEY CALCULATOR");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(getCustomFont().deriveFont(45f));
        title.setForeground(new Color(202, 60, 1));
        return title;
    }

    public JLabel createAuthorName() {
        JLabel name = new JLabel("Juan Carlos Rodríguez Ramírez");
        name.setAlignmentX(Component.CENTER_ALIGNMENT);
        name.setFont(getCustomFont().deriveFont(14f));
        name.setForeground(new Color(70, 120, 201));
        return name;
    }

    public Font getCustomFont() {
        return customFont;
    }
}
