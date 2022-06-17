/*
 * Created by JFormDesigner on Fri Jun 03 09:11:26 BRT 2022
 */

package petshop.views.ListagemAtendimentos;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;
import net.miginfocom.swing.*;
import petshop.filtros.FiltroAtendimento;
import petshop.model.controllers.AtendimentoController;
import petshop.model.controllers.PetController;
import petshop.model.controllers.ServicoController;
import petshop.model.dtos.response.AtendimentoResponseListagemDTO;
import petshop.model.dtos.response.ServicoResponseDTO;
import petshop.model.enums.OrdemPesquisa;
import petshop.model.enums.StatusAtendimentoEnum;
import petshop.views.TelaRelatorioAtendimento.RelatorioAtendimento;

/**
 * @author unknown
 */
public class TelaListagemAtendimentos extends JPanel {
    public TelaListagemAtendimentos() {
        initComponents();
        loadServices();
    }

    private void filtrar(ActionEvent e) {
        // TODO add your code here
        FiltroAtendimento filtroAtendimento = new FiltroAtendimento();

        filtroAtendimento.setNomePet(textFieldNome.getText());

        String raca = (String) comboBoxRaca.getSelectedItem();

        filtroAtendimento.setRaca(raca.equals("Todos") ? null: raca);

        Long idServico;
        try{
            ServicoResponseDTO servico = (ServicoResponseDTO) comboBoxServico.getSelectedItem();
            idServico = servico.getIdServico();
        }catch (Exception ex){
            idServico = 0L;
        }

        filtroAtendimento.setIdServico(idServico);

        List<StatusAtendimentoEnum> status = new ArrayList<>();
        if(radioButtonMenuItemAgendado.isSelected()){
            status.add(StatusAtendimentoEnum.AGENDADO);
        }
        if(radioButtonMenuItemRealizados.isSelected()){
            status.add(StatusAtendimentoEnum.REALIZADO);
        }

        filtroAtendimento.setStatus(status);

        if(Objects.equals(comboBoxFiltro.getSelectedItem(), "Crescente")) {
            filtroAtendimento.setOrdemData(OrdemPesquisa.ASC);
        } else {
            filtroAtendimento.setOrdemData(OrdemPesquisa.DESC);
        }

        atendimentos = atendimentoController.findWithFilter(filtroAtendimento);

        DefaultTableModel tableModel = (DefaultTableModel) tableListaAtendimentos.getModel();
        tableModel.setRowCount(0);

        atendimentos.stream().forEach(a -> tableModel.addRow(
                new Object[]{a.getPetNome(), a.getPetRaca(), a.getServicoNome(), a.getServicoValor(), a.getDataAtendimento(), a.getHoraAtendimento(), a.getStatusAtendimento()}
        ));
    }

    private void gerarRelatorio(ActionEvent e) {
        // TODO add your code here
        RelatorioAtendimento relatorioAtendimento = new RelatorioAtendimento(null, this.atendimentoController);
        relatorioAtendimento.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        labelListaAtendimentos = new JLabel();
        buttonGerarRelatorio = new JButton();
        menuBarRealizadoAgendado = new JMenuBar();
        radioButtonMenuItemAgendado = new JRadioButtonMenuItem();
        radioButtonMenuItemRealizados = new JRadioButtonMenuItem();
        panelEntradas = new JPanel();
        labelNome = new JLabel();
        labelRaca = new JLabel();
        labelServico = new JLabel();
        labelOrdemData = new JLabel();
        textFieldNome = new JTextField();
        comboBoxRaca = new JComboBox();
        comboBoxServico = new JComboBox();
        comboBoxFiltro = new JComboBox<>();
        buttonFiltrar = new JButton();
        scrollPaneTabela = new JScrollPane();
        tableListaAtendimentos = new JTable();

        //======== this ========
        setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[grow,fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[grow,top]"));

        //---- labelListaAtendimentos ----
        labelListaAtendimentos.setText("Lista de atendimentos");
        labelListaAtendimentos.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        labelListaAtendimentos.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelListaAtendimentos, "cell 0 0");

        //---- buttonGerarRelatorio ----
        buttonGerarRelatorio.setText("Gerar relat\u00f3rio");
        buttonGerarRelatorio.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        buttonGerarRelatorio.addActionListener(e -> gerarRelatorio(e));
        add(buttonGerarRelatorio, "cell 0 1,alignx left,growx 0");

        //======== menuBarRealizadoAgendado ========
        {

            //---- radioButtonMenuItemAgendado ----
            radioButtonMenuItemAgendado.setText("Agendados");
            menuBarRealizadoAgendado.add(radioButtonMenuItemAgendado);

            //---- radioButtonMenuItemRealizados ----
            radioButtonMenuItemRealizados.setText("Realizados");
            menuBarRealizadoAgendado.add(radioButtonMenuItemRealizados);
        }
        add(menuBarRealizadoAgendado, "cell 0 1,alignx right,growx 0");

        //======== panelEntradas ========
        {
            panelEntradas.setLayout(new MigLayout(
                "hidemode 3",
                // columns
                "[grow,fill]" +
                "[grow,fill]" +
                "[fill]" +
                "[grow,fill]" +
                "[grow,fill]",
                // rows
                "[grow]" +
                "[grow]"));

            //---- labelNome ----
            labelNome.setText("Nome:");
            panelEntradas.add(labelNome, "cell 0 0,alignx left,growx 0");

            //---- labelRaca ----
            labelRaca.setText("Ra\u00e7a:");
            panelEntradas.add(labelRaca, "cell 1 0,alignx left,growx 0");

            //---- labelServico ----
            labelServico.setText("Servi\u00e7o:");
            panelEntradas.add(labelServico, "cell 2 0");

            //---- labelOrdemData ----
            labelOrdemData.setText("Ordem data:");
            panelEntradas.add(labelOrdemData, "cell 3 0,alignx left,growx 0");
            panelEntradas.add(textFieldNome, "cell 0 1,growx");
            panelEntradas.add(comboBoxRaca, "cell 1 1");
            panelEntradas.add(comboBoxServico, "cell 2 1");

            //---- comboBoxFiltro ----
            comboBoxFiltro.setModel(new DefaultComboBoxModel<>(new String[] {
                "Crescente",
                "Decrescente"
            }));
            panelEntradas.add(comboBoxFiltro, "cell 3 1,growx");

            //---- buttonFiltrar ----
            buttonFiltrar.setText("Filtrar");
            buttonFiltrar.addActionListener(e -> filtrar(e));
            panelEntradas.add(buttonFiltrar, "cell 4 1,alignx left,growx 0");
        }
        add(panelEntradas, "cell 0 2");

        //======== scrollPaneTabela ========
        {

            //---- tableListaAtendimentos ----
            tableListaAtendimentos.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null, null, null, null, null},
                },
                new String[] {
                    "Nome", "Ra\u00e7a", "Servi\u00e7o", "Valor", "Data", "Hor\u00e1rio", "Status"
                }
            ) {
                Class<?>[] columnTypes = new Class<?>[] {
                    String.class, String.class, String.class, String.class, Object.class, Object.class, Object.class
                };
                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    return columnTypes[columnIndex];
                }
            });
            tableListaAtendimentos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            scrollPaneTabela.setViewportView(tableListaAtendimentos);
        }
        add(scrollPaneTabela, "cell 0 3,growx");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private void loadServices() {
        atendimentoController = new AtendimentoController();
        atendimentos = atendimentoController.listAll();

        DefaultTableModel tableModel = (DefaultTableModel) tableListaAtendimentos.getModel();
        tableModel.setRowCount(0);

        atendimentos.stream().forEach(a -> tableModel.addRow(
                new Object[]{a.getPetNome(), a.getPetRaca(), a.getServicoNome(), a.getServicoValor(), a.getDataAtendimento(), a.getHoraAtendimento(), a.getStatusAtendimento()}
        ));

        petController = new PetController();
        racas = petController.getRacas();
        comboBoxRaca.addItem("Todos");
        racas.stream().forEach(r -> comboBoxRaca.addItem(r));

        servicoController = new ServicoController();
        servicos = servicoController.listAll();
        comboBoxServico.addItem("Todos");
        servicos.stream().forEach(s->comboBoxServico.addItem(s));
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel labelListaAtendimentos;
    private JButton buttonGerarRelatorio;
    private JMenuBar menuBarRealizadoAgendado;
    private JRadioButtonMenuItem radioButtonMenuItemAgendado;
    private JRadioButtonMenuItem radioButtonMenuItemRealizados;
    private JPanel panelEntradas;
    private JLabel labelNome;
    private JLabel labelRaca;
    private JLabel labelServico;
    private JLabel labelOrdemData;
    private JTextField textFieldNome;
    private JComboBox comboBoxRaca;
    private JComboBox comboBoxServico;
    private JComboBox<String> comboBoxFiltro;
    private JButton buttonFiltrar;
    private JScrollPane scrollPaneTabela;
    private JTable tableListaAtendimentos;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private List<AtendimentoResponseListagemDTO> atendimentos = new ArrayList<>();
    private List<String> racas = new ArrayList<>();
    private List<ServicoResponseDTO> servicos = new ArrayList<>();
    private AtendimentoController atendimentoController;
    private PetController petController;
    private ServicoController servicoController;
}
