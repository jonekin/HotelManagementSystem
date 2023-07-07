<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/6/2
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <title>修改密码</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.6.3/css/layui.css" media="all">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui/css/public.css" media="all">
  <style>
    .layui-form-item .layui-input-company {width: auto;padding-right: 10px;line-height: 38px;}
  </style>
</head>
<body>
<div class="layuimini-container">
  <div class="layuimini-main">

    <div class="layui-form layuimini-form">
      <div class="layui-form-item">
        <label class="layui-form-label required">旧的密码</label>
        <div class="layui-input-block">
          <input type="password" name="oldPassword" lay-verify="required" lay-reqtext="旧的密码不能为空" placeholder="请输入旧的密码"  value="" class="layui-input">
          <tip>填写自己账号的旧的密码。</tip>
        </div>
      </div>

      <div class="layui-form-item">
        <label class="layui-form-label required">新的密码</label>
        <div class="layui-input-block">
          <input type="password" name="newPassword" lay-verify="required" lay-reqtext="新的密码不能为空" placeholder="请输入新的密码"  value="" class="layui-input">
        </div>
      </div>
      <div class="layui-form-item">
        <label class="layui-form-label required">新的密码</label>
        <div class="layui-input-block">
          <input type="password" name="newPassword" lay-verify="required" lay-reqtext="新的密码不能为空" placeholder="请输入新的密码"  value="" class="layui-input">
        </div>
      </div>

      <div class="layui-form-item">
        <div class="layui-input-block">
          <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认保存</button>
        </div>
      </div>
    </div>
  </div>
</div>
<script src="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/statics/layui/js/lay-config.js?v=2.0.0" charset="utf-8"></script>
<script>
  layui.use(['form','miniTab'], function () {
    var form = layui.form,
            layer = layui.layer,
            miniTab = layui.miniTab,
            $ = layui.$;

    //监听提交
    form.on('submit(saveBtn)', function (data) {
      $.post("/admin/employee/changePassword",data.field,function (result) {
        if (result.success){
          layer.msg(result.message);
        }else {
          location.href="/admin/login.html";
        }
      })
    });

  });
</script>
</body>
</html>