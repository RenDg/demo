package com.yz.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
/**
 * 编辑器上传文件
 * @author lenovo
 *
 */
@Controller
public class CkeditorController {
	
	@RequestMapping("ckeditor")
	public String Ckeditor(){
		
		return "text/ckeditor";
	}
	@RequestMapping("/fileUpload")
    public void fileUpload (  HttpServletRequest request, HttpServletResponse response) throws IOException{
         response.setContentType("text/html");
         PrintWriter out = response.getWriter();
         StringBuffer sb = new StringBuffer();
         //通过request获取绝对路径
         String servletPath  = request.getSession().getServletContext().getRealPath("/upload/");
         //D:\tocmat_servers\apache-tomcat-6.0.35\webapps\hr
         //MultipartFile
         MultipartRequest multrequest = (MultipartRequest)request;
          Iterator<String> iter = multrequest.getFileNames();
          String  fileName = null;
          //通过迭代器遍历文件上传到集合
          while(iter.hasNext()){ 
              fileName = iter.next();
              MultipartFile file = multrequest.getFile(fileName);
                  if(file != null){
                       //取得当前上传文件的文件名称
                       String myFileName = file.getOriginalFilename();
                       //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                       if(myFileName.trim() !=""){
                            //重命名上传后的文件名
                            fileName = "demoUpload" + file.getOriginalFilename();
                            //定义上传路径
                            String filePath = servletPath + "/"+fileName;
                            File localFile = new File(filePath);//创建文件对象，指定服务器路径地址与文件
                            file.transferTo(localFile);//将文件上传到服务器上
                            //文件上传后返回，调用回掉地址// 将上传的图片的url返回给ckeditor
                            String fileUrl = request.getContextPath()+"/upload/"+fileName;//文件上传额服务器地址
                             String callback = request.getParameter("CKEditorFuncNum");
                           out.println("<script type=\"text/javascript\">");
                           out.println("window.parent.CKEDITOR.tools.callFunction("
                                   + callback + ",'" + fileUrl + "',''" + ")");
                           out.println("</script>");
                       }
                  }
          }
         out.flush();
         out.close();
    }
}
