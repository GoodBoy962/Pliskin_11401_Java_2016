<#include "main-template.ftl"/>

<#macro m_body>

<section id="main-slider" class="carousel">
    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">


        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">${admin.fio}</h3>
            </div>
            <div class="panel-body">
                <div class="row">
                <#--<div class="col-md-3 col-lg-3 " align="center"> <img alt="User Pic" src="http://babyinfoforyou.com/wp-content/uploads/2014/10/avatar-300x300.png" class="img-circle img-responsive"> </div>-->

                    <div class=" col-md-9 col-lg-9 ">
                        <table class="table table-user-information">
                            <tbody>
                            <tr>
                                <td>Email</td>
                                <td><a href="mailto:${admin.credentials.email}">${admin.credentials.email}</a></td>
                            </tr>
                            <tr>
                                <td>офис</td>
                                <td><a href="/offices/${admin.office.id}">${admin.office.address}</a></td>
                            </tr>
                            <tr>
                                <td>город</td>
                                <td>${admin.office.city}</td>
                            </tr>
                            <tbody>
                        </table>
                    </div>
                </div>
            </div>
</section>


</#macro>

<@main title="Администратор офиса"/>