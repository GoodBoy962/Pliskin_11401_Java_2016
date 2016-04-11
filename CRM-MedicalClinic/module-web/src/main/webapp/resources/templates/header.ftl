<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>

<header id="header" role="banner">
    <div class="container">
        <div id="navbar" class="navbar navbar-default">
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="/"><i class="icon-home"></i></a></li>
                <#if springMacroRequestContext.getRequestUri() == "/">
                    <li class="scrollButton"><a href="#services">Services</a></li>
                    <li class="scrollButton"><a href="#about-us">About Us</a></li>
                    <li class="scrollButton"><a href="#contact">Contact</a></li>
                </#if>
                <@security.authorize access="!isAnonymous()">
                    <li><a href="/logout">Выйти</a></li>
                </@security.authorize>
                <@security.authorize access="isAnonymous()">
                    <li><a href="/registration">Зарегистрироваться</a></li>
                    <li><a href="/login" id="login">Войти</a></li>
                </@security.authorize>
                </ul>
            </div>
        </div>
    </div>
</header>