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
 * �༭���ϴ��ļ�
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
         //ͨ��request��ȡ����·��
         String servletPath  = request.getSession().getServletContext().getRealPath("/upload/");
         //D:\tocmat_servers\apache-tomcat-6.0.35\webapps\hr
         //MultipartFile
         MultipartRequest multrequest = (MultipartRequest)request;
          Iterator<String> iter = multrequest.getFileNames();
          String  fileName = null;
          //ͨ�������������ļ��ϴ�������
          while(iter.hasNext()){ 
              fileName = iter.next();
              MultipartFile file = multrequest.getFile(fileName);
                  if(file != null){
                       //ȡ�õ�ǰ�ϴ��ļ����ļ�����
                       String myFileName = file.getOriginalFilename();
                       //������Ʋ�Ϊ����,˵�����ļ����ڣ�����˵�����ļ�������
                       if(myFileName.trim() !=""){
                            //�������ϴ�����ļ���
                            fileName = "demoUpload" + file.getOriginalFilename();
                            //�����ϴ�·��
                            String filePath = servletPath + "/"+fileName;
                            File localFile = new File(filePath);//�����ļ�����ָ��������·����ַ���ļ�
                            file.transferTo(localFile);//���ļ��ϴ�����������
                            //�ļ��ϴ��󷵻أ����ûص���ַ// ���ϴ���ͼƬ��url���ظ�ckeditor
                            String fileUrl = request.getContextPath()+"/upload/"+fileName;//�ļ��ϴ����������ַ
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
