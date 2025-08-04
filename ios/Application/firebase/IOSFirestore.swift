//
//  FirebaseApi.swift
//  IOSApp
//
//  Created by Sujan Poudel on 04/08/2025.
//  Copyright © 2025 orgName. All rights reserved.
//
import AppCore
import FirebaseFirestore

class IOSFirestore : AppCore.Firestore {
  
  var firestore = Firestore.firestore()
  
  init(){
    
  }
  
  func setDocument(documentPath:String, data: [String : Any]) {
    firestore.document(documentPath)
      .setData(data)
  }
  
  func addDocument(collection:String, data: [String : Any]) {
    firestore.collection(collection)
      .addDocument(data: data)
  }
  
  
  
  func listenForCollectionSnapShot(collection:String, onUpdate:@escaping ([[String : Any]]) -> Void) -> () -> Void {
  
    let listner = firestore.collection(collection)
      .addSnapshotListener { snapshot, error in
        if let documents = snapshot?.documents.map { $0.data() } {
          onUpdate(documents)
        }
      }
    
    return {
      listner.remove()
    }
  }

}
