//
//  Color+Extension.swift
//  iosKintro
//  https://developer.apple.com/design/human-interface-guidelines/ios/visual-design/color/
//
//  Created by zahn on 15/09/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

extension Color {
    
    static let theme = ColorTheme()
}

struct ColorTheme {
    let primary = Color("Primary")
    let primaryVariant = Color("PrimaryVariant")
    let onPrimary = Color("OnPrimary")
    
    let secondary = Color("Secondary")
    let secondaryVariant = Color("SecondaryVariant")
    let onSecondary = Color("OnSecondaryVariant")
    
    let error = Color("Error")
    let onError = Color("OnError")
    
    let background = Color("Background")
    let onBackground = Color("OnBackground")
    
    let surface = Color("Surface")
    let onSurface = Color("OnSurface")
}
