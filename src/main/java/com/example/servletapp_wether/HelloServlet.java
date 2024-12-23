package com.example.servletapp_wether;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "chal kam ho gya";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        // Get the city name from the request and URL-encode it
        String cityName = request.getParameter("city");
        String encodedCityName = URLEncoder.encode(cityName, StandardCharsets.UTF_8);
//        System.out.println(encodedCityName);
        String apiKey = "9ea33fc4b42f6101f239144bbcf46636";
        String apiURL = "https://api.openweathermap.org/data/2.5/weather?q=" + encodedCityName + "&appid=" + apiKey;

        // API Integration
        URL url = new URL(apiURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Check the response code before proceeding
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Read the data from the API response
            InputStream inputStream = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);

            // Store the response content
            StringBuilder responseContent = new StringBuilder();
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNext()) {
                responseContent.append(scanner.nextLine());
            }
            scanner.close();

            // Output the response
//            System.out.println(responseContent);

            // typecasting in json
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(responseContent.toString(), JsonObject.class);
//            System.out.println(jsonObject);

            // date and time
            long dateTimestamp = jsonObject.get("dt").getAsLong() * 1000;
            String date = new Date(dateTimestamp).toString();

            // temperature
            JsonElement tempKelvin = jsonObject.getAsJsonObject("main").get("temp");
            int tempInCelsius = (int) (tempKelvin.getAsDouble() - 273.15);

            // humidity
            int humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsInt();

            // wind speed
            double windSpeedInDouble = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble() * 3.6;
            String windSpeed = String.format("%.2f", windSpeedInDouble);

            // Weather condition (from the first object in the "weather" array)
            JsonObject weatherObject = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject();
            String weatherCond = weatherObject.get("main").getAsString();

//            // Output the data
//            System.out.println("Date: " + date);
//            System.out.println("Temperature: " + tempInCelsius + "°C");
//            System.out.println("Humidity: " + humidity + "%");
//            System.out.println("Wind Speed: " + windSpeed + " km/h");
//            System.out.println("Weather Condition: " + weatherCond);

            request.setAttribute("city",cityName);
            request.setAttribute("date",date);
            request.setAttribute("temperature",tempInCelsius);
            request.setAttribute("weatherCondition",weatherCond);
            request.setAttribute("humidity",humidity);
            request.setAttribute("windSpeed",windSpeed);
            connection.disconnect();

        } else {
            String errorMessage = "An error occurred. Please try again.";
            if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
                errorMessage = "Error: City not found. Please check the city name.";
            } else if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST) {
                errorMessage = "Error: Invalid request. Please check the query parameters.";
            } else if (responseCode == 401) {
                errorMessage = "Error: Invalid API key. Please check your API key.";
            } else if (responseCode == 500) {
                errorMessage = "Error: Server error. Please try again later.";
            }

// Add the error message to the request attributes
            request.setAttribute("errorMessage", errorMessage);

// Forward the request to the error.jsp page
            request.getRequestDispatcher("error.jsp").forward(request, response);

        }
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }

    public void destroy() {
    }
}