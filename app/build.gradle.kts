plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.0"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation(platform("org.http4k:http4k-bom:4.17.1.0"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-server-jetty")

//    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("io.strikt:strikt-core:0.33.0")
    testImplementation("org.http4k:http4k-client-jetty")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("it.granz.AppKt")
}
