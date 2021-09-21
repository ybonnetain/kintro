//
//  ObservableUsersStore.swift
//  iosKintro
//
//  Created by zahn on 16/09/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import Shared

class ObservableUsersStore: ObservableObject {
    let store = UsersStore()
    
    @Published private(set) var state = UsersStore.Companion().getInitialState()
    @Published private(set) var sideEffect: UsersSideEffect?
    
    var stateWatcher : Closeable?
    var sideEffectWatcher : Closeable?
    
    init() {
        stateWatcher = self.store.watchState().watch(block: { [weak self] state in
            self?.state = state
        })
        
        sideEffectWatcher = self.store.watchSideEffect().watch(block: { [weak self] state in
            self?.sideEffect = state
        })
    }
    
    convenience init(withInitialState state: UsersState) { // we can use in previews
        self.init()
        self.state = state
    }
    
    public func dispatch(_ action: UsersAction) {
        store.dispatch(action: action)
    }
    
    deinit {
        stateWatcher?.close()
        sideEffectWatcher?.close()
    }
}

