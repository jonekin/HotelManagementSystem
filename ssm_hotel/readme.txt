为了保证数据库中的创建时间createDate与本地时间一致：

方法1：修改数据库的链接url   --->    serverTimezone=GMT%2b8或serverTimezone=Asia/Shanghai
    修改前：jdbc.url = jdbc:mysql://localhost:3306/db_hotel?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC
    修改后：jdbc.url = jdbc:mysql://localhost:3306/db_hotel?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2b8

方法2：修改对应的mapper映射文件   --->    createDate赋值不用#{createDate}，用now()代替
    修改前：
    <insert id="addDept">
            INSERT INTO sys_dept(deptname,address,createDate,remark)
            VALUES(#{deptName},#{address},#{createDate},#{remark})
    </insert>
    修改后：
    <insert id="addDept">
            INSERT INTO sys_dept(deptname,address,createDate,remark)
            VALUES(#{deptName},#{address},now(),#{remark})
    </insert>