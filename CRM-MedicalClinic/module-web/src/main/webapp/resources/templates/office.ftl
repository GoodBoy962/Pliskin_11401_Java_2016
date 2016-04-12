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
            <#--<#list offs as of>-->
                <#--<div class="carousel-inner">-->
                    <#--<div class="item active">-->
                        <#--<div class="row col-md-9 col-lg-6 text text-left">-->
                            <#--<p><a href="/medical_clinics/${mc.id}/offices/${of.id}">${of.address}</a></p>-->
                        <#--&lt;#&ndash;<p>${of.address}</p>&ndash;&gt;-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->
            <#--</#list>-->
            <#--<@security.authorize access="hasRole('ROLE_SYSTEM_ADMIN')">-->
                <#--<a href="/system/medical_clinics/${mc.id}/offices/new">добавить офис и администратора офиса</a> <br/>-->
            <#--</@security.authorize>-->
        </div>
    </div>
</section>

</#macro>

<@main title="${office.medicalClinic.name}"/>