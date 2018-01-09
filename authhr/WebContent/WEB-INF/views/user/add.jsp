<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../commons/global.jsp" %>
<form id="userff" method="post">   
    <div style="width:100%;overflow:hidden">
    	<input type="hidden" name="id" value="${uuser.id }"/>   
    	<table class="grid" align="center">
    		<tr>
    			<td>登录名:</td>  
        		<td>
        			<input class="easyui-validatebox" type="text" name="username" value="${uuser.username }" data-options="required:true" />  
        		</td> 
        		<td>姓名:</td>
        		<td>
        			<input class="easyui-validatebox" type="text" name="name" value="${uuser.name }" data-options="required:true" /> 
        		</td>
    		</tr>
    		<tr>
    			<td>密码:</td>  
        		<td>
        			<input class="easyui-validatebox" type="text" name="pwd" value="${uuser.pwd }" data-options="required:true" />  
        		</td> 
        		<td>性别:</td>
        		<td>
        			<select name="sex">
        				<option value="1" ${uuser.age == 1?"selected":"" }>男</option>
        				<option value="2" ${uuser.age == 2?"selected":"" }>女</option>
        				<option value="3" ${uuser.age == 3?"selected":"" }>保密</option>
        			</select> 
        		</td>
    		</tr>
    		<tr>
    			<td>年龄:</td>  
        		<td>
        			<input class="easyui-validatebox" type="text" name="age" value="${uuser.age }" />  
        		</td> 
        		<td>用户类型:</td>
        		<td>
        			<select name="userType">
        				<option value="1" ${uuser.userType == 1?"selected":"" }>用户</option>
        				<option value="2" ${uuser.userType == 2?"selected":"" }>管理员</option>
        			</select> 
        		</td>
    		</tr>
    		<tr>
    			<td>部门:</td>  
        		 <td>
        		
        			<input id="deptcc" name="deptId"   >  
        			<script type="text/javascript">
        				$(function() {
        					$('#deptcc').combotree({    
        						url:'${ctxPath}/dept/tree', 
             				    required: false , // false true
             				    loadFilter:convert,    
        					}); 
        					
        					function convert(rows){
        					    function exists(rows, parentId){
        					        for(var i=0; i<rows.length; i++){
        					            if (rows[i].id == parentId) return true;
        					        }
        					        return false;
        					    }
        					    
        					    var nodes = [];
        					    // get the top level nodes
        					    for(var i=0; i<rows.length; i++){
        					        var row = rows[i];
        					        if (!exists(rows, row.parentId)){
        					        	//{"id":1,"parendId":0,"name":"中国"},
        					            nodes.push({
        					                id:row.id,
        					                text:row.name
        					            });
        					        }
        					    }
        					    
        					    var toDo = [];
        					    for(var i=0; i<nodes.length; i++){
        					        toDo.push(nodes[i]);
        					    }
        					    while(toDo.length){
        					        var node = toDo.shift();    // the parent node
        					        // get the children nodes
        					        for(var i=0; i<rows.length; i++){
        					            var row = rows[i];
        					            if (row.parentId == node.id){
        					                var child = {id:row.id,text:row.name};
        					                if (node.children){
        					                    node.children.push(child);
        					                } else {
        					                    node.children = [child];
        					                }
        					                toDo.push(child);
        					            }
        					        }
        					    }
        					    return nodes;
        					}

        				});
        			</script>
        		</td> 
    		</tr>
    		<tr>
    			<td>电话:</td>  
        		<td>
        			<input class="easyui-validatebox" type="text" name="telephone" value="${uuser.telephone }" data-options="required:true" />  
        		</td> 
        		<td>用户状态:</td>
        		<td>
        			<select name="usersState">
        				<option value="1"  ${uuser.usersState == 1?"selected":"" }>激活</option>
        				<option value="2"  ${uuser.usersState == 2?"selected":"" }>关闭</option>
        			</select> 
        		</td>
    		</tr>
    	</table>
    </div>
</form> 
<script type="text/javascript">








</script> 