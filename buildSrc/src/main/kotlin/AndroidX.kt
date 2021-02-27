/**
 * des：管理AndroidX相关依赖
 *
 * @author: Muppet
 * @date:   2021/2/15
 */
object AndroidX {

    const val coreKtx           = "androidx.core:core-ktx:1.3.2"
    const val appcompat         = "androidx.appcompat:appcompat:1.2.0"
    const val constraintLayout  = "androidx.constraintlayout:constraintlayout:2.0.4"

    val lifecycle = Lifecycle
    object Lifecycle {
        private const val lifecycle_version = "2.2.0"
        const val extensions    = "androidx.lifecycle:lifecycle-extensions:${lifecycle_version}"
        const val viewModelKtx  = "androidx.lifecycle:lifecycle-viewmodel-ktx:${lifecycle_version}"
        const val liveDataKtx   = "androidx.lifecycle:lifecycle-livedata-ktx:${lifecycle_version}"
    }

    val navigation = Navigation
    object Navigation {
        private const val navigation_version = "2.3.1"
        const val fragmentKtx   = "androidx.navigation:navigation-fragment-ktx:${navigation_version}"
        const val uiKtx         = "androidx.navigation:navigation-ui-ktx:${navigation_version}"
    }
}