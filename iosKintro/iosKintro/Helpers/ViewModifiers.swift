//
//  ViewModifiers.swift
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
}

// Text modifiers
//

struct Title: ViewModifier {
    func body(content: Content) -> some View {
        content
            .font(.system(size: 32, weight: .black, design: .rounded))
            .foregroundColor(Color.black)
    }
}

struct Number: ViewModifier {
    func body(content: Content) -> some View {
        content
            .font(.system(size: 40, weight: .heavy, design: .rounded))
            .foregroundColor(.black)
    }
}

struct Description: ViewModifier {
    func body(content: Content) -> some View {
        content
            .font(.headline)
            .foregroundColor(.black)
    }
}

// Stack modifiers
//

struct CounterTile: ViewModifier {
    func body(content: Content) -> some View {
        content
            .frame(minWidth: 0, maxWidth: .infinity, minHeight: 100)
            .padding(40)
            .background(Color.orange)
            .cornerRadius(16)
    }
}

struct AdderTile: ViewModifier {
    func body(content: Content) -> some View {
        content
            .frame(minWidth: 0, maxWidth: .infinity, minHeight: 100)
            .padding(40)
            .background(Color.white)
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
