//
//  ContentViewModel.swift
//  iosKintro
//
//  Created by zahn on 12/09/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import Shared

class ObservableCounter : ObservableObject {
    @Published private(set) var count: Int = 0
    
    private let counter = Counter()
    private var counterWatcher: Closeable?
    
    init() {
        counterWatcher = self.counter.watchCount().watch(block: { [weak self] count in
            self?.count = Int(truncating: count)
        })
    }

    func increment() {
        self.counter.incrementCounter()
    }
    
    func decrement() {
        self.counter.decrementCounter()
    }
    
    func reset() {
        self.counter.resetCounter()
    }
    
    deinit {
        counterWatcher?.close()
    }
}
