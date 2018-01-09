<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../commons/global.jsp"%>
<div id="depttb">
	<a id="addDept" href="#" class="easyui-linkbutton"
		data-options="iconCls:'icon-add'">添加</a> 
		
	<a id="updateDept" href="#" class="easyui-linkbutton"
	data-options="iconCls:'icon-edit'">修改</a> 
	
	<a id="removeDept" href="#" class="easyui-linkbutton"
		data-options="iconCls:'icon-remove'">批量删除</a> 
		
		
	<a  href="${ctxPath}/dept/expExcle" class="easyui-linkbutton"
		data-options="iconCls:'icon-remove'">导出文件</a> 
</div>

<!-- 列表 -->
<div id="depttreegrid"></div>
<!-- 添加 -->
<div id="deptadd"></div>
<script type="text/javascript">
	$(function(){
		// 添加
		$("#addDept").click(function(){
			$('#deptadd').dialog({    
			    title: '添加页面',    
			    width: 700,    
			    height: 400,    
			    closed: false,    
			    cache: false,    
			    href: '${ctxPath}/dept/toadd',    
			    modal: true ,
			    buttons:[{
					text:'保存',
					handler:function(){
						$('#deptff').form('submit', {    
						    url:'${ctxPath}/dept/add',    
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
							    	$('#depttreegrid').treegrid("reload");	
							    		//关闭
							    	$('#deptadd').dialog("close");	
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
						$('#deptadd').dialog("close");
					
					}
				}]
			}); 
			
		})
		//删除
	$("#removeDept").click(function(){
		//获取所有选中的行
		var selects = $("#depttreegrid").datagrid("getSelections");
		//1.若没有选中部门给予提示
		if(selects.length == 0 ){
			$.messager.alert('警告','请选择一条修改的记录');   
			return ;
		}
		$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
		    if (r){    
		      	// 获取ID
		      	var ids =[];
		      	for (var i = 0; i < selects.length; i++) {
					ids.push(selects[i].id);
				}
		      $.post("${ctxPath}/dept/del",{"ids":ids.join()},function(data){
		    	  if(data.success) {
		       			//提示删除成功
		       			$.messager.alert('提示','删除成功');
		       			
		       			$('#depttreegrid').treegrid("reload");	
		       		} else {
		       			alert(data.msg);
		       		}
		      },"json");
		    }    
		}); 
	});
	
	
	
	// 修改
	$("#updateDept").click(function(){
		//获取所有选中的行
		var selects = $("#depttreegrid").datagrid("getSelections");
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
		 $('#deptadd').dialog({    
		    title: '修改部门',    
		    width: 600,    
		    height: 300,    
		    closed: false,    
		    cache: false,    
		    href: '${ctxPath}/dept/init?id='+id,    
		    modal: true ,
		 	
		    buttons:[{
				text:'保存',
				handler:function(){
					$('#deptff').form('submit', {    
					    url:'${ctxPath}/dept/add',    
					    onSubmit: function(){ 
					    	var ret = $("#deptff").form('validate');
					    	return ret ;
					    },    
					    success:function(data){ 
					    	
					    	var json=eval("("+data+")");
					        if(json.success){
					        	$.messager.alert('消息窗口',"修改成功");  
				        		//刷新
						    	$('#depttreegrid').treegrid("reload");	
						    		//关闭
						    	$('#deptadd').dialog("close");	
					        }else{
					        	alert(json.msg);
					        } 
					        
					  		$.messager.show({
					  			title:'我的消息',
					  			msg:'',
					  			timeout:5000,
					  			showType:'slide'
					  		});
					    }    
					});  
				}
			},{
				text:'关闭',
				handler:function(){
					$('#deptadd').window('close'); 
				}
			}]
		});
	});
		//列表
		$('#depttreegrid').treegrid({    
			/* pagination : true,
			pageSize : 100,
			pageList : [100,200,300,], */
			checkOnSelect:true,
			striped:true,// 斑马线
			fit: true,
			toolbar:'#depttb',
		    url:'${ctxPath}/dept/list',    
		    idField:'id',    
		    treeField:'deptName',    
		    columns:[[    
				{field:'id',title:'部门ID',width:100,checkbox:true}, //显示checkbox   
	           	/* {field:'deptNo',title:'部门编号',width:100,sortable:true	
	           	} */ //sortable : 列排序   
	           	{field:'deptName',title:'部门名称',width:300,align:'center'
	           	},
	           	{field:'deptOrder',title:'部门编号',width:100,align:'center'	
	           	},
	           	{field:'iconCls',title:'图标',width:100,align:'center'	
	           	},
	           	{field:'address',title:'地址',width:100,align:'center'
	           	},
	        	{field:'createDate',title:'创建日期',width:100,align:'center'},
		    ]],
		    
		});  
		
	})
</script>