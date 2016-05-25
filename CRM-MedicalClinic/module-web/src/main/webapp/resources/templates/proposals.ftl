<#include "main-template.ftl"/>

<#macro m_body>

<section id="main-slider" class="carousel">
    <div class="container">
        <div class="box">
            <h3 class="page-header">Заявки</h3>
            <hr>
            <div class="row">
                <div id="team" class="list-table-container">
                    <table id="team-table" class="list-table">
                        <thead>
                        <tr>
                            <td>Фио</td>
                            <td>Email</td>
                            <td>Сообщение</td>
                        </tr>
                        </thead>
                        <tbody>
                            <#list proposals as p>
                            <tr id="ember709" class="ember-view">
                                <td class="light">${p.fio}</td>
                                <td class="light">${p.email}</td>
                                <td class="light">${p.message}</td>
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

<@main title="Заявки"/>