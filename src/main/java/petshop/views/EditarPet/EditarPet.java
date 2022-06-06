/*
 * Created by JFormDesigner on Fri Jun 03 21:02:05 BRT 2022
 */

package petshop.views.EditarPet;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import net.miginfocom.swing.*;
import petshop.exceptions.AtributosInvalidosException;
import petshop.exceptions.RegistroNaoEncontradoException;
import petshop.model.dtos.PetDTO;
import petshop.model.enums.TipoAnimal;
import petshop.model.service.PetService;

/**
 * @author unknown
 */
public class EditarPet extends JPanel {
    public EditarPet(PetDTO petDTO) {
        initComponents(petDTO);
        System.out.println(petDTO.toString());
        petId = petDTO.getIdPet();
        petNameField.setText(petDTO.getNome());
        ownerNameField.setText(petDTO.getNomeDono());
        breedField.setText(petDTO.getRaca());
        dataTeste.setDate(petDTO.getDataNascimento());
        animalsComboBox.setSelectedItem(petDTO.getTipoAnimal());
    }

    private void button1(ActionEvent e) {
        PetDTO pet = new PetDTO();
        pet.setNome(petNameField.getText().toString());
        pet.setNomeDono(ownerNameField.getText().toString());
        pet.setRaca(breedField.getText().toString());
        pet.setTipoAnimal((TipoAnimal) animalsComboBox.getSelectedItem());
        pet.setDataNascimento(dataTeste.getDate());

        try {
            petService.update(petId, pet);
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
            limparCampos();

        }catch (RegistroNaoEncontradoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void initComponents(PetDTO petDTO) {
        System.out.println("Acessei a pagina");
        DatePickerSettings dateSettings = new DatePickerSettings();
        dateSettings.setAllowKeyboardEditing(false);
        dataTeste.setBounds(2, 3, 1, 1);
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        label5 = new JLabel();
        petNameField = new JTextField();
        label3 = new JLabel();
        label7 = new JLabel();
        ownerNameField = new JTextField();
        breedField = new JTextField();
        label4 = new JLabel();
        animalsComboBox = new JComboBox();
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
        label1.setText("Editar pet");
        label1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        add(label1, "cell 0 0 3 1,alignx center,growx 0");

        //---- label2 ----
        label2.setText("Nome do pet");
        add(label2, "cell 0 2");

        //---- label5 ----
        label5.setText("Data de nascimento");
        add(label5, "cell 2 2");
        add(petNameField, "cell 0 3,growx");

        //---- label3 ----
        label3.setText("Nome do dono");
        add(label3, "cell 0 4");

        //---- label7 ----
        label7.setText("Ra\u00e7a");
        add(label7, "cell 2 4");
        add(ownerNameField, "cell 0 5,growx");
        add(breedField, "cell 2 5,growx");

        //---- label4 ----
        label4.setText("Animal");
        add(label4, "cell 0 6");
        add(animalsComboBox, "cell 0 7,growx");

        //---- button1 ----
        button1.setText("Editar");
        button1.addActionListener(e -> button1(e));
        add(button1, "cell 0 11 3 1,alignx center,growx 0,wmin 150");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        add(dataTeste,"cell 2 3, growx");
        animalsComboBox.addItem(TipoAnimal.CACHORRO);
        animalsComboBox.addItem(TipoAnimal.FURAO);
        animalsComboBox.addItem(TipoAnimal.GATO);
        animalsComboBox.addItem(TipoAnimal.LONTRA);
        animalsComboBox.addItem(TipoAnimal.RATO);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JLabel label5;
    private JTextField petNameField;
    private JLabel label3;
    private JLabel label7;
    private JTextField ownerNameField;
    private JTextField breedField;
    private JLabel label4;
    private JComboBox animalsComboBox;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private PetService petService = new PetService();
    private DatePicker dataTeste = new DatePicker();

    private Long petId;

    private void limparCampos(){
        petNameField.setText("");
        ownerNameField.setText("");
        breedField.setText("");
        dataTeste.clear();
    }
    private class this2 extends JPanel {
        private this2() {
            initComponents();
        }

        private void initComponents() {
            // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            // JFormDesigner - End of component initialization  //GEN-END:initComponents
        }

        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
        // JFormDesigner - End of variables declaration  //GEN-END:variables
    }
}
