/*
 * Created by JFormDesigner on Thu Jun 16 09:33:59 BRT 2022
 */

package petshop.views.TelaRelatorioAtendimento;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import net.miginfocom.swing.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petshop.filtros.FiltroRelatorioAtendimento;
import petshop.model.controllers.AtendimentoController;
import petshop.model.dtos.response.RelatorioAtendimentoDTO;
import petshop.model.services.PdfService;

/**
 * @author unknown
 */
public class RelatorioAtendimento extends JDialog {
    private static Logger LOG = LogManager.getLogger(RelatorioAtendimento.class);

    private AtendimentoController atendimentoController;
    private PdfService pdfService;

    public RelatorioAtendimento(Window parent, AtendimentoController atendimentoController) {
        super(parent);
        initComponents();
        this.atendimentoController = atendimentoController;
        this.pdfService = new PdfService();
    }

    private void gerarRelatorio(ActionEvent e) {
        // TODO finalizar função de relatório
        LOG.info("Preparando para gerar o relatório.");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setDialogTitle("Escolha um diretório para salvar o relatório");

        int userSelection = fileChooser.showSaveDialog(this);

        FiltroRelatorioAtendimento filtro = new FiltroRelatorioAtendimento(checkBoxGerarTabela.isSelected());

        RelatorioAtendimentoDTO relatorio = atendimentoController.gerarRelatorio(filtro);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getPath();
            LOG.info("Diretório selecionado: "+path);
            pdfService.gerarRelatorioAtendimento(path, relatorio);
            JOptionPane.showMessageDialog(null, "Relatório salvo com sucesso!", "Relatório salvo!", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }
    }

    private void cancelar(ActionEvent e) {
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        checkBoxGerarTabela = new JCheckBox();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new MigLayout(
                    "insets dialog,hidemode 3",
                    // columns
                    "[grow,fill]",
                    // rows
                    "[39,grow]"));

                //---- checkBoxGerarTabela ----
                checkBoxGerarTabela.setText("Gerar tabela de atendimentos");
                contentPanel.add(checkBoxGerarTabela, "cell 0 0,align center top,grow 0 0");
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setLayout(new MigLayout(
                    "insets dialog,alignx right",
                    // columns
                    "[button,grow,fill]" +
                    "[button,grow,fill]",
                    // rows
                    "[grow]"));

                //---- okButton ----
                okButton.setText("Gerar");
                okButton.addActionListener(e -> gerarRelatorio(e));
                buttonBar.add(okButton, "cell 0 0,alignx center,growx 0");

                //---- cancelButton ----
                cancelButton.setText("Cancelar");
                cancelButton.addActionListener(e -> cancelar(e));
                buttonBar.add(cancelButton, "cell 1 0,alignx center,growx 0");
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JCheckBox checkBoxGerarTabela;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
