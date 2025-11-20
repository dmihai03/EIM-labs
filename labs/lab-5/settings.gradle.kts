pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Laborator05"

include(":javaSkelStartedService")
include(":javaSkelStartedServiceActivity")
include(":kotlinSkelStartedService")
include(":kotlinSkelStartedServiceActivity")
include(":javaSolutionStartedService")
include(":javaSolutionStartedServiceActivity")
include(":kotlinSolutionStartedService")
include(":kotlinSolutionStartedServiceActivity")

