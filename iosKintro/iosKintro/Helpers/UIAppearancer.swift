//
//  UIAppearancer.swift
//  UIAppearancer
//
//  Created by zahn on 14/09/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import UIKit
import SwiftUI

// TODO: improve color scheme change when user is on a screen with underlying UIKit stuffs below
// TODO: on startup, invoke setupUIKit with device current mode

func setupUIKit(scheme: ColorScheme = .light) {
    
    print("setup UiKit with \(scheme) scheme")
    
    
    let scrollEdgeNavTitleColor = scheme == .dark ? UIColor.white : UIColor.black
    let scrollEdgeNavBackgroundColor = scheme == .dark ? UIColor.black : UIColor(hex: 0xf2f2f2)
    let tableBackgroundColor = scheme == .dark ? UIColor.black : UIColor(hex: 0xf2f2f2)
    
    
    // navigation scroll edge style (with large title)
    //
    let attrs: [NSAttributedString.Key: Any] = [
        .foregroundColor: scrollEdgeNavTitleColor,
        .font: UIFont.init(
            descriptor: (UIFont(name: "Inter-Black", size: 32)?.fontDescriptor)!,
            // ex. roundable system font
            // descriptor: UIFont.systemFont(ofSize: 32, weight: .black).fontDescriptor.withDesign(.rounded)! ,
            size: 32
        )
    ]
    
    let appearance = UINavigationBarAppearance()
    appearance.largeTitleTextAttributes = attrs
    appearance.shadowColor = .clear
    appearance.backgroundColor = scrollEdgeNavBackgroundColor
    UINavigationBar.appearance().scrollEdgeAppearance = appearance
    
    
    // navigation standard and compact appearance
    //
    let compactAppearance = UINavigationBarAppearance()
    UINavigationBar.appearance().standardAppearance = compactAppearance
    UINavigationBar.appearance().compactAppearance = compactAppearance
    
    // UITableView is still underlying SwiftUI's List
    // and its background color is not controllable from View
    //
    // UITableView.appearance().separatorStyle = .none
    // UITableViewCell.appearance().backgroundColor = .green
     UITableView.appearance().backgroundColor = tableBackgroundColor
}
