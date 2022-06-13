/*
 * Created by JFormDesigner on Fri Jun 03 10:13:59 BRT 2022
 */

package petshop.views.ListagemPet;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.*;
import petshop.exceptions.RegistroNaoEncontradoException;
import petshop.filtros.FiltroPet;
import petshop.model.controllers.PetController;
import petshop.model.dtos.response.PetResponseListagemDTO;
import petshop.model.enums.OrdemPesquisa;
import petshop.model.enums.SexoEnum;
import petshop.model.services.PdfService;

/**
 * @author unknown
 */
public class ListagemPet extends JPanel {
    public ListagemPet() {
        initComponents();
    }

    private void editarBtn(ActionEvent e) {
        Long idPet = pets.get(petTable.getSelectedRow()).getIdPet();
        System.out.println(idPet);

    }

    public Long getSelectedPet(){
        return pets.get(petTable.getSelectedRow()).getIdPet();
    }

    private void filterPet(ActionEvent e) {
        FiltroPet filtro = new FiltroPet();
        filtro.setNome(nameField.getText());

        String raca = (String) breedFilterBox.getSelectedItem();

        if(!raca.equals("Selecione")){
            filtro.setNomeRaca(raca);
        }else{
            filtro.setNomeRaca("");
        }

        if(Objects.equals(fidelityFilterBox.getSelectedItem(), "Crescente")) {
            filtro.setOrdemFidelidade(OrdemPesquisa.ASC);
        } else {
            filtro.setOrdemFidelidade(OrdemPesquisa.DESC);
        }

        if(!Objects.equals(sexComboBox.getSelectedItem(), "Ambos")){
            if(Objects.equals(sexComboBox.getSelectedItem(), "Macho")){
                filtro.setSexoEnum(SexoEnum.MACHO);
            }else{
                filtro.setSexoEnum(SexoEnum.FEMEA);
            }
        }

        pets = petController.findWithFilter(filtro);

        DefaultTableModel tableModel = (DefaultTableModel) petTable.getModel();
        tableModel.setRowCount(0);

        populateTable(tableModel);
    }

    private void delete(ActionEvent e) {
        Long idPet = pets.get(petTable.getSelectedRow()).getIdPet();

        try {
            int option = JOptionPane.showConfirmDialog(null, "Deseja realmente deletar este pet?", "Deletar pet", JOptionPane.YES_NO_OPTION);
            if(option == 0) {
                petController.delete(idPet);
                pets.clear();
                loadServices();
                JOptionPane.showMessageDialog(null, "Pet deletado com sucesso", "Deletar pet.",JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (RegistroNaoEncontradoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Deletar Pet.",JOptionPane.ERROR_MESSAGE);
        }

    }

    private void loadServices() {
        pets = petController.listAll();
        petTable.setDefaultEditor(Object.class, null);
        petTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableModel tableModel = (DefaultTableModel) petTable.getModel();
        tableModel.setRowCount(0);
        populateTable(tableModel);
        habilitarBotoes(false);
    }

    private void gerarRelatorioBtn(ActionEvent e) {
        PdfService pdfService = new PdfService();
        pdfService.gerarRelatorioPets();
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        gerarRelatorioBtn = new JButton();
        newPet = new JButton();
        panel1 = new JPanel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label2 = new JLabel();
        nameField = new JTextField();
        breedFilterBox = new JComboBox();
        fidelityFilterBox = new JComboBox<>();
        sexComboBox = new JComboBox<>();
        filterPet = new JButton();
        scrollPanePetTable = new JScrollPane();
        petTable = new JTable();
        editarBtn = new JButton();
        deletBtn = new JButton();
        buttonMaisInformacoes = new JButton();

        //======== this ========
        setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[175,grow]" +
            "[fill]" +
            "[grow,fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- label1 ----
        label1.setText("Lista de pets");
        label1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        add(label1, "cell 0 0 3 1,alignx center,growx 0");

        //---- gerarRelatorioBtn ----
        gerarRelatorioBtn.setText("Gerar relat\u00f3rio");
        gerarRelatorioBtn.addActionListener(e -> gerarRelatorioBtn(e));
        add(gerarRelatorioBtn, "cell 0 1,alignx left,growx 0");

        //---- newPet ----
        newPet.setText("Cadastrar novo pet");
        add(newPet, "cell 2 1,alignx right,growx 0");

        //======== panel1 ========
        {
            panel1.setLayout(new MigLayout(
                "hidemode 3",
                // columns
                "[grow]" +
                "[grow,fill]" +
                "[grow,fill]" +
                "[fill]" +
                "[fill]",
                // rows
                "[]" +
                "[]"));

            //---- label4 ----
            label4.setText("Nome");
            panel1.add(label4, "cell 0 0");

            //---- label5 ----
            label5.setText("Ra\u00e7a");
            panel1.add(label5, "cell 1 0");

            //---- label6 ----
            label6.setText("Ordem fidelidade");
            panel1.add(label6, "cell 2 0");

            //---- label2 ----
            label2.setText("Sexo");
            panel1.add(label2, "cell 3 0");
            panel1.add(nameField, "cell 0 1,growx");
            panel1.add(breedFilterBox, "cell 1 1");

            //---- fidelityFilterBox ----
            fidelityFilterBox.setModel(new DefaultComboBoxModel<>(new String[] {
                "Crescente",
                "Descrescente"
            }));
            panel1.add(fidelityFilterBox, "cell 2 1");

            //---- sexComboBox ----
            sexComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                "Ambos",
                "Macho",
                "F\u00eamea"
            }));
            panel1.add(sexComboBox, "cell 3 1");

            //---- filterPet ----
            filterPet.setText("Filtrar");
            filterPet.addActionListener(e -> filterPet(e));
            panel1.add(filterPet, "cell 4 1");
        }
        add(panel1, "cell 0 2 3 2,growx");

        //======== scrollPanePetTable ========
        {

            //---- petTable ----
            petTable.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                    "Nome", "Ra\u00e7a", "Sexo", "Dono"
                }
            ));
            scrollPanePetTable.setViewportView(petTable);
        }
        add(scrollPanePetTable, "cell 0 4 3 1,growx");

        //---- editarBtn ----
        editarBtn.setText("Editar");
        editarBtn.addActionListener(e -> editarBtn(e));
        add(editarBtn, "cell 0 5,growx");

        //---- deletBtn ----
        deletBtn.setText("Deletar");
        deletBtn.addActionListener(e -> delete(e));
        add(deletBtn, "cell 2 5");

        //---- buttonMaisInformacoes ----
        buttonMaisInformacoes.setText("+ Informa\u00e7\u00f5es");
        add(buttonMaisInformacoes, "cell 2 5");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        loadServices();

        List<String> racas = petController.getRacas();

        breedFilterBox.addItem("Selecione");

        for(String raca : racas){
            breedFilterBox.addItem(raca);
        }

        petTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                // do some actions here, for example
                // print first column value from selected row
                if(petTable.getSelectedRow() != -1) {
                    try {
                        habilitarBotoes(true);

                    }catch(Exception e){
                        habilitarBotoes(false);
                    }
                }
            }
        });
    }

    private void populateTable(DefaultTableModel tableModel) {
        for (PetResponseListagemDTO pet: pets) {
            tableModel.addRow(new Object[]{(pet.getNome()), (pet.getRaca()), (pet.getSexo()),
                    (pet.getNomeDono())});
        }
    }

    private void habilitarBotoes(boolean b) {
        editarBtn.setEnabled(b);
        deletBtn.setEnabled(b);
        buttonMaisInformacoes.setEnabled(b);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JButton gerarRelatorioBtn;
    private JButton newPet;
    private JPanel panel1;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label2;
    private JTextField nameField;
    private JComboBox breedFilterBox;
    private JComboBox<String> fidelityFilterBox;
    private JComboBox<String> sexComboBox;
    private JButton filterPet;
    private JScrollPane scrollPanePetTable;
    private JTable petTable;
    private JButton editarBtn;
    private JButton deletBtn;
    private JButton buttonMaisInformacoes;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    List<PetResponseListagemDTO> pets;
    PetController petController = new PetController();

    public JButton getEditarBtn() {
        return editarBtn;
    }

    public JButton getCadastrarBtn(){
        return newPet;
    }
}
