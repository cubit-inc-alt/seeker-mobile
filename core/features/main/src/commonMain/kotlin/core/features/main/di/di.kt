package core.features.main.di

import core.features.chat.ChatViewModel
import org.koin.dsl.module


fun mainModule() = module {
  factory { ChatViewModel() }
}

