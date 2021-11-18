<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/14
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head></head>
<style>
    .submit{
        background-color: #70bb8b; color:#FFF;
    }
    .input{
        height:30px;
        background-color: #F5FAFA;
    }
</style>
<body>
<table border="0" width="100%" cellspacing="3" cellpadding="3">
    <tr>
        <td>
            <div style="height:17px"></div>
            <h1 align="center">单词查询</h1>
        </td>
    </tr>
    <tr>
        <td  align="center" width="100%" style="margin-left: 20%">
            <div style="width:80%;" align="center" >
                <table align="center">
                    <tr><h5 align="center">*模糊查询：输入单词的部分字母，也可查询</h5></tr>
                    <tr>
                        <td align="center">
                            <form align="center" action="SearchResult.jsp" method="POST">
                                <table>
                                    <tr>
                                        <td>单词：</td>
                                        <td><input type="text" class="input" name="Initial"/>
                                        </td>
                                    </tr>
                                </table>
                                <input type="submit" style="margin-top: 30px;width: 125px;height: 30px" class="submit" value="检索"/>
                            </form>
                        </td>
                    </tr>
                </table>
            </div>
        </td>
    </tr>
    </tr>
</table>
</body>
</html>