<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<body>

<form action='/process' method='POST'>
    <textarea name='text' type='textarea'></textarea><br>
    <select name="operation">
        <option value="symbols-cnt">Количество символов</option>
        <option value="words-cnt">Количество слов</option>
        <option value="sentences-cnt">Количество предложений</option>
        <option value="paragraphs-cnt">Количество абзацев</option>
    </select>
    <br/>
    <input name='process' type='submit' value='Process!'>
</form>

</body>
</html>