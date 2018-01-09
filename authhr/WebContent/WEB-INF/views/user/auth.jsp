<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../commons/global.jsp"%>

<ul id="tree3" class="ztree" style="width:230px; overflow:auto;"></ul>

<script type="text/javascript">
$(function(){
	var zTreeObj=null,
	
	setting = {
			data: {
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "pid",
					rootPId: 0
				}
			},
			//复选框
			check: {
				enable: true,
				chkStyle: "checkbox"
			},
	};
	//初始化
	$(function(){
		 var userId = "${userId}";
		$.post("${ctxPath}/role/v_ztree",{"userId":userId },
			function(data){
			zTreeObj = $.fn.zTree.init($("#tree3"), setting, data);
			//默认展开状态
			zTreeObj.expandAll(true);
		}, "json");
	});	
})	
	//返回ztree选中节点 获取资源的ID
	function getCheckedNodes(){
		var treeObj = $.fn.zTree.getZTreeObj("tree3");
		var nodes = treeObj.getCheckedNodes(true);
		var ids = "";
		//  因为返回的是集合 给他转换成字符串
		for (var i = 0; i < nodes.length; i++) {
			// 用  , 分割
			 ids = ids +","+nodes[i].id;
		}
		return  ids;
	}
</script>