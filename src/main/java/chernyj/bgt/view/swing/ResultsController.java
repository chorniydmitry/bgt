package chernyj.bgt.view.swing;

import javax.swing.JFrame;

import chernyj.bgt.model.Player;

public class ResultsController {

	public ResultsFrame dialog;

	public ResultsController(ResultsFrame dialog) {
		this.dialog = dialog;
	}

	public void showResult(Player mainPlayer) {
		dialog.update(mainPlayer.getHeroId(), mainPlayer.getPlace());
		dialog.setExtendedState(JFrame.NORMAL);

	}

}
