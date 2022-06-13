/*
 * Created by JFormDesigner on Thu Jun 02 18:11:44 BRT 2022
 */

package petshop.views.ListagemServico;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.*;
import javax.swing.table.*;
import net.miginfocom.swing.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petshop.exceptions.RegistroNaoEncontradoException;
import petshop.model.controllers.ServicoController;
import petshop.filtros.FiltroServico;
import petshop.model.dtos.response.ServicoResponseDTO;
import petshop.model.enums.OrdemPesquisa;
import petshop.model.services.PdfService;

/**
 * @author unknown
 */
public class TelaListagemServico extends JPanel {
    private static Logger LOG = LogManager.getLogger(TelaListagemServico.class);

    ServicoController servicoController = new ServicoController();
    List<ServicoResponseDTO> servicesList = new ArrayList<>();

    public TelaListagemServico() {
        initComponents();
        loadServices();

        tableServicos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println(tableServicos.getSelectedRow());
                verifyRowToEnableEditAndDeleteButtons();
            }
        });
    }

    private void filtrar(ActionEvent e) {
        FiltroServico filtroServico = new FiltroServico();
        filtroServico.setNome(textFieldNome.getText());
        if(Objects.equals(comboBoxOrdemValor.getSelectedItem(), "Crescente")) {
            filtroServico.setOrdemValor(OrdemPesquisa.ASC);
        } else {
            filtroServico.setOrdemValor(OrdemPesquisa.DESC);
        }
        if(Objects.equals(comboBoxOrdemAtendimento.getSelectedItem(), "Crescente")) {
            filtroServico.setOrdemQuantidadeAtendimentos(OrdemPesquisa.ASC);
        } else {
            filtroServico.setOrdemQuantidadeAtendimentos(OrdemPesquisa.DESC);
        }

        servicesList = servicoController.findWithFilter(filtroServico);
        DefaultTableModel tableModel = (DefaultTableModel) tableServicos.getModel();
        tableModel.setRowCount(0);

        for(ServicoResponseDTO servico : servicesList) {
            tableModel.addRow(new Object[]{(servico.getNome()), servico.getValor(), (servico.getQuantidadeAtendimentos())});
        }
    }

    private void loadServices() {
        servicesList = servicoController.listAll();
        DefaultTableModel tableModel = (DefaultTableModel) tableServicos.getModel();
        tableModel.setRowCount(0);

        for(ServicoResponseDTO servico : servicesList) {
            tableModel.addRow(new Object[]{(servico.getNome()), (servico.getValor()), (servico.getQuantidadeAtendimentos())});
        }
    }

    private void deleteService(ActionEvent e)  {
        Long idService = getSelectedService();
        try {
            int option = JOptionPane.showConfirmDialog(null, "Deseja realmente deletar este serviço?", "Deletar serviço", JOptionPane.YES_NO_OPTION);
            if(option == 0) {
                servicoController.delete(idService);
                servicesList.clear();
                loadServices();
                JOptionPane.showMessageDialog(null, "Serviço deletado com sucesso", "Deletar serviço.",JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (RegistroNaoEncontradoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Deletar serviço.",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void gerarRelatorioBtn(ActionEvent e) {
        PdfService pdfService = new PdfService();
        pdfService.gerarRelatorioServicos();
    }

    private void initComponents() {
        LOG.info("Abrindo a tela de listagem de serviços.");
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        labelListaServico = new JLabel();
        gerarRelatorioBtn = new JButton();
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
        editButton = new JButton();
        deleteButton = new JButton();

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
            "[]" +
            "[]"));

        //---- labelListaServico ----
        labelListaServico.setText("Lista de servi\u00e7os");
        labelListaServico.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        add(labelListaServico, "cell 0 0 4 1,align center center,grow 0 0");

        //---- gerarRelatorioBtn ----
        gerarRelatorioBtn.setText("Gerar relat\u00f3rio");
        gerarRelatorioBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        gerarRelatorioBtn.addActionListener(e -> gerarRelatorioBtn(e));
        add(gerarRelatorioBtn, "cell 0 2,alignx right,growx 0");

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

        //---- editButton ----
        editButton.setText("Editar");
        editButton.setEnabled(false);
        add(editButton, "cell 0 7 4 1");

        //---- deleteButton ----
        deleteButton.setText("Deletar");
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(e -> deleteService(e));
        add(deleteButton, "cell 0 7 4 1,alignx center,growx 0");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel labelListaServico;
    private JButton gerarRelatorioBtn;
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
    private JButton editButton;
    private JButton deleteButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private void verifyRowToEnableEditAndDeleteButtons() {
        if(tableServicos.getSelectedRow() >= 0) {
            editButton.setEnabled(true);
            deleteButton.setEnabled(true);
        } else {
            editButton.setEnabled(false);
            deleteButton.setEnabled(false);
        }
    }

    public JButton getButtonCreateService() {
        return buttonCriarServico;
    }

    public JButton getButtonEditService() {
        return editButton;
    }

    public Long getSelectedService() {
        return getServiceFromRow(tableServicos.getSelectedRow());
    }

    private Long getServiceFromRow(int index) {
        return servicesList.get(index).getIdServico();
    }

}
