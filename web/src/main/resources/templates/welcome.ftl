<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" xmlns:layout="http://www.ultraq.net.nz/web/thymeleaf/layout">
  <head>
    <meta charset="utf-8">
    <title>Notification</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/6.7.2/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
  </head>
  <body>
    <header>
        <a href="/">
          <?xml version="1.0" encoding="UTF-8"?>
          <svg id="Layer_2" data-name="Layer 2" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 694.35 50.71">
            <defs>
              <style>
                .cls-1, .cls-2 {
                  fill: #f78526;
                }

                .cls-3, .cls-4, .cls-5 {
                  fill: #27adad;
                }

                .cls-6 {
                  font-family: Chillax-Semibold, Chillax;
                  font-size: 99px;
                  font-weight: 600;
                  letter-spacing: .02em;
                }

                .cls-4, .cls-2, .cls-7, .cls-8 {
                  fill-rule: evenodd;
                }

                .cls-4, .cls-2, .cls-7, .cls-8, .cls-5 {
                  stroke-width: 0px;
                }

                .cls-7 {
                  fill: #fff;
                }

                .cls-8 {
                  fill: #8acbce;
                }
              </style>
            </defs>
            <g id="Layer_1-2" data-name="Layer 1" transform="scale(0.2)">
              <g>
                <path class="cls-2" d="M173.95,8.52v77.24h77.25c4.69,0,8.52,3.83,8.52,8.52v71.15c0,4.69-3.83,8.52-8.52,8.52h-77.25s0,77.24,0,77.24c0,4.69-3.83,8.52-8.51,8.52h-71.15c-4.69,0-8.52-3.83-8.52-8.52v-77.25H8.52c-4.69,0-8.52-3.83-8.52-8.52v-71.15c0-4.69,3.83-8.52,8.52-8.52h77.24V8.52c0-4.69,3.83-8.52,8.52-8.52h71.15c4.69,0,8.51,3.85,8.51,8.52"/>
                <path class="cls-7" d="M129.86,229.64c55.11,0,99.79-44.68,99.79-99.79S184.97,30.06,129.86,30.06,30.07,74.74,30.07,129.85s44.68,99.79,99.79,99.79"/>
                <path class="cls-8" d="M159.58,199.51c6.61,2.55,21.18-5.06,26.7-10.55,14.68-14.63,22.81-34.15,22.81-54.84s-8.68-40.78-22.7-54.8c-14.02-14.02-33.4-22.69-54.8-22.69-10.3,0-20.12,2.01-29.11,5.65,5.58-.25,11.19.34,16.67,1.82,12.01,3.25,23.35,10.73,32.3,23.03,5.28,7.25,11.34,12.71,18.04,16.37,6.6,3.61,13.9,5.54,21.75,5.78h.38c3.47,0,6.28,2.8,6.28,6.28,0,10.69-.61,21.49-5.67,30.45-12.48,21.62-38.54,7.79-48,24.17-7.58,13.14-2.96,29.18,15.34,29.32"/>
                <path class="cls-5" d="M184.54,184.52c14.68-14.63,22.81-34.14,22.81-54.84s-8.68-40.78-22.7-54.8c-14.02-14.02-33.4-22.69-54.8-22.7-10.3,0-20.12,2.01-29.11,5.66,5.58-.25,11.19.34,16.67,1.82,12.01,3.25,23.35,10.73,32.3,23.03,5.28,7.25,11.34,12.71,18.04,16.37,6.61,3.61,13.9,5.54,21.76,5.78h.38c3.47-.01,6.28,2.8,6.28,6.27,0,10.69-.61,21.49-5.67,30.46-5.33,9.45-14.99,16.2-32.73,17.51-2.4,0-9.24,1.25-14.41,6.34-3.59,3.54-5.66,7.63-6.34,11.74-.5,3.03-.26,6.09.68,8.93,2.13,6.46,6.73,9.94,12.25,11.43,14.42,3.89,24.82-3.26,34.58-12.99M39.79,128.62c.03-1.77.1-3.5.21-5.27,1.55-22.35,11.26-42.45,26.16-57.35,16.3-16.3,38.82-26.38,63.69-26.38s47.39,10.08,63.68,26.38c16.3,16.3,26.38,38.82,26.38,63.69,0,23.88-9.62,46.86-26.54,63.72-13.12,13.08-25.71,20.97-44.84,17.06-9.05-1.85-18.28-7.23-22.66-20.49-1.55-4.69-1.96-9.77-1.12-14.82,1.3-7.82,5.57-15.52,13.18-21.49-.41-.2-.81-.41-1.19-.62-4.86-3.1-8.57-7.36-11.12-12.79,9.27,4.7,19.69,6.8,31.26,6.31,12.71-.93,19.32-5.2,22.66-11.12,2.82-5.01,3.71-11.57,3.96-18.49-7.74-.91-15.02-3.2-21.75-6.88-8.38-4.58-15.84-11.25-22.21-20.01-7.14-9.81-16.04-15.74-25.38-18.27-8.65-2.34-17.72-1.82-26.13,1.21-8.53,3.06-16.43,8.68-22.62,16.49-7.06,8.89-11.87,20.64-12.86,34.71-.13,1.83-.18,3.65-.19,5.49,0,7.08,2.97,11.45,7.13,13.25,2.21.95,4.77,1.29,7.45,1.01,2.89-.3,5.89-1.28,8.72-2.94,8.57-5.01,15.61-16.09,14.68-33.19-.18-3.46,2.48-6.4,5.94-6.58,3.46-.17,6.4,2.48,6.58,5.94,1.23,22.54-8.76,37.55-20.87,44.63-4.44,2.6-9.17,4.14-13.78,4.61-4.82.5-9.53-.15-13.69-1.94-10.86-4.69-14.8-14.6-14.74-25.85Z"/>
                <path class="cls-4" d="M121.53,96.31c-4.48,1.2-7.25,5.94-7.08,11.36,2.23-2.22,5.26-4,8.8-4.94,3.54-.95,7.05-.93,10.09-.12-2.55-4.78-7.33-7.5-11.81-6.3"/>
                <path class="cls-4" d="M109.64,192.52c-4.01.45-5.68.77-8.3,1.58-5.63,1.74-7.8,4.73-2.84,9.13,2.97,2.64,7.63,4.21,10.7,3.58,2.4-.66,3.11-1.71,4.93-3.6,4.61-4.81,3.18-11.57-4.49-10.7M108.28,184.6c-3.93.91-5.61,1.16-8.35,1.26-5.89.23-8.93-1.86-5.71-7.67,1.93-3.48,5.8-6.5,8.9-6.93,2.48-.18,3.51.57,5.84,1.76,5.94,3.01,6.84,9.85-.68,11.58ZM111.23,166.44c-3.77-2.18-6-5.18-.27-8.19,1.72-.9,3.8-1.6,5.62-1.98,4.41-.93,10.07,1.95,10.09,6.59-.51,8.11-10.73,6.31-15.44,3.58ZM118.44,208.67c-2.83,3.31-3.93,6.88,2.47,7.82,1.92.28,4.12.24,5.96,0,4.47-.59,8.85-5.19,7.32-9.57-3.18-7.48-12.21-2.39-15.76,1.75Z"/>
              </g>
              <text class="cls-6" transform="translate(290.54 162.87)"><tspan class="cls-3" x="0" y="0">VET</tspan><tspan class="cls-1" x="210.28" y="0">LOG</tspan></text>
            </g>
          </svg>
        </a>
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