<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>

<header id="header" role="banner">
    <div class="container">
        <div id="navbar" class="navbar navbar-default">
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="/"><i class="icon-home"></i></a></li>
                <#if springMacroRequestContext.getRequestUri() == "/">
                    <@security.authorize access="isAnonymous()">
                        <li class="scrollButton"><a href="#services">Предоставляемые услуги</a></li>
                        <li class="scrollButton"><a href="#contact">Контакты</a></li>
                    </@security.authorize>
                </#if>
                    <li><a href="/medical_clinics">Медицинские клиники</a></li>
                <@security.authorize access="!isAnonymous()">
                    <li><a href="/default">Профиль</a></li>
                </@security.authorize>
                <@security.authorize access="hasRole('ROLE_DOCTOR')">
                    <li><a href="/doctor/appointments">Приём пациентов</a></li>
                </@security.authorize>
                <@security.authorize access="isAnonymous()">
                    <li><a href="/registration">Зарегистрироваться</a></li>
                    <li><a href="/login" id="login">Войти</a></li>
                </@security.authorize>
                <@security.authorize access="hasRole('ROLE_SYSTEM_ADMIN')">
                    <li><a href="/system/medical_clinics/new">Добавить Мед клинику</a></li>
                </@security.authorize>
                <@security.authorize access="hasRole('ROLE_ADMIN')">
                    <li><a href="/admin/doctors/new">Добавить врача</a></li>
                    <#if springMacroRequestContext.getRequestUri() == "/admin">
                        <li><a href="/medical_clinics/${id}/offices/${officeId}/doctors">Посмотреть своих врачей</a>
                        </li><!--TODO-->
                    </#if>
                </@security.authorize>
                <@security.authorize access="hasRole('ROLE_PATIENT')">
                    <li><a href="/appointment">История посещений</a></li>
                    <li><a href="/appointment/new">Записаться на приём</a></li>
                </@security.authorize>
                <@security.authorize access="!isAnonymous()">
                    <li><a href="/logout">Выйти</a></li>
                </@security.authorize>
                </ul>
            </div>
        </div>
    </div>
</header>