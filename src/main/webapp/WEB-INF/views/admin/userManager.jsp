﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>会员管理</title>
    <link rel="stylesheet" href="../../../static/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="../../../static/css/global.css" media="all">
    <link rel="stylesheet" href="../../../static/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="../../../static/css/table.css"/>
    <script type="text/javascript" src="../../../js/jquery.js"></script>
</head>

<body>
<div class="admin-main">

    <blockquote class="layui-elem-quote">
        <button class="layui-btn layui-btn-small" id="addUser" lay-submit="" lay-filter="addUser">
            <i class="layui-icon">&#xe608;</i> 添加管理员
        </button>
    </blockquote>
    <fieldset class="layui-elem-field">
        <legend>数据列表</legend>
        <div class="layui-field-box layui-form">
            <table class="layui-table admin-table">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>用户名</th>
                    <th>邮箱</th>
                    <th>权限(1:管理员;0:会员)</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="content">
                </tbody>
            </table>
        </div>
    </fieldset>
    <div class="admin-table-page">
        <div id="paged" class="page">
        </div>
    </div>
</div>

<script type="text/javascript" src="../../../static/plugins/layui/layui.js"></script>
<script>
    layui.use(['form'], function () {
        var $ = layui.jquery,
            layer = layui.layer, //获取当前窗口的layer对象
            form = layui.form();

        $.ajax({
            type: 'post',
            url: '/usersServlet/usersManager',
            contentType: 'application/json;charset=utf-8',
            success: function (data) {
                var jsonData = eval(data);
                for (let i = 0; i < jsonData.length; i++) {
                    $("#content").append(
                        `<tr>
							<td>` + (i + 1) + `</td>
							<td>` + jsonData[i]['uname'] + `</td>
							<td>` + jsonData[i]['uemail'] + `</td>
							<td>` + jsonData[i]['utype'] +`</td>
							<td><button class="layui-btn layui-btn-small layui-icon" onclick="editUser(` + jsonData[i]['uid'] + `)">编辑
							</button>
								<button class="layui-btn layui-btn-small layui-btn-danger layui-icon" onclick="deleteUser(` + jsonData[i]['uid'] + `)">删除
								</button>
							</td>
						</tr>`
                    )
                }
            }
        });

        //添加管理员
        form.on('submit(addUser)',function () {
            layer.open({
                type: 1,
                title: '添加管理员',
                area: ['360px', '400px'],
                skin: 'layui-layer-rim',
                content: $('#userAdd')
            });
        })


    });

    /**
     * 编辑
     */
    function editUser(id) {
        var $ = layui.jquery,
            layer = layui.layer, //获取当前窗口的layer对象
            form = layui.form();
        $.ajax({
            type: 'get',
            url: '/usersServlet/selectUesrId',
            data: {id:id},
            contentType: 'application/json;charset=utf-8',
            success: function (data) {
                var jsonData = eval(data);
                $("input[name='u_id']").val(id);
                $("input[name='u_username']").val(jsonData[0]['uname']);
                $("input[name='u_pwd']").val(jsonData[0]['upwd']);
                $("input[name='u_info']").val(jsonData[0]['uemail']);
                $("#userUpdate input[name='power'][value='"+jsonData[0]['utype']+"']").attr("checked",true);
                // $("input[name=power][value="+jsonData[0]['utype']+"]").attr("checked",true);
                layer.open({
                    type: 1,
                    title: '修改信息',
                    area: ['360px', '400px'],
                    skin: 'layui-layer-rim',
                    content: $('#userUpdate')
                });

            }
        })
    }
    
    //删除管理员
    function deleteUser(id) {
        var $ = layui.jquery,
            layer = layui.layer, //获取当前窗口的layer对象
            form = layui.form();
        layer.confirm("是否删除？", {icon: 3, title: '删除'}, function (index) {
            $.ajax({
                type:'post',
                url:'/usersServlet/deleteUser',
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
<div class="layui-field-box layui-form" id="userUpdate" style="display: none">
    <form class="layui-form" action="/usersServlet/editUser" method="post">
        <div class="layui-form-item">
            <input type="hidden" name="s_id">
            <label class="layui-form-label">ID</label>
            <div class="layui-input-inline">
                <input style="background:#F6F6F6" name="u_id" id="u_id" readonly autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">输入用户名</label>
            <div class="layui-input-inline">
                <input type="text" name="u_username" id="u_name" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">请输入密码</label>
            <div class="layui-input-inline">
                <input name="u_pwd" id="u_pwd" lay-verify="pass_one1" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">请填写邮箱</label>
            <div class="layui-input-inline">
                <input name="u_info" id="u_info" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">权限</label>
            <div class="layui-input-block">
                <input type="radio" name="power" value="1" title="管理员">
                <input type="radio" name="power" value="0" title="会员">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" type="submit">保存</button>
            </div>
        </div>
    </form>
</div>



<div class="layui-field-box layui-form" id="userAdd" style="display: none">
    <form class="layui-form" action="/usersServlet/addUser" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">输入用户名</label>
            <div class="layui-input-inline">
                <input type="text" name="uname" id="uname" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">请输入密码</label>
            <div class="layui-input-inline">
                <input name="upwd" id="upwd" lay-verify="pass_one1" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">请填写邮箱</label>
            <div class="layui-input-inline">
                <input name="uinfo" id="uinfo" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">权限</label>
            <div class="layui-input-block">
                <input type="radio" name="power" value="1" title="管理员">
                <input type="radio" name="power" value="0" title="会员">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" type="submit">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
</html>