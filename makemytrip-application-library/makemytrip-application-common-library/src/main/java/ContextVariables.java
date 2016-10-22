
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ContextVariables {
	public static final String CONFIG_FILENAME = "config.properties";
	public static final String RUBY_PATH = "C:\\Ruby192\\bin\\";
	public static String PROJECT_PATH = "";
	static {
		try {
			PROJECT_PATH = new java.io.File(".").getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String SCRIPTS_PATH = PROJECT_PATH + "\\scripts";
	public static String UTILS_PATH = PROJECT_PATH + "\\utils";
	public static String CONFIG_PATH = PROJECT_PATH + "\\conf";
	public static String TESTS_PATH = PROJECT_PATH + "\\tests";

	public static final Map<String, String> CONFIG_DATA = new HashMap<String, String>();
	static { // This block builds Map from the properties file
		buildConfigData();
	}
	private final static Map CONTEXT_VARS = new HashMap<Object, Object>();

	//
	// ----------------Methods portion------------------------------------
	//
	public static Map getEnvironmentVars() {
		return CONTEXT_VARS;
	}

	public static void buildConfigData() {
		Properties prop = GenericFunctions
				.loadPropertiesFromThisFile(ContextVariables.CONFIG_PATH + "\\" + ContextVariables.CONFIG_FILENAME);
		Set<Map.Entry<Object, Object>> entrySet = prop.entrySet();
		Iterator<Map.Entry<Object, Object>> i = entrySet.iterator();
		while (i.hasNext()) {
			Map.Entry<Object, Object> element = i.next();
			CONFIG_DATA.put("" + element.getKey(), "" + element.getValue());
		}
	}
}
