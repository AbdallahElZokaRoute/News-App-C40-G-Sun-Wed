// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    kotlin("plugin.serialization") version "2.0.20"
    alias(libs.plugins.compose.compiler)
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
}