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
    <p>Now you have an account feel free to: ${message}</p>
    <p>Visit our <a href="https://vetlog.org/privacy/show">privacy policy</a></p>
    <p>Thank you for using Vetlog.</p>
    </div>
    <footer>
    <div class="d-flex flex-column text-white" style="background-color: black">
      <div class="p-2">
        <div class="d-flex justify-content-center">
          <a href="https://twitter.com/vetlog_care"><img style="margin: 0px 15px" src="https://storage.googleapis.com/vetlog-icons/twitter.png" height="20" width="20" alt="twitter"></a>
          <a href="https://www.instagram.com/vetlog_care"><img style="margin: 0px 15px" src="https://storage.googleapis.com/vetlog-icons/instagram.png" height="20" width="20" alt="instagram"></a>
          <a href="https://tiktok.com/@vetlog_care"><img style="margin: 0px 15px" src="https://storage.googleapis.com/vetlog-icons/tiktok.png" height="20" width="20" alt="tiktok"></a>
        </div>
      </div>
      <div class="p-2">
        <div class="d-flex justify-content-center">
          <p>© Copyright 2025, Vetlog.</p>
        </div>
      </div>
      <div class="p-2">
        <div class="d-flex justify-content-center">
          <a href="https://vetlog.org/">https://vetlog.org/</a>
        </div>
      </div>
    </div>
    </footer>
  </body>
</html>