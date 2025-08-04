import SwiftUI
import UIKit
import Firebase 

@main
struct iOSApp: App {

  init(){
      
    if let firebaseConfig = Configuration.firebaseConfigFile,
       let path = Bundle.main.path(forResource: firebaseConfig, ofType: "plist"),
       let firebaseOptions = FirebaseOptions(contentsOfFile: path ){
        
      FirebaseApp.configure(options: firebaseOptions)
    }else{
      print("Unable to initialize firebase")
    }

      initializeCore()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
