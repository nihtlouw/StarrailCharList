buildscript {
    project.extra["compose_ui_version"] = "1.2.1"
    project.extra["compose_compiler_version"] = "1.2.0"
}
plugins {
    id("com.android.application") version "7.3.1" apply false
    id("com.android.library") version "7.3.1" apply false
    id("org.jetbrains.kotlin.android") version "1.7.0" apply false
}

