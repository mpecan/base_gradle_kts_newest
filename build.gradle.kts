buildscript {
    val springBootVersion = "2.0.0.M3"
    val kotlinVersion = "1.1.3-2"

    repositories {
        mavenCentral()
        maven { url = uri("https://repo.spring.io/snapshot") }
        maven { url = uri("https://repo.spring.io/milestone") }
    }

    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
        classpath("org.jetbrains.kotlin:kotlin-noarg:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-allopen:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")

    }
}

apply {
    plugin("kotlin")
    plugin("kotlin-spring")
    plugin("kotlin-jpa")
    plugin("org.springframework.boot")
    plugin("io.spring.dependency-management")
}

version = "0.0.1-SNAPSHOT"

configure<JavaPluginConvention> {
    setSourceCompatibility(1.8)
    setTargetCompatibility(1.8)
}

repositories {
    mavenCentral()

    maven { url = uri("https://repo.spring.io/snapshot") }
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("http://dl.bintray.com/jetbrains/spek") }
}


dependencies {
    /*
    * Why do I need this anywho?
    * */
    val compile = "compile"
    val testCompile = "testCompile"
    val testRuntime = "testRuntime"

    compile("org.springframework.boot:spring-boot-starter-webflux")
    compile("com.h2database:h2")
    compile("org.jetbrains.kotlin:kotlin-stdlib")
    compile("org.jetbrains.kotlin:kotlin-reflect")
    compile("org.springframework.boot:spring-boot-starter-test")
    compile("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")

    compile("com.fasterxml.jackson.module:jackson-module-kotlin:2.8.2")

    testCompile("org.jetbrains.spek:spek-api:1.1.2")
    testRuntime("org.jetbrains.spek:spek-junit-platform-engine:1.1.2")
    testCompile("com.winterbe:expekt:0.5.0")
    testCompile("org.junit.platform:junit-platform-launcher:1.0.0-M4")

    compile("com.fasterxml.jackson.datatype:jackson-datatype-jdk8")
    compile("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

}

