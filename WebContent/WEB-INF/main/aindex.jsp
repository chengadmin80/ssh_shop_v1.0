<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@ include file="/public/head.jspf"%>
    <style type="text/css">
    	#menu{
    		width:200px;
    		/*border:1px solid red;*/
    	}
    	#menu ul{
    		list-style: none;
    		padding: 0px;
    		margin: 0px;
    	}
    	#menu ul li{
    		border-bottom: 1px solid #fff;
    	}
    	#menu ul li a{
    		/* 设置a标签的宽，和内间距 则先转化为块级元素 */
    		display: block;
    		background-color: #336699;
    		color: #fff;
    		padding: 5px;
    	}
    	#menu ul li a:HOVER {
    		background-color: #99CCFF;
    	}
    </style>
    <script type="text/javascript">
    	$(function(){
    		$("a[title]").click(function(){
    			//1：判断当前右边是否已有相应tab
    			var text=$(this).text();
    			var href=$(this).attr("title");
    			if($("#tt").tabs("exists",text)){
    				$("#tt").tabs("select",text);
    			}else{
    			//2：如果没有，添加一个TAB
    				$("#tt").tabs("add",{
							    title:text,
							    closable:true,
							    tools:[{
        								iconCls:"icon-mini-refresh",    
        								handler:function(){
            										alert("refresh");
        										}
    									}],
    							//href：默认是通过url地址加载远程的页面，但仅仅是body部分的内容
    							//href:"send_category_query.action",
    							//iframe 是内联框架，可以加载整个页面
    							//js是通过客户端的浏览器来进行解释的，所以第一种${shop}方式的写法可能会出错
    							//content:'<iframe src="${shop}/send_category_query.action"></iframe>',
    							content:'<iframe title="类别管理" frameborder="0" height="100%" width="100%" src="'+href+'"></iframe>'
					});
    			}
    		});
    	});
    </script>
  </head>

<body class="easyui-layout">
    <div data-options="region:'north',title:'欢迎来到易购后台管理系统',split:true" style="height:100px;"></div>   
    <div data-options="region:'west',title:'系统操作',split:true" style="width:200px;">
    	<!-- 此处显示的是系统菜单 -->
    	<div id="menu" class="easyui-accordion" data-options="fit:true" >   
    		<div title="基本操作" data-options="iconCls:'icon-save'">   
        		<ul>
	 				<li><a href="#" title="send_category_query.action">类别管理</a></li>
	 				<li><a href="#" title="send_product_query.action">商品管理</a></li>
	 			</ul>
    		</div>   
    		<div title="其他操作" data-options="iconCls:'icon-reload'">   
        		<ul>
	 				<li><a href="#">类别管理</a></li>
	 				<li><a href="#">商品管理</a></li>
	 			</ul> 
    		</div>   
    		  
		</div>
    </div>
    <div data-options="region:'center',title:'后台操作页面'" style="padding:1px;background:#fff;">
		<div id="tt" class="easyui-tabs" data-option="fit:true">
		    <div title="系统缺省页面" style="padding:10px;">
		    	此处以后显示相应的系统信息（当前操作系统的类型，包括当前项目的域名，硬件的相关配置，或者显示报表 
		    </div>
		</div>
    </div>
    <div id="win" data-options="collasible:false,minimizable:false,maximizable:false,modal:true" ></div>
</body>

</html>
