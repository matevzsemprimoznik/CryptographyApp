package views.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FormRow{
    private String labelText;
    private JComponent component;
    private JComponent rightHelper;

    public FormRow(String labelText, JComponent component, JComponent rightHelper) {
        this.labelText = labelText;
        this.component = component;
        this.rightHelper = rightHelper;
    }

    public JPanel create(){
        JPanel panel = new JPanel();
        panel.setMaximumSize(new Dimension(8000, 100));
        BoxLayout bl = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(bl);
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Dialog", Font.BOLD, 14));
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelPanel.setMaximumSize(new Dimension(8000, 20));
        labelPanel.add(label);
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
        inputPanel.add(component);
        if(rightHelper != null)
            inputPanel.add(rightHelper);
        panel.add(labelPanel);
        panel.add(inputPanel);
        return panel;
    }
}
