/*
 * Created by JFormDesigner on Fri Jun 03 08:29:12 BRT 2022
 */

package petshop.views.MainView;

import java.awt.event.*;
import javax.swing.*;
import net.miginfocom.swing.*;
import petshop.model.dtos.PetDTO;
import petshop.views.CadastrarPet.CadastrarPet;
import petshop.views.CriarServico.CriarServico;
import petshop.views.EditarAtendimento.TelaEditarAtendimento;
import petshop.views.EditarPet.EditarPet;
import petshop.views.EditarServico.EditarServico;
import petshop.views.ListagemAtendimentos.TelaListagemAtendimentos;
import petshop.views.ListagemPet.ListagemPet;
import petshop.views.ListagemServico.TelaListagemServico;
import petshop.views.AgendarAtendimento.TelaAgendarAtendimento;
import petshop.views.RegistrarAtendimento.TelaRegistrarAtendimento;

/**
 * @author unknown
 */
public class MainView extends JFrame {

    public MainView() {
        initComponents();
    }

    private void renderizarListagemServico() {
        TelaListagemServico telaListagemServico = new TelaListagemServico();
        setContentPane(telaListagemServico);
        revalidate();
    }

    private void renderizarCriarServico() {
        CriarServico criarServico = new CriarServico();
        setContentPane(criarServico);
        revalidate();
    }

    private void renderizarEditarServico() {
        EditarServico editarServico = new EditarServico();
        setContentPane(editarServico);
        revalidate();
    }

    private void renderizarCadastrarPet() {
        CadastrarPet cadastrarPet = new CadastrarPet();
        setContentPane(cadastrarPet);
        revalidate();
    }

    private void renderizarListagemPet() {
        ListagemPet listagemPet = new ListagemPet();
        listagemPet.getEditarBtn().addActionListener(e1 ->{
            PetDTO petDTO = (listagemPet.getSelectedPet());
            EditarPet editarPet = new EditarPet(petDTO);
            setContentPane(editarPet);
            revalidate();
        });
        setContentPane(listagemPet);
        revalidate();
    }

    private void renderizarAgendarAtendimento() {
        TelaAgendarAtendimento telaAgendarAtendimento = new TelaAgendarAtendimento();
        setContentPane(telaAgendarAtendimento);
        revalidate();
    }

    private void renderizarEditarAtendimento() {
        TelaEditarAtendimento telaEditarAtendimento = new TelaEditarAtendimento();
        setContentPane(telaEditarAtendimento);
        revalidate();
    }

    private void renderizarRegistrarAtendimento() {
        TelaRegistrarAtendimento telaRegistrarAtendimento = new TelaRegistrarAtendimento();
        setContentPane(telaRegistrarAtendimento);
        revalidate();
    }

    private void renderizarListagemAtendimento() {
        TelaListagemAtendimentos telaListagemAtendimentos = new TelaListagemAtendimentos();
        setContentPane(telaListagemAtendimentos);
        revalidate();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem2 = new JMenuItem();
        menuItem1 = new JMenuItem();
        menu2 = new JMenu();
        menuItem5 = new JMenuItem();
        menuItem3 = new JMenuItem();
        menuItem4 = new JMenuItem();
        menu3 = new JMenu();
        menuItem6 = new JMenuItem();
        menuItem7 = new JMenuItem();
        menu4 = new JMenu();
        menuItem8 = new JMenuItem();
        panel1 = new JPanel();
        renderizarCadastrarPet = new RenderizarCadastrarPet();
        renderizarListagemPet = new RenderizarListagemPet();
        renderizarListagemAtendimentos = new RenderizarListagemAtendimentos();
        renderizarAgendarAtendimento = new RenderizarAgendarAtendimento();
        renderizarRegistrarAtendimento = new RenderizarRegistrarAtendimento();
        renderizarListagemServicos = new RenderizarListagemServicos();
        renderizarCriarServicos = new RenderizarCriarServicos();

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

                //---- menuItem2 ----
                menuItem2.setAction(renderizarListagemPet);
                menu1.add(menuItem2);

                //---- menuItem1 ----
                menuItem1.setAction(renderizarCadastrarPet);
                menuItem1.setText("Cadastrar pet");
                menu1.add(menuItem1);
            }
            menuBar1.add(menu1);

            //======== menu2 ========
            {
                menu2.setText("Atendimentos");

                //---- menuItem5 ----
                menuItem5.setText("Lista de atendimentos");
                menuItem5.setAction(renderizarListagemAtendimentos);
                menu2.add(menuItem5);

                //---- menuItem3 ----
                menuItem3.setAction(renderizarAgendarAtendimento);
                menu2.add(menuItem3);

                //---- menuItem4 ----
                menuItem4.setAction(renderizarRegistrarAtendimento);
                menu2.add(menuItem4);
            }
            menuBar1.add(menu2);

            //======== menu3 ========
            {
                menu3.setText("Servi\u00e7os");

                //---- menuItem6 ----
                menuItem6.setAction(renderizarListagemServicos);
                menu3.add(menuItem6);

                //---- menuItem7 ----
                menuItem7.setAction(renderizarCriarServicos);
                menu3.add(menuItem7);
            }
            menuBar1.add(menu3);

            //======== menu4 ========
            {
                menu4.setText("Ajuda");

                //---- menuItem8 ----
                menuItem8.setText("Sobre");
                menu4.add(menuItem8);
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
    private JMenuItem menuItem2;
    private JMenuItem menuItem1;
    private JMenu menu2;
    private JMenuItem menuItem5;
    private JMenuItem menuItem3;
    private JMenuItem menuItem4;
    private JMenu menu3;
    private JMenuItem menuItem6;
    private JMenuItem menuItem7;
    private JMenu menu4;
    private JMenuItem menuItem8;
    private JPanel panel1;
    private RenderizarCadastrarPet renderizarCadastrarPet;
    private RenderizarListagemPet renderizarListagemPet;
    private RenderizarListagemAtendimentos renderizarListagemAtendimentos;
    private RenderizarAgendarAtendimento renderizarAgendarAtendimento;
    private RenderizarRegistrarAtendimento renderizarRegistrarAtendimento;
    private RenderizarListagemServicos renderizarListagemServicos;
    private RenderizarCriarServicos renderizarCriarServicos;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void initialize() {
        MainView mainView = new MainView();
        mainView.setVisible(true);
        mainView.setLocationRelativeTo(null);
    }

    private class RenderizarCadastrarPet extends AbstractAction {
        private RenderizarCadastrarPet() {
            // JFormDesigner - Action initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            putValue(NAME, "Cadastrar Pet");
            // JFormDesigner - End of action initialization  //GEN-END:initComponents
        }

        public void actionPerformed(ActionEvent e) {
            renderizarCadastrarPet();
        }
    }

    private class RenderizarListagemPet extends AbstractAction {
        private RenderizarListagemPet() {
            // JFormDesigner - Action initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            putValue(NAME, "Lista de pets");
            // JFormDesigner - End of action initialization  //GEN-END:initComponents
        }

        public void actionPerformed(ActionEvent e) {
            renderizarListagemPet();
        }
    }

    private class RenderizarListagemAtendimentos extends AbstractAction {
        private RenderizarListagemAtendimentos() {
            // JFormDesigner - Action initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            putValue(NAME, "Lista de atendimentos");
            // JFormDesigner - End of action initialization  //GEN-END:initComponents
        }

        public void actionPerformed(ActionEvent e) {
            renderizarListagemAtendimento();
        }
    }

    private class RenderizarAgendarAtendimento extends AbstractAction {
        private RenderizarAgendarAtendimento() {
            // JFormDesigner - Action initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            putValue(NAME, "Agendar atendimento");
            // JFormDesigner - End of action initialization  //GEN-END:initComponents
        }

        public void actionPerformed(ActionEvent e) {
            renderizarAgendarAtendimento();
        }
    }

    private class RenderizarRegistrarAtendimento extends AbstractAction {
        private RenderizarRegistrarAtendimento() {
            // JFormDesigner - Action initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            putValue(NAME, "Registrar atendimento");
            // JFormDesigner - End of action initialization  //GEN-END:initComponents
        }

        public void actionPerformed(ActionEvent e) {
            renderizarRegistrarAtendimento();
        }
    }

    private class RenderizarListagemServicos extends AbstractAction {
        private RenderizarListagemServicos() {
            // JFormDesigner - Action initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            putValue(NAME, "Lista de servi\u00e7os");
            // JFormDesigner - End of action initialization  //GEN-END:initComponents
        }

        public void actionPerformed(ActionEvent e) {
            renderizarListagemServico();
        }
    }

    private class RenderizarCriarServicos extends AbstractAction {
        private RenderizarCriarServicos() {
            // JFormDesigner - Action initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            putValue(NAME, "Criar servi\u00e7o");
            // JFormDesigner - End of action initialization  //GEN-END:initComponents
        }

        public void actionPerformed(ActionEvent e) {
            renderizarCriarServico();
        }
    }
}
