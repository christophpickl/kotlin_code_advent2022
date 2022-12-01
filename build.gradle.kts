repositories {
    mavenCentral()
}

plugins {
    kotlin("jvm") version "1.7.21"
}

dependencies {
    testImplementation("io.kotest:kotest-runner-junit5:5.5.1")
    testImplementation("io.kotest:kotest-assertions-core:5.5.1")
    testImplementation("io.kotest:kotest-property:5.5.1")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}