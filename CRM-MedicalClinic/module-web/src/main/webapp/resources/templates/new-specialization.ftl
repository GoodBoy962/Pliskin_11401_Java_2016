<#include "main-template.ftl"/>

<#macro m_body>

<section id="main-slider" class="carousel">
    <div class="container">
        <div class="col-md-10 col-lg-offset-2 text-center">
            <@sf.form action="/system/specializations" method="post" modelAttribute="specializationForm" class="form-horizontal text-center">
            <div class="form-group text-center">
                <div class="col-md-8">
                    <@sf.input path="name" id="name" placeholder="название"/><br/>
                    <@sf.errors path="name"/><br/>
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

<@main title="Создание специализации"/>