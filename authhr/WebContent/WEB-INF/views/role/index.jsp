<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../commons/global.jsp"%>
<!-- 列表 -->
<table id="roledg"></table> 
<!-- 添加 --> 
<div id="roleaddtd"></div>
<div id="roletd">
	
	<a id="roleadd" href="#" class="easyui-linkbutton"
		data-options="iconCls:'icon-add'">添加</a>
	<a id="roleedit" href="#" class="easyui-linkbutton"
		data-options="iconCls:'icon-edit'">修改</a>
	<a id="roleremove" href="#" class="easyui-linkbutton"
		data-options="iconCls:'icon-remove'">删除</a>
	<a id="role_auth" href="#" class="easyui-linkbutton" 
		data-options="iconCls:'icon-add'">分配权限</a>
</div>

<script type="text/javascript">

$(function(){
	
	//删除
	$("#roleremove").click(function(){
		//获取所有选中的行
		var selects = $("#roledg").datagrid("getSelections")
		if(selects.length==0){
			$.messager.alert('警告','请选择一条记录');    
			return ;
		}
		$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
		    if (r){  
		    	var ids =[];
		    	for (var i = 0; i < selects.length; i++) {
					ids.push(selects[i].id);
				}						
		    										// 数组转字符串
		    	$.post("${ctxPath}/role/del", {"ids":ids.join()}, function(data){
		    		if(data>0){
		    			//删除成功
		    			$.messager.alert('提示','删除成功');    
		    			//刷新
		    			$("#roledg").datagrid("reload");
		    		}else{
		    			$.messager.alert('提示','删除失败');
		    		}
		    	}, "json");
		    }    
		}); 
		
	})
	//分配权限
	$("#role_auth").click(function(){
		 var select =$("#roledg").datagrid("getSelections");
		//1.若没有选中部门给予提示
		if(select.length == 0) {
			$.messager.alert('警告','请选择一条记录');
			return;//结束执行
		}
		//2.若选中多哥部门也要给予提示
		if(select.length > 1) {
			$.messager.alert('警告','请选择一条记录,不能选择多条');
			return;//结束执行
		}
		 $('#roleaddtd').dialog({    
			    title: '角色授权',    
			    width: 600,    
			    height: 400,    
			    closed: false,    
			    cache: false,    
			    href: '${ctxPath}/role/v_auth?roleId='+select[0].id,    
			    modal: true ,
			    buttons:[{
					text:'保存',
					handler:function(){
						/*  var checked =zTreeOnCheck(checked);
						 alert(checked) */
						var resIds = getCheckedNodes();//返回ztree的选中节点函数
						var roleId = select[0].id;
		                $.post("${ctxPath}/role/o_auth", 
		                		// roleId角色的ID    resIds 资源的ID
		                		{ "roleId": roleId,"resIds":resIds },function(data){
		                			var ret = "";
							        if(data!=0){
							        	ret = '授权成功'
						        		//刷新
								    	$('#roledg').datagrid("reload");	
								    		//关闭
								    	$('#roleaddtd').dialog("close");	
							        }else{
							        	ret="授权失败"
							        } 
							        $.messager.show({
							        	title:'我的消息',
							        	msg:ret,
							        	timeout:5000,
							        	showType:'slide'
							        });
		                }, "json");
					}
				},{
					text:'关闭',
					handler:function(){
						$('#roleaddtd').dialog("close");	
					}
				}]
			}); 
	})
	
	//添加
	$("#roleadd").click(function(){
		$('#roleaddtd').dialog({    
		    title: '添加页面',    
		    width: 600,    
		    height: 300,    
		    closed: false,    
		    cache: false,    
		    href: '${ctxPath}/role/toadd',    
		    modal: true  ,
		    buttons:[{
				text:'保存',
				iconCls:'icon-ok',
				handler:function(){
					$('#roleff').form('submit', {    
					    url:'${ctxPath}/role/add',    
					    onSubmit: function(){  
					    	//做表单字段验证
					    	var ret = $("#roleff").form('validate');
					    	return ret ;
					    },    
					    success:function(data){    
					    	var ret = "";
					        if(data!=0){
					        	ret = '添加成功'
				        		//刷新
						    	$('#roledg').datagrid("reload");	
						    		//关闭
						    	$('#roleaddtd').dialog("close");	
					        }else{
					        	ret="添加失败"
					        } 
					        $.messager.show({
					        	title:'我的消息',
					        	msg:ret,
					        	timeout:5000,
					        	showType:'slide'
					        });

					    }    
					}); 
				}
			},{
				text:'关闭',
				iconCls:'icon-no',
				handler:function(){
					//关闭
					$('#roleaddtd').dialog("close"); 
				}
			}]
		}); 
	})
	
	//列表
	$('#roledg').datagrid({ 
		pagination : true,
		pageSize : 5,
		pageList : [5,10,20,],
		striped:true,// 斑马线
		fit: true,
		toolbar:'#roletd',
	    url:'${ctxPath}/role/list',    
	    columns:[[    
	        {field:'id',checkbox:true},    
	        {field:'roleName',title:'角色名称',width:100},    
	        {field:'roleDesc',title:'角色描述',width:100},    
	        {field:'roleState',title:'角色状态',width:100,align:'right',
	        	formatter: function(value,row,index){
  					if (row.roleState==0){
  						return "激活";
  					} else {
  						return "关闭";
  					}
  				}
	        },    
	        {field:'createDate',title:'创建时间',width:100,align:'right'},    
	    ]]    
	});   
});




</script>

