package chernyj.bgt.view.swing.tray;

import static chernyj.bgt.utils.ApplicationConstants.*;

import chernyj.bgt.view.swing.settings.SettingsController;
import chernyj.bgt.view.swing.settings.SettingsDialog;

public class TrayController {

	private Tray tray;

	public TrayController(Tray tray) {
		this.tray = tray;

		setListeners();

		tray.showTray();
	}

	private void setListeners() {
		tray.getMiSettings().addActionListener(l -> doOpenSettings());
		tray.getMiEditValues().addActionListener(l -> doEditValues());
		tray.getMiClose().addActionListener(l -> doExit());
	}

	private Object doEditValues() {
		// TODO Auto-generated method stub
		return null;
	}

	private void doExit() {
		System.exit(0);
	}

	private void doOpenSettings() {
		new SettingsController(new SettingsDialog(SETTINGSDIALOG_WIDTH, SETTINGSDIALOG_HEIGHT));

	}

}
