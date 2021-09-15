//
//  TodosViewModel.swift
//  iosKintro
//
//  Created by zahn on 13/09/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import Shared

extension TodosViewModel {
    enum Statuses {
        case done
        case todo
    }
}

class TodosViewModel : ObservableObject {
    @Published private(set) var todos = [Todo]()
    @Published private(set) var todo : Todo?
    
    var groupedTodos: [Statuses: [Todo]] {
        var groups : [Statuses: [Todo]] = [:]
        groups[.todo] = todos.filter { $0.completed }
        groups[.done] = todos.filter { !$0.completed }
        return groups
    }
    
    @Published private(set) var users = [User]()
    @Published private(set) var user : User?
    @Published private(set) var loading = false
    
    public let repository = Todos()

    func getTodo(id: Int) {
        self.loading = true
        self.repository.getTodo(id: Int32(id), completionHandler: { todo, error in
            self.loading = false
            if todo != nil {
                self.todo = todo
            }
        })
    }

    func getTodos() {
        self.loading = true
        self.repository.getTodos(completionHandler: { todos, error in
            self.loading = false
            if let todos = todos {
                self.todos = todos
            }
        })
    }
    
    func getUser(id: Int32) {
        self.loading = true
        self.repository.getUser(id: Int32(id), completionHandler: { user, error in
            self.loading = false
            if user != nil {
                self.user = user
            }
        })
    }
    
    func getUsers() {
        self.loading = true
        self.repository.getUsers(completionHandler: { users, error in
            self.loading = false
            if let users = users {
                self.users = users
            }
        })
    }
}

