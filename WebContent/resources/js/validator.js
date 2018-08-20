/* 加载声音开始*/
function getStyleNew(link, event) {
	if (navigator.appName == "Microsoft Internet Explorer") {
		if (window.event.keyCode == 13) {
			window.event.keyCode = 0;
			prodCount();
			link.ondblclick();
		}
	} else {
		var keycode = event.keyCode;
		if (keycode == 13) {
			judgeUniqueness(link);
			prodCount();
			link.ondblclick(event);
			return false;
		}
	}
}

function judgeUniqueness(link) {
	var value = link.value;
	var array = value.split('\n');
	for (i = 0; i < array.length; i++) {
		if (array[i].length > 0 && array[i].length != 10
				&& array[i].length != 19) {
			play();
			return false;
		}
	}
	for (i = 0; i < array.length; i++) {
		for (j = i + 1; j < array.length; j++) {
			if (array[i].length != 10 && array[j].length != 10
					&& array[i].length > 0 && array[j].length > 0
					&& array[i] == array[j]) {
				play();
				return false;
			}
		}
	}
}

function play() {
	var dewp = document.getElementById("dewplayerjs");
	if (dewp != null)
		dewp.dewplay();
}
function stop() {
	var dewp = document.getElementById("dewplayerjs");
	if (dewp != null)
		dewp.dewstop();
}
function pause() {
	var dewp = document.getElementById("dewplayerjs");
	if (dewp != null)
		dewp.dewpause();
}
function next() {
	var dewp = document.getElementById("dewplayerjs");
	if (dewp != null)
		dewp.dewnext();
}
function prev() {
	var dewp = document.getElementById("dewplayerjs");
	if (dewp != null)
		dewp.dewprev();
}
function set(file) {
	var dewp = document.getElementById("dewplayerjs");
	if (dewp != null)
		dewp.dewset(file);
}
function go(index) {
	var dewp = document.getElementById("dewplayerjs");
	if (dewp != null)
		dewp.dewgo(index);
}
function setpos(ms) {
	var dewp = document.getElementById("dewplayerjs");
	if (dewp != null)
		dewp.dewsetpos(ms);
}
function getpos() {
	var dewp = document.getElementById("dewplayerjs");
	if (dewp != null)
		alert(dewp.dewgetpos());
}
function volume(val) {
	var dewp = document.getElementById("dewplayerjs");
	if (dewp != null)
		alert(dewp.dewvolume(val));
}
/* 加载声音结束*/
function validateDate(id, desc) {
	var param = document.getElementById(id).value;
	if (param == '') {
		alert(desc + '不能为空');
		document.getElementById(id).focus();
		return false;
	} else {
		return true;
	}
}

function yan() {
	var test = {
		node : null,
		count : 10,
		start : function() {
			if (this.count > 0) {
				this.node.value = this.count--;
				var _this = this;
				setTimeout(function() {
					_this.start();
				}, 1000);
			} else {
				this.node.removeAttribute("disabled");
				this.node.value = "再次发送";
				this.count = 10;
			}
		},
		// 初始化
		init : function(node) {
			this.node = node;
			this.node.setAttribute("disabled", true);
			this.start();
		}
	};
	var btn = document.getElementById("mainForm:newAddSaleInvoiceForm:btn");
	test.init(btn);
}


// 款式时间查询条件简单验证
function checkTime(id) {
	var date = document.getElementById(id).value;
	if (date != null && date != "" && date.toString().length != 19) {
		alert("时间输入有误,完整的时间格式 为：\“年-月-日 分:时:秒\”");
		return false;
	}
	return true;
}

// 为输入的商品价格监听双击和回车事件
function checkProductPrice() {
	if (navigator.appName == "Microsoft Internet Explorer") {
		if (window.event.keyCode == 13) {
			window.event.keyCode = 0;
			link.ondblclick();
		}
	} else {
		var keycode = event.keyCode;
		if (keycode == 13) {
			link.ondblclick(event);
			return false;
		}
	}
}
// 计算上交营业款
function calCashIn(link, event, cashIn, tempCashIn) {
	replacePay(link, event);// 验证输入值
	cashIn.value = tempCashIn.value - link.value;
}
function showandhidedetailInfo(param) {
	var detailInfo = document.getElementById('detailInfo');
	if (detailInfo.style.display == 'none') {
		detailInfo.style.display = 'block';
		if (param == '会员') {
			document.getElementById('outputInfo').innerHTML = '-详细信息';
		} else if (param == '款式') {
			document.getElementById('outputInfo').innerHTML = '-更多查询条件';
		} else if (param == '订单') {
			document.getElementById('outputInfo').innerHTML = '-点击收起查询';
		} else if (param == '计划') {
			document.getElementById('outputInfo').innerHTML = '-详细信息';
		}
	} else if (detailInfo.style.display == 'block') {
		detailInfo.style.display = 'none';
		if (param == '会员') {
			document.getElementById('outputInfo').innerHTML = '+详细信息';
		} else if (param == '款式') {
			document.getElementById('outputInfo').innerHTML = '+更多查询条件';
		} else if (param == '订单') {
			document.getElementById('outputInfo').innerHTML = '+点击展开查询';
		} else if (param == '计划') {
			document.getElementById('outputInfo').innerHTML = '+详细信息';
		}
	}
}

function orderInfo(param) {
	var orderInfo = document.getElementById('orderInfo');
	if (orderInfo.style.display == 'none') {
		orderInfo.style.display = 'block';
		if (param == '排序') {
			document.getElementById('ordOutputInfo').innerHTML = '-排序信息';
		}
	} else if (orderInfo.style.display == 'block') {
		orderInfo.style.display = 'none';
		if (param == '排序') {
			document.getElementById('ordOutputInfo').innerHTML = '+排序';
		}
	}
}

/**
 * MAC
 */
function doAlert(s) {
	var bro = $.browser;
	var binfo = "";
	var binf = "";
	if (bro.msie) {
		binfo = "IE" + bro.version;
		binf = "IE";
	}
	if (bro.mozilla) {
		binfo = "FF" + bro.version;
		binf = "FF";
	}
	if (bro.safari) {
		binfo = "AS" + bro.version;
	}
	if (bro.opera) {
		binfo = "Opera" + bro.version;
	}
	if (binf == 'FF') {
		SetCookie("mac", s);
		document.getElementById('LoginForm:mac').value = s;
	}
	if (binf == 'IE') {
		setCookie('mac', s);
		document.getElementById('LoginForm:mac').value = s;
	}

}
// 设定Cookie值-将值保存在Cookie中
function SetCookie(name, value) {
	var expdate = new Date(); // 获取当前日期
	var argv = SetCookie.arguments; // 获取cookie的参数
	var argc = SetCookie.arguments.length; // cookie的长度
	var expires = (argc > 2) ? argv[2] : null; // cookie有效期
	var path = (argc > 3) ? argv[3] : null; // cookie路径
	var domain = (argc > 4) ? argv[4] : null; // cookie所在的应用程序域
	var secure = (argc > 5) ? argv[5] : false; // cookie的加密安全设置
	if (expires != null)
		expdate.setTime(expdate.getTime() + (expires * 1000));
	document.cookie = name + "=" + escape(value)
			+ ((expires == null) ? "" : ("; expires=" + expdate.toGMTString()))
			+ ((path == null) ? "" : ("; path=" + path))
			+ ((domain == null) ? "" : ("; domain=" + domain))
			+ ((secure == true) ? "; secure" : "");
}

// 取会员
function getMemberCard(link, event) {
	if (navigator.appName == "Microsoft Internet Explorer") {
		if (window.event.keyCode == 13) {
			window.event.keyCode = 0;
			link.ondblclick();
		}
	} else {
		var keycode = event.keyCode;
		if (keycode == 13) {
			link.ondblclick(event);
			return false;
		}
	}
}

var ManyStyleCode = "";
function getStyleCodeIsnull() {
	ManyStyleCode = null;
	ManyStyleCode = "";
}
function getStyleCode(comId, comValue, bean, close) {
	var explorer = window.navigator.userAgent;
	// ie
	if (explorer.indexOf("MSIE") >= 0) {
		if (bean == "many") {
			ManyStyleCode = ManyStyleCode
					+ document.getElementById(comValue).outerText + ",";
			document.getElementById(comId).value = ManyStyleCode;
		} else {
			document.getElementById(comId).value = document
					.getElementById(comValue).outerText;

		}
	} else {
		if (bean == "many") {
			ManyStyleCode = ManyStyleCode
					+ document.getElementById(comValue).textContent + ",";
			document.getElementById(comId).value = ManyStyleCode;
		} else {
			document.getElementById(comId).value = document
					.getElementById(comValue).textContent;
			document.getElementById(close).style.display = "none";
			document.getElementById(comId).focus();// 定位光标

		}

	}
}

/**
 * 检查浏览器是否安装flash插件
 */
function Checkflash() {
	var explorer = window.navigator.userAgent;
	if (explorer.indexOf("MSIE") >= 0) {
		try {
			var swf = new ActiveXObject("ShockwaveFlash.ShockwaveFlash");

		} catch (e) {
			var r = confirm("您使用的浏览器没有安装flash插件,系统部分内容无法显示!\n是否下载安装！")
			if (r == true) {
				window.location
						.href("http://aihdownload.adobe.com/bin/live/install_flashplayer12x32_mssd_aaa_aih.exe");
			}
		}
	} else if (explorer.indexOf("Firefox") >= 0) {
		var swf = navigator.plugins["Shockwave Flash"];
		if (swf == null) {
			var r = confirm("您使用的浏览器没有安装flash插件,系统部分内容无法显示!\n是否下载安装！")
			if (r == true) {
				window.location = "http://aihdownload.adobe.com/bin/live/install_flashplayer12x32_mssd_aaa_aih.exe";
			}
		}
		// (swf)?alert("已安装插件"):alert("没有安装插件");
	}
}

/**
 * 检查用户登录浏览器版本和型号
 */
function CheckBrowser() {
	var explorer = window.navigator.userAgent;
	// ie
	if (explorer.indexOf("MSIE") >= 0) {
		var isIE = !!window.ActiveXObject;
		var isIE6 = isIE && !window.XMLHttpRequest;
		var isIE8 = isIE && !!document.documentMode;
		var isIE7 = isIE && !isIE6 && !isIE8;
		if (isIE6 || isIE7) {
			var r = confirm("您使用的浏览器版本太低,请使用火狐浏览器访问!\n是否下载安装火狐浏览器")
			if (r == true) {
				// window.location.href("http://download.microsoft.com/download/4/E/3/4E30CB91-FC6D-4358-AE62-C8F1382D2C03/IE8-WindowsServer2003-x64-CHS.exe");
				window.location
						.href("http://download.firefox.com.cn/releases/webins3.0/official/zh-CN/Firefox-latest.exe");
			} else {
				window.opener = null; // for Ie6
				window.open("", "_self"); // for ie7-8
				window.close();
			}
		}
	}
	// firefox
	// http://download.firefox.com.cn/releases/webins3.0/official/zh-CN/Firefox-latest.exe
	else if (explorer.indexOf("Firefox") >= 0) {
	} else if (explorer.indexOf("Chrome") >= 0) {

	} else if (explorer.indexOf("Prism") >= 0) {

	} else {
		var r = confirm("请您使用火狐浏览器访问系统 \n 是否下载安装火狐浏览器!")
		if (r == true) {
			top.location = 'http://download.firefox.com.cn/releases/webins3.0/official/zh-CN/Firefox-latest.exe';
		} else {
			window.opener = null; // for Ie6
			window.open("", "_self"); // for ie7-8
			window.close();
		}

	}

	// //Chrome
	// else if(explorer.indexOf("Chrome") >= 0){
	// alert("Chrome");
	// }
	// //Opera
	// else if(explorer.indexOf("Opera") >= 0){
	// alert("Opera");
	// }
	// //Safari
	// else if(explorer.indexOf("Safari") >= 0){
	// alert("Safari");
	// }

}
/**
 * 2012-4-17 * add by xbs 计算导入数据的总数量
 * 
 * @param
 */
function countTxt(targetSrc, displaySrc, radioId) {
	var array = targetSrc.value.split('\n');
	var f = radioId.value;
	var tempArray;
	var count = 0;
	for (i = 0; i < array.length; i++) {
		if (f == 'true') {
			tempArray = array[i].split(',');
			if (tempArray.length == 2 && tempArray[0] != ''
					&& tempArray[1] != '' && checkintegerExtend(tempArray[1])) {
				count += parseInt(tempArray[1]);
			}
		} else {
			count = array.length;
		}

	}
	displaySrc.innerHTML = count;
}
/**
 * 2012-4-16 * add by xbs 验证扫描枪导出也有用到数据的正确性
 * 
 * @param 被验证的文本框
 */
function checkTxt(targetSrc) {
	if (targetSrc.value == '') {
		alert('没有数据!');
		return false;
	}
	var array = targetSrc.value.split('\n');
	var tempArray;
	var errstr = '';
	for (i = 0; i < array.length; i++) {
		tempArray = array[i].split(',');
		if (tempArray.length != 2 || tempArray[0] == '' || tempArray[1] == ''
				|| !checkintegerExtend(tempArray[1])) {
			errstr += array[i] + "\n";
		}
	}
	if (errstr != '') {
		alert('格式有误!' + '\n' + errstr);
		return false;
	}
	return true;
}

/**
 * 2012-4-16 * add by xbs 验证扫描枪导入数据的正确性
 * 
 * @param 被验证的文本框
 * 
 */
function checkTxt(targetSrc, targetSrc1) {

	var f = targetSrc1.value;
	if (targetSrc.value == '') {
		alert('没有数据!');
		return false;
	}
	if (f == 'true') {
		var array = targetSrc.value.split('\n');
		var tempArray;
		var errstr = '';

		for (i = 0; i < array.length; i++) {
			tempArray = array[i].split(',');
			if (f == 'true') {
				if (tempArray.length != 2 || tempArray[0] == ''
						|| tempArray[1] == ''
						|| !checkintegerExtend(tempArray[1])) {
					errstr += array[i] + "\n";
				}
			}
		}
	}
	if (f == 'false') {
		var array = targetSrc.value.split('\n');
		var tempArray;
		var errstr = '';
		for (i = 0; i < array.length; i++) {
			tempArray = array[i];
			var j = tempArray.indexOf(',')
			if (j != -1) {
				errstr += array[i] + "\n";
			}
		}
	}
	if (errstr != '') {
		alert('格式有误!' + '\n' + errstr);
		return false;
	}
	return true;
}
function setRadioValue(value, value1) {
	value1.value = value.value;
}
/**
 * 2012-4-16 * add by xbs 清空文本框的值
 * 
 * @param 被清空的文本框
 */
function clearTxt(targetSrc, displaySrc) {
	targetSrc.value = "";
	countTxt(targetSrc, displaySrc);
}

/**
 * 2012-4-16
 * 
 * @param 刷新验证码
 * @return 验证码
 */
function refresh(param) {
	document.getElementById('checknum').src = '/' + param
			+ '/pages/sys/loginChecknum.jsp?1=' + new Date();
}

/* 款式管理显示start图片 */
function showPic(id, picName, picID, picID2, styleCode, codeID) {
	RichFaces.$(id).show();
	if (picName != '') {
		document.getElementById(picID).src = picID2.src + 'thumbnail100/'
				+ picName;
	} else {
		document.getElementById(picID).src = picID2.src + 'default.jpg';
	}
	if (codeID != '') {
		codeID.innerHTML = styleCode;
	}
}

/* 图片服务 款式管理显示start图片 */
function showPicFtp(id, picName, picID, ftpUrl, styleCode, codeID) {
	RichFaces.$(id).show();
	if (picName != '') {
		document.getElementById(picID).src = ftpUrl + picName;
	} else {
		document.getElementById(picID).src = ftpUrl + 'default.jpg';
	}
	if (codeID != '') {
		codeID.innerHTML = styleCode;
	}

}

/* 款式管理显示end图片 */
/* 款式管理隐藏start图片 */
function hidePic(id) {
	RichFaces.$(id).hide();
}
/* 款式管理隐藏end图片 */
/* 淘宝上传图片显示start图片 */
function picShow(id, picName, picID, picID2) {
	RichFaces.$(id).show();
	if (picName != '') {
		document.getElementById(picID).src = picID2.src + picName;
	} else {
		document.getElementById(picID).src = picID2.src + 'default.jpg';
	}
}
/* 淘宝上传显示end图片 */
/* 淘宝上传隐藏start图片 */
function picHide(id) {
	RichFaces.$(id).hide();
}
/* 淘宝上传隐藏end图片 */

function getTextsize(textid) {
	var b = textid.value;
	document.getElementById("mainForm:msgForm:textsize").innerHTML = b.length;

}
/* 求短信群发字数 */

function getTextsmsSignsize(textid) {
	var b = textid.value;
	var b1 = document.getElementById("mainForm:msgForm:smsSignId").innerHTML;
	document.getElementById("mainForm:msgForm:textsize").innerHTML = b.length
			+ b1.length;

}
// 获取屏幕分辨率
function screens() {
	var xx = document.getElementById("LoginForm:invoiceWidth");
	var yy = document.getElementById("LoginForm:invoiceHeight");
	xx.value = document.body.clientWidth;
	yy.value = document.body.clientHeight;
	
	//xx.value = window.screen.width;
	//yy.value = window.screen.height;
//	alert(xx.value);
//	alert(yy.value);
}

function customFilter(itemLabel, filterValue) {
	if(itemLabel.indexOf(filterValue)>-1){
			return true;
		}
	return false
}


//获取屏幕分辨率
function screensnew() {
	var xx = document.getElementById("signin:invoiceWidth");
	var yy = document.getElementById("signin:invoiceHeight");
//	xx.value = document.body.scrollWidth ;//网页正文全文宽高
//	yy.value = document.body.scrollHeight;
//	alert(xx.value);
//	alert(yy.value);
	xx.value = document.documentElement.clientWidth;//网页可见区域宽
	yy.value = document.documentElement.clientHeight;
//	xx.value = window.screen.width;//屏幕分辨率的宽
//	yy.value = window.screen.height;
//	alert(xx.value);
//	alert(yy.value);
}
// 默认将光标放在第一个控件上，主框架模板调用实现所有页面在打开之后，默认将光标放在第一个输入框
function focusElement() {
	for ( var i = 10; i < document.getElementsByTagName("input").length; i++) {
		if (document.getElementsByTagName("input")[i].type == "text"
				&& document.getElementsByTagName("input")[i].parentNode.nodeName != "SPAN") {
			document.getElementsByTagName("input")[i].focus();
			break;
		}
	}
}
function focusSelected() {
	for ( var i = 10; i < document.getElementsByTagName("input").length; i++) {
		if (document.getElementsByTagName("input")[i].type == "text"
				&& document.getElementsByTagName("input")[i].name
						.indexOf("InputDate") < 0
				&& document.getElementsByTagName("input")[i].name
						.indexOf("Input") > 0
				&& document.getElementsByTagName("input")[i].parentNode.nodeName == "SPAN") {
			document.getElementsByTagName("input")[i].setAttribute("onclick",
					"this.select()");

		}
	}
}
function setStyleFoucus() {
	for ( var i = 0; i < document.getElementsByTagName("input").length; i++) {
		if (document.getElementsByTagName("input")[i].type == "text"
				&& document.getElementsByTagName("input")[i].name
						.indexOf("cellItemTable") > 0) {
			var input = document.getElementsByTagName("input")[i];
			input.focus();
			input.select();
			break;
		}
	}
}
function setInvoiceProdFocus() {
	for ( var i = 0; i < document.getElementsByTagName("textarea").length; i++) {
		if (document.getElementsByTagName("textarea")[i].name.indexOf("prod") > 0) {
			var input = document.getElementsByTagName("textarea")[i];
			input.focus();
			break;
		}
	}
}
function setInvoiceSaveFocus(a) {
	document.getElementById(a).focus();

}
function setInvoiceFocus() {
	for ( var i = 0; i < document.getElementsByTagName("input").length; i++) {
		if (document.getElementsByTagName("input")[i].type == "text"
				&& document.getElementsByTagName("input")[i].name
						.indexOf("prodInput") > 0) {
			var input = document.getElementsByTagName("input")[i];
			input.focus();
			input.select();
			break;
		}
	}
}

function setInvoiceStyleFocus(flag, id) {
	if (flag != true) {
		for ( var i = 0; i < document.getElementsByTagName("input").length; i++) {
			if (document.getElementsByTagName("input")[i].type == "text"
					&& document.getElementsByTagName("input")[i].name
							.indexOf(id) > 0) {
				var input = document.getElementsByTagName("input")[i];
				input.focus();
				input.select();
				break;
			}
		}
	} else {
		for ( var i = 0; i < document.getElementsByTagName("input").length; i++) {
			if (document.getElementsByTagName("input")[i].type == "text"
					&& document.getElementsByTagName("input")[i].name
							.indexOf("styleInput") > 0) {
				var input = document.getElementsByTagName("input")[i];
				input.focus();
				input.select();
				break;
			}
		}
	}

}
function expand(loaded) {
	var closed;
	for ( var i = 0; i < document.getElementsByTagName("input").length; i++) {
		if (document.getElementsByTagName("input")[i].type == "hidden"
				&& document.getElementsByTagName("input")[i].name
						.indexOf("hidden_closed") > 0) {
			closed = document.getElementsByTagName("input")[i];
			break;
		}
	}
	var center = document.getElementById("center");
	var left = document.getElementById("left");
	var leftExpand = document.getElementById("leftExpand");
	if (loaded == "true") {
		if (closed.value == "true") {
			left.style.display = "none";
			center.style.width = "99%";
			leftExpand.src = "javax.faces.resource/images/switch_right.gif.xhtml";
			closed.value = "true";
		} else {
			left.style.display = "block";
			left.style.width = "24%";
			center.style.width = "75%";
			leftExpand.src = "javax.faces.resource/images/switch_left.gif.xhtml";
			closed.value = "false";
		}
	} else {
		if (closed.value == "true") {
			left.style.display = "block";
			left.style.width = "24%";
			center.style.width = "75%";
			leftExpand.src = "javax.faces.resource/images/switch_left.gif.xhtml";
			closed.value = "false";
		} else {
			left.style.display = "none";
			center.style.width = "99%";
			leftExpand.src = "javax.faces.resource/images/switch_right.gif.xhtml";
			closed.value = "true";
		}
	}
}
// 校验器,适用所有浏览器，但是必须用dir属性
function validator(validatorForm) {

	if (validatorForm.elements != null) {
		for ( var i = 0; i < validatorForm.elements.length; i++) {
			var element = validatorForm.elements[i];
			var rtn = validatorDetail(element);
			if (!rtn) {
				return rtn;
			}
		}
	} else {
		return validatorDetail(validatorForm);
	}
	return true;
}
/**
 * 2012-4-6添加方法来验证金苑的登陆、注册页面
 * 
 * @param 带参数为form的非空验证
 *            校验器,适用所有浏览器，但是必须用dir属性，主要在web页面按钮是超链接时使用
 */
function webValidator(forms) {
	if(forms != null)
	{		
		validatorForm=document.getElementById(forms);
	}
	
	var returnBlooen=true;
	if (validatorForm.elements != null) {
		for (var i = 0; i < validatorForm.elements.length; i++) {
			var element = validatorForm.elements[i];
			var rtn = validatorDetail(element);
			if (!rtn) {
				return rtn;
			}
		}
	} else {
		returnBlooen= validatorDetail(validatorForm);
	}
	if(true==returnBlooen){
		if(forms != 'modifyPwdForm')
		{	
			alert("\u64CD\u4F5C\u6210\u529F");
		}	
	}
	return returnBlooen;
}
/**
 * //校验器,适用所有浏览器，但是必须用dir属性，主要在web页面按钮是超链接时使用 function webValidator() {
 * validatorForm=document.getElementById('webForm'); var returnBlooen=true; if
 * (validatorForm.elements != null) { for (var i = 0; i <
 * validatorForm.elements.length; i++) { var element =
 * validatorForm.elements[i]; var rtn = validatorDetail(element); if (!rtn) {
 * return rtn; } } } else { returnBlooen= validatorDetail(validatorForm); }
 * if(true==returnBlooen){ alert("\u64CD\u4F5C\u6210\u529F"); } return
 * returnBlooen; }
 */

/**
 * 2012-4-6添加方法来验证金苑的登陆、注册页面
 * 
 * @param 带参数为form的非空验证
 */
function webValidatorNotPrompt(form) {
	if(form != null)
	{		
		validatorForm=document.getElementById(form);
	}
	
	var returnBlooen=true;
	if (validatorForm.elements != null) {
		for (var i = 0; i < validatorForm.elements.length; i++) {
			var element = validatorForm.elements[i];
			var rtn = validatorDetail(element);
			if (!rtn) {
				return rtn;
			}
		}
		
		// 2012-4-16 增加对密码和验证密码的等值验证
		if(form == 'registerForm')
		{
			if(document.getElementById("registerForm:password") != null && document.getElementById("registerForm:password1") != null)
			{			
				var password = document.getElementById("registerForm:password");
				var password1 = document.getElementById("registerForm:password1");
				
				if(password.value != password1.value)
				{
					alert('密码与验证密码有误,请重新输入！');
					return false;
				}
			}	
		}
	} else {
		returnBlooen= validatorDetail(validatorForm);
	}
	return returnBlooen;
}

/**
 * //校验器,适用所有浏览器，但是必须用dir属性，主要在web页面按钮是超链接时使用 function webValidatorNotPrompt() {
 * validatorForm=document.getElementById('webForm'); var returnBlooen=true; if
 * (validatorForm.elements != null) { for (var i = 0; i <
 * validatorForm.elements.length; i++) { var element =
 * validatorForm.elements[i]; var rtn = validatorDetail(element); if (!rtn) {
 * return rtn; } } } else { returnBlooen= validatorDetail(validatorForm); }
 * return returnBlooen; }
 */
function validatorDetail(element) {
	if (null != element.getAttribute("dir")) {
		var validator = element.getAttribute("dir");
		if (validator != null && validator != "") {
			var validators = splitType(validator);
			for (var j = 0; j < validators.length; j++) {
				var styles = validators[j];
				var validatorType = findType(styles);
				var params = findParam(styles);
				var rtn = validatorChech(element, validatorType, params);
				if (!rtn) {
					return rtn;
				}
			}
		}
	} else {
		if (null != element.getAttribute("size")) {
			var validator = element.getAttribute("size");
			if (validator != null && validator != "") {
				var validators = splitType(validator);
				for (var j = 0; j < validators.length; j++) {
					var styles = validators[j];
					if (styles.indexOf("check") != -1) {
						var validatorType = findType(styles);
						var params = findParam(styles);
						var rtn = validatorChech(element, validatorType, params);
						if (!rtn) {
							return rtn;
						}
					}
				}
			}
		}
	}
	return true;
}


// 校验器,适用所有浏览器，但是必须用dir属性
function validator1(validatorForm) {
	for (var i = 0; i < validatorForm.elements.length; i++) {
		var element = validatorForm.elements[i];
		if (null != element.getAttribute("dir")) {
			var validator = element.getAttribute("dir");
			if (validator != null && validator != "") {
				var validators = splitType(validator);
				for (var j = 0; j < validators.length; j++) {
					var styles = validators[j];
					var validatorType = findType(styles);
					var params = findParam(styles);
					var rtn = validatorChech(element, validatorType, params);
					if (!rtn) {
						return rtn;
					}
				}
			}
		}
	}
	return true;
}


// 校验器,适用chrom
function validatorChrom(validatorForm) {
	for (var i = 0; i < validatorForm.elements.length; i++) {
		var element = validatorForm.elements[i];
		if (null != element.getAttribute("style")) {
			var validator = element.getAttribute("style");
			var index = validator.indexOf("validator");
			if (validator != null && validator != "" && index > -1) {
					// 去掉validator前面的字符
				validator = validator.substring(index, validator.length);
					// 截取validator到;之间的字符
				validator = validator.substring(index, validator.indexOf(";"));
					// 去掉validator:字符
				validator = validator.substring(validator.indexOf(":") + 1, validator.length);
				var validators = splitType(validator);
				for (var j = 0; j < validators.length; j++) {
					var styles = validators[j];
					var validatorType = findType(styles);
					var params = findParam(styles);
					var rtn = validatorChech(element, validatorType, params);
					if (!rtn) {
						return rtn;
					}
				}
			}
		}
	}
	return true;
}


// 校验器 适用IE
function validatorIE(validatorForm) {
	var length = validatorForm.all.length;
	for (var i = 0; i < length; i++) {
		var elem = validatorForm.all(i);
		var styles = elem.style;
		if (styles.validator != null && styles.validator != "") {
			var validators = splitType(styles.validator);
			for (var j = 0; j < validators.length; j++) {
				styles = validators[j];
				var validatorType = findType(styles);
				var params = findParam(styles);
				var rtn = validatorChech(validatorForm.all(i), validatorType, params);
				if (!rtn) {
					return rtn;
				}
			}
		}
	}
	return true;
}


// 拆分校验类型
function splitType(validator) {
	var validators = validator.split("|");
	return validators;
}
function validatorChech(validatorObject, validatorType, params) {

	switch (validatorType) {
	  case "checkNotNull":
		return checkNotNull(validatorObject, params[0]);
		break;
	  case "checkNotNull1":
		return checkNotNull1(validatorObject, params[0]);
		break;
	  case "checkemail":
		return checkemail(validatorObject, params[0]);
		break;
	  case "checkemail1":
		return checkemail1(validatorObject, params[0]);
		break;
	  case "checkinteger":
		return checkinteger(validatorObject, params[0]);
		break;
	  case "checkintegerZero":
		return checkintegerZero(validatorObject, params[0]);
		break;
	  case "checkinteger1":
		return checkinteger1(validatorObject, params[0]);
		break;
	  case "checkinteger2":
		return checkinteger2(validatorObject, params[0]);
		break;
	  case "checkintegermobile":
		return checkintegermobile(validatorObject, params[0]);
		break;
	  case "checkintegerphone":
		return checkintegerphone(validatorObject, params[0]);
		break;
	  case "checkMo":
		return checkMo(validatorObject, params[0]);
		break;
	  case "checkfloat":
		return checkfloat(validatorObject, params[0]);
		break;
	  case "checkfloat1":
		return checkfloat1(validatorObject, params[0]);
		break;
	  case "intRange":
		return intRange(validatorObject, params[0], params[1], params[2]);
		break;
	  case "floatRange":
		return floatRange(validatorObject, params[0], params[1], params[2]);
		break;
	  case "minlength":
		return minlength(validatorObject, params[0], params[1]);
		break;
	  case "maxlength":
		return maxlength(validatorObject, params[0], params[1]);
		break;
	  case "checkPlus":
		return checkPlus(validatorObject, params[0]);
		break;
	  case "checkNumber":
		return checkNumber(validatorObject, params[0]);
		break;
	  case "checkChinese":
		return checkChinese(validatorObject, params[0]);
		break;
	  case "testChinese":
		return testChinese(validatorObject, params[0]);
		break;
	  case "iponly":
		return iponly(validatorObject, params[0]);
		break;
	  case "trimField":
		return trimField(validatorObject);
		break;
	  case "IsTime":
		return IsTime(validatorObject, params[0], params[1]);
		break;
	  case "isDate":
		return isDate(validatorObject, params[0]);
		break;
	  case "checkLength":
		return checkLength(validatorObject, params[0]);
		break;
	  case "checkPay":
		return checkPay(validatorObject, params[0]);
		break;
	  case "checkEnough":
		return checkEnough(validatorObject, params[0]);
		break;
	  case "checkGeZero":
		return checkGeZero(validatorObject, params[0]);
		break;	
	  case "checkTotalMoney":
		return checkTotalMoney(validatorObject, params[0]);
	  case "checkAmout":
		return checkAmout(validatorObject, params[0]);
	  case "checkListBox":
		return checkListBox(validatorObject, params[0]);
	  case "checkdiscount":
		return checkdiscount(validatorObject, params[0]);
		break;
	  case "checkNegativeDiscount":
		return  checkNegativeDiscount(validatorObject, params[0]);
		break;
	  case "checkPositiveFactor":
		return checkPositiveFactor(validatorObject, params[0]);
		break;
	  case "checkLeastOneNotNull":
			return checkLeastOneNotNull(validatorObject, params[0],params[1]);
			break;
	  default:
		return true;
	}
}

function checkLeastOneNotNull(object,param1,param2){
	var val1 = document.getElementById(param1).value;
	var val2 = document.getElementById(param2).value;
	if((val1==null&&val2==null)||(trim(val1).length<=0 && trim(val2).length<=0)){
		alert("\u7535\u8BDD\u53F7\u7801\u3001\u624B\u673A\u53F7\u9009\u586B\u4E00\u9879,\u5176\u4F59\u5747\u4E3A\u5FC5\u586B\u9879");
		return false;
	}
		return true;
}

/**
 * 判断用户保存销售单时是否有销售明细：根据总金额是否为0
 * 
 * @param object
 *            传入的表单对象
 * @param desc
 *            描述
 * @author linjy
 */
function checkTotalMoney(object, desc) {
	var strValue = trim(object.value);
	if ((strValue.length == 1 && strValue == 0) || strValue.length == 0 || strValue == 0) {
		alert("\u8bf7\u8f93\u5165\u5546\u54c1\u660e\u7ec6\u3002");
		return false;
	}
	return true;
}
/**
 * 判断实付是否为正整数
 * 
 * @param object
 *            传入的表单对象
 * @param desc
 *            描述
 * @author linjy
 */
function checkPay(object, desc) {
	var rule = /^[0-9]\.?[0-9]*\.?[0-9]*[Ee]?\+?[0-9]*$/;// 正则表达式在/与/之间
	if (!rule.test(object.value)) {
		alert("\u8bf7\u8f93\u5165" + desc + "\u91d1\u989d");
		object.focus();
		return false;
	}
	return true;
}
/**
 * 判断找零是否为正整数或者是0
 * 
 * @param object
 *            传入的表单对象
 * @param desc
 *            描述
 * @author linjy
 */
function checkEnough(object, desc) {
	var strValue = trim(object.value);
	var rule = /^[0-9]\.?[0-9]*\.?[0-9]*[Ee]?\+?[0-9]*$/;// 正则表达式在/与/之间
	if (!rule.test(strValue)) {
		alert("\u5b9e\u4ed8\u91d1\u989d\u5c0f\u4e8e\u603b\u91d1\u989d\uff0c\u8bf7\u68c0\u67e5");
		return false;
	}
	return true;
}
/**
 * 是否大于0
 * 
 * @param object
 *            传入的表单对象
 * @param desc
 *            描述
 * @authorxbs
 */
function checkGeZero(object, desc){
	var strValue = trim(object.value);
	var rule = /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$/;// 正则表达式在/与/之间
	if (!rule.test(strValue)) {
		alert(desc + "\u4E0D\u80FD<\=0");
		object.select();
		object.focus();
		return false;
	}
	return true;
}
/**
 * 判断总数量为0,提示要添加商品
 * 
 * @param object
 *            传入的表单对象
 * @param desc
 *            描述
 * @author phg
 */
function checkAmout(object, desc) {
	var strValue = trim(object.value);
	if (strValue == 0) {
		alert("\u8bf7\u8f93\u5165\u6761\u5f62\u7801");
		return false;
	}
	return true;
}
/*
 * add by duanpeng ip验证
 */
function iponly(object, Desc) {
	var ipDomainPat = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])(\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])){3}$/;
	var address = trim(object.value);
	if (!ipDomainPat.exec(address)) {
		alert(Desc + "\u8f93\u5165\u4e0d\u6b63\u786e\uff01");
		object.focus();
		object.select();
		return false;
	}
	return true;
}

// 获得校验类型
function findType(validator) {
	validatorType = validator.substring(0, validator.indexOf("("));
	return validatorType;
}
// 获得校验类型
function findParam(validator) {
	var param = validator.substring(validator.indexOf("(") + 1, validator.indexOf(")"));
	var params = param.split(",");
	return params;
}
/**
 * 去字符串空格
 * 
 * @param s
 *            传入的字符串参数
 * @author caiyuan
 */
function trim(s) {
	return s.replace(/(^\s*)|(\s*$)/g, "");
}
/**
 * 去输入字符串空格
 * 
 * @param field
 *            表单输入框对象
 * @author caiyuan
 */
function trimField(field) {
	field.value = field.value.replace(/(^\s*)|(\s*$)/g, "");
	return true;
}
/**
 * 判断只能是数字
 * 
 * @param object
 *            传入的表单对象
 * @param desc
 *            描述
 * @author yejianfeng
 */
function checkNumber(object, desc) {
	var rule = /^[0-9]*[1-9][0-9]*$/;// 正则表达式在/与/之间
	if (!rule.test(object.value)) {
		alert(desc + "\u53ea\u80fd\u8f93\u5165\u6570\u5b57");
		object.focus();
		object.select();
		return false;
	}
	return true;
}
function checkNumber(object, desc) {
	var rule = /^[0-9]*[1-9][0-9]*$/;// 正则表达式在/与/之间
	if (!rule.test(object.value)) {
		alert(desc + "\u53ea\u80fd\u8f93\u5165\u6570\u5b57");
		object.focus();
		object.select();
		return false;
	}
	return true;
}
/**
 * 判断正整数，可以为空
 * 
 * @param object
 *            传入的表单对象
 * @param desc
 *            描述
 * @author caiyuan
 */
function checkPlus(object, desc) {
	var strValue = trim(object.value);
	if (strValue.length == 0) {
		return true;
	}
	var rule = /^[1-9][0-9]*$/;// 正则表达式在/与/之间
	if (!rule.test(object.value)) {
		alert(desc + "\u5fc5\u987b\u662f\u6574\u6570\u4e14\u5927\u4e8e0\uff01");
		object.focus();
		object.select();
		return false;
	}
	return true;
}
/**
 * 判断只能输入中文，可以为空
 * 
 * @param object
 *            传入的表单对象
 * @param desc
 *            描述
 * @author caiyuan
 */
function checkChinese(object, desc) {
	var strValue = trim(object.value);
	if (strValue.length == 0) {
		return true;
	}
	var rule = /^[\u4e00-\u9fa5]*$/;// 正则表达式在/与/之间
	if (!rule.test(object.value)) {
		alert(desc + "\u53ea\u80fd\u8f93\u5165\u4e2d\u6587");
		object.focus();
		object.select();
		return false;
	}
	return true;
}
/**
 * checkListBox,选择判断
 * 
 * Object目标对象,Desc中文的描述.
 * 
 * @author penghg
 */
function checkListBox(Object, Desc) {
	var strValue = Object.value.replace(/^\s*/, "").replace(/\s*$/, "");
	if (strValue == "\u8bf7\u9009\u62e9" || strValue == "") {
		alert(Desc + "\u4e0d\u80fd\u4e3a\u7a7a\uff0c\u8bf7\u9009\u62e9\uff01");
		Object.focus();
		return false;
	} else {
		return true;
	}
}
/**
 * checkNotNull函数校验此框输入的数据不为空,返回的值为真和假,true和false;
 * 
 * Object目标对象,Desc中文的描述.
 */
function checkNotNull(Object, Desc) {
	var strValue = trim(Object.value);
	if (strValue.length == 0) {
		alert(Desc + "\u4e0d\u80fd\u4e3a\u7a7a\uff0c\u8bf7\u4fee\u6539\uff01");
		Object.focus();
		return false;
	} else {
		return true;
	}
}
/**
 * 多个标签页时的判断,去掉光标定位 checkNotNull函数校验此框输入的数据不为空,返回的值为真和假,true和false;
 * 
 * Object目标对象,Desc中文的描述.
 */
function checkNotNull1(Object, Desc) {
	var strValue = trim(Object.value);
	if (strValue.length == 0) {
		alert(Desc + "\u4e0d\u80fd\u4e3a\u7a7a\uff0c\u8bf7\u4fee\u6539\uff01");
		return false;
	} else {
		return true;
	}
}
/**
 * checkemail 函数校验此框输入的是否为email型的数据,返回的值为真和假,true和false;
 * 
 * object目标对象,desc中文的描述.
 */
function checkemail(object, desc) {
	var pattern = /[a-za-z0-9_.]{1,}@[a-za-z0-9_]{1,}.[a-za-z0-9_]{1,}/;
	var strvalue = object.value;
	if (strvalue.length == 0) {
		return true;
	}
	if (strvalue.match(pattern) == null) {
		alert(desc + "\u5fc5\u987b\u4e3a\u5408\u6cd5\u7684email\uff0c\u8bf7\u4fee\u6539\uff01");
		object.focus();
		return false;
	} else {
		return true;
	}
}
/**
 * 标签页验证使用,缺少光标定位 checkemail 函数校验此框输入的是否为email型的数据,返回的值为真和假,true和false;
 * 
 * object目标对象,desc中文的描述.
 */
function checkemail1(object, desc) {
	var pattern = /[a-za-z0-9_.]{1,}@[a-za-z0-9_]{1,}.[a-za-z0-9_]{1,}/;
	var strvalue = object.value;
	if (strvalue.length == 0) {
		return true;
	}
	if (strvalue.match(pattern) == null) {
		alert(desc + "\u5fc5\u987b\u4e3a\u5408\u6cd5\u7684email\uff0c\u8bf7\u4fee\u6539\uff01");
		return false;
	} else {
		return true;
	}
}
/**
 * checkinteger 函数校验此框输入的是否为integer型的数据,返回的值为真和假,true和false;
 * 
 * object目标对象,desc中文的描述. modify by xbs 2012-04-16
 */
function checkinteger(object, desc) {
	var argvalue = trim(object.value);
	var checked=checkintegerExtend(argvalue);
	if(!checked){
		alert(desc + "\u5fc5\u987b\u4e3a\u4e00\u4e2a\u6574\u6570\uff0c\u8bf7\u4fee\u6539\uff01");
		object.focus();
		return false;
	}
		return true;
/*
 * var validChars = "0123456789"; var startFrom = 0; if (argvalue.substring(0,
 * 2) == "0x") { validChars = "0123456789abcdefABCDEF"; startFrom = 2; } else {
 * if (argvalue.charAt(0) == "0") { validChars = "0123456789"; startFrom = 1; }
 * else { if (argvalue.charAt(0) == "-") { startFrom = 1; } } } for (var n =
 * startFrom; n < argvalue.length; n++) { if
 * (validChars.indexOf(argvalue.substring(n, n + 1)) == -1) { alert(desc +
 * "\u5fc5\u987b\u4e3a\u4e00\u4e2a\u6574\u6570\uff0c\u8bf7\u4fee\u6539\uff01");
 * object.focus(); return false; } } return true;
 */
}
/**
 * 抽出验证方法方便其他地方调用 add by xbs 2012-04-16
 */
function checkintegerExtend(argvalue){
	var validChars = "0123456789";
	var startFrom = 0;
	if (argvalue.substring(0, 2) == "0x") {
		validChars = "0123456789abcdefABCDEF";
		startFrom = 2;
	} else {
		if (argvalue.charAt(0) == "0") {
			validChars = "0123456789";
			startFrom = 1;
		} else {
			if (argvalue.charAt(0) == "-") {
				startFrom = 1;
			}
		}
	}
	for (var n = startFrom; n < argvalue.length; n++) {
		if (validChars.indexOf(argvalue.substring(n, n + 1)) == -1) {
		// alert(desc +
		// "\u5fc5\u987b\u4e3a\u4e00\u4e2a\u6574\u6570\uff0c\u8bf7\u4fee\u6539\uff01");
		// object.focus();
			return false;
		}
	}
	return true;
}
/**
 * 标签页验证使用,缺少光标定位 checkinteger 函数校验此框输入的是否为integer型的数据,返回的值为真和假,true和false;
 * 
 * object目标对象,desc中文的描述.
 */
function checkinteger1(object, desc) {
	var argvalue = trim(object.value);
	var validChars = "0123456789";
	var startFrom = 0;
	if (argvalue.substring(0, 2) == "0x") {
		validChars = "0123456789abcdefABCDEF";
		startFrom = 2;
	} else {
		if (argvalue.charAt(0) == "0") {
			validChars = "0123456789";
			startFrom = 1;
		} else {
			if (argvalue.charAt(0) == "-") {
				startFrom = 1;
			}
		}
	}
	for (var n = startFrom; n < argvalue.length; n++) {
		if (validChars.indexOf(argvalue.substring(n, n + 1)) == -1) {
			alert(desc + "\u5fc5\u987b\u4e3a\u4e00\u4e2a\u6574\u6570\uff0c\u8bf7\u4fee\u6539\uff01");
			return false;
		}
	}
	return true;
}
/**
 * 银行账号提示 checkinteger2 函数校验此框输入的是否为integer型的数据,返回的值为真和假,true和false;
 * 
 * object目标对象,desc中文的描述.
 */
function checkinteger2(object, desc) {
	var argvalue = trim(object.value);
	var validChars = "0123456789";
	var startFrom = 0;
	if (argvalue.substring(0, 2) == "0x") {
		validChars = "0123456789abcdefABCDEF";
		startFrom = 2;
	} else {
		if (argvalue.charAt(0) == "0") {
			validChars = "0123456789";
			startFrom = 1;
		} else {
			if (argvalue.charAt(0) == "-") {
				startFrom = 1;
			}
		}
	}
	for (var n = startFrom; n < argvalue.length; n++) {
		if (validChars.indexOf(argvalue.substring(n, n + 1)) == -1) {
			alert(desc);
			object.focus();
			return false;
		}
	}
	return true;
}
/**
 * 标签页验证使用,缺少光标定位,手机电话验证 checkinteger
 * 函数校验此框输入的是否为integer型的数据,返回的值为真和假,true和false;
 * 
 * object目标对象,desc中文的描述.
 */
function checkintegermobile(object, desc) {
	var argvalue = trim(object.value);
	var validChars = "0123456789";
	var startFrom = 0;
	if (argvalue.substring(0, 2) == "0x") {
		validChars = "0123456789abcdefABCDEF";
		startFrom = 2;
	} else {
		if (argvalue.charAt(0) == "0") {
			validChars = "0123456789";
			startFrom = 1;
		} else {
			if (argvalue.charAt(0) == "-") {
				startFrom = 1;
			}
		}
	}
	for (var n = startFrom; n < argvalue.length; n++) {
		if (validChars.indexOf(argvalue.substring(n, n + 1)) == -1) {
			alert(desc);
			return false;
		}
	}
	
	if(argvalue.length != 0)
	{	if(argvalue.length != 11 && argvalue.length != 12)
		{
			alert("手机长度有误");
			return false;
		}
	}
	return true;
}
/*
 * 电话号码
 */
function checkintegerphone(object, desc) {
	if (object.value == "") {
		return true;
	}
// var p1 = /^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
	var p1 = /^\d{3,4}-\d{7,8}(-\d{3,4})?$/;
	var me = false;
	if (p1.test(object.value)) {
		me = true;
	}
	if (!me) {
		alert(desc);
		object.focus();
		return false;
	}
	return true;
}
function checkMo(object, desc) {
	if (object.value == "") {
		return true;
	}
	if(navigator.appName == "Microsoft Internet Explorer") { 
		if (object.value.lastIndexOf("\r\n") != object.value.length - 2) {
			alert(desc);
			object.focus();
			return false;
		}
		var p1 = /^(1\d{10})$/;
		var mo = object.value.split("\r\n");
	}else{
		if (object.value.lastIndexOf("\n") != object.value.length - 1) {
			alert(desc);
			object.focus();
			return false;
		}
		var p1 = /^(1\d{10})$/;
		var mo = object.value.split("\n");
	}
	
	for (var i = 0; i < mo.length - 1; i++) {
		var me = false;
		if (p1.test(mo[i])) {
			me = true;
		}
		if (!me) {
			alert(desc);
			object.focus();
			return false;
		}
	}
	return true;
}
function checkMo1(object, desc) {
	if (object.value == "") {
		return true;
	}
	var p1 = /^(1\d{10});$/;
	var me = false;
	if (p1.test(object.value)) {
		me = true;
	}
	if (!me) {
		alert(desc);
		object.focus();
		return false;
	}
	return true;
}

/**
 * 折扣 0=<discount,判断折扣输入的数量是否为>=0,若<0,则返回false check float
 * 函数校验此框输入的是否为float型的数据,返回的值为真和假,true和false; object目标对象,desc中文的描述.
 */
function checkNegativeDiscount(object, desc) {
	var argvalue = object.value;
	if ( argvalue < 0) {//
		alert(desc + " \u4E0D\u80FD\u5C0F\u4E8E0\uFF01");
		return false;
	}
                // remove '.' before checking digits
	argvalue = argvalue.split(".");
	argvalue = argvalue.join("");
	var validChars = "0123456789";
	var startFrom = 0;
	if (argvalue.substring(0, 2) == "0x") {
		validChars = "0123456789abcdefABCDEF";
		startFrom = 2;
	} else {
		if (argvalue.charAt(0) == "0") {
			validChars = "0123456789";
			startFrom = 1;
		} else {
			if (argvalue.charAt(0) == "-") {
				startFrom = 1;
			}
		}
	}
	for (var n = startFrom; n < argvalue.length; n++) {
		if (validChars.indexOf(argvalue.substring(n, n + 1)) == -1) {
			alert(desc + "\u5fc5\u987b\u4e3a\u4e00\u4e2a\u6570\u5b57\uff0c\u8bf7\u4fee\u6539\uff01");
			object.focus();
			object.value = "";
			return false;
		}
	}
	return true;
}

/**
 * 折扣 0<x<=1 checkfloat 函数校验此框输入的是否为float型的数据,返回的值为真和假,true和false;
 * 
 * object目标对象,desc中文的描述.
 */
function checkdiscount(object, desc) {
	var argvalue = object.value;
	if ( argvalue > 1 ||argvalue < 0) {//
		alert(desc + "\u5fc5\u987b\u5927\u4e8e0\uff0c\u5c0f\u4e8e\u7b49\u4e8e1");
		return false;
	}
                // remove '.' before checking digits
	argvalue = argvalue.split(".");
	argvalue = argvalue.join("");
	var validChars = "0123456789";
	var startFrom = 0;
	if (argvalue.substring(0, 2) == "0x") {
		validChars = "0123456789abcdefABCDEF";
		startFrom = 2;
	} else {
		if (argvalue.charAt(0) == "0") {
			validChars = "0123456789";
			startFrom = 1;
		} else {
			if (argvalue.charAt(0) == "-") {
				startFrom = 1;
			}
		}
	}
	for (var n = startFrom; n < argvalue.length; n++) {
		if (validChars.indexOf(argvalue.substring(n, n + 1)) == -1) {
			alert(desc + "\u5fc5\u987b\u4e3a\u4e00\u4e2a\u6570\u5b57\uff0c\u8bf7\u4fee\u6539\uff01");
			object.focus();
			object.value = "";
			return false;
		}
	}
	return true;
}
/**
 * checkfloat 函数校验此框输入的是否为float型的数据,返回的值为真和假,true和false;
 * 
 * object目标对象,desc中文的描述.
 */
function checkfloat(object, desc) {
	var argvalue = object.value;
                // remove '.' before checking digits
	argvalue = argvalue.split(".");
	argvalue = argvalue.join("");
	var validChars = "0123456789";
	var startFrom = 0;
	if (argvalue.substring(0, 2) == "0x") {
		validChars = "0123456789abcdefABCDEF";
		startFrom = 2;
	} else {
		if (argvalue.charAt(0) == "0") {
			validChars = "0123456789";
			startFrom = 1;
		} else {
			if (argvalue.charAt(0) == "-") {
				startFrom = 1;
			}
		}
	}
	for (var n = startFrom; n < argvalue.length; n++) {
		if (validChars.indexOf(argvalue.substring(n, n + 1)) == -1) {
			alert(desc + "\u5fc5\u987b\u4e3a\u4e00\u4e2a\u6570\u5b57\uff0c\u8bf7\u4fee\u6539\uff01");
			object.focus();
			object.value = "";
			return false;
		}
	}
	return true;
}
/**
 * 标签页验证使用,缺少光标定位 checkfloat 函数校验此框输入的是否为float型的数据,返回的值为真和假,true和false;
 * 
 * object目标对象,desc中文的描述.
 */
function checkfloat1(object, desc) {
	var argvalue = object.value;
                // remove '.' before checking digits
	argvalue = argvalue.split(".");
	argvalue = argvalue.join("");
	var validChars = "0123456789";
	var startFrom = 0;
	if (argvalue.substring(0, 2) == "0x") {
		validChars = "0123456789abcdefABCDEF";
		startFrom = 2;
	} else {
		if (argvalue.charAt(0) == "0") {
			validChars = "0123456789";
			startFrom = 1;
		} else {
			if (argvalue.charAt(0) == "-") {
				startFrom = 1;
			}
		}
	}
	for (var n = startFrom; n < argvalue.length; n++) {
		if (validChars.indexOf(argvalue.substring(n, n + 1)) == -1) {
			alert(desc + "\u5fc5\u987b\u4e3a\u4e00\u4e2a\u6570\u5b57\uff0c\u8bf7\u4fee\u6539\uff01");
			object.value = "";
			return false;
		}
	}
	return true;
}
/**
 * intRange函数校验此框输入的是否为int型的数据,同时校验是否在值范围内,返回的值为真和假,true和false;
 * 
 * object目标对象,desc中文的描述.iMin最小值，iMax 最大值
 */
function intRange(object, desc, iMin, iMax) {
	var argvalue = object.value;
	var validChars = "0123456789";
	var startFrom = 0;
	if (argvalue.substring(0, 2) == "0x") {
		validChars = "0123456789abcdefABCDEF";
		startFrom = 2;
	} else {
		if (argvalue.charAt(0) == "0") {
			validChars = "0123456789";
			startFrom = 1;
		} else {
			if (argvalue.charAt(0) == "-") {
				startFrom = 1;
			}
		}
	}
	for (var n = startFrom; n < argvalue.length; n++) {
		if (validChars.indexOf(argvalue.substring(n, n + 1)) == -1) {
			alert(desc + "\u5fc5\u987b\u4e3a\u4e00\u4e2a\u6570\u5b57\uff0c\u8bf7\u4fee\u6539\uff01");
			object.focus();
			return false;
		}
	}
	if (!(parseInt(argvalue) >= parseInt(iMin) && parseInt(argvalue) <= parseInt(iMax))) {
		alert(desc + "\u4e0d\u5728" + iMin + "\u548c" + iMax + "\u4e4b\u95f4\uff0c\u8bf7\u4fee\u6539\uff01");
		object.focus();
		return false;
	}
	return true;
}
/**
 * floatRange函数校验此框输入的是否为int型的数据,同时校验是否在值范围内,返回的值为真和假,true和false;
 * 
 * object目标对象,desc中文的描述.iMin最小值，iMax 最大值
 */
function floatRange(object, desc, iMin, iMax) {
	var argvalue = object.value;
                // remove '.' before checking digits
	argvalue = argvalue.split(".");
	argvalue = argvalue.join("");
	var validChars = "0123456789";
	var startFrom = 0;
	if (argvalue.substring(0, 2) == "0x") {
		validChars = "0123456789abcdefABCDEF";
		startFrom = 2;
	} else {
		if (argvalue.charAt(0) == "0") {
			validChars = "0123456789";
			startFrom = 1;
		} else {
			if (argvalue.charAt(0) == "-") {
				startFrom = 1;
			}
		}
	}
	for (var n = startFrom; n < argvalue.length; n++) {
		if (validChars.indexOf(argvalue.substring(n, n + 1)) == -1) {
			alert(desc + "\u5fc5\u987b\u4e3a\u4e00\u4e2a\u6570\u5b57\uff0c\u8bf7\u4fee\u6539\uff01");
			object.focus();
			return false;
		}
	}
	if (!(parseInt(object.value) >= parseInt(iMin) && parseInt(object.value) <= parseInt(iMax))) {
		alert(desc + "??" + iMin + "?" + iMax + "???????");
		object.focus();
		return false;
	}
	return true;
}
/**
 * minlength函数校验此框输入的是否小于设定长度,返回的值为真和假,true和false;
 * 
 * object目标对象,desc中文的描述,length最小长度
 */
function minlength(object, desc, length) {
	var strvalue = object.value;
	var newlength = strvalue.replace(/[^\x00-\xff]/g, "**").length;
	if (newlength > 0 && (newlength < length)) {
		alert(desc + "\u7684\u957f\u5ea6\u4e0d\u80fd\u5c0f\u4e8e" + length + "\uff0c\u8bf7\u4fee\u6539\uff01");
		object.focus();
		return false;
	} else {
		return true;
	}
}
/**
 * maxlength函数校验此框输入的是否超过设定长度,返回的值为真和假,true和false;
 * 
 * object目标对象,desc中文的描述,length最大长度
 */
function maxlength(object, desc, length) {
	var strvalue = object.value;
	var newlength = strvalue.replace(/[^\x00-\xff]/g, "**").length;
	if (newlength > 0 && (newlength > length)) {
		alert(desc + "\u7684\u957f\u5ea6\u4e0d\u80fd\u5927\u4e8e" + length + "\uff0c\u8bf7\u4fee\u6539\uff01");
		object.focus();
		return false;
	} else {
		return true;
	}
}
/**
 * maxlength函数校验此框输入的是否超过设定长度,返回的值为真和假,true和false;
 * 
 * object目标对象,desc中文的描述,length最大长度
 */
function testChinese(object, desc) {
	var strvalue = object.value;
	if ((/[\u0391-\uFFE5]+$/).test(strvalue)) {
		alert(desc + "\u4e0d\u80fd\u4e3a\u4e2d\u6587!");
		object.focus();
		return false;
	} else {
		return true;
	}
}
function IsTime(obj, desc, curtime) {
	var s = obj.value;
	if (s.split(":").length > 2) {
		alert(desc + "\u683c\u5f0f\u4e0d\u6b63\u786e\uff01");
		obj.value = curtime;
		return false;
	} else {
		s = s + ":00";
		var b = /^([01]?\d|2[0-3])(:)([0-5]?\d)\2([0-5]?\d)$/.test(s);
		if (!b) {
			alert(desc + "\u683c\u5f0f\u4e0d\u6b63\u786e\uff01");
			obj.value = curtime;
			return false;
		}
	}
	return true;
}
function isDate(obj, desc) {
	var dateIn = obj.value;
	var result = true;
	if (dateIn == null || trim(dateIn) == "") {
		return true;
	}
	dateIn = trim(dateIn);
	var dateLen = dateIn.length;
	if (dateLen < 8 || dateLen > 10) {
		alert(desc + "\ufeff\u957f\u5ea6\u9519\u8bef,8-10\u4e2a\u5b57\u7b26.");
		return false;
	}
	var pattern1 = /^(\d{4})(-|\/)(\d{1,2})(-|\/)(\d{1,2})$/;
	var pattern2 = /^(\d{8})$/;
	if (pattern1.test(dateIn)) {
		var result = dateIn.match(pattern1);
		var d = new Date(result[1], result[3] - 1, result[5]);
		result = (d.getFullYear() == result[1] && (d.getMonth() + 1) == result[3] && d.getDate() == result[5]);
	} else {
		if (pattern2.test(dateIn)) {
			var d = new Date(dateIn.substr(0, 4), parseInt(dateIn.substr(4, 2)) - 1, dateIn.substr(6, 2));
			result = (d.getFullYear() == dateIn.substr(0, 4) && (d.getMonth() + 1) == dateIn.substr(4, 2) && d.getDate() == dateIn.substr(6, 2));
		} else {
			result = false;
		}
	}
	if (result == false) {
		alert(desc + "\ufeff\u683c\u5f0f\u9519\u8bef,\u5982YYYYMMDD,YYYY-MM-DD,YYYY/MM/DD.");
		obj.value = "";
	}
	return result;
}
function IgnoreSpaces(Str) {
	var ResultStr = "";
	Temp = Str.split(" "); // 双引号之间是个空格；
	for (i = 0; i < Temp.length; i++) {
		ResultStr += Temp[i];
	}
	return ResultStr;
}
function checkintegerZero(object, desc) {
	var argvalue = trim(object.value);
	if (argvalue == 0) {
		alert(desc);
		object.focus();
		return false;
	}
	return true;
}
/**
 * maxlength函数校验此框输入的是否超过设定长度,返回的值为真和假,true和false;
 * 
 * object目标对象,length最大长度
 */
function checkLength(object, maxlength) {
	if (object.value.length > maxlength) {
		alert("\u8d85\u8fc7\u5141\u8bb8\u8f93\u5165\u7684\u957f\u5ea6!");
		return false;
	}
	return true;
}
/**
 * checkinteger 函数校验此框输入的是否为integer型的数据,返回的值为真和假,true和false;
 * 
 * object目标对象,desc中文的描述.
 */
function checkPositiveFactor(object, desc) {
	var argvalue = trim(object.value);
	if (argvalue <= 0) {
		alert(desc + "\u5fc5\u987b\u4e3a\u6b63\u6570\uff01");
		object.focus();
		return false;
	}
	return true;
}
function showJCB(link, event) {
	if (navigator.appName == "Microsoft Internet Explorer") {
		if (window.event.keyCode == 13) {
			window.event.keyCode = 0;
			if (link.value == "") {
				return;
			}
			link.blur();
		}
	} else {
		var keycode = event.keyCode;
		if (keycode == 13) {
			link.blur(event);
			return false;
		}
	}
}
function showStock(link, event) {
	if (navigator.appName == "Microsoft Internet Explorer") {
		if (window.event.keyCode == 13) {
			window.event.keyCode = 0;
			if (link.value == "") {
				return;
			}
			link.blur();
		}
	} else {
		var keycode = event.keyCode;
		if (keycode == 13) {
			link.blur(event);
			return false;
		}
	}
}
function showRetJCB(link, event) {
	if (navigator.appName == "Microsoft Internet Explorer") {
		if (window.event.keyCode == 13) {
			window.event.keyCode = 0;
			if (link.value == "") {
				return;
			}
			link.onselectitem();
		}
	} else {
		var keycode = event.keyCode;
		if (keycode == 13) {
			link.onselectitem(event);
			return false;
		}
	}
}
// 转换大写
function A(link) {
	link.value = link.value.toUpperCase();
}
// 单据交叉表数量录入 事件捕获
function getAllcateStyle(link,event){
  	if(navigator.appName == "Microsoft Internet Explorer") { 
  		var keycode = window.event.keyCode; 
		if(keyCode==13){
		   keyCode=0;
		   link.ondblclick();
		}else if(keycode==38){
		 	keyCode=0;
			prevRow();			
		} else if(keycode==40){
		 	keyCode=0;
			nextRow();
		}
	}else{ 
	
		var keycode = event.keyCode; 
		if(keycode==13){
			link.ondblclick(event); 
			return false;
		}else if(keycode==38){
			prevRow();
			return false;
		} else if(keycode==40){
			nextRow();
			return false;
		}
	} 	
}
// 单据交叉表数量录入 事件捕获
function getStyle(link,event){
  	if(navigator.appName == "Microsoft Internet Explorer") { 
		if(window.event.keyCode==13){
		   window.event.keyCode=0;
		   link.ondblclick();
		}
	}else{ 	
		var keycode = event.keyCode; 
		if(keycode==13){
			link.ondblclick(event); 
			return false;
		}
	} 	
}

// 值改变，并失去焦点，刚重新渲染。
/*
 * function getChangeValue(link,event){ var m=0; var name=link.name; for (var i =
 * 0; i < document.getElementsByTagName("input").length; i++) { if
 * (document.getElementsByTagName("input")[i].type == "text" &&
 * (document.getElementsByTagName("input")[i].name.indexOf("cellItemTable")>0||document.getElementsByTagName("input")[i].name.indexOf("itemtable")>0) ) {
 * if(document.getElementsByTagName("input")[i].value !=
 * document.getElementsByTagName("input")[i].defaultValue){ m=m+1; } } }
 * if(name.indexOf("cellItemTable")>0){
 * setTimeout(function(){if((document.activeElement.id.indexOf("cellItemTable")
 * <=0) && m > 0){link.ondblclick(event); }}, 10); }else{ if(m>0){
 * link.ondblclick(event); }
 *  } }
 */


// 实时库存查询 添加款式
function addEnterStyle(link, event) {
	if (navigator.appName == "Microsoft Internet Explorer") {
		if (window.event.keyCode == 13) {
			window.event.keyCode = 0;
			if (link.value == "") {
				return;
			}
			addStyle();
		}else{
			return;
		}
	} else {
		var keycode = event.keyCode;
		
		if (keycode == 13) {
			addStyle();
			return false;
		}else{
		return false;
		}
	}
}
// 事件捕获 款式添加
function selectOutStyle(link,event){
    	if(navigator.appName == "Microsoft Internet Explorer") { 
		if(window.event.keyCode==13){
		   window.event.keyCode=0;
 		   link.ondblclick();
		}
	}else{ 	
		var keycode = event.keyCode; 
		if(keycode==13){
 			link.ondblclick(event); 
			return false;
		}
	} 	
}
// 出库单 快速输入 事件捕获 带更新扫描件数
function stockOutStyle(link,event){
  	if(navigator.appName == "Microsoft Internet Explorer") { 
		if(window.event.keyCode==13){
		   window.event.keyCode=0;
		   prodCount();
		   link.ondblclick();
		}
	}else{ 	
		var keycode = event.keyCode; 
		if(keycode==13){
			prodCount();
			link.ondblclick(event); 
			return false;
		}
	} 	
}

// 出库单扫描件数
function prodCount(){
 	for (var i = 0; i < document.getElementsByTagName("textarea").length; i++) {
		if (document.getElementsByTagName("textarea")[i].name.indexOf("prod") > 0) {
			
			var input = document.getElementsByTagName("textarea")[i];			
			var str = input.value;
			var array=str.split('\n');
			var count=0;
			for (i=0;i<array.length ;i++ ){   
	      		 if(array[i]!='')
	      		 count++;
   			}    			
			for (var i = 0; i < document.getElementsByTagName("span").length; i++) {
				if (document.getElementsByTagName("span")[i].id.indexOf("prodCount") > 0) {
					var input = document.getElementsByTagName("span")[i];
				    input.innerHTML=count;
					break;
				}
			}
			break;
		}
	}
}

function countTel(){
		var count=countLine('telInput');
			count=count+countLine('tempTelInput');
		for (var i = 0; i < document.getElementsByTagName("span").length; i++) {
				if (document.getElementsByTagName("span")[i].id.indexOf("prodCount") > 0) {
					var input = document.getElementsByTagName("span")[i];
				    input.innerHTML=count;
					break;
				}
			}

}

// 统计行数 
function countLine(id){
var count=0;
 	for (var i = 0; i < document.getElementsByTagName("textarea").length; i++) {
		if (document.getElementsByTagName("textarea")[i].name.indexOf(id) > 0) {
			
			var input = document.getElementsByTagName("textarea")[i];			
			var str = input.value;
			var array=str.split('\n');
			var count=0;
			for (i=0;i<array.length ;i++ ){   
	      		 if(array[i]!='')
	      		 count++;
   			}    			
   			return count;
		}
	}
}


// 屏蔽主窗体text的回车事件，其他控件的不屏蔽（兼容IE srcElement\fireFox target）
function mainFormKeyPress(event){
 if (event.keyCode == 13) if ((event.target?event.target:event.srcElement).type =='text') return false;
}


// 点击查询表任意行时，键盘上下键将不再控制页面的上下滚动。
function stoproll(){
         $(document).keydown(function(event){
            switch(event.keyCode) {
                case (38):
                return false
                break;
                case (40):
                return false
                break;
            }
         }
        );
}
// 鼠标点击任意一行时，全部行样式恢复，键盘上下键控制当前行的样式为font-weight='bold';
function changeStyle(col){
	var trcount=jQuery(col).parent().find('tr').size()-1;
	var a=col.id; 
    var b=a.charAt(a.length - 1);
    stoproll();
    jQuery(col).parent().find('tr').removeClass('highlight-row');
	jQuery(col).addClass('highlight-row');
    document.onkeydown=function(e){
         var keyCode;
         if(window.event){
        	 keyCode=event.keyCode;
         }
         else{
        	 keyCode=e.which;   
         }
         if (keyCode==38) {   
        	if(b>0){
            		b=b-1;
            	}
        	 jQuery(col).parent().find('tr').removeClass('highlight-row');
        	  jQuery(col).parent().find("tr:eq("+b+")").addClass('highlight-row');
            	
         }
         if (keyCode==40) {
            if(b<trcount){
            	b=parseInt(b)+1;
            }
        	 jQuery(col).parent().find('tr').removeClass('highlight-row');
        	  jQuery(col).parent().find("tr:eq("+b+")").addClass('highlight-row');
         }
          
     }
}

//鼠标点击任意一行时，全部行样式恢复，键盘上下键控制当前行的样式为font-weight='bold';
function changeStyleNew(col){	
	var trcount=jQuery(col).parent().find('tr').size()-1;
	var a=col.id; 	
    var b=a.charAt(a.length - 2);   
    if(b.indexOf(':')>=0){
    	b=a.charAt(a.length - 1);    	
    }else{
    	b=a.charAt(a.length - 2)+a.charAt(a.length - 1) ;    	
    }
    stoproll();
    jQuery(col).parent().find('tr').removeClass('highlight-row');
	jQuery(col).addClass('highlight-row');
	var ids = col.id.split(":");
	var inputidd =ids[0]+':'+ids[1]+':'+ids[2]+':'+b+':_input-0';  
	if(document.activeElement.id.indexOf(':_input-')<0){
		 if(document.getElementById(inputidd)){
			 document.getElementById(inputidd).focus();
				document.getElementById(inputidd).select();
		 }		
	}	
    document.onkeydown=function(e){
         var keyCode;
         var idss = document.activeElement.id.split('-'); 
         if(window.event){
         keyCode=event.keyCode;
         }
         else{
          keyCode=e.which;   
         }
         if (keyCode==38) {   
        	if(b>0){
            		b=b-1;
            	}
        	 jQuery(col).parent().find('tr').removeClass('highlight-row');
        	  jQuery(col).parent().find("tr:eq("+b+")").addClass('highlight-row');    	
        	  var inputid =ids[0]+':'+ids[1]+':'+ids[2]+':'+b+':_input-'+idss[1];  
        	  if(document.getElementById(inputid)){
        		  document.getElementById(inputid).focus();        		
            	  document.getElementById(inputid).select();
        	  }        	 
         }
         if (keyCode==40) {
            if(b<trcount){
            	b=parseInt(b)+1;
            }
        	 jQuery(col).parent().find('tr').removeClass('highlight-row');
        	  jQuery(col).parent().find("tr:eq("+b+")").addClass('highlight-row');        	 
        	  var inputid =ids[0]+':'+ids[1]+':'+ids[2]+':'+b+':_input-'+idss[1]; 
        	  if(document.getElementById(inputid)){
        		  document.getElementById(inputid).focus();
            	  document.getElementById(inputid).select();
        	  }        	 
         }
         if (keyCode==37) {        	
        	 if(parseInt(idss[1])>0){
        		 var inputid = idss[0]+'-'+(parseInt(idss[1])-1);
        		 if(document.getElementById(inputid)){
        			 document.getElementById(inputid).focus();   
        			 setTimeout(function(){
        				 document.getElementById(inputid).select();
        			 },10);
        		 }        		
        	 }
          } 
         if (keyCode==39) {         	 
        	 var inputid = idss[0]+'-'+(parseInt(idss[1])+1);        		
        	 if(document.getElementById(inputid)){
        		 document.getElementById(inputid).focus();   
    			 setTimeout(function(){
    				 document.getElementById(inputid).select();
    			 },10);
        	 }
          } 
     }
}

function fixedKeyevent(){	
	 document.onkeydown=function(e){
		 return false;
	 }
	         
}
// 去除复制粘贴过来前面的空格。
function ignorespaces(inputvalue){
	return inputvalue.replace( /^\s*/, "");
}
function setFocus1(){
	var m=document.getElementsByClassName('jfbutton');
	var n;
	var k;
	for(i=0;i<m.length;i++){
  		n=m[i].id.split(":");
		k=n.length;
  		if(n[k-1]=='firstInput'){
			m[i].focus();
		}
	}
}

//根据价格计算金额=价格*数量
function calcMoney(price) {
if(price.id.indexOf("columnPrice")>0){
	var priceid = price.id.replace(/columnPrice/, "");
	var array = $('.columnAmount').toArray();//获取classStyle=columnAmount的元素
	var money;
	//获取当前价格行的数量值并相乘
	for ( var i = 0; i < array.length; i++) {
		var amountid = array[i].id.replace(/columnAmount/, "");
		if (amountid == priceid)
			money=array[i].value * price.value;
	}
	//获取当前价格行的金额行并赋值
	array = $('.columnMoney').toArray();
	for ( var i = 0; i < array.length; i++) {
		var amountid = array[i].id.replace(/columnMoney/, "");
		if (amountid == priceid && money !=0 || (money ==0 && array[i].value ==null && array[i].value ==0))
			array[i].value=Math.round(money*100)/100;
	}
}else if(price.id.indexOf("columnAmount")>0){
		var priceid = price.id.replace(/columnAmount/, "");
	var array = $('.columnPrice').toArray();//获取classStyle=columnAmount的元素
	var money;
	//获取当前价格行的数量值并相乘
	for ( var i = 0; i < array.length; i++) {
		var amountid = array[i].id.replace(/columnPrice/, "");
		if (amountid == priceid)
			money=array[i].value * price.value;
	}
	//获取当前价格行的金额行并赋值
	array = $('.columnMoney').toArray();
	for ( var i = 0; i < array.length; i++) {
		var amountid = array[i].id.replace(/columnMoney/, "");
		if (amountid == priceid && money !=0 || (money ==0 && array[i].value ==null && array[i].value ==0))
			array[i].value=Math.round(money*100)/100;
	}
}
}
