<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>

${mc.name}<br/>
${mc.info}<br/>
<@security.authorize access="hasRole('ROLE_SYSTEM_ADMIN')">
добавить офис и администратора офиса <br/>
</@security.authorize>
<hr/>
<#list offices as of>
${of.address}<br/>
</#list>
<hr/>