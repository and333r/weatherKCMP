import UIKit
import SwiftUI
import ComposeApp


struct ComposeView: UIViewControllerRepresentable {
    @StateObject private var locationManager = LocationManager()

    func makeUIViewController(context: Context) -> UIViewController {
        locationManager.checkLocationAuthorization()
        let coordinates = locationManager.lastKnownLocation
        let mainViewControllerKT = MainViewControllerKt.MainViewController(latitude: coordinates!.latitude, longitude: coordinates!.longitude)
        return mainViewControllerKT
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
        var body: some View {
                    ComposeView()
                        .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
        }

    
    
}


