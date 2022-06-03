/*
 * Created by JFormDesigner on Fri Jun 03 08:45:04 BRT 2022
 */

package petshop.views.RegistrarAtendimento;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class TelaRegistrarAtendimento extends JPanel {
    public TelaRegistrarAtendimento() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        comboBox1 = new JComboBox();
        comboBox2 = new JComboBox();
        label4 = new JLabel();
        button1 = new JButton();

        //======== this ========
        setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[193,fill]" +
            "[0,fill]" +
            "[193,fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- label1 ----
        label1.setText("Registrar Atendimento");
        label1.setFont(new Font("Serif", Font.BOLD, 24));
        add(label1, "cell 1 0");

        //---- label2 ----
        label2.setText("Atendimento");
        add(label2, "cell 0 2");

        //---- label3 ----
        label3.setText("Servi\u00e7o");
        add(label3, "cell 2 2");
        add(comboBox1, "cell 0 3");
        add(comboBox2, "cell 2 3");

        //---- label4 ----
        label4.setText("Valor R$50,00");
        label4.setHorizontalAlignment(SwingConstants.CENTER);
        label4.setFont(new Font("Serif", Font.BOLD, 20));
        add(label4, "cell 1 5");

        //---- button1 ----
        button1.setText("Registrar");
        add(button1, "cell 1 7");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JLabel label4;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
