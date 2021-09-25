//
//  ContentViewModel.swift
//  iosKintro
//
//  Created by zahn on 12/09/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import Shared

class CounterViewModel : ObservableObject {
    @Published private(set) var count: Int = 0
    private let repository = Counter()

    func increment() {
        self.count = Int(self.repository.incrementCounter())
    }
    
    func decrement() {
        self.count = Int(self.repository.decrementCounter())
    }
    
    func reset() {
        self.count = Int(self.repository.resetCounter())
    }
}
