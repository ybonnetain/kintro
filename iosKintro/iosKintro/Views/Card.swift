//
//  Card.swift
//  Card
//
//  Created by zahn on 25/09/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct Card<Content: View>: View {
    var heading : String
    @ViewBuilder var content: Content
    
    var body: some View {
        ZStack(alignment: .leading) {
            Color.theme.background
            VStack(alignment: .leading) {
                Text(heading).h2()
                Divider()
                content
                    .foregroundColor(Color.black)
                Spacer()
            }
            .padding()
        }
        .frame(minWidth: 0, maxWidth: .infinity, minHeight: 100, maxHeight: 180)
        .cornerRadius(16)
        .padding()
    }
}

struct Card_Previews: PreviewProvider {
    static var previews: some View {
        ZStack {
            Color.white
            Card(heading: "My title") {
                Text("line 1")
                Text("line 2")
                Text("line 3")
                Text("line 4")
                Text("line 5")
            }
        }
    }
}
