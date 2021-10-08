//
//  View+Extension.swift
//  iosKintro
//
//  Created by zahn on 11/09/2021.
//  Copyright © 2021 orgName. All rights reserved.
//
import SwiftUI

extension View {

    // Conditionally chain a view modifier
    // ex: .if(true) { $0.overlay(Color.yellow) }
    // where $0 = anonymous param which is our initial view that was chained by `if` modifier
    //
    @ViewBuilder
    func `if`<Transform: View>(_ condition: Bool, transform: (Self) -> Transform) -> some View {
        if condition { transform(self) }
        else { self }
    }
    
    // Conditionally chain the refreshable modifier
    // api not available before iOS 15
    //
    @ViewBuilder
    func refresh(action: @escaping () -> Void) -> some View {
        if #available(iOS 15.0, *) {
            self
                .refreshable {
                    action()
                }
        } else {
            self
        }
    }
}
