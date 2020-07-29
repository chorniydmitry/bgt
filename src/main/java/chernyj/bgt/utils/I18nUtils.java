package chernyj.bgt.utils;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

public class I18nUtils {
	
	private String bundleName = null;
	
	public I18nUtils(String bundleName) {
		this.bundleName = bundleName;
	}
	
	public String getText(String key) {
		Locale locale = new Locale(ApplicationConfiguration.getItem("language"), ApplicationConfiguration.getItem("country"));
        ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale);

        try {
			return new String(bundle.getString(key).getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return null;

	}
	
	public void setBundleName(String bundleName) {
		this.bundleName = bundleName;
	}

}
