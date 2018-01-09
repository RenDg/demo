<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../commons/global.jsp"%>
<div id="custtb">
	<a id="addcus" href="#" class="easyui-linkbutton"
		data-options="iconCls:'icon-add'">添加</a> 
		
	<a id="updatecus" href="#" class="easyui-linkbutton"
	data-options="iconCls:'icon-edit'">修改</a> 
	
	<a id="removecus" href="#" class="easyui-linkbutton"
		data-options="iconCls:'icon-remove'">删除</a> 
</div>
<!-- 添加 -->
<div id="cusadd"></div>

<table id="cusdg"></table>  
<script type="text/javascript">
$(function(){
	
	//删除
	$("#removecus").click(function(){
		//获取所有选中的行
		var selects = $("#cusdg").datagrid("getSelections");
		//1.若没有选中部门给予提示
		if(selects.length == 0 ){
			$.messager.alert('警告','请选择一条删除的记录');   
			return ;
		}
		$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
		    if (r){    
		      	// 获取ID
		      	var ids =[];
		      	for (var i = 0; i < selects.length; i++) {
					ids.push(selects[i].id);
				}
		      $.post("${ctxPath}/cus/del",{"ids":ids.join()},function(data){
		    	  if(data.success) {
		       			//提示删除成功
		       			$.messager.alert('提示','删除成功');
		       			
		       			$('#cusdg').datagrid("reload");	
		       		} else {
		       			alert(data.msg);
		       		}
		      },"json");
		    }    
		}); 
	});
	
	
	//修改
	$("#updatecus").click(function(){
		//获取所有选中的行
		var selects = $("#cusdg").datagrid("getSelections");
		//1.若没有选中部门给予提示
		if(selects.length == 0 ){
			$.messager.alert('警告','请选择一条修改的记录');    
			return ;
		}
		//2.若选中多哥部门也要给予提示
		if(selects.length >1){
			$.messager.alert('警告','请选择一条修改的记录,不能选择多条');
			return ;
		}
		//获取ID  selects 是返回的空数组 
		var id = selects[0].id;
		$('#cusadd').dialog({    
		    title: '修改页面',    
		    width: 700,    
		    height: 400,    
		    closed: false,    
		    cache: false,    
		    href: '${ctxPath}/cus/init?id='+id,    
		    modal: true ,
		    buttons:[{
				text:'保存',
				handler:function(){
					$('#cusff').form('submit', {    
					    url:'${ctxPath}/cus/add',    
					    onSubmit: function(){    
					    	//做表单字段验证
					    	var ret = $("#cusff").form('validate');
					    	return ret ;
					    },    
					    success:function(data){  
					    	var json=eval("("+data+")");
					        if(json.success){
					        	$.messager.alert('消息窗口',"修改成功");  
				        		//刷新
						    	$('#cusdg').datagrid("reload");	
						    		//关闭
						    	$('#cusadd').dialog("close");	
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
					$('#cusadd').dialog("close");
				
				}
			}]
		}); 
		
	})
	// 添加
	$("#addcus").click(function(){
		$('#cusadd').dialog({    
		    title: '添加页面',    
		    width: 700,    
		    height: 400,    
		    closed: false,    
		    cache: false,    
		    href: '${ctxPath}/cus/toadd',    
		    modal: true ,
		    buttons:[{
				text:'保存',
				handler:function(){
					$('#cusff').form('submit', {    
					    url:'${ctxPath}/cus/add',    
					    onSubmit: function(){    
					    	//做表单字段验证
					    	var ret = $("#cusff").form('validate');
					    	return ret ;
					    },    
					    success:function(data){  
					    	var json=eval("("+data+")");
					    	alert(json)
					        if(json.success){
					        	$.messager.alert('消息窗口',"添加成功");  
				        		//刷新
						    	$('#cusdg').datagrid("reload");	
						    		//关闭
						    	$('#cusadd').dialog("close");	
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
					$('#cusadd').dialog("close");
				
				}
			}]
		}); 
		
	})
	
	
	//列表
	$('#cusdg').datagrid({    
		pagination : true,
		pageSize : 10,
		pageList : [10,20,30,], 
		checkOnSelect:true,
		striped:true,// 斑马线
		fit: true,
		toolbar:'#custtb',
	    url:'${ctxPath}/cus/list', 
	    columns:[[    
	        {field:'id',title:'ID',width:100,checkbox:true},    
	        {field:'empNo',title:'员工编号',width:100},    
	        {field:'unit',title:'单位',width:100,align:'right'},	    
	        {field:'deptId',title:'部门',width:100,align:'right'},	    
	        {field:'position',title:'职位',width:100,align:'right'},	    
	        {field:'cusName',title:'姓名',width:100,align:'right'},	    
	        {field:'chargework',title:'主要负责工作',width:100,align:'right'},	    
	        {field:'remarks',title:'备注',width:100,align:'right'},	    
	    ]]    
	});  
	
	
});
</script>