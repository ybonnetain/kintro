//
//  UIAppearancer.swift
//  UIAppearancer
//
//  Created by zahn on 14/09/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import UIKit

func setupUIKit() {
    
    let attrs: [NSAttributedString.Key: Any] = [
        .foregroundColor: UIColor.black,
        .font: UIFont.init(
            descriptor: UIFont.systemFont(ofSize: 32, weight: .black).fontDescriptor.withDesign(.rounded)! , // TODO unwrap correctly
            size: 32
        )
    ]
    
    let appearance = UINavigationBarAppearance()
    appearance.largeTitleTextAttributes = attrs
    appearance.shadowColor = .clear
    appearance.backgroundColor = UIColor(hex: 0xf2f2f7)
    UINavigationBar.appearance().scrollEdgeAppearance = appearance
    // UINavigationBar.appearance().setBackgroundImage(UIImage(), for: .default) // transparent
    
    let compactAppearance = UINavigationBarAppearance()
    UINavigationBar.appearance().standardAppearance = compactAppearance
    UINavigationBar.appearance().compactAppearance = compactAppearance
}
