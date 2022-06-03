/*
 * Created by JFormDesigner on Fri Jun 03 20:25:07 BRT 2022
 */

package petshop.views.CadastrarPet;

import java.awt.*;
import javax.swing.*;

import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class CadastrarPet extends JPanel {
    public CadastrarPet() {
        initComponents();
    }

    private void initComponents() {
        DatePickerSettings dateSettings = new DatePickerSettings();
        dateSettings.setAllowKeyboardEditing(false);

        final DateTimePicker dataTeste = new DateTimePicker(dateSettings, null);

        dataTeste.setBounds(2, 3, 500, 45);


        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        label5 = new JLabel();
        textField1 = new JTextField();
        label3 = new JLabel();
        label7 = new JLabel();
        textField3 = new JTextField();
        textField5 = new JTextField();
        label4 = new JLabel();
        comboBox1 = new JComboBox();
        button1 = new JButton();

        //======== this ========
        setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[grow]" +
            "[fill]" +
            "[grow,fill]",
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
            "[]" +
            "[]"));

        //---- label1 ----
        label1.setText("Cadastrar pet");
        label1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        add(label1, "cell 0 0 3 1,alignx center,growx 0");

        //---- label2 ----
        label2.setText("Nome do pet");
        add(label2, "cell 0 2");

        //---- label5 ----
        label5.setText("Data de nascimento");
        add(label5, "cell 2 2");
        add(textField1, "cell 0 3,growx");

        //---- label3 ----
        label3.setText("Nome do dono");
        add(label3, "cell 0 4");

        //---- label7 ----
        label7.setText("Ra\u00e7a");
        add(label7, "cell 2 4");
        add(textField3, "cell 0 5,growx");
        add(textField5, "cell 2 5,growx");

        //---- label4 ----
        label4.setText("Animal");
        add(label4, "cell 0 6");
        add(comboBox1, "cell 0 7,growx");

        //---- button1 ----
        button1.setText("Cadastrar");
        add(button1, "cell 0 11 3 1,alignx center,growx 0,wmin 150");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JLabel label5;
    private JTextField textField1;
    private JLabel label3;
    private JLabel label7;
    private JTextField textField3;
    private JTextField textField5;
    private JLabel label4;
    private JComboBox comboBox1;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
