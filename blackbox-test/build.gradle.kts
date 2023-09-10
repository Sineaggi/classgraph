plugins {
    `java-library`
}

dependencies {
    implementation(projects.classgraph)
    // https://mvnrepository.com/artifact/org.webjars/webjars-locator-core
    // implementation("org.webjars:webjars-locator-core:0.53")
}

testing {
    suites {
        named<JvmTestSuite>("test") {
            useJUnitJupiter("5.10.0")
        }
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

tasks.test {
    // todo: somehow patch module with additional classes
    jvmArgs("--patch-module=classgraph.blackbox.test.test=/Users/cwalker/git/webjars-locator-core/target/test-classes")
    // jvmArgs =listOf("--patch-module=classgraph.blackbox.test.test=\"/Users/cwalker/git/webjars-locator-core/target/test-classes\""))
}
