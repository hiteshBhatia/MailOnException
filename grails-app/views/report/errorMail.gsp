<%@ page contentType="text/html" %>
<html>
<head></head>

<body>

<div style="font-size: 16px; font-weight: 200; line-height: 27px; color: inherit;">
    <h2 style="font-size: 24px; line-height: 36px;">Exception On ${mailInfoMap."Environment"}</h2>

    <div id="table"
         style="background-color: transparent; border-spacing: 0; border: 1px solid #DDD; border-left: 0; -webkit-border-radius: 4px; -moz-border-radius: 4px; border-radius: 4px;">
        <table style="width: 100%; margin-bottom: 18px;">
            <tbody>
            <g:each in="${keys}" var="key">
                <tr>
                    <td style="background-color: #F9F9F9;border-left: 1px solid #DDD;padding: 8px; line-height: 18px; text-align: left; vertical-align: top; border-top: 1px solid #DDD;">${key}</td>
                    <td style="border-left: 1px solid #DDD;padding: 8px; line-height: 18px; text-align: left; vertical-align: top; border-top: 1px solid #DDD;">${mailInfoMap[key]}</td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div id="exception"
         style="min-height: 20px; padding: 19px; margin-bottom: 20px; background-color: whiteSmoke; border: 1px solid rgba(0, 0, 0, 0.05); -webkit-border-radius: 4px; -moz-border-radius: 4px; border-radius: 4px; -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.05); -moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.05); box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.05);">
        <h2 style="font-size: 16px; line-height: 36px;">Exception</h2>

        <p style="margin: 0 0 9px;">
            ${exceptionMap.stackTrace}
        </p>
    </div>

</div>
</body>
</html>




