/*
 * Created by JFormDesigner on Fri Jun 03 08:29:12 BRT 2022
 */

package petshop.views.MainView;

import javax.swing.*;
import net.miginfocom.swing.*;
import petshop.views.CadastrarPet.CadastrarPet;
import petshop.views.ListagemServico.TelaListagemServico;
import petshop.views.AgendarAtendimento.TelaAgendarAtendimento;

/**
 * @author unknown
 */
public class MainView extends JFrame {

    public MainView() {
        initComponents();
        renderizarAgendarAtendimento();
    }

    private void renderizarListagemServico() {
        CadastrarPet telaListagemServico = new CadastrarPet();
        setContentPane(telaListagemServico);
        revalidate();
    }

    private void renderizarAgendarAtendimento() {
        TelaAgendarAtendimento telaAgendarAtendimento = new TelaAgendarAtendimento();
        setContentPane(telaAgendarAtendimento);
        revalidate();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menu2 = new JMenu();
        menu3 = new JMenu();
        menu4 = new JMenu();
        panel1 = new JPanel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]"));

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("Pets");

                //---- menuItem1 ----
                menuItem1.setText("Criar servi\u00e7o");
                menu1.add(menuItem1);
            }
            menuBar1.add(menu1);

            //======== menu2 ========
            {
                menu2.setText("Atendimentos");
            }
            menuBar1.add(menu2);

            //======== menu3 ========
            {
                menu3.setText("Servi\u00e7os");
            }
            menuBar1.add(menu3);

            //======== menu4 ========
            {
                menu4.setText("Ajuda");
            }
            menuBar1.add(menu4);
        }
        setJMenuBar(menuBar1);

        //======== panel1 ========
        {
            panel1.setLayout(new MigLayout(
                "fill,hidemode 3",
                // columns
                "[508,fill]",
                // rows
                "[307,fill]"));
        }
        contentPane.add(panel1, "cell 0 0 2 3");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenu menu2;
    private JMenu menu3;
    private JMenu menu4;
    private JPanel panel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void initialize() {
        MainView mainView = new MainView();
        mainView.setVisible(true);
        mainView.setLocationRelativeTo(null);
    }
}
