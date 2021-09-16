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
    @Published var count: Int

    public let repository = Counter()

    init() {
        self.count = Int(self.repository.incrementCounter())
    }

    func increment() {
        self.count = Int(self.repository.incrementCounter())
    }
}
