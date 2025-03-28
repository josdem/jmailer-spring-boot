<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" xmlns:layout="http://www.ultraq.net.nz/web/thymeleaf/layout">
  <head>
      <meta charset="utf-8">
      <title>Notification</title>
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
  <body>
    <header>
      <a href="/"><img src="https://storage.googleapis.com/vetlog-icons/logo.png" alt="vetlog"></a>
    </header>
    <hr>
    <div class="d-flex flex-column" style="margin: 0px 15px">
      <h3>Do you want to reset your password?</h3>
      <br/>
      <p>Someone sent a reset password request on your behalf</p>
      <p>If you want to proceed, please click in the following link to reset your password</p>
      <p><br></p>
      <p class="text-center"><a href="${message}">${message}</a></p>
      <br/>
      <p>If you want to keep using your current password, just ignore this message.</p>
      <p>Visit our privacy policy: <a href="https://vetlog.org/privacy/show">aviso de privacidad</a></p>
      <p>Thanks for using Vetlog.</p>
    </div>
    <footer>
      <table width="100%" bgcolor="#000000" cellpadding="10">
        <tr>
          <td align="center">
            <a href="https://twitter.com/vetlog_care">
              <img src="https://storage.googleapis.com/vetlog-icons/twitter.png" height="20" width="20" alt="twitter" style="margin: 0px 10px;">
            </a>
            <a href="https://www.instagram.com/vetlog_care">
              <img src="https://storage.googleapis.com/vetlog-icons/instagram.png" height="20" width="20" alt="instagram" style="margin: 0px 10px;">
            </a>
            <a href="https://tiktok.com/@vetlog_care">
              <img src="https://storage.googleapis.com/vetlog-icons/tiktok.png" height="20" width="20" alt="tiktok" style="margin: 0px 10px;">
            </a>
          </td>
        </tr>
        <tr>
          <td align="center" style="color: white; font-size: 14px;">
            <span>&#169;</span> Copyright 2025, Vetlog.
          </td>
        </tr>
        <tr>
          <td align="center">
            <a href="https://vetlog.org/" style="color: white; font-size: 14px;">https://vetlog.org/</a>
          </td>
        </tr>
      </table>
    </footer>
  </body>
</html>
