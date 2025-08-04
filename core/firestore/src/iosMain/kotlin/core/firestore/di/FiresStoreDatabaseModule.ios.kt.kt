package core.firestore.di

import core.firestore.Firestore
import core.firestore.FirestoreFactory
import org.koin.dsl.module


actual fun fireStoreDatabaseModule() = module {
  single<Firestore> { FirestoreFactory.firestore }
}
