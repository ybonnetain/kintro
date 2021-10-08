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
            Color.theme.background
            VStack {
                HeaderView()
                    
                Text("Here is the Fibonacci counter aimed at demonstrating algorythm sharing with Kotlin")
                    .subtitle2()
                    .multilineTextAlignment(.center)
                    .padding(Scale.base)

                HStack(spacing: Scale.base) {
                    SumView()
                    AddView()
                }
                .padding(.horizontal)

                Spacer()
            }
            .padding(.top, Scale.plus2)
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
                .font(.system(size: Scale.plus3))
                .foregroundColor(Color.theme.primary)
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
                .foregroundColor(Color.theme.onPrimary)
                .lineLimit(1)
                .minimumScaleFactor(0.6)
            Text(String(viewModel.count))
                .h1()
                .foregroundColor(Color.theme.onPrimary)
                .lineLimit(1)
                .minimumScaleFactor(0.4)
            Text("thing(s)")
                .body1()
                .foregroundColor(Color.theme.onPrimary)
        }
        .modifier(CounterTile())
        
        .contentShape(RoundedRectangle(cornerRadius: Scale.base, style: .continuous))
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
                    .font(.system(size: Scale.plus1, weight: .heavy, design: .rounded))
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
