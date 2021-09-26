//
//  TodoDetailView.swift
//  iosKintro
//
//  Created by zahn on 14/09/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct TodoDetailView: View {
    @Environment(\.isPreview) var isPreview
    @EnvironmentObject var store : ObservableUsersStore
    
    let todo : Todo
    
    var body: some View {
        ZStack {
            Color.layout
            VStack {
                Card(heading: "User") {
                    Text(self.findUser(id: todo.userId, store: store)?.name ?? "name")
                    Text(self.findUser(id: todo.userId, store: store)?.username ?? "username")
                    Text(self.findUser(id: todo.userId, store: store)?.email ?? "email")
                }
                
                Card(heading: "Todo") {
                    Text(todo.title)
                        .multilineTextAlignment(.center)
                        .applyTitleStyle()
                }
                
                Spacer()
            }
            .navigationTitle("Detail")
            .navigationBarTitleDisplayMode(.inline)
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
        .onAppear {
            if !isPreview { store.dispatch(UsersAction.Load()) }
        }
        .onReceive(store.$sideEffect) { value in
            if let errorMessage = (value as? UsersSideEffect.Error)?.error.message {
               print("error \(errorMessage)")
            }
        }
    }
    
    private func findUser(id: Int32, store: ObservableUsersStore) -> User? {
        return store.state.users.first(where: { $0.id == Int(id) })
    }
}

struct TodoDetailView_Previews: PreviewProvider {
    
    static let todo = Todo(id: 1, userId: 1, title: "Mon super todo", completed: false)
    static let user = User(id: 1, name: "jean-michel cornalin", username: "jmco", email: "jmcornalin@test12.dev")
    static var previews: some View {
        TodoDetailView(todo: todo)
            .environmentObject(ObservableUsersStore(withInitialState: UsersState(users: [user], loading: false)))
            .preferredColorScheme(.dark)
    }
}
