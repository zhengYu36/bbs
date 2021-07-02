<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@ include file="jstl.jsp"%>
<head>
    <meta charset="UTF-8"/>
    <title>发帖</title>
    <link href="../../css/style.css" rel="stylesheet" type="text/css">
    <!--[if IE]>
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <!--[if IE 6]>
    <script src="../../js/belatedPNG.js"></script>
    <script>
        DD_belatedPNG.fix('*');
    </script>
    <![endif]-->
    <script src="../../js/jquery-1.4.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../js/loopedslider.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../js/jquery.js" type="text/javascript"></script>
    <script type="text/javascript" charset="utf-8">
        function fatieSubmit() {
            $('#mySubmit').submit();
        }

    </script>
</head>

<body>
<div id="bodywrap">
    <section id="pagetop">
        <div id="siteinfoDev">
        <p id="siteinfo">
            <%--<a href="login.jsp">登录</a> | <a href="register.jsp">注册</a>--%>
        </p>
        </div>

        <p></p>
        <div class="clear"></div>
        <div id="indexInfoDev">
            <p id="indexInfo">
                <a href="/index">首页</a>
            </p>
        </div>
    </section>
    <header id="pageheader">
        <h1>
            发帖
        </h1>
    </header>
    <div id="contents">
        <section id="main">
            <div id="leftcontainer">
                <h2>发帖人</h2>

                <article class="post">

                    <form class="form" action="${ctx }/tieziServlet/fatie"  method="post" id="mySubmit">
                        <p class="textfield">
                            <label for="author">
                                <small>标题（你要发个什么帖子呢？）</small>
                            </label>
                        <div class="clear"></div>
                        <input name="title" id="author" value="" size="22" tabindex="1" type="text">
                        </p>
                        <p>
                            <small><strong>内容</strong> 你想写些什么呢？
                            </small>
                        </p>
                        <p class="text-area">
                            <textarea name="tcontent" id="comment" cols="50" rows="10" tabindex="4"></textarea>
                        </p>
                        <p>
                            <input type="button" value="提交" class="submit" onclick="javascript:fatieSubmit()">
                            <input name="comment_post_ID" value="1" type="hidden">
                        </p>
                        <div class="clear"></div>
                    </form>

                    <!--Important-->
                    <div class="clear"></div>
                </article>
            </div>
        </section>
        <section id="sidebar">
            <div id="sidebarwrap">

                <!-- 发帖不需要热帖 -->
               <%-- <h2>Categories</h2>
                <ul>
                    <li><a href="#">Web Design</a>(4)</li>
                </ul>--%>

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
