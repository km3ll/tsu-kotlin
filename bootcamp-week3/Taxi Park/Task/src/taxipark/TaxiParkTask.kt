package taxipark

import kotlin.math.ceil
import kotlin.math.roundToInt

/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> {
    fun isFakeDrive(drive: Driver): Boolean {
        return trips.count { trip -> trip.driver == drive } == 0
    }
    return allDrivers.filter(::isFakeDrive).toSet()
}

/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> {
    fun countTrips(passenger: Passenger): Int {
        return trips.count { trip -> trip.passengers.contains(passenger) }
    }
    return allPassengers
        .filter { passenger ->  countTrips(passenger) >= minTrips }
        .toSet()
}

/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> {
    val driverTrips = trips
        .filter { trip -> trip.driver == driver }
    val driverPassengers = driverTrips
        .map { trip -> trip.passengers.toList() }
        .flatten()
    val passengersMoreThanOnce = driverPassengers
        .groupBy { it.name }
        .filter { it.value.size > 1 }
    val uniquePassengers = passengersMoreThanOnce
        .flatMap { it.value }
        .toSet()
    return uniquePassengers
}

/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> {

    fun hadMassDiscounts(passenger: Passenger, discounts: List<Double?>): Boolean {
        val (withDiscount, withoutDiscount) = discounts
            .partition { it != null }
        return withDiscount.size > withoutDiscount.size
    }

    val passengerAndDiscounts: List<Pair<Passenger, List<Double?>>> = trips
        .flatMap { trip ->
            trip.passengers
                .toList()
                .map { passenger -> Pair(passenger, trip.discount) }
        }
        .groupBy { it.first }
        .map { entry -> Pair(entry.key, entry.value.map { it.second }) }

    val passengers = passengerAndDiscounts
        .filter{ it -> hadMassDiscounts(it.first, it.second) }
        .map { it.first }
        .toSet()

    return passengers

}

/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {

    fun newRange(lower: Int, upper: Int): IntRange {
        return lower..upper
    }

    fun getRange(duration: Int): Pair<IntRange, Int> {
        var rangeNotFound = true
        var range = newRange(0, 9)
        do {
            println("range: $range")
            if (duration in range) {
                rangeNotFound = false
            } else {
                range = newRange(range.first.plus(10), range.last.plus(10))
            }
        } while (rangeNotFound)
        return Pair(range, duration)
    }

    val ranges: List<Pair<IntRange, Int>> = trips
        .map { getRange(it.duration) }

    val grouped: List<Pair<IntRange, Int>> = ranges
        .groupBy { it.first }
        .map { Pair(it.key, it.value.size) }
        .sortedByDescending { it.second  }

    return if (grouped.isEmpty()) null
    else grouped.first().first

}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {

    if (trips.isEmpty()) return false

    // Drivers
    val costByDriver: List<Pair<Driver, Double>> = trips
        .map { trip -> trip.driver to trip.cost }
        .groupBy { it.first }
        .map { entry ->
            Pair(entry.key, entry.value.map { it.second }.sum() )
        }
        .sortedByDescending { it.second }
    costByDriver.forEach { println(it) }

    val totalDrivers = allDrivers.size
    val twentyPercent = (totalDrivers * 0.2).toInt()
    val twentyPercentCost = costByDriver.take(twentyPercent).sumOf { it.second }

    val totalCost: Double = trips.sumOf { it.cost }
    val eightyPercent: Double = totalCost * 0.8

    return twentyPercentCost >= eightyPercent

}