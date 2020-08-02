package chernyj.bgt.view.swing.editValues;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.UIManager;

import chernyj.bgt.model.BattlegroundsAnalyser;

public class EditValuesController {

	private EditValuesDialog dialog;
	private BattlegroundsAnalyser analyzer;

	public EditValuesController(EditValuesDialog dialog, BattlegroundsAnalyser analyzer) {
		this.dialog = dialog;
		this.analyzer = analyzer;

		loadValues();

		setListeners();
	}

	private void loadValues() {
		dialog.getTfStartMmr().setText(String.valueOf(analyzer.getStartMmr()));
		dialog.getTfCurrentMmr().setText(String.valueOf(analyzer.getCurrentMmr()));
	}

	private void setListeners() {
		dialog.getBtnOk().addActionListener(l -> doButtonOkAction());
	}

	private void doButtonOkAction() {
		if (!isValid(dialog.getTfStartMmr()) || !isValid(dialog.getTfCurrentMmr()))
			return;
			
		String startMmr = dialog.getTfStartMmr().getText();
		String currentMmr = dialog.getTfCurrentMmr().getText();

		analyzer.setStartMmr(Integer.parseInt(startMmr));
		analyzer.setCurrentMmr(Integer.parseInt(currentMmr));
		
		dialog.dispose();
	}

	private boolean isValid(JTextField tf) {
		try {
			Integer.parseInt(tf.getText());
			tf.setBackground(UIManager.getColor ("TextField.background"));
			return true;
		} catch (NumberFormatException e) {
			tf.setBackground(Color.red);
			return false;
		}

	}

}
