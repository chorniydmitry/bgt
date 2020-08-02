package chernyj.bgt.mustache;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import chernyj.bgt.utils.ApplicationConfiguration;
import chernyj.bgt.utils.ApplicationConstants;

public class HTMLUpdater {

	private List<GamePlayed> gamesPlayedList = new ArrayList<>();
	private int currentMmr;
	private int startMmr;

	public HTMLUpdater() {
	}

	public void addResult(String hero, int place, int currentMmr) {
		String image = "res/" + hero + ".jpg";

		if (currentMmr == 0)
			currentMmr = startMmr;

		gamesPlayedList.add(new GamePlayed(image, place, currentMmr, startMmr));

		this.currentMmr = currentMmr;

		updateHtml();
	}

	private double getAveragePlace() {
		int sum = 0;

		for (GamePlayed gp : gamesPlayedList)
			sum += gp.getPlace();

		return gamesPlayedList.size() == 0 ? 0 : sum / gamesPlayedList.size();
	}

	private int getMmrRating() {
		return currentMmr - startMmr;
	}

	private String getMmrRatingString() {
		int mmrRating = getMmrRating();
		String returnValue = new String();
		if (mmrRating > 0)
			returnValue = "^" + String.valueOf(Math.abs(mmrRating));
		if (mmrRating < 0)
			returnValue = "v" + String.valueOf(Math.abs(mmrRating));
		if (mmrRating == 0)
			returnValue = "";

		return returnValue;
	}

	private String getMmrRatingColor() {
		if(getMmrRating() > 0) return "green";
		if(getMmrRating() < 0) return "red";
		
		return "white";
	}

	public void updateHtml() {
		Map<String, Object> context = new HashMap<>();

		context.put("games", gamesPlayedList);
		context.put("startMmr", startMmr);
		context.put("currentMmr", currentMmr);
		context.put("averagePlace", getAveragePlace());
		context.put("mmrRating", getMmrRatingString());
		context.put("mmrColor", getMmrRatingColor());

		StringWriter writer = new StringWriter();
		MustacheFactory mustacheFactory = new DefaultMustacheFactory();
		Mustache template = (Boolean.parseBoolean(ApplicationConfiguration.getItem("show.updatemmrdialog")))
				? mustacheFactory.compile("templates/game_with_mmr.mustache")
				: mustacheFactory.compile("templates/game.mustache");
		try {
			template.execute(writer, context).flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		writeToHtml(writer);

	}

	private void writeToHtml(StringWriter writer) {
		File fileToSave = new File(ApplicationConstants.GAMERESULTS_FILEPATH);
		if (!fileToSave.exists())
			try {
				fileToSave.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		PrintWriter prw;
		try {
			prw = new PrintWriter(ApplicationConstants.GAMERESULTS_FILEPATH);
			prw.println(writer.toString());
			prw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void clear() {
		writeToHtml(new StringWriter());
	}

	public List<GamePlayed> getGamesPlayedList() {
		return gamesPlayedList;
	}

	public void setGamesPlayedList(List<GamePlayed> gamesPlayedList) {
		this.gamesPlayedList = gamesPlayedList;
	}

	class GamePlayed {
		private String imageLink;
		private int place;
		private int startMmr;
		private int currentMmr;
		private double averagePlace;

		public GamePlayed(String imageLink, int place) {
			this.imageLink = imageLink;
			this.place = place;
		}

		public GamePlayed(String imageLink, int place, int startMmr, int currentMmr) {
			super();
			this.imageLink = imageLink;
			this.place = place;
			this.startMmr = startMmr;
			this.currentMmr = currentMmr;
		}

		public String getImageLink() {
			return imageLink;
		}

		public void setImageLink(String imageLink) {
			this.imageLink = imageLink;
		}

		public int getPlace() {
			return place;
		}

		public void setPlace(int place) {
			this.place = place;
		}

		public int getStartMmr() {
			return startMmr;
		}

		public void setStartMmr(int startMmr) {
			this.startMmr = startMmr;
		}

		public int getCurrentMmr() {
			return currentMmr;
		}

		public void setCurrentMmr(int currentMmr) {
			this.currentMmr = currentMmr;
		}

		public double getAveragePlace() {
			return averagePlace;
		}

		public void setAveragePlace(double averagePlace) {
			this.averagePlace = averagePlace;
		}

	}

	public void setCurrentMmr(int mmr) {
		this.currentMmr = mmr;
		updateHtml();
	}

	public void setStartMmr(int startMmr) {
		this.startMmr = startMmr;
		updateHtml();

	}
}
