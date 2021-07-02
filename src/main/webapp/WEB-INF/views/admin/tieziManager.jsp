<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>

	<head>
		<meta charset="UTF-8">
		<title>帖子管理</title>
		<link rel="stylesheet" href="../../../static/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="../../../static/css/global.css" media="all">
		<link rel="stylesheet" href="../../../static/plugins/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="../../../static/css/table.css" />
	</head>

	<body>
		<div class="admin-main">
			<fieldset class="layui-elem-field">

				<legend>数据列表</legend>
				<div class="layui-field-box layui-form">
					<table class="layui-table admin-table">
						<thead>
							<tr>
								<th>ID</th>
								<th>帖子名称</th>
								<th>帖子内容</th>
								<th>创建时间</th>
								<th>浏览数</th>
								<th>回帖数</th>
								<th>帖子状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="content">
						</tbody>
					</table>
				</div>
			</fieldset>
			<div class="admin-table-page">
				<div id="paged" class="laypage">
				</div>
			</div>
		</div>
		<script type="text/javascript" src="../../../static/plugins/layui/layui.js"></script>
		<script type="text/javascript" src="../../../js/jquery.js"></script>
		<script type="text/javascript" charset="UTF-8">
            function ChangeDateFormat(d) {
                //将时间戳转为int类型，构造Date类型
                var date = new Date(parseInt(d.time, 10));
                //月份得+1，且只有个位数时在前面+0
                var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
                //日期为个位数时在前面+0
                var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
                //getFullYear得到4位数的年份 ，返回一串字符串
                return date.getFullYear() + "-" + month + "-" + currentDate;
            }
		</script>
		<script>

			layui.use('laypage', function(){
				var laypage = layui.laypage;
				//执行一个laypage实例
				laypage.render({
					elem: 'test1' //注意，这里的 test1 是 ID，不用加 # 号
					,count: 50 //数据总数，从服务端得到
				});
			});

			layui.use([ 'form'], function() {
                var $ = layui.jquery,
                    layer = layui.layer, //获取当前窗口的layer对象
                    form = layui.form();

				$.ajax({
					type:'post',
					url:'/tieziServlet/tieziShowInit',
                    contentType: 'application/json;charset=utf-8',
					success:function (data) {
                        var jsonData = eval(data);
                        console.log(jsonData)
                        for (let i = 0; i < jsonData.length; i++) {

							var title = jsonData[i]['title'];
							if(title.length>20){
								title = title.substr(0,13)+"……";
							}

							var tcontent = jsonData[i]['tcontent'];
							if(tcontent.length>20){
								tcontent = tcontent.substr(0,13)+"……";
							}

							var status = jsonData[i]['status'];
							console.log("status is:"+status);
							var statusInfo = "未加精";
							if(status == 1){
								statusInfo = "已加精";
								statusInfo = "<span style=\"color:red\">"+statusInfo;
							}


                            $("#content").append(
                                `<tr>

							<td>` + (i + 1) + `</td>
							<td>` + title + `</td>
							<td>` + tcontent + `</td>
							<td>` + ChangeDateFormat(jsonData[i]['tdate']) +`</td>
							<td>` + jsonData[i]['tnum1'] +`</td>
							<td>` + jsonData[i]['tnum2'] +`</td>
							<td>` + statusInfo +`</td>
							<td><button class="layui-btn layui-btn-small layui-icon" onclick="jiajing(` +
									jsonData[i]['tid'] + `)">加精
							</button>
								<button class="layui-btn layui-btn-small layui-btn-danger layui-icon" onclick="deleteTiezi(` + jsonData[i]['tid'] + `)">删除
								</button>
							</td>
						</tr>`
                            )
                        }
                    }
				})
            });

			//加精
			function jiajing(id) {
				var $ = layui.jquery,
						layer = layui.layer, //获取当前窗口的layer对象
						form = layui.form();
				layer.confirm("是否加精？", {icon: 3, title: '确定'}, function (index) {
					$.ajax({
						type:'post',
						url:'/tieziServlet/jiajing',
						data:{id : id}
					})
					layer.msg('加精成功', {icon: 6, time: 500}, function () {
						location.reload();
					});
					layer.close(index);
				})
			}

			//删除
			function deleteTiezi(id) {
                var $ = layui.jquery,
                    layer = layui.layer, //获取当前窗口的layer对象
                    form = layui.form();
                layer.confirm("是否删除？", {icon: 3, title: '删除'}, function (index) {
                    $.ajax({
                        type:'post',
                        url:'/tieziServlet/deleteTiezi',
                        data:{id : id}
                    })
                    layer.msg('删除成功', {icon: 6, time: 500}, function () {
                        location.reload();
                    });
                    layer.close(index);
                })
            }
		</script>
	</body>

</html>