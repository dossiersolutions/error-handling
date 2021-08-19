version = "0.1"

kotlin {
    val jvm = jvm()
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmDoc by creating {
            kotlin.srcDir("src/jvmDoc/kotlin")
            dependsOn(jvmMain)
        }
    }
}