<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<head>
    <meta charset="UTF-8"/>
    <title>论坛首页</title>
    <link href="../../css/style.css" rel="stylesheet" type="text/css">
    <!--[if IE]>
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <!--[if IE 6]>
    <script src="js/belatedPNG.js"></script>
    <script>
        DD_belatedPNG.fix('*');
    </script>
    <![endif]-->
    <script src="../../js/jquery-1.4.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../js/loopedslider.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../js/jquery.js" type="text/javascript"></script>
    <script type="text/javascript" charset="utf-8">
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

        function logout() {
            localStorage.setItem("uid",0);
        }


        //翻页
        function pageSkip(index) {
            $.ajax({
                type: 'post',
                url: '/tieziServlet/tieziShow&currentPage='+index,
                success: function (data) {
                    var dataInfo = eval(data);
                    var jsonData = dataInfo[0]['pt'];
                    var pageInfo = dataInfo[0]['pageInfo'];
                    //首先清空 老数据
                    $("#tiezi").text("");
                    //普通帖子
                    for (var i = 0;i < jsonData.length;i++){
                        $("#tiezi").append("<header><h3><a href='about.jsp?id="+jsonData[i]['tid']+"'>"+jsonData[i]['title']+"</a></h3>"
                            +"<p class=\"postinfo\">"+jsonData[i]['uname']+"<time>&nbsp;&nbsp;&nbsp;&nbsp;"
                            +ChangeDateFormat(jsonData[i]['tdate'])+"</time></p></header>"
                            +"<p>"+jsonData[i]['tcontent'].substr(0,100)+"……"+"</p>"
                            +"<footer><span class=\"author\">浏览人数&nbsp;&nbsp;&nbsp;"+jsonData[i]['tnum1']+"</span>"
                            +"<span>回帖人数&nbsp;&nbsp;&nbsp;"+jsonData[i]['tnum2']+"</span></footer><div class=\"clear\"></div>"
                        )
                    }

                    $("#pageInfo").text("");
                    //需要显示当前页
                    if(pageInfo != null){
                        let pageCount = pageInfo['pageCount'];
                        for(var i=1;i<=pageCount;i++){
                            let pageone;
                            if(i == index){
                                //默认选择第一页
                                pageone = "<span class=\"current\">"+i+"</a>";;
                            }else{
                                pageone = "<a onclick=pageSkip("+i+")>"+i+"</a>";;
                            }
                            $("#pageInfo").append(pageone);
                        }

                    }

                },
                error: function () {
                    alert("失败！")
                }
            })
        }

        $(function () {
            //普通帖子
            let pageInfo = 1;
            $.ajax({
                type: 'post',
                url: '/tieziServlet/tieziShow?currentPage='+pageInfo,
                contentType: 'application/json;charset=utf-8',
                success: function (data) {
                    var dataInfo = eval(data);
                    var jsonData = dataInfo[0]['pt'];
                    var hotData = dataInfo[0]['hot'];
                    var pageInfo = dataInfo[0]['pageInfo'];
                    //普通帖子
                    for (var i = 0;i < jsonData.length;i++){
                        $("#tiezi").append("<header><h3><a href='about.jsp?id="+jsonData[i]['tid']+"'>"+jsonData[i]['title']+"</a></h3>"
                            +"<p class=\"postinfo\">"+jsonData[i]['uname']+"<time>&nbsp;&nbsp;&nbsp;&nbsp;"
                            +ChangeDateFormat(jsonData[i]['tdate'])+"</time></p></header>"
                            +"<p>"+jsonData[i]['tcontent'].substr(0,100)+"……"+"</p>"
                            +"<footer><span class=\"author\">浏览人数&nbsp;&nbsp;&nbsp;"+jsonData[i]['tnum1']+"</span>"
                            +"<span>回帖人数&nbsp;&nbsp;&nbsp;"+jsonData[i]['tnum2']+"</span></footer><div class=\"clear\"></div>"
                        )
                    }

                    //热帖
                    for (var i = 0;i < hotData.length;i++){
                        var hottitle = hotData[i]['title'];
                        if(hottitle.length>20){
                            hottitle = hottitle.substr(0,13)+"……";
                        }

                        var aa =
                            "<li><a href='about.jsp?id="+hotData[i]['tid']+"'>"+hottitle+"</a></li>";
                        $("#hotList").append(aa);
                    }

                    //分页图标展示，并显示当前是那一页
                    if(pageInfo != null){
                        let pageCount = pageInfo['pageCount'];
                        for(var i=0;i<pageCount;i++){
                            let pageone;
                            if(i==0){
                                //默认选择第一页
                                pageone = "<span class=\"current\">"+(i+1)+"</a>";;
                            }else{
                                pageone = "<a onclick=pageSkip("+(i+1)+")>"+(i+1)+"</a>";;
                            }
                            $("#pageInfo").append(pageone);
                        }

                    }

                }
            });

        });


        $(function () {
            let user = localStorage.getItem("uid");
            if(user != null && user > 0){
                //会员登录
                $("#siteinfo").text("");
                $("#siteinfo").append("<a onclick=logout() href=\"index.jsp\">注销</a> | <a href=\"contact.jsp\">发帖</a>");
                //显示回到首页
            }
        });

    </script>
</head>

<body>
<div id="bodywrap">
    <section id="pagetop">
        <p id="siteinfo">
            <a href="/login">登录</a> | <a href="register.jsp">注册</a>
        </p>
    </section>
    <header id="pageheader">
        <h1>
            Welcome BBS 论坛
        </h1>
    </header>
    <div id="contents">
        <section id="main">
            <section id="featured">
                <h2 class="ftheading">Featured</h2>
                <div class="ftwrap">
                    <div class="ftimg">
                        <img src="../../images/index.jpeg" width="204" height="128" alt="index"></div>
                    <div class="fttxt">
                        <%--<h3>Featured Content</h3>--%>
                        <p>像悲伤，像自由，像无所畏惧，像极度渴望的灵魂! 就在这里让我们相遇、相知、相识. </p>
                    </div>
                </div>
            </section>
            <div id="leftcontainer">
                <h2 class="mainheading">
                    Latest from the blog
                </h2>

                <article class="post" id="tiezi">
                    <br>
                    <div class="clear"></div>
                </article>

                <!-- 页数，这里后面是从后台来获取-->
                <div class="wp-pagenavi" id="pageInfo">
                </div>
                <div class="clear"></div>
            </div>
        </section>

        <!-- 右边论坛帖子加精 -->
        <section id="sidebar">
            <div id="sidebarwrap">
                <h2>最热资讯</h2>
                <p>“青眼白龙”游戏王卡牌遭法拍：起拍价 80 元 市场价数十万...
                    <a target="_blank" href="http://www.pcbeta.com/viewnews-80919-1.html">Read More</a></p>
                <!-- 这里是加精的帖子，是管理员来分配的 -->
                <h2>热点</h2>
                <ul id="hotList">
                </ul>
            </div>
        </section>


        <div class="clear"></div>
    </div>

</div>
<footer id="pagefooter">
    <div id="footerwrap">
        <div class="copyright">
            2021 &copy; Z121 的论坛
        </div>
    </div>
</footer>
</body>
</html>
