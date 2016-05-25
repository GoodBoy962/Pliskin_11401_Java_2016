<#include "main-template.ftl"/>

<#macro m_body>

<section id="main-slider" class="carousel">
    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">


        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">${doctor.fio}</h3>
            </div>
            <div class="panel-body">
                <div class="row">
                <#--<div class="col-md-3 col-lg-3 " align="center"> <img alt="User Pic" src="http://babyinfoforyou.com/wp-content/uploads/2014/10/avatar-300x300.png" class="img-circle img-responsive"> </div>-->


                    <div class=" col-md-9 col-lg-9 ">
                        <table class="table table-user-information">
                            <tbody>
                            <tr>
                                <td>специальность</td>
                                <td>${doctor.specialization.name}</td>
                            </tr>
                            <tr>
                                <td>дата начала практики</td>
                                <td>${doctor.inceptionDate?datetime?string('dd-MM-yyyy')}</td>
                            </tr>
                            <tr>
                                <td>дата рождения</td>
                                <td>${doctor.birthDay?datetime?string('dd-MM-yyyy')}</td>
                            </tr>
                                <@security.authorize access="hasRole('ROLE_ADMIN')">
                                <tr>
                                    <td>дата устройства на работу</td>
                                    <td>${doctor.employmentDate?datetime?string('dd-MM-yyyy')}</td>
                                </tr>
                                </@security.authorize>
                            <tr>
                                <td>Email</td>
                                <td><a href="mailto:${doctor.credentials.email}">${doctor.credentials.email}</a></td>
                            </tr>
                            <tr>
                                <td>расписание</td>
                                <#if isTimetable?has_content>
                                    <td><a href="/doctors/${doctor.id}/timetable">посмотреть</a></td>
                                <#else>
                                    <td>
                                        <@security.authorize access="hasRole('ROLE_ADMIN')">
                                            <p><a href="/doctors/${doctor.id}/timetable/new">создать</a></p>
                                        </@security.authorize>
                                    </td>
                                </#if>
                            </tr>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
</section>


</#macro>

<@main title="Доктор ${doctor.fio}"/>