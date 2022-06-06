/*
 * Created by JFormDesigner on Fri Jun 03 20:25:07 BRT 2022
 */

package petshop.views.CadastrarPet;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;
import net.miginfocom.swing.*;
import petshop.exceptions.AtributosInvalidosException;
import petshop.model.dtos.PetDTO;
import petshop.model.entity.Pet;
import petshop.model.enums.TipoAnimal;
import petshop.model.service.PetService;

/**
 * @author unknown
 */
public class CadastrarPet extends JPanel {
    public CadastrarPet() {
        initComponents();
    }

    private void registerBtn(ActionEvent e) {
        PetDTO pet = new PetDTO();
        pet.setNome(petNameField.getText().toString());
        pet.setNomeDono(ownerNameField.getText().toString());
        pet.setRaca(breed.getText().toString());
        pet.setTipoAnimal((TipoAnimal) animalsComboBox.getSelectedItem());
        pet.setDataNascimento(dataTeste.getDate());

        PetService petService = new PetService();

        try {
            petService.save(pet);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (AtributosInvalidosException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }

    }

    private void initComponents() {
        DatePickerSettings dateSettings = new DatePickerSettings();
        dateSettings.setAllowKeyboardEditing(false);


        dataTeste.setBounds(2, 3, 1, 1);

        /*
        * add(dataTeste,"cell 2 3, growx");
        * Code to set datepicker *
        * */

        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        label5 = new JLabel();
        petNameField = new JTextField();
        label3 = new JLabel();
        label7 = new JLabel();
        ownerNameField = new JTextField();
        breed = new JTextField();
        label4 = new JLabel();
        animalsComboBox = new JComboBox();
        registerBtn = new JButton();

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
        add(petNameField, "cell 0 3,growx");

        //---- label3 ----
        label3.setText("Nome do dono");
        add(label3, "cell 0 4");

        //---- label7 ----
        label7.setText("Ra\u00e7a");
        add(label7, "cell 2 4");
        add(ownerNameField, "cell 0 5,growx");
        add(breed, "cell 2 5,growx");

        //---- label4 ----
        label4.setText("Animal");
        add(label4, "cell 0 6");
        add(animalsComboBox, "cell 0 7,growx");

        //---- registerBtn ----
        registerBtn.setText("Cadastrar");
        registerBtn.addActionListener(e -> registerBtn(e));
        add(registerBtn, "cell 0 11 3 1,alignx center,growx 0,wmin 150");
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
    private JTextField breed;
    private JLabel label4;
    private JComboBox animalsComboBox;
    private JButton registerBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private DatePicker dataTeste = new DatePicker();
}
