<#assign dd = dates_doctors>
<#assign keys = dd?keys>
<#assign values = dd?values>
<#list values as v>
    <#assign keys2 = v?keys>
    <#list keys2 as k2>
        <option>${} ${k2?date}</option>
    </#list>

<#--<#assign dates = dd[k]?keys>-->
<#--<#list dates as d>-->
<#--<select>${k.fio}</select>-->
<#--</#list>-->
</#list>