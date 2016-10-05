$(function() {
	//登录界面
	$("#login").dialog({
		title: "登录后台",
		width: 300,
		height: 180,
		modal: true,
		closable:false,
		draggable:false,
		iconCls: 'icon-login',
		buttons: '#btn'
	});

	//管理员账号验证
	$("#userName").validatebox({
		required: true,
		missingMessage: '请输入管理员账号',
		invalidMessage: '管理员账号不得为空'
	});

	//管理员密码验证
	$("#password").validatebox({
		required: true,
		validType: 'length[4,30]',
		missingMessage: '请输入管理员密码',
		invalidMessage: '管理员密码不得为空并且长度大于4小于30'
	});

	//加载时验证判断
	if(!$("#userName").validatebox('isValid')) {
		$("#userName").focus();
	} else if(!$("#password").validatebox('isValid')) {
		$("#password").focus();
	}

	//单击登录
	$("#btn").click(function() {
		if(!$("#userName").validatebox('isValid')) {
			$("#userName").focus();
		} else if(!$("#password").validatebox('isValid')) {
			$("#password").focus();
		}else{
			$.ajax({
				url: contextRoot + 'admin/login',
				type:'post',
				data:{
					userName:$("#userName").val(),
					password:$("#password").val()
				},
				beforeSend:function(){
					$.messager.progress({
						text:"正在登录中..."
					});
				},
				success:function(data,response,status){
					$.messager.progress('close');
					if(data.flag){
						location.href = contextRoot + 'admin/main';
					}else{
						$.messager.alert('登录失败！',data.msg,'warning',function(){
							$('#password').select();
						});
					}
				}
			});
		}
	});
})