//
//  ContentViewModel.swift
//  iosKintro
//
//  Created by zahn on 12/09/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import Shared

class ContentViewModel : ObservableObject {
    @Published var todos = [Todo]()
    @Published var todo : Todo?
    @Published var count: Int

    public let repository : Repository

    init(repository: Repository) {
        self.repository = repository
        self.count = Int(self.repository.incrementCounter())
    }

    func increment() {
        self.count = Int(self.repository.incrementCounter())
    }

    func getTodo(id: Int) {
        self.repository.getTodo(id: Int32(id), completionHandler: { todo, error in
            if todo != nil {
                self.todo = todo
            }
        })
    }

    func getTodos() {
        self.repository.getTodos(completionHandler: { todos, error in
            if let todos = todos {
                self.todos = todos
            }
        })
    }
}
