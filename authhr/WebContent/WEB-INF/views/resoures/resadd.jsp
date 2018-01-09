<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../commons/global.jsp"%>    
<form id="resourceff" method="post">  
	<input type="hidden" name="id" value="${resoures.id }"/>
    <div style="width:100%;overflow:hidden">   
    	<table class="grid" align="center">
    		<tr>
    			<td>资源名称:</td>  
        		<td>
        			<input class="easyui-validatebox" type="text" name="resName" data-options="required:true" value="${resoures.resName }" />  
        		</td> 
        		<td>资源类型:</td>
        		<td>
        			<select name="resType">
        				<option value="1" ${resoures.resType == 1?"selected":"" } >菜单</option>
        				<option value="0"  ${resoures.resType == 0?"selected":"" } >按钮</option>
        			</select> 
        		</td>
    		</tr>
    		<tr>
    			<td>资源路径:</td>  
        		<td>
        			<input class="easyui-validatebox" type="text" name="url" value="${resoures.url }"/>  
        		</td> 
        		<td>状态:</td>  
        		<td>
        			<select name="resState">
        				<option value="1"  ${resoures.resState == 1?"selected":"" } >激活</option>
        				<option value="2"  ${resoures.resState == 2?"selected":"" } >关闭</option>
        			</select> 
        		</td> 
    		</tr>
    		<tr>
    			<td>上级资源:</td>
        		<td colspan="3">
        			<!-- <select id="resources" name="pid" style="width:200px;">   
					    <option value="0">菜单</option>   
					</select>  -->
					<input id="resources" name="pid" value="${resoures.pid }">
        			<script type="text/javascript">
        			$(function(){
        				$('#resources').combotree({    
        				    url:'${ctxPath}/resoures/tree', 
        				    required: false ,
        				    loadFilter:convert,
        				}); 
        				//清空
        				 $("#resourceclear").click(function(){
        					$('#resources').combotree("clear");
        				});
        				

        				//实现将普通的json格式转换为easyui tree需要的标准格式（id ，text children
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
        			<a id="resourceclear" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">清空</a>  
        		</td> 
    		</tr>
    	</table>
    </div>   
</form> 