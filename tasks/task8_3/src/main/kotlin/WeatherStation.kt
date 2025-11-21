/**
 * Task 8.3 - Weather Station Temperature Analysis
 * 
 * This program analyzes temperature data from weather stations.
 * It demonstrates data analysis using Kotlin collections and statistical operations.
 */

import kotlin.math.*
import kotlin.random.Random

/**
 * Data class representing a temperature reading from a weather station
 */
data class TemperatureReading(
    val stationId: String,
    val timestamp: String,
    val temperature: Double,
    val unit: String = "C"
) {
    override fun toString(): String = 
        "Station $stationId at $timestamp: $temperature°$unit"
}

/**
 * Data class representing weather station information
 */
data class WeatherStation(
    val id: String,
    val name: String,
    val location: String,
    val elevation: Double
) {
    override fun toString(): String = 
        "$name ($id) - $location, ${elevation}m"
}

/**
 * Main class for weather station temperature analysis
 */
class WeatherStationAnalyzer {
    private val stations = mutableListOf<WeatherStation>()
    private val readings = mutableListOf<TemperatureReading>()
    
    /**
     * Adds a weather station to the analyzer
     */
    fun addStation(station: WeatherStation) {
        stations.add(station)
    }
    
    /**
     * Adds a temperature reading
     */
    fun addReading(reading: TemperatureReading) {
        readings.add(reading)
    }
    
    /**
     * Adds multiple readings at once
     */
    fun addAllReadings(newReadings: List<TemperatureReading>) {
        readings.addAll(newReadings)
    }
    
    /**
     * Gets all readings for a specific station
     */
    fun getReadingsForStation(stationId: String): List<TemperatureReading> {
        return readings.filter { it.stationId == stationId }
    }
    
    /**
     * Calculates average temperature for a station
     */
    fun calculateAverageTemperature(stationId: String): Double? {
        val stationReadings = getReadingsForStation(stationId)
        if (stationReadings.isEmpty()) return null
        
        return stationReadings.map { it.temperature }.average()
    }
    
    /**
     * Finds the maximum temperature for a station
     */
    fun findMaxTemperature(stationId: String): TemperatureReading? {
        return getReadingsForStation(stationId).maxByOrNull { it.temperature }
    }
    
    /**
     * Finds the minimum temperature for a station
     */
    fun findMinTemperature(stationId: String): TemperatureReading? {
        return getReadingsForStation(stationId).minByOrNull { it.temperature }
    }
    
    /**
     * Calculates temperature range for a station
     */
    fun calculateTemperatureRange(stationId: String): Double? {
        val stationReadings = getReadingsForStation(stationId)
        if (stationReadings.isEmpty()) return null
        
        val max = stationReadings.maxOf { it.temperature }
        val min = stationReadings.minOf { it.temperature }
        return max - min
    }
    
    /**
     * Groups readings by station and calculates statistics
     */
    fun analyzeAllStations(): Map<String, StationStatistics> {
        return readings.groupBy { it.stationId }
            .mapValues { (stationId, stationReadings) ->
                val temperatures = stationReadings.map { it.temperature }
                StationStatistics(
                    stationId = stationId,
                    count = stationReadings.size,
                    average = temperatures.average(),
                    min = temperatures.minOrNull() ?: 0.0,
                    max = temperatures.maxOrNull() ?: 0.0,
                    range = (temperatures.maxOrNull() ?: 0.0) - (temperatures.minOrNull() ?: 0.0),
                    standardDeviation = calculateStandardDeviation(temperatures)
                )
            }
    }
    
    /**
     * Finds stations with temperatures above a threshold
     */
    fun findStationsAboveTemperature(threshold: Double): List<String> {
        return analyzeAllStations()
            .filter { it.value.average > threshold }
            .map { it.key }
    }
    
    /**
     * Finds stations with temperatures below a threshold
     */
    fun findStationsBelowTemperature(threshold: Double): List<String> {
        return analyzeAllStations()
            .filter { it.value.average < threshold }
            .map { it.key }
    }
    
    /**
     * Calculates daily averages for a station
     */
    fun calculateDailyAverages(stationId: String): Map<String, Double> {
        return getReadingsForStation(stationId)
            .groupBy { it.timestamp.substringBefore(" ") } // Extract date part
            .mapValues { (_, dayReadings) ->
                dayReadings.map { it.temperature }.average()
            }
    }
    
    /**
     * Gets overall statistics across all stations
     */
    fun getOverallStatistics(): OverallStatistics {
        val allTemperatures = readings.map { it.temperature }
        val stationStats = analyzeAllStations()
        
        return OverallStatistics(
            totalReadings = readings.size,
            totalStations = stations.size,
            overallAverage = allTemperatures.average(),
            overallMin = allTemperatures.minOrNull() ?: 0.0,
            overallMax = allTemperatures.maxOrNull() ?: 0.0,
            mostActiveStation = stationStats.maxByOrNull { it.value.count }?.key ?: "None",
            stationWithHighestAvg = stationStats.maxByOrNull { it.value.average }?.key ?: "None",
            stationWithLowestAvg = stationStats.minByOrNull { it.value.average }?.key ?: "None"
        )
    }
    
    /**
     * Helper function to calculate standard deviation
     */
    private fun calculateStandardDeviation(numbers: List<Double>): Double {
        if (numbers.size < 2) return 0.0
        
        val mean = numbers.average()
        val variance = numbers.map { (it - mean).pow(2) }.sum() / numbers.size
        return sqrt(variance)
    }
    
    /**
     * Displays all stations and their basic info
     */
    fun displayStations() {
        println("\n=== Weather Stations ===")
        if (stations.isEmpty()) {
            println("No stations registered.")
            return
        }
        
        stations.forEachIndexed { index, station ->
            val readingsCount = getReadingsForStation(station.id).size
            println("${index + 1}. $station (Readings: $readingsCount)")
        }
    }
    
    /**
     * Displays detailed analysis for all stations
     */
    fun displayDetailedAnalysis() {
        val analysis = analyzeAllStations()
        
        println("\n=== Detailed Temperature Analysis ===")
        analysis.forEach { (stationId, stats) ->
            println("""
            Station: $stationId
            Readings: ${stats.count}
            Average: ${stats.average.format(1)}°C
            Min: ${stats.min.format(1)}°C, Max: ${stats.max.format(1)}°C
            Range: ${stats.range.format(1)}°C
            Std Dev: ${stats.standardDeviation.format(2)}°C
            """.trimIndent())
            println("-".repeat(40))
        }
    }
}

/**
 * Data class for station statistics
 */
data class StationStatistics(
    val stationId: String,
    val count: Int,
    val average: Double,
    val min: Double,
    val max: Double,
    val range: Double,
    val standardDeviation: Double
)

/**
 * Data class for overall statistics
 */
data class OverallStatistics(
    val totalReadings: Int,
    val totalStations: Int,
    val overallAverage: Double,
    val overallMin: Double,
    val overallMax: Double,
    val mostActiveStation: String,
    val stationWithHighestAvg: String,
    val stationWithLowestAvg: String
)

/**
 * Extension function to format Double values
 */
fun Double.format(decimals: Int): String = "%.${decimals}f".format(this)

/**
 * Main function - Entry point of the application
 */
fun main() {
    println("=== Weather Station Temperature Analysis ===")
    println("Amper Project - Task 8.3")
    
    // Create analyzer instance
    val analyzer = WeatherStationAnalyzer()
    
    // Add sample weather stations
    val sampleStations = listOf(
        WeatherStation("STN001", "Central Park", "New York, NY", 25.0),
        WeatherStation("STN002", "Downtown", "Chicago, IL", 182.0),
        WeatherStation("STN003", "Harbor View", "San Francisco, CA", 16.0),
        WeatherStation("STN004", "Mountain Top", "Denver, CO", 1609.0),
        WeatherStation("STN005", "Beach Front", "Miami, FL", 2.0)
    )
    
    sampleStations.forEach { analyzer.addStation(it) }
    
    // Generate sample temperature readings
    val sampleReadings = generateSampleReadings(sampleStations)
    analyzer.addAllReadings(sampleReadings)
    
    // Display basic information
    analyzer.displayStations()
    
    // Run comprehensive analysis
    runComprehensiveAnalysis(analyzer)
    
    // Demonstrate specific queries
    demonstrateQueries(analyzer)
    
    // Show overall statistics
    displayOverallStatistics(analyzer)
}

/**
 * Generates sample temperature readings for demonstration
 */
fun generateSampleReadings(stations: List<WeatherStation>): List<TemperatureReading> {
    val readings = mutableListOf<TemperatureReading>()
    val random = Random(42) // Fixed seed for reproducible results
    
    stations.forEach { station ->
        // Generate different temperature patterns based on location
        val baseTemp = when (station.location) {
            "New York, NY" -> 15.0
            "Chicago, IL" -> 12.0
            "San Francisco, CA" -> 18.0
            "Denver, CO" -> 8.0
            "Miami, FL" -> 28.0
            else -> 20.0
        }
        
        // Generate 24 hourly readings for 3 days
        for (day in 1..3) {
            for (hour in 0..23) {
                val temperature = baseTemp + 
                    (sin(hour * Math.PI / 12) * 8) + // Daily cycle
                    (random.nextDouble() * 4 - 2) // Random variation
                
                val timestamp = "2024-01-${String.format("%02d", day)} ${String.format("%02d", hour)}:00"
                
                readings.add(
                    TemperatureReading(
                        stationId = station.id,
                        timestamp = timestamp,
                        temperature = temperature
                    )
                )
            }
        }
    }
    
    return readings
}

/**
 * Runs comprehensive temperature analysis
 */
fun runComprehensiveAnalysis(analyzer: WeatherStationAnalyzer) {
    println("\n" + "=".repeat(50))
    println("COMPREHENSIVE TEMPERATURE ANALYSIS")
    println("=".repeat(50))
    
    // Display detailed analysis
    analyzer.displayDetailedAnalysis()
    
    // Show daily averages for each station
    println("\n=== Daily Averages ===")
    analyzer.displayStations()
    analyzer.stations.forEach { station ->
        val dailyAverages = analyzer.calculateDailyAverages(station.id)
        println("\n${station.name} Daily Averages:")
        dailyAverages.forEach { (date, avg) ->
            println("  $date: ${avg.format(1)}°C")
        }
    }
}

/**
 * Demonstrates specific temperature queries
 */
fun demonstrateQueries(analyzer: WeatherStationAnalyzer) {
    println("\n" + "=".repeat(50))
    println("SPECIFIC TEMPERATURE QUERIES")
    println("=".repeat(50))
    
    // Find stations above certain temperature
    val warmStations = analyzer.findStationsAboveTemperature(20.0)
    println("Stations with average temperature above 20°C: $warmStations")
    
    // Find stations below certain temperature
    val coolStations = analyzer.findStationsBelowTemperature(15.0)
    println("Stations with average temperature below 15°C: $coolStations")
    
    // Show min/max for each station
    println("\n=== Extreme Temperatures ===")
    analyzer.stations.forEach { station ->
        val minTemp = analyzer.findMinTemperature(station.id)
        val maxTemp = analyzer.findMaxTemperature(station.id)
        val range = analyzer.calculateTemperatureRange(station.id)
        
        println("${station.name}:")
        println("  Min: ${minTemp?.temperature?.format(1)}°C at ${minTemp?.timestamp}")
        println("  Max: ${maxTemp?.temperature?.format(1)}°C at ${maxTemp?.timestamp}")
        println("  Range: ${range?.format(1)}°C")
    }
}

/**
 * Displays overall statistics
 */
fun displayOverallStatistics(analyzer: WeatherStationAnalyzer) {
    println("\n" + "=".repeat(50))
    println("OVERALL STATISTICS")
    println("=".repeat(50))
    
    val overallStats = analyzer.getOverallStatistics()
    
    println("""
        Total Stations: ${overallStats.totalStations}
        Total Readings: ${overallStats.totalReadings}
        Overall Average Temperature: ${overallStats.overallAverage.format(1)}°C
        Overall Temperature Range: ${overallStats.overallMin.format(1)}°C to ${overallStats.overallMax.format(1)}°C
        Most Active Station: ${overallStats.mostActiveStation}
        Station with Highest Average: ${overallStats.stationWithHighestAvg}
        Station with Lowest Average: ${overallStats.stationWithLowestAvg}
    """.trimIndent())
}

/**
 * Function to demonstrate data processing with Kotlin collections
 */
fun demonstrateCollectionOperations() {
    println("\n" + "=".repeat(50))
    println("KOTLIN COLLECTIONS DEMONSTRATION")
    println("=".repeat(50))
    
    val numbers = listOf(15.2, 18.7, 12.3, 22.1, 19.8, 14.5, 25.0, 16.7)
    
    println("Original temperatures: $numbers")
    println("Sorted: ${numbers.sorted()}")
    println("Average: ${numbers.average().format(1)}")
    println("Max: ${numbers.maxOrNull()?.format(1)}")
    println("Min: ${numbers.minOrNull()?.format(1)}")
    println("Filtered (>20): ${numbers.filter { it > 20.0 }}")
    println("Mapped (to Fahrenheit): ${numbers.map { it * 9/5 + 32 }}")
    
    val grouped = numbers.groupBy { if (it > 20.0) "Hot" else "Cool" }
    println("Grouped: $grouped")
}
