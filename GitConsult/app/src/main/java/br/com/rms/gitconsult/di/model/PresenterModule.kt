package br.com.rms.gitconsult.di.model

import br.com.rms.gitconsult.data.repository.UserRepository
import br.com.rms.gitconsult.ui.login.LoginContrac
import br.com.rms.gitconsult.ui.login.LoginPresenter
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {

    @Provides
    fun provideLoginPresenter(userRepository: UserRepository): LoginContrac.Presenter = LoginPresenter(userRepository)
}