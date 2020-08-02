package chernyj.bgt.view.swing.tray;

import static chernyj.bgt.utils.ApplicationConstants.*;

import chernyj.bgt.model.BattlegroundsAnalyser;
import chernyj.bgt.utils.ApplicationConfiguration;
import chernyj.bgt.utils.LogFileReader;
import chernyj.bgt.utils.LogFileUtils;
import chernyj.bgt.view.swing.editValues.EditValuesController;
import chernyj.bgt.view.swing.editValues.EditValuesDialog;
import chernyj.bgt.view.swing.settings.SettingsController;
import chernyj.bgt.view.swing.settings.SettingsDialog;

public class TrayController {

	private Tray tray;
	private BattlegroundsAnalyser analyzer;

	public TrayController(Tray tray) {
		this.tray = tray;
		
		setListeners();

		tray.showTray();
		
		initAnalyzer();
	}
	
	private void initAnalyzer() {
		LogFileReader reader = new LogFileReader(ApplicationConfiguration.getItem("hearthsone.path"));

		analyzer = new BattlegroundsAnalyser();
		
		reader.register(new LogFileUtils());
		reader.register(analyzer);

		reader.run();
	}

	private void setListeners() {
		tray.getMiSettings().addActionListener(l -> doOpenSettings());
		tray.getMiEditValues().addActionListener(l -> doEditValues());
		tray.getMiClose().addActionListener(l -> doExit());
	}

	private void doEditValues() {
		new EditValuesController(new EditValuesDialog(200, 100) ,analyzer);
	}

	private void doExit() {
		System.exit(0);
	}

	private void doOpenSettings() {
		new SettingsController(new SettingsDialog(SETTINGSDIALOG_WIDTH, SETTINGSDIALOG_HEIGHT));

	}

}
