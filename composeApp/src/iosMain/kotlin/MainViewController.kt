import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController
import ui.App

fun MainViewController(latitude: Double, longitude: Double) = ComposeUIViewController { App(latitude, longitude) }
