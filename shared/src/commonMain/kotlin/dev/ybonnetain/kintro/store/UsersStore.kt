package dev.ybonnetain.kintro.store

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

import dev.ybonnetain.kintro.models.User
import dev.ybonnetain.kintro.repositories.UsersRepository

data class UsersState(
    val users: List<User>,
    val loading: Boolean,
) : RState

sealed class UsersAction : Action {
    object Load : UsersAction()
    data class Data(val users: List<User>) : UsersAction()
    data class Loading(val loading: Boolean) : UsersAction()
    data class Error(val error: Exception) : UsersAction()
}

sealed class UsersSideEffect : Effect {
    data class Error(val error: Exception) : UsersSideEffect()
}

class UsersStore : Store<UsersState, UsersAction, UsersSideEffect>,
    CoroutineScope by CoroutineScope(Dispatchers.Main),
        KoinComponent
{
    private val repository: UsersRepository by inject()
    private val state = MutableStateFlow(getInitialState())
    private val sideEffect = MutableSharedFlow<UsersSideEffect>()

    override fun observeState(): StateFlow<UsersState> = state
    override fun observeSideEffect(): Flow<UsersSideEffect> = sideEffect

    override fun dispatch(action: UsersAction) {
        val oldState = state.value

        val nextState = when(action) {
            is UsersAction.Load -> {
                launch { loadUsers() }
                oldState
            }
            is UsersAction.Data -> {
                UsersState(loading = false, users = action.users)
            }
            is UsersAction.Loading -> {
                UsersState(loading = action.loading, users = oldState.users)
            }
            is UsersAction.Error -> {
                launch { sideEffect.emit(UsersSideEffect.Error(action.error)) }
                oldState
            }
        }

        if (nextState != oldState) {
            state.value = nextState
        }
    }

    private suspend fun loadUsers() {
        try {
            dispatch(UsersAction.Loading(true))
            dispatch(UsersAction.Data(repository.getUsers()))
        } catch (e: Exception) {
            dispatch(UsersAction.Error(e))
        } finally {
            dispatch(UsersAction.Loading(false))
        }
    }

    companion object {
        fun getInitialState() = UsersState(users = emptyList(), loading = false)
    }
}
