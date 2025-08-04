//
//  CoreInitializer.swift
//  IOSApp
//
//  Created by Sujan Poudel on 04/08/2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import AppCore

func initializeCore(){
  FirestoreFactory.shared.firestore = IOSFirestore()
}
