<#assign dd = dates_doctors>
<#assign doctors = dd?keys>
<#assign values = dd?values>
<#assign count1 = 0>
<#list doctors as doctor>
    <#assign dates = dd?values[count1]?keys>
    <#list dates as date>
        <#assign count2 = 0>
        <#assign times = dd?values[count1]?values[count2]>
        <#list times as t>
        <option>${doctor.fio} ${date?date} ${t.weekDay} ${t.startTime?time}
        </option>
        </#list>
        <#assign count2++/>
    </#list>
    <#assign count1++/>
</#list>