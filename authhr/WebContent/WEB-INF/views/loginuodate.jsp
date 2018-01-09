<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="commons/global.jsp" %>



<form id="loginff" method="post">  
	<input type="hidden" name="id" value="${user.id }"/>
    <div style="width:100%;overflow:hidden">   
    	<table class="grid" align="center">
    		
    		<tr>
    			<td>姓名 </td>
    			
    			<td>
    			<input class="easyui-validatebox" type="text" name="username"
    			 	data-options="required:true" value="${user.username }" readonly="readonly"/>  
    			</td>
    		</tr>
    		
    		
    		<tr>
				<td>密码:</td>
				<td>
				 <input id="pwd" name="pwd" type="password" class="easyui-passwordbox" 
						data-options="required:true" value="${user.pwd }"/>
				</td>
				<td>密码是否相同:</td>
				<td>
				<input id="rpwd" name="rpwd" type="password" class="easyui-validatebox"     
			    			required="required" validType="equals['#pwd']" /> 
				</td>
			</tr>
    		
    		
    	</table>
    </div>   
</form> 


<script type="text/javascript">
$.extend($.fn.validatebox.defaults.rules, {    
    equals: {    
        validator: function(value,param){    
            return value == $(param[0]).val();    
        },    
        message: '密码不一致.'   
    }    
});  


</script>