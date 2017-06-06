package hello;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
@EnableScheduling
@SpringBootApplication
public class SampleController {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
    
    @RequestMapping("/data")
    @ResponseBody
    MyData getData() {
    	MyData data = new MyData();
//    	List<String> categoryList = new ArrayList<String>();
//    	categoryList.add("ÉÏÒÂ");
//    	categoryList.add("¿ã×Ó");
//		data.setCategoryList(categoryList);
//		
//		List<String> dataList = new ArrayList<String>();
//		dataList.add("20");
//		dataList.add("80");
//		data.setDataList(dataList);
    	
    	List<String> categoryList = new ArrayList<String>();
    	
    	List<String> dataList = new ArrayList<String>();
    	try {
			List<String> lineList = FileUtils.readLines(new File("data.txt"), "UTF-8");
			for(String oneLine : lineList) {
				String[] oneLineArr = oneLine.trim().split("\\s+");
				categoryList.add(oneLineArr[oneLineArr.length - 1]);
				dataList.add(oneLineArr[2]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	data.setCategoryList(categoryList);
    	data.setDataList(dataList);
    	return data;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }
}

