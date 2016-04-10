<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>

${mc.name}<br/>
${mc.info}<br/>
<@security.authorize access="hasRole('ROLE_SYSTEM_ADMIN')">
<a href="/system/medical_clinics/${mc.id}/offices/new">добавить офис и администратора офиса</a> <br/>
</@security.authorize>
<hr/>
<#list offices as of>
${of.address}<br/>
</#list>
<hr/>