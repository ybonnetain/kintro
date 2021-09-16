//
//  TodoDetailView.swift
//  TodoDetailView
//
//  Created by zahn on 14/09/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct TodoDetailView: View {
    @Environment(\.isPreview) var isPreview
    
    let todo : Todo
    var body: some View {
        ZStack {
            Color.layout
            VStack {
                TodoDetailTileView(heading: "User") {
                    Text("name")
                    Text("username")
                    Text("email")
                }
                
                TodoDetailTileView(heading: "Todo") {
                    Text(todo.title)
                        .multilineTextAlignment(.center)
                        .modifier(Title())
                }
                
                Spacer()
                
            }
            .navigationTitle("Detail")
            .navigationBarTitleDisplayMode(.inline)
        }

        .if(false) {
            $0.overlay(
               ZStack {
                   Color.layout
                       .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .center)
                   ProgressView()
               }
           )
        }
        .onAppear {
            if !isPreview { print("load side effect here" ) }
        }
    }
}

struct TodoDetailTileView<Content: View>: View {
    var heading : String
    @ViewBuilder var content: Content
    
    var body: some View {
        ZStack(alignment: .leading) {
            Color.white
            VStack(alignment: .leading) {
                Text(heading)
                    .modifier(Description())
                Divider()
                content
            }
            .padding()
        }
        .frame(minWidth: 0, maxWidth: .infinity, minHeight: 100, maxHeight: 180) // TODO verify
        .cornerRadius(16)
        .padding()
    }
}

struct TodoDetailView_Previews: PreviewProvider {
    
    static let todo = Todo(id: 1, userId: 1, title: "Mon super todo", completed: false)
    static var previews: some View {
        TodoDetailView(todo: todo)
            .preferredColorScheme(.dark)
    }
}
