
plugins {
    application // <1>
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(files("utf8chr.jar"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_5
    targetCompatibility = JavaVersion.VERSION_1_5
}

application {
    mainClass.set("rege.rege.nbtutils.test.Test1") // <5>
}