# Watchos KMP DSL

- dependant app ? (in which case KMP dep is probably not need as watchkit app might rely ios app companionship)
- independant app ?

## independant app with KMP pod

- create source set -> `watchosMain`
- add dependencies to the source set (look at being dependant on ios source set ?)
- set kotlin/native target for watchos

```kotlin
// TODO: targets are messed up
val watchosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
    System.getenv("SDK_NAME")?.startsWith("watchos") == true -> ::watchosArm64
    // System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::watchosSimulatorArm64 // is it correct
    else -> ::watchosX64
}
watchosTarget("watchos") {}
```

- set deployment target in `cocoapods > framework > `watchos.deploymentTarget = "8.0"` (so the podspec is updated for watchos)
