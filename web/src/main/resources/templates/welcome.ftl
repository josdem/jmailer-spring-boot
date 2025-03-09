<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
  <head>
    <meta charset="utf-8">
    <title>Notification</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/6.7.2/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
  </head>
  <body>
    <header>
        <a href="/"><img src="https://storage.googleapis.com/vetlog-icons/logo.png" alt="vetlog"></a>
    </header>
    <hr>
    <div class="d-flex flex-column" style="margin: 0px 15px">
    <h3>Welcome to Vetlog!</h3>
    <br/>
    <p>Hello dear ${name}, we are glad to have you.</p>
    <p>Now that you have an account feel free to: ${message}</p>
    <p>Visit our <a href="https://vetlog.org/privacy/show">privacy policy</a></p>
    <p>Thank you for using Vetlog.</p>
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
