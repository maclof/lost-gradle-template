val userHomeDir: String = System.getProperty("user.home");

plugins {
    id("java")
}

subprojects {
    val projectName: String = name;

    apply(plugin = "java")

    repositories {
        mavenCentral()
    }

    configurations {
        create("library")
        get("implementation").apply {
            extendsFrom(configurations["library"])
        }
    }

    dependencies {
        annotationProcessor("org.projectlombok:lombok:1.18.24")

        compileOnly("org.projectlombok:lombok:1.18.24")

        compileOnly(files("${userHomeDir}/Documents/LostClientEnhanced.jar"))
    }

    tasks {
        if (path.contains("scripts:")) {
            jar {
                archiveFileName.set("$projectName.jar")
                destinationDirectory.set(file("$userHomeDir/LostClient/Scripts"))
                from(sourceSets.main.get().output)
                from(configurations["library"].map { if (it.isDirectory) it else zipTree(it) })
            }
        }
    }
}