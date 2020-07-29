package chernyj.bgt.model;

import static chernyj.bgt.utils.ApplicationConstants.*;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import chernyj.bgt.mustache.HTMLUpdater;
import chernyj.bgt.utils.AnalyserConfiguration;
import chernyj.bgt.utils.ApplicationConfiguration;
import chernyj.bgt.utils.observers.LogFileObserver;
import chernyj.bgt.view.swing.ResultsController;
import chernyj.bgt.view.swing.ResultsFrame;
import chernyj.bgt.view.swing.inputMmr.InputMmrDialog;

public class BattlegroundsAnalyser implements LogFileObserver {

	private ArrayList<Player> playersList = new ArrayList<>();

	private ResultsController resultsController;

	private Player mainPlayer;

	private int startMmr;
	private int currentMmr;
	private boolean isCurrentGameTracking = false;

	private HTMLUpdater htmlUpdater = new HTMLUpdater();
	
	public BattlegroundsAnalyser() {
		if(Boolean.parseBoolean(ApplicationConfiguration.getItem("use.gameresultshtml")))
			new HTMLUpdater().clear();
		
		if (Boolean.parseBoolean(ApplicationConfiguration.getItem("show.prevresultdialog"))) {
			ResultsController resController = new ResultsController(new ResultsFrame(RESULTSFRAME_WIDTH, RESULTSFRAME_HEIGHT, APPLICATION_NAME));
			setResultsController(resController);
		}

		if (Boolean.parseBoolean(ApplicationConfiguration.getItem("show.updatemmrdialog"))) {
			int startMmr = InputMmrDialog.showInputDialog(null, null, 0, 0, null);
			setStartMmr(startMmr);
		}
	}
	
	public void setResultsController(ResultsController resultsController) {
		this.resultsController = resultsController;
	}
	
	private void updateHtml() {
		
		if(!AnalyserConfiguration.usingHtml())
			return;

		if (AnalyserConfiguration.updatingMmr() && isCurrentGameTracking) {

			Icon icon = new ImageIcon(new ImageIcon(
					BattlegroundsAnalyser.class.getResource("/images/heroes/" + mainPlayer.getHeroId() + ".jpg"))
							.getImage()
							.getScaledInstance(HERO_IMAGE_WIDTH, HERO_IMAGE_HEIGHT, Image.SCALE_DEFAULT));

			currentMmr = InputMmrDialog.showInputDialog(null, icon, mainPlayer.getPlace(), 0, null);
			htmlUpdater.setCurrentMmr(currentMmr);
		}

		if (currentMmr == 0)
			currentMmr = startMmr;

		htmlUpdater.addResult(mainPlayer.getHeroId(), mainPlayer.getPlace(), 0);
		htmlUpdater.setCurrentMmr(currentMmr);

	}

	private void updateResultsDialog() {
		if (AnalyserConfiguration.showingResultsDialog()) {
			if (resultsController == null)
				resultsController = new ResultsController(new ResultsFrame(RESULTSFRAME_WIDTH, RESULTSFRAME_HEIGHT, APPLICATION_NAME));
			resultsController.showResult(mainPlayer);
		}
	}

	private void showResults() {
		mainPlayer = getMainPlayer();
		
		updateHtml();
		updateResultsDialog();

	}

	private void setMainPlayer(Map<String, String> data) {
		Player player = new Player();
		player.setName(data.get("playerName"));
		player.setbTag(data.get("playerBTag"));
		player.setId(Integer.parseInt(data.get("playerId")));
		player.setMainPlayer(true);

		player.setHeroId(data.get("playerHeroId"));

		playersList.add(player);
	}

	private Player getMainPlayer() {
		for (Player player : playersList) {
			if (player.isMainPlayer())
				return player;
		}

		return null;
	}

	private void setPlayerIdsAndHeroes(Map<String, String> data) {
		Player player = new Player();

		player.setId(Integer.parseInt(data.get("playerId")));

		player.setHeroId(data.get("playerHeroId"));

		playersList.add(player);
	}

	private void updatePlayerPlace(Map<String, String> data) {
		String heroId = data.get("playerHeroId");
		Player player = getPlayerByHeroId(heroId);
		if (player != null)
			player.setPlace(Integer.parseInt(data.get("playerPlace")));
	}

	private Player getPlayerByHeroId(String heroId) {
		for (Player player : playersList)
			if (player.getHeroId().equals(heroId))
				return player;
		return null;
	}

	@Override
	public void update(Map<String, String> data) {
//		if (data.get("infoType").equals("gameStarted"))
//			setGameStartedTime(data);

		if (data.get("infoType").equals("mainPlayer"))
			setMainPlayer(data);

		if (data.get("infoType").equals("playerHero"))
			setPlayerIdsAndHeroes(data);

		if (data.get("infoType").equals("playerPlace"))
			updatePlayerPlace(data);

		if (data.get("infoType").equals("gameFinished"))
			finishGame(data);

		if (data.get("infoType").equals("fileReachedEOF"))
			isCurrentGameTracking = true;
	}

	private void finishGame(Map<String, String> data) {
		showResults();
		playersList = new ArrayList<>();
	}

	public int getStartMmr() {
		return startMmr;
	}

	public void setStartMmr(int startMmr) {
		this.startMmr = startMmr;
		htmlUpdater.setStartMmr(startMmr);
		htmlUpdater.setCurrentMmr(startMmr);
	}

}
