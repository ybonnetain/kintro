import SwiftUI
import Shared

struct ContentView: View {
    @StateObject var viewModel = ContentViewModel(repository: Repository())
    
    var body: some View {
        MainTabbedView()
            .environmentObject(viewModel)
    }
}

extension MainTabbedView {
    enum Tab : CaseIterable, Identifiable {
        var id: Tab { self }
        case counter
        case todos
    }
}

struct MainTabbedView: View {
    @State var selectedTab = Tab.counter

    private var tabsCount: CGFloat = CGFloat(Tab.allCases.count)
    private var badgedTabs = [Tab.todos]

    var body: some View {
        GeometryReader { g in
            TabView(selection: $selectedTab) {
                CounterView()
                    .tabItem({
                        VStack {
                            Image(systemName: computeTabImageName(tab: Tab.counter))
                            Text("Hello")
                        }
                    })
                    .tag(Tab.counter)

                Text("todo todos").tabItem({
                    VStack {
                        Image(systemName: computeTabImageName(tab: Tab.todos))
                        Text("Todos")
                    }
                })
                .tag(Tab.todos)

            }.accentColor(.pink) /// TabView

            ZStack {
                ForEach(Tab.allCases) { t in
                    if (self.badgedTabs.contains(t)) {
                        Circle()
                            .foregroundColor(.blue)
                            .frame(width: 12, height: 12)
                            .offset(x: computeBadgeXOffset(
                                        badgePositionIndex: CGFloat(Tab.allCases.firstIndex(of: t)!),
                                        width: g.size.width), y: -32)
                    }
                }
            }
        }
    }

    private func computeBadgeXOffset(badgePositionIndex: CGFloat, width: CGFloat) -> CGFloat {
        let factorOne : CGFloat = (2 * (badgePositionIndex + 1)) - 0.95
        let factorTwo : CGFloat = (width / ( 2 * self.tabsCount))
        return factorOne * factorTwo
    }

    private func computeTabImageName(tab: Tab) -> String {
        var imageName : String
        switch tab {
        case .counter : imageName = "plus.circle"
        case .todos : imageName = "checkmark.circle"
        }

        return tab == selectedTab ? imageName + ".fill" : imageName
    }
}
