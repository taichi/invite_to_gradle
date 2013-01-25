package jp.example;

import java.io.InputStream;
import java.util.Properties;

public class Greeter {

	public String getGreeting() throws Exception {
		Properties prop = new Properties();
		InputStream in = null;
		try {
			in = getClass().getResourceAsStream("/props.xml");
			prop.loadFromXML(in);
			return prop.getProperty("greet");
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}
}
