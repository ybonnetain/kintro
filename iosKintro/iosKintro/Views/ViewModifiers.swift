//
//  ViewModifiers.swift
//  ViewModifiers
//
//  Created by zahn on 02/10/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

// Stack modifiers
//

struct CounterTile: ViewModifier {
    func body(content: Content) -> some View {
        content
            .frame(minWidth: 0, maxWidth: .infinity, minHeight: 100)
            .padding(40)
            .background(Color.theme.secondary)
            .cornerRadius(16)
    }
}

struct AdderTile: ViewModifier {
    func body(content: Content) -> some View {
        content
            .frame(minWidth: 0, maxWidth: .infinity, minHeight: 100)
            .padding(40)
            .background(Color.theme.surface)
            .cornerRadius(16)
    }
}

struct TodoTile: ViewModifier {
    func body(content: Content) -> some View {
        content
            .frame(minWidth: 0, maxWidth: .infinity, minHeight: 100)
            .background(Color.white)
            .cornerRadius(16)
            .contentShape(RoundedRectangle(cornerRadius: 16, style: .continuous)) // to keep context menu mode rounded
            .padding()
    }
}

// SAV modifiers
//

struct IgnoredSafeAreaModifier: ViewModifier {
    func body(content: Content) -> some View {
        Group {
            if #available(iOS 14, *) {
                AnyView(content.ignoresSafeArea(.all, edges: .all))
            } else {
                content.edgesIgnoringSafeArea(.all)
                content
            }
        }
    }
}

struct IgnoredSafeAreaBottomModifier: ViewModifier {
    func body(content: Content) -> some View {
        Group {
            if #available(iOS 14, *) {
                AnyView(content.ignoresSafeArea(.all, edges: .bottom))
            } else {
                content.edgesIgnoringSafeArea(.bottom)
                content
            }
        }
    }
}
