import SwiftUI
import Shared

@main
struct iOSApp: App {
    init() {
        KoinKt.doInitKoin()
        setupUIKit()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
