<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>


<@sf.form action="/admin/doctors" method="post" modelAttribute="doctor_form">
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
    <@sf.input type="date" path="birthDay" id="birthDay" placeholder="дата рождения"/>
    <@sf.errors path="birthDay"/><br/>
    <@sf.input type="date" path="inceptionDate" id="inceptionDate" placeholder="дата начала проктики"/>
    <@sf.errors path="inceptionDate"/><br/>
    <@sf.input type="date" path="employmentDate" id="employmentDate" placeholder="дата приёма на роботу"/>
    <@sf.errors path="employmentDate"/><br/>
    <@sf.select path="specialization" id="specialization" placeholder="специализация">
        <#list specializations as sp>
            <@sf.option value="${sp.name}"/>
        </#list>
    </@sf.select>
<#--<@sf.errors  path="specialization"/><br/>-->


<button type="submit">Содать</button>
</@sf.form>