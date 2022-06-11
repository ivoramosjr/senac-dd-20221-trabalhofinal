/*
 * Created by JFormDesigner on Fri Jun 03 08:56:02 BRT 2022
 */

package petshop.views.CriarServico;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;

import net.miginfocom.swing.*;
import petshop.exceptions.AtributosInvalidosException;
import petshop.model.controllers.ServicoController;
import petshop.model.dtos.request.ServicoRequestDTO;

/**
 * @author unknown
 */
public class CriarServico extends JPanel {

    ServicoRequestDTO servicoRequest = new ServicoRequestDTO();
    ServicoController servicoController = new ServicoController();
    String regex = "^(\\d+(\\.?\\d{0,2})?|\\.\\d{1,2})$";
    public CriarServico() {
        initComponents();
    }

    private void createService(ActionEvent e) {
        try {
            servicoRequest.setNome(nameServiceField.getText());

            if(valueServiceField.getText().isEmpty()) {
                servicoRequest.setValor(null);
            } else {
                servicoRequest.setValor(Double.parseDouble(valueServiceField.getText()));
            }

            servicoRequest.setDescricao(descriptionServiceField.getText());
            servicoController.save(servicoRequest);

            JOptionPane.showMessageDialog(null, "Serviço cadastrado com sucesso", "Salvar serviço.",JOptionPane.INFORMATION_MESSAGE);
            clearInputs();
        } catch (SQLException | AtributosInvalidosException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro ao salvar serviço.",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearInputs() {
        nameServiceField.setText("");
        valueServiceField.setText("");
        descriptionServiceField.setText("");
    }

    private void valueServiceFieldKeyReleased(KeyEvent e) {
        String text = valueServiceField.getText();
        if(text.matches(regex)) {
            e.consume();
        } else {
            if(valueServiceField.getText().length() > 0) {
                String previousText = valueServiceField.getText().substring(0, valueServiceField.getText().length() - 1);
                valueServiceField.setText(previousText);
            }
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        vSpacer1 = new JPanel(null);
        label2 = new JLabel();
        label3 = new JLabel();
        nameServiceField = new JTextField();
        scrollPane1 = new JScrollPane();
        descriptionServiceField = new JTextArea();
        label4 = new JLabel();
        valueServiceField = new JTextField();
        vSpacer2 = new JPanel(null);
        createButton = new JButton();

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
        add(nameServiceField, "cell 1 3");

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(descriptionServiceField);
        }
        add(scrollPane1, "cell 3 3 1 3,growy");

        //---- label4 ----
        label4.setText("Valor do servi\u00e7o");
        add(label4, "cell 1 4");

        //---- valueServiceField ----
        valueServiceField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                valueServiceFieldKeyReleased(e);
            }
        });
        add(valueServiceField, "cell 1 5");
        add(vSpacer2, "cell 2 6 1 2");

        //---- createButton ----
        createButton.setText("Criar");
        createButton.addActionListener(e -> createService(e));
        add(createButton, "cell 1 11 4 1,alignx center,growx 0,wmin 150");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JPanel vSpacer1;
    private JLabel label2;
    private JLabel label3;
    private JTextField nameServiceField;
    private JScrollPane scrollPane1;
    private JTextArea descriptionServiceField;
    private JLabel label4;
    private JTextField valueServiceField;
    private JPanel vSpacer2;
    private JButton createButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
