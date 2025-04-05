package tsu.pod.bootcamp.m4_ooo_conventions

fun main() {

    println("Late Initialization")

    val activity = Activity()
    println(" > events: ${activity.events}")
    activity.start()
    println(" > events: ${activity.events}")

    /**
     * Late Initialization (lateinit)
     * - Initialize a property not in a constructor, but in a
     *   designated method.
     * - Might produce: UninitializedPropertyAccessException
     *   <name> has not been initialized
     * - Property Type
     *   - Cannot be 'val', it's always mutable
     *   - Cannot be nullable
     *   - Cannot be primitive
     */
    activity.setup()
    println(" > data: ${activity.data}")
    activity.setup()
    println(" > data: ${activity.data}")

}

class Activity {

    // Has to be defined as 'null'
    var events: List<String>? = null
    fun start() {
        events = listOf("started")
    }

    /**
     * Check initialized properties
     */
    lateinit var data: Data
    fun setup() {
        println("   > isInitialized: ${this::data.isInitialized}")
        data = Data(0)
    }

}

data class Data(val accessCount: Int)