package com.boot.config;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.springframework.util.ResourceUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FMUtils {
	
	static Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
	
	public static void createHtml(HttpServletRequest request,String ftlName,String outName,Map<String,Object> map) throws Exception{
		
		cfg.setDirectoryForTemplateLoading(new File(ResourceUtils.getURL("classpath:").getPath()+"/templates/ftl"));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setOutputEncoding("UTF-8");		
		Template t = cfg.getTemplate(ftlName);
		
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		map.put("basePath", basePath);
		
		FileWriterWithEncoding w = new FileWriterWithEncoding(new File(ResourceUtils.getURL("classpath:").getPath()+("/"+outName)),"UTF-8");
		
		t.process(map, w);
		
		w.close();
		
	}
}
