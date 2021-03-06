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
                                Создание расписания для<br/>
                                ${doctor.fio}
                            </h2>
                        </div>
                        <form action="/doctors/${doctor.id}/timetable" method="post">
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
                                                <td data-title="${w_day}"><input type="checkbox" name="${w_day}-${time}"></td>
                                            </#list>
                                        </tr>
                                        </#list>
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-md-12 col-lg-6"><button class="btn btn-success" type="submit">Создать</div>
                        </form>
                    </div>
                </div>
            </div>
</section>

</#macro>

<@main title="Создание расписания для ${doctor.fio}"/>