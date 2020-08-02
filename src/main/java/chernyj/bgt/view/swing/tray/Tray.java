package chernyj.bgt.view.swing.tray;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.util.LinkedList;
import java.util.List;

import chernyj.bgt.utils.ApplicationConstants;
import chernyj.bgt.utils.I18nUtils;

/**
 * @author Chernyj Dmitry
 *
 */
public class Tray {
	private I18nUtils localization = new I18nUtils("i18n.Tray");

	private MenuItem miSettings = new MenuItem(localization.getText("settings"));
	private MenuItem miEditValues = new MenuItem(localization.getText("editValues"));
	private MenuItem miClose = new MenuItem(localization.getText("close"));

	private PopupMenu puTrayMenu = new PopupMenu();

	private TrayIcon trayIcon;

	private List<MenuItem> menuItems = new LinkedList<MenuItem>();
	
	public void showTray() {
		fillMenuItems();
		initTrayIcon();
		initTray();
	}

	private void fillMenuItems() {
		menuItems.add(miSettings);
		menuItems.add(miEditValues);
		menuItems.add(miClose);
	}
	
	public void displayMessage(String text) {
		trayIcon.displayMessage(text, null, MessageType.NONE);
	}

	private void initTrayIcon() {

		trayIcon = new TrayIcon(ApplicationConstants.APP_ICON, null, puTrayMenu);
		trayIcon.setImageAutoSize(true);
	}

	private void initTray() {
		if (!SystemTray.isSupported())
			return;

		for (MenuItem mi : menuItems) {
			puTrayMenu.add(mi);
		}

		SystemTray tray = SystemTray.getSystemTray();
		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	public void setInfoMessageInTray(String message) {
		trayIcon.displayMessage(ApplicationConstants.APPLICATION_NAME, message, TrayIcon.MessageType.INFO);
	}

	public void setErrorMessageInTray(String message) {
		trayIcon.displayMessage(ApplicationConstants.APPLICATION_NAME, message, TrayIcon.MessageType.ERROR);
	}

	public MenuItem getMiSettings() {
		return miSettings;
	}

	public void setMiSettings(MenuItem miSettings) {
		this.miSettings = miSettings;
	}

	public MenuItem getMiClose() {
		return miClose;
	}

	public void setMiClose(MenuItem miClose) {
		this.miClose = miClose;
	}

	public MenuItem getMiEditValues() {
		return miEditValues;
	}

	public void setMiEditValues(MenuItem miEditValues) {
		this.miEditValues = miEditValues;
	}
	
}
