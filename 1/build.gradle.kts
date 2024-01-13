version = "0.0.1a1"

base {
    archivesName = "nbtutils"
}

plugins {
    application // <1>
}

tasks.jar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    manifest {
        attributes("Main-Class" to "rege.rege.nbtutils.app.NBTutils")
    }

    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    /* Edit where the UTF8chr Jar file is.
     * You can build it from https://github.com/IAmREGE/UTF8chr
     */
    implementation(files("utf8chr-0.0.1a1.jar"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_5
    targetCompatibility = JavaVersion.VERSION_1_5
    withSourcesJar()
    withJavadocJar()
}

application {
    mainClass.set("rege.rege.nbtutils.test.Test1") // <5>
}