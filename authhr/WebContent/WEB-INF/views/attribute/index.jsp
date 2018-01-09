<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../commons/global.jsp"%>
<div id="attributetreegrid"> </div>

<div id="depttb">
	<a id="addattribute" href="#" class="easyui-linkbutton"
		data-options="iconCls:'icon-add'">添加</a> 
		
	<a id="updateattribute" href="#" class="easyui-linkbutton"
	data-options="iconCls:'icon-edit'">修改</a> 
	
	<a id="removeattribute" href="#" class="easyui-linkbutton"
		data-options="iconCls:'icon-remove'">删除</a> 
		
		
</div>
<div id="attributedd"></div>
<script type="text/javascript">

$(function(){
	
	// 添加
	$("#addattribute").click(function(){
		$('#attributedd').dialog({    
		    title: '添加页面',    
		    width: 700,    
		    height: 400,    
		    closed: false,    
		    cache: false,    
		    href: '${ctxPath}/attribute/toadd',    
		    modal: true ,
		    buttons:[{
				text:'保存',
				handler:function(){
					$('#deptff').form('submit', {    
					    url:'${ctxPath}/attribute/add',    
					    onSubmit: function(){    
					    	//做表单字段验证
					    	var ret = $("#deptff").form('validate');
					    	return ret ;
					    },    
					    success:function(data){  
					    	var json=eval("("+data+")");
					        if(json.success){
					        	$.messager.alert('消息窗口',"添加成功");  
				        		//刷新
						    	$('#attributetreegrid').datagrid("reload");	
						    		//关闭
						    	$('#attributedd').dialog("close");	
					        }else{
					        	alert(json.msg);
					        } 
					        $.messager.show({
					        	title:'我的消息',
					        	msg:'提示',
					        	timeout:5000,
					        	showType:'slide'
					        });    
					    }    
					});  	
				}
			},{
				text:'关闭',
				handler:function(){
					//关闭
					$('#attributedd').dialog("close");
				
				}
			}]
		}); 
		
	})
	// 列表
	$('#attributetreegrid').datagrid({    
		pagination : true,
		pageSize : 10,
		pageList : [10,20,30,], 
		checkOnSelect:true,
		striped:true,// 斑马线
		fit: true,
		toolbar:'#depttb',
	    url:'${ctxPath}/attribute/list',   
	    columns:[[    
	        {field:'id',title:'ID',width:100,checkbox:true}, //显示checkbox   
	       	{field:'attrName',title:'名称',width:300,align:'center'},
	       	{field:'tname',title:'绑定分类',width:300,align:'center'},
	       	{field:'kname',title:'可选项',width:300,align:'center'},
	    ]]    
	}); 
	
})
</script>