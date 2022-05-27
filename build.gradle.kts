plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    implementation("org.seleniumhq.selenium:selenium-java:3.141.59")
    implementation("io.cucumber:cucumber-java:5.6.0")
    implementation("org.yaml:snakeyaml:1.30")

    testImplementation("io.cucumber:cucumber-junit:5.6.0")
    testImplementation("org.hamcrest:hamcrest:2.2")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}