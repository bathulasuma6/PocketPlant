# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.

# Sprint 1 scope: no reflection-driven libraries yet. This file is left in place so
# Sprint 5's minification pass can simply flip isMinifyEnabled to true and layer
# rules for Retrofit, Room and any other libraries as they are introduced.

# Keep Kotlin Coroutines internals (silence warnings).
-dontwarn kotlinx.coroutines.**
