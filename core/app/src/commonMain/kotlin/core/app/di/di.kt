package core.app.di

import org.koin.dsl.module
import core.data.di.dataModule
import core.features.chat.di.chatModule
import kotlin.native.HiddenFromObjC

@HiddenFromObjC
fun appModule() = module {
  includes(
    dataModule(),
    viewModelModule(),
    chatModule()
  )
}
