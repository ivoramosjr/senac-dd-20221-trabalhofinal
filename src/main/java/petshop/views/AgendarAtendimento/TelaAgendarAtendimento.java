/*
 * Created by JFormDesigner on Fri Jun 03 09:06:54 BRT 2022
 */

package petshop.views.AgendarAtendimento;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import javax.swing.*;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;
import net.miginfocom.swing.*;
import petshop.exceptions.AtributosInvalidosException;
import petshop.exceptions.HorarioJaMarcadoException;
import petshop.exceptions.RegistroNaoEncontradoException;
import petshop.model.controllers.AtendimentoController;
import petshop.model.controllers.PetController;
import petshop.model.controllers.ServicoController;
import petshop.model.dtos.request.AtendimentoRequestDTO;
import petshop.model.dtos.response.PetResponseListagemDTO;
import petshop.model.dtos.response.ServicoResponseDTO;
import petshop.model.enums.StatusAtendimentoEnum;

/**
 * @author unknown
 */
public class TelaAgendarAtendimento extends JPanel {

    public TelaAgendarAtendimento() {
        initComponents();
        loadPets();
        loadServices();
        atendimentoController = new AtendimentoController();
    }

    private void agendarAtendimento(ActionEvent e) {
        AtendimentoRequestDTO atendimentoRequestDTO = new AtendimentoRequestDTO();
        atendimentoRequestDTO.setStatusAtendimentoEnum(StatusAtendimentoEnum.AGENDADO);

        PetResponseListagemDTO selectedPet = (PetResponseListagemDTO) comboPet.getSelectedItem();
        ServicoResponseDTO selectedService = (ServicoResponseDTO) comboBoxServico.getSelectedItem();

        atendimentoRequestDTO.setPetIdPet(selectedPet.getIdPet());
        atendimentoRequestDTO.setServicoIdServico(selectedService.getIdServico());
        LocalDate localDate = data.getDatePicker().getDate();
        LocalTime localTime = data.getTimePicker().getTime();

        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        atendimentoRequestDTO.setDataAtendimento(localDateTime);

        try {
            atendimentoController.save(atendimentoRequestDTO);
            JOptionPane.showMessageDialog(null, "Atendimento agendado com sucesso", "Agendar atendimento.",JOptionPane.INFORMATION_MESSAGE);
            clearInputs();
        } catch (SQLException | AtributosInvalidosException | HorarioJaMarcadoException | RegistroNaoEncontradoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro ao agendar atendimento.",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearInputs() {
        comboPet.setSelectedItem(null);
        comboBoxServico.setSelectedItem(null);
        data.clear();
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
        buttonAgendar.addActionListener(e -> agendarAtendimento(e));
        add(buttonAgendar, "cell 0 9 4 1,align center bottom,grow 0 0");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        add(data, "cell 2 3");
    }

    private void loadPets() {
        PetController petController = new PetController();
        List<PetResponseListagemDTO> petResponseListagemDTOS = petController.listAll();

        for(PetResponseListagemDTO pet : petResponseListagemDTOS) {
            comboPet.addItem(pet);
        }
        comboPet.setSelectedItem(null);
    }

    private void loadServices() {
        ServicoController servicoController = new ServicoController();
        List<ServicoResponseDTO> servicoResponseDTOS = servicoController.listAll();

        for(ServicoResponseDTO service : servicoResponseDTOS) {
            comboBoxServico.addItem(service);
        }
        comboBoxServico.setSelectedItem(null);
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
    AtendimentoController atendimentoController;
}
