package monitor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class TestProcessBuilder {

	@Test
	public void test1() throws Exception {
//		ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "echo", "This is ProcessBuilder Example from JCG");
//		ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "jstat -gcutil 2328");
//		System.out.println("Run echo command");
//		Process process = pb.start();
//		int errCode = process.waitFor();
//		System.out.println("Echo command executed, any errors? " + (errCode == 0 ? "No" : "Yes"));
//		String outputStr = output(process.getInputStream());
//		System.out.println("Echo Output:\n" + outputStr);
//		String usefulLine = outputStr.split(System.getProperty("line.separator"))[1];
//		System.out.println("usefulLine:\n" + usefulLine);
//		FileUtils.writeStringToFile(new File("data.txt"), usefulLine + System.getProperty("line.separator"), "UTF-8", true);
		
		System.in.read();
	}

	private static String output(InputStream inputStream) throws IOException {

		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;

		try {
			br = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + System.getProperty("line.separator"));
			}
		} finally {
			br.close();
		}
		return sb.toString();
	}

}
