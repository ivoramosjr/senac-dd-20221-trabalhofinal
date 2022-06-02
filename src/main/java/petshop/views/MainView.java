package petshop.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainView extends JFrame {

    private JPanel mainPanel;
    private JMenuBar menuBar;

    private JMenu petMenu = new JMenu("Pets");
    private JMenu attendanceMenu = new JMenu("Atendimentos");
    private JMenu serviceMenu = new JMenu("Serviços");
    private JMenu helpMenu = new JMenu("Ajuda");

    private JMenuItem addPetMenuItem = new JMenuItem("Adicionar pet");

    MainView() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(100, 100, 450, 300);
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        menuBar.add(petMenu);
        menuBar.add(attendanceMenu);
        menuBar.add(serviceMenu);
        menuBar.add(helpMenu);

        petMenu.add(addPetMenuItem);



        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        mainPanel.setLayout(new BorderLayout(0, 0));
        setContentPane(mainPanel);
    }

    public static void showScreen() {
        MainView mainView = new MainView();
        mainView.setLocationRelativeTo(null);
        mainView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainView.setVisible(true);
    }

}
