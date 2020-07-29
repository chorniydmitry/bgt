package chernyj.bgt.view.swing.settings;

import java.io.File;
import java.util.Locale;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import chernyj.bgt.utils.ApplicationConfiguration;
import chernyj.bgt.utils.ApplicationConstants;
import chernyj.bgt.utils.I18nUtils;

public class SettingsController {

	private SettingsDialog dialog;

	public SettingsController(SettingsDialog dialog) {
		this.dialog = dialog;

		loadAppConfig();

		setListeners();
	}
	
	
	private void loadCbAvailibleLanguages() {
		String language = ApplicationConfiguration.getItem("language");
		String country = ApplicationConfiguration.getItem("country");
		
		Locale[] locales = ApplicationConstants.AVAILIBLE_LOCALES;
		
		int selected = 0;
		
		for (int i = 0; i < locales.length; i++) {
			if(locales[i].equals(new Locale(language,country)))
				selected = i;
			dialog.getCbAvailibleLanguages().addItem(locales[i].getDisplayLanguage());
		}
		
		dialog.getCbAvailibleLanguages().setSelectedIndex(selected);
	}

	private void loadAppConfig() {
		
		loadCbAvailibleLanguages();

		String pathToHS = ApplicationConfiguration.getItem("hearthsone.path").replace(ApplicationConstants.POWERLOG_ADDITON_PATH, "");

		boolean showingPreviousResultsDialog = Boolean
				.parseBoolean(ApplicationConfiguration.getItem("show.prevresultdialog"));
		boolean showingUpdateMMRDialog = Boolean.parseBoolean(ApplicationConfiguration.getItem("show.updatemmrdialog"));
		boolean useGameResultHTML = Boolean.parseBoolean(ApplicationConfiguration.getItem("use.gameresultshtml"));

		dialog.getLblPathToHearthstone().setText(pathToHS);

		dialog.getCbShowResultPopup().setSelected(showingPreviousResultsDialog);
		dialog.getCbShowMMRUpdateDialog().setSelected(showingUpdateMMRDialog);
		dialog.getCbUpdateFileForStream().setSelected(useGameResultHTML);

	}

	private void setListeners() {
		dialog.getBtnChangePathToHearthstone().addActionListener(l -> doChangePathToHearthstone());
		dialog.getCbShowResultPopup().addActionListener(l -> doShowResultPopupAction());
		dialog.getCbUpdateFileForStream().addActionListener(l -> doUseUpateFileForStreamAction());
		dialog.getCbShowMMRUpdateDialog().addActionListener(l -> doShowMRRUpdateDialogAction());
		dialog.getCbAvailibleLanguages().addActionListener(l -> doChangeLanguageAction());
	}

	private void doChangeLanguageAction() {
		String selectedLanguage = String.valueOf(dialog.getCbAvailibleLanguages().getSelectedItem());
		
		Locale[] locales = ApplicationConstants.AVAILIBLE_LOCALES;
		
		String language = new String();
		String country = new String();
		
		for(int i = 0; i < locales.length; i++) {
			if(locales[i].getDisplayLanguage().equals(selectedLanguage)) {
				language = locales[i].getLanguage();
				country = locales[i].getCountry();
			}
		}
		
		ApplicationConfiguration.saveItem("language", language);
		ApplicationConfiguration.saveItem("country", country);
		
		
	}


	private void doUseUpateFileForStreamAction() {
		boolean isSelected = dialog.getCbUpdateFileForStream().isSelected();
		if(isSelected) {
			dialog.getCbShowMMRUpdateDialog().setEnabled(true);
		} else {
			dialog.getCbShowMMRUpdateDialog().setEnabled(false);
			dialog.getCbShowMMRUpdateDialog().setSelected(false);
			
		}
		
		ApplicationConfiguration.saveItem("use.gameresultshtml", String.valueOf(isSelected));
		ApplicationConfiguration.saveItem("show.updatemmrdialog", String.valueOf(dialog.getCbShowMMRUpdateDialog().isSelected()));
	
	}

	private void doShowMRRUpdateDialogAction() {
		boolean isSelected = dialog.getCbShowMMRUpdateDialog().isSelected();
		
		ApplicationConfiguration.saveItem("show.updatemmrdialog", String.valueOf(isSelected));
	
	}

	private void doShowResultPopupAction() {
		boolean isSelected = dialog.getCbShowResultPopup().isSelected();
		ApplicationConfiguration.saveItem("show.prevresultdialog", String.valueOf(isSelected));
	}

	private void doChangePathToHearthstone() {
		I18nUtils localization = new I18nUtils(SettingsDialog.class.getCanonicalName());
		
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle(localization.getText("selectDirectory"));
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		if (chooser.showOpenDialog(dialog) == JFileChooser.APPROVE_OPTION) {
			String selectedPath = String.valueOf(chooser.getSelectedFile() + ApplicationConstants.POWERLOG_ADDITON_PATH);

			if (!new File(selectedPath).exists()) {
				JOptionPane.showMessageDialog(dialog, localization.getText("wrongDirectory"), localization.getText("error"),
						JOptionPane.ERROR_MESSAGE, null);
				return;
			}
			ApplicationConfiguration.saveItem("hearthsone.path", selectedPath);
		} else {
			System.out.println("No Selection ");
		}

		loadAppConfig();

	}

}
