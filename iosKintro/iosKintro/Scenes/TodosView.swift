//
//  TodosView.swift
//  iosKintro
//
//  Created by zahn on 13/09/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import UIKit
import Shared


struct TodosView: View {
    @EnvironmentObject var viewModel : TodosViewModel
    
    init() {
        let attrs: [NSAttributedString.Key: Any] = [
            .foregroundColor: UIColor.black,
            .font: UIFont.init(descriptor: UIFont.systemFont(ofSize: 32, weight: .black).fontDescriptor.withDesign(.rounded)! , size: 32) // TODO unwrap correctly
        ]
        
        let compactAppearance = UINavigationBarAppearance()
//        compactAppearance.shadowColor = .

        let appearance = UINavigationBarAppearance()
        appearance.largeTitleTextAttributes = attrs
        appearance.shadowColor = .clear
        appearance.backgroundColor = UIColor(hex: 0xf2f2f7)
//appearance.configureWithTransparentBackground()
        
        UINavigationBar.appearance().standardAppearance = compactAppearance
        UINavigationBar.appearance().compactAppearance = compactAppearance
        UINavigationBar.appearance().scrollEdgeAppearance = appearance
// UINavigationBar.appearance().setBackgroundImage(UIImage(), for: .default) // transparent
    }
    
    var body: some View {
        NavigationView {
           
            
            VStack (alignment: .leading) {
                List(viewModel.todos, id: \.id) { todo in
                    TodoListItem(item: todo)
                }
                .listStyle(InsetGroupedListStyle()) // or PlainListStyle()
            }
            
            .if(viewModel.loading) {
                $0.overlay(
                   ZStack {
                       Color.white
                           .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .center)
                       ProgressView()
                   }
               )
            }
            
            .navigationTitle("Todos")
            .navigationBarTitleDisplayMode(.large)
            
//            .navigationBarItems(
//                trailing: Button(action: {}) {
//                    Text("settings")
//                }
//            )
//            .toolbar(content: {
//                 ToolbarItem(placement: .principal, content: {
//                 Text("Todos")
//              })})
            
            
        }
        
        .onAppear() {
            viewModel.getTodos()
        }
                            
    }
}

struct TodosView_Previews: PreviewProvider {
    static var previews: some View {
        TodosView()
            .environmentObject(TodosViewModel())
    }
}

struct TodoListItem: View {
    let item : Todo
    var body: some View {
        NavigationLink(destination: Text(item.title)
                        .navigationTitle("Detail")
        ) {
            VStack(alignment: .leading) {
                Text(item.title)
                    .font(.body)
                
            }
        }
        
    }
}
