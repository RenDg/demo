<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../commons/global.jsp" %>
<script type="text/javascript">
function ajaxFileUpload(){
    //开始上传文件时显示一个图片,文件上传完成将图片隐藏
    //$("#loading").ajaxStart(function(){$(this).show();}).ajaxComplete(function(){$(this).hide();});
    //执行上传文件操作的函数
    $.ajaxFileUpload({
        //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
        url:'${pageContext.request.contextPath}/ajaxfileUpload',
        secureuri:false,                           //是否启用安全提交,默认为false
        fileElementId:'myBlogImage',               //文件选择框的id属性
        dataType:'text',                           //服务器返回的格式,可以是json或xml等
        success:function(data, status){            //服务器响应成功时的处理函数
            data = data.replace(/<pre.*?>/g, '');  //ajaxFileUpload会对服务器响应回来的text内容加上<pre style="....">text</pre>前后缀
            data = data.replace(/<PRE.*?>/g, '');
            data = data.replace("<PRE>", '');
            data = data.replace("</PRE>", '');
            data = data.replace("<pre>", '');
            data = data.replace("</pre>", ''); //本例中设定上传文件完毕后,服务端会返回给前台[0`filepath]
            if(data.substring(0, 1) == 0){//0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述)
                $("img[id='uploadImage']").attr("src", data.substring(2));
            
            	$("#iconcls").val(data.substring(2));
                $('#result').html("图片上传成功<br/>");
            }else{
                $('#result').html('图片上传失败，请重试！！');
            }
        },
        error:function(data, status, e){ //服务器响应失败时的处理函数
            $('#result').html('图片上传失败，请重试！！');
        }
    });
}
</script>
<form id="deptff" method="post" enctype="multipart/form-data">   
	<!-- 主键 -->
	<input type="hidden" name="id" value="${dept.id }"/>
    <div style="width:100%;overflow:hidden">  
    	<table class="grid" align="center">
    		<tr>
    			<td>编号:</td>  
        		<td>
        			<input class="easyui-validatebox" type="text" name="deptOrder"
        			 data-options="required:true" value="${dept.deptOrder }"  />  
        		</td> 
        		<td>部门名称:</td>
        		<td>
        			<input class="easyui-validatebox" type="text" name="deptName" 
        			data-options="required:true"  value="${dept.deptName }"/> 
        		</td>
    		</tr>
    		<tr>
    			<td>排序:</td>  
        		<td>
        		
        		<input class="easyui-validatebox" type="text" name="deptOrder" value="${dept.deptOrder }" /> 
        		</td> 
        		<td>地址:</td>  
        		<td colspan="3">
        			<input class="easyui-validatebox" type="text" name="address" value="${dept.address }"/>  
        		</td> 
    		</tr>
    		<tr>
        		<td>菜单图标:</td>
        		<td colspan="4">
        			<input id="iconcls"  class="easyui-validatebox" type="text" 
        				name="iconCls" value="${dept.iconCls }"   />  
        		<!-- result ： 显示失败信息 -->
        		<div id="result"></div>
        		
				<img id="uploadImage" >
				<input type="file" id="myBlogImage" name="myfiles"/>
				<input type="button" value="上传图片" onclick="ajaxFileUpload()"/>
        		</td>
    		</tr>
    		<tr>
    			<td>上级部门:</td>  
        		<td colspan="3">
        			<!-- <input id="deptcombotree" class="easyui-combotree" name="pid" style="width:200px;">   
					    <option value="0">菜单</option>   
					</select> -->
					
					<input id="deptcombotree" name="pid" value="${dept.pid } ">
					 
        			<script type="text/javascript">
        			$(function(){
        				$('#deptcombotree').combotree({    
        				    url:'${ctxPath}/dept/tree', 
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
        			<a id="resourceclear" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">清空</a>  
        		</td> 
    		</tr>
    	</table>
    </div>   
</form>  
