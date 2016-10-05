$(document).ready(function() {
	$(".navbar-fixed-height").height($(".my-navbar").height());
	$(".childnav-fixed-height").height($(".child-nav").height() + 2);
	//setContentMinHeight();
})

//设置文字垂直居中，谷歌浏览器加载图片的顺序问题，导致高度无法获取
$(window).load(function() {
	$(".index-text").eq(0).css("margin-top", ($(".auto").eq(0).height() - $(".index-text").eq(0).height()) / 2 + "px");
	$(".index-text").eq(1).css("margin-top", ($(".auto").eq(1).height() - $(".index-text").eq(1).height()) / 2 + "px");
})

$(window).resize(function() {
	$(".navbar-fixed-height").height($(".my-navbar").height());
	$(".childnav-fixed-height").height($(".child-nav").height() + 2);
	$(".index-text").eq(0).css("margin-top", ($(".auto").eq(0).height() - $(".index-text").eq(0).height()) / 2 + "px");
	$(".index-text").eq(1).css("margin-top", ($(".auto").eq(1).height() - $(".index-text").eq(1).height()) / 2 + "px");
	//setContentMinHeight();
})

$(window).scroll(function() {
	if($(window).width() > 768 && $(".left-content").height() > $(".right-content").height()) {
		var targetPercentage = 91;
		var targetID = ".myzz";
		var scrollHeight = $(window).scrollTop(),
			docHeight = $(document).height(),
			windowHeight = $(window).height();
		var offsetHeight = $(".fixPosition").offset().top;

		var scrollPosition = (scrollHeight / (docHeight - windowHeight)) * 100;
		scrollPosition = scrollPosition.toFixed(1);

		if(scrollPosition >= targetPercentage) {
			$(targetID).css({
				"position": "fixed",
				"margin-right": "15px",
				"bottom": "100px"
			});
		} else {
			$(targetID).removeAttr("style");
		}
	}
}).trigger('scroll');

//只有PC端才设置内容页的最小高度
function setContentMinHeight() {
	if($(window).width() > 768) {
		var minHeight = 0;
		if(navigator.userAgent.indexOf('Firefox') >= 0) {
			minHeight = 40;
		} else {
			minHeight = 60;
		}
		$(".left-content,.right-content").css("min-height", ($(window).height() - $(".navbar-fixed-height").outerHeight() - $(".childnav-fixed-height").outerHeight() - $("footer").outerHeight() - minHeight) + "px");
	}
}