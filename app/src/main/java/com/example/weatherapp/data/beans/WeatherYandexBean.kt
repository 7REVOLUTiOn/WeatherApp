package com.example.weatherapp.data.beans

import com.google.gson.annotations.SerializedName

data class WeatherYandexBean(
    @SerializedName("fact") val fact: Fact?,
    @SerializedName("forecast") val forecast: Forecast?,
    @SerializedName("info") val info: Info?,
    @SerializedName("now") val now: Int?,
    @SerializedName("now_dt") val nowDt: String?
) {
    data class Fact(
        @SerializedName("condition") val condition: String?,
        @SerializedName("daytime") val daytime: String?,
        @SerializedName("feels_like") val feelsLike: String,
        @SerializedName("humidity") val humidity: Int?,
        @SerializedName("icon") val icon: String?,
        @SerializedName("obs_time") val obsTime: Int?,
        @SerializedName("polar") val polar: Boolean?,
        @SerializedName("pressure_mm") val pressureMm: Int?,
        @SerializedName("pressure_pa") val pressurePa: Int?,
        @SerializedName("season") val season: String,
        @SerializedName("temp") val temp: String,
        @SerializedName("wind_dir") val windDir: String?,
        @SerializedName("wind_gust") val windGust: Double?,
        @SerializedName("wind_speed") val windSpeed: Int?
    )

    data class Forecast(
        @SerializedName("date") val date: String?,
        @SerializedName("date_ts") val dateTs: Int?,
        @SerializedName("moon_code") val moonCode: Int?,
        @SerializedName("moon_text") val moonText: String?,
        @SerializedName("parts") val parts: List<Part?>?,
        @SerializedName("sunrise") val sunrise: String?,
        @SerializedName("sunset") val sunset: String?,
        @SerializedName("week") val week: Int?
    ) {
        data class Part(
            @SerializedName("condition") val condition: String?,
            @SerializedName("daytime") val daytime: String?,
            @SerializedName("feels_like") val feelsLike: Int?,
            @SerializedName("humidity") val humidity: Int?,
            @SerializedName("icon") val icon: String?,
            @SerializedName("part_name") val partName: String?,
            @SerializedName("polar") val polar: Boolean?,
            @SerializedName("prec_mm") val precMm: Int?,
            @SerializedName("prec_period") val precPeriod: Int?,
            @SerializedName("prec_prob") val precProb: Int?,
            @SerializedName("pressure_mm") val pressureMm: Int?,
            @SerializedName("pressure_pa") val pressurePa: Int?,
            @SerializedName("temp_avg") val tempAvg: Int?,
            @SerializedName("temp_max") val tempMax: Int?,
            @SerializedName("temp_min") val tempMin: Int?,
            @SerializedName("wind_dir") val windDir: String?,
            @SerializedName("wind_gust") val windGust: Int?,
            @SerializedName("wind_speed") val windSpeed: Double?
        )
    }

    data class Info(
        @SerializedName("lat") val lat: Double?,
        @SerializedName("lon") val lon: Double?,
        @SerializedName("url") val url: String?
    )
}
