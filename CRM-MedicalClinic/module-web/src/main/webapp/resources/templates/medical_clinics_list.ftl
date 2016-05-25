<#include "main-template.ftl"/>

<#macro m_body>


<section id="main-slider" class="carousel">
    <div class="container">
        <div class="box">
            <div class="center">
                <h2>Медицинские клиники</h2>
                <p class="lead">Выберите любую клинику системы<br></p>
            </div>
            <#list medical_clinics as mc>
                <div class="carousel-inner">
                    <div class="item active">
                        <div class="row col-md-9 col-lg-6 text text-left">
                            <p><a href="/medical_clinics/${mc.id}">${mc.name}</a></p>
                            <p>${mc.info}</p>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</section>


</#macro>

<@main title="Медицинские клиники"/>