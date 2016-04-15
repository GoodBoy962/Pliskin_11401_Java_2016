<#include "main-template.ftl"/>

<#macro m_body>

<section id="main-slider" class="carousel">
    <div class="container">
        <div class="col-md-10 col-lg-offset-2 text-center">
            <form action="/appointment" method="post" class="form-horizontal text-center">
                <div class="form-group text-center">
                    <div class="col-md-8">
                        <h3>Выберите город</h3>
                        <input name="city" id="city" placeholder="город" type="text"
                               onchange="getMedClinicsAndOffices()"/>
                    </div>
                </div>
                <div class="form-group text-left" id="medicalClinics" hidden>
                    <div class="col-md-8">
                        <h3>Клиники и адреса</h3>
                        <select id="offices">

                        </select>
                    </div>
                </div>
                <div class="form-group text-left" id="specializations" hidden>
                    <div class="col-md-8">
                        <h3>Специальность врача</h3>
                        <select id="specs">

                        </select>
                    </div>
                </div>
                <div class="form-group text-left" hidden>
                    <div class="col-md-8" id="doctors">
                        <h3>Врач</h3>
                        <select id="docs">

                        </select>
                    </div>
                </div>
                <div class="form-group text-left" hidden>
                    <div class="col-md-8" id="period">
                        <h3>выберите период времени, чтобы узнать, когда можно записаться</h3>
                        <select id="period">
                            <option id="w" name="w"> на две недели</option>
                            <option id="m" name="m"> на месяц</option>
                            <option id="2m" name="2m"> на два месяца</option>
                        </select>
                    </div>
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

<@main title="Запись на приём" customScripts=["/js/cool-appointment.js"]/>