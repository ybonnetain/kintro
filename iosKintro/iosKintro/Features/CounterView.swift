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
                    .applyDescriptionStyle()
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
                .applyTitleStyle()
                .colorInvert()
            Text(String(viewModel.count))
                .lineLimit(1)
                .minimumScaleFactor(0.4)
                .applyNumberStyle()
                .colorInvert()
            Text("thing(s)")
                .applyDescriptionStyle()
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
    @EnvironmentObject var viewModel: CounterViewModel

    var body: some View {
        Button(action: { viewModel.increment() }) {
            VStack {
                Text("Next")
                    .applyTitleStyle()
                Image(systemName: "goforward.plus")
                    .applyNumberStyle()
                Text("Fibonacci")
                    .applyDescriptionStyle()
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
