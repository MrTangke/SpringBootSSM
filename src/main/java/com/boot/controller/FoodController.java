package com.boot.controller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boot.config.FMUtils;
import com.boot.config.PageUtil;
import com.boot.entity.Foods;
import com.boot.service.FoodService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Controller
@MapperScan("com.boot.mapper")
@RequestMapping("/food")
public class FoodController {

	@Autowired(required = false)
	private FoodService foodService ; 
	
	@Autowired(required = false)
    Configuration cfg;
	
	@RequestMapping("/selectFoodList")
	public ModelAndView selectFoodList(ModelAndView modelAndView , HttpServletRequest request , Foods foods , String currentPage) throws Exception{
		System.out.println("----------------"+foods.getFoodName()+"-------------------");
		
		//分页获得中记录条数
		Integer count = foodService.seleFoodCount(foods);
		//工具类调用 当前页  每页记录条数  总条数
		PageUtil page = new PageUtil(currentPage, 5, count);
		
		//创建map集合存放查询数据
		Map<String,Object> map = new HashMap<String, Object>();
		//记录
		map.put("PageRecord", page.getPageRecord());
		//每页条数
		map.put("PageSize", page.getPageSize());
		//模糊条件
		map.put("foodName", foods.getFoodName());
		List<Foods> list = foodService.selectFoodList(map);
		
		/*System.out.println(ClassUtils.getDefaultClassLoader().getResource("").getPath());
		File path2 = new File(ResourceUtils.getURL("classpath:").getPath());
		System.out.println(path2.getAbsolutePath());
		File upload = new File(path2.getAbsolutePath(),"static/images/upload/");
		System.out.println("upload url:"+upload.getAbsolutePath());*/
	
		//生命绝对路径传送前台引用
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		request.setAttribute("basePath", basePath);
		
		//modelandview 输出数据
		//html页面路径
		modelAndView.setViewName("ftl/index");
		modelAndView.addObject("list", list);
		modelAndView.addObject("page", page);
		modelAndView.addObject("foodName", foods.getFoodName());
		
		//生成静态页打包数据
		HashMap<String,Object> map2 = new HashMap<String, Object>();
		map2.put("list", list);
		map2.put("page", page);
		map2.put("foodName", foods.getFoodName());
		
		FMUtils.createHtml(request, "index.ftl", "index.html", map2);
		
		return modelAndView ;
	}
	
	@RequestMapping("/toCreateHtml")
	private String toCreateHtml(Map<String,Object> root , Foods foods , String currentPage, HttpServletRequest request ){
				//分页获得中记录条数
				Integer count = foodService.seleFoodCount(foods);
				//工具类调用 当前页  每页记录条数  总条数
				PageUtil page = new PageUtil(currentPage, 5, count);
				
				//创建map集合存放查询数据
				Map<String,Object> map = new HashMap<String, Object>();
				//记录
				map.put("PageRecord", page.getPageRecord());
				//每页条数
				map.put("PageSize", page.getPageSize());
				//模糊条件
				map.put("foodName", foods.getFoodName());
				List<Foods> list = foodService.selectFoodList(map);
				//生命绝对路径传送前台引用
				String path1 = request.getContextPath();
				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path1+"/";
				//生成静态页打包数据
				root.put("list", list);
				root.put("page", page);
				root.put("foodName", foods.getFoodName());
				root.put("basePath", basePath);
	        try {
	            Template temp = cfg.getTemplate("ftl/index.ftl");
	            //以classpath下面的static目录作为静态页面的存储目录，同时命名生成的静态html文件名称
	            String path=this.getClass().getResource("/").toURI().getPath()+"static/index.html";
	            System.out.println(path.toString());
	            Writer file = new FileWriter(new File(path.substring(path.indexOf("/"))));
	            System.out.println(file.toString());
	            temp.process(root, file);
	            file.flush();
	            file.close();
	            return "redirect:/static/index.html" ;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return "" ;
	        } catch (TemplateException e) {
	            e.printStackTrace();
	            return "" ;
	        } catch (URISyntaxException e) {
	            e.printStackTrace();
	            return "" ;
	        }
        
    }
	
}
