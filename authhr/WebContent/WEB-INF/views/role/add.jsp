<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../commons/global.jsp" %>
<form id="roleff"  method="post">
  <div style="width:100%;overflow:hidden">  
  
<table class="grid" align="center">
<input type="hidden" name="id" value="${role.id }"> 
	<tr>	
		<td>角色名称</td>
		<td>
        	<input class="easyui-validatebox" type="text" name="roleName"
        	 data-options="required:true" value="${role.roleName }"  />  
        </td>
		<td>角色描述</td>
		<td>
        	<input class="easyui-validatebox" id="goodsType" type="text" name="roleDesc"
        	 data-options="required:true" value="${role.roleDesc }"  />  
        </td>
	</tr>
	
	<tr>
		<td>角色状态</td>
		<td>
        	<select name="roleState">
 				<option value="0" ${role.roleState == 0?"selected":"" } >激活</option>
 				<option value="1" ${role.roleState == 1?"selected":"" } >关闭</option>
        	</select>
        </td>
		<td>是否拥有权限</td>
		<td>
        	<select name="isAllAuth">
 				<option value="1" ${role.isAllAuth == 1?"selected":"" } >是</option>
 				<option value="2"  ${role.isAllAuth == 2?"selected":"" } >否</option>
        	</select>
        </td>
	</tr>
	
</table>
</div>
</form>
