package hello;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
    	System.out.println("here");
        log.info("The time is now {}", dateFormat.format(new Date()));
        
        try {
			ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "jstat -gcutil 1032");
			System.out.println("Run echo command");
			Process process = pb.start();
			int errCode = process.waitFor();
			System.out.println("Echo command executed, any errors? " + (errCode == 0 ? "No" : "Yes"));
			String outputStr = output(process.getInputStream());
			System.out.println("Echo Output:\n" + outputStr);
			String usefulLine = outputStr.split(System.getProperty("line.separator"))[1];
			System.out.println("usefulLine:\n" + usefulLine);
			FileUtils.writeStringToFile(new File("data.txt"), usefulLine + " " + dateFormat.format(new Date()) + System.getProperty("line.separator"), "UTF-8", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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