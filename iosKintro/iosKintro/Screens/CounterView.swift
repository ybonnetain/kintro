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
            Color.theme.primary
            VStack {
                HeaderView()
                    
                Text("Here is the Fibonacci counter aimed at demonstrating algorythm sharing with Kotlin")
                    .subtitle2()
                    .multilineTextAlignment(.center)
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
                Text("Hello\nSwiftUI + KMP").h1()
            }
            Spacer()
            Image(systemName: "swift")
                .font(.system(size: 60))
                .foregroundColor(Color.theme.secondary)
        }
        .padding()
    }
}

struct SumView: View {
    @EnvironmentObject var viewModel: ObservableCounter

    var body: some View {
        VStack {
            Text("Count")
                .h2()
                .lineLimit(1)
                .minimumScaleFactor(0.6)
                .colorInvert()
            Text(String(viewModel.count))
                .h1()
                .lineLimit(1)
                .minimumScaleFactor(0.4)
                .colorInvert()
            Text("thing(s)")
                .body1()
                .colorInvert()
        }
        .modifier(CounterTile())
        
        .contentShape(RoundedRectangle(cornerRadius: 16, style: .continuous))
        .contextMenu {
            
            if #available(iOS 15.0, *) {
                Button {
                    //
                } label: {
                    Label("Some disabled action", systemImage: "questionmark.circle")
                }.disabled(true)
                
                Button {
                    viewModel.decrement()
                } label: {
                    Label("Previous term", systemImage: "gobackward.minus")
                }
                
                Divider()
                
                Button(role: .destructive) {
                    viewModel.reset()
                } label: {
                    Label("Reset", systemImage: "trash")
                }
            } else {
                // Fallback on earlier versions
            }
            
        }
        
    }
}

struct AddView: View {
    @EnvironmentObject var viewModel: ObservableCounter

    var body: some View {
        Button(action: { viewModel.increment() }) {
            VStack {
                Text("Next")
                    .h2()
                    .foregroundColor(Color.theme.onSurface)
                Image(systemName: "goforward.plus")
                    .font(.system(size: 40, weight: .heavy, design: .rounded))
                    .foregroundColor(Color.theme.onSurface)
                Text("Fibonacci")
                    .body1()
                    .foregroundColor(Color.theme.onSurface)
            }
            .modifier(AdderTile())
        }
    }
}

struct CounterView_Previews: PreviewProvider {
    static var previews: some View {
        CounterView()
            .environmentObject(ObservableCounter())
    }
}
