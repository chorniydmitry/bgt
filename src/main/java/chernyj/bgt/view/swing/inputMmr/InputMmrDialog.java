package chernyj.bgt.view.swing.inputMmr;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import chernyj.bgt.utils.I18nUtils;

public class InputMmrDialog {
	
	private static I18nUtils localization = new I18nUtils("i18n.InputMmrDialog");

	public static int showInputDialog(Component parent, Icon icon, int place, int lastMmrValue, String message) {
		
		message = message == null ? localization.getText("inputMmrValue") : message;
		
		JOptionPane optionPane = new JOptionPane(localization.getText("inputMmrValue"), JOptionPane.PLAIN_MESSAGE,
				JOptionPane.DEFAULT_OPTION, icon, null, lastMmrValue);
		optionPane.setWantsInput(true);
		
		String title = place == 0 ? localization.getText("inputMmr") : localization.getText("youTook") + place + localization.getText("place");

		JDialog dialog = optionPane.createDialog(parent, title);
		dialog.setLocation(10, 200);
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
		
		int returnValue = 0;
		try {
			returnValue = Integer.parseInt((String)optionPane.getInputValue());
		} catch(NumberFormatException e) { 
			showInputDialog(parent, icon, place, lastMmrValue, localization.getText("wrongInput"));
		}
		
		return returnValue;
	}
}
