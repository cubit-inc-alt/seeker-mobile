package core.firestore.di

import core.firestore.Firestore
import core.firestore.FireStoreApiAndroid
import org.koin.dsl.module


actual fun fireStoreDatabaseModule() = module {
  single<Firestore> { FireStoreApiAndroid() }
}
