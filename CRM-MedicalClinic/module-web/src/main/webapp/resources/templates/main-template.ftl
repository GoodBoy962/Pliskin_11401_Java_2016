<#macro main title="Hello!" customScripts=[] customStyles=[]>
    <#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>
    <#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/font-awesome.min.css" rel="stylesheet">
    <link href="/css/prettyPhoto.css" rel="stylesheet">
    <link href="/css/main.css" rel="stylesheet">
    <link rel="shortcut icon" href="/images/ico/medicine box 2.ico">
    <link rel="apple-touch-icon-precomposed" href="/images/ico/apple-touch-icon-57-precomposed.png">

    <#list customStyles as style>
        <link rel="stylesheet" type="text/css" href="${style}"/>
    </#list>

</head>
<body data-spy="scroll" data-target="#navbar" data-offset="0">
    <#include "header.ftl"/>

<div class="content">
    <@m_body/>
</div>


    <#include "footer.ftl"/>

<script src="/js/jquery.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/jquery.isotope.min.js"></script>
<script src="/js/jquery.prettyPhoto.js"></script>
<script src="/js/main.js"></script>
    <#list customScripts as script>
    <script src="${script}"></script>
    </#list>
</body>
</html>
</#macro>