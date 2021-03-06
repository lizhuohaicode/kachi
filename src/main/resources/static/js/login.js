

$(function(){

	setPersonal();

	$('.login').css('margin-top',($(window).height()-420)/2+'px');
	
	changeValidateCode();
	initLogInfo();
	
	$('#savePwd').click(function(){
		$(this).toggleClass('checked');
	});
	
	//绑定enter键登录事件
	$(document).keydown(function(e){
		if(e.keyCode == 13) {
			doLogin();
		}
	});
	$.ajaxSetup({async : false}); 
});

function checkLogin() {
	var loginName=$('[name=userName]').val();
	var passWord=$('[name=passWord]').val();
	var code=$('[name=code]').val();
	if(''==loginName) {
		$('[name=userName]').focus();
		$('#msg').text('请输入用户名!');
		return false;
	}
	if(''==passWord) {
		$('[name=passWord]').focus();
		$('#msg').text('请输入密码!');
		return false;
	}
	if(''==code) {
		$('#msg').text('请输入验证码!');
		$('[name=code]').focus();
		return false;
	}
	return true;
}

function doLogin() {
	var bl=checkLogin();
	if(bl) {
		checked=$('#savePwd')[0].checked;
		if(checked) {
			createCookie('kachi_username',$('[name=userName]').val(),7);
			createCookie('kachi_password',$('[name=passWord]').val(),7);
		}
		else {
			createCookie('kachi_username','',7);
			createCookie('kachi_password','',7);
		}
		createCookie('kachi_savePwd',checked,7);
		// var loginName=$('[name=userName]').val();
		// var passWord=$('[name=passWord]').val();
		// var code=$('[name=code]').val();


        $.jCryption.getKeys(getContextPath()+'/getPublicKey',function(receivedKeys){ // 异步请求获取用来加密的公钥
			var keys = receivedKeys;
            if( null != keys && "undefined" != keys){
                var postData = {};
                var origPwd = $('[name=passWord]').val();
                var ip = /(\d{1,3})(\.(\d{1,3})){3}|localhost/.exec(location.href)[0];
                //实用公钥进行加密
                $.jCryption.encrypt(origPwd, keys, function(encryptedPasswd) {  ///使用公钥谨慎性加密
					$.post(getContextPath()+'/login',{userName:$('[name=userName]').val(),
													  passWord:encryptedPasswd,
						   							  code:$('[name=code]').val(),
													  ip:ip
						 							 },
						function(data){
							if(data && data.code=='200'){
								window.location.href=getContextPath()+'/index';
							}else{
								$('#msg').text(data.msg);
								changeValidateCode();
								$('[name=code]').val('');
						}
					});
                })
            }
        });


	}
}

	
function changeValidateCode() {
	   //获取当前的时间作为参数，无具体意义
	   var timenow = new Date().getTime();
	   //每次请求需要一个不同的参数，否则可能会返回同样的验证码
	   $('#code')[0].src = getContextPath()+"/verificationCode?d=" + timenow;
	   // $.get(getContextPath()+"/getCode?d"+timenow,function(data){
		//    if(data){
		// 	   $('#code')[0].src = data;
		//    }
	   // },'text');
 }

/**初始化登陆信息**/
function initLogInfo() {
    savePwd=readCookie('kachi_savePwd');
    if(eval(savePwd)) {
    	$('[name=userName]').val(readCookie('kachi_username'));
    	$('[name=passWord]').val(readCookie('kachi_password'));
    	$('#savePwd').attr('checked',true).addClass('checked');
    }
}

function createCookie(name,value,days){
	if (days){
		var date = new Date();
		date.setTime(date.getTime()+(days*24*60*60*1000));
		var expires = "; expires="+date.toGMTString();
	}else var expires = "";
	document.cookie = name+"="+value+expires+"; path=/";
}
function readCookie(name){
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for(var i=0;i < ca.length;i++){
		var c = ca[i];
		while (c.charAt(0)==' ') c = c.substring(1,c.length);
		if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
	}
	return null;
}
function eraseCookie(name){
	this.createCookie(name,"",-1);
}
function getContextPath(fullUrl){
	   if(fullUrl==null || fullUrl==''){
		   fullUrl = window.location.href+'';
	   }
	   var arrUrl = fullUrl.split('/');
	   return arrUrl[0]+'//'+arrUrl[2]+'/'+arrUrl[3];
}
//解决在火狐上不兼容
$.fn.serializeObject = function()
{
 var o = {};
 var a = this.serializeArray();
 $.each(a, function() {
  if (o[this.name]) {
   if (!o[this.name].push) {
    o[this.name] = [o[this.name]];
   }
   o[this.name].push(this.value || '');
  } else {
   o[this.name] = this.value || '';
  }
 });
 return o;
};

function setPersonal() {
	$.post(getContextPath()+'/personal/personal',{ip:/(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])(\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])){3}|localhost/.exec(location.href)[0]},function (data) {
		var bgImg ,titleText;
		if(data.data){
			$.each(data.data,function (idx, obj) {
				if(obj.type === '0')
					bgImg = obj.content;
				if(obj.type === '1')
					titleText = obj.content;
			});

			$('#titleText').text(titleText);
			$('body').css('backgroundImage',"url('../"+bgImg+"')");
		}
	})
}