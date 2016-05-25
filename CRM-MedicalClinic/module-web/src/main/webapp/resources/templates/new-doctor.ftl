<#include "main-template.ftl"/>

<#macro m_body>

<section id="main-slider" class="carousel">
    <div class="container">
        <div class="col-md-10 col-lg-offset-2 text-center">
            <@sf.form action="/admin/doctors" method="post" modelAttribute="doctor_form" class="form-horizontal text-center">
                <div class="form-group text-center">
                    <div class="col-md-8">
                        <@sf.input path="name" id="name" placeholder="Имя"/><br/>
                        <@sf.errors path="name"/><br/>
                    </div>
                </div>
                <div class="form-group text-center">
                    <div class="col-md-8">
                        <@sf.input path="surname" id="surname" placeholder="Фамилия"/><br/>
                        <@sf.errors path="surname"/><br/>
                    </div>
                </div>
                <div class="form-group text-center">
                    <div class="col-md-8">
                        <@sf.input path="lastname" id="lasname" placeholder="Отчество"/><br/>
                        <@sf.errors path="lastname"/><br/>
                    </div>
                </div>
                <div class="form-group text-center">
                    <div class="col-md-8">
                        <@sf.input path="login" id="login" placeholder="Логин"/><br/>
                        <@sf.errors path="login"/><br/>
                    </div>
                </div>
                <div class="form-group text-center">
                    <div class="col-md-8">
                        <@sf.input path="email" id="email" placeholder="email"/><br/>
                        <@sf.errors path="email"/><br/>
                    </div>
                </div>
                <div class="form-group text-center">
                    <div class="col-md-8">
                        <@sf.input path="password" id="password" placeholder="пароль"/><br/>
                        <@sf.errors path="password"/><br/>
                    </div>
                </div>
                <div class="form-group text-center">
                    <div class="col-md-8">
                        Дата рождения<br/>
                        <@sf.input type="date" path="birthDay" id="birthDay" placeholder="дата рождения"/><br/>
                        <@sf.errors path="birthDay"/><br/>
                    </div>
                </div>
                <div class="form-group text-center">
                    <div class="col-md-8">
                        Дата начала практики<br/>
                        <@sf.input type="date" path="inceptionDate" id="inceptionDate" placeholder="дата начала проктики"/><br/>
                        <@sf.errors path="inceptionDate"/><br/>
                    </div>
                </div>
                <div class="form-group text-center">
                    <div class="col-md-8">
                        Дата приёма на работу<br/>
                        <@sf.input type="date" path="employmentDate" id="employmentDate" placeholder="дата приёма на роботу"/><br/>
                        <@sf.errors path="employmentDate"/><br/>
                    </div>
                </div>
                <div class="form-group text-center">
                    <div class="col-md-8">
                        <@sf.select path="specialization" id="specialization" placeholder="специализация"><br/>
                            <#list specializations as sp>
                                <@sf.option value="${sp.name}"/>
                            </#list>
                        </@sf.select>
                        <@sf.errors  path="specialization"/><br/>
                    </div>
                </div>
                <div class="form-group text-center">
                    <div class="col-md-8">
                        <button type="submit" class="btn btn-success">Создать</button>
                    </div>
                </div>
            </@sf.form>
        </div>
    </div>
</section>

</#macro>

<@main title="Создание нового врача"/>