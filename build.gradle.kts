plugins {
    id("java")
    kotlin("jvm") version "1.8.20"
}

group = "dev.pfilaretov42"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // all the code in the project is actually a test, so junit added to implementation, not testImplementation
    implementation(platform("org.junit:junit-bom:5.9.1"))
    implementation("org.junit.jupiter:junit-jupiter")
    implementation(kotlin("stdlib-jdk8"))

    compileOnly("org.projectlombok:lombok:1.18.28")
    annotationProcessor("org.projectlombok:lombok:1.18.28")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(19)
}