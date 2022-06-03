/*
 * Created by JFormDesigner on Fri Jun 03 08:56:02 BRT 2022
 */

package petshop.views.CriarServico;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class CriarServico extends JPanel {
    public CriarServico() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        vSpacer1 = new JPanel(null);
        label2 = new JLabel();
        label3 = new JLabel();
        textField1 = new JTextField();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        label4 = new JLabel();
        textField2 = new JTextField();
        vSpacer2 = new JPanel(null);
        button1 = new JButton();

        //======== this ========
        setLayout(new MigLayout(
            "hidemode 3,alignx center",
            // columns
            "[0,fill]" +
            "[104,grow,fill]" +
            "[fill]" +
            "[150,grow,fill]" +
            "[fill]",
            // rows
            "[]" +
            "[43,fill]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[fill]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- label1 ----
        label1.setText("Criar Servi\u00e7o");
        label1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        add(label1, "cell 0 0 4 1,alignx center,growx 0");
        add(vSpacer1, "cell 2 1 1 2");

        //---- label2 ----
        label2.setText("Nome do servi\u00e7o");
        add(label2, "cell 1 2");

        //---- label3 ----
        label3.setText("Descri\u00e7\u00e3o do servi\u00e7o");
        add(label3, "cell 3 2");
        add(textField1, "cell 1 3");

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(textArea1);
        }
        add(scrollPane1, "cell 3 3 1 3,growy");

        //---- label4 ----
        label4.setText("Valor do servi\u00e7o");
        add(label4, "cell 1 4");
        add(textField2, "cell 1 5");
        add(vSpacer2, "cell 2 6 1 2");

        //---- button1 ----
        button1.setText("Registrar");
        add(button1, "cell 1 11 4 1,alignx center,growx 0,wmin 150");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JPanel vSpacer1;
    private JLabel label2;
    private JLabel label3;
    private JTextField textField1;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JLabel label4;
    private JTextField textField2;
    private JPanel vSpacer2;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
