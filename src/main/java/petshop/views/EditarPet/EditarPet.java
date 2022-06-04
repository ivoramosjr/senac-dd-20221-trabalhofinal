/*
 * Created by JFormDesigner on Fri Jun 03 21:02:05 BRT 2022
 */

package petshop.views.EditarPet;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class EditarPet extends JPanel {
    public EditarPet() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        this2 = new JPanel();
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

        //======== this2 ========
        {
            this2.setLayout(new MigLayout(
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
            label1.setText("Editar pet");
            label1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
            this2.add(label1, "cell 0 0 3 1,alignx center,growx 0");

            //---- label2 ----
            label2.setText("Nome do pet");
            this2.add(label2, "cell 0 2");

            //---- label5 ----
            label5.setText("Data de nascimento");
            this2.add(label5, "cell 2 2");
            this2.add(textField1, "cell 0 3,growx");

            //---- label3 ----
            label3.setText("Nome do dono");
            this2.add(label3, "cell 0 4");

            //---- label7 ----
            label7.setText("Ra\u00e7a");
            this2.add(label7, "cell 2 4");
            this2.add(textField3, "cell 0 5,growx");
            this2.add(textField5, "cell 2 5,growx");

            //---- label4 ----
            label4.setText("Animal");
            this2.add(label4, "cell 0 6");
            this2.add(comboBox1, "cell 0 7,growx");

            //---- button1 ----
            button1.setText("Editar");
            this2.add(button1, "cell 0 11 3 1,alignx center,growx 0,wmin 150");
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel this2;
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
