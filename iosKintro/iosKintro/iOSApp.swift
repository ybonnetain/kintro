import SwiftUI
import Shared

@main
struct iOSApp: App {
    @Environment(\.scenePhase) var scenePhase
    
    init() {
        KoinKt.doInitKoin()
        setupUIKit()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
                .onChange(of: scenePhase) { phase in
                    switch (phase) {
                    case .active: self.applicationDidBecomeActive()
                    case .inactive: self.applicationWillResingActive()
                    case .background: self.applicationDidEnterBackground()
                    @unknown default:
                        fatalError("unhandled phase")
                    }
                }
		}
	}
    
    private func applicationDidBecomeActive() {}
    private func applicationWillResingActive() {}
    private func applicationDidEnterBackground() {}
}
