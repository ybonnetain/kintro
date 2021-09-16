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
    @State private var isPresenting : Bool = false
    
    var body: some View {
        NavigationView {
            VStack (alignment: .leading) {
                List {
                    Section(
                        header: Text("Yet to be done .."),
                        footer: Text("La procrastination est une tendance à remettre systématiquement au lendemain des actions. Le « retardataire chronique », appelé procrastinateur, n’arrive pas à se « mettre au travail », surtout lorsque cela ne lui procure pas de satisfaction immédiate.")
                            .font(.footnote)
                    ) {
                        ForEach(viewModel.groupedTodos[TodosViewModel.Statuses.todo] ?? [], id: \.id) { t in
                            TodoListItem(item: t)
                        }
                    }
                    
                    Section(
                        header: Text("Already Done"),
                        footer: Text("Congratulation, what is done is DONE")
                            .font(.footnote)
                    ) {
                        ForEach(viewModel.groupedTodos[TodosViewModel.Statuses.done] ?? [], id: \.id) { t in
                            TodoListItem(item: t)
                        }
                    }
                }
                .listStyle(InsetGroupedListStyle())
            }
            
            .if(viewModel.loading) {
                $0.overlay(
                    ZStack {
                        Color.layout
                            .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .center)
                        ProgressView()
                    }
                )
            }
            
            .navigationTitle("Todos")
            .navigationBarTitleDisplayMode(.large)
            .navigationBarItems(
                trailing: Button(action: { self.isPresenting.toggle() }) {
                    Text("readme")
                }
            )
        }
        .onAppear() {
            viewModel.getTodos()
        }
        
        .sheet(isPresented: $isPresenting, content: {
            Readme()
        })
    }
}

struct TodoListItem: View {
    let item : Todo
    
    var body: some View {
        NavigationLink(destination: TodoDetailView(todo: item)) {
            Text(item.title)
                .font(.body)
        }
    }
}

struct Readme: View {
    var body: some View {
        VStack {
            Image("coin")
                .resizable()
                .scaledToFit()
                .clipShape(Circle())
                .padding(60)
            Text("quack")
                .multilineTextAlignment(.center)
                .font(.system(size: 32, weight: .black, design: .rounded))
                .padding()
            Text("lorem")
                .multilineTextAlignment(.center)
                .font(.callout)
                .padding(40)
        }
    }
}

struct TodosView_Previews: PreviewProvider {
    static var previews: some View {
        ForEach(ColorScheme.allCases, id: \.self) {
            Readme()
                .preferredColorScheme($0)
        }
        
        ForEach(["ja", "fr", "en"], id: \.self) { lang in
            Readme()
                .environment(\.locale, Locale(identifier: lang))
        }
        
    }
}
