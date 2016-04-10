<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>

<@sf.form action="/system/medical_clinics/${id}/offices" method="post" modelAttribute="office_admin_form">
    <@sf.input path="address" id="address" placeholder="Адрес"/>
    <@sf.errors path="address"/><br/>
<p>данные администратора нового офиса</p>
<br/>
    <@sf.input path="name" id="name" placeholder="Имя"/>
    <@sf.errors path="name"/><br/>
    <@sf.input path="surname" id="surname" placeholder="Фамилия"/>
    <@sf.errors path="surname"/><br/>
    <@sf.input path="lastname" id="lasname" placeholder="Отчество"/>
    <@sf.errors path="lastname"/><br/>
    <@sf.input path="login" id="login" placeholder="Логин"/>
    <@sf.errors path="login"/><br/>
    <@sf.input path="email" id="email" placeholder="email"/>
    <@sf.errors path="email"/><br/>
    <@sf.input path="password" id="password" placeholder="пароль"/>
    <@sf.errors path="password"/><br/>
<button type="submit">Содать</button>
</@sf.form>