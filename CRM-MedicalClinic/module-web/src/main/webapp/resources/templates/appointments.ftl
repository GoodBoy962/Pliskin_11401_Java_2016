<#include "main-template.ftl"/>

<#macro m_body>

<section id="main-slider" class="carousel">
    <div class="container">
        <div class="box">
            <#list appointments as a>
            ${a.date}
            <#--${a.doctor.fio}-->
            ${a.patient.fio}
            ${a.status?string('was', 'wasnt')}
            <#--${a.cost}-->
            </#list>
        </div>
    </div>
</section>

</#macro>

<@main title="Доктора моего офиса"/>