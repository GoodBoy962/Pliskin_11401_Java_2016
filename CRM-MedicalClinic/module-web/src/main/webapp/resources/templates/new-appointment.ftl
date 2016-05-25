<#include "main-template.ftl"/>

<#macro m_body>

<section id="main-slider" class="carousel">
    <div class="container">
        <div class="col-md-10 col-lg-offset-2 text-center">
            <form action="/appointment" method="post" class="form-horizontal text-center">
                <div class="form-group text-center">
                    <div class="col-md-8">
                        <input name="weekDay" id="weekDay" placeholder="День недели" value="${w_day?string}"
                               <#--disabled-->
                                type="hidden"
                        />
                    </div>
                </div>
                <div class="form-group text-center">
                    <div class="col-md-8">
                        <input name="time" id="time" placeholder="Время" value="${time?string}"
                               <#--disabled-->
                                type="hidden"
                        /><br/>
                    </div>
                </div>
                <div class="form-group text-center">
                    <div class="col-md-8">
                        <input name="doctorFio" id="doctorFio" placeholder="ФИО доктора" value="${doctor.fio?string}"
                               <#--disabled-->
                                type="hidden"
                        />
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
                        <button type="submit" id="appointment-create" class="btn btn-success" disabled>Записаться
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</section>

</#macro>

<@main title="Запись на приём" customScripts=["/js/appointment.js"]/>