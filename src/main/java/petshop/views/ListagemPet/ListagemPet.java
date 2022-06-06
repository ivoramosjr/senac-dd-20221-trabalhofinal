/*
 * Created by JFormDesigner on Fri Jun 03 10:13:59 BRT 2022
 */

package petshop.views.ListagemPet;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.*;
import petshop.model.dtos.PetDTO;
import petshop.model.service.PetService;

/**
 * @author unknown
 */
public class ListagemPet extends JPanel {
    public ListagemPet() {
        initComponents();
    }

    private void editarBtn(ActionEvent e) {
        PetDTO pet = pets.get(petTable.getSelectedRow());
        System.out.println(pet);

    }

    public PetDTO getSelectedPet(){
        return pets.get(petTable.getSelectedRow());
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        newPet = new JButton();
        panel1 = new JPanel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        nameField = new JTextField();
        breedFilterBox = new JComboBox();
        fidelityFilterBox = new JComboBox();
        filterPet = new JButton();
        scrollPanePetTable = new JScrollPane();
        petTable = new JTable();
        editarBtn = new JButton();
        deletBtn = new JButton();

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
            "[]"));

        //---- label1 ----
        label1.setText("Lista de pets");
        label1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        add(label1, "cell 0 0 3 1,alignx center,growx 0");

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
            panel1.add(nameField, "cell 0 1,growx");
            panel1.add(breedFilterBox, "cell 1 1");
            panel1.add(fidelityFilterBox, "cell 2 1");

            //---- filterPet ----
            filterPet.setText("Filtrar");
            panel1.add(filterPet, "cell 3 1");
        }
        add(panel1, "cell 0 2 3 2,growx");

        //======== scrollPanePetTable ========
        {

            //---- petTable ----
            petTable.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                    "Nome", "Ra\u00e7a", "Data Nascimento", "Fidelidade", "Dono", "Editar", "Deletar"
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
        add(deletBtn, "cell 2 5");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        PetService petService = new PetService();

        pets = petService.listAll();
        petTable.setDefaultEditor(Object.class, null);
        petTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableModel tableModel = (DefaultTableModel) petTable.getModel();
        for (PetDTO pet: pets) {
            tableModel.addRow(new Object[]{(pet.getNome()), (pet.getRaca()), (pet.getDataNascimento()),
                    (pet.getPontosFidelidade()), (pet.getNomeDono())});
        }
        editarBtn.setEnabled(false);
        deletBtn.setEnabled(false);

        petTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                // do some actions here, for example
                // print first column value from selected row
                if(petTable.getSelectedRow() != -1) {
                    try {

                        editarBtn.setEnabled(true);
                        deletBtn.setEnabled(true);

                    }catch(Exception e){
                        editarBtn.setEnabled(false);
                        deletBtn.setEnabled(false);
                    }
                }
            }
        });
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JButton newPet;
    private JPanel panel1;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JTextField nameField;
    private JComboBox breedFilterBox;
    private JComboBox fidelityFilterBox;
    private JButton filterPet;
    private JScrollPane scrollPanePetTable;
    private JTable petTable;
    private JButton editarBtn;
    private JButton deletBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    List<PetDTO> pets;

    public JButton getEditarBtn() {
        return editarBtn;
    }
}
