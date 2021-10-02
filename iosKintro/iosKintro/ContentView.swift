import SwiftUI
import Shared

struct ContentView: View {
    @StateObject var counter = ObservableCounter()
    @StateObject var todosStore = ObservableTodosStore()
    @StateObject var usersStore = ObservableUsersStore()
    
    @Environment(\.colorScheme) var colorScheme
    
    var body: some View {
        MainTabbedView()
            .environmentObject(counter)
            .environmentObject(todosStore)
            .environmentObject(usersStore)
        
            .onChange(of: colorScheme, perform: { color in
                setupUIKit(scheme: color)
            })
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
        GeometryReader  { g in
            TabView(selection: $selectedTab) {
                CounterView().tabItem({
                    VStack {
                        Image(systemName: computeTabImageName(tab: Tab.counter))
                            .foregroundColor(.black)
                        Text("Hello")
                    }
                })
                .tag(Tab.counter)

               TodosView().tabItem({
                    VStack {
                        Image(systemName: computeTabImageName(tab: Tab.todos))
                            .foregroundColor(.black)
                        Text("Todos")
                    }
                })
                .tag(Tab.todos)

            }
            .accentColor(.orange) /// TabView

            ZStack {
                ForEach(Tab.allCases) { t in
                    if (self.badgedTabs.contains(t)) {
                        Circle()
                            .foregroundColor(.blue)
                            .frame(width: 12, height: 12)
                            .offset(x: computeBadgeXOffset(
                                        badgePositionIndex: CGFloat(Tab.allCases.firstIndex(of: t)!),
                                        width: g.size.width), y: g.size.height - 45)
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

struct MainTabbedView_Previews: PreviewProvider {
    static var previews: some View {
        MainTabbedView()
            .environmentObject(ObservableCounter())
            .environmentObject(ObservableTodosStore())
            .environmentObject(ObservableUsersStore())
    }
}
