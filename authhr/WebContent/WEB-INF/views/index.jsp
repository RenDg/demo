<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="commons/global.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="commons/basejs.jsp" %>
<title>Insert title here</title>


</head>
<div id="userupdate"></div>
<body class="easyui-layout">   
    <div  id="title"  data-options="region:'north',title:'头部'" style="height:100px;" align="center">
    	 <!-- <marquee><h1 style="color:  green;">云优众欢迎您:尊敬的任东</h1></marquee> -->
    	 
    <p>北京云优众教育平台</p>
    <p><span id="sp"></span></p>
    <p><span>欢迎您：${user.username}</span>
    <a href="${ctxPath }/login/loginsser" >退出登录</a>
    <a href="#"  id="updateid" onclick="update()">修改密码</a>
    </p>
    
    </div>   
    <div data-options="region:'south',title:'下面',split:true" style="height:100px;"></div>  
     
    <div data-options="region:'west',title:'导航菜单'" style="width:180px;">
    	<!-- 折叠面板 -->
    	<div id="aa" class="easyui-accordion" fit=true>   
		    <div title="管理" data-options="iconCls:'icon-save'" style="overflow:auto;padding:10px;">   
		     <!-- ztree -->
		     <ul id="tree1" class="ztree" fit:true></ul>
		    </div> 
		      
		    <div title="权限" data-options="iconCls:'icon-reload'" style="overflow:auto;padding:10px;">   
		        
		    </div>   
		      
		</div>  
		
    </div>
       
    <div data-options="region:'center'" >
    	
    	<div id="dataTab"></div>
    </div>   
</body> 
<script type="text/javascript">
	
	// 修改登录密码
	function update(){
		// 获取ID
		var id = ${user.id}
		$('#userupdate').dialog({    
		    title: '修改页面',    
		    width: 600,    
		    height: 300,    
		    closed: false,    
		    cache: false,    
		    href: '${ctxPath}/login/toupdate?id='+id,    
		    modal: true,
		    buttons:[{
				text:'保存',
				handler:function(){
					$('#loginff').form('submit', {    
					    url:'${ctxPath}/login/update',    
					    onSubmit: function(){    
					    	var ret = $("#loginff").form('validate');
					    	return ret ;
					    },    
					    success:function(data){
					    	var ret='';
					    	if(data!=0){
					    		ret = "修改成功";
					    		//刷新
					    	//$('#title').datagrid("reload");	
					    		//关闭
					    	$('#userupdate').dialog("close");	
					    	}else{
					    		ret = "修改失败";
					    	}
					    	//提示消息
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
					$('#userupdate').window("close"); 
				}
			}]
		});
		
	};	
			
		
	var zTreeObj = null;
	//添加狗皮膏药
	var webpath = '${ctxPath }';
		var setting = {
			data: {
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "pid",
					rootPId: 0
				},
				//为了不让跳转 
				key: {
					url: "xUrl"
				}
			},
			//点击事件
			callback: {
				onClick: zTreeOnClick
			}
		};
	
	
	$(function(){
		 $.post(" ${ctxPath }/resoures/v_tree  ",  function(data){
			// 初始化
			zTreeObj = $.fn.zTree.init($("#tree1"), setting, data);
			//默认展开状态
			zTreeObj.expandAll(true);
		}, "json"); 
	});
	
	function zTreeOnClick(event, treeId, treeNode) {
		
		var title = treeNode.name;
	    //跳转路径
	    var url = webpath + treeNode.url;
	    //判断是不是父节点
	   if(treeNode.isParent != true){ 
		    //判断是不是子节点isParent
		    if($("#dataTab").tabs("exists",title)){
				//有就选中
				$("#dataTab").tabs("select",title);
			}else{//没有就添加
				$('#dataTab').tabs('add',{    
				    title:title,    
				   	href:url,    
				    closable:true,    
				    tools:[{    
				        iconCls:'icon-mini-refresh',    
				        handler:function(){    
				            alert('refresh');    
				        }    
				    }]    
				});
			}
	    }
		
	};
	 $(function(){
		// 初始化
		$('#dataTab').tabs({  
			fit :true 
		});
		
	}) 
	
	
	
</script>

</html>