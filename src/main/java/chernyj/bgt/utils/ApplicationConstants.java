package chernyj.bgt.utils;

import java.awt.Image;
import java.io.File;
import java.util.Locale;

import javax.swing.ImageIcon;

public class ApplicationConstants {

	public static final int MAX_MINIONS_ON_BOARD = 7;
	public static final int PLAYERS_AMONUT = 7;
	public static final int HERO_IMAGE_WIDTH = 40;
	public static final int HERO_IMAGE_HEIGHT = 40;

	public static final String APPLICATION_NAME = "BATTLEGROUDS TRACKER";

	public static final Image APP_ICON = new ImageIcon(ApplicationConstants.class.getResource("/images/logo.png")).getImage();
	public static final String GAMERESULTS_FILEPATH = "games_results.html";
	
	public static final int RESULTSFRAME_WIDTH = 1000;
	public static final int RESULTSFRAME_HEIGHT = 110;
	
	public static final int SETTINGSDIALOG_WIDTH = 500;
	public static final int SETTINGSDIALOG_HEIGHT = 200;

	public static final String POWERLOG_ADDITON_PATH = File.separator + "Logs" + File.separator + "Power.log";
	
	public static final Locale[] AVAILIBLE_LOCALES = {new Locale("ru", "RU"), new Locale("en", "US")}; 

}
