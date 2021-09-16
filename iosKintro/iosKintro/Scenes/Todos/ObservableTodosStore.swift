//
//  ObservableTodosStore.swift
//  ObservableTodosStore
//
//  Created by zahn on 16/09/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import Shared

class ObservableTodosStore: ObservableObject {
    @Published private(set) var state: TodosState = TodosState(todos: [], loading: false)
    @Published private(set) var sideEffect: TodosSideEffect?
    
    let store = TodosStore()
    
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
    
    convenience init(withInitialState state: TodosState) { // we can use in previews for initial state
        self.init()
        self.state = state
    }
    
    public func dispatch(_ action: TodosAction) {
        store.dispatch(action: action)
    }
    
    deinit {
        stateWatcher?.close()
        sideEffectWatcher?.close()
    }
}
