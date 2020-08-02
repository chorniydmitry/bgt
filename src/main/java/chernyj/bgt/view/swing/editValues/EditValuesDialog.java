package chernyj.bgt.view.swing.editValues;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import chernyj.bgt.utils.I18nUtils;

public class EditValuesDialog extends JDialog {
	private static final long serialVersionUID = 7492051099032254052L;
	
	private static I18nUtils localization = new I18nUtils("i18n.EditValuesDialog");
	
	private JLabel lblStartMmrCaption = new JLabel(localization.getText("start"));
	private JLabel lblCurrentMmrCaption = new JLabel(localization.getText("current"));
	
	private JTextField tfStartMmr = new JTextField(20);
	private JTextField tfCurrentMmr = new JTextField(20);
	
	private JButton btnOk = new JButton(localization.getText("button"));
	
	
	public EditValuesDialog(int width, int height) {
		initDialog(width, height);
		layoutDialog();
	}
	
	private void initDialog(int width, int height) {
		this.setTitle(localization.getText("title"));
		this.setSize(new Dimension(width, height));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(3,2));
		this.setVisible(true);
	}
	
	private void layoutDialog() {
		this.add(lblStartMmrCaption);
		this.add(tfStartMmr);
		this.add(lblCurrentMmrCaption);
		this.add(tfCurrentMmr);
		this.add(new JLabel());
		this.add(btnOk);
	}

	public JTextField getTfStartMmr() {
		return tfStartMmr;
	}

	public void setTfStartMmr(JTextField tfStartMmr) {
		this.tfStartMmr = tfStartMmr;
	}

	public JTextField getTfCurrentMmr() {
		return tfCurrentMmr;
	}

	public void setTfCurrentMmr(JTextField tfCurrentMmr) {
		this.tfCurrentMmr = tfCurrentMmr;
	}

	public JButton getBtnOk() {
		return btnOk;
	}

	public void setBtnOk(JButton btnOk) {
		this.btnOk = btnOk;
	}

}
