<%--
  Created by IntelliJ IDEA.
  User: anura
  Date: 22-12-2024
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Weather Web</title>
    <link rel="stylesheet" href="styleJSP.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
  </head>
  <body>
  <div class="mainContainer">

    <form action="hello-servlet" method="post" class="searchInput">
      <input type="text" placeholder="Enter City Name" id="searchInput" name="city"/>
      <button id="searchButton"><i class="fas fa-search"></i></button>
    </form>

  <div class="weatherDetails">
    <div class="cityDetails">
      <div class="desc"><strong>${city}</strong></div>
    </div>
    <div class="weatherIcon">
      <img src="" alt="Clouds" id="weather-icon">
      <h2>${temperature} °C</h2>
      <input type="hidden" id="wc" value="${weatherCondition}">
    </div>

    <div class="cityDetails">
      <div class="date">${date}</div>
      <div class="desc">${weatherCondition}</div>
    </div>
    <div class="windDetails">
      <div class="humidityBox">
        <img src="https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEhgr7XehXJkOPXbZr8xL42sZEFYlS-1fQcvUMsS2HrrV8pcj3GDFaYmYmeb3vXfMrjGXpViEDVfvLcqI7pJ03pKb_9ldQm-Cj9SlGW2Op8rxArgIhlD6oSLGQQKH9IqH1urPpQ4EAMCs3KOwbzLu57FDKv01PioBJBdR6pqlaxZTJr3HwxOUlFhC9EFyw/s320/thermometer.png" alt="Humidity">
        <div class="humidity">
          <span>Humidity</span>
          <h2>${humidity} % </h2>
        </div>
      </div>

      <div class="windSpeed">
        <img src="https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEiyaIguDPkbBMnUDQkGp3wLRj_kvd_GIQ4RHQar7a32mUGtwg3wHLIe0ejKqryX8dnJu-gqU6CBnDo47O7BlzCMCwRbB7u0Pj0CbtGwtyhd8Y8cgEMaSuZKrw5-62etXwo7UoY509umLmndsRmEqqO0FKocqTqjzHvJFC2AEEYjUax9tc1JMWxIWAQR4g/s320/wind.png">
        <div class="wind">
          <span>Wind Speed</span>
          <h2> ${windSpeed} km/h</h2>
        </div>
      </div>
    </div>
  </div>
  </div>
  <script src="script.js"></script>
  </body>
</html>
