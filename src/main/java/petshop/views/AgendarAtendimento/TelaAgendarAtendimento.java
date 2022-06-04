/*
 * Created by JFormDesigner on Fri Jun 03 09:06:54 BRT 2022
 */

package petshop.views.AgendarAtendimento;

import java.awt.*;
import javax.swing.*;

import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class TelaAgendarAtendimento extends JPanel {
    public TelaAgendarAtendimento() {
        initComponents();
    }

    private void initComponents() {
        DatePickerSettings dateSettings = new DatePickerSettings();
        dateSettings.setAllowKeyboardEditing(false);

        final DateTimePicker dataTeste = new DateTimePicker(dateSettings, null);

        dataTeste.setBounds(2, 3, 540, 45);
        dataTeste.setBackground(new java.awt.Color(60, 63, 65));
        dataTeste.setForeground(new java.awt.Color(187, 187, 187));



        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        comboBox1 = new JComboBox();
        label4 = new JLabel();
        label5 = new JLabel();
        comboBox3 = new JComboBox();
        comboBox4 = new JComboBox();
        button1 = new JButton();

        //======== this ========
        setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[193,grow,fill]" +
            "[fill]" +
            "[193,grow,fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- label1 ----
        label1.setText("Agendar Atendimento");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font(Font.SERIF, Font.BOLD, 24));
        add(label1, "cell 0 0 3 1,alignx center,growx 0");

        //---- label2 ----
        label2.setText("Selecionar pet");
        add(label2, "cell 0 2");

        //---- label3 ----
        label3.setText("Data de atendimento");
        add(label3, "cell 2 2");
        add(comboBox1, "cell 0 3,growx");

        //---- label4 ----
        label4.setText("Selecionar hor\u00e1rio");
        add(label4, "cell 0 5");

        //---- label5 ----
        label5.setText("Servi\u00e7o");
        add(label5, "cell 2 5");
        add(comboBox3, "cell 0 6,growx");
        add(comboBox4, "cell 2 6");

        //---- button1 ----
        button1.setText("Agendar");
        add(button1, "cell 0 8 3 1,alignx center,growx 0");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        add(dataTeste, "cell 2 3");
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JComboBox comboBox1;
    private JLabel label4;
    private JLabel label5;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
