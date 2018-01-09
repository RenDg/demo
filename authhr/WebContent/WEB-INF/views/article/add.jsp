<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../commons/global.jsp" %>
<form id="articleff" method="post" enctype="multipart/form-data">   
	<!-- 主键 -->
	<input type="hidden" name="id" value="${article.id }"/>
    <div style="width:100%;overflow:hidden">  
    	<table class="grid" align="center">
    		<tr>
    			<td>所属栏目:</td>  
        		<td>
        			<input id="deptcombotree" name="typeId" value="${article.typeId } ">
        		</td> 
        		<td>新闻标题:</td>
        		<td>
        			<input class="easyui-validatebox" type="text" name="newsTitle" 
        			data-options="required:true"  value="${article.newsTitle }"/> 
        		</td>
    		</tr>
    		<tr>
    			<td>新闻作者:</td>  
        		<td>
        		
        		<input class="easyui-validatebox" type="text" name="newsAuthor" value="${article.newsAuthor }" /> 
        		</td> 
        		<td>是否在 轮播图 区域显示:</td>  
        		<td colspan="3">
        			<input class="easyui-validatebox" type="text" name="show" value="${article.show }"/>  
        		</td> 
    		</tr>
    		<tr>
        		<td>状态:</td>
        		<td colspan="4">
        			<input id="iconcls"  class="easyui-validatebox" type="text" 
        				name="states" value="${article.states }"   />  
        		</td>
    		</tr>
    		<tr>
    			<td>新闻内容:</td>  
        		<td colspan="3">
					<textarea rows="10" cols="111"  id="MyTextarea" name="newsContent"></textarea>
					<script type="text/javascript">
						CKEDITOR.replace('MyTextarea',{				
							  filebrowserImageUploadUrl : '${ctxPath}/fileUpload?type=image',//图片上传组件路径
							  filebrowserFlashUploadUrl : '${ctxPath}/fileUpload?type=flash' //Flash上传组件路径
							});
					</script>
				</td>	
					
        			
    		</tr>
    	</table>
    </div>   
</form>  
<script type="text/javascript">
	$(function(){
		$('#deptcombotree').combotree({    
		    url:'${ctxPath}/article_type/tree', 
		    required: false , // false true
		    loadFilter:convert,
		}); 
		
		//清空
		 $("#resourceclear").click(function(){
			$('#deptcombotree').combotree("clear");
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