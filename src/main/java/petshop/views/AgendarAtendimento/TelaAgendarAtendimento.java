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

        data = new DateTimePicker(dateSettings, null);

        data.setBounds(2, 3, 540, 45);
        data.setBackground(new java.awt.Color(60, 63, 65));
        data.setForeground(new java.awt.Color(187, 187, 187));



        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        comboPet = new JComboBox();
        label5 = new JLabel();
        comboBoxServico = new JComboBox();
        buttonAgendar = new JButton();

        //======== this ========
        setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[193,grow,fill]" +
            "[fill]" +
            "[193,grow,fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
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
        label2.setText("Selecione um Pet");
        add(label2, "cell 0 2");

        //---- label3 ----
        label3.setText("Data de atendimento");
        add(label3, "cell 2 2");

        //---- label4 ----
        label4.setText("Hora atendimento");
        add(label4, "cell 2 2");
        add(comboPet, "cell 0 3,growx");

        //---- label5 ----
        label5.setText("Selecione um Servi\u00e7o");
        add(label5, "cell 0 5");
        add(comboBoxServico, "cell 0 6");

        //---- buttonAgendar ----
        buttonAgendar.setText("Agendar");
        add(buttonAgendar, "cell 0 9 4 1,align center bottom,grow 0 0");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        add(data, "cell 2 3");
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JComboBox comboPet;
    private JLabel label5;
    private JComboBox comboBoxServico;
    private JButton buttonAgendar;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    DateTimePicker data;
}
