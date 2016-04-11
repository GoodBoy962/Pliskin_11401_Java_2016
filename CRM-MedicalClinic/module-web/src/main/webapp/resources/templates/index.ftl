<#include "main-template.ftl"/>

<#macro m_body>

<section id="main-slider" class="carousel">
    <div class="carousel-inner">
        <div class="item active">
            <div class="container">
                <div class="carousel-content">
                    <h1>Медицина на новом уровне</h1>
                    <p class="lead">Медицинсие услуги online</p>
                </div>
            </div>
        </div>
        <div class="item">
            <div class="container">
                <div class="carousel-content">
                    <h1>Вся история посещений</h1>
                    <p class="lead">Как врач, так и пациент могут посматривать всю историю посещений</p>
                </div>
            </div>
        </div>
    </div>
    <a class="prev" href="#main-slider" data-slide="prev"><i class="icon-angle-left"></i></a>
    <a class="next" href="#main-slider" data-slide="next"><i class="icon-angle-right"></i></a>
</section>

<br/>
<br/>
<br/>
<br/>
<br/>

<section id="services">
    <div class="container">
        <div class="box first">
            <div class="row">
                <div class="col-md-4 col-sm-6">
                    <div class="center">
                        <i class="icon-medkit icon-md icon-color1"></i>
                        <h4>Медицинские учреждения</h4>
                        <p>Любая клиника может зарегестрировать все свои офисы и врачей</p>
                    </div>
                </div>
                <div class="col-md-4 col-sm-6">
                    <div class="center">
                        <i class="icon-calendar icon-md icon-color2"></i>
                        <h4>Запись на приём</h4>
                        <p>Пациенты могут легко записываться на приём</p>
                    </div>
                </div>
                <div class="col-md-4 col-sm-6">
                    <div class="center">
                        <i class="icon-folder-open icon-md icon-color3"></i>
                        <h4>Просмотр истории посещений</h4>
                        <p>Врачи могут просматривать истории посещений пациентов, как и сами пациенты могут
                            просматривать их</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<br/>
<br/>
<br/>
<br/>
<br/>

<section id="contact">
    <div class="container">
        <div class="box last">
            <div class="row">
                <div class="col-sm-6">
                    <h1>Контактная форма</h1>
                    <p>Напишите нам, если хотите зарегестрировать вашу Медицинскую клинику в нашем сервисе</p>
                    <div class="status alert alert-success" style="display: none"></div>
                    <form id="main-contact-form" class="contact-form" name="contact-form" method="post"
                          action="sendemail.php" role="form">
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <input type="text" class="form-control" required="required" placeholder="ФИО">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <input type="text" class="form-control" required="required"
                                           placeholder="email">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group">
                                    <textarea name="message" id="message" required="required" class="form-control"
                                              rows="8" placeholder="сообщение"></textarea>
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-danger btn-lg">отправить</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-sm-6">
                    <h1>Наш адрес</h1>
                    <div class="row">
                        <div class="col-md-6">
                            <address>
                                <strong>Pliskin corporation</strong><br>
                                San Francisco 770<br>
                                <abbr title="Phone">tel:+7(967)362-03-39</abbr>
                            </address>
                        </div>
                    </div>
                    <h1>Наши контакты в соц сетях</h1>
                    <div class="row">
                        <div class="col-md-6">
                            <ul class="social">
                                <li><a href="https://www.facebook.com/alexnetmore"><i
                                        class="icon-facebook icon-social"></i> Facebook</a></li>
                                <li><a href="https://plus.google.com/117236137415946671771"><i
                                        class="icon-google-plus icon-social"></i> Google Plus</a></li>
                            </ul>
                        </div>
                        <div class="col-md-6">
                            <ul class="social">
                                <li>
                                    <a href="https://www.linkedin.com/profile/preview?locale=ru_RU&trk=prof-0-sb-preview-primary-button"><i
                                            class="icon-linkedin icon-social"></i> LinkedIn</a></li>
                                <li><a href="https://twitter.com/good_boy_961"><i class="icon-twitter icon-social"></i>
                                    Twitter</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


</#macro>

<@main title="Главная"/>