<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>

<header id="header" role="banner">
    <div class="container">
        <div id="navbar" class="navbar navbar-default">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-medkit"></span>
                    <span class="icon-medkit"></span>
                    <span class="icon-medkit"></span>
                </button>
                <a class="navbar-brand" href="/"></a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="/"><i class="icon-home"></i></a></li>
                <@security.authorize access="!isAnonymous()">
                    <li><a href="/logout">Выйти</a></li>
                </@security.authorize>
                <@security.authorize access="isAnonymous()">
                    <li><a href="/registration">Зарегистрироваться</a></li>
                    <li><li><a href="/login" id="login">Войти</a></li></li>
                </@security.authorize>
                    <li class="scrollButton"><a href="#services">Services</a></li>
                    <li class="scrollButton"><a href="#portfolio">Portfolio</a></li>
                    <li class="scrollButton"><a href="#about-us">About Us</a></li>
                    <li class="scrollButton"><a href="#contact">Contact</a></li>
                </ul>
            </div>
        </div>
    </div>
</header><!--/#header-->