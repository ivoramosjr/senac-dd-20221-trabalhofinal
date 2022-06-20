/*
 * Created by JFormDesigner on Mon Jun 20 10:13:52 BRT 2022
 */

package petshop.views.TelaRelatorioPets;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import net.miginfocom.swing.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petshop.filtros.FiltroRelatorioPet;
import petshop.model.controllers.PetController;
import petshop.model.dtos.response.RelatorioPetDTO;
import petshop.model.services.PdfService;
import petshop.views.TelaRelatorioAtendimento.RelatorioAtendimento;

/**
 * @author unknown
 */
public class TelaRelatorioPets extends JDialog {

    private static Logger LOG = LogManager.getLogger(RelatorioAtendimento.class);

    private PetController petController;
    private PdfService pdfService;


    public TelaRelatorioPets(Window owner, PetController petController) {
        super(owner);
        initComponents();
        this.petController = petController;
        this.pdfService = new PdfService();
    }

    private void ok(ActionEvent e) {
        LOG.info("Preparando para gerar o relatório.");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setDialogTitle("Escolha um diretório para salvar o relatório");

        int userSelection = fileChooser.showSaveDialog(this);

        FiltroRelatorioPet filtro = new FiltroRelatorioPet(checkBoxGerarTabela.isSelected());

        RelatorioPetDTO relatorio = petController.gerarRelatorio(filtro);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getPath();
            LOG.info("Diretório selecionado: "+path);
            pdfService.gerarRelatorioPets(path, relatorio);
            JOptionPane.showMessageDialog(null, "Relatório salvo com sucesso!", "Relatório salvo!", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }
    }

    private void cancel(ActionEvent e) {
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
                    "[grow]"));

                //---- checkBoxGerarTabela ----
                checkBoxGerarTabela.setText("Gerar tabela de pets");
                contentPanel.add(checkBoxGerarTabela, "cell 0 0,alignx center,growx 0");
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
                    null));

                //---- okButton ----
                okButton.setText("Gerar");
                okButton.addActionListener(e -> ok(e));
                buttonBar.add(okButton, "cell 0 0,alignx center,growx 0");

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.addActionListener(e -> cancel(e));
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
