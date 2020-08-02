package chernyj.bgt.view.swing.settings;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import chernyj.bgt.utils.I18nUtils;

public class SettingsDialog extends JDialog {
	private static final long serialVersionUID = 6816002482388563385L;
	
	private I18nUtils localization = new I18nUtils("i18n.SettingsDialog");
	private JLabel lblPathToHearthstoneCaption = new JLabel(localization.getText("pathToHS"));
	private JLabel lblPathToHearthstone = new JLabel();
	
	private JLabel lblLanguage = new JLabel(localization.getText("language"));
	
	private JComboBox<String> cbAvailibleLanguages = new JComboBox<>();

	private JCheckBox cbShowResultPopup = new JCheckBox(localization.getText("showPastResultsFrame"));
	private JCheckBox cbUpdateFileForStream = new JCheckBox(localization.getText("updateHtmlFile"));
	private JCheckBox cbShowMMRUpdateDialog = new JCheckBox(localization.getText("showMmrInputDialog"));

	private JButton btnChangePathToHearthstone = new JButton(localization.getText("change"));

	public SettingsDialog(int width, int height) {
		this.setSize(new Dimension(width, height));
		this.setTitle(localization.getText("title"));
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);

		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		layoutDialog();
		this.setVisible(true);
	}

	private void layoutDialog() {
		this.setLayout(new GridBagLayout());

		// 1st row
		this.add(lblPathToHearthstoneCaption, new GridBagConstraints(0, 0, 3, 1, 1, 1, GridBagConstraints.NORTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 1, 1));
		
		// 2nd row
		this.add(lblPathToHearthstone, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 1, 1));

		this.add(btnChangePathToHearthstone, new GridBagConstraints(2, 1, 1, 1, 1, 1, GridBagConstraints.NORTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 1, 1));
		
		// 3rd row
		this.add(lblLanguage, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.NORTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 1, 1));
		
		this.add(cbAvailibleLanguages, new GridBagConstraints(1, 2, 2, 1, 1, 1, GridBagConstraints.NORTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 1, 1));
		

		// 4th row
		this.add(cbShowResultPopup, new GridBagConstraints(0, 3, 3, 1, 1, 1, GridBagConstraints.NORTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 1, 1));

		// 5th row
		this.add(cbUpdateFileForStream, new GridBagConstraints(0, 4, 3, 1, 1, 1, GridBagConstraints.NORTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 1, 1));
		
		// 6th row
		this.add(cbShowMMRUpdateDialog, new GridBagConstraints(0, 5, 3, 1, 1, 1, GridBagConstraints.NORTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 1, 1));

	}

	public JLabel getLblPathToHearthstone() {
		return lblPathToHearthstone;
	}

	public void setLblPathToHearthstone(JLabel lblPathToHearthstone) {
		this.lblPathToHearthstone = lblPathToHearthstone;
	}

	public JCheckBox getCbShowResultPopup() {
		return cbShowResultPopup;
	}

	public void setCbShowResultPopup(JCheckBox cbShowResultPopup) {
		this.cbShowResultPopup = cbShowResultPopup;
	}

	public JCheckBox getCbUpdateFileForStream() {
		return cbUpdateFileForStream;
	}

	public void setCbUpdateFileForStream(JCheckBox cbUpdateFileForStream) {
		this.cbUpdateFileForStream = cbUpdateFileForStream;
	}

	public JCheckBox getCbShowMMRUpdateDialog() {
		return cbShowMMRUpdateDialog;
	}

	public void setCbShowMMRUpdateDialog(JCheckBox cbShowMMRUpdateDialog) {
		this.cbShowMMRUpdateDialog = cbShowMMRUpdateDialog;
	}

	public JButton getBtnChangePathToHearthstone() {
		return btnChangePathToHearthstone;
	}

	public void setBtnChangePathToHearthstone(JButton btnChangePathToHearthstone) {
		this.btnChangePathToHearthstone = btnChangePathToHearthstone;
	}

	public JComboBox<String> getCbAvailibleLanguages() {
		return cbAvailibleLanguages;
	}

	public void setCbAvailibleLanguages(JComboBox<String> cbAvailibleLanguages) {
		this.cbAvailibleLanguages = cbAvailibleLanguages;
	}
	
}
