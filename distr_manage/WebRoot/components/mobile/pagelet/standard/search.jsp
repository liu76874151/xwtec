<%@page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<div class="box-body">
<form action="actionDispatcher.do" method="post">
  <p><input type="text" value="$!{rsMap.get("keywords")}" name="keywords"/> 
     <input class="btn btn-orange" type="submit" name="ss" value="搜索"/></p>
  <p>
   <input type="hidden" name="pageNum" value="searchResult"/>
   <input type="hidden" name="ver" value="s"/>
   <input type="hidden" name="operType" value="3">
   #foreach($item in ${result.vRetLst})##遍历结果 
     <p><font color="red">$!{item.retMsg}</font></p>
   #end
</form>
<p> 热搜：</p>
 </div>      