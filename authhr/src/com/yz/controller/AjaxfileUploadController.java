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
 * 文件上传下载的Controller
 * @author lenovo
 *
 */
@Controller
public class AjaxfileUploadController {
	 /**
     * 这里这里用的是MultipartFile[] myfiles参数,所以前台就要用<input type="file" name="myfiles"/>
     * 上传文件完毕后返回给前台[0`filepath],0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述)
     */
    @RequestMapping("ajaxfileUpload")
     public String addUser( @RequestParam("myfiles") MultipartFile[] myfiles, HttpServletRequest request, HttpServletResponse response) throws IOException{
    	//这里实现文件上传操作用的是commons.io.FileUtils类,它会自动判断/upload是否存在,不存在会自动创建
        String realPath = request.getSession().getServletContext().getRealPath("/upload/");
        //设置响应给前台内容的数据格式
        response.setContentType("text/plain; charset=UTF-8");
        //设置响应给前台内容的PrintWriter对象
        PrintWriter out = response.getWriter();
        //上传文件的原名(即上传前的文件名字)
        String originalFilename = null;
        for(MultipartFile myfile : myfiles){
            if(myfile.isEmpty()){
                out.print("1`请选择文件后上传");
                out.flush();
                return null;
            }else{
                originalFilename = myfile.getOriginalFilename();
                try {
                    //这里不必处理IO流关闭的问题,因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
                    //此处也可以使用Spring提供的MultipartFile.transferTo(File dest)方法实现文件的上传
//                    FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, originalFilename));
                    //文件上传到服务器上 
                	File serverFile = new File(realPath + "/" + myfile.getOriginalFilename());
                    myfile.transferTo(serverFile);
                } catch (IOException e) {
                    System.out.println("文件[" + originalFilename + "]上传失败,堆栈轨迹如下");
                    e.printStackTrace();
                    out.print("1`文件上传失败，请重试！！");
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
