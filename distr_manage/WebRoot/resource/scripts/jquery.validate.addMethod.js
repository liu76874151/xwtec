//中文验证
jQuery.validator.addMethod("isChcharacter", function (value, element) { 
	
		var tel = /^[\u4e00-\u9fa5]+$/;  
    	return this.optional(element) ||  tel.test(value);
}, $.validator.format("请输入汉字"));  
//大写字母验证
jQuery.validator.addMethod("isUppercase", function (value, element) { 
	
	var tel = /^[A-Z]+$/;  
    	return this.optional(element) ||tel.test(value);
}, $.validator.format("请输入大写英文字母"));  


//字母数字  
jQuery.validator.addMethod("isCharacter", function (value, element) {  
    return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);  
}, $.validator.format("只能包括英文字母和数字"));  



//数字验证
jQuery.validator.addMethod("isNum", function (value, element) { 
	
	var tel = /^[0-9]+$/;  
    	return this.optional(element) ||tel.test(value);
}, $.validator.format("请输入数字"));  

// 字符最小长度验证（一个中文字符长度为2）
jQuery.validator.addMethod("stringMinLength", function(value, element, param) {
 var length = value.length;
 for ( var i = 0; i < value.length; i++) {
  if (value.charCodeAt(i) > 127) {
   length++;
  }
 }
 return this.optional(element) || (length >= param);
}, $.validator.format("长度不能小于{0}"));

// 字符最大长度验证（一个中文字符长度为2）
jQuery.validator.addMethod("stringMaxLength", function(value, element, param) {
 var length = value.length;
 for ( var i = 0; i < value.length; i++) {
  if (value.charCodeAt(i) > 127) {
   length++;
  }
 }
 return this.optional(element) || (length <= param);
}, $.validator.format("长度不能大于{0}"));


//字符验证  
jQuery.validator.addMethod("isString", function (value, element) {  
    return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);  
},  $.validator.format("不允许包含特殊符号"));  


//手机号码验证  
jQuery.validator.addMethod("isMobile", function (value, element) {  
    var length = value.length;  
    var mobile = /^1[3|4|5|8][0-9]\d{4,8}$/;  
    return this.optional(element) || (length == 11 && mobile.test(value));  
},  $.validator.format("请正确填写您的手机号码"));  
  