<#list dates as d>
<div class="checkbox col-lg-3">
    <input name="date" id="date" type="checkbox" value="${d?date}"/>${d?date}
</div>
</#list>