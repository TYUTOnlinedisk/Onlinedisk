<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en-gb" dir="ltr">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>我的网盘</title>
        <meta name="description" content="开始学习使用 UIkit，获取UIkit源文件并熟悉 UIkit 的基本组织结构。">
        <meta name="author" content="UIkit中文网">
         
        <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
        <link rel="apple-touch-icon-precomposed" href="images/apple-touch-icon.png">
        <link id="data-uikit-theme" rel="stylesheet" href="css/uikit.docs.min.css">
        <link rel="stylesheet" href="css/docs.css">
        <link rel="stylesheet" href="css/highlight.css">
        <script src="js/jquery.js"></script>
        <script src="js/uikit.min.js"></script>
        <script src="js/highlight.js"></script>
        <script src="js/docs.js"></script>
        <script src="js/sticky.js"></script>
    </head>

    <body class="tm-background">
        <div class="tm-middle" style="padding-top: -70px;">
            <div class="uk-container uk-container-center">
                <div class="uk-grid" data-uk-grid-margin>
	                   <%@ include file="menu.html"%>
                    <div class="tm-main uk-width-medium-4-5">
                        <article class="uk-article">
                            <h1>修改密码</h1>
                            <br/>
                            <form class="uk-form uk-form-horizontal" action="updatePwd" method="post">
                            	<div class="uk-form-row">
                                    <label class="uk-form-label" for="form-h-ip">原密码</label>
                                    <div class="uk-form-controls">
                                        <input id="form-h-ip" placeholder="原密码" type="password" name="passwordB">
                                    </div>
                                </div>
                                <div class="uk-form-row">
                                    <label class="uk-form-label" for="form-h-ip">新密码</label>
                                    <div class="uk-form-controls">
                                        <input id="form-h-ip" placeholder="新密码" type="password" name="password">
                                    </div>
                                </div>
                                <div class="uk-form-row">
                                    <label class="uk-form-label" for="form-h-ip">新密码确认</label>
                                    <div class="uk-form-controls">
                                        <input id="form-h-ip" placeholder="新密码确认" type="password" name="passwordCF">
                                    </div>
                                </div>
                                <input type="submit" name="veryify" value="确认" onclick="return verifyPwd()"/>
                            </form>
                            <br/>
                            <p>  
                            </p>
                        </article>
                    </div>
                </div>
            </div>
        </div>
  <script src="js/jquery.js"></script>
  <script type="text/javascript">
    function verifyPwd(){
    	if($('[name="password"]').val() != $('[name="passwordCF"]').val()){
    		alert("两次输入的内容不一致");
    		return false;
    	}
    	return true;
    }
  </script>
    </body>
</html>
