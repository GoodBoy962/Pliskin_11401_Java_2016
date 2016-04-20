<option></option>
<#assign dd = dates_doctors>
<#assign doctors = dd?keys>
<#assign values = dd?values>
<#assign count1 = 0>
<#list doctors as doctor>
    <#assign dates = dd?values[count1]?keys>
    <#assign count2 = 0>
    <#list dates as date>
        <#assign times = dd?values[count1]?values[count2]>
        <#list times as t>
        <option>${doctor.fio} // ${date?date?string}
        ${t.weekDay} ${t.startTime?time?string("hh:mm:ss")}
        </option>
        </#list>
        <#assign count2++/>
    </#list>
    <#assign count1++/>
</#list>