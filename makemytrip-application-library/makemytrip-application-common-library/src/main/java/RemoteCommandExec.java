
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class RemoteCommandExec {

	/*
	 * public void testGetOutput() { System.out.println(
	 * "\n\n****This is the testGetOutput Method!****"); String s = null; String
	 * query = "dir "; try { Runtime runtime = Runtime.getRuntime(); InputStream
	 * input = runtime.exec("cmd /c " + query).getInputStream();
	 * BufferedInputStream buffer = new BufferedInputStream(input);
	 * BufferedReader commandResult = new BufferedReader(new
	 * InputStreamReader(buffer)); String line = ""; try { while ((line =
	 * commandResult.readLine()) != null) { s += line + "\n"; } } catch
	 * (Exception e) { e.printStackTrace(); } System.out.println(s); } catch
	 * (Exception e) { e.printStackTrace(); } }//end testGetOutput()
	 */
	/*
	 * public void ExecuteCmdOntThisHost(String ip, String user, String pwd)
	 * throws IOException { String currentDir = new java.io.File( "."
	 * ).getCanonicalPath(); File f = new File(currentDir+"\\scripts");
	 * 
	 * //Assert.assertTrue(f.exists(), "Verify if the ruby script exists");
	 * 
	 * 
	 * ProcessBuilder pb = new ProcessBuilder(currentDir+
	 * "\\scripts\\"+rubyscript); //, "myArg1", "myArg2");
	 * 
	 * //ProcessBuilder pb = new
	 * ProcessBuilder("C:\\Windows\\System32\\cmd.exe");
	 * 
	 * Map<String, String> env = pb.environment(); env.put("VAR1", "myValue");
	 * env.remove("OTHERVAR"); env.put("VAR2", env.get("VAR1") + "suffix");
	 * //pb.directory(f); //directory("myDir");
	 * 
	 * 
	 * //Process p = Runtime.getRuntime().exec(currentDir+"/utils/"+rubyscript);
	 * Process p = pb.start();
	 * 
	 * InputStream is = p.getInputStream(); InputStreamReader isr = new
	 * InputStreamReader(is); BufferedReader br = new BufferedReader(isr);
	 * String line;
	 * 
	 * System.out.printf("Output is:");
	 * 
	 * while ((line = br.readLine()) != null) { System.out.println(line); }
	 * 
	 * 
	 * 
	 * 
	 * Runtime r = Runtime.getRuntime(); Process p = null; String command;
	 * 
	 * File dir = new File("C:\\Automation_projects\\MakeMyTrip\\scripts\\");
	 * command=
	 * "C:\\Ruby192\\bin\\ruby.exe C:\\Automation_projects\\MakeMyTrip\\scripts\\executecmdonremoteserver.rb"
	 * ; p = r.exec(command,null,dir); InputStream is = p.getInputStream();
	 * InputStreamReader isr = new InputStreamReader(is); BufferedReader br =
	 * new BufferedReader(isr); String line; try { p.waitFor();
	 * System.out.printf("Output is:");
	 * 
	 * while ((line = br.readLine()) != null) { System.out.println(line); } }
	 * catch (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * 
	 * }
	 */

	public ArrayList<String> ExecuteThisRubyScript(String scriptName, String scriptParameters)
	/**
	 * This function executes ruby script with given parameters
	 */
	{
		ArrayList<String> output = new ArrayList<String>();
		CustomLogger.logMethodStart(); // To report method started
		String s = "";

		String command = "cmd /c " + EnvironmentVariables.RUBY_PATH + "ruby.exe " + scriptName + " " + scriptParameters
				+ "";
		System.out.println(command);
		try {
			Runtime r = Runtime.getRuntime();
			Process p = r.exec(command, null, null);
			p.waitFor();

			// System.out.printf("Output is: \n");

			InputStream input = p.getInputStream();
			BufferedInputStream buffer = new BufferedInputStream(input);
			BufferedReader commandResult = new BufferedReader(new InputStreamReader(buffer));
			String line = "";
			try {
				while ((line = commandResult.readLine()) != null) {
					// s += line + "\n";
					output.add(line);
				}
			} catch (Exception ee) {
				ee.printStackTrace();
			}
			// System.out.println(s);

			/*
			 * Iterator<String> itr = output.iterator(); while(itr.hasNext()){
			 * System.out.println(itr.next()); }
			 */
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		CustomLogger.logMethodEnd(); // To report method end
		return output;
	}

	public ArrayList<String> ExecuteCmdOnThisHost(String hostIP, String user, String pwd, String command)
			throws IOException {
		CustomLogger.logMethodStart(); // To report method started
		String rubyscript = null;
		try {
			rubyscript = this.getClass().getResource("executecmdonremoteserver.rb").toURI().getPath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		String parameters = "-h " + hostIP + " -u " + user + " -p " + pwd + " -c " + command + "";
		ArrayList<String> output = ExecuteThisRubyScript(rubyscript, parameters);
		CustomLogger.logMethodEnd(); // To report method end
		return output;
	}

}
