<#include "main-template.ftl"/>
<#macro m_body>

<section id="main-slider" class="carousel">
    <div class="container">
        <div class="col-md-10 col-lg-offset-2 text-center">
            <div class="col-md-4 col-md-offset-4">
                <h3>Регистрация клиентов/пациентов сервиса</h3>
                <@sf.form action="/registration" method="post" modelAttribute="userform">
                    <div class="form-group">
                        <@sf.input path="name" id="name" cssClass="form-control" placeholder="Имя"/><br/>
                        <@sf.errors path="name"/><br/>
                    </div>
                    <div class="form-group">
                        <@sf.input path="surname" id="surname" cssClass="form-control" placeholder="Фамилия"/><br/>
                        <@sf.errors path="surname"/><br/>
                    </div>
                    <div class="form-group">
                        <@sf.input path="lastname" id="lasname" cssClass="form-control" placeholder="Отчество"/><br/>
                        <@sf.errors path="lastname"/><br/>
                    </div>
                    <div class="form-group">
                        <label for="username">Логин</label>
                        <@sf.input path="login" id="login" cssClass="form-control" placeholder="Логин"/>
                        <@sf.errors path="login"/>
                    </div>
                    <div class="form-group">
                        Дата рождения<br/>
                        <@sf.input type="date" path="birthDay" id="birthDay" cssClass="form-control" placeholder="дата рождения"/><br/>
                        <@sf.errors path="birthDay"/><br/>
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <@sf.input path="email" id="email" cssClass="form-control" placeholder="Email"/>
                        <@sf.errors path="email"/>
                    </div>
                    <div class="form-group">
                        <label for="password">Пароль</label>
                        <@sf.input path="password" id="password" cssClass="form-control" placeholder="Пароль"/>
                        <@sf.errors path="password"/>
                    </div>
                    <div class="form-group">
                        <label for="repassword">Подтверждение пароля</label>
                        <@sf.input path="repassword" id="repassword" cssClass="form-control" placeholder="Повторите пароль"/>
                        <@sf.errors path="repassword"/>
                    </div>
                    <button type="submit" class="btn btn-success">Присоединиться</button>
                </@sf.form>
            </div>
        </div>
    </div>
</section>

</#macro>
<@main title="Регистрация"/>