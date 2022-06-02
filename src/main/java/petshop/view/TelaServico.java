package petshop.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaServico extends JPanel {
	private JTextField nameField;
	private JTextField valueField;

	/**
	 * Create the panel.
	 */
	public TelaServico() {
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("177px"),
				ColumnSpec.decode("130px"),
				ColumnSpec.decode("55px"),
				ColumnSpec.decode("294px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(66dlu;default)"),},
			new RowSpec[] {
				RowSpec.decode("39px"),
				RowSpec.decode("32px"),
				RowSpec.decode("37px"),
				RowSpec.decode("16px"),
				FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("16px"),
				FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC,
				RowSpec.decode("100px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblTitle = new JLabel("Criar servi\u00E7o");
		lblTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
		add(lblTitle, "1, 2, 6, 1, center, top");
		
		JLabel lblNewLabel = new JLabel("Nome do servi\u00E7o:");
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		add(lblNewLabel, "2, 4, fill, top");
		
		nameField = new JTextField();
		add(nameField, "2, 6, fill, top");
		nameField.setColumns(10);
		
		JTextPane descriptionField = new JTextPane();
		add(descriptionField, "4, 6, 1, 5, fill, fill");
		
		JLabel lblNewLabel_1 = new JLabel("Valor do servi\u00E7o:");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 12));
		add(lblNewLabel_1, "2, 8, fill, top");
		
		valueField = new JTextField();
		add(valueField, "2, 10, fill, top");
		valueField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Descri\u00E7\u00E3o do servi\u00E7o:");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 12));
		add(lblNewLabel_2, "4, 4, left, top");
		
		JButton btnConfirm = new JButton("Confirmar");
		btnConfirm.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(btnConfirm, "1, 16, 6, 1, center, default");

	}
}
