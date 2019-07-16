<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@ include file="/public/head.jspf"%>
    <style type="text/css">
    	body{
    		margin: 1px;
    	}
    	.searchbox {
		  margin: -3;
		}
    </style>
    <script type="text/javascript">
		$(function() {
			$('#dg').datagrid({
	    		url:'category_queryJoinAccount.action',
	    		loadMsg:'加载中........',
	    		//斑马线,默认为true
	    		striped:true,
	    		//自适应行
	    		fitColumns:true,
	    		pageNumber:1,
	    		pageSize:5,
	    		pageList:[5,10,15,20,25],
	    		//指定id字段为标识字段，在删除，更新的时候有用，如果配置此字段，
	    		//在翻页的时候，被选中的记录是不会丢失
	    		idField:'id',
	    		/*
	    		rowStyler:function(index,row){
	    			if(index%2==0){
	    				return 'background-color:#00000f;color:#ff0000;';
	    			}else{
	    				return 'background-color:#f00000;color:#0000ff;';
	    			}
	    		},*/
	    		//单行选择，全选功能会失效
	    		//singleSelect:true,
	    		pagination:true,
	    		queryParams:{type:''},
	    		toolbar: [{
					iconCls: 'icon-add',
					text:'添加类别',
					handler: function(){
						parent.$("#win").window({
							title:'添加类别',
							width:300,
							heigth:100,
							content:'<iframe src="send_category_save.action" frameborder="0" ></iframe>'
						});
					}
				},'-',{
					iconCls: 'icon-edit',
					text:'更新类别',
					handler: function(){
						//1:判断是否有选中的行记录 
						var rows=$("#dg").datagrid("getSelections");
						if(rows.length!=1){
							$.messager.show({
								title:'错误提示',
								msg:'一次只能更新一条记录',
								timeout:2000,
								showType:'slide'
							});
						}else{
							//1：弹出更新页面
							parent.$("#win").window({
								title:'更新类别',
								width:320,
								heigth:250,
								content:'<iframe src="send_category_update.action" frameborder="0" ></iframe>'
							});
						}
					}
				 },'-',{
					iconCls: 'icon-remove',
					text:'删除类别',
					handler: function(){
						var rows=$("#dg").datagrid("getSelections");
						if(rows.length==0){
							//弹出提示信息
							$.messager.show({
								title:'错误提示',
								msg:'消息将在2秒后关闭。',
								timeout:2000,
								showType:'slide'
							});
						}else{
							//删除提示，如果确认则执行删除逻辑
							$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
							    if (r){    
							        //1:获取被选中的记录，然后从记录中获取相应的id
							        var ids = "";
							        for(var i=0;i<rows.length;i++){
							        	ids+=rows[i].id+",";
							        }
							        //2：拼接id的值，然后发送到后台
							        ids=ids.substring(0, ids.lastIndexOf(","));
							        //3:发送ajax请求
							        $.post("category_deleteByIds.action",
							        	{ids:ids},function(result){
							        		if(result=="true"){
							        			//取消选中所有行
							        			$("#dg").datagrid("uncheckAll");
							        			//如果删除成功，刷新当前页面
							        			$("#dg").datagrid("reload");
							        		}else{
							        			$.messager.show({
							        				title:'删除异常',
							        				msg:'删除失败，请检查操作',
							        				timeout:2000,
							        				showType:'slide'
							        			});
							        		}
							        	},"text"
							        );
							    }    
							}); 
						}
					}
				},'-',{
					text:"<input id='ss' name='search' />"
				}],
	    		
	    		frozenColumns:[[
	    			{field:'cc',checkbox:true},
	    			{field:'id',title:'编号',width:100}
	    		]],
	    		//配置dg的列字段field:列字段名称，与json的key捆绑,title:列标题
			    columns:[[
			        {field:'type',title:'类别名称',width:100,
			        		//用来格式化当前列的值,返回的是最终的数据
							formatter: function(value,row,index){
								return "<span title="+ value +">"+value+"</span>";
							}
			        },
			        {field:'hot',title:'热点',width:100,align:'right',
			        		formatter: function(value,row,index){
								if(value){
									return "<input type='checkbox' checked='checked' disabled='true' />";
								}else{
									return "<input type='checkbox' disabled='true' />";
								}
							}
			        },
			        {field:'account.login',title:'所属管理员',width:100,
			        		formatter: function(value,row,index){
			        			if(row.account!=null && row.account.login!=null){
			        				return row.account.login;
			        			}
							}
			        }
			    ]]
			});
			//把普通文本框转化为搜索文本框
			$('#ss').searchbox({
				//触发查询事件
				searcher:function(value,name){
					//获取当前查询的关键字，通过dg加载相应的信息
					$('#dg').datagrid('load',{type: value});
				},
				menu:'#mm', 
				prompt:'变型金钢' 
			});
		});
	</script>
    
  </head>
	
<body>
	<table id="dg"></table>
</body>
</html>
