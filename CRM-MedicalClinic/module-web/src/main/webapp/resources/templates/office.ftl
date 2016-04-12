<#include "main-template.ftl"/>

<#macro m_body>

<section id="main-slider" class="carousel">
    <div class="container">
        <div class="box">
            <div class="center">
                <h2>${office.medicalClinic.name}</h2>
                <h3>${office.address}</h3>
            </div>
            <br/>
            <#list doctors as d>
                <a href="/doctors/${d.id}">${d.fio}</a>
            </#list>
        </div>
    </div>
</section>

</#macro>

<@main title="${office.medicalClinic.name}"/>