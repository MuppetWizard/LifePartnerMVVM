/**
 * des： 管理kotlin相关依赖
 *
 * @author: Muppet
 * @date:   2021/2/15
 */
object Kotlin {
    const val kotlinVersion = "1.4.21"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"

    val coroutines = Coroutines
    object Coroutines {
        private const val coroutines_version = "1.3.7"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"
    }
}