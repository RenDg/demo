<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ include file="../commons/global.jsp" %>

<body class="easyui-layout">   
        <div class="easyui-layout" data-options="fit:true">   
            <div data-options="region:'west' ,title:'部门树' ,collapsed:true " style="width:180px">
            	<ul id="atee11" class="ztree" style="width:230px; overflow:auto;"></ul>
            </div> 
            
            <div data-options="region:'center', title:'用户管理'">
            	<!--Datagrid 表格  -->
          		 <div id="userDatagrid"></div>
          		 
          		 <div id="userdd"></div>
          		 
          		<div id="usertb">
					<label>姓名:</label>
					<input id="usernameInput" class="easyui-textbox" name="name"> 
					
					<label>创建时间:</label>
					<input id="startTimeInput" type= "text" class="easyui-datebox" name="firstDate"/>
					<label>至</label>
					<input id="endTimeInput" type= "text" class="easyui-datebox" name="endDate"/>
					
					
					<a id="search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>  
					<a id="clear" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">清空</a>  
					<a id="useradd" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a> 
					<a id="updateUser" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>  
					<a id="removeuser" href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-remove'">批量删除</a> 	
					<a id="user_auth" href="#" class="easyui-linkbutton" 
							data-options="iconCls:'icon-add'">分配角色</a>
				</div> 
          		 
            </div>   
        </div>   
<script type="text/javascript">

//分配权限
$("#user_auth").click(function(){
	 var select =$("#userDatagrid").datagrid("getSelections");
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
	 $('#userdd').dialog({    
		    title: '角色授权',    
		    width: 600,    
		    height: 400,    
		    closed: false,    
		    cache: false,    
		    href: '${ctxPath}/user/v_auth?userId='+select[0].id,    
		    modal: true ,
		    buttons:[{
				text:'保存',
				handler:function(){
					/*  var checked =zTreeOnCheck(checked);
					 alert(checked) */
					var resIds = getCheckedNodes();//返回ztree的选中节点函数
					var userid = select[0].id;
	                $.post("${ctxPath}/user/o_auth", 
	                		
	                		{ "userid": userid,"resIds":resIds },function(data){
	                			var ret = "";
						        if(data!=0){
						        	ret = '授权成功'
					        		//刷新
							    	$('#userDatagrid').datagrid("reload");	
							    		//关闭
							    	$('#userdd').dialog("close");	
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
					$('#userdd').dialog("close");	
				}
			}]
		}); 
})

//删除
$("#removeuser").click(function(){
	//获取所有选中的行
	var selects = $("#userDatagrid").datagrid("getSelections")
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
	    	$.post("${ctxPath}/user/del", {"ids":ids.join()}, function(data){
	    		if(data>0){
	    			//删除成功
	    			$.messager.alert('提示','删除成功');    
	    			//刷新
	    			$("#userDatagrid").datagrid("reload");
	    		}else{
	    			$.messager.alert('提示','删除失败');
	    		}
	    	}, "json");
	    }    
	}); 
	
})

//修改
$("#updateUser").click(function() {
	//获取选中的行
	var selects = $("#userDatagrid").datagrid("getSelections");
	//1.若没有选中部门给予提示
	if(selects.length == 0) {
		$.messager.alert('警告','请选择一条修改的记录');
		return;//结束执行
	}
	//2.若选中多哥部门也要给予提示
	if(selects.length > 1) {
		$.messager.alert('警告','请选择一条修改的记录,不能选择多条');
		return;//结束执行
	}
	var id = selects[0].id;

	$('#userdd').dialog({    
	    title: '修改部门',    
	    width: 600,    
	    height: 350,    
	    closed: false,    
	    cache: false,    
	    href: '${ctxPath}/user/init1?id=' + id,    
	    modal: true,
	    buttons:[{
			text:'保存',
			iconCls:'icon-save',
			handler:function(){
				//提交表单
				$("#userff").form("submit",{
					url:"${ctxPath}/user/add",    
				    onSubmit: function(){    
				        // do some check    
				        // return false to prevent submit; 
				        return $("#userff").form("validate");
				    },    
				    success:function(data){ 
				    	var msg = "修改失败";
				       	if(data != 0) {
				       		msg = "修改成功";
				       		//刷新datagrid
				       		$("#userDatagrid").datagrid("reload");
				       		//关闭dialog
				       		$("#userdd").dialog("close");
				       	}  
				       	$.messager.show({
				       		title:'我的消息',
				       		msg:msg,
				       		timeout:1000,
				       		showType:'slide'
				       	});
				    }    
				});
			}
		},{
			text:'关闭',
			iconCls:'icon-cancel',
			handler:function(){
				$("#userdd").dialog("close");
			}
		}]
	});
});
//添加：
$("#useradd").click(function() {
	$('#userdd').dialog({    
	    title: '修改部门',    
	    width: 600,    
	    height: 350,    
	    closed: false,    
	    cache: false,    
	    href: '${ctxPath}/user/toadd',    
	    modal: true,
	    buttons:[{
			text:'保存',
			iconCls:'icon-save',
			handler:function(){
				//提交表单
				$("#userff").form("submit",{
					url:"${ctxPath}/user/add",     
				    onSubmit: function(){    
				        // do some check    
				        // return false to prevent submit; 
				        return $("#userff").form("validate");
				    },    
				    success:function(data){ 
				    	var msg = "添加失败";
				       	if(data != 0) {
				       		msg = "添加成功";
				       		//刷新datagrid
				       		$("#userDatagrid").datagrid("reload");
				       		//关闭dialog
				       		$("#userdd").dialog("close");
				       	}  
				       	$.messager.show({
				       		title:'我的消息',
				       		msg:msg,
				       		timeout:1000,
				       		showType:'slide'
				       	});
				    }    
				});
			}
		},{
			text:'关闭',
			iconCls:'icon-cancel',
			handler:function(){
				$("#userdd").dialog("close");
			}
		}]
	});
});


$(function(){
	
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
		
		
	function zTreeOnClick(event, treeId, treeNode) {
	
		// 查询列表 时候 查询 部门的ID 
		$('#userDatagrid').datagrid('load',{"deptId":treeNode.id});


	};
	
	$(function(){
		$.post(" ${ctxPath }/dept/v_tree",  function(data){
			// 初始化
			zTreeObj = $.fn.zTree.init($("#atee11"), setting, data);
			//默认展开状态
			zTreeObj.expandAll(true);
		}, "json"); 
	});
	
	//清空
	$("#clear").click(function() {
		$("#usernameInput").textbox("clear");
		$("#startTimeInput").datebox("clear");
		$("#endTimeInput").datebox("clear");
	})
	//查询
	$("#search").click(function() {
		$("#userDatagrid").datagrid("load",{
			"name":$("input[name=name]").val(),
			"firstDate":$("input[name=firstDate]").val(),
			"endDate":$("input[name=endDate]").val()
		});
	});
	
	/**
		初始化datagrid 列表
	*/
	$("#userDatagrid").datagrid({
		fitColumns:true,//自动适应列，防止出现滚动条
	    url:'${ctxPath}/user/list',//数据来源
	    pagination:true,//显示分页组件
	    pageSize:20,//默认每页的大小
	    fit:true,
	    //rownumbers:true,
	    toolbar:'#usertb',
	    pageList:[10,20,50,400],//设置可选择大小列表
	    //sortable:是可序列列
	    //checkbox ： 将某一列，设成显示复选框；一般将id列，设为复选框列
	    columns:[[//将列 和 返回的json数据绑定  field ：属性名称  title：列名
	        {field:'id',title:'用户ID',checkbox:true},    
	        {field:'name',title:'真实姓名',width:'100'},
	        {field:'username',title:'用户名',width:'100'},
	        {field:'sex',title:'性别',width:'80',
	        	formatter:function(value) {
	        		if(value != null) {
	        			if(value == 1) {
	        				return "男";
	        			} else if(value == 2){
	        				return "女";
	        			} else {
	        				return "保密";
	        			}
	        		}
	        		return null;
	        	}	
	        }, 
	        {field:'age',title:'年龄',width:'180'}, 
	        {field:'telephone',title:'电话',width:'100'},
	        {field:'userType',title:'用户类型',width:'100',
	        	formatter:function(value) {
	        		if(value != null) {
	        			if(value == 1) {
	        				return "前台用户";
	        			} else {
	        				return "后台用户";
	        			}
	        		}
	        		return null;
	        	}
	        },
	        {field:'usersState',title:'用户状态',width:'100',
	        	formatter:function(value) {
	        		if(value != null) {
	        			if(value == 1) {
	        				return "激活";
	        			} else {
	        				return "关闭";
	        			}
	        		}
	        		return null;
	        	}	
	       	},
	        {field:'dept',title:'所属部门',width:'80',
	       		  formatter:function(value) {
	        		if(value != null) {
	        			return value.deptName;
	        		}
	        		return null;
	        	} 
	        },
	        {field:'updateDate',title:'修改时间',width:'80'},
	        {field:'createDate',title:'创建时间',width:'80'},
	    ]]
	});
	
	
})

	

</script>     
</body>  

