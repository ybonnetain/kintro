//
//  EnvironmentValues+Extension.swift
//  EnvironmentValues+Extension
//
//  Created by zahn on 15/09/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

extension EnvironmentValues {
    
    // Detect if Xcode is running SwiftUI previews
    // This will be useful to conditionally invoke view models and such
    //
    var isPreview: Bool {
        #if DEBUG
        return ProcessInfo.processInfo.environment["XCODE_RUNNING_FOR_PREVIEWS"] == "1"
        #else
        return false
        #endif
    }
}
