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
    @EnvironmentObject var viewModel : TodosViewModel
    
    let todo : Todo
    var body: some View {
        ZStack {
Color(red: 242/255, green: 242/255, blue: 247/255) // TODO: Color+Extension :)
            VStack {
                TodoDetailTileView(heading: "User") {
                    Text(viewModel.user?.name ?? "name")
                    Text(viewModel.user?.username ?? "username")
                    Text(viewModel.user?.email ?? "email")
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

        .if(viewModel.loading) {
            $0.overlay(
               ZStack {
Color(red: 242/255, green: 242/255, blue: 247/255) // TODO: Color+Extension :)
   .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .center)
                   ProgressView()
               }
           )
        }
        .onAppear(perform: {
            viewModel.getUser(id: todo.userId)
        })
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
            .environmentObject(TodosViewModel())
    }
}
