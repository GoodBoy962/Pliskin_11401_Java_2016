<#include "main-template.ftl"/>

<#macro m_body>

<section id="main-slider" class="carousel">
    <div class="container">
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">
            <div class="panel-body">
                <div class="row">
                    <div class="row">
                        <div class="col-md-12">
                            <h2 class="text-center">
                                Расписание ${doctor.fio}
                            </h2>
                        </div>
                        <div id="no-more-tables">
                            <table class="col-sm-12 table-bordered table-striped table-condensed cf">
                                <thead class="cf">
                                <tr>
                                    <th></th>
                                    <th>Понедельник</th>
                                    <th>Вторник</th>
                                    <th>Среда</th>
                                    <th>Четверг</th>
                                    <th>Пятница</th>
                                    <th>Суббота</th>
                                    <th>Воскресенье</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#list ["07:00 AM", "08:00 AM", "09:00 AM", "10:00 AM", "11:00 AM", "12:00 AM", "01:00 PM", "02:00 PM", "03:00 PM", "04:00 PM", "05:00 PM", "06:00 PM", "07:00 PM", "08:00 PM"] as time>
                                    <tr>
                                        <td data-title="      ">${time}</td>
                                        <#list ["MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"] as w_day>
                                            <#if timetable??>
                                                <#list timetable as t>
                                                    <#assign flag = 0>
                                                    <#if t.weekDay == w_day && (t.startTime?string("hh:mm a") == time || t.startTime?string("hh:mm a") == "12:00 PM" && time =="12:00 AM")>
                                                        <@security.authorize access="hasRole('ROLE_PATIENT')">
                                                            <td data-title="${w_day}"><a href="/appointment/new?w_day=${w_day}&time=${time}&doctor_id=${doctor.id}">записаться</a></td>
                                                        </@security.authorize>
                                                        <@security.authorize access="isAnonymous()">
                                                            <td data-title="${w_day}"><a href="/login">записаться</a>
                                                            </td>
                                                        </@security.authorize>
                                                        <@security.authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SYSTEM_ADMIN', 'ROLE_DOCTOR')">
                                                            <td data-title="${w_day}">+</td>
                                                        </@security.authorize>
                                                        <#assign flag = 1>
                                                        <#break>
                                                    </#if>
                                                </#list>
                                                <#if flag == 0>
                                                    <td data-title="${w_day}"></td>
                                                </#if>
                                            </#if>
                                        </#list>
                                    </tr>
                                    </#list>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
</section>

</#macro>

<@main title="Доктор ${doctor.fio} расписание"/>