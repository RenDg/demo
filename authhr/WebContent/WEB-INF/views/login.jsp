<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="commons/global.jsp"%>
<%@ include file="commons/basejs.jsp"%>


<div id="logindd">Dialog Content.</div>

<script type="text/javascript">
         
	     $('#logindd').dialog({    
	    	    title: '登陆页面',    
	    	    width: 400,    
	    	    height: 200,    
	    	    closed: false,    
	    	    cache: false,    
	    	    href: '<%=request.getContextPath()%>/login/from',    
	    	    modal: true,
	    	    buttons:[{
					text:'登录',
					iconCls: 'icon-ok',
					handler:function(){
						
						$.messager.progress();	// 显示进度条
						$('#loginff').form('submit', {
							url: '<%=request.getContextPath()%>/login/login',
							onSubmit: function(){
								var isValid = $(this).form('validate');
								if (!isValid){
									$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
								}
								return isValid;	// 返回false终止表单提交
							},
							success: function(data){
								 
									//json字符串转换为字符串
				                    var json   = eval("("+data+")");
								if(json.success){
					            	//发送请求
					                window.location.href="<%=request.getContextPath() %>/login/hrindex"
					            }else{
					                alert("登陆失败");
					            } 
					         	$.messager.progress('close');	// 如果提交成功则隐藏进度条
							}
						});
					}
				},{
					text:'重置',
					iconCls: 'icon-no',
					handler:function(){
						$('#loginff').form("clear");
					}
				}]

	    	});   
     </script>