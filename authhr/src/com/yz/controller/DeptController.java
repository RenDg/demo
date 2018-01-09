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
	
	// 列表加分页
	@RequestMapping("list")
	@ResponseBody
	public Map<String ,Object> list1(){
		 Map<String, Object> list = deptservice.list();		
		 return list;
	}
	
	//添加跳转
	@RequestMapping("toadd")
	public String toadd(){
		
		return "dept/deptadd";
	}
	
	//修改添加
//	@RequestMapping("add")
//	@ResponseBody
//	public Integer add(Dept dept){
//		return deptservice.add_update(dept);
//	}
	
	//修改添加
	@RequestMapping("add")
	@ResponseBody
	public AjaxJson add(Dept dept){
		AjaxJson json=new AjaxJson();
		boolean flag=false;
		if(dept.getId()==null){
			dept.setCreateDate(new Date());
			flag =deptservice.add(dept);
			json.setMsg("对不起，添加失败！");
		}else{
			if(dept.getId().equals(dept.getPid())){
				json.setMsg("对不起，不能修改本部门！");
				json.setSuccess(false);
				return json;
			}
			dept.setUpdateDate(new Date());
			flag=deptservice.update(dept);
			json.setMsg("对不起，修改失败！");
		}
		json.setSuccess(flag);
		 return json;
	}
	
	//删除
	@RequestMapping("del")
	@ResponseBody
	public AjaxJson delete_dept(String ids){
		AjaxJson json=new AjaxJson();
		boolean flag=false;
		List<Dept> list=deptservice.findByPid(ids);
		if(list.size()>0){
			flag=true;
		}
		if(flag){//如果需要删除的部门下还有子级部门
			json.setMsg("对不起，该部门下存在子级部门，操作非法！");
			json.setSuccess(false);
			return json;
		}else{//说明要删除的部门下没有子级部门
			
			//判断该部门下是否有员工信息
			flag=deptservice.findUserByDeptId(ids);
			if(flag){
				json.setMsg("对不起，该部门下存在员工，操作非法！");
				json.setSuccess(false);
				return json;
			}else{
				flag=deptservice.del(ids);
				json.setMsg("对不起，删除失败！");
				json.setSuccess(flag);
				return json;
			}
			
		}
	}
	
	//修改回显
	@RequestMapping("/init")
	public String toupdate(String id ,Map map ){
		Dept dept = deptservice.findll(id);
		map.put("dept", dept);
		return "dept/deptadd";
	}
	
	//上级资源
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
	
	// v_tree 树
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
             //浏览器弹出下载狂
              response.setContentType("application/x-msdownload");
             //设置附加下载的名称
              response.setHeader("Content-Disposition", "attachment; "
                       + "filename=" + new String("部门.xlsx".getBytes("utf-8"), "ISO8859-1"));
             OutputStream os = response.getOutputStream();
             /**********创建excel****************/
             XSSFWorkbook workbook    = new XSSFWorkbook();
             
             XSSFSheet sheet = workbook.createSheet();
             
             //创建行
             XSSFRow headRow = sheet.createRow(0);
             //创建列
             XSSFCell ce11 = headRow.createCell(0);
             ce11.setCellValue("编号");
             
             XSSFCell ce111 = headRow.createCell(1);
             ce111.setCellValue("部门名称 ");
             
             XSSFCell ce112 = headRow.createCell(2);
             ce112.setCellValue("部门编号");
             
             //查询数据内容
             List<Dept> deptList =  deptservice.findall();
             //循环赋值
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
             workbook.write(os);//导出excel文件
             os.close();
             workbook.close();
             
         }catch(Exception e){
             e.printStackTrace();
         }
         
    }
}
