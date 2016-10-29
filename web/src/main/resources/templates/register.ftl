<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" xmlns:layout="http://www.ultraq.net.nz/web/thymeleaf/layout">
  <head>
    <meta charset="utf-8">
    <title>Notification</title>
  </head>
  <body>
    <h1>Jmailer</h1>
    <h3>Por favor, confirme su email</h3>
    <hr>
    <p>Gracias por registrarse. Su cuenta ha sido creada y debe ser activada antes de que la pueda usar.</p>
    <p>Para activar la cuenta, haga clic sobre el siguiente enlace o copie y pegue la URL completa en su navegador</p>
    <p><br></p>
    <p class="text-center"><a href="${token}">${token}</a></p>
    <p>Thank you for using Jmailer!!</p>
  </body>
</html>

