<#include "main-template.ftl"/>

<#macro m_body>

<section id="main-slider" class="carousel">
    <div class="container">
        <div class="box">
            <h3 class="page-header">Специализации</h3>
            <hr>
            <div class="row">
                <div id="team" class="list-table-container">
                    <table id="team-table" class="list-table">
                        <tbody>
                            <#list specializations as s>
                            <tr>
                                <td class="large">${s.name}</td>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>
            </div>
            <div><a href="/system/specialization/new">Создать новую</a></div>
        </div>
    </div>
</section>

</#macro>

<@main title="Cпециализации"/>