package chernyj.bgt.utils;

public class AnalyserConfiguration {
	
	public static boolean usingHtml() {
		return Boolean.parseBoolean(ApplicationConfiguration.getItem("use.gameresultshtml"));
	}
	
	public static boolean updatingMmr() {
		return Boolean.parseBoolean(ApplicationConfiguration.getItem("show.updatemmrdialog"));
	}
	
	public static boolean showingResultsDialog() {
		return Boolean.parseBoolean(ApplicationConfiguration.getItem("show.prevresultdialog"));
	}


}
