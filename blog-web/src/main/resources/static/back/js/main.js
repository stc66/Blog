$(function(){
	$("#nav").tree({
		url: contextRoot + 'admin/nav',
		lines:true,
		onLoadSuccess:function(node,data){
			if(data){
				$(data).each(function(index,value){
					if(this.state == 'closed'){
						$("#nav").tree('expandAll');
					}
				})
			}
		},
		onClick:function(node){
			if(node.url){
				if($("#tabs").tabs('exists',node.text)){
					$("#tabs").tabs('select',node.text);
				}else{
					$("#tabs").tabs('add',{
						title:node.text,
						iconCls:node.iconCls,
						closable:true,
						href:node.url
					})
				}
			}
		}
	});
	

	
})


//左边导航菜单
$("#left ul li a").click(function(){
	var _href = $(this).attr("link");
	var _text = $(this).text();
	if(_href){
		$("#left ul li a").removeClass("active");
		$(this).addClass("active");
		
		if($("#tabs").tabs('exists',_text)){
			$("#tabs").tabs('select',_text);
		}else{
			$("#tabs").tabs('add',{
				title: _text,
				//iconCls:node.iconCls,
				closable: true,
				href: contextRoot + _href
			})
		}			
	}
})

//退出系统
function logout(){
	$.messager.confirm('确定操作', '确定要退出吗？', function (flag) {
		if (flag) {
			window.location.href=contextRoot + 'admin/logout';
		}
	});
}