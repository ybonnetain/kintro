//
//  CounterView.swift
//  iosKintro
//
//  Created by zahn on 12/09/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct CounterView: View {
    @StateObject var viewModel = ContentViewModel(repository: Repository())

    var body: some View {
        ZStack {
            Color.yellow
            VStack {
                HeaderView()

                HStack(spacing: 16) {
                    SumView()
                    AddView()
                }
                .padding(.horizontal)

                DescriptionView()
                TodoView()

                Spacer()
            }
            .padding(.top, 40)
            .environmentObject(viewModel)
        }
        .modifier(IgnoredSafeAreaModifier())
    }
}

struct HeaderView: View {
    var body: some View {
        HStack {
            VStack(alignment: .leading, spacing: 2) {
                Text("Hello")
                    .font(.system(.largeTitle, design: .rounded))
                    .fontWeight(.black)
                Text("SwiftUI + KMP")
                    .font(.system(.largeTitle, design: .rounded))
                    .fontWeight(.black)
            }
            Spacer()
            Image(systemName: "swift")
                .font(.system(size: 60))
                .foregroundColor(Color.orange)
        }
        .padding()
    }
}

struct SumView: View {
    @EnvironmentObject var viewModel: ContentViewModel

    var body: some View {
        VStack {
            Text("Count")
                .lineLimit(1)
                .minimumScaleFactor(0.6)
                .modifier(Title())
                .colorInvert()
            Text(String(viewModel.count))
                .lineLimit(1)
                .minimumScaleFactor(0.4)
                .modifier(Number())
                .colorInvert()
            Text("thing(s)")
                .modifier(Excerpt())
                .colorInvert()
        }
        .modifier(CounterTile())
    }
}

struct AddView: View {
    @EnvironmentObject var viewModel: ContentViewModel

    var body: some View {
        Button(action: {
            viewModel.increment()
            viewModel.getTodo(id: viewModel.count)
        }) {
            VStack {
                Text("Next")
                    .modifier(Title())
                Image(systemName: "goforward.plus")
                    .modifier(Number())
                Text("Fibonacci")
                    .modifier(Excerpt())
            }
            .modifier(AdderTile())
        }
    }
}

struct TodoView: View {
    @EnvironmentObject var viewModel: ContentViewModel

    var body: some View {
        if let todo = viewModel.todo {
            VStack {
                Image(systemName: "clock")
                    .font(.system(size: 40))
                    .foregroundColor(Color.orange)
                Text(todo.title)
                    .font(.system(.largeTitle, design: .rounded))
                    .fontWeight(.black)
            }
            .padding()
            .modifier(TodoTile())
            .contextMenu {
                Button {
                    print("C'est fait")
                } label: {
                    Label("C'est fait", systemImage: "checkmark.circle")
                }

                Button {
                    print("A faire")
                } label: {
                    Label("A faire", systemImage: "clock")
                }
            }
        }
    }
}

struct DescriptionView: View {
    var body: some View {
        Text("This is a mix of the Counter and the Todo list, the most famous hello-worlds of front-end development.")
            .foregroundColor(.white)
            .padding()
    }
}

struct CounterView_Previews: PreviewProvider {
    static var previews: some View {
        CounterView()
    }
}

