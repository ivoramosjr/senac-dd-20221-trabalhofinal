/*
 * Created by JFormDesigner on Fri Jun 03 09:11:26 BRT 2022
 */

package petshop.views.ListagemAtendimentos;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class TelaListagemAtendimentos extends JPanel {
    public TelaListagemAtendimentos() {
        initComponents();
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
        labelOrdemData = new JLabel();
        textFieldNome = new JTextField();
        textFieldRaca = new JTextField();
        comboBoxFiltro = new JComboBox<>();
        buttonFiltrar = new JButton();

        //======== this ========
        setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[grow,fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[grow]"));

        //---- labelListaAtendimentos ----
        labelListaAtendimentos.setText("Lista de atendimentos");
        labelListaAtendimentos.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        labelListaAtendimentos.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelListaAtendimentos, "cell 0 0");

        //---- buttonGerarRelatorio ----
        buttonGerarRelatorio.setText("Gerar relat\u00f3rio");
        buttonGerarRelatorio.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        add(buttonGerarRelatorio, "cell 0 1,alignx left,growx 0");

        //======== menuBarRealizadoAgendado ========
        {

            //---- radioButtonMenuItemAgendado ----
            radioButtonMenuItemAgendado.setText("Agendados");
            radioButtonMenuItemAgendado.setSelected(true);
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

            //---- labelOrdemData ----
            labelOrdemData.setText("Ordem data:");
            panelEntradas.add(labelOrdemData, "cell 2 0,alignx left,growx 0");
            panelEntradas.add(textFieldNome, "cell 0 1,growx");
            panelEntradas.add(textFieldRaca, "cell 1 1");

            //---- comboBoxFiltro ----
            comboBoxFiltro.setModel(new DefaultComboBoxModel<>(new String[] {
                "Crescente",
                "Decrescente"
            }));
            panelEntradas.add(comboBoxFiltro, "cell 2 1,alignx left,growx 0");

            //---- buttonFiltrar ----
            buttonFiltrar.setText("Filtrar");
            panelEntradas.add(buttonFiltrar, "cell 3 1,alignx left,growx 0");
        }
        add(panelEntradas, "cell 0 2");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
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
    private JLabel labelOrdemData;
    private JTextField textFieldNome;
    private JTextField textFieldRaca;
    private JComboBox<String> comboBoxFiltro;
    private JButton buttonFiltrar;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
