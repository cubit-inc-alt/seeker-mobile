//
//  Config.swift
//  IOSApp
//
//  Created by Sujan Poudel on 04/08/2025.
//  Copyright © 2025 Cubit.Inc. All rights reserved.
//

import Foundation

struct Configuration{
  static var firebaseConfigFile = Bundle.main.infoDictionary!["FIREBASE_CONFIG_FILE"] as? String
}
