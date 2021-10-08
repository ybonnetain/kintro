//
//  TodosView.swift
//  iosKintro
//
//  Created by zahn on 13/09/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct TodosView: View {
    @Environment(\.isPreview) var isPreview
    @EnvironmentObject var store : ObservableTodosStore
    
    @State private var isPresenting : Bool = false
    @State private var filter = TodosFilter.todo // this is a little bit of a problem as we may start UI state not in sync with app state
    
    var body: some View {
        NavigationView {
            VStack (alignment: .leading) {
                List {
                    Section(
                        header: Text(store.state.filter == .todo ? "header_todo" : "header_done"),
                        footer: Text(store.state.filter == .todo ? "footer_todo" : "footer_done")
                    ) {
                        ForEach(TodosSelector().filteredTodos(state: store.state), id: \.id) { t in
                            TodoListItem(item: t)
                        }
                        .listRowBackground(Color.theme.surface)
                    }
                    
                }
                .listStyle(InsetGroupedListStyle())
            }
            
            .if(store.state.loading) {
                $0.overlay(
                    ZStack {
                        Color.theme.background
                            .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .center)
                        ProgressView()
                    }
                )
            }
                        
            // iOS 15 conditionned view extension for refreshable
            .refresh(action: {
                // refreshable is designed for async/await
                // therefor it will auto-set to a background context
                // however our store was created from main and
                // calling it from a global background context will result in a
                // `kotlin.native.IncorrectDereferenceException` unless we explicitly
                // call it from main (coroutine scope for iOS managed internally)
                DispatchQueue.main.async {
                    store.dispatch(TodosAction.Load())
                }
            }) {
                $0
            }

            .navigationTitle("Todos")
            .navigationBarTitleDisplayMode(.large)
            .navigationBarItems(
                leading: FilterView(filter: $filter),
                trailing: Button(action: { self.isPresenting.toggle() }) {
                    Text("readme").subtitle2()
                }
            )
        }
        .onAppear() {
            if !isPreview { store.dispatch(TodosAction.Load()) }
        }
        .onReceive(store.$sideEffect) { value in
            if let errorMessage = (value as? TodosSideEffect.Error)?.error.message {
               print("error \(errorMessage)")
            }
        }
        .sheet(isPresented: $isPresenting, content: {
            Readme()
        })
    }
}

struct TodoListItem: View {
    let item : Todo
    
    var body: some View {
        NavigationLink(destination: TodoDetailView(todo: item)) {
            Text(item.title)
        }
    }
}

struct FilterView: View {
    @EnvironmentObject var store : ObservableTodosStore
    @Binding var filter: TodosFilter
    var body: some View {
        Picker("Status filter", selection: $filter) {
            Text("Todo").body1().tag(TodosFilter.todo)
            Text("Done").body1().tag(TodosFilter.done)
        }
        .pickerStyle(.segmented)
        .onChange(of: filter) { value in // ios 14+
            store.dispatch(TodosAction.Filter(filter: value))
        }
    }
}

struct Readme: View {
    var body: some View {
        VStack {
            Image("coin")
                .resizable()
                .scaledToFit()
                .clipShape(Circle())
                .padding(60)
            Text("quack")
                .multilineTextAlignment(.center)
                .font(.system(size: Scale.plus1, weight: .black, design: .rounded))
                .padding()
            Text("lorem")
                .multilineTextAlignment(.center)
                .font(.callout)
                .padding(Scale.plus1)
        }
    }
}

struct TodosView_Previews: PreviewProvider {
    static let dummyTodo = Todo(id: 1, userId: 1, title: "dummy todo", completed: false)
    static var previews: some View {
        TodosView()
            .environmentObject(ObservableTodosStore(withInitialState: TodosState(todos: [dummyTodo], loading: false, filter: TodosFilter.todo)))
        
        ForEach(ColorScheme.allCases, id: \.self) {
            Readme()
                .preferredColorScheme($0)
        }
        
        ForEach(["ja", "fr", "en"], id: \.self) { lang in
            Readme()
                .environment(\.locale, Locale(identifier: lang))
        }
        
    }
}
