
plugins {
    application // <1>
}

repositories {
    mavenCentral()
}

dependencies {
    /* Edit where the UTF8chr Jar file is.
     * You can build it from https://github.com/IAmREGE/UTF8chr
     */
    implementation(files("utf8chr.jar"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_5
    targetCompatibility = JavaVersion.VERSION_1_5
}

application {
    mainClass.set("rege.rege.nbtutils.test.Test1") // <5>
}