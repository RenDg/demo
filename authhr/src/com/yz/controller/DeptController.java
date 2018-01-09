package com.yz.controller;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yz.entity.Dept;
import com.yz.service.IdeptService;
import com.yz.vo.AjaxJson;
import com.yz.vo.Node;
import com.yz.vo.ZtreeNode;

@Controller
@RequestMapping("dept")
public class DeptController {
	@Autowired
	private  IdeptService deptservice;
	
	@RequestMapping("index")
	public String index(){
		
		return "dept/index";
	}
	
	// �б�ӷ�ҳ
	@RequestMapping("list")
	@ResponseBody
	public Map<String ,Object> list1(){
		 Map<String, Object> list = deptservice.list();		
		 return list;
	}
	
	//�����ת
	@RequestMapping("toadd")
	public String toadd(){
		
		return "dept/deptadd";
	}
	
	//�޸����
//	@RequestMapping("add")
//	@ResponseBody
//	public Integer add(Dept dept){
//		return deptservice.add_update(dept);
//	}
	
	//�޸����
	@RequestMapping("add")
	@ResponseBody
	public AjaxJson add(Dept dept){
		AjaxJson json=new AjaxJson();
		boolean flag=false;
		if(dept.getId()==null){
			dept.setCreateDate(new Date());
			flag =deptservice.add(dept);
			json.setMsg("�Բ������ʧ�ܣ�");
		}else{
			if(dept.getId().equals(dept.getPid())){
				json.setMsg("�Բ��𣬲����޸ı����ţ�");
				json.setSuccess(false);
				return json;
			}
			dept.setUpdateDate(new Date());
			flag=deptservice.update(dept);
			json.setMsg("�Բ����޸�ʧ�ܣ�");
		}
		json.setSuccess(flag);
		 return json;
	}
	
	//ɾ��
	@RequestMapping("del")
	@ResponseBody
	public AjaxJson delete_dept(String ids){
		AjaxJson json=new AjaxJson();
		boolean flag=false;
		List<Dept> list=deptservice.findByPid(ids);
		if(list.size()>0){
			flag=true;
		}
		if(flag){//�����Ҫɾ���Ĳ����»����Ӽ�����
			json.setMsg("�Բ��𣬸ò����´����Ӽ����ţ������Ƿ���");
			json.setSuccess(false);
			return json;
		}else{//˵��Ҫɾ���Ĳ�����û���Ӽ�����
			
			//�жϸò������Ƿ���Ա����Ϣ
			flag=deptservice.findUserByDeptId(ids);
			if(flag){
				json.setMsg("�Բ��𣬸ò����´���Ա���������Ƿ���");
				json.setSuccess(false);
				return json;
			}else{
				flag=deptservice.del(ids);
				json.setMsg("�Բ���ɾ��ʧ�ܣ�");
				json.setSuccess(flag);
				return json;
			}
			
		}
	}
	
	//�޸Ļ���
	@RequestMapping("/init")
	public String toupdate(String id ,Map map ){
		Dept dept = deptservice.findll(id);
		map.put("dept", dept);
		return "dept/deptadd";
	}
	
	//�ϼ���Դ
	@RequestMapping("tree")
	@ResponseBody
	public List<Node> list (){
		List<Dept> deptlist = deptservice.findall();
		List<Node> nodelist = new ArrayList<Node>();
		 Node node = null;
		 for (Dept res : deptlist) {
			 node = new Node();
			 node.setId(res.getId());
			 node.setName(res.getDeptName());
			 node.setparentId(res.getPid());
			 nodelist.add(node);
		}
		return nodelist;
	}
	
	// v_tree ��
	@RequestMapping("/v_tree")
	@ResponseBody
	public List<ZtreeNode> aree(Integer deptId){
		List<ZtreeNode>  ztreenodelist = new ArrayList<ZtreeNode>();
		List<Dept> deptlist = deptservice.findall();
		ZtreeNode ztreenode=null;
		if(deptlist!=null&&!deptlist.isEmpty()){
			for (Dept res : deptlist) {
				ztreenode = new ZtreeNode();
				ztreenode.setId(res.getId());
				ztreenode.setPid(res.getPid());
				ztreenode.setName(res.getDeptName());
				ztreenodelist.add(ztreenode);
			}
		}
		return ztreenodelist;
	}
	
	@RequestMapping("/expExcle")
    public void expExcel(HttpServletResponse response) {
         try{
             //������������ؿ�
              response.setContentType("application/x-msdownload");
             //���ø������ص�����
              response.setHeader("Content-Disposition", "attachment; "
                       + "filename=" + new String("����.xlsx".getBytes("utf-8"), "ISO8859-1"));
             OutputStream os = response.getOutputStream();
             /**********����excel****************/
             XSSFWorkbook workbook    = new XSSFWorkbook();
             
             XSSFSheet sheet = workbook.createSheet();
             
             //������
             XSSFRow headRow = sheet.createRow(0);
             //������
             XSSFCell ce11 = headRow.createCell(0);
             ce11.setCellValue("���");
             
             XSSFCell ce111 = headRow.createCell(1);
             ce111.setCellValue("�������� ");
             
             XSSFCell ce112 = headRow.createCell(2);
             ce112.setCellValue("���ű��");
             
             //��ѯ��������
             List<Dept> deptList =  deptservice.findall();
             //ѭ����ֵ
             int rownu = 1;
             for(Dept dept  : deptList){
                  XSSFRow row = sheet.createRow(rownu++);
                  
                  XSSFCell ce1111 = row.createCell(0);
                  ce1111.setCellValue(dept.getId());
                  
                  XSSFCell ce11112 = row.createCell(1);
                  ce11112.setCellValue(dept.getDeptName());
                  
                  XSSFCell ce1112 = row.createCell(2);
                  ce1112.setCellValue(dept.getDeptNo());
             }
             /**************************/
             workbook.write(os);//����excel�ļ�
             os.close();
             workbook.close();
             
         }catch(Exception e){
             e.printStackTrace();
         }
         
    }
}
