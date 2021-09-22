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
                    }
                    
                }
                .listStyle(InsetGroupedListStyle())
            }
            
            .if(store.state.loading) {
                $0.overlay(
                    ZStack {
                        Color.layout
                            .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .center)
                        ProgressView()
                    }
                )
            }
            
            .navigationTitle("Todos")
            .navigationBarTitleDisplayMode(.large)
            .navigationBarItems(
                leading: FilterView(filter: $filter),
                trailing: Button(action: { self.isPresenting.toggle() }) {
                    Text("readme")
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
                .font(.body)
        }
    }
}

struct FilterView: View {
    @EnvironmentObject var store : ObservableTodosStore
    @Binding var filter: TodosFilter
    var body: some View {
        Picker("Status filter", selection: $filter) {
            Text("Todo").tag(TodosFilter.todo)
            Text("Done").tag(TodosFilter.done)
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
                .font(.system(size: 32, weight: .black, design: .rounded))
                .padding()
            Text("lorem")
                .multilineTextAlignment(.center)
                .font(.callout)
                .padding(40)
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
