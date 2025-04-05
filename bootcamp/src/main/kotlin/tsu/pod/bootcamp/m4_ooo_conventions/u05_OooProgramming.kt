package tsu.pod.bootcamp.m4_ooo_conventions

fun main() {

    println("OOO Programming")

    /**
     * Defaults
     * - Any declaration is 'final' by default
     *   - Mark it as 'open' if you don't want to
     * - No 'package private' visibility
     * - internal: visible inside a module
     * - Module: set of Kotlin files compiled together
     *   - IntelliJ module
     *   - Maven project
     *   - Gradle source set
     */

    /**
     * Modifiers
     * - final (default): cannot be overridden
     * - open: can be overridden
     * - abstract: must be overridden (can't have an implementation)
     * - override: (mandatory) overrides a member in superclass or interface
     */

    /**
     * Visibility
     * - public: visible everywhere
     * - internal: visible in the same module
     * - protected: visible in subclasses (not visible in package)
     * - private
     *   - class member: visible inside the class
     *   - top-level declaration: visible in the same file
     */

    /**
     * Packages
     * - You can put several classes inside one file
     * - You can put top-level declarations and properties
     * - Package name doesn't need to correspond to the directory structure
     */

}