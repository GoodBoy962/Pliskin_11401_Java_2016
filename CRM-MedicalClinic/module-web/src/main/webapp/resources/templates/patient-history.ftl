<#include "main-template.ftl"/>

<#macro m_body>

<section id="main-slider" class="carousel">
    <div class="container">
        <div class="box">
            <h3 class="page-header">История посещений</h3>
            <hr>
            <div class="row">
                <div id="team" class="list-table-container">
                    <table id="team-table" class="list-table">
                        <thead>
                        <tr>
                            <td>Доктор</td>
                            <td>Пациент</td>
                            <td>Дата</td>
                            <td>Время</td>
                            <td>Цена</td>
                            <td>Разъяснение</td>
                        </tr>
                        </thead>
                        <tbody>
                            <#list appointments as a>
                                <#if a.status>
                                <tr id="ember709" class="ember-view">
                                    <td class="middle"><a
                                            href="/doctors/${a.doctorSchedule.doctor.id}">${a.doctorSchedule.doctor.fio}</a>
                                    </td>
                                    <td class="large"><a href="/patients/${a.patient.id}">${a.patient.fio}</a></td>
                                    <td class="light">${a.date?string("dd-MM-yyyy")}</td>
                                    <td class="light">${a.doctorSchedule.startTime}</td>
                                    <td class="light">
                                        <#if a.cost??>
                                        ${a.cost}
                                    </#if>
                                    </td>
                                    <td class="large">
                                        <#if a.description??>
                                        ${a.description}
                                    </#if>
                                    </td>
                                </tr>
                                </#if>
                            </#list>

                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    </div>
</section>

</#macro>

<@main title="История пациента"/>