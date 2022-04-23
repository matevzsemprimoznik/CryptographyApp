package views.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FormRow extends JPanel{
    public FormRow(String labelText, JComponent component, JComponent rightHelper) {
        createFromRow(labelText, component, rightHelper);
    }

    public FormRow(String labelText, JComponent component) {
        createFromRow(labelText, component, null);
    }

    private void createFromRow(String labelText, JComponent component, JComponent rightHelper){
        this.setMaximumSize(new Dimension(8000, 100));
        BoxLayout bl = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(bl);
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Dialog", Font.BOLD, 14));
        JPanel labelthis = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelthis.setMaximumSize(new Dimension(8000, 20));
        labelthis.add(label);
        JPanel inputthis = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputthis.setBorder(new EmptyBorder(0, 0, 20, 0));
        inputthis.add(component);
        if(rightHelper != null)
            inputthis.add(rightHelper);
        this.add(labelthis);
        this.add(inputthis);
    }

}
