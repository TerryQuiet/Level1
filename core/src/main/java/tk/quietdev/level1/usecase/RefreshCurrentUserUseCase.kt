package tk.quietdev.level1.usecase

import tk.quietdev.level1.common.Resource
import tk.quietdev.level1.data.UsersRepository

class RefreshCurrentUserUseCase(
    private val usersRepository: UsersRepository
) {
    suspend operator fun invoke(): Resource<Boolean> {
        return usersRepository.refreshCurrentUser()
    }
}