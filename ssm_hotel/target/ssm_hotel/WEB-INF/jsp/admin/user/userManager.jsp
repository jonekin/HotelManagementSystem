<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>用户管理</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.6.3/css/layui.css" media="all">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui/css/public.css" media="all">
</head>
<body>
<div class="layuimini-container">
  <div class="layuimini-main">

    <!-- 搜索条件 -->
    <fieldset class="table-search-fieldset">
      <legend>搜索信息</legend>
      <div style="margin: 10px 10px 10px 10px">
        <form class="layui-form layui-form-pane" action="">
          <div class="layui-form-item">
            <div class="layui-inline">
              <label class="layui-form-label">用户姓名</label>
              <div class="layui-input-inline">
                <input type="text" name="loginName" autocomplete="off" class="layui-input">
              </div>
            </div>
            <div class="layui-inline">
              <label class="layui-form-label">真实姓名</label>
              <div class="layui-input-inline">
                <input type="text" name="realName" autocomplete="off" class="layui-input">
              </div>
            </div>
            <div class="layui-inline">
              <label class="layui-form-label">身份证号</label>
              <div class="layui-input-inline">
                <input type="text" name="idCard" autocomplete="off" class="layui-input">
              </div>
            </div>
            <div class="layui-inline">
              <label class="layui-form-label">手机号码</label>
              <div class="layui-input-inline">
                <input type="text" name="phone" autocomplete="off" class="layui-input">
              </div>
            </div>
          </div>
          <div class="layui-form-item">
            <div class="layui-input-block" style="text-align: center">
              <button type="submit" class="layui-btn"  lay-submit lay-filter="data-search-btn"><i class="layui-icon layui-icon-search"></i>搜索</button>
              <button type="reset" class="layui-btn layui-btn-warm"><i class="layui-icon layui-icon-refresh-1"></i>重置</button>
            </div>
          </div>
        </form>
      </div>
    </fieldset>

    <!-- 数据表格 -->
    <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

    <!-- 行工具栏 -->
    <script type="text/html" id="currentTableBar">
      <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
      <button class="layui-btn layui-btn-xs layui-btn-warm" lay-event="resetPwd">重置密码</button>
    </script>

    <!-- 添加和修改窗口 -->
    <div style="display: none;padding: 5px" id="addOrUpdateWindow">
      <form class="layui-form" style="width:90%;" id="dataFrm" lay-filter="dataFrm">
        <!-- 隐藏域，保存员工id -->
        <input type="hidden" name="id" id="id">

        <div class="layui-form-item">
          <div class="layui-inline">
            <label class="layui-form-label">用户姓名</label>
            <div class="layui-input-block">
              <input type="text" name="loginName" id="loginname" lay-verify="required"  autocomplete="off" placeholder="请输入登陆名称" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label">真实姓名</label>
            <div class="layui-input-block">
              <input type="text" name="realName" id="realname" lay-verify="required" autocomplete="off" placeholder="请输入用户姓名" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label">身份证号</label>
            <div class="layui-input-inline">
              <input type="text" name="idcard" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label">手机号码</label>
            <div class="layui-input-inline">
              <input type="text" name="phone" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-inline">
              <input type="text" name="email" autocomplete="off" class="layui-input">
            </div>
          </div>
        </div>

        <div class="layui-form-item layui-row layui-col-xs12">
          <div class="layui-input-block" style="text-align: center;">
            <button type="button" class="layui-btn" lay-submit lay-filter="doSubmit"><span class="layui-icon layui-icon-add-1"></span>提交</button>
            <button type="reset" class="layui-btn layui-btn-warm" ><span class="layui-icon layui-icon-refresh-1"></span>重置</button>
          </div>
        </div>
      </form>
    </div>

  </div>
</div>
<script src="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script>
  layui.use(['form', 'table','laydate','jquery'], function () {
    var $ = layui.jquery,
            form = layui.form,
            laydate = layui.laydate,
            table = layui.table;

    //渲染表格组件
    var tableIns = table.render({
      elem: '#currentTableId',
      url: '${pageContext.request.contextPath}/admin/userManager/list',
      toolbar: '#toolbarDemo',
      cols: [[
        {type: "checkbox", width: 50},
        {field: 'loginName', width: 100, title: '用户姓名', align: "center"},
        {field: 'realName', width: 100, title: '真实姓名', align: "center"},
        {field: 'idCard', width: 220, title: '身份证号', align: "center"},
        {field: 'phone', width: 180, title: '电话', align: "center"},
        {field: 'email', width: 220, title: '邮箱', align: "center"},
        {title: '操作', minWidth: 100, toolbar: '#currentTableBar', align: "center"}
      ]],
      page: true,
      done: function (res, curr, count) {
        //判断当前页码是否是大于1并且当前页的数据量为0
        if (curr > 1 && res.data.length == 0) {
          var pageValue = curr - 1;
          //刷新数据表格的数据
          tableIns.reload({
            page: {curr: pageValue}
          });
        }
      }
    });

    // 监听搜索操作
    form.on('submit(data-search-btn)', function (data) {
      tableIns.reload({
        where: data.field,
        page: {
          curr: 1
        }
      });
      return false;
    });


    //监听行工具栏
    table.on("tool(currentTableFilter)",function (obj) {
      switch (obj.event) {
        case "delete":
          deleteById(obj.data);
          break;
        case "resetPwd":
          resetPwd(obj.data);
          break;
      }
    });

    /**
     * 删除员工
     * @param data 当前行数据
     */
    function deleteById(data){
          //提示用户是否删除该用户
          layer.confirm("确定要删除[<font color='red'>"+data.loginName+"</font>]吗",{icon: 3,title: '提示'},function (index) {
            //发送ajax请求进行删除
            $.post("/admin/userManager/deleteByName",{"loginName":data.loginName},function (result) {
              if (result.success){
                //刷新数据表格
                tableIns.reload();
              }
              //提示
              layer.msg(result.message);
            },"json");
            layer.close(index);
          });
        }

    /**
     * 重置密码
     * @param data
     */
    function resetPwd(data){
      //提示用户是否重置密码
      layer.confirm("确定要重置[<font color='red'>"+data.loginName+"</font>]的密码吗",{icon: 3,title: '提示'},function (index) {
        //发送ajax请求进行删除
        $.post("/admin/userManager/resetPwd",{"loginName":data.loginName},function (result) {
          if (result.success){
            //刷新数据表格
            tableIns.reload();
          }
          //提示
          layer.msg(result.message);
        },"json");
        layer.close(index);
      });
    }


  });

</script>

</body>
</html>

