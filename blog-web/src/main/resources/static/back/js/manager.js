//扩展 dateTimeBox
$.extend($.fn.datagrid.defaults.editors, {
	datetimebox : {
		init: function(container, options){
			var input = $('<input type="text">').appendTo(container);
			options.editable = false;
			input.datetimebox(options);
			return input;
		},
		getValue: function(target){
			return $(target).datetimebox('getValue');
		},
		setValue: function(target, value){
			$(target).datetimebox('setValue', value);
		},
		resize: function(target, width){
			$(target).datetimebox('resize', width);
		},
		destroy : function (target) {
			$(target).datetimebox('destroy');
		},
	}
});

$(function(){
	$("#manager").datagrid({
		url: contextRoot + "admin/manager",
		fit : true,
		fitColumns : true,
		striped : true,
		nowrap : true,
		rownumbers : true,
		border : false,
		pagination : true,
		pageSize : 20,
		pageList : [2, 20, 30, 40, 50],
		pageNumber : 1,
		sortName : 'name',
		sortOrder : 'desc',
		toolbar : '#manager_tool',
        frozenColumns:[[
                        {
							 field:"id",
							 title:"自动编号",
							 width:300,
							 checkbox:true,
							 sortable : true,
							 fixed:true
                        },
                        {
							field:"name",
							title:"用户名",
							width:100,
							sortable : true               	 
                        }       
                       ]], 		
		columns:[[
/*		        {
		        	field:"id",
		        	title:"自动编号",
		        	width:100,
		        	checkbox:true,
		        	sortable : true
		        },
		        {
		        	field:"name",
		        	title:"用户名",
		        	width:100,
		        	sortable : true
		        },*/
		        {
		        	field:"password",
		        	title:"密码",
		        	width:100,
		        	sortable : true,
					formatter : function (value, rowData, rowIndex) {
						return 'PWD:' + value;
					}		        	
		        },
				{
					field : 'auth',
					title : '拥有权限',
					width:100
				}		        
		        ]],
				onRowContextMenu : function (e, rowIndex, rowData) {
					e.preventDefault();
					$('#menu').menu('show', {
						left : e.pageX,
						top : e.pageY,
					});
				}		        
	});
	
	$('#manager_add').dialog({
		width : 350,
		title : '新增管理',
		modal : true,
		closed : true,
		iconCls : 'icon-user-add',
		buttons : [{
			text : '提交',
			iconCls : 'icon-add-new',
			handler : function () {
				if ($('#manager_add').form('validate')) {
					$.ajax({
						url : contextRoot + 'admin/addUser',
						type : 'post',
						data : {
							manager : $('input[name="manager"]').val(),
							password : $('input[name="password"]').val(),
							auth : $('#auth').combotree('getText'),
						},
						beforeSend : function () {
							$.messager.progress({
								text : '正在新增中...',
							});
						},
						success : function (data, response, status) {
							$.messager.progress('close');
							
							if (data) {
								$.messager.show({
									title : '提示',
									msg : '新增管理成功',
								});
								$('#manager_add').dialog('close').form('reset');
								$('#manager').datagrid('reload');
							} else {
								$.messager.alert('新增失败！', '未知错误导致失败，请重试！', 'warning');
							}
						}
					});
				}
			},
		},{
			text : '取消',
			iconCls : 'icon-redo',
			handler : function () {
				$('#manager_add').dialog('close').form('reset');
			},
		}],
	});	


	$('#manager_edit').dialog({
		width : 350,
		title : '修改管理',
		modal : true,
		closed : true,
		iconCls : 'icon-user-add',
		buttons : [{
			text : '提交',
			iconCls : 'icon-edit-new',
			handler : function () {
				if ($('#manager_edit').form('validate')) {
					$.ajax({
						url : contextRoot + 'admin/editUser',
						type : 'post',
						data : {
							id : $('input[name="id_edit"]').val(),
							password : $('input[name="password_edit"]').val(),
							auth : $('#auth_edit').combotree('getText'),
						},
						beforeSend : function () {
							$.messager.progress({
								text : '正在修改中...',
							});
						},
						success : function (data, response, status) {
							$.messager.progress('close');
							if (data) {
								$.messager.show({
									title : '提示',
									msg : '修改管理成功',
								});
								$('#manager_edit').dialog('close').form('reset');
								$('#manager').datagrid('reload');
							} else {
								$.messager.alert('修改失败！', '未知错误或没有任何修改，请重试！', 'warning');
							}
						}
					});
				}
			},
		},{
			text : '取消',
			iconCls : 'icon-redo',
			handler : function () {
				$('#manager_edit').dialog('close').form('reset');
			},
		}],
	});	
	
	//管理帐号
	$('input[name="manager"]').validatebox({
		required : true,
		validType : 'length[2,20]',
		missingMessage : '请输入管理名称',
		invalidMessage : '管理名称在 2-20 位',
	});
	
	//管理密码
	$('input[name="password"]').validatebox({
		required : true,
		validType : 'length[6,30]',
		missingMessage : '请输入管理密码',
		invalidMessage : '管理密码在 6-30 位',
	});
	
	//修改管理密码
	$('input[name="password_edit"]').validatebox({
		//required : true,
		validType : 'length[6,30]',
		missingMessage : '请输入管理密码',
		invalidMessage : '管理密码在 6-30 位',
	});	
	
	//分配权限
	$('#auth').combotree({
		url : contextRoot + 'admin/nav',
		required : true,
		lines : true,
		multiple : true,
		checkbox : true,
		onlyLeafCheck : true,
		onLoadSuccess : function (node, data) {
			var _this = this;
			if (data) {
				$(data).each(function (index, value) {
					if (this.state == 'closed') {
						$(_this).tree('expandAll');
					}
				});
			}
		},
	});
	
	manager_tool = {
			reload : function () {
				$('#manager').datagrid('reload');
			},
			redo : function () {
				$('#manager').datagrid('unselectAll');
			},			
			add : function () {
				$('#manager_add').dialog('open');
				$('input[name="manager"]').focus();
			},
			edit : function () {
				var rows = $('#manager').datagrid('getSelections');
				if (rows.length > 1) {
					$.messager.alert('警告操作！', '编辑记录只能选定一条数据！', 'warning');
				} else if (rows.length == 1) {
					$.ajax({
						url : contextRoot + 'admin/getUser',
						type : 'post',
						data : {
							id : rows[0].id,
						},
						beforeSend : function () {
							$.messager.progress({
								text : '正在获取中...',
							});
						},
						success : function (data, response, status) {
							$.messager.progress('close');
							if (data) {
								var obj = data;
								$('#manager_edit').form('load', {
									id_edit : obj.id,
									manager_edit : obj.name,
									//auth_edit : obj.auth,
								}).dialog('open');
								//分配权限,字符裁取一下要放在open下面，否则为空时打不开弹窗
								var auth = obj.auth.split(',');
								$('#auth_edit').combotree({
									url : contextRoot + 'admin/nav',
									required : true,
									lines : true,
									multiple : true,
									checkbox : true,
									onlyLeafCheck : true,
									onLoadSuccess : function (node, data) {
										var _this = this;
										if (data) {
											$(data).each(function (index, value) {
												if ($.inArray(value.text, auth) != -1) {
													var _node = $(_this).tree("find",value.id);
													$(_this).tree('check', _node.target);
											    }
												if (this.state == 'closed') {
													$(_this).tree('expandAll');
												}												
											});
										}
									},
								});
								
							} else {
								$.messager.alert('获取失败！', '未知错误导致失败，请重试！', 'warning');
							}
						}
					});
				} else if (rows.length == 0) {
					$.messager.alert('警告操作！', '编辑记录至少选定一条数据！', 'warning');
				}
			},			
			remove : function () {
				var rows = $('#manager').datagrid('getSelections');
				if (rows.length > 0) {
					$.messager.confirm('确定操作', '您正在要删除所选的记录吗？', function (flag) {
						if (flag) {
							var ids = [];
							for (var i = 0; i < rows.length; i ++) {
								ids.push(rows[i].id);
							}
							//console.log(ids.join(','));
							$.ajax({
								type : 'POST',
								url : contextRoot + 'admin/deleteUser',
								data : {
									ids : ids.join(','),
								},
								beforeSend : function () {
									$('#manager').datagrid('loading');
								},
								success : function (data) {
									if (data) {
										$('#manager').datagrid('loaded');
										$('#manager').datagrid('load');
										$('#manager').datagrid('unselectAll');
										$.messager.show({
											title : '提示',
											msg : data + '个管理被删除成功！',
										});
									}
								},
							});
						}
					});
				} else {
					$.messager.alert('提示', '请选择要删除的记录！', 'info');
				}
			},
			search:function(){
				$('#manager').datagrid('load', {
					user : $.trim($('input[name="user"]').val()),
					date_from : $('input[name="date_from"]').val(),
					date_to : $('input[name="date_to"]').val(),
				});			
			}
	};	
})