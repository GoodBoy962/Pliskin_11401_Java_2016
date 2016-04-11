<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>

<header id="header" role="banner">
    <div class="container">
        <div id="navbar" class="navbar navbar-default">
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="/"><i class="icon-home"></i></a></li>
                <#if springMacroRequestContext.getRequestUri() == "/">
                    <li class="scrollButton"><a href="#services">Предоставляемые услуги</a></li>
                    <li class="scrollButton"><a href="#contact">Контакты</a></li>
                </#if>
                    <li><a href="/medical_clinics">Медицинские клиники</a></li>
                <@security.authorize access="!isAnonymous()">
                    <li><a href="/default">Профиль</a></li>
                    <li><a href="/logout">Выйти</a></li>
                </@security.authorize>
                <@security.authorize access="isAnonymous()">
                    <li><a href="/registration">Зарегистрироваться</a></li>
                    <li><a href="/login" id="login">Войти</a></li>
                </@security.authorize>
                <@security.authorize access="hasRole('ROLE_SYSTEM_ADMIN')">
                    <li><a href="/system/medical_clinics/new">Добавить Мед клинику</a></li>
                </@security.authorize>

                </ul>
            </div>
        </div>
    </div>
</header>