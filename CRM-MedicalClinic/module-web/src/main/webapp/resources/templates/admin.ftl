<#include "main-template.ftl"/>

<#macro m_body>

<section id="main-slider" class="carousel">
    <div class="container">
        <div class="col-md-4 text text-center">
            <h1 class="col-md-5 text text-center">
                Администратор офиса
            </h1>
        </div>
    </div>
    <div class="container">
        <div class="col-md-5 text text-center">
            <h3 class="col-md-5 text text-center">
                ${admin.fio}
            </h3>
        </div>
    </div>
</section>


</#macro>

<@main title="Администратор офиса"/>