kotlin {
    jvm()
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
    }
}