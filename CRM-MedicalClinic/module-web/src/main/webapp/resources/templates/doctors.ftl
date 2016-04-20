<#include "main-template.ftl"/>

<#macro m_body>

<section id="main-slider" class="carousel">
    <div class="container">
        <div class="box">
            <#list doctors as d>
                <div class="carousel-inner">
                    <div class="item active">
                        <div class="row col-md-9 col-lg-6 text text-left">
                            <p><a href="/doctors/${d.id}">${d.fio}</a></p><br/>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</section>

</#macro>

<@main title="Доктора моего офиса"/>