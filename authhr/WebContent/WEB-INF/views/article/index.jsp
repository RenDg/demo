<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../commons/global.jsp" %>
  <div class="easyui-layout" data-options="fit:true">   
          <div data-options="region:'west',title:'文档管理' ,collapsed:true" style="width:180px">
          <!-- ztree树 -->
          <ul id="articletree" class="ztree" style="width:230px; overflow:auto;"></ul>
          
          </div>   
            
         <div data-options="region:'center',title:'文档管理' ">
        		  <!--Datagrid 表格  -->
        		 <div id="articleDatagrid"></div>
        		 <!-- 添加 -->
        		 <div id="articledd"></div>
        		 
        		<div id="articletb">
			<label>姓名:</label>
			<input id="usernameInput" class="easyui-textbox" name="name"> 
			<a id="search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>  
			<a id="clear" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">清空</a>  
			<a id="articleadd" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a> 
			<a id="updatearticle" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>  
			<a id="removearticle" href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-remove'">批量删除</a> 	
			<a id="user_auth" href="#" class="easyui-linkbutton" 
					data-options="iconCls:'icon-add'">审核</a>
		</div> 
         </div>   
  </div>   
<script type="text/javascript">

$(function(){
	
	
	
	//ztree 树
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
		$('#articleDatagrid').datagrid('load',{"articleId":treeNode.id});

	
	};
	
	$(function(){
		$.post(" ${ctxPath }/channel/v_tree",  function(data){
			// 初始化
			zTreeObj = $.fn.zTree.init($("#articletree"), setting, data);
			//默认展开状态
			zTreeObj.expandAll(true);
		}, "json"); 
	});
	
	
	/**
	初始化datagrid 列表
*/
	$("#articleDatagrid").datagrid({
		//fitColumns:true,//自动适应列，防止出现滚动条
	    url:'${ctxPath}/article/list',//数据来源
	    pagination:true,//显示分页组件
	    pageSize:20,//默认每页的大小
	    //fit:true,
	    //rownumbers:true,
	    toolbar:'#articletb',
	    pageList:[10,20,50,400],//设置可选择大小列表
	    //sortable:是可序列列
	    //checkbox ： 将某一列，设成显示复选框；一般将id列，设为复选框列
	    columns:[[//将列 和 返回的json数据绑定  field ：属性名称  title：列名
	        {field:'id',title:'用户ID',checkbox:true},    
	        {field:'newsTitle',title:'文章标题',width:'100'},
	        {field:'newsAuthor',title:'录入员',width:'100'},
	        {field:'newsContent',title:'新闻内容',width:'100'},
	        {field:'states',title:'状态',width:'80',
	        	formatter:function(value) {
	        		if(value != null) {
	        			if(value == 0) {
	        				return "未审核";
	        			} else if(value == 1){
	        				return "审核通过";
	        			} else {
	        				return "审核失败";
	        			}
	        		}
	        		return null;
	        	}	
	        }, 
	        {field:'createDate',title:'创建时间',width:'80'},
	    ]]
	});
})

</script>