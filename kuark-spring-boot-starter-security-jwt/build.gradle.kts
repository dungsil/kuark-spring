import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("java-library")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

group = "io.kuark.spring"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

dependencies {
    // Auth0
    api("com.auth0:java-jwt:3.18.3")
    api("com.auth0:jwks-rsa:0.20.2")

    // Spring boot
    compileOnly("org.springframework.boot:spring-boot-starter")
    compileOnly("org.springframework.boot:spring-boot-starter-web")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    testImplementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation(kotlin("stdlib-jdk8"))
}

tasks {
    withType<JavaCompile> {
        dependsOn(processResources)
    }
}

tasks.withType<Jar> {
    enabled = true
}

tasks.withType<BootJar> {
    enabled = false
}

tasks.withType<Test> {
    useJUnitPlatform()
}
