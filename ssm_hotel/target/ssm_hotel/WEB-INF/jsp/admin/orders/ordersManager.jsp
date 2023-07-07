<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <!-- 获取CSRF Token -->
    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- 获取CSRF头，默认为X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.6.3/css/layui.css"
          media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui/css/public.css" media="all">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <%-- 搜索条件 --%>
        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">预订人</label>
                            <div class="layui-input-inline">
                                <input type="text" name="reservationname" autocomplete="off" class="layui-input">
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
                            <label class="layui-form-label">房间类型</label>
                            <div class="layui-input-inline">
                                <select name="roomtypeid"  autocomplete="off" class="layui-input s_roomTypeId">
                                    <option value="">全部</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">预订状态</label>
                            <div class="layui-input-inline">
                                <select name="status" autocomplete="off" class="layui-input">
                                    <option value="">全部</option>
                                    <option value="1">待确认</option>
                                    <option value="2">已确认</option>
                                    <option value="3">已入住</option>
                                    <option value="4">已完成</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">开始日期</label>
                            <div class="layui-input-inline">
                                <input type="text" name="startDate" id="startTime" autocomplete="off" class="layui-input" placeholder="请选择开始日期(预订)" readonly>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">结束日期</label>
                            <div class="layui-input-inline">
                                <input type="text" name="endDate" id="endTime" autocomplete="off" class="layui-input" placeholder="请选择结束日期(预订)" readonly>
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

        <%-- 表格工具栏 --%>
        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="batchConfirm"><i
                        class="layui-icon layui-icon-edit"></i>批量确认
                </button>
            </div>
        </script>

        <%-- 数据表格 --%>
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <%-- 行工具栏 --%>
        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-xs data-count-edit" lay-event="edit"><i
                    class="layui-icon layui-icon-ok-circle"></i>确认</a>
        </script>


    </div>
</div>
<script src="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'table', 'laydate', 'jquery', 'layer'], function () {
        var $ = layui.jquery,
            form = layui.form,
            laydate = layui.laydate,
            layer = layui.layer,
            table = layui.table;
        //
        // //获取<meta>标签中封装的CSRF Token
        // var token = $("meta[name='_csrf']").attr("content");
        // var header = $("meta[name='_csrf_header']").attr("content");
        // //将头中的CSRF Token信息进行发送
        // $(document).ajaxSend(function (e,xhr,options) {
        //     xhr.setRequestHeader(header,token);
        // });

        //渲染日期组件
        laydate.render({
            elem: '#startTime', //指定元素
            type: 'datetime'
        });
        laydate.render({
            elem: '#endTime', //指定元素
            type: 'datetime'
        });


        //渲染表格组件
        var tableIns = table.render({
            elem: '#currentTableId',
            url: '${pageContext.request.contextPath}/admin/orders/list',
            toolbar: '#toolbarDemo',
            cols: [[
                {type: "checkbox", width: 50},
                {field: 'id', width: 100, title: '预订编号', align: "center"},
                {field: 'roomType', width: 100, title: '预订房型', align: "center",templet:function (d) {
                        return d.roomType.typename;
                    }},
                {field: 'room', width: 80, title: '房间号', align: "center",templet:function (d) {
                        return d.room.roomnum;
                    }},
                {field: 'reservationname', width: 100, title: '预订人', align: "center"},
                {field: 'idcard', width: 170, title: '身份证号', align: "center"},
                {field: 'phone', width: 150, title: '手机号', align: "center"},
                {field: 'status', width: 80, title: '状态', align: "center",templet:function (d) {
                        if(d.status==1){
                            return "<font color='red'>待确认</font>";
                        }else if(d.status==2){
                            return "<font color='blue'>已确认</font>";
                        }else if(d.status==3){
                            return "<font color='orange'>入住中</font>";
                        }else {
                            return "<font color='black'>已完成</font>";
                        }
                    }},
                {field: 'reserveprice', width: 80, title: '预订价', align: "center"},
                {field: 'reservedate', width: 160, title: '预订时间', align: "center"},
                {field: 'arrivedate', width: 110, title: '入住日期', align: "center"},
                {field: 'leavedate', width: 110, title: '离店日期', align: "center"},
                {title: '操作', width: 120, toolbar: '#currentTableBar', align: "center",fixed:"right"}
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

        //查询房型列表
        $.get("/admin/roomType/findAll",function(result){
            var html = "";
            //循环遍历数据
            for (let i = 0; i <result.length; i++) {
                html +="<option value='"+result[i].id+"'>"+result[i].typename+"</option>"
            }
            //将数据追加到下拉列表中
            $("[name='roomtypeid']").append(html);
            //重新渲染下拉列表
            form.render("select");
        },"json");


        //监听表格头部工具栏事件
        table.on("toolbar(currentTableFilter)",function (obj) {
            switch (obj.event) {
                case 'batchConfirm':
                    batchConfirm();
                    break;
            }
        });

        //监听表格行工具栏事件
        table.on("tool(currentTableFilter)",function (obj) {
            switch (obj.event) {
                case 'edit':
                    confirmOrders(obj.data);
                    break;
            }
        });

        /**
         * 确认订单
         * @param data
         */
        function confirmOrders(data) {
            //判断当前订单状态是否确认
            if(data.status==1){
                //发送请求
                $.post("/admin/orders/confirmOrders",{"id":data.id},function(result){
                    if(result.success){
                        layer.alert(result.message,{icon:1});
                        //刷新数据表格
                        tableIns.reload();
                    }else{
                        layer.alert(result.message,{icon:2});
                    }
                },"json");
            }else{
                layer.msg("该订单已确认，无需重复操作");
            }
        }

        /**
         * 批量确认
         */
        function batchConfirm() {
            //获取选中行
            var checkStatus = table.checkStatus('currentTableId');
            //判断是否有选中行
            if(checkStatus.data.length>0){
                //循环遍历选中行的数据
                //判断选中行中是否有包含(已确认)的状态，如果包含已确认或已入住，此时提示用户“只能操作状态为待确认的订单”
                for (let i = 0; i < checkStatus.data.length; i++) {
                    if(checkStatus.data[i].status!=1){
                        layer.alert("只能操作状态为<font color='red'>待确认</font>的订单!",{icon:0});
                        return;
                    }
                }
                //提示用户是否确认
                layer.confirm("确定要确认这些订单吗?",{icon:3,title:"提示"},function (index) {
                    //定义数组保存选中行的id值
                    var idArr = [];
                    //循环遍历
                    for (let i = 0; i <checkStatus.data.length; i++) {
                        idArr.push(checkStatus.data[i].id)
                    }
                    //将数组转成字符串
                    var ids = idArr.join(",");
                    //发送请求
                    $.post("/admin/orders/batchConfirm",{"ids":ids},function(result){
                        if(result.success){
                            layer.alert(result.message,{icon:1});
                            //刷新数据表格
                            tableIns.reload();
                        }else{
                            layer.alert(result.message,{icon:2});
                        }
                    },"json");
                    layer.close(index);
                });
            }else {
                layer.msg("请选择要确认的订单信息");
            }
        }


    });
</script>

</body>
</html>
