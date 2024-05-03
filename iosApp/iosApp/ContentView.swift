import UIKit
import SwiftUI
import ComposeApp


struct ComposeView: UIViewControllerRepresentable {
    @StateObject private var locationManager = LocationManager()

    func makeUIViewController(context: Context) -> UIViewController {
        locationManager.checkLocationAuthorization()
        let coordinates = locationManager.lastKnownLocation
        let actualWeatherViewModel = ActualWeatherViewModel()
        let dailyWeatherViewModel = DailyWeatherViewModel()
        let weeklyWeatherViewModel = WeeklyWeatherViewModel()
        actualWeatherViewModel.setLatAndLong(latitude: coordinates!.latitude, longitude: coordinates!.longitude)
        dailyWeatherViewModel.setLatAndLong(latitude: coordinates!.latitude, longitude: coordinates!.longitude)
        weeklyWeatherViewModel.setLatAndLong(latitude: coordinates!.latitude, longitude: coordinates!.longitude)
        let mainViewControllerKT = MainViewControllerKt.MainViewController(actualWeatherViewModel: actualWeatherViewModel,
        dailyWeatherViewModel: dailyWeatherViewModel,
        weeklyWeatherViewModel: weeklyWeatherViewModel)
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


