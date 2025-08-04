package core.features.chat.di

import core.features.chat.ChatViewModel
import org.koin.dsl.module


fun chatModule() = module {
  factory { ChatViewModel() }
}

