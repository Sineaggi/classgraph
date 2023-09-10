plugins {
    `java-library`
}

/*
    <groupId>io.github.classgraph</groupId>
    <artifactId>classgraph</artifactId>
    <version>4.8.163-SNAPSHOT</version>
 */
group = "io.github.classgraph"
version = "4.8.163-SNAPSHOT"

dependencies {

}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(8)
    }
}

tasks.compileJava {
    sourceCompatibility = "1.7"
    targetCompatibility = "1.7"
    modularity.inferModulePath = true
}

tasks.compileTestJava {
    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"
    options.compilerArgs.plusAssign(listOf("-parameters"))
}

tasks.test {
    javaLauncher = javaToolchains.launcherFor {
        languageVersion = JavaLanguageVersion.of(11)
    }
}

val test1 by tasks.registering(Test::class) {
    // todo: run the same tests as ":test", but with java >8
    //  maybe copy classpath?
    javaLauncher = javaToolchains.launcherFor {
        languageVersion = JavaLanguageVersion.of(17) // or otherwise >8
    }
}

dependencies {
    implementation("io.github.toolfactory:narcissus:1.0.7")
    implementation("io.github.toolfactory:jvm-driver:9.4.3")
}

testing {
    suites {
        named<JvmTestSuite>("test") {
            useJUnitJupiter("5.10.0")
            dependencies {
                implementation("org.assertj:assertj-core:3.24.2")
                implementation("com.google.jimfs:jimfs:1.3.0")
                implementation("org.hibernate.javax.persistence:hibernate-jpa-2.1-api:1.0.2.Final")
                implementation("org.ops4j.pax.url:pax-url-aether:2.6.14")

                // https://mvnrepository.com/artifact/org.slf4j/slf4j-jdk14
                implementation("org.slf4j:slf4j-api:2.0.9")
                implementation("org.slf4j:slf4j-jdk14:2.0.9")

                implementation("javax.enterprise:cdi-api:2.0")

                annotationProcessor("org.openjdk.jmh:jmh-generator-annprocess:1.36")
                implementation("org.openjdk.jmh:jmh-core:1.36")
            }
        }
    }
}

sourceSets {
    main {
        resources {
            // srcDirs("src/module-info")
        }
    }
}

//tasks.jar {
//    this.
//}


tasks.jar {
    into("META-INF/versions/9") {
        //from(sourceSets.named("main").get().output)// .java9.output
        from(files("src/module-info/io.github.classgraph/")) {
            include("module-info.class")
        }
    }

    manifest.attributes(
        "Multi-Release" to "true"
    )
}

