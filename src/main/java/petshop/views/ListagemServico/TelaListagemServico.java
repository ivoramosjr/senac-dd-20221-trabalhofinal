/*
 * Created by JFormDesigner on Thu Jun 02 18:11:44 BRT 2022
 */

package petshop.views.ListagemServico;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import net.miginfocom.swing.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author unknown
 */
public class TelaListagemServico extends JPanel {
    private static Logger LOG = LogManager.getLogger(TelaListagemServico.class);

    public TelaListagemServico() {
        initComponents();
    }

    private void criarServico(ActionEvent e) {
        // TODO add your code here
        LOG.info("Trocando a tela para criar um serviço.");
    }

    private void buttonCriarServicoMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void filtrar(ActionEvent e) {
        // TODO add your code here
        LOG.info("Filtrando serviço");
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
            "[244]" +
            "[]"));

        //---- labelListaServico ----
        labelListaServico.setText("Lista de servi\u00e7os");
        labelListaServico.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        add(labelListaServico, "cell 1 0,align center center,grow 0 0");

        //---- buttonCriarServico ----
        buttonCriarServico.setText("Criar servi\u00e7o");
        buttonCriarServico.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        buttonCriarServico.addActionListener(e -> criarServico(e));
        add(buttonCriarServico, "cell 3 1,align right center,grow 0 0");

        //---- labelNome ----
        labelNome.setText("Nome:");
        labelNome.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        add(labelNome, "cell 0 2,alignx left,growx 0");

        //---- labelOrdemValor ----
        labelOrdemValor.setText("Ordem valor:");
        labelOrdemValor.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        add(labelOrdemValor, "cell 1 2,alignx left,growx 0");

        //---- labelOrdemValor2 ----
        labelOrdemValor2.setText("Ordem QTD atendimentos:");
        labelOrdemValor2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        add(labelOrdemValor2, "cell 2 2,alignx left,growx 0");
        add(textFieldNome, "cell 0 3,growx");

        //---- comboBoxOrdemValor ----
        comboBoxOrdemValor.setModel(new DefaultComboBoxModel<>(new String[] {
            "Crescente",
            "Descrescente"
        }));
        add(comboBoxOrdemValor, "cell 1 3,growx");

        //---- comboBoxOrdemAtendimento ----
        comboBoxOrdemAtendimento.setModel(new DefaultComboBoxModel<>(new String[] {
            "Crescente",
            "Descrescente"
        }));
        add(comboBoxOrdemAtendimento, "cell 2 3");

        //---- buttonFiltrar ----
        buttonFiltrar.setText("Filtrar");
        buttonFiltrar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        buttonFiltrar.addActionListener(e -> filtrar(e));
        add(buttonFiltrar, "cell 3 3,alignx left,growx 0");

        //======== scrollPaneListaServicos ========
        {

            //---- tableServicos ----
            tableServicos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tableServicos.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                    "Servi\u00e7o", "Valor", "QTD atendimentos", "Editar", "Deletar"
                }
            ) {
                boolean[] columnEditable = new boolean[] {
                    false, true, true, true, true
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
        add(scrollPaneListaServicos, "cell 0 4 4 1,grow");
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
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
