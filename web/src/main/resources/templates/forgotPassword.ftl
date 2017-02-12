<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" xmlns:layout="http://www.ultraq.net.nz/web/thymeleaf/layout">
  <head>
    <meta charset="utf-8">
    <title>Notification</title>
  </head>
  <body>
    <h1>Jmailer</h1>
    <h3>¿Necesitas resetear tu contraseña?</h3>
    <hr>
    <p>Alguien ha solicitado resetear tu contraseña.</p>
    <p>Si solicitaste esto, por favor da clic en el siguiente link para resetear</p>
    <p><br></p>
    <p class="text-center"><a href="${url}">${url}</a></p>
    <br/>
    <p>Si tu no solicitaste o quieres conservar la contraseña actual, simplemente ignora este mensaje</p>
    <p>Thank you for using Jmailer!</p>
  </body>
</html>

