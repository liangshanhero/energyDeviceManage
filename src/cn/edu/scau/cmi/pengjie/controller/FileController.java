package cn.edu.scau.cmi.pengjie.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.scau.cmi.dao.ProjectDAO;
import cn.edu.scau.cmi.domain.Project;
import cn.edu.scau.cmi.domain.Staff;

import cn.edu.scau.cmi.domain.Project;

@Controller("FileController")
public class FileController {

	@Autowired
	ProjectDAO projectDAO;

	// 使用方法二上传文件
	@RequestMapping("/fileUpload")
	public String fileUpload(@RequestParam("file") CommonsMultipartFile file,	HttpServletRequest req) throws IOException {
		CreateDataDir();
		String value = req.getParameter("select");
		String reqpath = null;
		switch (value) {
			case "LED":			reqpath = "d:/devicemanage/temp/LED/";			break;
			case "中央空调":		reqpath = "d:/devicemanage/temp/CAC/";			break;
			case "热水":			reqpath = "d:/devicemanage/temp/WH/";			break;
		}
		System.out.println(file.toString());
		System.out.println(reqpath);
		long startTime = System.currentTimeMillis();
		System.out.println("fileName? + file.getOriginalFilename()");
		String path = reqpath + file.getOriginalFilename();
		File newFile = new File(path);
		// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
		file.transferTo(newFile);
		long endTime = System.currentTimeMillis();
		System.out.println("方法二的运行时间 "+ String.valueOf(endTime - startTime) + "ms");
		req.getSession().setAttribute("uploadMessage", "upload successfully");
		return "../../fileHandler.jsp";
	}

	// 使用方法三上传文�?
	@RequestMapping("/springUpload")
	public String springUpload(HttpServletRequest request)
			throws IllegalStateException, IOException {

		System.out.println("here it is ");

		System.out.println(request.getParameter("select"));

		long startTime = System.currentTimeMillis();
		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// �?��form中是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件�?
			Iterator iter = multiRequest.getFileNames();

			while (iter.hasNext()) {
				// �?��遍历�?��文件
				MultipartFile file = multiRequest.getFile(iter.next()
						.toString());
				if (file != null) {
					String path = "F:/devicemanage"
							+ file.getOriginalFilename();
					// 上传
					file.transferTo(new File(path));
				}
			}

		}
		long endTime = System.currentTimeMillis();
		System.out.println("方法三的运行时间�? + String.valueOf(endTime - startTime"
				+ "ms");
		return "/success";
	}

	public static void CreateDataDir() {
		String basePath = "d:/devicemanage";
		// temp path
		String tempRootPath = "d:/devicemanage/temp";
		// temperary Project path
		String LEDPath = "d:/devicemanage/temp/LED";
		String WHPath = "d:/devicemanage/temp/WH";
		String CACPath = "d:/devicemanage/temp/CAC";
		// permanent root path
		String permanentRootPath = "d:/devicemanage/permanent";
		// permanent project path
		String permanentLEDPath = "d:/devicemanage/permanent/LED";
		String permanentWHPath = "d:/devicemanage/permanent/WH";
		String permanentCACPath = "d:/devicemanage/permanent/CAC";

		File base = new File(basePath);
		if (!base.exists() && !base.isDirectory()) {
			base.mkdir();
			System.out.println("base");
		}
		File tempRoot = new File(tempRootPath);
		if (!tempRoot.exists() && !tempRoot.isDirectory()) {
			tempRoot.mkdir();
			System.out.println("temproot");
		}

		File LED = new File(LEDPath);
		if (!LED.exists() && !LED.isDirectory()) {
			LED.mkdir();
			System.out.println("LED");
		}
		File WH = new File(WHPath);
		if (!WH.exists() && !WH.isDirectory()) {
			WH.mkdir();
			System.out.println("WH");
		}
		File CAC = new File(CACPath);
		if (!CAC.exists() && !CAC.isDirectory()) {
			CAC.mkdir();
			System.out.println("CAC");
		}
		File permanentRoot = new File(permanentRootPath);
		if (!permanentRoot.exists() && !permanentRoot.isDirectory()) {
			permanentRoot.mkdir();
			System.out.println("pr");
		}

		File permanentLED = new File(permanentLEDPath);
		if (!permanentLED.exists() && !permanentLED.isDirectory()) {
			permanentLED.mkdir();
			System.out.println("pl");
		}
		File permanentWH = new File(permanentWHPath);
		if (!permanentWH.exists() && !permanentWH.isDirectory()) {
			permanentWH.mkdir();
			System.out.println("pw");
		}

		File permanentCAC = new File(permanentCACPath);
		if (!permanentCAC.exists() && !permanentCAC.isDirectory()) {
			permanentCAC.mkdir();
			System.out.println("pc");
		}

	}

	/*
	 * 以下是我写的 private void recordFile(Project project,CommonsMultipartFile file)
	 * throws IOException { // TODO Auto-generated method stub
	 * 
	 * //临时文件记录刚刚上传的文件 //和永久文件，需要修改名字后放进来的数据文件
	 * 
	 * System.out.println("获取到的文件"+file); String fileName=file.toString(); File
	 * recordFile=new File("d:/energyDeviceManage/fileUploadRecord.txt");
	 * if(!recordFile.exists()){ recordFile.createNewFile(); }else {
	 * //打开文件并在在文件中追加已经上传的文件 FileWriter writer = null; writer = new
	 * FileWriter(recordFile, true); writer.write(fileName+"\n");
	 * writer.close(); //这是个临时记录文件 }
	 * 
	 * File recordUploadFile=new
	 * File("d:/energyDeviceManage/recordFileUpload.txt"); Date date=new
	 * Date(System.currentTimeMillis()); DateFormat format1 = new
	 * SimpleDateFormat("yyyyMMddHHmmss"); String
	 * timeString=format1.format(date);
	 * fileName=project.getId()+project.getName()+fileName+timeString;
	 * 
	 * if(!recordUploadFile.exists()){ recordUploadFile.createNewFile(); } else{
	 * 
	 * FileWriter writer = null; writer = new FileWriter(recordUploadFile,
	 * true); writer.write(fileName+"\n"); writer.close(); }
	 * System.out.println("record this file!"); }
	 */
	// 以上是我写的

	// 子军写的----

	@RequestMapping("/getProjectFiles")
	public ModelAndView getProjectFiles(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Staff staff = (Staff) request.getSession().getAttribute("staff");
		System.out.println("123" + staff);
		Set<Project> allProjects = projectDAO.findStaffProjects(staff.getId(),
				-1, -1);
		System.out.println("456" + allProjects);
		mav.addObject("allProjects", allProjects);
		mav.setViewName("getProjectFiles.jsp");
		return mav;
	}

}
