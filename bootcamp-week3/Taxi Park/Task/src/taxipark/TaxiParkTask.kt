package taxipark

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
    return trips
        .filter { trip -> trip.driver == driver }
        .map { trip -> trip.passengers.toList() }
        .flatten()
        .groupBy { it.name }
        .filter { it.value.size > 1 }
        .flatMap { it.value }
        .toSet()
}

/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> {
    return setOf()
}

/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
    return null
}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    return true
}