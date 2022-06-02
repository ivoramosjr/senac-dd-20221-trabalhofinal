package petshop.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class MainView extends JFrame {

	private JFrame frame;
	private JPanel panel;
	private JMenuBar menuBar;
	private JMenu petsMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	MainView() {
		initialize();
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		petsMenu = new JMenu("Pets");
		menuBar.add(petsMenu);
		
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
