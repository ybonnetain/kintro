//
//  Text+Extension.swift
//  iosKintro
//
//  Created by zahn on 02/10/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

extension Text {
    
    func h1() -> Text {
        self
            .font(.custom("Inter-Black", size: 40))
            .fontWeight(.black)
    }
    
    func h2() -> Text {
        self
            .font(.custom("Inter-Bold", size: 32))
            .fontWeight(.bold)
    }
    
    func subtitle1() -> Text {
        self
            .font(.custom("Inter-Bold", size: 24))
            .fontWeight(.bold)
    }
    
    func subtitle2() -> Text {
        self
            .font(.custom("Inter-Regular", size: 18))
            .fontWeight(.regular)
    }
    
    func body1() -> Text {
        self
            .font(.custom("Inter-Regular", size: 20))
            .fontWeight(.regular)
    }
    
    func body2() -> Text {
        self
            .font(.custom("Inter-Light", size: 16))
            .fontWeight(.light)
    }
}
