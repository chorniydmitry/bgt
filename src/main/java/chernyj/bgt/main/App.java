package chernyj.bgt.main;


import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import chernyj.bgt.model.BattlegroundsAnalyser;
import chernyj.bgt.utils.ApplicationConfiguration;
import chernyj.bgt.utils.LogFileReader;
import chernyj.bgt.utils.LogFileUtils;
import chernyj.bgt.view.swing.tray.Tray;
import chernyj.bgt.view.swing.tray.TrayController;

public class App {
	
	private static void uiConfig() {
		 try {
		        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		    }catch(Exception ex) {
		        ex.printStackTrace();
		    }
		
	}

	public static void appStart() {
		uiConfig();
		
		new TrayController(new Tray());

		LogFileReader reader = new LogFileReader(ApplicationConfiguration.getItem("hearthsone.path"));

		BattlegroundsAnalyser game = new BattlegroundsAnalyser();
		
		reader.register(new LogFileUtils());
		reader.register(game);

		reader.run();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				appStart();
				
			}
		});
	}
}
