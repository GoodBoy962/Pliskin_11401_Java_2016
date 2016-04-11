<#include "main-template.ftl"/>

<#macro m_body>

<section id="main-slider" class="carousel">
    <div class="col-md-5 col-lg-offset-5 text-center">
        <@sf.form method="post" action="/system/medical_clinics" class="form-horizontal text-center" modelAttribute="mc_new_form">
            <div class="form-group text-center">
                <div class="col-md-5"><@sf.input path="name" type="text" class="form-control" placeholder="название"/>
                    <br/>
                    <@sf.errors path="name"/>
                </div>
            </div>
            <div class="form-group text-center">
                <div class="col-md-5"><@sf.input path="info" type="text" class="form-control" placeholder="описание"/>
                    <br/>
                    <@sf.errors path="info"/>
                </div>
            </div>
            <div class="form-group text-center">
                <div class="col-md-5">
                    <button type="submit" class="btn btn-success">Создать</button>
                </div>
            </div>
        </@sf.form>
    </div>

    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
</section>


</#macro>

<@main title="Создание Мед клиники"/>
