<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@ include file="/public/head.jspf"%>
    <style type="text/css">
    	form div{
    		margin:5px;
    	}
    </style>
    <script type="text/javascript">
    	$(function(){
    		$("input[name=type]").validatebox({
    			required:true,
    			missingMessage:'请输入类别名称'
    		});
    		//窗口弹出时默认关闭验证
    		$("#ff").form("disableValidation");
    		//注册button的事件
    		$("#btn").click(function(){
    			$("#ff").form("enableValidation");
    			//如果验证成功，则提交数据
    			if($("#ff").form("validate")){
    				//调用submit方法，提交数据
    				$("#ff").form('submit',{
					    url:'category_save.action',    
					    success: function(){
					        //关闭当前窗体
					        parent.$("#win").window("close");
					        //刷新页面 
					        //这里出现了兼容性问题，当出现这种问题时，要进行向上强转：dom-->jquery-->easyUi
					        //parent.$("iframe[title='类别管理']").contents().find("#dg").datagrid("reload");
					        var dg=parent.$("iframe[title='类别管理']").get(0).contentWindow.$("#dg").datagrid("reload");
					    }  
					}); 
    			};
    		});
    	});
    </script>
  </head>
	
<body>
	<form id="ff" method="post">
	    <div>   
	        <label for="type">类别:</label>   
	        <input type="text" name="type" />   
	    </div>   
	    <div>   
	        <label for="hot">
	        	热点：<input type="radio" name="hot" value="true" />
	        	非热点：<input type="radio" name="hot" value="false" />
	        </label> 
	    </div>
	    <a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">添加类别</a> 
	</form>
</body>
</html>
