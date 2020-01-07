<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>web应用</title>
<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<script type="text/javascript" src="easyui/jquery.min.js">
</script>
<script type="text/javascript" src="easyui/jquery.easyui.min.js">
</script>
</head>
<body class="easyui-layout">
	 <div data-options="region:'north'" style="height:60px;">
	 <h3 style="padding-left:20px">延大教学管理系统</h3>
	 </div>
	 <!-- 导航菜单 -->
     <div data-options="region:'west',title:'系统菜单',split:true,iconCls:'icon-man'" style="width:260px;">
	    <!-- 折叠面板start -->
	    <div id="aa" class="easyui-accordion" data-options="fit:true,border:false">
	    <div title="教学管理" data-options="iconCls:'icon-save',selected:true" style="overflow:auto;padding:6px;padding-left:22px;">
			<h4 style="color:#0099FF;" onclick="addTab('学员管理','student_grid.html')">学员管理</h4>
			<h4 style="color:#0099FF;" onclick="addTab('员工管理')">员工管理</h4>
			<h4 style="color:#0099FF;" onclick="addTab('教师管理')">教师管理</h4>
	    </div>
	    <div title="部门管理" data-options="iconCls:'icon-print'" style="padding:10px;">
	    <div id="sm" class="easyui-sidemenu" data-options="data:data,border:false," style="width:100%"></div>
	    </div>
	    <div title="系统管理" data-options="iconCls:'icon-search'">
			content3
	    </div>
	  </div>
    <!-- 折叠面板end -->
    </div>
    <!-- 中间面板 -->
    <div data-options="region:'center',border:false">
    <!-- 选项卡 -->
	    <div id="tabs" class="easyui-tabs" data-options="fit:true">
		    <div title="系统主页" style="padding:20px;display:none;">
				延大教学管理系统
		    </div>
		   <!--  <div title="数据表格" style="padding:20px;display:none;">
				<table class="easyui-datagrid">
				    <thead>
						<tr>
							<th data-options="field:'code'">Code</th>
							<th data-options="field:'name'">Name</th>
							<th data-options="field:'price'">Price</th>
						</tr>
				    </thead>
				    <tbody>
						<tr>
							<td>001</td><td>name1</td><td>2323</td>
						</tr>
						<tr>
							<td>002</td><td>name2</td><td>4612</td>
						</tr>
					</tbody>
				</table>
		    </div>
		  </div> -->
    	</div>
    
    <!-- 脚本 -->
    <script type="text/javascript">
    	function addTab(tabName,source){
    		console.info(source);
    		//判断选项卡是否存在
    		if($("#tabs").tabs("exists",tabName)){
    			$("#tabs").tabs("select",tabName);//选中选项卡
    			return;
    		}
    		$("#tabs").tabs("add",{
    			title: tabName,
    			href:source,
    			/* content:"Hello easyui", */
    			closable:true,
    		});
    	}
    </script>
    
     <script type="text/javascript">
        var data = [{
            text: 'Item1',
            iconCls: 'icon-sum',
            state: 'open',
            children: [{
                text: 'Option1'
            },{
                text: 'Option2'
            },{
                text: 'Option3',
                children: [{
                    text: 'Option31'
                },{
                    text: 'Option32'
                }]
            }]
        },{
            text: 'Item2',
            iconCls: 'icon-more',
            children: [{
                text: 'Option4'
            },{
                text: 'Option5'
            },{
                text: 'Option6'
            }]
        }];
 
        function toggle(){
            var opts = $('#sm').sidemenu('options');
            $('#sm').sidemenu(opts.collapsed ? 'expand' : 'collapse');
            opts = $('#sm').sidemenu('options');
            $('#sm').sidemenu('resize', {
                width: opts.collapsed ? 60 : 200
            })
        }
    </script>
</body>
</html>