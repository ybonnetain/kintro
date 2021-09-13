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
    var body: some View {
        ZStack {
            Color.yellow
            VStack {
                HeaderView()
                    
                Text("The counter is basically what comes right after the hello world")
                    .multilineTextAlignment(.center)
                    .modifier(Description())
                    .colorInvert()
                    .padding()

                HStack(spacing: 16) {
                    SumView()
                    AddView()
                }
                .padding(.horizontal)

                Spacer()
            }
            .padding(.top, 40)
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
    @EnvironmentObject var viewModel: CounterViewModel

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
                .modifier(Description())
                .colorInvert()
        }
        .modifier(CounterTile())
    }
}

struct AddView: View {
    @EnvironmentObject var viewModel: CounterViewModel

    var body: some View {
        Button(action: { viewModel.increment() }) {
            VStack {
                Text("Next")
                    .modifier(Title())
                Image(systemName: "goforward.plus")
                    .modifier(Number())
                Text("Fibonacci")
                    .modifier(Description())
            }
            .modifier(AdderTile())
        }
    }
}

struct CounterView_Previews: PreviewProvider {
    static var previews: some View {
        CounterView()
            .environmentObject(CounterViewModel())
    }
}

