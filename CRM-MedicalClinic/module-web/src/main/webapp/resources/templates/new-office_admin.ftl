<#include "main-template.ftl"/>

<#macro m_body>

<section id="main-slider" class="carousel">
    <div class="col-md-5 col-lg-offset-4 text-center">
        <@sf.form action="/system/medical_clinics/${id}/offices" method="post" class="form-horizontal text-center" modelAttribute="office_admin_form">
            <div class="form-group text-center">
                <div class="col-md-12">
                    <@sf.input path="address" id="address" class="form-control" placeholder="Адрес офиса"/><br/>
                    <@sf.errors path="address" class="alert alert-danger form-control"/>
                </div>
            </div>
            <div class="form-group text text-center">
                <div class="col-md-12">
                    данные администратора нового офиса
                </div>
            </div>
            <br/>
            <div class="form-group text-center">
                <div class="col-md-12">
                    <@sf.input path="name" id="name" class="form-control" placeholder="Имя"/>
                        <@sf.errors path="name" class="alert alert-danger form-control"/>
                </div>
            </div>
            <div class="form-group text-center">
                <div class="col-md-12">
                    <@sf.input path="surname" id="surname" class="form-control" placeholder="Фамилия"/>
                        <@sf.errors path="surname" class="alert alert-danger form-control"/>
                </div>
            </div>
            <div class="form-group text-center">
                <div class="col-md-12">
                    <@sf.input path="lastname" id="lasname" class="form-control" placeholder="Отчество"/>
                        <@sf.errors path="lastname" class="alert alert-danger form-control"/>
                </div>
            </div>
            <div class="form-group text-center">
                <div class="col-md-12">
                    <@sf.input path="login" id="login" class="form-control" placeholder="Логин"/>
                        <@sf.errors path="login" class="alert alert-danger form-control"/>
                </div>
            </div>
            <div class="form-group text-center">
                <div class="col-md-12">
                    <@sf.input path="email" id="email" class="form-control" placeholder="email"/><br/>
                    <@sf.errors path="email" class="alert alert-danger form-control"/>
                </div>
            </div>
            <div class="form-group text-center">
                <div class="col-md-12">
                    <@sf.input path="password" id="password" class="form-control" placeholder="пароль"/><br/>
                    <@sf.errors path="password" class="alert alert-danger form-control"/>
                </div>
            </div>
            <div class="form-group text-center">
                <div class="col-md-7">
                    <button type="submit" class="btn btn-success">Добавить</button>
                </div>
            </div>
        </@sf.form>
    </div>


    <div class="form-group text-center">
        <div class="col-md-7"><@sf.input path="name" type="text" class="form-control" placeholder="название"/>
            <br/>
            <@sf.errors path="name"/>
        </div>
    </div>


</section>


</#macro>

<@main title="Содание офиса и администратора офиса"/>