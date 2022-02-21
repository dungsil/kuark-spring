plugins {
  java

  id("org.springframework.boot")
  id("io.spring.dependency-management")
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation(project(":kuark-spring-boot-starter-security-jwt"))
}
