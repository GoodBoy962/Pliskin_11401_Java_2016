<#include "main-template.ftl"/>

<#macro m_body>

<section id="main-slider" class="carousel">
    <div class="container">
        <div class="col-md-10 col-lg-offset-2 text-center">
            <@sf.form action="/appointment" method="post" modelAttribute="appointment_form" class="form-horizontal text-center">
                <div class="form-group text-center">
                    <div class="col-md-8">
                        <@sf.input path="weekDay" id="weekDay" placeholder="День недели" value="${w_day}" disabled=true/>
                        <br/>
                        <@sf.errors path="weekDay"/><br/>
                    </div>
                </div>
                <div class="form-group text-center">
                    <div class="col-md-8">
                        <@sf.input path="time" id="time" placeholder="Время" value="${time}" disabled=true/><br/>
                        <@sf.errors path="time"/><br/>
                    </div>
                </div>
                <div class="form-group text-center">
                    <div class="col-md-8">
                        <@sf.input path="doctorFio" id="doctorFio" placeholder="ФИО доктора" value="${doctor.fio}" disabled=true/>
                        <br/>
                        <@sf.errors path="doctorFio"/><br/>
                    </div>
                </div>
                <div class="form-group text-left">
                    <div class="col-md-8" id="period">
                        <h3>выберите период времени, чтобы узнать, когда можно записаться</h3>
                        <select id="period">
                            <option id="w" name="w"> на две недели</option>
                            <option id="m" name="m"> на месяц</option>
                            <option id="2m" name="2m"> на два месяца</option>
                        </select>
                    </div>
                </div>

                <div id="dates" onchange="doSendAble()">

                </div>

                <div class="form-group text-center">
                    <div class="col-md-8">
                        <button type="submit" id="appointment-create" class="btn btn-success" disabled>Записаться</button>
                    </div>
                </div>
            </@sf.form>
        </div>
    </div>
</section>

</#macro>

<@main title="Запись на приём" customScripts=["/js/appointment.js"]/>