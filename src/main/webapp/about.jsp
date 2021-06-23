<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<head>
    <meta charset="UTF-8"/>
    <title>回帖</title>
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

        function GetQueryString(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]);
            return null;
        }

        //回帖
        function huitieSubmit() {
            var comment = $('#comment').val();
            var pid = $('#pidInfo').text();
            $.ajax({
                type: 'post',
                url: '/tieziServlet?method=huitie',
                data: {comment: comment, pid: pid},
                success: function () {
                    alert("回帖成功！");
                    //刷新自己
                    location.href = "/about.jsp";
                },
                error: function () {
                    alert("失败！")
                }
            })
        }

        $.ajax({
            type: 'get',
            url: '/tieziServlet',
            data: {id: GetQueryString("id")},
            contentType: 'application/json;charset=utf-8',
            success: function (data) {
                var jsonData = eval(data);
                console.log(jsonData);
                $("#title").append(jsonData[0]['title']);
                $("#pidInfo").append(jsonData[0]['uid']);
                $("#leftcontainer").append("<h2 class=\"mainheading\"> " + jsonData[0]['uid'] + "</h2>"
                    + "<article class=\"post\">" + jsonData[0]['tcontent'] + "</article>");

                //这里是回帖信息
                var reply = jsonData[0]['replytiezis'];
                if (reply != null) {
                    for (var i = 0; i < reply.length; i++) {
                        var info =
                            "<div class=\"avatar\"><img src=\"http://1.gravatar.com/avatar/ad516503a11cd5ca435acc9bb6523536?s=38\">" +
                            " <p class=\"author\">" +
                            " <span class=\"name\">" + reply[i]['uid'] +
                            "</span> <time class=\"date\">" + ChangeDateFormat(reply[i]['tdate']) +
                            "</time></p></div> <div class=\"comment\"><p>" + reply[i]['tcontent'] +
                            "</p></div><div class=\"clear\"></div>";
                        $("#replyList").append(info);
                    }
                }
            },
            error: function () {
                alert("请转到首页打开一个帖子！")
            }
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
        <h1 id="title">

        </h1>
    </header>
    <div id="contents">
        <section id="main">
            <div id="leftcontainer">
                <div class="clear"></div>
            </div>

            <%--回复列表--%>
            <div id="commentlist">
                <article class="entry" id="replyList">

                </article>
            </div>


            <%--回贴--%>
            <h3 id="respond">Leave a Reply</h3>
            <form action="#" method="post" id="commentform">
                <p>
                    <small><strong>回复:</strong>写些你想写的！</small>
                </p>
                <p class="text-area">
                    <!-- 隐藏uid 的值 -->
                <div hidden id="pidInfo"></div>
                <textarea name="comment" id="comment" cols="50" rows="10" tabindex="0">
       </textarea>
                </p>
                <p>
                    <input type="button" id="submit" value="提交" class="submit" onclick="huitieSubmit()">
                    <input name="comment_post_ID" value="1" type="hidden">
                </p>
                <div class="clear"></div>
            </form>

        </section>

        <section id="sidebar">
            <div id="sidebarwrap">
                <h2>热点</h2>
                <ul>
                    <li><a href="#">Web Design</a>(4)</li>
                    <li><a href="#">Graphics Design</a>(8)</li>
                    <li><a href="#">Computers</a>(12)</li>
                    <li><a href="#">Typography</a>(3)</li>
                    <li><a href="#">Photogrphy</a>(4)</li>
                    <li><a href="#">Mathematics</a>(5)</li>
                    <li><a href="#">General News</a>(24)</li>
                    <li><a href="#">Music and Entertainment</a>(1)</li>
                </ul>
            </div>
        </section>


        <div class="clear"></div>
    </div>

</div>
<footer id="pagefooter">
    <div id="footerwrap">
        <div class="copyright">
            Z121 的论坛
        </div>
    </div>
</footer>
</body>
</html>
