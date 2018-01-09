<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../commons/global.jsp" %>
<!-- 列表 -->
<table id="articletypett" style="width:600px;height:400px"></table>  
 <div id="articletypetb">
			<a id="articletypeadd" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a> 
			<a id="articletypeupdate" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>  
			<a id="articletyperemove" href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-remove'">删除</a> 	
</div> 
<script type="text/javascript">


// 列表
$('#articletypett').treegrid({    
	striped:true,// 斑马线 
	fit: true,
	url:'${ctxPath}/article_type/list',        
    idField:'id',    
    treeField:'typeName',    
    columns:[[    
        {field:'typeName',title:'栏目名称',width:100,align:'right',align:'left'},    
        {field:'createDate',title:'创建时间',width:80},    
    ]]    
});  




</script>