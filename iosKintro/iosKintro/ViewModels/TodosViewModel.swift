//
//  TodosViewModel.swift
//  iosKintro
//
//  Created by zahn on 13/09/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import Shared

class TodosViewModel : ObservableObject {
    @Published var todos = [Todo]()
    @Published var todo : Todo?
    @Published var loading = false

    public let repository = Todos()

    func getTodo(id: Int) {
        self.repository.getTodo(id: Int32(id), completionHandler: { todo, error in
            if todo != nil {
                self.todo = todo
            }
        })
    }

    func getTodos() {
        self.loading = true
        self.repository.getTodos(completionHandler: { todos, error in
            if let todos = todos {
                self.todos = todos
                self.loading = false
            }
        })
    }
}
