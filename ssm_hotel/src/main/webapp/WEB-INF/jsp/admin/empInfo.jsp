<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/6/2
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>基本资料</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui/css/public.css" media="all">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <!-- 数据表格 -->
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <div style="display: none;padding: 5px" id="addOrUpdateWindow">
            <form class="layui-form" style="width:90%;" id="dataFrm" lay-filter="dataFrm">
                <!-- 隐藏域，保存员工id -->
                <input type="hidden" name="id" id="id">

                <div class="layui-form-item">
                    <label class="layui-form-label">登陆名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="loginName" id="loginname" lay-verify="required"  autocomplete="off" placeholder="请输入登陆名称" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">员工姓名</label>
                    <div class="layui-input-block">
                        <input type="text" name="name" id="name" lay-verify="required" autocomplete="off" placeholder="请输入员工姓名" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">入职日期</label>
                    <div class="layui-input-block">
                        <input type="text" name="hireDate" id="hiredate" readonly lay-verify="required" autocomplete="off" placeholder="请选择入职日期" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">所属部门</label>
                    <div class="layui-input-block">
                        <input type="text" name="deptId" id="deptid" readonly lay-verify="required" autocomplete="off"  class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">员工性别</label>
                        <input type="text" name="sex" id="sex" readonly lay-verify="required" autocomplete="off"  class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">员工备注</label>
                    <div class="layui-input-block">
                        <textarea class="layui-textarea" name="remark" id="remark"></textarea>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'table','jquery'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;

        //渲染表格组件
        var tableIns = table.render({
            elem: '#currentTableId',
            url: '${pageContext.request.contextPath}/admin/employee/basicInfo',
            cols: [[
                {field: 'id', width: 100, title: '员工编号', align: "center"},
                {field: 'loginName', width: 100, title: '登录名', align: "center"},
                {field: 'name', width: 100, title: '真实姓名', align: "center"},
                {field: 'sex', width: 80, title: '性别', align: "center",templet:function (d) {
                        return d.sex == 1 ? "男" : "女";
                    }},
                {field: 'deptName', width: 120, title: '所属部门', align: "center"},
                {field: 'hireDate', width:150,  title: '入职日期', align: "center"},
                {field: 'remark',  title: '备注', align: "center"}
            ]],

        });


    });

</script>

</body>
</html>

