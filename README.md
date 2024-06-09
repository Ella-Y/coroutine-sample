# Coroutine Sample

1. Deferred
2. yield, isActive, supervisorJob


### Deferred 를 이용한 순차 처리
OpenWeatherApi를 이용하여 날씨를 받아온다.

'CurrentWeather'를 이용하여 날씨, 온도를 받아오고 'Call 5 day / 3 hour forecast data'를 이용하여 3시간 단위의 미래 날씨(오늘, 내일)를 받아서 `WeatherModel` 에 담는다.

- :app
- :network

### yield, isActive, supervisorJob
- https://github.com/Ella-Y/slilabs-ota