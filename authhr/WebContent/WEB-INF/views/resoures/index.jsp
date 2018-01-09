<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../commons/global.jsp"%>
<div id="resourcestd">
<a id="resourcesadd" href="#" class="easyui-linkbutton"
		data-options="iconCls:'icon-add'">添加</a>
</div>

<!-- 列表 -->
<div id="resourcesTreeGrid"></div>
<!-- 添加 -->
<div id="rsadd"></div>
<script type="text/javascript">
	$(function(){
		// 添加
		$("#resourcesadd").click(function(){
			$('#rsadd').dialog({    
			    title: '添加页面',    
			    width: 700,    
			    height: 400,    
			    closed: false,    
			    cache: false,    
			    href: '${ctxPath}/resoures/toadd',    
			    modal: true ,
			    buttons:[{
					text:'保存',
					handler:function(){
						$('#resourceff').form('submit', {    
						    url:'${ctxPath}/resoures/add',    
						    onSubmit: function(){    
						    	//做表单字段验证
						    	var ret = $("#resourceff").form('validate');
						    	return ret ;
						    },    
						    success:function(data){    
						    	var ret = "";
						        if(data!=0){
						        	ret = '添加成功'
					        		//刷新
							    	$('#resourcesTreeGrid').treegrid("reload");	
							    		//关闭
							    	$('#rsadd').dialog("close");	
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
					handler:function(){
						//关闭
						$('#rsadd').dialog("close");
					
					}
				}]
			}); 
			
		})
		
		//列表
		$('#resourcesTreeGrid').treegrid({    
			striped:true,// 斑马线 
			fit: true,
			toolbar:'#resourcestd',
		    url:'${ctxPath}/resoures/list',    
		    idField:'id',    
		    treeField:'resName',    
		    columns:[[   
				{field:'id',title:'编号',width:100},   
		        {field:'resName',title:'资源名称',width:150,align:'left'},    
		        {field:'url',title:'资源路径',width:100},    
		        {field:'resType',title:'资源类型',width:100,
		        	formatter: function(value,row,index){
	  					if (row.resType==1){
	  						
	  						return "菜单";
	  					} else {
	  						return "按钮";
	  					}
	  				}
		        },   
		        {field:'resState',title:'资源状态',width:100,
		        	
		        	formatter: function(value,row,index){
	  					if (row.resState==1){
	  						return "激活";
	  					} else {
	  						return "关闭";
	  					}
	  				}
		        },
		        {field:'handler',title:'操作',width:200,
	  	      		//若有样式，没有渲染easyui样式的功能
		        	//对linkbutton做初始化操作
	  	        	formatter: function(value,row,index){
	  					return '<a id="btn" href="#" resourceid="'+row.id+'" class="easyui-linkbutton-edit" data-options="iconCls:\'icon-edit\'">修改</a>'
		        		+ "&nbsp;&nbsp;|&nbsp;&nbsp;"
		        		+ '<a id="btn" href="#" resourceid="'+row.id+'" class="easyui-linkbutton-remove" data-options="iconCls:\'icon-remove\'">删除</a>'
	  	        	}
	  	        },
		    ]],
		    //在数据加载完成之前触发
		    onLoadSuccess:function(){
		    	// 修改的按钮是没有样式的 我要给你渲染一下
		    	$(".easyui-linkbutton-edit").linkbutton({text:'修改',
		    		// 给修改添加一个点击事件
		    		onClick:function(){
		    			//获取ID
		    			var id = $(this).attr("resourceid");
		    			$('#rsadd').dialog({    
		    			    title: '修改资源',    
		    			    width: 600,    
		    			    height: 300,    
		    			    closed: false,    
		    			    cache: false,    
		    			    href: '${ctxPath}/resoures/init?id='+id,    
		    			    modal: true ,
		    			    buttons:[{
		    					text:'保存',
		    					handler:function(){
		    						$('#resourceff').form('submit', {    
		    						    url:'${ctxPath}/resoures/add',    
		    						    onSubmit: function(){ 
		    						    	var ret = $("#resourceff").form('validate');
		    						    	return ret ;
		    						    },    
		    						    success:function(data){    
		    						  		var ret = '';
		    						  		if(data.status !=0){
		    						  			ret = "修改成功";
		    						  			//刷新treegrid
		    						       		$("#resourcesTreeGrid").treegrid("reload");
		    						       		//关闭dialog
		    						       		$("#rsadd").dialog("close");
		    						  		}else{
		    						  			ret= "修改失败";
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
		    					handler:function(){
		    						$('#rsadd').window('close'); 
		    					}
		    				}]
		    			}); 	
		    		}
		    	})
		    	 // 删除的按钮是没有样式的 我要给你渲染一下
		    	$(".easyui-linkbutton-remove").linkbutton({tetx:'删除',
		    		//给删除添加一个点击事件
		    		onClick:function(){
		    			// 获取ID
		    			var id = $(this).attr("resourceid");
		    			
		    			$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
		    			    if (r){ 
		    	    			 $.post("${ctxPath}/resoures/del",{"id":id},function(data){
		    	   		    	  if(data != 0) {
		    	   		       			//提示删除成功
		    	   		       			$.messager.alert('提示','删除成功');
		    	   		       			//刷新treegrid
		    	   		       		$("#resourcesTreeGrid").treegrid("reload");
		    	   		       		} else {
		    	   		       			$.messager.alert('提示','删除失败');
		    	   		       		}
		    	   		      },"json"); 
		    			    }    
		    			}); 
		    		}
		    	})
		    }
		    
		});  
	})
</script>