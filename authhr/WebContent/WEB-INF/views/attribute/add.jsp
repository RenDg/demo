<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../commons/global.jsp" %>




<form id="deptff" method="post" enctype="multipart/form-data">   
	<!-- 主键 -->
	<input type="hidden" name="id" value="${dept.id }"/>
    <div style="width:100%;overflow:hidden">  
    	<table class="grid" align="center">
    		<tr>
    			<td>绑定分类</td>
    			<td> 
    				<input id="attributetree" name="typeid" >
    			</td>
    			
    			<td>名称</td>
    			<td>
    				<input name="attrName">
    				
    			</td>
    		</tr>
    			
    		<tr>
    			<td colspan="4">
    				<a id="ad" href="#" class="easyui-linkbutton" >添加可选项</a>  
    				<div id="a" style="padding: 5px;"> </div>
    			</td>
    		</tr>
    	</table>
    </div>   
</form>  
<script type="text/javascript">
	$("#ad").click(function(){
		$("#a").append("<tr class='ss'><td ><input  name='itemname ' type='text'>   <a href='#' class='d'>刪除</a>  </td></tr><br>");		
	
		$(".d").click(function(){
			//现获取当前对象 然后再删除
              var tr = $(this).parents(".ss")
              tr.remove()
           });
	});
</script>
<script type="text/javascript">
$(function(){
	$('#attributetree').combotree({    
	    url:'${ctxPath}/goodstype/tree', 
	    required: false , // false true
	    loadFilter:convert,
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