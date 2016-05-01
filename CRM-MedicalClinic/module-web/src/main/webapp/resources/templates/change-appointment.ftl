<#include "main-template.ftl"/>

<#macro m_body>

<section id="main-slider" class="carousel">
    <div class="container">
        <div class="col-md-5 col-lg-offset-5 text-center">
            <@sf.form method="post" action="/doctor/appointments/${appointmentId}/change" class="form-horizontal text-center" modelAttribute="changeForm">
                <div class="form-group text-center">
                    <div class="col-md-9">
                        <@sf.input path="patient" class="form-control" placeholder="пациент"/>
                        <br/>
                        <@sf.errors path="patient"/>
                    </div>
                </div>
                <div class="form-group text-center">
                    <div class="col-md-9">
                        <@sf.textarea path="description" class="form-control" placeholder="описание/рецепт"/>
                        <br/>
                        <@sf.errors path="description"/>
                    </div>
                </div>
                <div class="form-group text-center">
                    <div class="col-md-9">
                        <@sf.input path="time" class="form-control" placeholder="время"/>
                        <br/>
                        <@sf.errors path="time"/>
                    </div>
                </div>
                <div class="form-group text-center">
                    <div class="col-md-9">
                        <@sf.input path="cost" class="form-control" placeholder="цена"/>
                        <br/>
                        <@sf.errors path="cost"/>
                    </div>
                </div>
                <div class="form-group text-center">
                    <div class="col-md-5">
                        <button type="submit" class="btn btn-success">Сохранить</button>
                    </div>
                </div>
            </@sf.form>
        </div>

    </div>
</section>


</#macro>

<@main title="Приём"/>