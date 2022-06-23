/*
 * Created by JFormDesigner on Fri Jun 03 08:45:04 BRT 2022
 */

package petshop.views.RegistrarAtendimento;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import net.miginfocom.swing.*;
import petshop.exceptions.RegistroNaoEncontradoException;
import petshop.model.controllers.AtendimentoController;
import petshop.model.dtos.response.AtendimentoRegistroDTO;

/**
 * @author unknown
 */
public class TelaRegistrarAtendimento extends JPanel {
    public TelaRegistrarAtendimento() {
        initComponents();
        atendimentoController = new AtendimentoController();
        carregarAtendimentos();
    }

    private void carregarAtendimentos(){
        listaAtendimentos = atendimentoController.listAllRegistrar();
        comboBoxAtendimentos.removeAllItems();
        comboBoxAtendimentos.addItem(null);
        listaAtendimentos.stream().forEach(a -> comboBoxAtendimentos.addItem(a));
    }

    private void selecionarAtendimento(ActionEvent e) {
        if(comboBoxAtendimentos.getSelectedItem() != null){
            setLabels((AtendimentoRegistroDTO) comboBoxAtendimentos.getSelectedItem());
            habilitarBotoes(true);
        }else{
            resetaLabels();
            habilitarBotoes(false);
        }
    }

    private void habilitarBotoes(boolean valor){
        buttonCancelar.setEnabled(valor);
        buttonConfimar.setEnabled(valor);
    }

    private void setLabels(AtendimentoRegistroDTO atendimento){
        labelPetNome.setText(atendimento.getPetNome());
        labelServicoNome.setText(atendimento.getServicoNome());
        labelValorServico.setText(atendimento.getServicoValor());
        labelDtAtendimentoValor.setText(atendimento.getDataAtendimento());
    }

    private void resetaLabels(){
        labelPetNome.setText("");
        labelServicoNome.setText("");
        labelValorServico.setText("");
        labelDtAtendimentoValor.setText("");
    }

    private void confimar(ActionEvent e) {
        if(comboBoxAtendimentos.getSelectedItem() != null) {
            Long idAtendimento = getIdSelectAtendimento();
            try{
                int option = JOptionPane.showConfirmDialog(null, "Deseja finalizar o atendimento?", "Finalizar atendimento", JOptionPane.YES_NO_OPTION);
                if(option == 0) {
                    atendimentoController.finalizarAtendimento(idAtendimento);
                    JOptionPane.showMessageDialog(null, "Atendimento de ID "+idAtendimento+" finalizado com sucesso!","Sucesso!",JOptionPane.INFORMATION_MESSAGE);
                    carregarAtendimentos();
                    resetaLabels();
                }
            }catch (RegistroNaoEncontradoException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(),"Atenção!",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void cancelar(ActionEvent e) {
        if(comboBoxAtendimentos.getSelectedItem() != null) {
            Long idAtendimento = getIdSelectAtendimento();
            try{
                int option = JOptionPane.showConfirmDialog(null, "Deseja cancelar o atendimento?", "Cancelar atendimento", JOptionPane.YES_NO_OPTION);
                if(option == 0) {
                    atendimentoController.deletarAtendimento(idAtendimento);
                    JOptionPane.showMessageDialog(null, "Atendimento de ID "+idAtendimento+" foi cancelado!","Sucesso!",JOptionPane.INFORMATION_MESSAGE);
                    carregarAtendimentos();
                    resetaLabels();
                }
            }catch (RegistroNaoEncontradoException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(),"Atenção!",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private Long getIdSelectAtendimento(){
        AtendimentoRegistroDTO atendimentoSelecionado = (AtendimentoRegistroDTO) comboBoxAtendimentos.getSelectedItem();
        return atendimentoSelecionado.getIdAtendimento();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        labelAtendimento = new JLabel();
        comboBoxAtendimentos = new JComboBox();
        labelPet = new JLabel();
        labelPetNome = new JLabel();
        labelServico = new JLabel();
        labelServicoNome = new JLabel();
        labelValor = new JLabel();
        labelValorServico = new JLabel();
        labelDtAtendimento = new JLabel();
        labelDtAtendimentoValor = new JLabel();
        buttonConfimar = new JButton();
        buttonCancelar = new JButton();

        //======== this ========
        setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[222,grow,fill]" +
            "[61,grow,fill]",
            // rows
            "[grow]" +
            "[grow]" +
            "[]" +
            "[]" +
            "[grow]" +
            "[grow]" +
            "[]" +
            "[12]" +
            "[grow]"));

        //---- label1 ----
        label1.setText("Registrar Atendimento");
        label1.setFont(new Font(Font.SERIF, Font.BOLD, 24));
        add(label1, "cell 0 0 2 1,alignx center,growx 0");

        //---- labelAtendimento ----
        labelAtendimento.setText("Selecione o atendimento");
        labelAtendimento.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        labelAtendimento.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelAtendimento, "cell 0 1 2 1,alignx center,growx 0");

        //---- comboBoxAtendimentos ----
        comboBoxAtendimentos.addActionListener(e -> selecionarAtendimento(e));
        add(comboBoxAtendimentos, "cell 0 2 2 1,alignx center,growx 0");

        //---- labelPet ----
        labelPet.setText("Pet:");
        labelPet.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        add(labelPet, "cell 0 3 2 1,alignx left,growx 0");

        //---- labelPetNome ----
        labelPetNome.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        add(labelPetNome, "cell 0 3 2 1,alignx left,growx 0");

        //---- labelServico ----
        labelServico.setText("Servi\u00e7o:");
        labelServico.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        add(labelServico, "cell 0 4 2 1,alignx left,growx 0");

        //---- labelServicoNome ----
        labelServicoNome.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        add(labelServicoNome, "cell 0 4 2 1,alignx left,growx 0");

        //---- labelValor ----
        labelValor.setText("Valor: ");
        labelValor.setHorizontalAlignment(SwingConstants.CENTER);
        labelValor.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        add(labelValor, "cell 0 5 2 1,alignx left,growx 0");

        //---- labelValorServico ----
        labelValorServico.setHorizontalAlignment(SwingConstants.CENTER);
        labelValorServico.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        add(labelValorServico, "cell 0 5 2 1,alignx left,growx 0");

        //---- labelDtAtendimento ----
        labelDtAtendimento.setText("Data do atendimento: ");
        labelDtAtendimento.setHorizontalAlignment(SwingConstants.CENTER);
        labelDtAtendimento.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        add(labelDtAtendimento, "cell 0 6 2 1,alignx left,growx 0");

        //---- labelDtAtendimentoValor ----
        labelDtAtendimentoValor.setHorizontalAlignment(SwingConstants.CENTER);
        labelDtAtendimentoValor.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        add(labelDtAtendimentoValor, "cell 0 6 2 1,alignx left,growx 0");

        //---- buttonConfimar ----
        buttonConfimar.setText("Confirmar");
        buttonConfimar.setEnabled(false);
        buttonConfimar.addActionListener(e -> confimar(e));
        add(buttonConfimar, "cell 0 8 2 1,alignx left,growx 0");

        //---- buttonCancelar ----
        buttonCancelar.setText("Cancelar");
        buttonCancelar.setEnabled(false);
        buttonCancelar.addActionListener(e -> cancelar(e));
        add(buttonCancelar, "cell 0 8 2 1,alignx right,growx 0");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel labelAtendimento;
    private JComboBox comboBoxAtendimentos;
    private JLabel labelPet;
    private JLabel labelPetNome;
    private JLabel labelServico;
    private JLabel labelServicoNome;
    private JLabel labelValor;
    private JLabel labelValorServico;
    private JLabel labelDtAtendimento;
    private JLabel labelDtAtendimentoValor;
    private JButton buttonConfimar;
    private JButton buttonCancelar;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private AtendimentoController atendimentoController;
    private List<AtendimentoRegistroDTO> listaAtendimentos;
}
