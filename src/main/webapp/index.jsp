<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<head>
    <meta charset="UTF-8"/>
    <title>论坛首页</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <!--[if IE]>
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <!--[if IE 6]>
    <script src="js/belatedPNG.js"></script>
    <script>
        DD_belatedPNG.fix('*');
    </script>
    <![endif]-->
    <script src="js/jquery-1.4.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/loopedslider.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/jquery.js" type="text/javascript"></script>
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
        $(function () {
            //普通帖子
            let pageInfo = 1;
            $.ajax({
                type: 'post',
                url: '/tieziServlet?method=tieziShow&currentPage='+pageInfo,
                contentType: 'application/json;charset=utf-8',
                success: function (data) {
                    var dataInfo = eval(data);
                    var jsonData = dataInfo[0]['pt'];
                    var hotData = dataInfo[0]['hot'];
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
                        var aa = "<li><a href='about.jsp?id="+hotData[i]['tid']+"'>"+hotData[i]['title']+"</a></li>";
                        $("#hotList").append(aa);
                    }
                }
            });
        });
    </script>
</head>

<body>
<div id="bodywrap">
    <section id="pagetop">
        <p id="siteinfo">
            <a href="login.jsp">登录</a> | <a href="register.jsp">注册</a>
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
                        <img src="images/img3.jpg" width="204" height="128" alt="img3"></div>
                    <div class="fttxt">
                        <h3>Featured Content</h3>
                        <p>Lorema psum dolor sit amet,
                            consectetur adipiscing elit.
                            Integer egestas purus bibendum
                            neque aliquam ut posuere elit semper. Fusce sagittis pharetra eros, sit amet consequat sem
                            mollis vitae. </p>
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
                <div class="wp-pagenavi">
                    <span class="current">1</span><a href="/page/2/" title="2">2</a><a href="/page/3/" title="3">3</a><a
                        href="/page/4/" title="4">4</a><a href="/page/5/" title="5">5</a><a href="/page/6/"
                                                                                            title="6">6</a><a
                        href="/page/7/" title="7">7</a><a href="/page/8/" title="8">8</a><a href="/page/2/">Next
                    &raquo;</a><span class="extend">...</span><a href="/page/27/" title="Last &raquo;">Last &raquo;</a>
                </div>
                <div class="clear"></div>
            </div>
        </section>

        <!-- 右边论坛帖子加精 -->
        <section id="sidebar">
            <div id="sidebarwrap">
                <h2>About SilverBlog</h2>
                <p>SilverBlog is a free CSS Template released under GNU GPL license. You are free to use / modify it in
                    any way you want without any restrictions. A link back to this website will always be appreciated.
                    <a href="#">Read More</a></p>

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
            2018 &copy; chenjiale的论坛
        </div>
    </div>
</footer>
</body>
</html>
