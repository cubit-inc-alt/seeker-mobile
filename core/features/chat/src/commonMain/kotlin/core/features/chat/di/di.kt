package core.features.chat.di

import core.data.repository.AuthRepository
import core.data.repository.ChatRepository
import core.features.chat.ChatViewModel
import core.firestore.di.fireStoreDatabaseModule
import org.koin.dsl.module


fun chatModule() = module {
  includes(
    fireStoreDatabaseModule()
  )
  factory {
    ChatViewModel(
      get<ChatRepository>()
    )
  }
}

