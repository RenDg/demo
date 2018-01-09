package com.yz.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
/**
 * �ļ��ϴ����ص�Controller
 * @author lenovo
 *
 */
@Controller
public class AjaxfileUploadController {
	 /**
     * ���������õ���MultipartFile[] myfiles����,����ǰ̨��Ҫ��<input type="file" name="myfiles"/>
     * �ϴ��ļ���Ϻ󷵻ظ�ǰ̨[0`filepath],0��ʾ�ϴ��ɹ�(����ϴ�����ļ�·��),1��ʾʧ��(���ʧ������)
     */
    @RequestMapping("ajaxfileUpload")
     public String addUser( @RequestParam("myfiles") MultipartFile[] myfiles, HttpServletRequest request, HttpServletResponse response) throws IOException{
    	//����ʵ���ļ��ϴ������õ���commons.io.FileUtils��,�����Զ��ж�/upload�Ƿ����,�����ڻ��Զ�����
        String realPath = request.getSession().getServletContext().getRealPath("/upload/");
        //������Ӧ��ǰ̨���ݵ����ݸ�ʽ
        response.setContentType("text/plain; charset=UTF-8");
        //������Ӧ��ǰ̨���ݵ�PrintWriter����
        PrintWriter out = response.getWriter();
        //�ϴ��ļ���ԭ��(���ϴ�ǰ���ļ�����)
        String originalFilename = null;
        for(MultipartFile myfile : myfiles){
            if(myfile.isEmpty()){
                out.print("1`��ѡ���ļ����ϴ�");
                out.flush();
                return null;
            }else{
                originalFilename = myfile.getOriginalFilename();
                try {
                    //���ﲻ�ش���IO���رյ�����,��ΪFileUtils.copyInputStreamToFile()�����ڲ����Զ����õ���IO���ص�
                    //�˴�Ҳ����ʹ��Spring�ṩ��MultipartFile.transferTo(File dest)����ʵ���ļ����ϴ�
//                    FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, originalFilename));
                    //�ļ��ϴ����������� 
                	File serverFile = new File(realPath + "/" + myfile.getOriginalFilename());
                    myfile.transferTo(serverFile);
                } catch (IOException e) {
                    System.out.println("�ļ�[" + originalFilename + "]�ϴ�ʧ��,��ջ�켣����");
                    e.printStackTrace();
                    out.print("1`�ļ��ϴ�ʧ�ܣ������ԣ���");
                    out.flush();
                    return null;
                }
            }
        }
        out.print("0`" + request.getContextPath() + "/upload/" + originalFilename);
        out.flush();
        return null;
    }
}
