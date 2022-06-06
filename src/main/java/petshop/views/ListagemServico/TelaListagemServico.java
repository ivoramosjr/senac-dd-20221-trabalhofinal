/*
 * Created by JFormDesigner on Thu Jun 02 18:11:44 BRT 2022
 */

package petshop.views.ListagemServico;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;
import net.miginfocom.swing.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petshop.model.dtos.ServicoDTO;
import petshop.model.service.ServicoService;

/**
 * @author unknown
 */
public class TelaListagemServico extends JPanel {
    private static Logger LOG = LogManager.getLogger(TelaListagemServico.class);

    ServicoService servicoService = new ServicoService();
    List<ServicoDTO> servicesList = new ArrayList<>();

    public TelaListagemServico() {
        initComponents();
        loadServices();
    }

    public JButton getButtonCriarServico() {
        return buttonCriarServico;
    }

    private void filtrar(ActionEvent e) {
        // TODO add your code here
        LOG.info("Filtrando serviço");
    }

    private void loadServices() {
        servicesList = servicoService.listAll();
        DefaultTableModel tableModel = (DefaultTableModel) tableServicos.getModel();

        for(ServicoDTO servico : servicesList) {
            tableModel.addRow(new Object[]{(servico.getNome()), (servico.getValor()), (servico.getQuantidadeAtendimentos())});
        }
    }

    private void initComponents() {
        LOG.info("Abrindo a tela de listagem de serviços.");
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        labelListaServico = new JLabel();
        buttonCriarServico = new JButton();
        labelNome = new JLabel();
        labelOrdemValor = new JLabel();
        labelOrdemValor2 = new JLabel();
        textFieldNome = new JTextField();
        comboBoxOrdemValor = new JComboBox<>();
        comboBoxOrdemAtendimento = new JComboBox<>();
        buttonFiltrar = new JButton();
        scrollPaneListaServicos = new JScrollPane();
        tableServicos = new JTable();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[154,grow,center]" +
            "[127,grow,center]" +
            "[fill]" +
            "[180,grow,center]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[244]" +
            "[]" +
            "[]"));

        //---- labelListaServico ----
        labelListaServico.setText("Lista de servi\u00e7os");
        labelListaServico.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        add(labelListaServico, "cell 0 0 4 1,align center center,grow 0 0");

        //---- buttonCriarServico ----
        buttonCriarServico.setText("Criar Servi\u00e7o");
        buttonCriarServico.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        add(buttonCriarServico, "cell 3 2,alignx right,growx 0");

        //---- labelNome ----
        labelNome.setText("Nome:");
        labelNome.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        add(labelNome, "cell 0 3,alignx left,growx 0");

        //---- labelOrdemValor ----
        labelOrdemValor.setText("Ordem valor:");
        labelOrdemValor.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        add(labelOrdemValor, "cell 1 3,alignx left,growx 0");

        //---- labelOrdemValor2 ----
        labelOrdemValor2.setText("Ordem QTD atendimentos:");
        labelOrdemValor2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        add(labelOrdemValor2, "cell 2 3,alignx left,growx 0");
        add(textFieldNome, "cell 0 4,growx");

        //---- comboBoxOrdemValor ----
        comboBoxOrdemValor.setModel(new DefaultComboBoxModel<>(new String[] {
            "Crescente",
            "Descrescente"
        }));
        add(comboBoxOrdemValor, "cell 1 4,growx");

        //---- comboBoxOrdemAtendimento ----
        comboBoxOrdemAtendimento.setModel(new DefaultComboBoxModel<>(new String[] {
            "Crescente",
            "Descrescente"
        }));
        add(comboBoxOrdemAtendimento, "cell 2 4");

        //---- buttonFiltrar ----
        buttonFiltrar.setText("Filtrar");
        buttonFiltrar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        buttonFiltrar.addActionListener(e -> filtrar(e));
        add(buttonFiltrar, "cell 3 4,alignx left,growx 0");

        //======== scrollPaneListaServicos ========
        {

            //---- tableServicos ----
            tableServicos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tableServicos.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                    "Servi\u00e7o", "Valor", "QTD atendimentos"
                }
            ) {
                boolean[] columnEditable = new boolean[] {
                    false, true, true
                };
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            {
                TableColumnModel cm = tableServicos.getColumnModel();
                cm.getColumn(0).setResizable(false);
            }
            scrollPaneListaServicos.setViewportView(tableServicos);
        }
        add(scrollPaneListaServicos, "cell 0 5 4 1,grow");

        //---- button1 ----
        button1.setText("Editar");
        add(button1, "cell 0 7 4 1");

        //---- button2 ----
        button2.setText("Deletar");
        add(button2, "cell 0 7 4 1,alignx center,growx 0");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel labelListaServico;
    private JButton buttonCriarServico;
    private JLabel labelNome;
    private JLabel labelOrdemValor;
    private JLabel labelOrdemValor2;
    private JTextField textFieldNome;
    private JComboBox<String> comboBoxOrdemValor;
    private JComboBox<String> comboBoxOrdemAtendimento;
    private JButton buttonFiltrar;
    private JScrollPane scrollPaneListaServicos;
    private JTable tableServicos;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
