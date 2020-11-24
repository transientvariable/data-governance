// ======================================================================
// Log Event : Build
// ======================================================================

// Plugins
// ========================================

plugins {
    java
    application

    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    id("io.freefair.lombok")              version "5.0.0"
}

// GAV
// ========================================

group       = "com.github.transientvariable"
version     = "1.0.0-SNAPSHOT"
description = "Log Event"

// Java Version
// ========================================

java {
    sourceCompatibility = JavaVersion.VERSION_14
    targetCompatibility = JavaVersion.VERSION_14
}

// Application
// ========================================

application {
    mainClassName = "com.github.transientvariable.logevent.Main"
}

// Dependency Management
// ========================================

extra["fasterxml_jackson"] = "2.10.3"
extra["junit"]             = "5.6.1"

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:${project.extra["junit"]}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${project.extra["junit"]}")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    implementation("com.fasterxml.jackson.core:jackson-databind:${project.extra["fasterxml_jackson"]}")
    implementation("com.fasterxml.jackson.module:jackson-module-afterburner:${project.extra["fasterxml_jackson"]}")
}

// Repositories
// ========================================

repositories {
    jcenter()
    mavenCentral()
}

// Tasks
// ========================================

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes(
            mapOf("Implementation-Title" to project.name,
                  "Implementation-Vendor" to "Matt Nicholls",
                  "Implementation-Version" to project.version)
        )
    }
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform()
}
