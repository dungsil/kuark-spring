plugins {
  id("org.springframework.boot") version "2.6.3" apply false
  id("io.spring.dependency-management") version "1.0.11.RELEASE" apply false
}

allprojects {
  group = "io.kuark.spring"
  version = "0.1.0-SNAPSHOT"

  repositories {
    mavenCentral()
  }
}
