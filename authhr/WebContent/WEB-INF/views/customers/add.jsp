<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../commons/global.jsp"%>    
<form id="cusff" method="post">  
	<input type="hidden" name="id" value="${cus.id }"/>
    <div style="width:100%;overflow:hidden">   
    	<table class="grid" align="center">
    		<tr>
    			<td>员工编号:</td>  
        		<td>
        			<input class="easyui-validatebox" type="text" name="empNo" data-options="required:true" value="${cus.empNo }" />  
        		</td> 
        		<td>姓名:</td>
        		<td>
        			<input class="easyui-validatebox" type="text" name="cusName" data-options="required:true" value="${cus.cusName }" /> 
        		</td>
    		</tr>
    		<tr>
    			<td>单位:</td>  
        		<td>
        			<input class="easyui-validatebox" type="text" name="unit" value="${cus.unit }"/>  
        		</td> 
        		<td>部门:</td>  
        		<td>
        			<input class="easyui-validatebox" type="text" name="deptId" value="${cus.deptId }"/>  
        		</td> 
    		</tr>
    		<tr>
    			<td>职位:</td>
        		<td colspan="3">
					<input class="easyui-validatebox" type="text" name="position" value="${cus.position }">
        		</td> 
    		</tr>
    		
    		<tr>
    			<td>主要负责工作:</td>
        		<td colspan="3">
					<textarea rows="10" cols="50" name="chargework">${cus.chargework }</textarea>
        		</td> 
    		</tr>
    	</table>
    </div>   
</form> 