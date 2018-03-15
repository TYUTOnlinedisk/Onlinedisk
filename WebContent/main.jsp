<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%-- request.setCharacterEncoding("utf-8");--%>
<% if(request.getSession().getAttribute("user") == null){
      response.sendRedirect("login.html");
   }
%>
<!DOCTYPE html>
<html lang="en-gb" dir="ltr">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>我的网盘</title>
        <link id="data-uikit-theme" rel="stylesheet" href="css/uikit.docs.min.css">
        <link rel="stylesheet" href="css/docs.css">
        <link rel="stylesheet" href="css/highlight.css">
        <script src="js/jquery.js"></script>
        <script src="js/uikit.min.js"></script>
        <script src="js/highlight.js"></script>
        <script src="js/docs.js"></script>
        <script src="js/sticky.js"></script>
        <script type="text/javascript">
        	var curdir = '';
        	<% String dir = request.getParameter("dir");
			   if(dir != null) {
			%>
			curdir = '<%=dir %>';
			<% } %>
        </script>
    </head>

    <body class="tm-background">
        <div class="tm-middle">
            <div class="uk-container uk-container-center">

                <div class="uk-grid" data-uk-grid-margin>
                    <%@ include file="menu.html"%>
                    <div class="tm-main uk-width-medium-4-5">

                        <article class="uk-article">

                            <h1>我的网盘</h1>

                            <p>
                                <a class="uk-button" href="#" onclick="openUpload()">上传文件</a>
                                <button class="uk-button" type="button" data-uk-modal="{target:'#newDirDia'}">新建文件夹</button>
                                <input onchange="upload()" type="file" id="custfile" name="custfile" style="display: none;" />
                            </p>
                            <p id="path">
                            </p>
                            <div class="uk-overflow-container">
                                <table class="uk-table uk-table-striped uk-text-nowrap">
                                    <thead>
                                        <tr>
                                            <th style="width: 100px">操作</th>
											<th>文件名</th>
											<th style="width: 80px">大小</th>
											<th style="width: 160px">创建时间</th>
                                        </tr>
                                    </thead>
                                    <tbody id="docList">
                                    </tbody>
                                </table>
                            </div>

                        </article>

                    </div>
                </div>

            </div>
        </div>
        <div id="newDirDia" class="uk-modal">
		    <div class="uk-modal-dialog">
		        <a class="uk-modal-close uk-close"></a>
		        <h2>新建文件夹</h2>
		        <form class="uk-form uk-form-horizontal">
                	<div class="uk-form-row">
                        <label class="uk-form-label" for="newName">名称</label>
                        <div class="uk-form-controls">
                            <input id="newName" type="text">
                            <a class="uk-button" href="" onclick="addDir()">确定</a>
                        </div>
                    </div>
                </form>
		    </div>
		</div>
		<script type="text/javascript" src="js/ajaxfileupload.js"></script>
		<script type="text/javascript">
			function openUpload(){
				$('#custfile').click();
			}
			
			function upload() {
				if ($('#custfile').val() == "") {
					return;
				}
				//一个异步上传文件的jQuery插件
				$.ajaxFileUpload({
					url : 'UploadFile?dir=' + curdir,
					fileElementId : 'custfile',//需要上传的文件域的ID
					type : 'POST', //当要提交自定义参数时，这个参数要设成post
					success : function(r, status) {
						openDir(curdir);
					},
					error : function(data, status, e){
	
					}
				});
			}
			
			function openDir(dir){
				if (dir != '') {
					var head = ' <a href="#" onclick="openDir(\'\')">返回上一级</a> | ';
					var inner = ' <a href="#" onclick="openDir(\'\')">全部文件</a> &gt;';
					var strs = new Array();
					strs = dir.split("/");
					var d = '';
					$.each(strs,function(n, value) {
						if (n == strs.length - 1) {
							inner += '<span>' + value + '</span>';
						} else {
							if (d != '') {
								d += '/';
							}
							d += value;
							inner += '<a href="#" onclick="openDir(\'' + d + '\')">'
									+ value
									+ '</a> &gt; ';
						}
						if (n == strs.length - 2) {
							head = '<a href="#" onclick="openDir(\'' + d + '\')">返回上一级</a> |';
						}
					});
					$('#path').html(head + inner);
				} else{
					$('#path').html('');
				}
				
				$.ajax({
					url : 'GetFilesInDir?dir=' + dir,
					type : "GET",
					async : false,
					data : {},
					error : function(errorMsg) {
					},
					success : function(data) {
						$('#docList').empty();
						
						$.each(data,function(n, value) {
							var line = '<tr><td>';
							if(value.isdir == 0){
								line += '<a href="#" onclick="downloadFile(\'' + value.id + '\')">下载</a>&nbsp';
							}
							line += '<a href="#" onclick="deleteFile(\'' + value.directory + '\', '+ value.isdir + ')">删除</a></td>';
							if(value.isdir == 0){
								line += '<td><i class="uk-icon-file-word-o"></i>&nbsp' + value.name + '</td>';
								line += '<td>' + value.size + 'k</td>';
							} else{
								line += '<td><i class="uk-icon-folder-open-o"></i>&nbsp<a href="javascript:openDir(\'' + value.directory + '\')">' + value.name + '</a></td>';
								line += '<td></td>';
							}
							line += '<td>' + value.createtime + '</td>';
							line += '<tr>';
							$('#docList').append(line);
						});
					}
				});
				curdir = dir;
			}
			
			function downloadFile(id){
				window.location.href = 'DownloadFile?id=' + id + '&dir=' + curdir;
			}
			
			function deleteFile(dir, isdir){
				$.ajax({
					url : 'DeleteFile?dir=' + dir + '&isdir=' + isdir,
					type : "GET",
					async : false,
					data : {},
					error : function(errorMsg) {
					},
					success : function(data) {
						if(data == '0'){
							alert('删除失败');
						}
						openDir(curdir);
					}
				});
			}
			
			function addDir(){
				
				$.ajax({
					url : 'CreateDir?dir=' + curdir + '&name=' + $('#newName').val(),
					type : "GET",
					async : false,
					data : {},
					error : function(errorMsg) {
					},
					success : function(data) {
						if(data == '0'){
							alert('创建失败');
						}
						openDir(curdir);
					}
				});
			}
			
			jQuery(function($) {
				
				
			});
			
			openDir(curdir);
		</script>
    </body>
    
</html>
