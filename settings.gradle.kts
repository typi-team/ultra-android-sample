pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    defaultLibrariesExtensionName.set("projectLibs")
    versionCatalogs {
        create("libs") {
            from(files("gradle/libs.versions.toml"))
        }
    }
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://gitlab.typi.team/api/v4/projects/19/packages/maven")
            name = "GitLab"
            credentials(HttpHeaderCredentials::class) {
                name = "Deploy-Token"
                value = "8XvktUFCRs4bxK9_iwHd"
            }
            authentication {
                create<HttpHeaderAuthentication>("header")
            }
        }
        maven(url = "https://jitpack.io")
    }
}

rootProject.name = "Ultra"

include(":sample")