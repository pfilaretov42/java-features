plugins {
    id("java")
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

    // lombok does not support Java 21 yet
//    compileOnly("org.projectlombok:lombok:1.18.28")
//    annotationProcessor("org.projectlombok:lombok:1.18.28")
}

tasks.test {
    useJUnitPlatform()
}